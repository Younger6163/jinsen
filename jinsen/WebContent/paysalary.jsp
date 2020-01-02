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
    <script type="text/javascript">
    function table1()
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
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams:function queryParams(params){
                var temp = {
						action:"salarylist_all_wff"  
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
                 	title: '加班次数',
                    field: 'staff_salary_list_overtime',
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
    function ff_salary()
    {
    	$("#year_salary").empty();
    	$("#department_select").empty();
    	var y ='<option>所有部门</option>';
    	$("#department_select").show().append(y);
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#year_salary").show().append(str);
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
        })
        $.ajax({
            url:"servlet",
            data:{
                "action":"find_ready_salary",
                "year":year
            },
            type: "POST",
            dataType:"html",
            success: function (data) {
                $("#ready_salary").val(data);
                $("#ff_salary").val(data);
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
                "action":"find_ready_salary_three",
                "year":document.getElementById("year_salary").value,
                "department":document.getElementById("department_select").value,
                "month":document.getElementById("month_salary").value
            },
            type: "POST",
            dataType:"html",
            success: function (data) {
                $("#ready_salary").val(data);
                $("#ff_salary").val(data);
                $("#bl_salary").val("100%");
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
                "action":"find_ready_salary_bl",
                "bl_salary":document.getElementById("bl_salary").value,
                "year":document.getElementById("year_salary").value,
                "department":document.getElementById("department_select").value,
                "month":document.getElementById("month_salary").value
            },
            type: "POST",
            dataType:"html",
            success: function (data) {
                $("#ff_salary").val(data);
            }
        })
    }
    </script>
</head>
<body onload="table1()">
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>发放工资</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是发放工资，如何发放工资，发放多少等。</p>
    </div>
    <div class="find-top">
        <!--  <span class="find-span">姓名检索：<input type="text" id="name" class="input-find"><button onclick="hah()" class="add-but"><i class="glyphicon glyphicon-search"></i> 查找</button></span>-->
    </div>
    <button class="add-but" style="width:100px;height:30px;color:white" data-toggle="modal" data-target="#myModal_salary" onclick="ff_salary()" >发放工资</button>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    <div class="modal fade" id="myModal_salary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								发放工资
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form" action="servlet?action=ff_salary_1" method="post">
								<div class="form-group">
									<label for="department_select" class="col-sm-2 control-label">部门</label>
									<div class="col-sm-10">
										<select class="form-control" id="department_select" name="department_select" onChange="change()"><option>所有部门</option></select>
									</div>
								</div>
								<div class="form-group">
									<label for="year_salary" class="col-sm-2 control-label" >年份</label>
									<div class="col-sm-10">
										<select class="form-control" id="year_salary" name="year_salary" onChange="change()"></select>
									</div>
								</div>
								<div class="form-group">
									<label for="month_salary" class="col-sm-2 control-label" >月份</label>
									<div class="col-sm-10">
										<select class="form-control" id="month_salary" name="month_salary" onChange="change()"><option>所有月份</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
									</div>
								</div>
								<div class="form-group">
									<label for="ready_salary" class="col-sm-2 control-label" >待发放薪资</label>
									<div class="col-sm-10">
										<input class="form-control" type="text" id="ready_salary" name="ready_salary" readonly="true"/>
									</div>
								</div>
								<div class="form-group">
									<label for="bl_salary" class="col-sm-2 control-label" >发放薪资比例</label>
									<div class="col-sm-10">
										<select class="form-control" id="bl_salary" name="bl_salary" onChange="change_bl()"><option>100%</option><option>90%</option><option>80%</option><option>70%</option><option>60%</option><option>50%</option><option>40%</option><option>30%</option><option>20%</option><option>10%</option></select>
									</div>
								</div>
								<div class="form-group">
									<label for="ff_salary" class="col-sm-2 control-label" >发放薪资</label>
									<div class="col-sm-10">
										<input class="form-control" type="text" id="ff_salary" name="ff_salary" readonly="true"/>
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
</main>
<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>