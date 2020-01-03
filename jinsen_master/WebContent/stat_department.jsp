<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>录入部门绩效信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript">
	 function load(){ 
		 
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
	                "action":"find_depart_perfor_all",
	                "year":document.getElementById("select_year").value
	            },
	            type: "POST",
	            dataType:"json",
	            success: function (data) {
	                var str="";
	                var i =0;
	                str = "<option></option>";
	                $("#department_name").show().append(str);
	                for(i = 0;i<data.length;i++)
	                {
	                	str = "<option>"+data[i].dp_name+"</option>";
	                	
	                	$("#department_name").show().append(str);
	                }
	            }
	        })
	} 
	</script>
	<script type="text/javascript">
	function change_year()
	{
		$.ajax({
            url:"servlet",//要发送的地址
            data:{
                "action":"find_depart_perfor_all",
                "year":document.getElementById("select_year").value
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
            	$("#department_name").empty();
            	document.getElementById("select_department_id").value ="";
            	document.getElementById("department_perfor").value = "";
            	document.getElementById("kpi_perf").value = "";
            	document.getElementById("remark").value = "";
                var str="";
                var i =0;
                str = "<option></option>";
                $("#department_name").show().append(str);
                for(i = 0;i<data.length;i++)
                {
                	str = "<option>"+data[i].dp_name+"</option>";
                	
                	$("#department_name").show().append(str);
                }
            }
        })
	}
	</script>
	<script type="text/javascript">
	function change_department()
	{
		$.ajax({
            url:"servlet",//要发送的地址
            data:{
                "action":"find_depart_message",
                "name":document.getElementById("department_name").value,
                "year":document.getElementById("select_year").value
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
            	document.getElementById("kpi_perf").value = "";
            	document.getElementById("remark").value = "";
            	document.getElementById("select_department_id").value = data.dp_id;
            	document.getElementById("department_perfor").value = data.dp_perfor;
            }
        })
	}
	</script>
	<script type="text/javascript">
	function submit()
	{
		$("#dp_id").val(document.getElementById("select_department_id").value);
		$("#dp_name").val(document.getElementById("department_name").value);
		$("#year").val(document.getElementById("select_year").value);
		$("#dp_perfor").val(document.getElementById("department_perfor").value);
		$("#dp_kpi").val(document.getElementById("kpi_perf").value);
		var kpi_score = document.getElementById("kpi_perf").value * 10;
		$("#dp_kpi_grade").val(kpi_score);
		var kpi_score_zhi = kpi_score / 100;
		$("#dp_kpi_grade_zhi").val(kpi_score_zhi);
		var kpi_perfor_zhi = kpi_score_zhi * document.getElementById("department_perfor").value;
		$("#dp_perfor_zhi").val(kpi_perfor_zhi);
		$("#dp_remark").val(document.getElementById("remark").value);
	}
	</script>
</head>
<body onload="load()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>录入绩效信息</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是录入部门绩效信息</p>
    </div>
    <div class="find-top1">
      <table class="top-table">
      	  <tr><td class="top-table-label">年份：</td><td><select id="select_year" onChange="change_year()" readonly unselectable="on"></select></tr>
      	     <tr><td class="top-table-label">部门：</td><td><select id="department_name"  readonly unselectable="on" onChange="change_department()" ></select></td></tr>
      	   <tr><td class="top-table-label">部门编号：</td><td><input type="text" id="select_department_id" readonly unselectable="on"/></td></tr>
          <tr><td class="top-table-label">部门绩效系数：</td><td><input id="department_perfor" type="text" readonly unselectable="on"></td></tr>
          <tr><td class="top-table-label">KPI：</td><td><input id="kpi_perf" type="text" ></td></tr>
          <tr><td class="top-table-label">备注：</td><td><input id="remark" type="text" ></td></tr>
          <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="submit()" data-toggle="modal" data-target="#myModal_perfor_department"><i class="glyphicon glyphicon-edit"></i> 提交</button></td></tr>
      </table>
    </div>
    
    <div class="modal fade" id="myModal_perfor_department" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								部门绩效信息确认
							</h4>
						</div>
						<div class="modal-body">

							<form class="form-horizontal" role="form" action="servlet?action=dp_perfor_submit&admin_id=admin_1" method="POST">
							<div class="form-group">
									<label for="dp_id" class="col-sm-2 control-label" >部门编号:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_id" name="dp_id" readonly="true" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="dp_name" class="col-sm-2 control-label" >部门名称:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_name" readonly="true" name="dp_name" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="year" class="col-sm-2 control-label" >年份:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="year" readonly="true" name="year" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="dp_perfor" class="col-sm-2 control-label" >部门绩效系数:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_perfor" readonly="true" name="dp_perfor" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="dp_kpi" class="col-sm-2 control-label" >KPI:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_kpi" name="dp_kpi" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="dp_kpi_grade" class="col-sm-2 control-label" >考评分:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_kpi_grade" name="dp_kpi_grade" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="dp_kpi_grade_zhi" class="col-sm-2 control-label" >考评分值:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_kpi_grade_zhi" name="dp_kpi_grade_zhi" placeholder="" readonly="true" >
									</div>
							</div>
						    <div class="form-group">
									<label for="dp_perfor_zhi" class="col-sm-2 control-label" >部门绩效分值:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_perfor_zhi" name="dp_perfor_zhi" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="dp_remark" class="col-sm-2 control-label" >备注:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="dp_remark" name="dp_remark" placeholder="" readonly="true" >
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
</main>



<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>