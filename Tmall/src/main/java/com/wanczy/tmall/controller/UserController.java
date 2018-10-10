package com.wanczy.tmall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanczy.tmall.pojo.User;
import com.wanczy.tmall.service.UserService;
import com.wanczy.tmall.util.Page;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("admin_user_list")
	public String list(Map<String,Object> map,Page page) {
		PageHelper.offsetPage(page.getStart(),page.getCount());
		 List<User> us = this.userService.list();
		 
		 int total =(int) new PageInfo<User>(us).getTotal();
		 page.setTotal(total);
		
		 map.put("us", us);
		 map.put("page", page);
		 
		return "admin/listUser";
	}
	
	
}
