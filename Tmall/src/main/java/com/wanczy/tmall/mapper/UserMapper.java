package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.User;

public interface UserMapper {
	
	public void add(User u);
	
	public void update(User u);
	
	
	
	public User get(int id);
	
	public List<User> list();	
	
	public User check(String name,String password) ;
	
	public boolean isExist(String name); 
}
