package com.sikiedu.text;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sikiedu.bean.UserService;

public class text {
	public static void main(String[] args) {
		//ʹ��xmlʵ�ֵ�aop
		ApplicationContext application = new ClassPathXmlApplicationContext("/applicationContext.xml");
	
		UserService user = (UserService)application.getBean("userService");
		
		user.search();
	}
}
