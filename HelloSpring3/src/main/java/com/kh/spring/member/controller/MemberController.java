package com.kh.spring.member.controller;

import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes("loginMember")
@Slf4j
public class MemberController {
	
	//private Logger logger=LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService service;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		return "member/memberEnroll";
	}
	//회원등록 서비스
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member m, Model model) {
		/*
		 * System.out.println(m); System.out.println("암호화전:"+m.getPassword());
		 */
		//BCryptPasswordEncoder 객체를 이용해서 문자열을 단방향 암호화하기
		//encode(암호화값)메소드 이용하면 됨! 끝!
		//System.out.println(pwEncoder.encode(m.getPassword()));
		log.debug("파리머터값{}",m);
		log.debug("암호화전:{}",m.getPassword());
		m.setPassword(pwEncoder.encode(m.getPassword()));
		
		int result=service.insertMember(m);
		System.out.println(result);
		model.addAttribute("msg",result>0?"회원가입성공":"회원가입실패");
		model.addAttribute("loc","/");
		return "common/msg";
	
	}
	//회원로그인로직 구현하기
	@RequestMapping("member/memberLogin.do")
		public String login(@RequestParam Map param, HttpSession session, Model model) {
		
		log.debug((String)param.get("password"));
		
		//비교를 위한 메소드가 있음.
		//matches(지금 입력받은 원본 값, 암호화된 값);-> 반환값은 boolean
		
		Member m=service.selectMember(param);
		String msg="로그인 실패";
		//System.out.println(param.get("password"));
		
		
		if(m!=null&&pwEncoder.matches((String)param.get("password"),m.getPassword())) {
		//session.setAttribute("loginMember", m);
		//로그인에 대한 정보를 session 객체를 이용해서
		//처리하지 않고 model 객체를 이용할 수 있음
		//클래스 선언부에 @SessionAttributes 어노테이션을 이용
		//@SessionAttributes(model의 키값)
		model.addAttribute("loginMember",m);
		msg="로그인성공";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc","/");
		return "common/msg";
	}
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, SessionStatus ss) {
		if(session!=null) session.invalidate();
		//model에서 추가해서 sessionAttribute추가한 데이터를 삭제되지않음
		//model에서 추가한 객체를 SessionStatus객체를 이용해서 관리
		if(!ss.isComplete()) {
			ss.setComplete();
		}
		
		//리다이렉트 해야함.
		//"redirect:주소"
		return "redirect:/";
	}
	//기본 요청 ajax 응답하기
	
	@RequestMapping("/member/checkUserId.do")
	public void checkUserId(@RequestParam Map param, Writer writer,HttpServletResponse res) throws Exception {
		log.info("{}",param);
		
		
		Member m=service.selectMember(param);
		
		//res.getWriter().append(m!=null?"false":"true");
		//writer.append(m!=null?"false":"true");
		new Gson().toJson(m,writer);
	}
	//jsonView를 통한 ajax처리하기
	//return 값이 ModelAndView 설정해야함.
	@RequestMapping("/member/checkIdJsonView.do")
	public ModelAndView jsonviewTest(@RequestParam Map param, ModelAndView mv) {
		
		Member m=service.selectMember(param);
		mv.addObject("isAble",m!=null?"false":"true");
		mv.addObject("su",20);
		mv.addObject("name","김태희포");
		
		//viewName 세팅시 등록한 JsonView의 id이름으로 설정해야함.
		mv.setViewName("jsonView");
		return mv;
	}
	
	//@ResponseBody로 ajax 처리하기
	//1. jackson databind 라이브러리를 받아온다.
	//2. Beanconfiguration.xml에서 jackson클래스를 converter로 등록해준다.
	//3. mapping된 매소드에 @ResponseBody 어노테이션 표시를 한다.
	//4. 원하는 DATA를 리턴값으로 설정한다.
	
	@RequestMapping("/member/responseBody.do")
	@ResponseBody
	public Member responseBody(@RequestParam Map param) {
		Member m=service.selectMember(param);
		List<Board> list=boardService.selectBoardList(1, 20);
		return m;
	}
}	


