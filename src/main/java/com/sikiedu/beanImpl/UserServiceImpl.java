package com.sikiedu.beanImpl;

import org.springframework.stereotype.Component;

import com.sikiedu.bean.UserService;

@Component
public class UserServiceImpl implements UserService{
	@Override
	public void add() {
		System.out.println("增加用户");
	}
	
	@Override
	public void update(int a) {
		System.out.println("修改用户");
	}
	
	@Override
	public void delete() {
		System.out.println("删除用户");
	}
	
	@Override
	public String search() {
		System.out.println("查询用户");
		return "123";
	}

}
