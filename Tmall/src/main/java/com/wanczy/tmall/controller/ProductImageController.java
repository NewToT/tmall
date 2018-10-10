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

import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.pojo.ProductImage;
import com.wanczy.tmall.service.ProductImageService;
import com.wanczy.tmall.service.ProductService;
import com.wanczy.tmall.util.ImageUtil;
import com.wanczy.tmall.util.UploadedImageFile;

@Controller
@RequestMapping("")
public class ProductImageController {
	@Autowired
	ProductImageService productImageService;
	@Autowired
	ProductService productService;
	
/*
 * pi对象接收图片的type和pid（所属商品）
 * 向数据库添加    add()
 * session取得要存放图片的路径，每张图片又有不同的大小（正常  中等  小图）
 * 所以定义了single_smll和single_middle，同理就有三个不同的路径来保存该图片
 * 
 * 通过uploadedImageFile来保存文件
 * change2jpg转换图片格式
 * resizeImage改变图片大小
 * 
*/
	@RequestMapping("admin_productImage_add")
	public String add(ProductImage pi,HttpSession session ,UploadedImageFile uploadedImageFile) {
		this.productImageService.add(pi);
		
		String fileName = pi.getId()+".jpg";
		String imageFolder;
		String imageFolder_small = null;
		String imageFolder_middle = null;
		if(ProductImageService.type_single.equals(pi.getType())) {
			imageFolder = session.getServletContext().getRealPath("img/productSingle");
			imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
			imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
		}else {
			imageFolder = session.getServletContext().getRealPath("img/productDetail");
		}
		
		File file = new File(imageFolder, fileName);
		
		file.getParentFile().mkdirs();//mkdirs方法用于生成一层一层的文件夹，getParentFile获取父目录
		
		try {
			uploadedImageFile.getImageFile().transferTo(file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jpg", file);
			
			if(ProductImageService.type_single.equals(pi.getType())) {
				File f_small = new File(imageFolder_small,fileName);
				File f_middle = new File(imageFolder_middle,fileName);
				
				ImageUtil.resizeImage(file, 56, 56, f_small);
				ImageUtil.resizeImage(file, 217, 217, f_middle);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:admin_productImage_list?pid="+pi.getPid();
	}
	/*
获取产品id
 根据id获取ProductImage 对象pi
借助productImageService，删除数据：
 如果是单个图片，那么删除3张图片。。正常，中等，小号图片
如果是详情图片，那么删除一张图片
客户端跳转到admin_productImage_list地址
	*/
	@RequestMapping("admin_productImage_delete")
	public String delete(int id,HttpSession session) {
		
		ProductImage pi = this.productImageService.get(id);
		
		String fileName = pi.getId()+".jpg";
		String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
 
        if(ProductImageService.type_single.equals(pi.getType())){
            imageFolder= session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small= session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle= session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder,fileName);
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle,fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
 
        }
        else{
            imageFolder= session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();
        }
 
        productImageService.delete(id);
 
        return "redirect:admin_productImage_list?pid="+pi.getPid();
	}
	/*
获取参数pid
 根据pid获取Product对象
根据pid对象获取单个图片的集合pisSingle 
 根据pid对象获取详情图片的集合pisDetail
 把product 对象，pisSingle ，pisDetail放在model上
服务端跳转到admin/listProductImage.jsp页面
 在listProductImage.jsp，使用c:forEach 遍历pisSingle 
在listProductImage.jsp，使用c:forEach 遍历pisDetail
	*/
	 @RequestMapping("admin_productImage_list")
	    public String list(int pid,Map<String,Object> map) {
	        Product p =this.productService.get(pid);
	        List<ProductImage> pisSingle = this.productImageService.list(pid, ProductImageService.type_single);
	        List<ProductImage> pisDetail = this.productImageService.list(pid, ProductImageService.type_detail);
	 
	        map.put("p", p);
	        map.put("pisSingle", pisSingle);
	        map.put("pisDetail", pisDetail);
	 
	        return "admin/listProductImage";
	    }
	}
	

