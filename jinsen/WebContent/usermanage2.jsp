<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>管理员员信息管理</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/moment.js"></script>
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>人员信息管理界面</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是人员信息管理的详情的页面！主要实现人员信息的增，删，改，查等操作</p>
    </div>
    <div class="find-top1">
        <table class="top-table" >
            <tr><td class="top-table-label">管理员员工号：</td><td><input type="text" id="staff_id"></td><td class="top-table-label">管理员姓名：</td><td><input type="text"id="staff_name"></td><td class="top-table-label">管理员密码：</td><td><input type="text" id="password"></td></tr>
            <tr><td class="top-table-label">管理员权限：</td><td><select id="power_type"><option>管理员</option><option>超级管理员</option></select></td><td class="top-table-label">备份记录：</td><td><input type="text" id="backups"></tr>
            <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="add_manager()"><i class="glyphicon glyphicon-edit"></i> 提交</button> <button class="add-del"><i class="glyphicon glyphicon-remove"></i> 取消</button></td></tr>
        </table>
    </div>
    <!--<div class="table-con">-->
        <!--<button class="btn btn-xs btn-primary">导入文件</button>-->
        <!--<button class="btn btn-xs btn-primary" onclick="location.href='qingjiastand.jsp'">调整加班类型加费标准</button>-->
    <!--</div>-->
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div> 
</main>
<script src="js/jquery.js"></script>
<script src="js/jquery.singlePageNav.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/province.js"></script>
<script src="js/wow.min.js"></script>
<script>
    $(function(){
        table1();

    })
    function table1(){
        $('#table1').bootstrapTable({
            method: "get",
            striped: false,
            singleSelect: true,
            url: "managerServlet",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: function queryParams(params)
        {
            	var temp={
            			action:"all_manager"
            	};
            	return temp;
        }
        ,
            columns: [
                {
                    title: "管理员员工号",
                    field: 'staff_id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '管理员姓名',
                    field: 'staff_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '创建时间',
                    field: 'createtime',
                    align: 'center',
                    valign: 'middle',
                    formatter : function(value,row,index)
                    {
                    	return changeDateFormat(value)
                    }
                },
                {
                    title: '锁定',
                    field: 'is_lock',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '上一次登录时间',
                    field: 'logintime',
                    align: 'center',
                    valign: 'middle',
                    formatter : function(value,row,index)
                    {
                    	return changeDateFormat(value)
                    }
                },
                {
                    title: '退出时间',
                    field: 'exittime',
                    align: 'center',
                    valign: 'middle',
                    formatter : function(value,row,index)
                    {
                    	return changeDateFormat(value)
                    }
                },
                {
                    title: '操作',
                    field: 'staff_id',
                    width: 180,
                    align: 'center',
                    formatter: function (value, row) {
                        var  d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + staff_id + '\')">编辑</button> ';
                        var  c = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="delete_staff(\'' + staff_id + '\')">删除</button> ';
                        var  a ='<div role="presentation" class="dropdown"><button class="btn btn-xs btn-primary dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">操作<span class="caret"></span></button><ul class="dropdown-menu"><li><a href="managerServlet?action=delete_manager&staff_id=' + value + '">删除</a></li><li><a href="managerServlet?action=lock_manager&staff_id=' + value + '">锁定</a></li><li><a href="managerServlet?action=unlock_manager&staff_id=' + value + '">解锁</a></li><li><a href="managerServlet?action=recode&staff_id=' + value + '">重置密码</a></li></ul></div>';
                        return  a;
                    }
                }
            ]
        });
    }
    //转换日期格式(时间戳转换为datetime格式)
    function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
    function add_manager()
    {
    	var staff_id=document.getElementById('staff_id').value;
    	var staff_name=document.getElementById('staff_name').value;
    	var password=document.getElementById('password').value;
    	//alert(staff_born);
    	var backups=document.getElementById('backups').value;
    	var power_type=document.getElementById('power_type').value;
    	alert("adasdadf");
    	$.ajax({
    		url     :"managerServlet",
			type    :	"POST",
			data    :{"action":"add_manager",
						 "staff_id":staff_id,
						 "staff_name":staff_name,
						 "password":password,
						 "backups":backups,
						 "power_type":power_type,
						},
			"dataType" :"text",
			"success"  :function(data){
		
				if(data=="success")
					{
					alert("添加成功！");
					window.location.href='usermanage2.jsp';
					}
				else if(data="fail")
					{
					alert("添加失败")
					window.location.href='usermanage2.jsp';
					}
				else if(data="null")
				{
				alert("数据不能为空")
				window.location.href='usermanage2.jsp';
				}
			}
    	})
    }

</script>
<!-- Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改管理员信息</h4>
            </div>
            <div class="modal-body">
                <form action="#">
                    <div class="form-group">
                        <label for="staff_id">管理员员工号</label>
                        <input type="text" id="staff_id" class="form-control" placeholder="管理员员工号">
                    </div>
                    <div class="form-group">
                        <label for="addpassword">管理员密码</label>
                        <input type="text" id="addpassword" class="form-control" placeholder="请输入新密码">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="yesadd" onclick="add_user()">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>