package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.Order;
import com.wanczy.tmall.pojo.OrderItem;

public interface OrderItemService {
	
    public void add(OrderItem o);
    
    public void update(OrderItem o);
    
    public void delete(int id);
    
    public OrderItem get(int id);
    
    public List<OrderItem> list(int oid);
    
    public void fill(Order o);//订单管理界面，先遍历多个订单，再遍历订单下的多个订单项
    
    public void fill(List<Order> os);//遍历每个订单，然后挨个调用fill(Order o)
    
    public int getSaleCount(int pid);//根据产品获取销量
    
    public List<OrderItem> listByUser(int uid);
    
}
