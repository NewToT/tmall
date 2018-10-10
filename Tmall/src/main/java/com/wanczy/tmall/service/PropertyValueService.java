package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.pojo.PropertyValue;

public interface PropertyValueService {
   public void init(Product p);//初始化值
   //属性值的管理，应该没有增加，只有修改，通过初始化方法直接进行自动的新增，以便于修改
   
   public void update(PropertyValue pv);
   
   public PropertyValue get(int ptid , int pid);
   
   public List<PropertyValue> list(int pid);
}
