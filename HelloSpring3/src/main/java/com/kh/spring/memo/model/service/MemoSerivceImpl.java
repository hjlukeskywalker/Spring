package com.kh.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.vo.Member;
import com.kh.spring.memo.model.dao.MemoDao;
@Service
public class MemoSerivceImpl implements MemoService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemoDao dao;
	
	@Override
	public int memoInsert(Map param) {
		// TODO Auto-generated method stub
		return dao.memoInsert(sqlSession, param );
	}

	@Override
	public List<Map> selectMemoList() {
		// TODO Auto-generated method stub
		return dao.selectMemoList(sqlSession);
	}
	
	

}
