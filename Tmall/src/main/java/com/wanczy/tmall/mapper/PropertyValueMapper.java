package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.pojo.PropertyValue;

public interface PropertyValueMapper {
	  public void init(Product p);//初始化值
	   
	   public void insert(PropertyValue pv);
	  
	   public void update(PropertyValue pv);
	   
	   public List<PropertyValue> get(int ptid , int pid);
	   
	   public List<PropertyValue> list(int pid);
}
