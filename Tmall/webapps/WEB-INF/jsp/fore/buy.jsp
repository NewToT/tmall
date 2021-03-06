<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <link rel="stylesheet" type="text/css" href="css/buy.css">
      <script type="text/javascript" src="js/jquery/2.0.0/jquery.min.js" ></script>

<script type="text/javascript" src="js/city-picker.data.js"></script>

<script type="text/javascript" src="js/city-picker.js" ></script>

<link rel="stylesheet" href="css/city-picker.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap/3.3.6/bootstrap.min.css">

  <script type="text/javascript">
             $(function(){
            	 $('#confirm').click(function(){
            			var a =$('#area').val();
                    	var b = $('input[name=addressM]').val();
                    	var c = a+b;
                    	$('input[name=address]').val(c);
                    	if(a.length == 0){
                    		alert("请选择城市");
                    		return false;
                    	}
                    	if(b.length == 0){
                    		alert("请输入详细地址");
                    		return false;
                    	}
                    	if($('input[name=receiver]').val().length == 0){
                    		alert("请输入收件人");
                    		return false;
                    	}
                    	if($('input[name=mobile]').val().length == 0){
                    		alert("请输入手机号码");
                    		return false;
                    	}
                    	$('.submitOrderButton').prop('disabled',false);
                    	$('.submitOrderButton').css({'cursor':'pointer','background-color':'#C40','border':'1px solid #C40'});
                    	alert("确认信息成功");
            	 })
             })
             </script>
</head>
<body>
<div class="buyPageDiv">
 <form action="forecreateOrder" method="post">
    <div class="buyFlow">
        <img class="pull-left" src="img/logo.png" width="150px;">

        <div style="clear:both"></div>
    </div>
    <div class="address">

        <div class="addressTip">输入收货地址</div>
     
         
            <table class="addressTable">
                <tr>
                    <td class="firstColumn">详细地址<span class="redStar">*</span></td>
                     
                    <td> 
                    <div style="position: relative;">
                             <input id="area" name="area" type="text" data-toggle="city-picker" size="70" />
                            </div> 
             </td>
             <td><input type="text" name="addressM" placeholder="详细街道">
             <input type="hidden" name="address"  value="">
           
             </td>
                </tr>
                <tr>
                    <td>邮政编码</td>
                    <td><input  name="post" placeholder="如果您不清楚邮递区号，请填写000000" type="text"></td>
                </tr>
                <tr>
                    <td>收货人姓名<span class="redStar">*</span></td>
                    <td><input  name="receiver"  placeholder="长度不超过25个字符" type="text"></td>
                </tr>
                <tr>
                    <td>手机号码 <span class="redStar">*</span></td>
                    <td><input name="mobile"  placeholder="请输入11位手机号码" type="text"></td>
                </tr>
            </table>
            
            <button type="button" id = "confirm" >确认信息</button>
            
        </div>

<!-- 地址区结束 -->


    <div class="productList">
          <div class="productListTip">确认订单信息</div>
          <!-- 商品详情遍历 -->
        <table class="productListTable">
            <thead>
                <tr>
                    <th colspan="2" class="productListTableFirstColumn">
                        
                        <a class="marketLink" href="#nowhere">有间专卖</a>
                        <a class="wangwanglink" href="#nowhere"> <span class="wangwangGif"></span> </a>
                    </th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>配送方式</th>
                </tr>
                <tr class="rowborder">
                    <td  colspan="2" ></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </thead>
            <tbody class="productListTableTbody">
                <c:forEach items="${ois}" var="oi" varStatus="st" >
                    <tr class="orderItemTR">
                        <td class="orderItemFirstTD"><img class="orderItemImg" src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg"></td>
                        <td class="orderItemProductInfo">
                        <a  href="foreproduct?pid=${oi.product.id}" class="orderItemProductLink">
                            ${oi.product.name}
                        </a>
                         
                            <img src="img/site/creditcard.png" title="支持信用卡支付">
                            <img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
                            <img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">
                         
                        </td>
                        <td>
                         
                        <span class="orderItemProductPrice">￥<fmt:formatNumber type="number" value="${oi.product.promotePrice}" minFractionDigits="2"/></span>
                        </td>
                        <td>
                        <span class="orderItemProductNumber">${oi.number}</span>
                        </td>
                        <td><span class="orderItemUnitSum">
                        ￥<fmt:formatNumber type="number" value="${oi.number*oi.product.promotePrice}" minFractionDigits="2"/>
                        </span></td>
                        <c:if test="${st.count==1}">
                        <td rowspan="5"  class="orderItemLastTD">
                        <label class="orderItemDeliveryLabel">
                            <input type="radio" value="" checked="checked">
                            普通配送
                        </label>
                         
                        <select class="orderItemDeliverySelect" class="form-control">
                            <option>快递 免邮费</option>
                        </select>
 
                        </td>
                        </c:if>
                         
                    </tr>
                </c:forEach>             
                 
            </tbody>
             
        </table>
         <!-- 商品详情遍历 -->


        <div class="orderItemSumDiv">
            <div class="pull-left">
                <span class="leaveMessageText">给卖家留言:</span>
               
                <span class="leaveMessageTextareaSpan">
                    <textarea name="userMessage" class="leaveMessageTextarea"></textarea>
                                    </span>
            </div>
             
           <span class="pull-right">合计: ￥<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/></span>
        </div>
         
    </div>
 
    <div class="orderItemTotalSumDiv">
        <div class="pull-right">
            <span>实付款：</span>
            <span class="orderItemTotalSumSpan">￥<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/></span>
        </div>
    </div>
     
    <div class="submitOrderDiv">
            <button type="submit" class="submitOrderButton" disabled="disabled" style="cursor:no-drop;background-color:#666;">提交订单</button>
    </div>
   </form>
</div>
</body>
</html>