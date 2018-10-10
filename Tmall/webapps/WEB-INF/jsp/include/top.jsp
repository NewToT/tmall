<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="css/iconfont.css"/>
		<link rel="stylesheet" href="css/top.css" />
</head>
<body>
	<div id="top">
			<div class="top-nav">
				<div class="top-left">
					<span>喵，欢迎来到一间商店</span>
					<c:if test="${!empty user}">
					<a href = "#nowhere">${user.name}</a>
					<a href ="forelogout">退出</a>
					</c:if>
					<c:if test="${empty user}">
					<a class="login" href="loginPage">请登录</a>
					<a class="register" href="registPage">免费注册</a>
					</c:if>
				</div>
				<div class="none"></div>
				<div class="top-right">
					<!--我的淘宝-->
					<div class="mine">
						<a class="nav-mine" href="">我的有间</a>
						<div class="mine-content">
							<ul>
								<li><a href="forebought">我的订单</a></li>
							</ul>
						</div>
					</div>
				
				
				<!--购物车-->
					<div class="goCar">
						<i class="icon iconfont icon-gouwuche"></i><a href="forecar">购物车<strong>${cartTotalItemNumber}</strong>件</a>
					</div>
				
				<!--收藏夹-->
					<div class="save">
						<a class="nav-save" href="">收藏夹</a>
						<div class="save-content">
							<ul>
								<li><a href="#">收藏的宝贝</a></li>
								<li><a href="#">收藏的店铺</a></li>
							</ul>
						</div>
					</div>
					
					<div class="top-line">
						<span>|</span>
					</div>
					
					
					<!--手机版-->
					<div class="phone">
						<div class="nav-phone">
							<i class="icon iconfont icon-phone"></i><a href="#">手机版</a>
						</div>
						<img class="phone-content" src="img/two-code.png" alt="二维码" />
					</div>
					
					<a  class="taobao"href="#">有间网</a>
					
					<!--商家支持-->
					<div class="sale">
						<a class="nav-sale" href="">商家支持</a>
						<div class="sale-content">
							<dl>
								<dt>商家:</dt>
								<dd><a href="">商家中心</a></dd>
								<dd><a href="">商家入驻</a></dd>
								<dd><a href="">商家品控</a></dd>
								<dd><a href="">商家工具</a></dd>
								<dd><a href="">天猫智库</a></dd>
								<dd><a href="">天猫规则</a></dd>
								<dd><a href="">运营服务</a></dd>
								<dd><a href="">喵言喵语</a></dd>
								<dt>帮助:</dt>
								<dd><a href="">帮助中心</a></dd>
								<dd><a href="">问商友</a></dd>
							</dl>
						</div>
					</div>
					
					<!--网站导航-->
					<div class="web">
						<div class="nav-web">
							<i class="icon iconfont icon-daohang"></i><a  href="">网站导航</a>
						</div>	
						<div class="web-content">
							<div class="web-left">
								<dl>
									<dt class="web-title-hot">热点推荐 Hot</dt>
									<dd><a href="">天猫超市</a></dd>
									<dd><a href="">喵鲜生</a></dd>
									<dd><a href="" class="diy-new">科技新品</a></dd>
									<dd><a href="" class="diy-hot">女装新品</a></dd>
									<dd><a href="">酷玩街</a></dd>
									<dd><a href="" class="diy-hot">内衣新品</a></dd>
									<dd><a href="">试美妆</a></dd>
									<dd><a href="">运动新品</a></dd>
									<dd><a href="" class="diy-hot">时尚先生</a></dd>
									<dd><a href="">精明妈咪</a></dd>
									<dd><a href="">吃乐会</a></dd>
									<dd><a href="" class="diy-new">企业采购</a></dd>
									<dd><a href="">会员积分</a></dd>
									<dd><a href="">天猫国际</a></dd>
									<dd><a href="">品质频道</a></dd>
								</dl>
							</div>
							<div class="web-center">
								<dl>
									<dt class="web-title-mak">行业市场 Market</dt>
									<dd><a href="">美妆</a></dd>
									<dd><a href="">电器</a></dd>
									<dd><a href="" class="diy-hot">女装</a></dd>
									<dd><a href="" class="diy-hot">男装</a></dd>
									<dd><a href="">女鞋</a></dd>
									<dd><a href="">男鞋</a></dd>
									<dd><a href="" class="diy-hot">内衣</a></dd>
									<dd><a href="">箱包</a></dd>
									<dd><a href="">运动</a></dd>
									<dd><a href="">母婴</a></dd>
									<dd><a href="">家装</a></dd>
									<dd><a href="">医药</a></dd>
									<dd><a href="">食品</a></dd>
									<dd><a href="" class="diy-new">配饰</a></dd>
									<dd><a href="">汽车</a></dd>
								</dl>
							</div>
							<div class="web-right">
								<dl>
									<dt class="web-title-ser">服务指南 Help</dt>
									<dd><a href="">帮助中心</a></dd>
									<dd><a href="">品质保障</a></dd>
									<dd><a href="">特色服务</a></dd>
									<dd><a href="">七天退换</a></dd>
								</dl>
							</div>
						</div>
					</div>
				</div>		
		</div>
	</div>
</body>
</html>