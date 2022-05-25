package com.springboot.study.service.user;

import com.springboot.study.domain.user.UserDtl;

public interface AccountService {
	public boolean updateProfileImg(UserDtl userdtl) throws Exception;
}
