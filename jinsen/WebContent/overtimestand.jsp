<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假标准</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">  
   
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>加班补贴标准界面</span><a href="overtime.jsp" style="float:right;margin-right:20px;color:black;"><span>返回</span></a></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面可添加，删除，修改加班类型的补贴标准</p>
    </div>
    <div class="find-top1">
        <table class="top-table">
            <tr><td></td></tr>
            <tr><td class="top-table-label">加班类型：</td><td><input type="text" id="types"></td><td class="top-table-label">加班标准：</td><td><input type="text" id="stand"></td></tr>
            <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="addtype()"><i class="glyphicon glyphicon-edit"></i> 提交</button> <button class="add-del"><i class="glyphicon glyphicon-remove"></i> 取消</button></td></tr>
        </table>
    </div>
        <div class="table-con">
        <button class='btn   btn-default' id="remove" >批量删除</button>
    </div>
    <div class="table-con">
          <table class="table" id="query">
               <!--  <thead>
                    <tr>
                        <th>ID</th>
                        <th>加班类型名称</th>
                        <th>补贴标准</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="body1">
               </tbody>
               -->
           </table>
           </div>
 
</main>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑内容</h4>
            </div>
            <div class="modal-body" id="body2">
            </div>

        </div>
    </div>
</div>
<script>
$(function(){
	//getAllinfo();
	table1();
})
function getAllinfo(){//显示加班标准内容
var type="select";
$.ajax({
	url:'overtime',
	type:'post',
	data:{"type":type,"action":"typeselect"},
	dataType:"json",
	success:function(data){
		var htmlstr="";
		totalCount = data.length;
		 for(var i=0;i<data.length;i++){
					var o = data[i];
					var str = 
					"<tr><th>"+o.overtimetype_id
					+"</th><th>"+o.overtimetype_type
					+"</th><th>"+o.overtimetype_stand
					+"</th><th><button type='button' class='btn btn-info' id='user_edit' onclick='editorselect("+o.overtimetype_id+")' style='margin-right:10px;'data-toggle='modal' data-target='#myModal'>编辑</button>"
					+"<button type='button' class='btn btn-danger' id='user_del' onclick='typedelete("+o.overtimetype_id+")'>删除</button>"
					+"</th></tr>"
					htmlstr+=str; 
		}
		$("#body1").html(htmlstr); 
	},
	error:function(){
		alert('服务器执行错误！');
	}
});
}
function addtype()
{
	var types=$("#types").val();
	var stand=$("#stand").val();
	$.ajax({
		 url:"overtime",
		 type:"POST",
		 data:{"action":"typeadd","types":types,"stand":stand},
	     dataType:"text",
	     success:function (data)
	   {
	    		if(data=="true")
	    		{
	    			alert("添加成功！");	
	    			window.location.reload();
	    		}
	    		else
	    		{
	    			alert("添加失败！");	  
	    		}
	    },
	     error:function(e){
	    	 alert("请联系管理员！");
	    }
	});
}
function typedelete(id)
{
	$.ajax({
		 url:"overtime",
		 type:"POST",
		 data:{"action":"typedelete","id":id},
	     dataType:"text",
	     success:function (data)
	    	{
	    		if(data=="true")
	    		{
	    			alert("删除成功！");
	    			window.location.reload();
	    		}
	    		else
	    		{
	    			alert("删除失败！");	  
	    		}
	    	},
	     error:function(e){
	    	 alert("请联系管理员！");	  
	    }
	});
}
function editorselect(id){
	$.ajax({
		url:"overtime",
		type:"POST",
		data:{"action":"editselect","id":id},
		dataType:"json",
		success:function(data){
			var htmlstr="";
			totalCount = data.length;
			 for(var i=0;i<data.length;i++){
						var o = data[i];
						var str ="<form action='#'><div class='form-group'><label for='addname'>加班类型</label><input type='text' id='type' class='form-control' value="+o.overtimetype_type+"></div><div class='form-group'><label for='addpassword'>补贴标准</label><input type='text' id='stands' class='form-control' value="+o.overtimetype_stand+"></div></form><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button><button type='button' class='btn btn-primary' onclick='updatetype("+o.overtimetype_id+")'>提交</button></div>"
						htmlstr+=str; 
			}
			$("#body2").html(htmlstr); 
		},
		error:function(){
			alert('服务器执行错误！');
		}
	});
}
function updatetype(id)
{
	var types=$("#type").val();
	var stands=$("#stands").val();
	$.ajax({
		 url:"overtime",
		 type:"POST",
		 data:{"action":"updatetype","id":id,"types":types,"stands":stands},
	     dataType:"text",
	     success:function (data)
	    	{
	    		if(data=="true")
	    		{
	    			alert("修改成功！");	
	    			window.location.reload();
	    		}
	    		else
	    		{
	    			alert("修改失败！");	  
	    		}
	    	},
	     error:function(e){
	    	 alert("请联系管理员！");	  
	    }
	});
}
function table1() {
    $('#query').bootstrapTable("destroy");
    //加载表格
    $('#query').bootstrapTable({
        rowStyle: function (row, index) {//row 表示行数据，object,index为行索引，从0开始
            var style = "";
            if (row.SignInTime == '' || row.SignOutTime=='') {
                style = { css: { 'color': 'red' } };
            }
            return  style;
        },
        method:"post",
        showHeader:true,
        showLoading: true,
        striped: true,       
        singleSelect:false,
        cacahe:false,
        showColumns: true,                  //是否显示所有的列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        showRefresh: true,                  //是否显示刷新按钮
        url:"overtime?action=typeselect",
        dataType:"json",
        pagination:true,
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 5,                       //每页的记录行数（*）
        editable:true,
        pageList: [2, 3, 10, 15],
        clickToSelect:true,
        contentType: "application/x-www-form-urlencoded",//post请求的话就加上这个句话
        queryParms:null,
        columns: [{
            checkbox: true
        }, 
        {
                field: 'overtimetype_id',
                title:  '序列号'
        }, {
                field: 'overtimetype_type',
                title:  '加班类型名称'
        }, {
                field: 'overtimetype_stand',
                title:  '补贴标准'
        },{
                 title: '操作',
                 field: 'overtimetype_id',
                formatter: operateFormatter
          }
        ]
    });
    return InitTable;
};
function operateFormatter(value, row, index) {
    return [
         '<button type="button" class="btn btn-default"  onclick="editorselect('+value+')" style="margin-right:10px;"data-toggle="modal" data-target="#myModal"">修改</button><button type="button" class="btn btn-default"  onclick="typedelete('+value+')">删除</button>'
     ].join('');
   };
   $("#remove").on("click", function() {
	     if (!confirm("是否确认删除？"))
	         return;
	     var rows = $("#query").bootstrapTable('getSelections');// 获得要删除的数据
	     if (rows.length == 0) {// rows 主要是为了判断是否选中，下面的else内容才是主要
	         alert("请先选择要删除的记录!");
	         return;
	     } else {
	         var ids = new Array();// 声明一个数组
	         $(rows).each(function() {// 通过获得别选中的来进行遍历
	        	 typedelete(this.overtimetype_id)
	         });
	     }
	 })
</script>
</html>