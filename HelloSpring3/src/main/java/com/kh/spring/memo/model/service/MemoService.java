package com.kh.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoService  {
	int memoInsert(Map param);
	List<Map> selectMemoList();
}
