	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<html>
	<head>
	<title>IBM MemProCpn: Home</title>
	
	<style>
	  table#table-1 {
		width: 450px;
		background-color: #CCCCFF;
		margin-top: 5px;
		margin-bottom: 10px;
	    border: 3px ridge Gray;
	    height: 80px;
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
	
	</head>
	<body bgcolor='white'>
	
	<table id="table-1">
	   <tr><td><h3>IBM MemProCpn: Home</h3><h4>( MVC )</h4></td></tr>
	</table>
	
	<p>This is the Home page for IBM MemProCpn: Home</p>
	
	<h3>資料查詢:</h3>
		
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<ul>
	  <li><a href='listAllMemProCpn.jsp'>List</a> all MemProCpns.  <br><br></li>
	  
	  
	  <li>
	    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/backend/memprocpn/MemProCpn.do" >
	        <b>輸入折價卷持有者編號 (如1):</b>
	        <input type="text" name="cpnHolderDetailId">
	        <input type="hidden" name="action" value="getOne_For_Display">
	        <input type="submit" value="送出">
	    </FORM>
	  </li>
	
	  <jsp:useBean id="MemProCpnSvc" scope="page" class="fftest.memprocpn.service.MemProCpnServiceImpl" />
	   
	  <li>
	     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/backend/memprocpn/MemProCpn.do" >
	       <b>選擇折價卷明細編號:</b>
	       <select size="1" name="cpnHolderDetailId">
	         <c:forEach var="MemProCpnVO" items="${MemProCpnSvc.all}" > 
	          <option value="${MemProCpnVO.cpnHolderDetailId}">${MemProCpnVO.cpnHolderDetailId}
	         </c:forEach>   
	       </select>
	       <input type="hidden" name="action" value="getOne_For_Display">
	       <input type="submit" value="送出">
	    </FORM>
	  </li>
	  
	  <li>
	     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/backend/memprocpn/MemProCpn.do" >
	       <b>選擇狀態:</b>
	        <select size="1" name="cpnUseStatus">
	          <option value="0">0</option>
  <option value="1">1</option>
  <option value="2">2</option>  
	       </select>
	       <input type="hidden" name="action" value="getOne_For_Display">
	       <input type="submit" value="送出">
	     </FORM>
	  </li>
	</ul>
	
	
	
	<ul>
	  <li><a href='addMemProCpn.jsp'>Add</a> a new MemProCpn.</li>
	</ul>
	
	</body>
	</html>