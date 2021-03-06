<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	   
<script>
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});


</script>


<nav>
  <ul class="pagination">
    <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
      <a  href="?start=0${page.param}" aria-label="Previous" >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
      <a  href="?start=${page.start-page.count}${page.param}" aria-label="Previous" >
        <span aria-hidden="true">&lsaquo;</span>
      </a>
    </li>    

    <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
    <!-- 
    varStatus=“status”事实上定义了一个status名的对象作为varStatus的绑定值。

该绑定值也就是status封装了当前遍历的状态，比如，可以从该对象上查看是遍历到了第几个元素：${status.count}
index  : 当前这次迭代从 0 开始的迭代索引
count  : 当前这次迭代从 1 开始的迭代计数
  -->
    
    	
		    <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
		    
		    	<a  href="?start=${status.index*page.count}${page.param}"
		    	
		    	<c:if test="${status.index*page.count==page.start}">class="current"</c:if>>${status.count}</a>
		    </li>
		
    </c:forEach>

    <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
      <a href="?start=${page.start+page.count}${page.param}" aria-label="Next">
        <span aria-hidden="true">&rsaquo;</span>
      </a>
    </li>
    <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
      <a href="?start=${page.last}${page.param}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
