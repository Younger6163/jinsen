<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>薪资管理界面</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
   <style>
    </style>
</head>
<body>
<main class="all">
    <article class="artlce">
        <header class="aside-head">
            <ul class="nav-ul">
            <li class="active-li"><a href="in_salary.jsp" target="fraName">录入职级信息</a> </li>
            <li><a href="adjust_salary.jsp" target="fraName">工资调整</a> </li>
            <li><a href="find_salarylist.jsp" target="fraName">查询工资单</a> </li>
            <li><a href="paysalary.jsp" target="fraName" >发放工资</a> </li>
            <li><a href="outlist.jsp" target="fraName">导出报表</a> </li>
            </ul>
        </header>
        <section class="section">
            <iframe class="ifraem-style" frameborder="0" src="in_salary.jsp" name="fraName"></iframe>

        </section>
    </article>
</main>
<script src="js/jquery.js"></script>
<script src="js/jQueryCalendar/js/calendar.js"></script>
<script>
    $('#ca').calendar({
        width: 240,
        height: 280,
        data: [
            {
                date: '2015/12/24',
                value: 'Christmas Eve'
            },
            {
                date: '2015/12/25',
                value: 'Merry Christmas'
            },
            {
                date: '2016/01/01',
                value: 'Happy New Year'
            }
        ],
        onSelected: function (view, date, data) {
            console.log('view:' + view)
            alert('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });
    $(function(){
        $(".nav-ul li").click(function(){
            $(this).addClass("active-li").siblings().removeClass("active-li");
            $(this).find("ul").show();
            $(this).siblings().find('ul').hide();
        })
    })
</script>
</body>
</html>