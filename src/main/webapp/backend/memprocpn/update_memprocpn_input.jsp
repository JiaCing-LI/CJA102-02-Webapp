<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fftest.memprocpn.model.*"%>

<%
  MemProCpnVO memProCpnVO = (MemProCpnVO) request.getAttribute("memProCpnVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>折價卷明細資料修改 - update_memprocpn_input.jsp</title>

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
		 <h3>商品折價卷持有者資料修改 - update_memprocpn_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/backend/memprocpn/MemProCpn.do" name="form1">
<table>
	<tr>
		<td>商品折價卷持有者流水號:<font color=red><b>*</b></font></td>
		<td><%=memProCpnVO.getCpnHolderDetailId() %></td>
	</tr>
	<tr>
		<td>商品折價卷編號:</td>
		<td><%=memProCpnVO.getProCpnId()%></td>
	</tr>
<tr>
		<td>一般會員編號:</td>
		<td><%=memProCpnVO.getMemId()%></td>
	</tr>
	<tr>
  <td>使用狀態:</td>
  <td>
    <input type="checkbox" name="cpnUseStatus" value="1"
      <c:if test="${memProCpnVO.cpnUseStatus == 1}">checked</c:if> />
    已使用
  </td>
</tr>
	
</table>
<br>
<input type="hidden" name="action" value="updateStatus">
<input type="hidden" name="cpnHolderDetailId" value="<%=memProCpnVO.getCpnHolderDetailId()%>">
<input type="hidden" name="proCpnId" value="<%=memProCpnVO.getProCpnId()%>">
<input type="hidden" name="memId" value="<%=memProCpnVO.getMemId()%>">
<input type="submit" value="送出修改"></FORM>
<br>
<button type="button" onclick="location.href='listAllMemProCpn.jsp'">取消</button>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


</html>