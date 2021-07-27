package com.kh.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MemoDaoImpl implements MemoDao {

	
	@Override
	public int memoInsert(SqlSessionTemplate sqlSession, Map param) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memo.memoInsert", param);
	}

	@Override
	public List<Map> selectMemoList(SqlSessionTemplate sqlsession) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("memo.selectMemoList");
	}
	

	
}


