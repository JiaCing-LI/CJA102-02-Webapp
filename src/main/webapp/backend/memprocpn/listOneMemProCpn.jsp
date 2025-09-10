<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fftest.memprocpn.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
  MemProCpnVO memProCpnVO = (MemProCpnVO) request.getAttribute("memProCpnVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>



<html>
<head>
<title>商品折價卷明細 - listOnememProCpn.jsp</title>

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
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>折價卷明細資料 - listOneMemProCpn.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品折價卷持有者流水號</th>
		<th>商品折價卷編號</th>
		<th>一般會員編號</th>
		<th>折價券使用狀態</th>
		<th>建立時間</th>
		<th>領券時間</th>
		<th>實際生效起</th>
		<th>實際失效止</th>
		<th>使用時間</th>
	</tr>
	<tr>
		<td><%=memProCpnVO.getCpnHolderDetailId()%></td>
        <td><%=memProCpnVO.getProCpnId()%></td>
        <td><%=memProCpnVO.getMemId()%></td>
 <td>
  <%
    if (memProCpnVO.getCpnUseStatus() == 0) {
  %>
      未使用
  <%
    } else if (memProCpnVO.getCpnUseStatus() == 1) {
  %>
      已使用
  <%
    } else if (memProCpnVO.getCpnUseStatus() == 2) {
  %>
      已過期
  <%
    } else {
  %>
      未知
  <%
    }
  %>
</td>

<td><%= memProCpnVO.getCrtAt() %></td>
<td><%= memProCpnVO.getRcvAt() %></td>
<td><%= memProCpnVO.getEffStart() %></td>
<td><%= memProCpnVO.getEffEnd() %></td>
<td>
  <c:choose>
    <c:when test="${memProCpnVO.cpnUseStatus == 0}">
      未使用
    </c:when>
    <c:when test="${memProCpnVO.cpnUseStatus == 2}">
      已過期
    </c:when>
    <c:otherwise>
      ${memProCpnVO.usedAt}
    </c:otherwise>
  </c:choose>
</td>
</tr>
</table>

</body>
</html>