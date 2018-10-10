<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" type="text/css" href="css/category.css">
 <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
        <script>
$(function(){
    $("input.sortBarPrice").keyup(function(){
        var num= $(this).val();
        if(num.length==0){
            $("div.productUnit").show();
            return;
        }
             
        num = parseInt(num);
        if(isNaN(num))
            num= 1;
        if(num<=0)
            num = 1;
        $(this).val(num);      
         
        var begin = $("input.beginPrice").val();
        var end = $("input.endPrice").val();
        if(!isNaN(begin) && !isNaN(end)){
            console.log(begin);
            console.log(end);
            $("div.productUnit").hide();
            $("div.productUnit").each(function(){
                var price = $(this).attr("price");
                price = new Number(price);
                 
                if(price<=end && price>=begin)
                    $(this).show();
            });
        }
         
    });
});
</script>
<title>一间商店   ${c.name }</title>
</head>
<body>
<div class="category">
<img src="img/category/${c.id}.jpg" width="128px">
<div class="categorySortBar">
 
    <table class="categorySortBarTable categorySortTable">
        <tr>
            <td <c:if test="${'all'==param.sort||empty param.sort}">class="grayColumn"</c:if> ><a href="?cid=${c.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'review'==param.sort}">class="grayColumn"</c:if> ><a href="?cid=${c.id}&sort=review">人气<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'date'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'saleCount'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=saleCount">销量<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'price'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=price">价格<span class="glyphicon glyphicon-resize-vertical"></span></a></td>
        </tr>
    </table>
     
    <table class="categorySortBarTable">
        <tr>
            <td><input class="sortBarPrice beginPrice" type="text" placeholder="请输入价格区间"></td>
            <td class="grayColumn priceMiddleColumn">-</td>
            <td><input class="sortBarPrice endPrice" type="text" placeholder="请输入价格区间"></td>
        </tr>
    </table>
</div>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>
 
<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>
     
<div class="categoryProducts">
    <c:forEach items="${c.products}" var="p" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
        <div class="productUnit" price="${p.promotePrice}">
            <div class="productUnitFrame">
                <a href="foreproduct?pid=${p.id}">
                    <img class="productImage" src="img/productSingle_middle/${p.firstProductImage.id}.jpg">
                </a>
                <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
                <a class="productLink" href="foreproduct?pid=${p.id}">
                 ${fn:substring(p.name, 0, 50)}
                </a>
                 
                <a  class="tmallLink" href="foreproduct?pid=${p.id}">有间专卖</a>
     
                <div class="show1 productInfo">
                    <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                    <span class="wangwang">
                    <a class="wangwanglink" href="#nowhere">
                        <img src="img/site/wangwang.png">
                    </a>
                     
                    </span>
                </div>
            </div>
        </div>
        </c:if>
    </c:forEach>
        <div style="clear:both"></div>
</div>

</div>
</body>
</html>