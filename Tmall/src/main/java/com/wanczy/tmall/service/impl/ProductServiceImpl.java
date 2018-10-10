package com.wanczy.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.CategoryMapper;
import com.wanczy.tmall.mapper.ProductMapper;
import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.pojo.ProductImage;
import com.wanczy.tmall.service.OrderItemService;
import com.wanczy.tmall.service.ProductImageService;
import com.wanczy.tmall.service.ProductService;
import com.wanczy.tmall.service.ReviewService;
@Service
public class ProductServiceImpl implements ProductService {
//在get和list方法中要把取出来的product对象设置上对应的Category,要写一个setCategory方法来设置
	@Autowired
	ProductMapper productMapper;
	@Autowired
	CategoryMapper categoryMapper;
	 @Autowired
	    ProductImageService productImageService;
	 @Autowired
	    OrderItemService orderItemService;
	    @Autowired
	    ReviewService reviewService;
	
	public void add(Product p) {
		this.productMapper.add(p);
		
	}

	public void delete(int id) {
	this.productMapper.delete(id);
		
	}

	public void update(Product p) {
	this.productMapper.update(p);
		
	}

	public Product get(int id) {
		Product p = this.productMapper.get(id);
		setFirstProductImage(p);
		 setCategory(p);
		 
		return p;
	}

	public List<Product> list(int cid) {
		List<Product> ps = this.productMapper.list(cid);
		setCategory(ps);
		setFirstProductImage(ps);
		return ps;
	}
	
	
	//这一部分用于给get和list方法取得的product设置上Category，因为会去使用到在显示产品的页面显示产品的类别
	public void setCategory(List<Product> ps) {
		for (Product product : ps) {
			setCategory(product);
		}
	}
	public void setCategory(Product p) {
		int cid = p.getCid();
		Category c =this.categoryMapper.get(cid);
		p.setCategory(c);
	}

    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }
 
    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }

	public void fill(Category c) {
		List<Product> ps = this.list(c.getId());
		c.setProducts(ps);
	}

	public void fill(List<Category> cs) {
	for (Category c : cs) {
       this.fill(c);
	}
		
	}

	public void fillByRow(List<Category> cs) {
		 int productNumberEachRow = 8;
	        for (Category c : cs) {
	            List<Product> products =  c.getProducts();
	            List<List<Product>> productsByRow =  new ArrayList<List<Product>>();
	            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
	                int size = i+productNumberEachRow;
	                size= size>products.size()?products.size():size;
	                List<Product> productsOfEachRow =products.subList(i, size);
	                productsByRow.add(productsOfEachRow);
	            }
	            c.setProductsByRow(productsByRow);
	        }
	}
	
	
	public void setSaleAndReviewNumber(Product p) {
		int saleCount = this.orderItemService.getSaleCount(p.getId());
		p.setSaleCount(saleCount);
		
		int reviewCount = this.reviewService.getCount(p.getId());
		p.setReviewCount(reviewCount);
	}
	
	public void setSaleAndReviewNumber(List<Product> ps) {
		for (Product product : ps) {
			this.setSaleAndReviewNumber(product);
		}
	}

	public List<Product> search(String keyword) {
		List<Product> result = this.productMapper.search(keyword);
		setCategory(result);
		setFirstProductImage(result);
		return result;
	}
	
}
