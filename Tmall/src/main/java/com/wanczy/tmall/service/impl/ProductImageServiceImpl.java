package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.ProductImageMapper;
import com.wanczy.tmall.pojo.ProductImage;
import com.wanczy.tmall.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService{
	@Autowired
	ProductImageMapper productImageMapper;
	
	public void add(ProductImage p) {
		this.productImageMapper.add(p);
		
	}
	public void delete(int id) {
		this.productImageMapper.delete(id);
		
	}
	public void update(ProductImage pi) {
		this.productImageMapper.update(pi);
		
	}
	public ProductImage get(int id) {
		
		return this.productImageMapper.get(id);
	}
	public List<ProductImage> list(int pid, String type) {
		
		return this.productImageMapper.list(pid, type);
	}


}
