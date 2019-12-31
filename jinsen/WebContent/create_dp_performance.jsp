<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生成部门绩效统计表</title>
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
	}
	</script>
	<script type="text/javascript">
	function submit()
	{
		$.ajax({
            url:"servlet",//要发送的地址
            data:{
                "action":"find_dp_performance_isall",
                "year":document.getElementById("select_year").value
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
                if(data.length > 0)
                {
                	var year = document.getElementById("select_year").value;
                	var str = "";
                	for(var i = 0;i < data.length; i ++)
                	{
                		if(i == 0)
                			str = str + data[i].dp_name;
                		else
                			str = str + "," + data[i].dp_name;
                	}
                	alert(year+"年，以下部门还未录入绩效信息：\n"+str+"\n请先录入！");
                }
                else
                {
                	create_depart_table();
                }
            }
        })
	}
	
	function create_depart_table()
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
						action:"find_depart_performance",
		                company_perfor:document.getElementById("select_company_performance").value,
		                year:document.getElementById("select_year").value
				};     
                return temp;
            },
            columns: [
                {
                    title: '部门',
                    field: 'dp_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门绩效系数',
                    field: 'depart_performance',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门考核分数',
                    field: 'depart_kpi',
                    formatter: function (value,row,index) {
                    	return value * 10;
                    },
                    align: 'center',
                    valign: 'middle'
                },
                   {
                    	title: '部门考核分值',
                        field: 'depart_kpi',
                        formatter: function (value,row,index) {
                        	return value * 10 / 100;
                        },
                        align: 'center',
                        valign: 'middle'
                   },{
                   	title: '部门绩效分值',
                    field: 'depart_perfor_score',
                    align: 'center',
                    valign: 'middle'
                 },{
               	title: '部门绩效总额',
                field: 'depart_perfor_total',
                align: 'center',
                valign: 'middle'
              },{
             	title: '已发绩效',
                field: 'depart_perfor_yetpay',
                align: 'center',
               valign: 'middle'
          },{
         	title: '年终剩余绩效',
            field: 'depart_perfor_residue',
           align: 'center',
           valign: 'middle'
          }
            ]
        });
	}
	</script>
</head>
<body onload="load()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>生成部门绩效统计表</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是生成部门绩效统计表，可以查询和导出部门绩效统计表</p>
    </div>
    <div class="find-top1">
      <table class="top-table">
      	  <tr><td class="top-table-label">年份：</td><td><select id="select_year" onChange="change_year()" readonly unselectable="on"></select></tr>
      	   <tr><td class="top-table-label">公司绩效总额：</td><td><input type="text" id="select_company_performance"/></td></tr>
          <tr><td colspan="6" style="text-align: center"><button class="add-but" onclick="submit()"><i class="glyphicon glyphicon-edit"></i>生成部门绩效统计表</button></td></tr>
      </table>
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
</main>

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