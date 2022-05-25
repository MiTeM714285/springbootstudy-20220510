package com.springboot.study.web.dto.board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springboot.study.domain.board.BoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertReqDto {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotNull // int형식은 @NotBlank 사용불가
	private int usercode;
	
	public BoardMst toBoardMstEntity() {
		return BoardMst.builder()
				.board_title(title)
				.board_content(content)
				.board_writer(usercode)
				.build();
	}

}