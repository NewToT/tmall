package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.Review;

public interface ReviewMapper {
	void add(Review review);
	
	void delete(int id);
	
	void update(Review review);
	
	Review get(int id);
	
	List<Review> list(int pid);
}
