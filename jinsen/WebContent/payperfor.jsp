<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>绩效发放</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <script type="text/javascript">
	function load()
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
            queryParams:function queryParams(params){
                var temp = {
						action:"find_staff_performance_list_all"
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
	<script type="text/javascript">
    function ff_perfor()
    {
    	$("#year_perfor").empty();
    	$("#department_select").empty();
    	var y ='<option>所有部门</option>';
    	$("#department_select").show().append(y);
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#year_perfor").show().append(str);
    	}
    	
    	$.ajax({
            url:"servlet",
            data:{
                "action":"find_department"
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
                for(var i = 0;i<data.length;i ++)
                {
                	var str = '<option>'+data[i].dp_name+'</option>';
                	
                	$("#department_select").show().append(str);
                }
            }
        });
        
        $.ajax({
            url:"servlet",
            data:{
                "action":"find_ready_perfor",
                "year":year
            },
            type: "POST",
            dataType:"html",
            success: function (data) {
                $("#ready_perfor").val(data);
                $("#ff_perfor").val(data);
            }
        })
    }
    </script>
    <script type="text/javascript">
    function change()
    {
        $.ajax({
            url:"servlet",
            data:{
                "action":"find_ready_perfor_depart",
                "year":document.getElementById("year_perfor").value,
                "department":document.getElementById("department_select").value
            },
            type: "POST",
            dataType:"html",
            success: function (data) {
                $("#ready_perfor").val(data);
                $("#ff_perfor").val(data);
                $("#bl_perfor").val("100%");
            }
        })
    }
    </script>
    <script type="text/javascript">
    function change_bl()
    {
        $.ajax({
            url:"servlet",
            data:{
                "action":"find_ready_perfor_depart_year_bl",
                "bl_perfor":document.getElementById("bl_perfor").value,
                "year":document.getElementById("year_perfor").value,
                "department":document.getElementById("department_select").value
            },
            type: "POST",
            dataType:"html",
            success: function (data) {
                $("#ff_perfor").val(data);
            }
        })
    }
    </script>
</head>
<body onload="load()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>发放绩效</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是发放绩效，如何发放绩效，发放多少等。</p>
    </div>
    <div class="find-top">
        <!--  <span class="find-span">姓名检索：<input type="text" id="name" class="input-find"><button onclick="hah()" class="add-but"><i class="glyphicon glyphicon-search"></i> 查找</button></span>-->
    </div>
    <button class="add-but" style="width:100px;height:30px;color:white" data-toggle="modal" data-target="#myModal_salary" onclick="ff_perfor()" >发放绩效</button>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    
        <div class="modal fade" id="myModal_salary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								发放绩效
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form" action="servlet?action=ff_perfor_1" method="post">
								<div class="form-group">
									<label for="department_select" class="col-sm-2 control-label">部门</label>
									<div class="col-sm-10">
										<select class="form-control" id="department_select" name="department_select" onChange="change()"><option>所有部门</option></select>
									</div>
								</div>
								<div class="form-group">
									<label for="year_perfor" class="col-sm-2 control-label" >年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="year_perfor" name="year_perfor" onChange="change()"></select>
									</div>
								</div>
								<div class="form-group">
									<label for="ready_perfor" class="col-sm-2 control-label" >待发放绩效</label>
									<div class="col-sm-10">
										<input class="form-control" type="text" id="ready_perfor" name="ready_perfor" readonly="true"/>
									</div>
								</div>
								<div class="form-group">
									<label for="bl_perfor" class="col-sm-2 control-label" >发放绩效比例</label>
									<div class="col-sm-10">
										<select class="form-control" id="bl_perfor" name="bl_perfor" onChange="change_bl()"><option>100%</option><option>90%</option><option>80%</option><option>70%</option><option>60%</option><option>50%</option><option>40%</option><option>30%</option><option>20%</option><option>10%</option></select>
									</div>
								</div>
								<div class="form-group">
									<label for="ff_perfor" class="col-sm-2 control-label" >发放绩效</label>
									<div class="col-sm-10">
										<input class="form-control" type="text" id="ff_perfor" name="ff_perfor" readonly="true"/>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">确认发放</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
<input type="text" style="visibility:hidden"  id="perfor_year"/>
<input type="text" style="visibility:hidden"  id="score_zhi"/>
</main>





<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>