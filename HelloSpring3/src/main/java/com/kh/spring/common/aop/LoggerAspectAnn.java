package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspectAnn {
	//1.pointCUT 등록
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void memoMethod() {}
	
	@Pointcut("execution(* com.kh.spring..s*(..))")
	public void all() {};
	
	//2.advisor등록
	@Before("memoMethod()")
	public void loggerTest(JoinPoint jp) {
		log.debug("=====어노테이션으로 적용한 aop=======");
		log.debug("-------시작---------");
		Signature sig=jp.getSignature();
		log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
		log.debug("-------------------");
	}
	@After("memoMethod()")
	public void loggerTestAfter(JoinPoint jp) {
		Signature sig=jp.getSignature();
		log.debug("========= after AOP =============");
		log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
		log.debug("---------매소드종료휴----------");
	}
	@Around("all()")
	public Object aroundTest(ProceedingJoinPoint join) throws Throwable{
		
		//join.proceed() -> 매소드 실행을 기준으로 전/후 프로시드 실행전 before 프로시드 실행후 after
		log.debug("==========around 매소드 실행전 ==============");
		Signature sig=join.getSignature();
		log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
		log.debug("-----------------------------------------");
		
		Object obj=join.proceed();
		return obj;
		/*
		 * Object obj=join.proceed();
		 * 
		 * log.debug("==========around 매소드 실행 후=============");
		 * log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
		 * log.debug("------------------------------------------");
		 * 
		 * 
		 * Object[] param= join.getArgs(); for (Object p: param) { log.debug("파라미터:"
		 * +p.toString()); } return obj;
		 */
	}
	
	@Around("execution(* com.kh.spring..*Dao.*(..))")
	public Object checkRuntime(ProceedingJoinPoint join) throws Throwable{
		log.debug("============= dao 성능 측정=============");
		StopWatch stop=new StopWatch();
		stop.start();
		Object obj=join.proceed();
		log.debug("==== 측정결과====");
		stop.stop();
		Signature sig=join.getSignature();
		log.debug(sig.getName()+"소요시간: "+stop.getTotalTimeMillis()+"ms");
		return obj;
	}
}
