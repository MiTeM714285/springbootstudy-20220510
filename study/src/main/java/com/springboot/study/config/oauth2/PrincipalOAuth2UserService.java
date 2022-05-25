package com.springboot.study.config.oauth2;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("oAuth2User -> " + oAuth2User);
		System.out.println("Attributes -> " + oAuth2User.getAttributes());
		
		String provider = userRequest.getClientRegistration().getRegistrationId(); // provider 생성
		
		Map<String, Object> attributes = oAuth2User.getAttributes(); // OAuth2 관련 자신의 정보들
		
		String oAuth2_username = createOAuth2Username(provider, attributes);
		User userEntity = userRepository.findOAuth2UserByOAuth2Username(oAuth2_username);
		if(userEntity == null) { // 해당 객체가 비어있다면
			// 회원가입 진행
			User user = User.builder()
					.email((String)attributes.get("email"))
					.name((String)attributes.get("name"))
					.username(oAuth2_username)
					.oauth2_username(oAuth2_username)
					.password(new BCryptPasswordEncoder().encode("1234")) // 비밀번호는 임시로 부여
					.roles("ROLE_USER")
					.provider(provider)
					.build();
			

			if(userRepository.insertUser(user) == 0) { // user_mst DB에 insert
				throw new OAuth2AuthenticationException(new OAuth2Error("400", "회원가입 실패","/auth/signup"), "회원가입 실패");
			}
			// user.xml의  useGeneratedKeys="true" keyProperty="user_code" 로 인하여 usercode 값 자동부여
			userEntity = user;
		}
		return new PrincipalDetails(userEntity, attributes);
		
	}
	
	private String createOAuth2Username(String provider, Map<String, Object> attributes) {
		if(provider.equals("naver")) {
			return null;
		} else if(provider.equals("google")) {
			return provider + "_" + (String)attributes.get("sub"); // google은 id개념을 sub라는 명칭으로 사용
		} else {
			return null;
		}
	}
}
