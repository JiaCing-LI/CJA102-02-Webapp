<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fftest.memprocpn.model.*"%>
<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
MemProCpnVO memProCpnVO = (MemProCpnVO) request.getAttribute("memProCpnVO");
%>
--<%= memProCpnVO==null %>--${memProCpnVO.cpnHolderDetailId}--<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>領取折價券折價卷明細新增 - addMemProCpn.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>折價卷明細新增 - addMemProCpn.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<form action="${pageContext.request.contextPath}/backend/memprocpn/MemProCpn.do" method="post">
    會員ID: <input type="text" name="memId" value="1"><br>
    折價券ID: <input type="text" name="proCpnId" value="5"><br>
    有效天數: <input type="text" name="validDays" value="30"><br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="領取">
</form>
<table>
	
	

	
</table>
<br>


</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>