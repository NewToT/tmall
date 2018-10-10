<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<head>
<link rel="stylesheet" href="css/confirmPay.css" />
</head>
<body>

<%@include file="../include/header.jsp"%>
<%@include file="../include/top.jsp"%>
<%@include file="../include/simpleSearch.jsp"%>
<div class="orderFinishDiv">
    <div class="orderFinishTextDiv">
        <img src="img/site/orderFinish.png">
        <span>交易已经成功，卖家将收到您的货款。</span>
    </div>
</div>
</body>