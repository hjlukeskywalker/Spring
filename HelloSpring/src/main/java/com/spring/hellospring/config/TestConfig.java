package com.spring.hellospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.hellospring.Department;
import com.spring.hellospring.Employee;

@Configuration	

public class TestConfig {
	//servlet-context.xml의 역할을 할수 있는 클래스
	
	
	@Bean//springcontext에서 활용하는 bean이 등록됨
	public Employee getEmp() {
		return new Employee();
	}
	@Bean
	public Department getDept() {
		Department d=new Department();
		d.setDeptCount(20);
		d.setDeptName("자바학부");
		return d;
	}
}
