<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>工资调整</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <script type="text/javascript">
    function table_name()
    {
    	alert(document.getElementById("name").value);
    	$('#table1').empty();
    }
    </script>
    <script type="text/javascript">
	function hah(id)
	{
		$.ajax({
            url:"servlet",
            data:{
                "action":"find_staff_salary_name",
                "name":document.getElementById("name").value
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
            	$("#table1").empty();
            	var i =0;
            	var str = "<tr><th>员工编号</th><th>姓名</th><th>所属部门</th><th>岗位</th><th>薪资</th><th>工资调整</th></tr>";
            	$("#table1").show().append(str);
            	for(i = 0;i<data.length;i++)
                {
                	str="<tr><td>"+data[i].staff_id+"</td><td>"+data[i].staff_name+"</td><td>"+data[i].dp_name+"</td><td>"+data[i].staff_post+"</td><td>"+data[i].staff_jobsalary_basicpay+"</td><td><form action='servlet?action=update_salary_basicpay&id="+data[i].staff_id+"' method='POST'>薪资：<input type='text' name='salary'/><input type='submit' value='调整工资'/></form></td></tr>"
                	
                	$("#table1").show().append(str);
                }
            }
        })
	}
	
	function salaryclass_adjust()
	{
		
		var s = document.getElementById("adjust_salary").value;
		if(s > 500)
			document.getElementById("adjust_salary_class").value = "一级";
		else
			document.getElementById("adjust_salary_class").value = "二级";
	}
</script>
<style>
.table4_3{
	width:100%;
	margin:15px 0;
	border:0;
}
.table4_3 th {
	background-color:#87CEFA;
	color:#000000
}
.table4_3,.table4_3 th,.table4_3 td {
	font-size:0.95em;
	text-align:center;
	padding:4px;
	border-collapse:collapse;
}
.table4_3 th,.table4_3 td {
	border: 1px solid #bae3fc;
	border-width:1px 0 1px 0
}
.table4_3 tr {
	border: 1px solid #bae3fc;
}
.table4_3 tr:nth-child(odd){
	background-color:#d7eefd;
}
.table4_3 tr:nth-child(even){
	background-color:#fdfdfd;
}
</style>
</head>
<body onload="table1()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>绩效管理</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是查询员工基本薪级情况，并可进行调整。</p>
    </div>
    <div class="find-top">
        <button class="add-but" data-toggle="modal" data-target="#myModal_adjust_salary" onclick="pl_adjust_salary()"><i class="glyphicon glyphicon-edit"></i> 批量处理</button>
        <!-- <span class="find-span">姓名检索：<input type="text" id="name" class="input-find"><button onclick="hah()" class="add-but"><i class="glyphicon glyphicon-search"></i> 查找</button></span>-->
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>

</main>
<div class="modal fade" id="myModal_adjust_salary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								工资调整
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form" action="servlet?action=js_buy_1" method="POST">
							<input type="text" id="staffidstr" style="visibility:hidden" name="staffidstr"/>
							<table id="table" class="table4_3">
							
							</table>
						   <div class="form-group">
									<label for="adjust_salary" class="col-sm-2 control-label" >调整薪资:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="adjust_salary" name="adjust_salary" placeholder="" onblur="salaryclass_adjust()">
									</div>
							</div>
							<div class="form-group">
									<label for="adjust_salary_class" class="col-sm-2 control-label" >调整后薪资等级:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="adjust_salary_class" name="adjust_salary_class" placeholder="">
									</div>
							</div>
							<div class="form-group" align="right">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">提交</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
							</div>
							</form>

						</div>
					</div>
				</div>
</div>

<div class="modal fade" id="myModal_sure_salary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								工资调整确认
							</h4>
						</div>
						<div class="modal-body">

							<form class="form-horizontal" role="form" action="servlet?action=js_buy_2" method="POST">
							<h4 class="form-title">
								员工信息：
							</h4>
							<div class="form-group">
									<label for="salaryid" class="col-sm-2 control-label" >员工编号:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="salaryid" name="salaryid" readonly="true" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="salaryname" class="col-sm-2 control-label" >员工姓名:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="salaryname" readonly="true" name="salaryname" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="old_salary" class="col-sm-2 control-label" >原先薪资:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="old_salary" readonly="true" name="old_salary" placeholder="" >
									</div>
							</div>
						   <div class="form-group">
									<label for="ad_salary" class="col-sm-2 control-label" >调整薪资:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="ad_salary" name="ad_salary" placeholder="" >
									</div>
							</div>
							<div class="form-group" align="right">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">提交</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
							</div>
							</form>
						</div>
					</div>
				</div>
</div>
<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    //$(function(){
    //    table1();
    //})
    function table1(){
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
            queryParams:function queryParams(params){
                var temp = {
						action:"salary_all"  
				};     
                return temp;
            },
            columns: [
            	{
   				 checkbox:true,
                    align: 'center'
           		},
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
                        field: 'staff_jobsalary_basicpay',
                        align: 'center',
                        valign: 'middle'
                   },{
                	   title:'工资调整',
                	   field:'staff_id',
                        formatter: function (value,row,index) {
                        var  d = '<button data-toggle="modal" data-target="#myModal_sure_salary" onclick="adjust_s(\''+value+'\')">调整工资</button>';
                        //var d = '<input type="text" /><button onclick="t('+value+')">调整工资</button>'
                        //var d ='薪资：<input type="text" id="salary"/><a href="javascript:void(0);" onclick="hah('+value+')">调整薪资</a>'
                        return  d;
                    },
                   align:'center'
                }
            ]
        });
    }
</script>
<script type="text/javascript">
function pl_adjust_salary()
{
	$("#table").empty();
	var checkedbox= $("#table1").bootstrapTable('getSelections'); 
	var  jsonStr=JSON.stringify(checkedbox);
	 
	var jsonObject=jQuery.parseJSON(jsonStr);
	if(jsonObject.length == 0){
		$("#adjust_salary").attr("readonly",true);
		$("#adjust_salary_class").attr("readonly",true);
	}
	else{
		$("#adjust_salary").attr("readonly",false);
		$("#adjust_salary_class").attr("readonly",true);
	var staff_id = new Array();
	var tableh = '<tr><th>员工编号</th><th>员工姓名</th><th>所属部门</th><th>岗位</th><th>原先薪资</th><th>原先薪资等级</th></tr>';
	$("#table").show().append(tableh);
	for(var i = 0;i<jsonObject.length;i++)
	{
		staff_id[i] = jsonObject[i].staff_id;
		
		var str = '<tr><td>'+jsonObject[i].staff_id+'</td><td>'+jsonObject[i].staff_name+'</td>'+
		'<td>'+jsonObject[i].staff_department+'</td><td>'+jsonObject[i].staff_post+'</td>'+
		'<td>'+jsonObject[i].staff_jobsalary_basicpay+'</td><td>一级</td>'+
		'</tr>';
		
		$("#table").show().append(str);
	}
	var ddhstr = "";
	for(var j = 0;j<staff_id.length;j++)
	{
		if(j == 0)
			ddhstr = ddhstr + staff_id[j];
		else
			ddhstr = ddhstr + "," +staff_id[j];
	}
	$("#staffidstr").val(ddhstr);
	}
	}
	
	function adjust_s(value)
	{
		$.ajax({
            url:"servlet",
            data:{
                "action":"find_staff_s",
                "staff_id":value
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
            	$("#old_salary").val(data.staff_jobsalary_basicpay);
            	$("#salaryid").val(data.staff_id);
            	$("#salaryname").val(data.staff_name);
            }
        })
	 }
	
</script>
</body>
</html>