package com.springbook.biz.impl;

import java.util.List;

import com.springbook.biz.board.BoardVO;

public interface BoardService {

	//CRUD
	void insertBoard(BoardVO vo);

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(BoardVO vo);

}