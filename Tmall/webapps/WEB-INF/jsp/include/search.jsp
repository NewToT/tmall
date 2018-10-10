<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/iconfont.css"/>
		<link rel="stylesheet" href="css/search.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div id="header-search">
        <div class="search-content">
            <img src="img/logo.png" alt="天猫logo" width="300px" />
            <form action="foresearch" method="post" >
            <div class="search">
                <input name="keyword" value="${param.keyword}"class="search-text" type="text" placeholder="&nbsp;&nbsp;搜索  商品/品牌"/><input class="search-btn" type="submit" value="搜索"/>
                <ul class="search-hot">
                    <li><a href="">针织衫</a></li>
                    <li class="hot-line">|</li>
                    <li class="hot-red"><a href="">连衣裙</a></li>
                    <li class="hot-line">|</li>
                    <li><a href="">四件套</a></li>
                    <li class="hot-line">|</li>
                    <li class="hot-red"><a href="">摄像头</a></li>
                    <li class="hot-line">|</li>
                    <li><a href="">客厅灯</a></li>
                    <li class="hot-line">|</li>
                    <li class="hot-red"><a href="">口红</a></li>
                    <li class="hot-line">|</li>
                    <li><a href="">手机</a></li>
                    <li class="hot-line">|</li>
                    <li><a href="">运动鞋</a></li>
                    <li class="hot-line">|</li>
                    <li class="hot-red"><a href="">牛奶</a></li>
                </ul>
            </div>
            </form>
        </div>
    </div>
</body>
</html>