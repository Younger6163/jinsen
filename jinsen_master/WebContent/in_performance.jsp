<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>录入员工绩效基本信息</title>
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
	                "action":"find_staff_perfor_all",
	                "year":document.getElementById("select_year").value
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
	</script>
	<script type="text/javascript">
	function change_year()
	{
		 $.ajax({
	            url:"servlet",//要发送的地址
	            data:{
	                "action":"find_staff_perfor_all",
	                "year":document.getElementById("select_year").value
	            },
	            type: "POST",
	            dataType:"json",
	            success: function (data) {
                	document.getElementById("name").value = "";
                	document.getElementById("depart").value = "";
                	document.getElementById("post").value = "";
                	document.getElementById("study_month").value = "";
                	document.getElementById("remark").value = "";
	                var str="";
	                var i =0;
	                $("#select").empty();
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
	</script>
	<script type="text/javascript">
	function change()
	{
		if(document.getElementById("select").value=="")
		{
        	document.getElementById("name").value = "";
        	document.getElementById("depart").value = "";
        	document.getElementById("post").value = "";
        	document.getElementById("study_month").value = "";
        	document.getElementById("remark").value = "";
		}
		else
		{
			 $.ajax({
		            url:"servlet",//要发送的地址
		            data:{
		                "action":"find_staff_all_perfor",
		                "id":document.getElementById("select").value,
		                "year":document.getElementById("select_year").value
		            },
		            type: "POST",
		            dataType:"json",
		            success: function (data) {
	                	document.getElementById("name").value = data[0].staff_name;
	                	document.getElementById("depart").value = data[0].dp_name;
	                	document.getElementById("post").value = data[0].ps_name;
	                	document.getElementById("study_month").value = data[0].work_num;
		            }
		        })
		}
	}
	</script>
	<script type="text/javascript">
	function submit()
	{
		$("#staffid").val(document.getElementById("select").value);
		$("#staffname").val(document.getElementById("name").value);
		$("#year").val(document.getElementById("select_year").value);
		$("#perfor_month").val(document.getElementById("perfor").value);
		$("#work_num").val(document.getElementById("study_month").value);
		var year_perf = document.getElementById("perfor").value * document.getElementById("study_month").value;
		$("#perfor_year").val(year_perf);
		$("#kpi").val(document.getElementById("kpi_perf").value);
		var kpi_s = document.getElementById("kpi").value * 10;
		$("#kpi_grade").val(kpi_s);
		var kpi_s_zhi = kpi_s / 100;
		$("#kpi_grade_zhi").val(kpi_s_zhi);
		var perf_zhi = year_perf * kpi_s_zhi;
		$("#perfor_zhi").val(perf_zhi);
		$("#remark_perf").val(document.getElementById("remark").value);
	}
	</script>
</head>
<body onload="load()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>录入绩效信息</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是录入员工绩效信息</p>
    </div>
    <div class="find-top1">
      <table class="top-table">
      	  <tr><td class="top-table-label">年份：</td><td><select id="select_year" onChange="change_year()" readonly unselectable="on"></select></tr>
          <tr><td class="top-table-label">职员编号：</td><td><select id="select" onChange="change()" readonly unselectable="on"></select></td><td class="top-table-label">职员姓名：</td><td><input id="name" type="text" readonly unselectable="on"></td></tr>
          <tr><td class="top-table-label">部门：</td><td><input id="depart" type="text" readonly unselectable="on"></td><td class="top-table-label">岗位：</td><td><input id="post" onChange="t()" type="text" readonly unselectable="on"></td></tr>
          <tr><td class="top-table-label">工作月份数：</td><td><input id="study_month" type="text" readonly unselectable="on"></td><td class="top-table-label">备注：</td><td><input id="remark" type="text"></td></tr>
          <tr><td class="top-table-label">月绩效系数：</td><td><input id="perfor" type="text" ></td><td class="top-table-label">KPI：</td><td><input id="kpi_perf" type="text" ></td></tr>
          <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="submit()" data-toggle="modal" data-target="#myModal_perfor"><i class="glyphicon glyphicon-edit"></i> 提交</button></td></tr>
      </table>
    </div>
    
    <div class="modal fade" id="myModal_perfor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								员工绩效信息确认
							</h4>
						</div>
						<div class="modal-body">

							<form class="form-horizontal" role="form" action="servlet?action=perfor_submit&admin_id=4" method="POST">
							<div class="form-group">
									<label for="staffid" class="col-sm-2 control-label" >员工编号:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="staffid" name="staffid" readonly="true" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="staffname" class="col-sm-2 control-label" >员工姓名:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="staffname" readonly="true" name="staffname" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="year" class="col-sm-2 control-label" >年份:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="year" readonly="true" name="year" placeholder="" >
									</div>
							</div>
							<div class="form-group">
									<label for="perfor_month" class="col-sm-2 control-label" >月绩效系数:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="perfor_month" readonly="true" name="perfor_month" placeholder="" >
									</div>
							</div>
						   <div class="form-group">
									<label for="work_num" class="col-sm-2 control-label" >工作月份数:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="work_num" name="work_num" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="perfor_year" class="col-sm-2 control-label" >年绩效系数:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="perfor_year" name="perfor_year" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="kpi" class="col-sm-2 control-label" >KPI:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="kpi" name="kpi" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="kpi_grade" class="col-sm-2 control-label" >考评分:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="kpi_grade" name="kpi_grade" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="kpi_grade_zhi" class="col-sm-2 control-label" >考评分值:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="kpi_grade_zhi" name="kpi_grade_zhi" placeholder="" readonly="true" >
									</div>
							</div>
						    <div class="form-group">
									<label for="perfor_zhi" class="col-sm-2 control-label" >绩效分值:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="perfor_zhi" name="perfor_zhi" placeholder="" readonly="true" >
									</div>
							</div>
							<div class="form-group">
									<label for="remark_perf" class="col-sm-2 control-label" >备注:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" style="width:300px" id="remark_perf" name="remark_perf" placeholder="" readonly="true" >
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

<form id="file_form" action="servlet?action=excel_perfor"
          enctype="multipart/form-data" method="post" style="float:right">
            <input type="file" name="file" id="file_input">
            <input type="submit" class="add-but" style="color:white" value="导入员工绩效信息"/>
        </div>
</form>
</main>


<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>