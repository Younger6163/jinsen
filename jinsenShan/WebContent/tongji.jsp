<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>生成报表</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
        <style>
#h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
    </style>  
    <script type="text/javascript">
    function submit_year()
    {
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#sa_year").show().append(str);
    	    $("#sa_year1").show().append(str);
    	    $("#sa_year2").show().append(str);
    	    $("#sa_year3").show().append(str);
    	    $("#sa_year4").show().append(str);
    	    
    	}
    }
    </script>
    <script type="text/javascript">
    function month()
    {
    	$('#myModal_month_year').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "baobiao",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "金森公司结算签收单",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "金森公司结算签收单",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_month_year",
						year:document.getElementById("sa_year").value,
						month:document.getElementById("sa_month").value
				};     
                return temp;
            },
            columns: [
                {
                    title: "年",
                    field: '',
                    formatter:function(value,row,index){
                 	   return document.getElementById("sa_year").value;
                    },
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '月份',
                    field: '',
                    formatter:function(value,row,index){
                  	   return document.getElementById("sa_month").value;
                     },
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '伐区标品号',
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'checkNum',
                    align: 'center',
                    valign: 'middle'
                },
                 {
                    title: '委托人',
                    field: 'entrust',
                    align: 'center',
                    valign: 'middle'
                 },
                  {
                   	title: '树的类型',
                    field: 'treetype',
                    align: 'center',
                    valign: 'middle'
                  },
                  {
                  	title: '材积',
                    field: 'volume',
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '单价',
                    field: 'unitprice',
                    align: 'center',
                    valign: 'middle'
                    
               },{
                 	title: '金额',
                    field: 'totalnum',
                    align: 'center',
                    valign: 'middle'
                    
               }
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function year()
    {
    	$('#myModal_statement').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "baobiao",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "金森木材销售结算登记表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "金森木材销售结算登记表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_statement",
						year:document.getElementById("sa_year1").value
				};     
                return temp;
            },
            columns: [
                {
                    title: "采伐证号",
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '标品号',
                    field: 'checkNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '委托人',
                    field: 'entrust',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '中标人',
                    field: 'getPerson',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '工资',
                    field: 'salary',
                    align: 'center',
                    valign: 'middle'
                 },
             	{
                     title: "年",
                     field: '',
                     formatter:function(value,row,index){
                  	   return document.getElementById("sa_year1").value;
                     },
                     align: 'center',
                     valign: 'middle'
                 },
                  {
                   	title: '月',
                    field: 'smonth',
                    align: 'center',
                    valign: 'middle'
                  },
                  {
                  	title: '日',
                    field: 'sday',
                    align: 'center',
                    valign: 'middle'
                  },
                  {
                     title: '收货单位',
                      field: 'takein',
                      align: 'center',
                      valign: 'middle'
                  },
                    {
                      title: '结算单',
                      field: 'statement',
                      align: 'center',
                      valign: 'middle'
                    },
                  {
                     	title: '树的类型',
                      field: 'treetype',
                      align: 'center',
                      valign: 'middle'
                    },
                    {
                      	title: '单价',
                        field: 'unitprice',
                        align: 'center',
                        valign: 'middle'
                        
                   }
            ]
        });
    }
    </script>
    
<script type="text/javascript">
    function treePrice()
    {
    	$('#myModal_salary_tree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "baobiao",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "金森木材销售统计表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "金森木材销售统计表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_tree",
						year:document.getElementById("sa_year2").value,
						<!--month:document.getElementById("sa_month1").value-->
				};     
                return temp;
            },
            columns: [
            	{
                    title: "年",
                    field: '',
                    formatter:function(value,row,index){
                 	   return document.getElementById("sa_year2").value;
                    },
                    align: 'center',
                    valign: 'middle'
                },
            	{
                    title: "月份",
                    field: 'smonth',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树的类型',
                    field: 'treetype',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '单价',
                    field: 'unitprice',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '总金额',
                    field: 'totalnum',
                    align: 'center',
                    valign: 'middle'
                }
                
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function produce()
    {
    	$('#myModal_produce').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "baobiao",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "金森林业木材生产工资结算登记表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "金森林业木材生产工资结算登记表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_produce",
						year:document.getElementById("sa_year3").value
				};     
                return temp;
            },
            columns: [
                {
                    title: "采伐证号",
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '标品号',
                    field: 'checkNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '工资总额',
                    field: 'allsalary',
                    align: 'center',
                    valign: 'middle'
                },
             	{
                     title: "年",
                     field: '',
                     formatter:function(value,row,index){
                  	   return document.getElementById("sa_year3").value;
                     },
                     align: 'center',
                     valign: 'middle'
                 },
                  {
                   	title: '月',
                    field: 'smonth',
                    align: 'center',
                    valign: 'middle'
                  },
                  {
                  	title: '日',
                    field: 'sday',
                    align: 'center',
                    valign: 'middle'
                  },
                  {
                     title: '劳务承包人',
                      field: 'forperson',
                      align: 'center',
                      valign: 'middle'
                  },
                    {
                      title: '结算单',
                      field: 'statement',
                      align: 'center',
                      valign: 'middle'
                    },
                  {
                     	title: '树的类型',
                      field: 'treetype',
                      align: 'center',
                      valign: 'middle'
                    },
                    {
                      	title: '单价',
                        field: 'unitprice',
                        align: 'center',
                        valign: 'middle'
                        
                   }
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function monthsell ()
    {
    	$('#myModal_monthtree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "baobiao",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "金森公司月销售情况表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "金森公司月销售情况表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_monthtree",
						year:document.getElementById("sa_year4").value,
						month:document.getElementById("sa_month4").value
				};     
                return temp;
            },
            columns: [
            	{
                    title: "采伐证号",
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点（村）',
                    field: 'shipplace',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计出材量',
                    field: 'designV',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计面积',
                    field: 'designP',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "年",
                    field: '',
                    formatter:function(value,row,index){
                 	   return document.getElementById("sa_year4").value;
                    },
                    align: 'center',
                    valign: 'middle'
                },
                 {
                  	title: '月',
                   field: '',
                   formatter:function(value,row,index){
                 	   return document.getElementById("sa_month4").value;
                    },
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                 	title: '日',
                   field: 'sday',
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                  	title: '树的类型',
                   field: 'treetype',
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                   	title: '单价',
                     field: 'unitprice',
                     align: 'center',
                     valign: 'middle'
                     
                }
            ]
        });
    }
    </script>
    
</head>
<!--  <body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>发放工资</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是导出报表，导出单位薪酬发放月报表和年度报表</p>
    </div>-->
    
<body style="background-color: #ecf0f5;font-family: 微软雅黑;color: #475059;min-width: 1000px;overflow: auto">
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
    <div id="h">
    	<ul style="list-style-type:none; display:table; margin:0 auto">
    		<li ><a href="index.html" target="fraName">码单录入</a> </li>
                <li><a href="writeshenhe.html" target="fraName">码单审核</a> </li>
                <li><a href="laowu.html" target="fraName">劳务结算单</a> </li>
                <li><a href="xiaoshou.html" target="fraName">销售结算单</a> </li>
                <li   class="active-li"><a href="tongji.html" target="fraName">统计报表</a> </li>
    	</ul>
    </div>
</aside>
    <article class="artlce">
        <header class="aside-head">
            <ul class="nav-ul">
                <li><a href="index.html" target="fraName">码单录入</a> </li>
                <li><a href="writeshenhe.html" target="fraName">码单审核</a> </li>
                <li><a href="laowu.html" target="fraName">劳务结算单</a> </li>
                <li><a href="xiaoshou.html" target="fraName">销售结算单</a> </li>
                <li class="active-li"><a href="tongji.html" target="fraName">统计报表</a> </li>
            </ul>
        </header>
  <div class="find-top1">
      <table style="border:0px">
		   <tr><td colspan="6" style="text-align: center">
		   <button class="add-but" data-toggle="modal" data-target="#myModal_month_year" onclick="submit_year()">金森公司结算签收单</button>
		   <button class="add-but" onclick="submit_year()" data-toggle="modal" data-target="#myModal_statement">金森木材销售结算登记表</button>
		   <button class="add-but" data-toggle="modal" data-target="#myModal_salary_tree" onclick="submit_year()">金森木材销售统计表</button> 
		   <button class="add-but" data-toggle="modal" data-target="#myModal_produce" onclick="submit_year()">金森林业木材生产工资结算登记表</button>
		   <button class="add-but" onclick="submit_year()" data-toggle="modal" data-target="#myModal_monthtree">金森公司月销售情况表</button>
		   </td></tr>
      </table>
    </div>
    <div class="modal fade" id="myModal_month_year" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								生成报表
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="sa_year" class="col-sm-2 control-label">年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_year" name="sa_year"></select>
									</div>
								</div>
								<div class="form-group">
									<label for="sa_month" class="col-sm-2 control-label">月份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_month" name="sa_month"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-default" onclick="month()">确认</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			    <div class="modal fade" id="myModal_statement" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								生成报表
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="sa_year1" class="col-sm-2 control-label">年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_year1" name="sa_year1"></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-default" onclick="year()">确认</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			 <div class="modal fade" id="myModal_salary_tree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								生成报表
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="sa_year2" class="col-sm-2 control-label">年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_year2" name="sa_year2"></select>
									</div>
								</div>
								<!--<div class="form-group">
									<label for="sa_month1" class="col-sm-2 control-label">月份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_month1" name="sa_month1"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
									</div>
								</div>-->
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-default" onclick="treePrice()">确认</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="myModal_produce" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								生成报表
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="sa_year3" class="col-sm-2 control-label">年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_year3" name="sa_year3"></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-default" onclick="produce()">确认</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			    <div class="modal fade" id="myModal_monthtree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								生成报表
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="sa_year3" class="col-sm-2 control-label">年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_year4" name="sa_year4"></select>
									</div>
								</div>
								<div class="form-group">
									<label for="sa_month4" class="col-sm-2 control-label">月份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_month4" name="sa_month4"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-default" onclick="monthsell()">确认</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
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