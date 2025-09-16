<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="fftest.memprocpn.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
MemProCpnService memProCpnSvc = new MemProCpnService();
    List<MemProCpnVO> list = memProCpnSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有折價卷資料 - listAllMemProCpn.jsp</title>

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
</style>

<style>
  table {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 10px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>所有折價卷明細資料 - listAllMemProCpn.jsp</h3>
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
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
<%@ include file="page1.file" %> 
	<c:forEach var="memProCpnVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		<td>${memProCpnVO.cpnHolderDetailId}</td>
        <td>${memProCpnVO.proCpnId}</td>
        <td>${memProCpnVO.memId}</td>
        <td>
          <c:choose>
            <c:when test="${memProCpnVO.cpnUseStatus == 0}">未使用</c:when>
            <c:when test="${memProCpnVO.cpnUseStatus == 1}">已使用</c:when>
            <c:when test="${memProCpnVO.cpnUseStatus == 2}">已過期</c:when>
            <c:otherwise>未知</c:otherwise>
          </c:choose>
        </td>

        <td><fmt:formatDate value="${memProCpnVO.crtAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${memProCpnVO.rcvAt}</td>
        <td>${memProCpnVO.effStart}</td>
        <td>${memProCpnVO.effEnd}</td>
        <td>${memProCpnVO.usedAt}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/memprocpn/MemProCpn.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cpnHolderDetailId"  value="${memProCpnVO.cpnHolderDetailId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/memprocpn/MemProCpn.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="cpnHolderDetailId"  value="${memProCpnVO.cpnHolderDetailId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>