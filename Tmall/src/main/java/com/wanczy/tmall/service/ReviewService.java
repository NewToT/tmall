package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.Review;

public interface ReviewService {
	void add(Review review);
	
	void delete(int id);
	
	void update(Review review);
	
	Review get(int id);
	
	List<Review> list(int pid);
	
	int getCount(int pid);
}
