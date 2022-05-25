package com.springboot.study.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springboot.study.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // ROLE 관련
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); // 권한을 담는 리스트
		
		user.getRoleList().forEach(r -> { // r은 매개변수로써 Role, {}가 있으면 return이 있거나 없거나, {}가 없으면 return이 있음
			authorities.add(() -> r); // 이는 아래의 형태를 람다식으로 축소한 것. 메소드가 하나만 존재하기에 람다식이 가능
			/*
			authorities.add(new GrantedAuthority() {
				
				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return r;
				}
			});
			*/
		});
		authorities.forEach(r -> {System.out.println("권한 : " + r.getAuthority());});// 부여된 권한들 출력해보기
	 	return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되었는지 확인 (false일시 로그인불가)
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 비밀번호가 지정한 횟수 이상 틀릴시 잠김 (false일시 로그인불가)
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 자격증명 만료시 계정사용 불가 (false일시 로그인불가)
		// 해당 계정의 라이센스 별도 발급
		return true;
	}

	@Override
	public boolean isEnabled() { // 휴면계정 (false일시 로그인불가)
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String)attributes.get("name");
	}

}
