<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>属性值管理</title>
<script>
$(function() {
    $("input.pvValue").keyup(function(){
        var value = $(this).val();
        var page = "admin_propertyValue_update";
        var pvid = $(this).attr("pvid");
        var parentSpan = $(this).parent("span");
        $.post(
                page,
                {"value":value,"id":pvid},
                function(result){
                    if("success"==result)
                        parentSpan.css("border","1px solid yellow");
                    else
                        parentSpan.css("border","1px solid red");
                }
            );     
    });
});

</script>
</head>
<body>
<div class="workingArea">
    <ol class="breadcrumb">
      <li><a href="admin_category_list">所有分类</a></li>
      <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
      <li class="active">${p.name}</li>
      <li class="active">编辑产品属性</li>
    </ol>
     
    <div class="editPVDiv">
        <c:forEach items="${pvs}" var="pv">
            <div class="eachPV">
                <span class="pvName" >${pv.property.name}</span>
                <span class="pvValue"><input class="pvValue" pvid="${pv.id}" type="text" value="${pv.value}" onblur = "tips()"></span>
            </div>
        </c:forEach>
      
    <div style="clear:both"></div> 
    
    </div>
       <h1 align="center"> 修改成功，输入框变为黄色</h1>
</div>
</body>
</html>