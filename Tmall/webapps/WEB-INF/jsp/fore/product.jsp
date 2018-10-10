<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap/3.3.6/bootstrap.min.css">
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<title>有间商店  ${p.name}</title>
<script type="text/javascript">
$(function(){
    var stock = ${p.stock};
    $(".Setting").keyup(function(){
        var num= $(".numSetting").val();
        num = parseInt(num);
        if(isNaN(num))
            num= 1;
        if(num<=0)
            num = 1;
        if(num>stock)
            num = stock;
        $(".numSetting").val(num);
    });
     
    $(".increaseNum").click(function(){
        var num= $(".numSetting").val();
        num++;
        if(num>stock)
            num = stock;
        $(".numSetting").val(num);
    });
    $(".decreaseNum").click(function(){
        var num= $(".numSetting").val();
        --num;
        if(num<=0)
            num=1;
        $(".numSetting").val(num);
    });
     
    $(".addCartLink").click(function(){
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success"==result){
                        var pid = ${p.id};
                        var num= $(".numSetting").val();
                        var addCartpage = "foreaddCart";
                        $.get(
                                addCartpage,
                                {"pid":pid,"num":num},
                                function(result){
                                    if("success"==result){
                                        $(".addCarButton").html("已加入购物车");
                                        $(".addCarButton").attr("disabled","disabled");
                                        $(".addCarButton").css("background-color","lightgray")
                                        $(".addCarButton").css("border-color","lightgray")
                                        $(".addCarButton").css("color","black")
                                         
                                    }
                                    else{
                                         
                                    }
                                }
                        );                         
                    }
                    else{
                        $("#loginModal").modal('show');                    
                    }
                }
        );     
        return false;
    });
    $(".buyLink").click(function(){
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success"==result){
                        var num = $(".numSetting").val();
                        location.href= $(".buyLink").attr("href")+"&num="+num;
                    }
                    else{
                        $("#loginModal").modal('show');                    			
                    }
                }
        );     
        return false;
    });
     
    $("button.loginSubmitButton").click(function(){
        var name = $("#name").val();
        var password = $("#password").val();
         
        if(0==name.length||0==password.length){
            $("span.errorMessage").html("请输入账号密码");
            $("div.loginErrorMessageDiv").show();          
            return false;
        }
         
        var page = "foreloginAjax";
        $.get(
                page,
                {"name":name,"password":password},
                function(result){
                    if("success"==result){
                        location.reload();
                    }
                    else{
                        $("span.errorMessage").html("账号密码错误");
                        $("div.loginErrorMessageDiv").show();                      
                    }
                }  
        );         
         
        return true;
    });
     
    $("img.smallImage").mouseenter(function(){
        var bigImageURL = $(this).attr("bigImageURL");
        $("img.bigImg").attr("src",bigImageURL);
    });
     
    $("img.bigImg").load(
        function(){
            $("img.smallImage").each(function(){
                var bigImageURL = $(this).attr("bigImageURL");
                img = new Image();
                img.src = bigImageURL;
                 
                img.onload = function(){
                    console.log(bigImageURL);  
                    $("div.img4load").append($(img));
                };
            });    
        }
    );
});

</script>
</head>
<body>
<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>
<%@include file="../include/simpleSearch.jsp" %>
<div class="categoryPictureInProductPageDiv">
    <img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg" height="128px">
</div>
<div class="main">

    <div class="imgAndInfo">

<div class="sbImg">
          <img src="img/productSingle/${p.firstProductImage.id}.jpg" class="bigImg">
    <div class="smallDiv">
     <c:forEach items="${p.productSingleImages}" var="pi">
                <img src="img/productSingle_small/${pi.id}.jpg" bigImageURL="img/productSingle/${pi.id}.jpg" class="smallImage">
            </c:forEach>
    </div> 
    <div class="img4load hidden"></div>
</div>

<div class="info">
    <div class="title">
        ${p.name}
    </div>

    <div class="subTitle">
          ${p.subTitle}
    </div>

    <div class="juhuasuan">
        <span class="juhuasuanBig">聚划算</span>
    </div>

    <div class="priceDiv">
        
            <div class="originalDiv">
                <span class="originalPriceDesc">价格</span>
                <span class="mark">￥</span>
                <span class="orPrice"><fmt:formatNumber type="number" value="${p.originalPrice}" minFractionDigits="2"/></span>
            </div>


            <div class="promotionDiv">
                <span class="proPriceDesc">促销价</span>
                <span class="pmark">￥</span>
                <span class="prPrice"><fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
            </div>
    </div>

    <div class="saleAndReviewNum">
        <div>销量 <span class="redColor"> ${p.saleCount }</span></div>
        <div>累计评价 <span class="redColor">${p.reviewCount}</span></div>
    </div>


<div class="productNum">
 <span>数量</span>
 <span>
      <span class="numSettingSpan">
        <input type="text" class="numSetting" value="1">
     </span>

     <span class="arrow">
        <a href="#nowhere" class="increaseNum">
            <span class="updown">
                <img src="img/site/increase.png">
            </span>
        </a>
            <span class="updownMiddle">
                <a href="#nowhere" class="decreaseNum">
                    <span class="updown">
                        <img src="img/site/decrease.png">
                    </span>
                </a>
            </span>
            
     </span>
件
 </span>
 <span>库存 ${p.stock} 件</span>
</div>

<div class="serviceCommit">
        <span class="serviceCommitDesc">服务承诺</span>
        <span class="serviceCommitLink">
        <a href="#nowhere">正品保证</a>
        <a href="#nowhere">急速退款</a>
        <a href="#nowhere">七天无理由退换</a>
        </span>
</div>

<div class="buyDiv">
    <a href="forebuyone?pid=${p.id}" class="buyLink"><button class="buyButton">立即购买</button></a>
    <a href="#nowhere" class="addCartLink"><button class="addCarButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button></a>
</div>

        </div>
<div style="clear: both;"></div>
    </div>
<!-- 
    上部图片区

 -->
 <div class="productReviewDiv" >
    <div class="productReviewTopPart">
        <a  href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
        <a  href="#nowhere" class="selected">累计评价 <span class="productReviewTopReviewLinkNumber">${p.reviewCount}</span> </a>
    </div>
     
    <div class="productReviewContentPart">
        <c:forEach items="${reviews}" var="r">
        <div class="productReviewItem">
         
            <div class="productReviewItemDesc">
                <div class="productReviewItemContent">
                    ${r.content }
                </div>
                <div class="productReviewItemDate"><fmt:formatDate value="${r.createDate}" pattern="yyyy-MM-dd"/></div>
            </div>
            <div class="productReviewItemUserInfo">
             
                ${r.user.anonymousName}
            </div>
             
            <div style="clear:both"></div>
         
        </div>
        </c:forEach>
    </div>
 
</div>

<div class="productDetailDiv" >
    <div class="productDetailTopPart">
        <a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
        <a href="#nowhere" class="productDetailTopReviewLink">累计评价 <span class="productDetailTopReviewLinkNumber">${p.reviewCount}</span> </a>
    </div>
     
    <div class="productParamterPart">
        <div class="productParamter">产品参数：</div>
         
        <div class="productParamterList">
            <c:forEach items="${pvs}" var="pv">
                <span>${pv.property.name}:  ${fn:substring(pv.value, 0, 10)} </span>
            </c:forEach>
        </div>
        <div style="clear:both"></div>
    </div>
     
    <div class="productDetailImagesPart">
            <c:forEach items="${p.productDetailImages}" var="pi">
                <img src="img/productDetail/${pi.id}.jpg">
            </c:forEach>
    </div>
</div>


    </div>
    <%@include file="../include/footer.jsp"%>
</body>
</html>