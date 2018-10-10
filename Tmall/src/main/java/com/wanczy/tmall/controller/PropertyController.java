package com.wanczy.tmall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.pojo.Property;
import com.wanczy.tmall.service.CategoryService;
import com.wanczy.tmall.service.PropertyService;import com.wanczy.tmall.util.Page;

@Controller
@RequestMapping("")
public class PropertyController {
	@Autowired
	PropertyService propertyService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("admin_property_add")
	public String add(Map<String,Object> map,Property p) {
		this.propertyService.add(p);
		return "redirect:admin_property_list?cid="+p.getCid();
	}
	
	@RequestMapping("admin_property_list")
	public String list(int cid,Map<String,Object>map,Page page) {
		Category c = this.categoryService.get(cid);
		
		PageHelper.offsetPage(page.getStart(),page.getCount());
		List<Property> ps = this.propertyService.list(cid);
		
		int total = (int)new PageInfo<Property>(ps).getTotal();
		page.setTotal(total);
		page.setParam("&cid="+c.getId());
		
		map.put("ps", ps);
		map.put("c", c);
		map.put("page", page);
		
		return "admin/listProperty";
	}
	@RequestMapping("admin_property_delete")
	public String delete(int id) {
		Property p = this.propertyService.get(id);
		this.propertyService.delete(id);
		return "redirect:admin_property_list?cid="+p.getCid();
	}
	
	@RequestMapping("admin_property_edit")
	public String edit(Map<String,Object> map,int id) {
		Property p = this.propertyService.get(id);//根据id获取Property对象
		
		Category c =this.categoryService.get(p.getCid());
		
		p.setCategory(c);//设置在Property对象的category属性
		map.put("p", p);//放入request中使页面能读到，页面中要显示属性名
		return "admin/editProperty";
	}
	
	@RequestMapping("admin_property_update")
	public String update(Property p) {
		this.propertyService.update(p);
		return "redirect:admin_property_list?cid="+p.getCid();
	}
	
	
	
}
