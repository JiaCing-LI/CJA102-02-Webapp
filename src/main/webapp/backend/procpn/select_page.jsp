<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>商品折價券管理系統: 查詢頁</title>

<style>
  table#table-1 {
    width: 450px;
    background-color: #E0FFFF;
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
   <tr><td><h3>商品折價券管理</h3><h4>( MVC 範例 )</h4></td></tr>
</table>

<p>這是商品折價券的查詢頁面</p>

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
  <li><a href='listAllProCpn.jsp'>列出所有折價券</a><br><br></li>
  
  <li>
    <form method="post" action="procpn/proCpn.do">
        <b>輸入折價券編號 (如1):</b>
        <input type="text" name="proCpnId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </form>
  </li>

  <jsp:useBean id="proCpnSvc" scope="page" class="fftest.procpn.model.ProCpnService" />

  <li>
    <form method="post" action="procpn/proCpn.do">
       <b>選擇折價券編號:</b>
       <select size="1" name="proCpnId">
         <c:forEach var="proCpnVO" items="${proCpnSvc.all}">
           <option value="${proCpnVO.proCpnId}">${proCpnVO.proCpnId}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </form>
  </li>
  
  <li>
    <form method="post" action="procpn/proCpn.do">
       <b>選擇折價券名稱:</b>
       <select size="1" name="proCpnId">
         <c:forEach var="proCpnVO" items="${proCpnSvc.all}">
           <option value="${proCpnVO.proCpnId}">${proCpnVO.cpnName}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </form>
  </li>
</ul>


<h3>折價券管理</h3>

<ul>
  <li><a href='addProCpn.jsp'>新增折價券</a></li>
</ul>

</body>
</html>