<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    
        <!--jquery-->
    <script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
    <!--bootstrap-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!--fontawesome-->
    <script src="https://cdn.bootcss.com/font-awesome/5.8.1/js/all.min.js"></script>
    <!--bootstrap-table-->
    <link href="https://cdn.bootcss.com/bootstrap-table/1.14.2/bootstrap-table.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-table/1.14.2/bootstrap-table.min.js"></script>
    <!--bootstrap-table-lanuage-->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.14.2/bootstrap-table-locale-all.min.js"></script>
    <!--bootstrap-table-export-->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.14.2/extensions/export/bootstrap-table-export.min.js"></script>
    <!--在客户端保存生成的导出文件-->
    <script src="https://cdn.bootcss.com/FileSaver.js/2014-11-29/FileSaver.min.js"></script>
    <!--以XLSX（Excel 2007+ XML格式）格式导出表（SheetJS）-->
    <script src="https://cdn.bootcss.com/xlsx/0.14.2/xlsx.core.min.js"></script>
    <!--以PNG格式导出表格-->
    <!--对于IE支持包括 html2canvas 之前的 es6-promise-->
    <script src="https://cdn.bootcss.com/es6-promise/4.1.1/es6-promise.auto.min.js"></script>
    <script src="https://cdn.bootcss.com/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
    <!--将表导出为PDF文件-->
    <script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF/jspdf.min.js"></script>
    <script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
    <!--无论期望的格式如何，最后都包含 tableexport.jquery.plugin（不是tableexport）-->
    <script src="https://unpkg.com/tableexport.jquery.plugin/tableExport.min.js"></script>
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
    	    
    	}
    }
    </script>
    <script type="text/javascript">
    function month()
    {
    	$('#myModal_salary_month').modal('hide');
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
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_month",
						year:document.getElementById("sa_year").value,
						month:document.getElementById("sa_month").value
				};     
                return temp;
            },
            columns: [
                {
                    title: "部门",
                    field: 'dp_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '人数',
                    field: 'staff_num',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '总薪资',
                    field: 'salary_all',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '已发薪资',
                    field: 'salary_yetpay',
                    align: 'center',
                    valign: 'middle'
                },
                   {
                    	title: '剩余薪资',
                        field: '',
                        formatter:function(value,row,index){
                     	   return row.salary_all - row.salary_yetpay;
                        },
                        align: 'center',
                        valign: 'middle'
                   },
                   {
                   	title: '年份',
                       field: '',
                       formatter:function(value,row,index){
                    	   return document.getElementById("sa_year").value;
                       },
                       align: 'center',
                       valign: 'middle'
                  },{
                  	title: '月份',
                    field: '',
                    formatter:function(value,row,index){
                 	   return document.getElementById("sa_month").value;
                    },
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '结算时间',
                    field: 'js_time',
                    formatter:function(value,row,index){
                    	 if(value == null)
                    		 return "-";
                    	 else{
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
                          return time;}
                      },
                    align: 'center',
                    valign: 'middle'
                    
               },{
                 	title: '发放时间',
                    field: 'pay_time',
                    formatter:function(value,row,index){
                   	 if(value == null)
                		 return "-";
                	 else{
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
                         return time;}
                     },
                    align: 'center',
                    valign: 'middle'
                    
               },{
                  	title: '薪资状态',
                    field: 'staff_status',
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
    	$('#myModal_salary_year').modal('hide');
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
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_year1",
						year:document.getElementById("sa_year1").value
				};     
                return temp;
            },
            columns: [
                {
                    title: "部门",
                    field: 'dp_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '人数',
                    field: 'staff_num',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '总薪资',
                    field: 'salary_all',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '已发薪资',
                    field: 'salary_yetpay',
                    align: 'center',
                    valign: 'middle'
                },
                   {
                    	title: '剩余薪资',
                        field: '',
                        formatter:function(value,row,index){
                     	   return row.salary_all - row.salary_yetpay;
                        },
                        align: 'center',
                        valign: 'middle'
                   },
                   {
                   	title: '年份',
                       field: '',
                       formatter:function(value,row,index){
                    	   return document.getElementById("sa_year").value;
                       },
                       align: 'center',
                       valign: 'middle'
                  },{
                  	title: '薪资状态',
                    field: 'staff_status',
                    align: 'center',
                    valign: 'middle'
               }
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function day()
    {
    	$('#myModal_salary_day').modal('hide');
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
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_dary",
						year:document.getElementById("sa_year2").value,
						month:document.getElementById("sa_month").value
				};     
                return temp;
            },
            columns: [
            	{
                    title: "部门",
                    field: 'dp_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '人数',
                    field: 'staff_num',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '总薪资',
                    field: 'salary_all',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '已发薪资',
                    field: 'salary_yetpay',
                    align: 'center',
                    valign: 'middle'
                },
                   {
                    	title: '剩余薪资',
                        field: '',
                        formatter:function(value,row,index){
                     	   return row.salary_all - row.salary_yetpay;
                        },
                        align: 'center',
                        valign: 'middle'
                   },
                   {
                   	title: '年份',
                       field: '',
                       formatter:function(value,row,index){
                    	   return document.getElementById("sa_year").value;
                       },
                       align: 'center',
                       valign: 'middle'
                  },{
                  	title: '月份',
                    field: '',
                    formatter:function(value,row,index){
                 	   return document.getElementById("sa_month").value;
                    },
                    align: 'center',
                    valign: 'middle'
               },{
                  	title: '结算时间',
                    field: 'js_time',
                    formatter:function(value,row,index){
                    	 if(value == null)
                    		 return "-";
                    	 else{
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
                          return time;}
                      },
                    align: 'center',
                    valign: 'middle'
                    
               },{
                 	title: '发放时间',
                    field: 'pay_time',
                    formatter:function(value,row,index){
                   	 if(value == null)
                		 return "-";
                	 else{
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
                         return time;}
                     },
                    align: 'center',
                    valign: 'middle'
                    
               },{
                  	title: '薪资状态',
                    field: 'staff_status',
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
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_tree",
						year:document.getElementById("sa_year3").value,
						month:document.getElementById("sa_month1").value
				};     
                return temp;
            },
            columns: [
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
                    title: "月份",
                    field: '',
                    formatter:function(value,row,index){
                 	   return document.getElementById("sa_month1").value;
                    },
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
    
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>发放工资</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是导出报表，导出单位薪酬发放月报表和年度报表</p>
    </div>
  <div class="find-top1">
      <table style="border:0px">
		   <tr><td colspan="6" style="text-align: center"><button class="add-but" data-toggle="modal" data-target="#myModal_salary_month" onclick="submit_year()">生成单位薪酬发放月报表</button><button class="add-but" onclick="submit_year()" data-toggle="modal" data-target="#myModal_salary_year">生成单位薪酬发放年度报表</button><button class="add-but" onclick="submit_year()" data-toggle="modal" data-target="#myModal_salary_day">生成单位薪酬发放报表</button><button class="add-but" data-toggle="modal" data-target="#myModal_salary_tree" onclick="submit_year()">木材销售统计表</button> </td></tr>
      </table>
    </div>
    <div class="modal fade" id="myModal_salary_month" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
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
			    <div class="modal fade" id="myModal_salary_year" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
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
			    <div class="modal fade" id="myModal_salary_day" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
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
								<div class="form-group">
									<label for="sa_month" class="col-sm-2 control-label">月份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_month" name="sa_month"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-default" onclick="day()">确认</button>
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
									<label for="sa_year3" class="col-sm-2 control-label">年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_year3" name="sa_year3"></select>
									</div>
								</div>
								<div class="form-group">
									<label for="sa_month1" class="col-sm-2 control-label">月份</label>
									<div class="col-sm-10">
										<select class="form-control" id="sa_month1" name="sa_month1"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
									</div>
								</div>
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
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
  <script>
  
  </script>
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