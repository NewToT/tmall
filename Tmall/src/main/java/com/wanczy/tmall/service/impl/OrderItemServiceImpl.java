package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.OrderItemMapper;

import com.wanczy.tmall.pojo.Order;
import com.wanczy.tmall.pojo.OrderItem;
import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.service.OrderItemService;
import com.wanczy.tmall.service.ProductService;

 
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
 
     
    public void add(OrderItem c) {
        orderItemMapper.add(c);
    }
 
     
    public void delete(int id) {
        orderItemMapper.delete(id);
    }
 
     
    public void update(OrderItem c) {
        orderItemMapper.update(c);
    }
 
     
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.get(id);
        setProduct(result);
        return result;
    }
 
    public List<OrderItem> list(int oid){
        List<OrderItem> ls = this.orderItemMapper.list(oid);
        return ls;
 
    }
 
     
    public void fill(List<Order> os) {
        for (Order o : os) {
            fill(o);
        }
    }
// 为订单对象填充订单项
    public void fill(Order o) {
        
        List<OrderItem> ois =orderItemMapper.list(o.getId());
        setProduct(ois);
 
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
 
    }
 
    public void setProduct(List<OrderItem> ois){
        for (OrderItem oi: ois) {
            setProduct(oi);
        }
    }
 
    private void setProduct(OrderItem oi) {
        Product p = productService.get(oi.getPid());
        oi.setProduct(p);
    }
 
    public int getSaleCount(int pid) {
    	List<OrderItem> ois =this.orderItemMapper.listByProduct(pid);
    	int result = 0;
    	for (OrderItem orderItem : ois) {
			result+= orderItem.getNumber();
		}
    	return result;
    }

//根据用户查询没有生成订单的订单项集合，即还没有提交结算的订单项,并将产品设置到订单项中去
	@Override
	public List<OrderItem> listByUser(int uid) {
			List<OrderItem> result = this.orderItemMapper.listByUser(uid);
			this.setProduct(result);
		return result;
	}
 
}
