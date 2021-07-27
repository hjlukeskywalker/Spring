package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardDao {
	List<Board> selectBoardList(SqlSessionTemplate sqlSession, int cPage, int numPerpage);
	
	int selectBoardCount(SqlSessionTemplate sqlSession);

	int insertBoard(SqlSessionTemplate sqlSession,Board b);
	int insertAttachment(SqlSessionTemplate session,Attachment a);
	
	Board selectBoardView(SqlSessionTemplate sqlSession, int no);
}
