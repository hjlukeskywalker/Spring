package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoginCheckAspect {
	
	//memo패키지 메소드 전부
	
	//pointcut 등록
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void mMethod() {}
	
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void all() {}
	//advisor 등록
	@Around("all()")
	public Object aroundTest(ProceedingJoinPoint join) throws Throwable{
		
		//join.proceed() -> 매소드 실행을 기준으로 전/후 프로시드 실행전 before 프로시드 실행후 after
		log.debug("===============around 매소드 실행전===============");
		Signature sig=join.getSignature();
		log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
		log.debug("-----------------------------------------");
		
		Object obj=join.proceed();
		return obj;
		
	}
}
