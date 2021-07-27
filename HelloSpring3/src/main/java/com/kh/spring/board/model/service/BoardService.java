package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Board;

public interface BoardService {
	List<Board> selectBoardList(int cPage,int numPerPage);
	int selectBoardCount();
	int insertBoard(Board b);
	Board selectBoardView(int no);
}
