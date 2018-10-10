package com.wanczy.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.service.CategoryService;
import com.wanczy.tmall.util.ImageUtil;
import com.wanczy.tmall.util.Page;
import com.wanczy.tmall.util.UploadedImageFile;

@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
/*	
 *  首先浏览器上访问路径 /admin_category_list
	 tomcat根据web.xml上的配置信息，拦截到了/admin_category_list，并将其交由DispatcherServlet处理。
	DispatcherServlet 根据springMVC的配置，将这次请求交由CategoryController类进行处理，所以需要进行这个类的实例化
	在实例化CategoryController的时候，注入CategoryServiceImpl
	 在实例化CategoryServiceImpl的时候，又注入CategoryMapper
	CategoryController调用list方法
	在list方法中，访问CategoryService,并获取数据，并把数据放在"cs"上，接着服务端跳转到listCategory.jsp去
	最后在listCategory.jsp 中显示数据
	*/
	@RequestMapping("/admin_category_list")
	public String list(Map<String,Object> map,Page page) {
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Category> cs = this.categoryService.list();
	    int  total = (int) new PageInfo<Category>(cs).getTotal();
	    page.setTotal(total);
/*	   1. 通过分页插件指定分页参数
	    PageHelper.offsetPage(page.getStart(),page.getCount());
	    2. 调用list() 获取对应分页的数据
	    categoryService.list();
	    3. 通过PageInfo获取总数
	     int total = (int) new PageInfo<Category>(cs).getTotal();*/
		map.put("cs", cs);
		map.put("page", page);
		return "admin/listCategory";
	}
	
	/*新增方法
 参数 Category c接受页面提交的分类名称
 参数 session 用于在后续获取当前应用的路径
 UploadedImageFile 用于接受上传的图片
 
 
通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。

客户端跳转到admin_category_list
	  */
	@RequestMapping("admin_categroy_add")
	public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) {
		this.categoryService.add(c);//通过categoryService保存c对象
		File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
		/*
		 通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径
		*/
		File file = new File(imageFolder,c.getId() +".jpg");//根据分类id创建文件名
	if(!file.getParentFile().exists()) {
		file.getParentFile().mkdirs();
	}//如果/img/category目录不存在，则创建该目录
	try {
		uploadedImageFile.getImageFile().transferTo(file);// 通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
		BufferedImage img = ImageUtil.change2jpg(file);// 通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
	    ImageIO.write(img, "jpg", file);//ImageIO类中提供了ImageIO.writer方法可以生成指定的格式的图像
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "redirect:/admin_category_list";//新增之后就显示
	}
	
	/*
	 * 删除方法
	 * 每个请求到后台都会抽象为一个HttpRequestServlet的实例，这里面就有Session的信息,
	 * 接着我们可以通过传入一个Session参数来定位图片文件的位置即session.getServletContext().getRealPath()找到图片所在目录的绝对路径
	 
	 
	 */
@RequestMapping("admin_category_delete")
public String delete(int id,HttpSession session) {
	categoryService.delete(id);//从数据库中删除该分类
	
	File imageFolder = new File(session.getServletContext().getRealPath("img/category"));//获取"img/category"目录的绝对路径
	File file = new File(imageFolder,id + ".jpg");
	file.delete();
	return "redirect:/admin_category_list";
}

/*
 * 编辑的方法  edit
 * admin_category_edit
 * 返回的是editCategory.jsp页面
 */
@RequestMapping("admin_category_edit")
public String edit(int id,Map<String,Object> map) {
	Category c = categoryService.get(id);
	map.put("c", c);
	return "admin/editCategory";
}
/*
 * 修改的方法
 * admin_category_update
 * 第一个参数Category接受页面提交的分类名称
 * 第二个参数session用于获取图片的路径，类似于删除
 * 第三个参数UploadedImageFile用于接收要重新上传的图片，但是要先
 判断是否有要修改的图片（是否上传图片），如果上传了，就先通过session获取路径
 然后通过UploadedImageFile把浏览器传递过来的图片保存在指定路径
 */
@RequestMapping("admin_category_update")
public String update(Category c, HttpSession session, UploadedImageFile uploadedImageFile) {
	categoryService.update(c);
	MultipartFile image = uploadedImageFile.getImageFile();
	if(null!=image && !image.isEmpty()) {
		File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,c.getId()+".jpg");//根据分类id创建文件名
		try {
			image.transferTo(file);//是springMVC封装的方法，用于图片上传时，把内存中的图片写入磁盘
											  //https://blog.csdn.net/jaryle/article/details/72822667
			BufferedImage img = ImageUtil.change2jpg(file);//通过ImageUtil确保图像时jpg图像
			ImageIO.write(img, "jpg", file);//调用ImageIO.writer从BufferedImage生成jpg图像
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return "redirect:/admin_category_list";
}
}

