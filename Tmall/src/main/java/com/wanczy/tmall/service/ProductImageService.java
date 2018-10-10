package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.ProductImage;

public interface ProductImageService {
	String type_single = "type_single";
	String type_detail = "type_detail";
	
		public void add(ProductImage pi);
		
		public void delete(int id);
		
		public void update(ProductImage pi);
		
		public ProductImage get(int id);
		
		public List<ProductImage> list(int pid,String type);//显示不同类别的图片
		
}
