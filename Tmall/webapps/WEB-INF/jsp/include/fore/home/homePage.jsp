<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <head>	
 <link rel="stylesheet" type="text/css" href="css/index1.css">
    <link rel="stylesheet" type="text/css" href="css/iconfont.css"/>
		<title>一间商店官网</title>
		</head>

 <body>
<div id="container">
		<div id="shop-nav">
		<ul>
			<li class="shop-nav-title"><i class="icon iconfont icon-daohang"></i><a  href="">商品分类</a></li>
			<li><a href=""><img src="img/tmall-market.png" alt="天猫超市" /></a></li>
			<li><a href=""><img src="img/tmall-guoji.png" alt="天猫国际" /></a><li>
			<li><a href="">天猫会员</a></li>
			<li><a href="">电器城</a></li>
			<li><a href="">喵鲜生</a></li>
			<li><a href="">医药馆</a></li>
			<li><a href="">营业厅</a></li>
			<li><a href="">魅力惠</a></li>
			<li><a href="">飞猪旅行</a></li>
			<li><a href="">苏宁易购</a></li>
		</ul>
	</div>
	
	<!--主内容区-->
	<div id="main-content">
		<ul class="main-left-nav">
<c:forEach items="${cs}" var="c"> 
			<li class="cateName">
				<div>
					 <i class="glyphicon glyphicon-heart-empty"></i><a href="forecategory?cid=${c.id}">${c.name}</a>
				</div>

					<div class="hideContent">
						   <c:forEach items="${c.productsByRow}" var="ps">
                                    <c:forEach items="${ps}" var="p">
							<ul>
								<li><c:if  test="${!empty p.subTitle}">

                                                    <a href="foreproduct?pid=${p.id}">
                                                        <c:forEach items="${fn:split(p.subTitle,' ')}" var="title" varStatus="st">
                                                        
                                                        <c:if test="${st.index==0}">
                                                    ${title}
                                                </c:if>
                                                </c:forEach>
                                                </a>

                                            </c:if>
                                        </li>
                                </ul>
                                 </c:forEach>
                                  </c:forEach>
					</div>
<!-- froeach 2-->
			</li>

		</c:forEach>
		</ul>

	</div>
	
	<!--轮播图下方广告区  -->
<div class="line-pic">
<span>你的专属活动</span>
<ul>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
</ul>
</div>

<div class="adv">
<div class="adv-left">
    <p>品牌闪购 BRAND SALE</p>
    <a href=""><img src="img/scroll-bottom-left.png" width="234px" height="314px;"></a>
</div>

<div class="adv-center">
<p>聚名品 LUXURY CHANNEL</p>
<a href=""><img src="img/scroll-bottom-center.png" width="470px" height="300px;"></a>
</div>

<div class="ss">
<p>品牌活动 BRAND ACTIVITY</p>
<a href=""><img src="img/scroll-bottom-right.png"width="478px" height="300px;"></a>
</div>

</div>
<div  class="allCatAndPro">
<c:forEach items="${cs}" var="c" varStatus="stc">
	
	<div class="eachCat">
		<p>${c.name}</p>
		<c:forEach items="${c.products}" var="p" varStatus="st">
			<c:if test="${st.count<=5 }">
			<div class="products">

				<a href="foreproduct?pid=${p.id}"><img src="img/productSingle_middle/${p.firstProductImage.id}.jpg"></a>
				<a href="foreproduct?pid=${p.id}" class="message">${fn:substring(p.name,0,15)}</a>
				<span  class="price">
				<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
				</span>

			</div>
			</c:if>
		</c:forEach>
		<div style="clear:both"></div>
	</div>
	
</c:forEach>

</div>

<div class="line-pic">
<span>热门推荐</span>
<ul>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
<li><a href=""><img src="img/five-4.png"></a></li>
</ul>
</div>


	<div class="static">
    <div class="e1">

            <div class="e2">

            <div class="ea">
<img src="img/ea.png">
            </div>

            <div class="eb">
                <div class="ec">
                    <div class="ed">
                        <div class="ef"><img src="img/ef.jpg"><img src="img/ef1.png" class="ef1"></div>
                        <div class="eg"><img src="img/ef.jpg"><img src="img/ef1.png" class="ef1"></div>
                        <div class="ee"><img src="img/ef.jpg"><img src="img/ef1.png" class="ef1"></div>
                    </div>
                </div>
            </div>


            <div class="eh">
                    <a href=""><img src="img/eh1.png"></a>
            </div>
            <div class="eh2"><a href=""><img src="img/eh2.png"></a></div>
            <div class="eh3"><a href=""><img src="img/eh3.png"></a></div>
            <div class="eh4"><a href=""><img src="img/eh4.png"></a></div>
            <div class="eh5"><a href=""><img src="img/eh8.png"></a></div>
              <div class="eh6"><a href=""><img src="img/eh11.png"></a></div>
               <div class="eh7"><a href=""><img src="img/eh7.png"></a></div>
                <div class="eh8"><a href=""><img src="img/eh8.png"></a></div>
                 <div class="eh9"><a href=""><img src="img/eh9.png"></a></div>
                  <div class="eh10"><a href=""><img src="img/eh10.png"></a></div>
                   <div class="eh11"><a href=""><img src="img/eh11.png"></a></div>
                    <div class="eh12"><a href=""><img src="img/eh12.png"></a></div>
            </div>




</div>

</div>
</div>
</body>