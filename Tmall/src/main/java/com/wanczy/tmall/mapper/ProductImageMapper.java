package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.ProductImage;

public interface ProductImageMapper {
	
		public void add(ProductImage pi);
			
		public void delete(int id);
		
		public void update(ProductImage pi);
		
		public ProductImage get(int id);
		
		public List<ProductImage> list(int pid,String type);//显示不同类别的图片
		
}
