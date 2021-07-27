package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardList(SqlSessionTemplate sqlSession, int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.selectBoardList",null, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}
	@Override
	public int selectBoardCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("board.selectBoardCount");
	}

	@Override
	public int insertBoard(SqlSessionTemplate sqlSession, Board b) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.insertBoard",b);
	}
	@Override
	public int insertAttachment(SqlSessionTemplate sqlSession, Attachment a) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.insertAttachment",a);
	}
	@Override
	public Board selectBoardView(SqlSessionTemplate sqlSession, int no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.selectBoardView",no);
	}
	
}
