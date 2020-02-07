<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<%
    Locale locale = request.getLocale();
    Calendar calendar = Calendar.getInstance(locale);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    String greeting = "";
    if (hour <= 6) {
        greeting = "现在是凌晨";
    } else if (hour <= 9) {
        greeting = "早上好！";
    } else if (hour <= 12) {
        greeting = "上午好！";
    } else if (hour <= 18) {
        greeting = "下午好！";
    } else if (hour <= 24) {
        greeting = "晚上好！";
    }
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${path}/statics/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${path}/statics/css/public.css" />
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市订单管理系统</h1>
        <div class="publicHeaderR">
            <p><span><%=greeting%></span><span style="color: #fff21b"> ${loginUser.username }</span> , 欢迎你！</p>
            <a href="${path}/logout">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
         <nav>
             <ul class="list">
                 <li ><a href="${path}/sys/bill">订单管理</a></li>
              <li><a href="${path }/sys/provider">供应商管理</a></li>
              <li><a href="${path }/sys/user">用户管理</a></li>
              <li><a href="${path }/sys/pwdmodify">密码修改</a></li>
              <li><a href="${path }/logout">退出系统</a></li>
             </ul>
         </nav>
     </div>
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>