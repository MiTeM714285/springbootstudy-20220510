package com.springboot.study.service.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	@Value("${file.path}") // application.yml에서의 설정을 따름
	private String filePath; // @Value 어노테이션을 따라, filePath의 값을 지정
	private final UserRepository userRepository;

	@Override
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails){
		if(file != null) {
			
			String originalFileName = file.getOriginalFilename();
			String tempFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
			Path uploadPath = Paths.get(filePath, "profile/" + tempFileName); // filePath까지의 경로 다음의 profile 디렉토리에 tempFileName
			
			File f = new File(filePath + "profile");
			if (!f.exists()) { // 해당 경로가 존재하지 않는다면
				f.mkdirs(); // 경로 생성
			}
			
			try {  // 파일 쓰기
				Files.write(uploadPath, file.getBytes());
				User user = principalDetails.getUser();
				user.setProfile_img_url(tempFileName);
				return userRepository.updateProfileImg(user) > 0 ? true : false;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}


}
