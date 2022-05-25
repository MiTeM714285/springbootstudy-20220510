package com.springboot.study.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
	
	private int usercode;
	private String email;
	private String name;
	private String username;
	private String password;
	private String roles; // ROLE_USER,ROLE_MANAGER,ROLE_ADMIN
	private String oauth2_username;
	private String provider;
	private String profile_img_url;
	
	public List<String> getRoleList() {
		if(this.roles.length() > 0) { // 권한이 부여되었다면
			return Arrays.asList(this.roles.split(",")); // ',' 로 구분지어 리스트로 반환
		}
		return new ArrayList<String>(); // 비어있는 리스트 반환
	}

}
