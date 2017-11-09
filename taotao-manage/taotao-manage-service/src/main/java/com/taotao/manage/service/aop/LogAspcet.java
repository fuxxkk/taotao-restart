package com.taotao.manage.service.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspcet {
	
	private static final Logger LOOGER = LoggerFactory.getLogger(LogAspcet.class);
	
	private ThreadLocal<Long> threadlocal = new ThreadLocal<>();
	
	@Pointcut(value="execution(* com.taotao.manage.service.*.*(..))")
	public void recordLog() {
		
	}
	
	@Around("recordLog()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		
		//获取被增强的方法相关信息
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		//获取目标方法
		Method targetmethod = methodSignature.getMethod();
		LOOGER.info("接口类是:"+targetmethod.getDeclaringClass().getName()+",实现类是:"+pjp.getTarget()+",方法是:"+targetmethod.getName());
		
		//获取参数
		Object[] args = pjp.getArgs();
		if(args!=null&&args.length>0) {
			for(int i=0;i<args.length;i++) {
				LOOGER.info("参数是"+i+":"+pjp.getArgs()[i]);
			}
		}
		
		long startTime = System.currentTimeMillis();
		threadlocal.set(startTime);
		return pjp.proceed();
	}
	
	@After("recordLog()")
	public void after() {
		Long start = threadlocal.get();
		long end = System.currentTimeMillis();
		LOOGER.info("用时:"+(end-start)+"ms");
	}
	
}
