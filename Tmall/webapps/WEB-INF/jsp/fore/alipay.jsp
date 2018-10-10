<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<link rel="stylesheet" type="text/css" href="css/alipay.css">
<title>请使用支付宝支付</title>
</head>
<body>
<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>
<div class="aliPayPageDiv">
    <div class="aliPayPageLogo">
        <img class="pull-left" src="img/site/simpleLogo.png">
        <div style="clear:both"></div>
    </div>
     
    <div>
        <span class="confirmMoneyText">扫一扫付款（元）</span>
        <span class="confirmMoney">
        ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>
         
    </div>
    <div>
        <img class="aliPayImg" src="img/site/alipay2wei.png">
    </div>
 
    <div>
        <a href="forepayed?oid=${param.oid}&total=${param.total}"><button class="confirmPay">确认支付</button></a>
    </div>
 
</div>
<%@include file="../include/footer.jsp"%>
</body>
</html>