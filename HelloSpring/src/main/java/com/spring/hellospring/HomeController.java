package com.spring.hellospring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.hellospring.config.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	
	@Qualifier("emp1")
	private Employee e;
	
	@Autowired
	private Student s;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session,Locale locale, Model model, HttpServletResponse res) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//세션에 값넣고 컨트롤러에서 확인하기
		session.setAttribute("userId","admin");
		
		//COOKIE 생성하기
		Cookie c=new Cookie("choco","chip");
		c.setMaxAge(60*60*24);
		//res.addCookie(c);
		
		
		System.out.println(c);
		
		System.out.println(e);
		System.out.println(s);

		
		return "index";///WEB-INF/views/home.jsp
	}
	
}
