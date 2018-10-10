package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.Order;
import com.wanczy.tmall.pojo.OrderItem;

public interface OrderService {
		String waitPay ="waitPay";
		String waitDelivery = "waitDelivery";
		String waitConfirm = "waitConfirm";
		String waitReview = "waitReview";
		String finish = "finish";
		String delete = "delete";
		
		public void add(Order o);
		
		public void delete(int id);
		
		public void update(Order o);
		
		public Order get(int id);
		
		public List<Order> list();
		
		public float add(Order c,List<OrderItem> ois);
//		我的订单页，根据uid查看我提交的订单
		List<Order> list(int uid, String excludedStatus);
}
