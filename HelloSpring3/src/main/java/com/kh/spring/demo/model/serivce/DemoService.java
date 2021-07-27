package com.kh.spring.demo.model.serivce;

import java.util.List;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoService {
	
	int insertDemo(Dev d);


	List<Dev> selectDemoList();
}
