package com.kh.spring.memo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService service;
	
	@RequestMapping("memo/memo.do") //ModelandView mv 모델이랑 뷰 둘다 같이 넣을 수 있음
	public ModelAndView memo(ModelAndView mv) {
		mv.addObject("list",service.selectMemoList());
		mv.setViewName("memo/memo");
		return mv;
	}
	@RequestMapping("memo/memoInsert.do")
	public String memoInsert(@RequestParam Map param) {
		int result=service.memoInsert(param);
		return "redirect:/memo/memo";
	}

}
