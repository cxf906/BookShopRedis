<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookPageView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table align="center" width=90%>
      <jsp:include page="bhead.jsp"></jsp:include>   	
      <tr><td align="left"><h2>图书访问量统计</h2></td></tr>
      <tr>
      	<td>      	
      		<table border="0" width=60% align="center">       		
      			<tr><td>书号ISBN</td><td>图书名称</td><td>价格<td>访问次数</td></tr>
       			<c:forEach var="bk" items="${bkpv}">
       			   <tr><td>${bk.isbn}</td><td>${bk.bname}</td><td>${bk.price}<td>${bk.pageview}</td></tr>
       			</c:forEach>
    		</table>    	
      	</td>
      </tr>
    </table>
  </body>
</html>
