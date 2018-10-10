package com.wanczy.tmall.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.service.CategoryService;
import com.wanczy.tmall.service.ProductService;
import com.wanczy.tmall.util.Page;

@Controller
@RequestMapping("")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired	
	CategoryService categoryService;
	
	@RequestMapping("admin_product_add")
	public String add(Map<String,Object> map,Product p) {
		p.setCreateDate(new Date());
		this.productService.add(p);
		
		return "redirect:admin_product_list?cid="+p.getCid();
	}
	
	@RequestMapping("admin_product_delete")
	public String delete(int id) {
		Product p = this.productService.get(id);
		this.productService.delete(id);
		return "redirect:admin_product_list?cid="+p.getCid();
	}
	
	@RequestMapping("admin_product_edit")
	public String edit(Map<String,Object> map,int id) {
		Product p = this.productService.get(id);
		Category c = this.categoryService.get(p.getCid());
		p.setCategory(c);
		map.put("p",p);
		return "admin/editProduct";
	}
	
	@RequestMapping("admin_product_update")
	public String update(Product p) {
		this.productService.update(p);
		return "redirect:admin_product_list?cid="+p.getCid();
	}
	
	@RequestMapping("admin_product_list")

	public String list(int cid,Map<String,Object> map ,Page page) {
		Category c = this.categoryService.get(cid);//获取分类 cid,和分页对象page
		
		PageHelper.offsetPage(page.getStart(), page.getCount());//通过PageHelper设置分页参数
		List<Product> ps =this.productService.list(cid);//基于cid，获取当前分类下的产品集合
		
		int total = (int) new PageInfo<Product>(ps).getTotal();//通过PageInfo获取产品总数
		page.setTotal(total);//把总数设置给分页page对象
		page.setParam("&cid"+c.getId());//拼接字符串"&cid="+c.getId()，设置给page对象的Param值。 因为产品分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid
		
		map.put("ps", ps);
		map.put("c", c);//面包屑导航中会使用到这个id
		map.put("page", page);
		return "admin/listProduct";
	}
	
}
