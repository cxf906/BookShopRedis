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
    
    <title>My JSP 'BookComment.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
   <script type="text/javascript">
       function tijiao(){
         if( $('#bkComm').val() == ''){
            alert("评论信息不能为空");
         }else{
             form1.submit();
         }       
       }
       
       function fanhui(){
           window.location.href = "<%=basePath%>BookDetailSvl?isbn=${isbn}"
       }
       
       function zan(isbn,aid){
	        $.ajax({
			   type: "POST",
			   url: "<%=basePath%>PlzanSvl?isbn=" + isbn + "&aid=" + aid,
			   success: function(msg){			       
			      var sid = "#zan"+aid;
			      $(sid).html(msg.toString());		
			   }
			});
       }
             
   </script>
  </head>
  
  <body>
        <table align="center" width=90%>
  		<tr>
  		<td align=right>
      	    <jsp:include page="mhead.jsp"></jsp:include>      	  		
      	</td>
  		</tr>
  		<tr><td>用户评论(${isbn})：</td></tr>
  		<tr>
  		<td>
  		    <form id="form1" action="<%=basePath%>BookCommentSvl" method="post">
  					<textarea rows="3" cols="30" id="bkComm" name="bkComm"></textarea> 
  			   			&nbsp; <input type="button" onclick="tijiao()" value="提交"/>
  			   			&nbsp; <input type="button" onclick="fanhui()" value="返回"/>
  			   			<input type="hidden" name="isbn" value="${isbn}"/>
  			 </form>
  		</td>
  		</tr>	
  		<c:forEach var="bc" items="${bcList}">
  		     <tr>
  		     <td>
  		        <ul>
  		           <li>${bc.uname} &nbsp;&nbsp; ${bc.pdate} &nbsp;&nbsp; <a href="#" onclick="zan('${isbn}',${bc.aid});return false;"><img src="<%=basePath%>pic/thumb.png"/></a>&nbsp;<span id="zan${bc.aid}">${bc.zanNum}</span> </li>
  		           <li>${bc.info}</li>  		          
  		        </ul>
  		     </td>
  		    </tr>
  		    <tr><td>&nbsp;&nbsp;</td></tr>
  		</c:forEach>
  	</table>
  </body>
</html>
