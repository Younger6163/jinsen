<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>查询工资单</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body onload="table1()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>绩效管理</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是查询员工基本薪级情况，并可进行调整。</p>
    </div>
    <div class="find-top">
        <!--  <span class="find-span">姓名检索：<input type="text" id="name" class="input-find"><button onclick="hah()" class="add-but"><i class="glyphicon glyphicon-search"></i> 查找</button></span>-->
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    <div id="create_salarylist">
    	<p style="float:right">年份：<select id="selectyear" readonly unselectable="on"></select>月份：<select id="month" readonly unselectable="on"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select><br><button class="add-but" onclick="create_staff_salarylist()">生成工资单</button></p>
    </div>
    <div class="table-con">
        <table id="table2" class="table-style"></table>
    </div>
</main>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    //$(function(){
    //    table1();
    //})
    function table1(){
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#selectyear").show().append(str);
    	}
    	$('#table1').bootstrapTable('destroy');
        $('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "servlet",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams:function queryParams(params){
                var temp = {
						action:"salarylist_all"  
				};     
                return temp;
            },
            columns: [
                {
                    title: "员工编号",
                    field: 'staff_id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '姓名',
                    field: 'staff_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '所属部门',
                    field: 'staff_department',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '岗位',
                    field: 'staff_post',
                    align: 'center',
                    valign: 'middle'
                },
                   {
                    	title: '薪资',
                        field: 'staff_basicpay',
                        align: 'center',
                        valign: 'middle'
                   },
                   {
                   	title: '出勤天数',
                       field: 'staff_salary_list_attendday',
                       align: 'center',
                       valign: 'middle'
                  },{
                  	title: '请假天数',
                    field: 'staff_salary_list_offnum',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '旷工天数',
                    field: 'staff_salary_list_absentnum',
                    align: 'center',
                    valign: 'middle'
               },{
                 	title: '加班天数',
                    field: 'staff_salary_list_overtime',
                    align: 'center',
                    valign: 'middle'
               },{
                 	title: '加班工资',
                    field: 'staff_salary_list_overtimesalary',
                    align: 'center',
                    valign: 'middle'
               },
               {
                  	title: '应扣款项',
                    field: 'staff_salary_list_deductmoney',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '应发工资',
                    field: 'staff_salary_list_ssalary',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '实发工资',
                    field: 'staff_salary_list_netpayroll',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '已发工资',
                    field: 'staff_salary_list_yetpay',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '剩余工资',
                    field: 'staff_salary_list_residue',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '结算时间',
                    field: 'staff_salary_list_stockdater',
                    align: 'center',
                    formatter:function(value,row,index){
                   	 var myDate = new Date(value);
                        var year=myDate.getFullYear();
                        var month=myDate.getMonth()+1;
                        month = month < 10 ? "0"+month : month;
                        var date=myDate.getDate();
                        date = date < 10 ? "0"+date : date;
                        var h=myDate.getHours();     
                        h = h < 10 ? "0"+h : h;
                        var m=myDate.getMinutes();    
                        m = m < 10 ? "0"+m : m;
                        var s=myDate.getSeconds();
                        s = s < 10 ? "0"+s : s;
                        var time =year+'-'+month+"-"+date;
                        return time;
                    },
                    valign: 'middle'
               },{
                  	title: '发放状态',
                    field: 'staff_salary_list_status',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '备注',
                    field: 'staff_salary_list_remark',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '发放时间',
                    field: 'staff_salary_list_paytime',
                    formatter:function(value,row,index){
                   	 var myDate = new Date(value);
                        var year=myDate.getFullYear();
                        var month=myDate.getMonth()+1;
                        month = month < 10 ? "0"+month : month;
                        var date=myDate.getDate();
                        date = date < 10 ? "0"+date : date;
                        var h=myDate.getHours();     
                        h = h < 10 ? "0"+h : h;
                        var m=myDate.getMinutes();    
                        m = m < 10 ? "0"+m : m;
                        var s=myDate.getSeconds();
                        s = s < 10 ? "0"+s : s;
                        var time =year+'-'+month+"-"+date+" "+h+":"+m+":"+s;
                        if(value==null)
                        	return null;
                        else
                       	    return time;
                    },
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '年份',
                    field: 'year',
                    align: 'center',
                    valign: 'middle'
               },{
                 	title: '月份',
                    field: 'month',
                    align: 'center',
                    valign: 'middle'
               }
            ]
        });
    }
</script>
<script type="text/javascript">
function create_staff_salarylist()
{
	$.ajax({
        url:"servlet",//要发送的地址
        data:{
            "action":"find_staff_salarylistl",
            "month":document.getElementById("month").value,
            "year":document.getElementById("selectyear").value
        },
        type: "POST",
        dataType:"json",
        success: function (data) {
        	 //location.reload();
        	 $('#table2').bootstrapTable('destroy');
        	$('#table2').bootstrapTable({
                method: "post",
                striped: false,
                singleSelect: false,
                cache: false,//缓存
                url: "servlet",
                dataType: "json",
                pagination: true, //分页
                pageSize: 10,
                pageNumber: 1,
                search:false, //显示搜索框
                contentType: "application/x-www-form-urlencoded",
                queryParams:function queryParams(params){
                    var temp = {
    						action:"salarylist_all_m_y",
    						year:document.getElementById("selectyear").value,
    						month:document.getElementById("month").value
    				};     
                    return temp;
                },
                columns: [
                    {
                        title: "员工编号",
                        field: 'staff_id',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '姓名',
                        field: 'staff_name',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '所属部门',
                        field: 'staff_department',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '岗位',
                        field: 'staff_post',
                        align: 'center',
                        valign: 'middle'
                    },
                       {
                        	title: '薪资',
                            field: 'staff_basicpay',
                            align: 'center',
                            valign: 'middle'
                       },
                       {
                       	title: '出勤天数',
                           field: 'staff_salary_list_attendday',
                           align: 'center',
                           valign: 'middle'
                      },{
                      	title: '请假天数',
                        field: 'staff_salary_list_offnum',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '旷工天数',
                        field: 'staff_salary_list_absentnum',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '加班天数',
                        field: 'staff_salary_list_overtime',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '加班工资',
                        field: 'staff_salary_list_overtimesalary',
                        align: 'center',
                        valign: 'middle'
                   },
                   {
                      	title: '应扣款项',
                        field: 'staff_salary_list_deductmoney',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '应发工资',
                        field: 'staff_salary_list_ssalary',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '实发工资',
                        field: 'staff_salary_list_netpayroll',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '已发工资',
                        field: 'staff_salary_list_yetpay',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '剩余工资',
                        field: 'staff_salary_list_residue',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '结算时间',
                        field: 'staff_salary_list_stockdater',
                        align: 'center',
                        formatter:function(value,row,index){
                       	 var myDate = new Date(value);
                            var year=myDate.getFullYear();
                            var month=myDate.getMonth()+1;
                            month = month < 10 ? "0"+month : month;
                            var date=myDate.getDate();
                            date = date < 10 ? "0"+date : date;
                            var h=myDate.getHours();     
                            h = h < 10 ? "0"+h : h;
                            var m=myDate.getMinutes();    
                            m = m < 10 ? "0"+m : m;
                            var s=myDate.getSeconds();
                            s = s < 10 ? "0"+s : s;
                            var time =year+'-'+month+"-"+date;
                            return time;
                        },
                        valign: 'middle'
                   },{
                      	title: '发放状态',
                        field: 'staff_salary_list_status',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '备注',
                        field: 'staff_salary_list_remark',
                        align: 'center',
                        valign: 'middle'
                   },{
                      	title: '发放时间',
                        field: 'staff_salary_list_paytime',
                        formatter:function(value,row,index){
                       	 var myDate = new Date(value);
                            var year=myDate.getFullYear();
                            var month=myDate.getMonth()+1;
                            month = month < 10 ? "0"+month : month;
                            var date=myDate.getDate();
                            date = date < 10 ? "0"+date : date;
                            var h=myDate.getHours();     
                            h = h < 10 ? "0"+h : h;
                            var m=myDate.getMinutes();    
                            m = m < 10 ? "0"+m : m;
                            var s=myDate.getSeconds();
                            s = s < 10 ? "0"+s : s;
                            var time =year+'-'+month+"-"+date+" "+h+":"+m+":"+s;
                            if(value==null)
                            	return null
                            else
                            	return time;
                        },
                        align: 'center',
                        valign: 'middle'
                   },{
                     	title: '年份',
                        field: 'year',
                        align: 'center',
                        valign: 'middle'
                   },{
                     	title: '月份',
                        field: 'month',
                        align: 'center',
                        valign: 'middle'
                   }
                ]
            });
        }
    })
}
</script>
</body>
</html>