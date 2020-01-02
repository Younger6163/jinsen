<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
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
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>角色信息管理界面</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是角色信息管理的详情的页面！主要实现人员信息的增，删，改，查等操作</p>
    </div>
    <div class="find-top1">
        <table class="top-table" >
            <tr><td class="top-table-label">角色编码：</td><td><input type="text" id="role_num"></td><td class="top-table-label">角色名称：</td><td><input type="text"id="role_name"></td><td class="top-table-label">备注：</td><td><input type="text" id="remark"></td></tr>
            <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="add_role()"><i class="glyphicon glyphicon-edit"></i> 添加</button> <button class="add-del"><i class="glyphicon glyphicon-remove"></i> 取消</button></td></tr>
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
            showRefresh: true,
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: function queryParams(params)
            {
            	var temp={
            			action:"all_role"
            	};
            	return temp;
            },
            columns: [
                {
                    title: "角色编码",
                    field: 'role_num',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '角色名称',
                    field: 'role_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '备注',
                    field: 'remark',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'role_num',
                 //   width: 180,
                  //  align: 'center',
                    formatter: operateFormatter
                }
            ]
        });
    }
    function operateFormatter(value,row,index) {
    	
        return [
             '<button type="button" class="btn btn-xs btn-primary" onclick="delete_role('+value+')">删除</button> <button type="button" class="btn btn-xs btn-primary"  onclick="updatetable('+value+')" style="margin-right:5px;" data-toggle="modal" data-target="#updateroleModal">修改</button>'
             ].join('');
       };
    function add_role()
    {
    	alert("6666666666666666666666666666");
    	var role_num=document.getElementById('role_num').value;
    	var role_name=document.getElementById('role_name').value;
    	var remark=document.getElementById('remark').value;
    	//var role_num=#role_num
    	$.ajax({
    		url     :   "managerServlet",
			type    :	"POST",
			data    :{   "action":"add_role",
						 "role_num":role_num,
						 "role_name":role_name,
						 "remark":remark,
						},
			dataType :"text",
			success  :function(data){
				alert(data);
				if(data=="success")
					{
					alert("添加成功！");
					window.location.href='rolemanage.jsp';
					}
				else if(data="fail")
					{
					alert("添加失败")
					window.location.href='rolemanage.jsp';
					}
			},
			 error:function(e){
 	        	alert("error");
 	        }
    	})
    }
    function delete_role(role)
    {
    	alert(role);
    	alert("in updatetable");
    	$.ajax({
    		url     :   "managerServlet",
			type    :	"POST",
			data    :{   "action":"delete_role",
						 "role_num":role,
						},
			dataType :"html",
			success  :function(data){
				alert(data);
				if(data=="success")
					{
					alert("删除成功！");
					window.location.href='rolemanage.jsp';
					}
				else if(data="fail")
					{
					alert("删除失败")
					window.location.href='rolemanage.jsp';
					}
			},
			 error:function(e){
 	        	alert("error");
 	        }
    	});
    }
    function updatetable(role_num){
    	alert(role_num);
    	$.ajax({
    		url:"managerServlet",
    		type:"post",
    		data:{
    			"role_num":role_num,
    			"action":"get_role" , 
    			 },
    		dataType:"json",
    		success:function(data){
    			var htmlstr="";
 				alert(data.role_num);
				var str ="<form action='#'><div class='form-group'><label for='role_num'>角色编码</label><input type='text' id='role_num1' class='form-control'value="+data.role_num+"></div><div class='form-group'><label for='role_name'>角色名称</label><input type='text' id='role_name1' class='form-control' value="+data.role_name+"></div><div class='form-group'><label for='remark'>备注</label><input type='text' id='remark1' class='form-control' value="+data.remark+"></div></form><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button><button type='button' class='btn btn-primary' onclick='update_role("+data.role_num+")'>提交</button></div>";
				htmlstr+=str; 
    			alert(str);
    			$("#body2").html(str); 
    		},
    		error:function(){
    			alert('服务器执行错误！');
    		}
    	});
    }
    function update_role(role_num)
    {
    	alert("in updaterole");
    	var role_num=document.getElementById('role_num1').value;
    	alert(role_num);
    	var role_name=document.getElementById('role_name1').value;
    	alert(role_name);
    	var remark=document.getElementById('remark1').value;
    	alert(remark);
    	$.ajax({
    		url     :   "managerServlet",
			type    :	"POST",
			data    :{   "action":"upate_role",
						 "role_num":role_num,
						 "role_name":role_name,
						 "remark":remark,
						},
			dataType :"html",
			success  :function(data){
				alert(data);
				if(data=="success")
					{
					alert("修改成功！");
					window.location.href='rolemanage.jsp';
					}
				else if(data="fail")
					{
					alert("修改失败")
					window.location.href='rolemanage.jsp';
					}
			},
			 error:function(e){
 	        	alert("error");
 	        }
    	});
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
<!-- Modal -->
<div class="modal fade" id="addroleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加角色</h4>
            </div>
            <div class="modal-body">
                <form action="#">
                    <div class="form-group">
                        <label for="role_num">角色编码</label>
                        <input type="text" id="role_num" class="form-control" placeholder="角色编码">
                    </div>
                    <div class="form-group">
                        <label for="role_name">角色名称</label>
                        <input type="text" id="role_name" class="form-control" placeholder="角色名称">
                    </div>
                    <div class="form-group">
                        <label for="remark">备注</label>
                        <input type="text" id="remark" class="form-control" placeholder="备注">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="yesadd" onclick="add_role()">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="updateroleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
   <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改加班信息</h4>
            </div>
            <div class="modal-body" id="body2">
            </div>
        </div>
    </div>
</div>
</body>
</html>