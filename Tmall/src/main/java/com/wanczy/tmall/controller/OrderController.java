package com.wanczy.tmall.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanczy.tmall.pojo.Order;
import com.wanczy.tmall.service.OrderItemService;
import com.wanczy.tmall.service.OrderService;
import com.wanczy.tmall.util.Page;

@Controller
@RequestMapping("")
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;
	

	@RequestMapping("admin_order_list")
	public String list(Map<String,Object> map,Page page) {
		PageHelper.offsetPage(page.getStart(), page.getCount());// 获取分页对象
		List<Order> os =this.orderService.list();//查询订单集合
		
		int total = (int) new PageInfo<Order>(os).getTotal();
		page.setTotal(total);// 获取订单总数并设置在分页对象上
		
		this.orderItemService.fill(os);//借助orderItemService.fill()方法为这些订单填充上orderItems信息
		
		map.put("page", page);
		map.put("os", os);
		/*在listOrder.jsp借助c:forEach把订单集合遍历出来
		遍历订单的时候，再把当前订单的orderItem订单项集合遍历出来*/
		return "admin/listOrder";
	}
	
	@RequestMapping("admin_order_delivery")
	public String delivery(Order o) {
		o = this.orderService.get(o.getId());
		o.setDeliveryDate(new Date());
		o.setStatus("waitConfirm");
		this.orderService.update(o);
		return "redirect:admin_order_list";
	}
	
}
