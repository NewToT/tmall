<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
 <head>
    <link href="css/simpleSearch.css" rel="stylesheet">
 </head>
 <body>
<div >
    <a href="forehome">
        <img id="simpleLogo" class="simpleLogo" src="img/logo.png" style="position:absolute;left:380px;">   
    </a>
     
    <form action="foresearch" method="post" >
    <div class="simpleSearchDiv pull-right">
        <input type="text" placeholder="请输入商品    品牌" name="keyword" value="${param.keyword}">
        <button class="searchButton" type="submit" >搜有间</button>
    </div>
    </form>
    <div style="clear:both"></div>
</div>
</body>