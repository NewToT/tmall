package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.ReviewMapper;
import com.wanczy.tmall.mapper.UserMapper;
import com.wanczy.tmall.pojo.Review;
import com.wanczy.tmall.pojo.User;
import com.wanczy.tmall.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	UserMapper userMapper;
	
	
	public void add(Review review) {
	this.reviewMapper.add(review);
		
	}

	
	public void delete(int id) {
		this.reviewMapper.delete(id);
		
	}

	
	public void update(Review review) {
		// TODO Auto-generated method stub
		this.reviewMapper.update(review);
	}

	
	public Review get(int id) {
		// TODO Auto-generated method stub
		return this.reviewMapper.get(id);
	}

	
	public List<Review> list(int pid) {
		// TODO Auto-generated method stub
		setUser(this.reviewMapper.list(pid));
		return this.reviewMapper.list(pid);
		
	}

	
	public int getCount(int pid) {
		
		return this.list(pid).size();
	}
	
	public void setUser(List<Review> list) {
		for (Review review : list) {
			 this.setUser(review);
		}
}
	
	public void setUser(Review review) {
		int uid = review.getUid();
		User user = this.userMapper.get(uid);
		review.setUser(user);
	}	
	}
