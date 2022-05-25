package com.springboot.study.service.board;

import java.util.List;

import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;
import com.springboot.study.web.dto.board.BoardUpdateReqDto;

public interface BoardService {
	
	public List<BoardRespDto> getBoardListAll() throws Exception;
	public List<BoardRespDto> getBoardListByPage(int index) throws Exception;
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception;
	public int updateBoard(int boardCode, BoardUpdateReqDto boardupdateReqDto) throws Exception;
	public int deleteBoard(int boardCode) throws Exception;
	public BoardRespDto getBoardByBoardCode(int boardCode) throws Exception;

}
