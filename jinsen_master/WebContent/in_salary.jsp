<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>录入职级信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
	 function load(){ 

		 $.ajax({
	            url:"servlet",//要发送的地址
	            data:{
	                "action":"find_staff_all"
	            },
	            type: "POST",
	            dataType:"json",
	            success: function (data) {
	                var str="";
	                var i =0;
	                str = "<option></option>";
	                $("#select").show().append(str);
	                for(i = 0;i<data.length;i++)
	                {
	                	str = "<option>"+data[i].staff_id+"</option>";
	                	
	                	$("#select").show().append(str);
	                }
	            }
	        })
	} 
	 function change(){
		 $.ajax({
	            url:"servlet",
	            data:{
	                "action":"find_staff_id",
	                "id":document.getElementById("select").value
	            },
	            type: "POST",
	            dataType:"json",
	            success: function (data) {
	            	if(data.length==0){ location.reload();}
	            	else{
	            	t();
	                if(data.length>0)
	                {
	                	document.getElementById("name").value = data[0].staff_name;
	                	document.getElementById("depart").value = data[0].dp_name;
	                	document.getElementById("post").value = data[0].ps_name;
	                	document.getElementById("study").value = data[0].study;
	                	document.getElementById("class").value = data[0].staff_name;
	                }}
	            }
	        })
	 }
	 function t()
	 {
	        $.ajax({
	            url:"servlet",
	            data:{
	                "action":"find_staff_basicpay",
	                "id":document.getElementById("select").value
	            },
	            type: "POST",
	            dataType:"json",
	            success: function (data) {
	                if(data)
	                {
	                	
	                	document.getElementById("salary_b").value = data;
	                }
	                else
	                	document.getElementById("salary_b").value = "";
	                sclass();
	            }
	        })
	 }
	 function sclass()
	 {
	        $.ajax({
	            url:"servlet",
	            data:{
	                "action":"sclass",
	                "salary":document.getElementById("salary_b").value
	            },
	            type: "POST",
	            dataType:"html",
	            success: function (data) {
	                if(data)
	                {
	                	document.getElementById("sclass").value = data;
	                }
	            }
	        })
	 }
	 function submit()
	 {
	        $.ajax({
	            url:"servlet",
	            data:{
	                "action":"insert_staff_salary",
	                "id":document.getElementById("select").value,
	                "post":document.getElementById("post").value,
	                "salary":document.getElementById("salary_b").value,
	                "admin_id":"4"
	            },
	            type: "POST",
	            dataType:"html",
	            success: function (data) {
	                if(data)
	                {
	                	alert(data);
	                	 location.reload();
	                }
	            }
	        })
	 }
	 
	 
	</script>
</head>
<body onload="load()">

<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>录入职级信息</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是录入员工职级，分配基本工资</p>
    </div>
    <div class="find-top1">
      <table class="top-table">
          <tr><td class="top-table-label">职员编号：</td><td><select id="select" onChange="change()" readonly unselectable="on"></select></td><td class="top-table-label">职员姓名：</td><td><input id="name" type="text" readonly unselectable="on"></td></tr>
          <tr><td class="top-table-label">部门：</td><td><input id="depart" type="text" readonly unselectable="on"></td><td class="top-table-label">岗位：</td><td><input id="post" onChange="t()" type="text" readonly unselectable="on"></td></tr>
          <tr><td class="top-table-label">学历：</td><td><input id="study" type="text" readonly unselectable="on"></td><td class="top-table-label">员工类型：</td><td><input id="class" type="text" readonly unselectable="on"></td></tr>
          <tr><td class="top-table-label">薪级：</td><td><input id="sclass" type="text" readonly unselectable="on"></td><td class="top-table-label">基本薪资：</td><td><input id="salary_b" onChange="sclass()" type="text" ></td></tr>
          <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="submit()"><i class="glyphicon glyphicon-edit"></i> 提交</button></td></tr>
      </table>
    </div>
    <!--<div class="table-con">
        <table id="table1" class="table-style">
        <tr>
        <th>员工编号</th>
        <th>员工姓名</th>
        <th>所属部门</th>
        <th>岗位</th>
        <th>薪级</th>
        <th>基本薪资</th>
        </tr>
        <tr>
         <td>1</td>
         <td>1</td>
         <td>1</td>
         <td>1</td>
         <td>1</td>
         <td>1</td>
        </tr>
        </table>
    </div>-->
<form id="file_form" action="servlet?action=excel"
          enctype="multipart/form-data" method="post" style="float:right">
            <input type="file" name="file" id="file_input">
            <input type="submit" class="add-but" style="color:white" value="导入员工职级薪资信息"/>
        </div>
    </form>
</main>
<br><br><br>
<div>
<table id="table1"></table>
</div>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
$(document).ready(function (){
	$('#table1').bootstrapTable('destroy');
    $('#table1').bootstrapTable({
        method: "post",
        striped: false,
        cache: false,
        singleSelect: false,
        url: "servlet",
        dataType: "json",
        pagination: true, 
        pageSize: 4,
        pageNumber: 1,
        search: true, 
        contentType: "application/x-www-form-urlencoded",
        queryParams: function queryParams(params){
            var temp = {
					action:"find_staff_salary_table"
			};     
            return temp;
        },
        columns:[
        	 {
                 title: "员工id",
                 field: 'staff_id',
                 align: 'center'
             },
             {
                 title: '员工姓名',
                 field: 'staff_name',
                 align: 'center'
             },
             {
                 title: '所属部门',
                 field: 'staff_department',
                 align: 'center'
             },
             {
                 title: '员工岗位',
                 field: 'staff_post',
                 align: 'center'
             },
             {
                 title: '基本工资',
                 field: 'staff_jobsalary_basicpay',
                 align: 'center'
             },{
                 title: '操作员编号',
                 field: 'admin_id',
                 align: 'center'
             },
             {
                 title: '操作员姓名',
                 field: 'admin_name',
                 align: 'center'
             },
             {
            	 title: '录入时间',
                 field: 'staff_jobsalary_changetime',
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
                 align: 'center'
             }
        ]
    });
})
</script>
</body>
</html>