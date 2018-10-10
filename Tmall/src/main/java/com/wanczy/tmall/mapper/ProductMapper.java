package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.Product;

public interface ProductMapper {
public void add(Product p);
	
	public void delete(int id);
	
	public void update(Product p);
	
	public Product get(int id);
	
	public List<Product> list(int cid);
	
	public List<Product> search(String keyword);
}
