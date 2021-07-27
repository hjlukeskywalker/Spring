package com.spring.hellospring.demo.model.serivce;

import java.util.List;

import com.spring.hellospring.demo.model.vo.Dev;

public interface DemoService {
	
	int insertDemo(Dev d);


	List<Dev> selectDemoList();
}
