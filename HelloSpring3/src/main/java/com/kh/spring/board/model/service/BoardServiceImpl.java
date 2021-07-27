package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BoardDao dao;
	
	@Override
	public List<Board> selectBoardList(int cPage,int numPerpage) {
		// TODO Auto-generated method stub
		return dao.selectBoardList(sqlSession,cPage, numPerpage);
	}
	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(sqlSession);
	}
	@Override
	/*@Transactional*/
	public int insertBoard(Board b) throws RuntimeException {
		// TODO Auto-generated method stub
		int result=dao.insertBoard(sqlSession,b);
		if(result>0) {
			if(b.getAttachments().size()>0) {
				for(Attachment a: b.getAttachments()) {
				try {	
					a.setBoardNo(b.getBoardNo());
					dao.insertAttachment(sqlSession, a);
				}catch(RuntimeException e){
					throw new RuntimeException("작성실패");
				}
			}
		}
	}
		return result;
	}
	@Override
	public Board selectBoardView(int no) {
		// TODO Auto-generated method stub
		return dao.selectBoardView(sqlSession,no);
	}
	
		}