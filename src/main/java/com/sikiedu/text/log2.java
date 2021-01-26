package com.sikiedu.text;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * 前置通知（@Before,在执行方法之前，参数为JoinPoint）

后置通知(@After,无论方法抛不抛异常都会执行，所以获取不到方法的返回值。参数为JoinPoint)

返回通知（@AfterReturning,在方法正常结束后执行，可以获取到方法的返回值。参数为JoinPoint和result(Object)）

异常通知（@AfterThrowing,在方法抛出异常时执行，可以获取到异常对象，且可以指定在出现特定异常时执行的代码，参数为JoinPoint何Exception）

环绕通知（@Around,环绕通知需要携带的类型为ProceedingJoinPoint类型的参数, 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法,且环绕通知必须有返回值，返回值即目标方法的返回值)*/

															/*注解方式实现*/


@Aspect			//该标签把LoggerAspect类声明为一个切面
@Order(1)		//设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component 		//该标签把类注入到IOC容器中
public class log2 {
	 /**
	     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码
	     * 使用@Pointcut声明切入点表达式
	     * 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
     */
	
	/*
	 * 切入点，需要告诉方法在什么去执行 expression="execution(* com.spring.service.impl.*.*(..))"
	 * 第一个* 表示所有的返回值，然后就是包名 第二个*表示所有的类对象 第三个*表示类对象所有的方法
	 * 第四个*表示所有方法下面的带参数的方法或者是不带参数的方法
	 */
	@Pointcut("execution(* com.sikiedu.beanImpl.*.*(..))")
	public void declearJoinPointExpression(){}
	
	
	 /**
	  * 前置通知
     * @param joinPoint
     * value:上面切入点的方法名可以省略value
     */
    @Before("declearJoinPointExpression()")     //该标签声明次方法是一个前置通知：在目标方法开始之前执行
	public void before(JoinPoint joinPoint)
	{
		System.out.println("方法执行前");
	}
	
    
    /**
               * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
    @After("declearJoinPointExpression()")
	public void after(JoinPoint joinPoint)
	{
		System.out.println("方法之后");
	}
	

    /**
	     * 返回通知（在方法正常结束执行的代码）
	     * 返回通知可以访问到方法的返回值！
	 * returning:填写returning参数的名称
     * @param joinPoint
     */
    @AfterReturning(value = "declearJoinPointExpression()",returning = "result")
	public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知:this method "+methodName+" end.result<"+result+">");
    }
	
	
    
    /**
	     * 异常通知（方法发生异常执行的代码）
	     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
	  * throwing:填写throwing参数的名称
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "declearJoinPointExpression()",throwing = "ex")
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
    @Around("declearJoinPointExpression()")
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
