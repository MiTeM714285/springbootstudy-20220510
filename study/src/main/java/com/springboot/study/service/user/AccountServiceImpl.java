package com.springboot.study.service.user;

import org.springframework.stereotype.Service;

import com.springboot.study.domain.board.BoardRepository;
import com.springboot.study.domain.user.UserDtl;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final UserRepository userRepository;

	@Override
	public boolean updateProfileImg(UserDtl userdtl) throws Exception {
		return userRepository.updateProfileImg(userdtl) > 0;
	}


}
