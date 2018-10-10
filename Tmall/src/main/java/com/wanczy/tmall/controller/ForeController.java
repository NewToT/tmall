package com.wanczy.tmall.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.wanczy.tmall.comparator.ProductAllComparator;
import com.wanczy.tmall.comparator.ProductDateComparator;
import com.wanczy.tmall.comparator.ProductPriceComparator;
import com.wanczy.tmall.comparator.ProductReviewComparator;
import com.wanczy.tmall.comparator.ProductSaleCountComparator;
import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.pojo.Order;
import com.wanczy.tmall.pojo.OrderItem;
import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.pojo.ProductImage;
import com.wanczy.tmall.pojo.PropertyValue;
import com.wanczy.tmall.pojo.Review;
import com.wanczy.tmall.pojo.User;
import com.wanczy.tmall.service.CategoryService;
import com.wanczy.tmall.service.OrderItemService;
import com.wanczy.tmall.service.OrderService;
import com.wanczy.tmall.service.ProductImageService;
import com.wanczy.tmall.service.ProductService;
import com.wanczy.tmall.service.PropertyValueService;
import com.wanczy.tmall.service.ReviewService;
import com.wanczy.tmall.service.UserService;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;
 
    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> cs= categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
         model.addAttribute("cs", cs);
        return "fore/home";
    }
    @RequestMapping("foreregister")
    public String regist(Map<String,Object> map,User user) {
    	String name = user.getName();
    	name = HtmlUtils.htmlEscape(name);// 通过HtmlUtils.htmlEscape(name);把账号里的特殊符号进行转义
    	/*为什么要用 HtmlUtils.htmlEscape？ 因为在恶意注册的时候，会使用诸如 <script>alert('papapa')</script> 这样的名称，
    	会导致网页打开就弹出一个对话框。 那么在转义之后，就没有这个问题了。*/
    	user.setName(name);
    	boolean exist = this.userService.isExist(name);
    	
    	if(exist) {
    		String m = "用户名已经被使用，不能使用";
    		map.put("msg", m);
    		map.put("user", user);
    		return "fore/regist";
    	}
    	this.userService.add(user);
    	return "redirect:registerSuccessPage";
    }
    
    @RequestMapping("forelogin")
    public String login(Map<String,Object> map,@RequestParam("name") String name,@RequestParam("password") String password,HttpSession session) {
    	name = HtmlUtils.htmlEscape(name);
    	User user = this.userService.check(name, password);
    	if(null ==user) {
    		map.put("msg", "用户名或密码错误");
    		return "fore/login";
    	}
    	session.setAttribute("user", user);
    	session.setMaxInactiveInterval(30*60);
    	return "redirect:forehome";
    }
    
    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user");
    	return "redirect:forehome";
    }
    
    @RequestMapping("foreproduct")
    public String product(Map<String,Object> map,Integer pid) {
    	Product p = this.productService.get(pid);
    	
    	List<ProductImage> productSingleImages = this.productImageService.list(pid, ProductImageService.type_single);
    	List<ProductImage> productDetailImages = this.productImageService.list(pid, ProductImageService.type_detail);
       p.setProductSingleImages(productSingleImages);
       p.setProductDetailImages(productDetailImages);
       
       List<PropertyValue> pvs =this.propertyValueService.list(pid);
       List<Review> reviews =this.reviewService.list(pid);
       this.productService.setSaleAndReviewNumber(p);
       
       map.put("pvs", pvs);
       map.put("reviews", reviews);
       map.put("p", p);
     	return "fore/product";
    }
//点击立即购买按钮后验证是否已经登录
    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
    	User user = (User) session.getAttribute("user");
    	if(null != user) {
    		return "success";
    	}
    	return "fail";
    }
    /*
     * 用于模态登录时的验证
    */
    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name,@RequestParam("password") String password, HttpSession session) {
       name = HtmlUtils.htmlEscape(name);
       User user = this.userService.check(name, password);
       
       if(null == user) {
   		return "fail";
   	}
       session.setAttribute("user", user);
   		return "success";
    }
    /*
     * 用于模态登录时的验证
    */
    
    @RequestMapping("forecategory")
    public String category(int cid,String sort,Map<String,Object> map) {
    	Category c =this.categoryService.get(cid);
    	this.productService.fill(c);
    	this.productService.setSaleAndReviewNumber(c.getProducts());
    	
    	if(null != sort) {
    		switch(sort) {
    		case "review":
    			Collections.sort(c.getProducts(), new ProductReviewComparator());
    			break;
    		case "date":
    			Collections.sort(c.getProducts(), new ProductDateComparator());
    			break;
    		case "saleCount":
    			Collections.sort(c.getProducts(), new ProductSaleCountComparator());
    			break;
    		case "price":
    			Collections.sort(c.getProducts(), new ProductPriceComparator());
    			break;
    		case "all":
    			Collections.sort(c.getProducts(), new ProductAllComparator());
    			break;
    		}
    	}
    	map.put("c", c);
    	return "fore/category";
    }
    
//    搜索
    @RequestMapping("foresearch")
    public String  search(Map<String,Object> map,String keyword) {
    	List<Product> ps = this.productService.search(keyword);
    	this.productService.setSaleAndReviewNumber(ps);
    	map.put("ps",ps);
    	return "fore/searchResult";
    }
    
//    购买
//    从页面获取：用户、商品、购买数量
    @RequestMapping("forebuyone")
    public String buy(int pid,int num,HttpSession session) {
    Product p = this.productService.get(pid);
    int oiid = 0;
    
    User user = (User) session.getAttribute("user");
    boolean found = false;
    
    List<OrderItem> ois  = this.orderItemService.listByUser(user.getId());
   		for (OrderItem orderItem : ois) {
//    	如果获取的商品在订单项中，即该用户的购物车中已经存在了，就新增数量，并重新获取订单项id
		if(orderItem.getProduct().getId().intValue() == p.getId().intValue()) {
			orderItem.setNumber(orderItem.getNumber()+num);
// 	提交修改
			this.orderItemService.update(orderItem);
			found = true;
			oiid = orderItem.getId();
			break;
		}
	}
//   		如果在购物车中不存在
   		if(!found) {
//   			新建订单项
   			OrderItem oi = new OrderItem();
   			oi.setPid(pid);
   			oi.setUid(user.getId());
   			oi.setNumber(num);
//       提交新增
   			this.orderItemService.add(oi);
//   			获取到现在的订单项id，为下一步去结算页面提供
   			oiid = oi.getId();
   		}
    	return "redirect:forebuy?oiid="+oiid;
    	}
 
    @RequestMapping("forebuy")
    public String buy( Model model,String[] oiid,HttpSession session){
        List<OrderItem> ois = new ArrayList<>();
        float total = 0;
 
        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.get(id);
            total +=oi.getProduct().getPromotePrice()*oi.getNumber();
            ois.add(oi);
        }
 
        session.setAttribute("ois", ois);
        model.addAttribute("total", total);
        return "fore/buy";
    }
/*点击立即购买后分为两步，
 * 第一步通过forebuyone去查询订单项（购物车中是否有，没有则添加，有则改数量），
 * 第二步订单项（购物车）处理完毕后再跳转至forebuy法处理结算业务
 * forebuy方法获取到要处理的订单项的多个id后（因为结算时可能会有多个订单项，立即购买是一个，但是购物车中是可以选择多个订单想进行处理的）
 * 通过遍历oiid来获取该（可能为多个）订单项中多个商品的数量和价格，最后计算出总价格，将订单项集合放入session中
 * */
    
//    加入购物车的方法，类似于立即购买，但是不用获取oid和跳转至任何页面
    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid, int num, Model model,HttpSession session) {
        Product p = productService.get(pid);
        User user =(User)  session.getAttribute("user");
        boolean found = false;
 
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId().intValue()==p.getId().intValue()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found = true;
                break;
            }
        }
 
        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
        }
        return "success";
    }
    
    @RequestMapping("forecar")
    public String car(Map<String,Object> map,HttpSession session) {
    	User user = (User) session.getAttribute("user");
    	List<OrderItem> ois = this.orderItemService.listByUser(user.getId());
    	map.put("ois", ois);
    	
    	return "fore/car"; 
    }
//    改变购物车数量的方法
    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem( Model model,HttpSession session, int pid, int number) {
        User user =(User)  session.getAttribute("user");
        if(null==user)
            return "fail";
 
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId().intValue()==pid){
                oi.setNumber(number);
                orderItemService.update(oi);
                break;
            }
 
        }
        return "success";
    }
// 删除购物车中该商品的方法
    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem( Model model,HttpSession session,int oiid){
        User user =(User)  session.getAttribute("user");
        if(null==user)
        { return "fail";}
        orderItemService.delete(oiid);
        return "success";
    }
    
    @RequestMapping("forecreateOrder")
    public String createOrder(Model model, Order order,HttpSession session) {
    	User user = (User) session.getAttribute("user");
    	
    	String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
    	order.setOrderCode(orderCode);
    	order.setCreateDate(new Date());
    	order.setUid(user.getId());
    	order.setStatus(OrderService.waitPay);
    	@SuppressWarnings("unchecked")
		List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
    	
    	float total = orderService.add(order, ois);
    	return "redirect:forealipay?oid="+order.getId()+"&total="+total;
    }
    
    @RequestMapping("forepayed")
    public String payed(int oid, float total, Model model) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "fore/payed";
    }
    
    @RequestMapping("forebought")
    public String bought(Model model,HttpSession session) {
    	User user =(User)  session.getAttribute("user");
        List<Order> os= orderService.list(user.getId(),OrderService.delete);
 
        orderItemService.fill(os);
 
        model.addAttribute("os", os);
    	return "fore/bought";
    }
    
    
    @RequestMapping("foreconfirmPay")
    public String confirmPay( Model model,int oid) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        model.addAttribute("o", o);
        return "fore/confirmPay";
//    	确认收货后获取订单相关信息，为订单对象填充订单项
    }
    
    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed( Model model,int oid) {
        Order o = this.orderService.get(oid);
        o.setStatus(OrderService.waitReview);
        o.setConfirmDate(new Date());
        orderService.update(o);
        return "fore/orderConfirmed";
    }
    
    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder( Model model,int oid){
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }
    
    @RequestMapping("forereview")
    public String review( Model model,int oid) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        Product p = o.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("p", p);
        model.addAttribute("o", o);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }
    
    @RequestMapping("foredoreview")
    public String doreview( Model model,HttpSession session,@RequestParam("oid") int oid,@RequestParam("pid") int pid,String content) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);
     
        Product p = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);
     
        User user =(User)  session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        
        review.setPid(p.getId());
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        this.reviewService.add(review);
        
        return "redirect:forereview?oid="+oid+"&showonly=true";
    }
    
}
    



