package com.wanczy.tmall.mapper;

import java.util.List;


import com.wanczy.tmall.pojo.OrderItem;

public interface OrderItemMapper {
	 public void add(OrderItem o);
	    
	    public void update(OrderItem o);
	    
	    public void delete(int id);
	    
	    public List<OrderItem> list(int oid);
	    
	    public OrderItem get(int id);
//	    根据产品查看有多少订单项
	    public List<OrderItem> listByProduct(int pid);
//	    根据用户查看该用户的订单项
	    public List<OrderItem> listByUser(int uid);
}
