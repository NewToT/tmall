package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.Order;

public interface OrderMapper {
	public void add(Order o);
	
	public void delete(int id);
	
	public void update(Order o);
	
	public Order get(int id);
	
	public List<Order> list();
	
	public List<Order> listByUser(int uid,String excludedStatus);
}
