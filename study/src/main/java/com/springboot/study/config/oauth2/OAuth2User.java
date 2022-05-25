package com.springboot.study.config.oauth2;

import lombok.Builder;

@Builder
public class OAuth2User {
	private String oAuth2_username;
	private String email;
	private String name;
	private String role;
	private String provider;
}
