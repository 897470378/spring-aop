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
 * ǰ��֪ͨ��@Before,��ִ�з���֮ǰ������ΪJoinPoint��

����֪ͨ(@After,���۷����ײ����쳣����ִ�У����Ի�ȡ���������ķ���ֵ������ΪJoinPoint)

����֪ͨ��@AfterReturning,�ڷ�������������ִ�У����Ի�ȡ�������ķ���ֵ������ΪJoinPoint��result(Object)��

�쳣֪ͨ��@AfterThrowing,�ڷ����׳��쳣ʱִ�У����Ի�ȡ���쳣�����ҿ���ָ���ڳ����ض��쳣ʱִ�еĴ��룬����ΪJoinPoint��Exception��

����֪ͨ��@Around,����֪ͨ��ҪЯ��������ΪProceedingJoinPoint���͵Ĳ���, ����֪ͨ����ǰ�á����á����ء��쳣֪ͨ��ProceedingJoinPoin ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��,�һ���֪ͨ�����з���ֵ������ֵ��Ŀ�귽���ķ���ֵ)*/

															/*ע�ⷽʽʵ��*/


@Aspect			//�ñ�ǩ��LoggerAspect������Ϊһ������
@Order(1)		//������������ȼ�������ж�����棬��ͨ���������ȼ����������ִ��˳����ֵԽС�����ȼ�Խ�ߣ�
@Component 		//�ñ�ǩ����ע�뵽IOC������
public class log2 {
	 /**
	     * ����һ�����������������������ʽ��������һ�㲻��Ҫ�����������
	     * ʹ��@Pointcut�����������ʽ
	     * �����ֱ֪ͨ��ʹ�÷����������õ�ǰ���е���ʽ�������������ʹ�ã����ϰ�������
     */
	
	/*
	 * ����㣬��Ҫ���߷�����ʲôȥִ�� expression="execution(* com.spring.service.impl.*.*(..))"
	 * ��һ��* ��ʾ���еķ���ֵ��Ȼ����ǰ��� �ڶ���*��ʾ���е������ ������*��ʾ��������еķ���
	 * ���ĸ�*��ʾ���з�������Ĵ������ķ��������ǲ��������ķ���
	 */
	@Pointcut("execution(* com.sikiedu.beanImpl.*.*(..))")
	public void declearJoinPointExpression(){}
	
	
	 /**
	  * ǰ��֪ͨ
     * @param joinPoint
     * value:���������ķ���������ʡ��value
     */
    @Before("declearJoinPointExpression()")     //�ñ�ǩ�����η�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ��
	public void before(JoinPoint joinPoint)
	{
		System.out.println("����ִ��ǰ");
	}
	
    
    /**
               * ����֪ͨ�����۷����Ƿ����쳣����ִ��,���Է��ʲ��������ķ���ֵ��
     * @param joinPoint
     */
    @After("declearJoinPointExpression()")
	public void after(JoinPoint joinPoint)
	{
		System.out.println("����֮��");
	}
	

    /**
	     * ����֪ͨ���ڷ�����������ִ�еĴ��룩
	     * ����֪ͨ���Է��ʵ������ķ���ֵ��
	 * returning:��дreturning����������
     * @param joinPoint
     */
    @AfterReturning(value = "declearJoinPointExpression()",returning = "result")
	public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("����֪ͨ:this method "+methodName+" end.result<"+result+">");
    }
	
	
    
    /**
	     * �쳣֪ͨ�����������쳣ִ�еĴ��룩
	     * ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱִ�еĴ���
	  * throwing:��дthrowing����������
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "declearJoinPointExpression()",throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("�쳣֪ͨ:this method "+methodName+" end.ex message<"+ex+">");
    }
	
	
	
	/**
	     * ����֪ͨ(��ҪЯ������ΪProceedingJoinPoint���͵Ĳ���)
	     * ����֪ͨ����ǰ�á����á����ء��쳣֪ͨ��ProceedingJoinPoin ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��
	     * �һ���֪ͨ�����з���ֵ������ֵ��Ŀ�귽���ķ���ֵ
     * @param point
     */	
    @Around("declearJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point){

        Object result = null;
 
        try {
            //ǰ��֪ͨ
            System.out.println("����֪ͨ�е�ǰ�÷���");
            //ִ��Ŀ�귽��
            result = point.proceed();
            //����֪ͨ
            System.out.println("����֪ͨ�еķ���֪ͨ"+result);
        } catch (Throwable e) {
            //�쳣֪ͨ
            System.out.println("����֪ͨ�е��쳣֪ͨ"+e);
            throw new RuntimeException(e);
        }
        //����֪ͨ
        System.out.println("����֪ͨ�еĺ��÷���");
        return result;
    }
}
