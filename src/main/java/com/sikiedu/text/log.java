package com.sikiedu.text;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/*
 * 前置通知（@Before,在执行方法之前，参数为JoinPoint）

后置通知(@After,无论方法抛不抛异常都会执行，所以获取不到方法的返回值。参数为JoinPoint)

返回通知（@AfterReturning,在方法正常结束后执行，可以获取到方法的返回值。参数为JoinPoint和result(Object)）

异常通知（@AfterThrowing,在方法抛出异常时执行，可以获取到异常对象，且可以指定在出现特定异常时执行的代码，参数为JoinPoint何Exception）

环绕通知（@Around,环绕通知需要携带的类型为ProceedingJoinPoint类型的参数, 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法,且环绕通知必须有返回值，返回值即目标方法的返回值)*/


public class log {
	public void before()
	{
		System.out.println("方法执行前");
	}
	
	public void after()
	{
		System.out.println("方法之后");
	}
	
	/**
	 * 返回通知（在方法正常结束执行的代码）
	 * 返回通知可以访问到方法的返回值！
     * @param joinPoint
     */
	public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知:this method "+methodName+" end.result<"+result+">");
    }
	
	/**
	 * 异常通知（方法发生异常执行的代码）
             * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
	public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常通知:this method "+methodName+" end.ex message<"+ex+">");
    }
	
	
	
	/**
	     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
	     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
	     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     * @param point
     */
    public Object aroundMethod(ProceedingJoinPoint point){

        Object result = null;
 
        try {
            //前置通知
            System.out.println("环绕通知中的前置方法");
            //执行目标方法
            result = point.proceed();
            //返回通知
            System.out.println("环绕通知中的返回通知"+result);
        } catch (Throwable e) {
            //异常通知
            System.out.println("环绕通知中的异常通知"+e);
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("环绕通知中的后置方法");
        return result;
    }
}
