window.onload=function(){
				
				var ad=document.getElementById("ad");
				var close=document.getElementById("close");
				var x=1;
				var y=1;
//				获取窗口可视宽高
				var docH = document.documentElement.clientHeight;
				var docW = document.documentElement.clientWidth;	
				
//				获取图片自身的宽高
				var selfH=ad.offsetHeight;
				var selfW=ad.offsetWidth;
				
//				计算图片的活动区域
				var CanTop=docH-selfH;
				var CanLeft=docW-selfW;
				
//					让图片动起来
					function run(){
//						获取初始数据
						var oldTop=ad.offsetTop;
						var oldLeft=ad.offsetLeft;
						
//						设置新值
						var newTop=oldTop+x;
						var newLeft=oldLeft+y;
						
//						加速处理: 针对广告移动速度较快超出可视区域的处理
//						if(newTop>CanTop){
//							newTop=CanTop;
//						}
//						if(newLeft>CanLeft){
//							newLeft=CanLeft;
//						}
//						if(newTop < 0){
//						newTop = 0;
//						}
//						if(newLeft <0){
//							newLeft = 0;
//						}
						
//						赋值
						ad.style.top=newTop+'px';
						ad.style.left=newLeft+'px';
						
//						图片触底返回:
//						判断：如果偏移宽高和可活动区域宽高相等或偏移宽高为0时，将偏移量设为负值；
						if(newTop == CanTop || newTop == 0){
							x = x * -1;
						}
						if(newLeft == CanLeft || newLeft == 0){
							y = y * -1;
						}
						
					}
					
					var timer=setInterval(run,5);
					
					
//					当鼠标移入时停止
					ad.onmouseover=function(){
						clearInterval(timer);
					}
//					当鼠标移出时继续
					ad.onmouseout=function(){
					   timer=setInterval(run,5);
					}
					
//					点击X关闭广告
					close.onclick=function(){
						ad.style.display='none';
					}
					
//					窗口改变事件
					ad.onresize=function(){
//						将元素位置初始化
						ad.style.top = 0;
						ad.style.left = 0;
//						将速度初始化
						var x=3;
						var y=3;
//						获取新的页面可视区域
						var docH=document.documentElement.clientHeight;
						var docW=document.documentElement.clientWidth;
//						得到之前的元素自身的宽高;
						
//						计算新的可活动区域；
						var CanTop=docH-selfH;
						var CanLeft=docW-selfW;
					}
					
//					中间鼠标移入切换广告
					var tmallMakCcenter=document.getElementsByClassName("tmall-mak-center")[0];
					var left=tmallMakCcenter.getElementsByTagName("center-mak-left")[0];
					var right=tmallMakCcenter.getElementsByTagName("center-mak-right")[0];
					var span_con=tmallMakCcenter.getElementsByTagName("span");
					var div_con=document.getElementsByClassName("tmall-mak-center-content");
					for (var i=0;i<span_con.length;i++) {
						span_con[i].index=i;
						span_con[i].onclick=function(){
							for (var j=0;j<div_con.length;j++) {
								div_con[j].style.display="none";
							}
							div_con[this.index].style.display="block";

						}	
					}
					
				
			}