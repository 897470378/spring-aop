package com.sikiedu.beanImpl;

import org.springframework.stereotype.Component;

import com.sikiedu.bean.UserService;

@Component
public class UserServiceImpl implements UserService{
	@Override
	public void add() {
		System.out.println("�����û�");
	}
	
	@Override
	public void update(int a) {
		System.out.println("�޸��û�");
	}
	
	@Override
	public void delete() {
		System.out.println("ɾ���û�");
	}
	
	@Override
	public String search() {
		System.out.println("��ѯ�û�");
		return "123";
	}

}
