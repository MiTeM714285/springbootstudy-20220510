package com.springboot.study.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
		
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/list") // 게시글 리스트
	public String boardList() {
		return "board/board-list";
	}
	
	@GetMapping("/auth/signin") // 로그인
	public String signin() {
		return "auth/signin";
	}
	
	@GetMapping("/user/account/mypage")
	public String mypage() {
		return "account/mypage";
	}
	
	@GetMapping("/board-info/{boardCode}")
	public String boardDtl(@PathVariable int boardCode) {
		return "board/board-dtl";
		
	}
	
	@GetMapping("/board")
	public String boardInsert() {
		return "board/board-insert";
	}
	
	@GetMapping("/board/{boardCode}")
	public String boardUpdate() {
		return "board/board-update";
	}
}