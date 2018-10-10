package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wanczy.tmall.mapper.OrderMapper;
import com.wanczy.tmall.pojo.Order;
import com.wanczy.tmall.pojo.OrderItem;
import com.wanczy.tmall.pojo.User;
import com.wanczy.tmall.service.OrderItemService;
import com.wanczy.tmall.service.OrderService;
import com.wanczy.tmall.service.UserService;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	UserService userService;
	@Autowired
	OrderItemService orderItemService;
	
	
	
	public void add(Order o) {
		
		this.orderMapper.add(o);
	}

	
	public void delete(int id) {
		
		this.orderMapper.delete(id);
	}

	
	public void update(Order o) {
		
		this.orderMapper.update(o);

	}

	
	public Order get(int id) {
		// TODO Auto-generated method stub
		return this.orderMapper.get(id);
	}

	//和OrderIetm类似
	//获取到所有订单后，遍历所有订单，把用户设置到订单的User属性上
	
	public List<Order> list() {
		
		List<Order> ls = this.orderMapper.list();
		setUser(ls);
		return ls;
	}
    
	public void setUser(Order o) {
		int uid = o.getId();
		User u = this.userService.get(uid);
		o.setUser(u);
	}
	
	public void setUser(List<Order> os) {
		for (Order order : os) {
			this.setUser(order);
		}
	}




	@Override
//	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public float add(Order o, List<OrderItem> ois) {
		float total = 0;
//		增加一笔订单项数据
		add(o);
		
//		if(false)
//				throw new RuntimeException();
		
//		为订单项设置上订单id，表明是已经提交的订单
		for (OrderItem oi : ois) {
			oi.setOid(o.getId());
			this.orderItemService.update(oi);
//			计算总价格
			total += oi.getProduct().getPromotePrice()*oi.getNumber();
		}
		return total;
	}


	@Override
//	根据用户和状态查询订单
	public List<Order> list(int uid, String excludedStatus) {
		
		return this.orderMapper.listByUser(uid,excludedStatus);
	}
	
}
