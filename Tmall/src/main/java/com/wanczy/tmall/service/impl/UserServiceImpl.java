package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.UserMapper;
import com.wanczy.tmall.pojo.User;
import com.wanczy.tmall.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	
	public void add(User u) {
		this.userMapper.add(u);

	}

	
	public void update(User u) {
		// TODO Auto-generated method stub
		this.userMapper.update(u);
	}



	
	public User get(int id) {
		// TODO Auto-generated method stub
		return this.userMapper.get(id);
	}

	
	public List<User> list() {
		// TODO Auto-generated method stub
		return this.userMapper.list();
	}
	
	
	//验证登录的方法
	public User check(String name,String password) {
		User result = this.userMapper.check(name, password);
		
		return result;
	}

	public boolean isExist(String name) {
		Boolean result = this.userMapper.isExist(name);
		
		return result;
	}
}
