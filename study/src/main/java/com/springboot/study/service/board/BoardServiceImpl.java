 package com.springboot.study.service.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.study.domain.board.BoardMst;
import com.springboot.study.domain.board.BoardRepository;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;
import com.springboot.study.web.dto.board.BoardUpdateReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository; // @RequiredArgsConstructor가 @Autowired 역할, final 키워드 필수.

	@Override
	public List<BoardRespDto> getBoardListAll() throws Exception {
		List<BoardRespDto> boardRespDtos = new ArrayList<BoardRespDto>();
		List<Map<String, Object>> boardListAll = boardRepository.getBoardListAll();
		for(Map<String, Object> boardMap : boardListAll) {
			boardRespDtos.add(BoardRespDto.builder()
					.boardCode((Integer)(boardMap.get("board_code")))
					.title((String)(boardMap.get("board_title")))
					.content((String)(boardMap.get("board_content")))
					.usercode((Integer)(boardMap.get("board_writer")))
					.boardCount((Integer)(boardMap.get("board_count")))
					.boardCountAll((Long)(boardMap.get("board_count_all")))
					.username((String)(boardMap.get("board_username")))
					.build());
		}
		return boardRespDtos;
	}

	@Override
	public List<BoardRespDto> getBoardListByPage(int index) throws Exception {
		// TODO Auto-generated method stub
		List<BoardRespDto> boardRespDtos = new ArrayList<BoardRespDto>();
		List<Map<String, Object>> boardListAll = boardRepository.getBoardListByPage((index - 1) * 5); // index가 1일때는 0~4페이지, 2일때는 5~9페이지
		for(Map<String, Object> boardMap : boardListAll) {
			boardRespDtos.add(BoardRespDto.builder()
					.boardCode((Integer)(boardMap.get("board_code")))
					.title((String)(boardMap.get("board_title")))
					.content((String)(boardMap.get("board_content")))
					.usercode((Integer)(boardMap.get("board_writer")))
					.boardCount((Integer)(boardMap.get("board_count")))
					.boardCountAll((Long)(boardMap.get("board_count_all")))
					.username((String)(boardMap.get("board_username")))
					.build());
		}
		return boardRespDtos;
	}

	@Override
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception {
		BoardMst boardMst = boardInsertReqDto.toBoardMstEntity();
		int result = boardRepository.insertBoard(boardMst);
		if(result > 0) {
			return boardMst.getBoard_code(); // 게시글 삽입뒤 즉시 해당 게시글로 리다이렉트를 위한 Board_code()를 얻기
		} else {
			return 0;
		}
	}

	@Override
	public BoardRespDto getBoardByBoardCode(int boardCode) throws Exception {
		Map<String, Object> boardMap = boardRepository.getBoardByBoardCode(boardCode);
		return BoardRespDto.builder()
				.boardCode((Integer)(boardMap.get("board_code")))
				.title((String)(boardMap.get("board_title")))
				.content((String)(boardMap.get("board_content")))
				.usercode((Integer)(boardMap.get("board_writer")))
				.boardCount((Integer)(boardMap.get("board_count")))
				.username((String)(boardMap.get("board_username")))
				.build();
	}

	@Override
	public int updateBoard(int boardCode, BoardUpdateReqDto boardupdateReqDto) throws Exception {
		BoardMst boardMst = boardupdateReqDto.toBoardMstEntity(boardCode);
		return boardRepository.updateBoard(boardMst) > 0 ? boardCode : 0;  // 게시글 수정뒤 즉시 해당 게시글로 리다이렉트를 위한 boardCode반환
	}

	@Override
	public int deleteBoard(int boardCode) throws Exception {
		return boardRepository.deleteBoard(boardCode);
	}


}
