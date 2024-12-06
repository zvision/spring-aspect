package com.alaf.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	
	
	@Before("execution(* com.alaf.controller.CustomerController.saveCustomer(..))")
	public void logBeforeControllerAdvice(JoinPoint joinPoint) {
		log.info("Before running loggingAdvice on method {}", joinPoint.toString()); 
		System.out.println("Arguments Passed=" + Arrays.toString(joinPoint.getArgs()));
				
	}
	
	@After("execution(* com.alaf.controller.CustomerController.saveCustomer(..))")
	public void logAfterControllerAdvice(JoinPoint joinPoint) {
		log.info("After running loggingAdvice on method {}", joinPoint.toString()); 
				
	}
	

	@Around("execution(* com.alaf.controller.*.*(..))")
	public Object logAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
		
		ObjectMapper mapper = new ObjectMapper();
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		
		Object[] args = jp.getArgs();
		log.info("Inside {} calss method {}, with request: {}", 
				new Object[] {className, methodName, mapper.writeValueAsString(args) });
		Object response = jp.proceed();
		log.info("Inside {} calss method {}, with response: {}", 
				new Object[] {className, methodName, mapper.writeValueAsString(response) });
		
		return response;
	}
	
	@AfterThrowing (pointcut = "execution(* com.alaf.service.*.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
		log.info("****LoggingAspect.logAfterThrowingAllMethods(), message: {}", ex.getMessage());
    }
	
}
