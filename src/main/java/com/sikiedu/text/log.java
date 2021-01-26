package com.sikiedu.text;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/*
 * ǰ��֪ͨ��@Before,��ִ�з���֮ǰ������ΪJoinPoint��

����֪ͨ(@After,���۷����ײ����쳣����ִ�У����Ի�ȡ���������ķ���ֵ������ΪJoinPoint)

����֪ͨ��@AfterReturning,�ڷ�������������ִ�У����Ի�ȡ�������ķ���ֵ������ΪJoinPoint��result(Object)��

�쳣֪ͨ��@AfterThrowing,�ڷ����׳��쳣ʱִ�У����Ի�ȡ���쳣�����ҿ���ָ���ڳ����ض��쳣ʱִ�еĴ��룬����ΪJoinPoint��Exception��

����֪ͨ��@Around,����֪ͨ��ҪЯ��������ΪProceedingJoinPoint���͵Ĳ���, ����֪ͨ����ǰ�á����á����ء��쳣֪ͨ��ProceedingJoinPoin ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��,�һ���֪ͨ�����з���ֵ������ֵ��Ŀ�귽���ķ���ֵ)*/


public class log {
	public void before()
	{
		System.out.println("����ִ��ǰ");
	}
	
	public void after()
	{
		System.out.println("����֮��");
	}
	
	/**
	 * ����֪ͨ���ڷ�����������ִ�еĴ��룩
	 * ����֪ͨ���Է��ʵ������ķ���ֵ��
     * @param joinPoint
     */
	public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("����֪ͨ:this method "+methodName+" end.result<"+result+">");
    }
	
	/**
	 * �쳣֪ͨ�����������쳣ִ�еĴ��룩
             * ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱִ�еĴ���
     * @param joinPoint
     * @param ex
     */
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
