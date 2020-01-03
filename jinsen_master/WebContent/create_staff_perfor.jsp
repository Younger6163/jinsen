<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生成部门员工绩效确认单</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript">
	function load()
	{
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#select_year").show().append(str);
    	}
    	
    	$.ajax({
            url:"servlet",//要发送的地址
            data:{
                "action":"find_dp_all"
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
                for(i = 0;i<data.length;i++)
                {
                	str = "<option>"+data[i].dp_name+"</option>";
                	
                	$("#select_department").show().append(str);
                }
            }
        })
	}
	</script>
	<script type="text/javascript">
	function submit()
	{
		$('#table1').bootstrapTable('destroy');
        $('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "servlet",
            dataType: "json",
            pagination: true, //分页
            pageSize: 5,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            queryParams:function queryParams(params){
                var temp = {
						action:"find_staff_perfor_qr",
		                dp_name:document.getElementById("select_department").value,
		                year:document.getElementById("select_year").value
				};     
                return temp;
            },
            columns: [
                {
                    title: '员工姓名',
                    field: 'staff_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '岗位',
                    field: 'ps_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '工作月份',
                    field: 'work_month',
                    align: 'center',
                    valign: 'middle'
                },
                   {
                    	title: '备注',
                        field: 'staff_performance_remark',
                        align: 'center',
                        valign: 'middle'
                   },{
                   	title: '月绩效系数',
                    field: 'staff_performance_coefficient',
                    align: 'center',
                    valign: 'middle'
                 },{
               	title: '月份数',
                field: 'work_num',
                align: 'center',
                valign: 'middle'
              },{
             	title: '年绩效系数',
                field: 'staff_performance_coefficient',
                formatter: function (value,row,index) {
                	document.getElementById("perfor_year").value = row.work_num * row.staff_performance_coefficient;
                	return row.work_num * row.staff_performance_coefficient;
                },
                align: 'center',
               valign: 'middle'
          },{
         	title: '考评分',
            field: 'staff_performance_kpi',
            formatter: function (value,row,index) {
            	return value * 10;
            },
           align: 'center',
           valign: 'middle'
          },{
         	title: '考评分值',
            field: 'staff_performance_kpi',
            formatter: function (value,row,index) {
            	document.getElementById("score_zhi").value = row.staff_performance_kpi * 10 / 100;
            	return row.staff_performance_kpi * 10 / 100;
            },
           align: 'center',
           valign: 'middle'
          },{
         	title: '绩效分值',
            field: 'staff_performance_kpi',
            formatter: function (value,row,index) {
            	return document.getElementById("score_zhi").value * document.getElementById("perfor_year").value;
            },
           align: 'center',
           valign: 'middle'
          },{
         	title: '全年绩效',
            field: 'staff_performance_list_total',
           align: 'center',
           valign: 'middle'
          },{
         	title: '已发绩效',
            field: 'staff_performance_list_yetpay',
           align: 'center',
           valign: 'middle'
          },{
         	title: '剩余绩效',
            field: 'staff_performance_list_residue',
           align: 'center',
           valign: 'middle'
          }
            ]
        });
	}
	</script>
</head>
<body onload="load()">
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>生成部门员工绩效确认单</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是生成部门员工绩效确认单，可以查询和导出部门员工绩效确认单</p>
    </div>
    <div class="find-top1">
      <table class="top-table">
      	  <tr><td class="top-table-label">年份：</td><td><select id="select_year" onChange="change_year()" readonly unselectable="on"></select></tr>
      	   <tr><td class="top-table-label">部门：</td><td><select id="select_department"></select></td></tr>
          <tr><td colspan="6" style="text-align: center"><button class="add-but" onclick="submit()"><i class="glyphicon glyphicon-edit"></i>查询员工绩效确认单</button></td></tr>
      </table>
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
<input type="text" style="visibility:hidden"  id="perfor_year"/>
<input type="text" style="visibility:hidden"  id="score_zhi"/>
<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/bootstrap-table-export.js"></script>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
</body>
</html>