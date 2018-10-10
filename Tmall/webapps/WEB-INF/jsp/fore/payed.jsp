<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/payed.css">
<title>支付成功</title>
</head>
<body>
<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>
<%@include file="../include/simpleSearch.jsp"%>
<div class="payedDiv">
    <div class="payedTextDiv">
        <img src="img/site/paySuccess.png">
        <span>您已成功付款</span>
         
    </div>
    <div class="payedAddressInfo">
        <ul>
            <li>收货地址：${o.address} ${o.receiver} ${o.mobile }</li>
            <li>实付款：<span class="payedInfoPrice">
            ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/>
            </li>
            <li>预计07月23日送达    </li>
        </ul>
                 
        <div class="paedCheckLinkDiv">
            您可以
            <a class="payedCheckLink" href="forebought">查看已买到的宝贝</a>
            <a class="payedCheckLink" href="forebought">查看交易详情 </a>
        </div>
             
    </div>
     
    <div class="payedSeperateLine">
    </div>
     
    <div class="warningDiv">
        <img src="img/site/warning.png">
        <b>安全提醒：</b>下单后，<span class="redColor boldWord">用QQ给您发送链接办理退款的都是骗子！</span>不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！
    </div>
 
</div>
<%@include file="../include/footer.jsp"%>
</body>
</html>