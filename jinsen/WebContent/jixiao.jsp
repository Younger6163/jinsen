<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>绩效管理界面</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
       <style>
    </style>
</head>
<body>
<main class="all">
   <aside class="aside">
    <header class="aside-head"><img src="img/logo.jpg"> </header>
    <div class="aside-per">
        <div><img src="img/my.jpg" class="per-img"> </div>
        <div><h3>xxxx</h3>
        <p>行政专员</p>
        <p>公司人事部一组</p></div>
    </div>
    <div class="clear"></div>
    <div class="aside-date">
        <p class="douline"><i class="i-date"></i> 日历</p>
        <div id="ca" class="date-con"></div>
    </div>
    <div class="clear"></div>
    <div class="aside-date">
        <p class="douline"><i class="i-list"></i>登录日志</p>
       <p class="mes-styel"><label>登录IP：</label>192.168.11.23</p>
        <p class="mes-styel"><label>登录时间：</label>2018-11-12 10:12:32</p>
    </div>
</aside>		<li><a href="">个人中心</a></li>
    	</ul>
    </div>
</aside>
    <article class="artlce">
        <header class="aside-head">
            <ul class="nav-ul">
            <li class="active-li"><a href="in_performance.jsp" target="fraName">录入员工绩效信息</a> </li>
            <li><a href="stat_department.jsp" target="fraName">录入部门绩效信息</a> </li>
            <li><a href="create_dp_performance.jsp" target="fraName">生成部门绩效统计表</a> </li>
            <li><a href="create_staff_perfor.jsp" target="fraName" >生成部门员工绩效确认单</a> </li>
            <li><a href="payperfor.jsp" target="fraName">绩效发放</a> </li>
            </ul>
        </header>
        <section class="section">
            <iframe class="ifraem-style" frameborder="0" src="in_performance.jsp" name="fraName"></iframe>

        </section>
    </article>
</main>
<script src="js/jquery.js"></script>
<script src="js/jQueryCalendar/js/calendar.js"></script>
</body>
</html>