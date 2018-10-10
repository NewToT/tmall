<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<html>
<head>
 <link rel="stylesheet" href="css/login.css">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script>
   $(function(){
	   var msg=$('input[name=msg]').val();

		   if(!msg.length==0){ 
			   alert(msg);
		   }
	   
   })
    </script>
<title>理想生活，一间商店</title>
</head>
<body>

    <div class="mallPage">
            <div class="header">
                <a href="forehome" title="一间商店">
                <img class="log" src="img/logo.png" alt="">
                </a>
            </div>

    <div class="content">
        <div class="wrap" style="width: 100%;background-color: rgb(242,64,60);height=450px;">
                <div class="inner">
                    <img src="img/01.jpg" alt="" style="display: block;margin: auto;">
                </div>
            </div>
       

        <div class="l_form">
            <div class="form_inner">
                <div class="form_content">
                    <div class="bd">
                        <form action="forelogin"method="post">                        
                            <h5>登录</h5>
                        <div style="position: relative;top: -10px;">
                    <i class="glyphicon glyphicon-user"></i>
                    用户名：<input type="text" name="name" placeholder="用户名"></div>
                    <div style="position: relative;top: 20px;">
                    <i class="  glyphicon glyphicon-lock"></i>
                    密码：<input type="password" name="password" placeholder="密码" style="margin-left: 14px;"></div>
                    </div>
                    <input type="submit" value="登录" style="height:40px;width:230px;color: #fff; background-color: rgb(242,64,60);border: none;margin-left: 30px;margin-top: 20px;margin-bottom: 75px;font-weight: 700;">
                 </form>
                </div>
                 <a href="fore" style="float: right; position: relative;bottom: 50px;right: 45px;color: black;">注册</a>
            </div>
        </div>
        
        
        
        
    </div>

<input type="hidden" name="msg" value="${msg}"/>
<%@include file="../include/footer.jsp" %>

    </div>
</body>
</html>