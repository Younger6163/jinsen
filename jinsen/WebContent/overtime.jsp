<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>加班</title>
   <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">  
    <script type="text/javascript" src="js/moment.js"></script> 
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>加班管理界面</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是加班管理的详情的页面！主要实现请假信息的增，删，改查等操作</p>
    </div>
    <div class="find-top1">
    <form>
        <table class="top-table">
            <tr><td class="top-table-label">职员编号：</td><td><input type="text" id="staff_id"></td><td class="top-table-label">职员姓名：</td><td><input type="text" id="staff_name"></td><td class="top-table-label">加班起始时间：</td><td><input type="datetime-local" id="overtime_time"></td></tr>
            <tr><td class="top-table-label">加班结束时间：</td><td><input type="datetime-local" id="overtime_times"></td><td class="top-table-label">加班事由：</td><td><input type="text" id="overtime_reason"></td><td class="top-table-label">加班类型：</td><td><select id="overtime_type"></select></td></tr>
            <tr><td class="top-table-label">审核状态：</td><td><input type="radio" checked="checked" name="overtime_state" value="通过"><span>通过</span><input type="radio" name="overtime_state" value="未通过"><span>未通过</span></td></tr>
            <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="overtimeadd()"><i class="glyphicon glyphicon-edit">提交</i></button> <button class="add-del"  type="reset" ><i class="glyphicon glyphicon-remove" >取消</i></button></td></tr>
        </table>
    </div>
    </form>
    <div class="table-con">
        <button class="btn  btn-default">导入文件</button>
        <button class="btn  btn-default" onclick="location.href='overtimestand.jsp'">调整加班类型补贴标准</button>
        <button class='btn   btn-default' id="remove" >批量删除</button>
    </div>
        <div class="table-con">
          <table class="table" id="query">
              <!--   <thead>
                    <tr>
                        <th>职员编号</th>
                        <th>职员姓名</th>
                        <th>加班起始时间</th>
                        <th>加班结束时间</th>
                        <th>加班事由</th>
                        <th>加班类型</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="body1">
                
               </tbody>-->
           </table>
           </div>
    
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
</main>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>

<script>
$(function(){
	//getAllinfo();
	table1();
})

function getAllinfo(){//显示加班标准内容
$.ajax({
	url:'overtime',
	type:'post',
	data:{"action":"jbselect"},
	dataType:"json",
	success:function(data){
		var htmlstr="";
		var htmlstr1="";
		totalCount = data.length;
		 for(var i=0;i<data.length;i++){
					var l = data[i];
					var str = 
						"<tr><th>"+l.staff_id
						+"</th><th>"+l.staff_name
						+"</th><th>"+ moment(l.overtime_time).format("YYYY-MM-DD HH:mm:ss")
						+"</th><th>"+ moment(l.overtime_times).format("YYYY-MM-DD HH:mm:ss")
						+"</th><th>"+l.overtime_reason
						+"</th><th>"+l.overtime_type
						+"</th><th>"+l.overtime_state
						+"</th><th><button type='button' class='btn btn-info' id='user_edit' onclick='editorselect("+l.overtime_id+")' style='margin-right:10px;'data-toggle='modal' data-target='#myModal'>修改</button>"
						if(l.overtime_state=="通过")
						{
							str+="</th></tr>"
						}
						else
						{
							str+="<button type='button' class='btn btn-danger' id='user_del' onclick='overtimedelete("+l.overtime_id+")'>删除</button>"
							+"</th></tr>"
						}
						htmlstr+=str;
					    selecttype();
		}

		$("#body1").html(htmlstr); 
	},
	error:function(){
		alert('服务器执行错误！');
	}
});
}
function overtimedelete(id)
{
	$.ajax({
		 url:"overtime",
		 type:"POST",
		 data:{"action":"overtimedelete","id":id},
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
	    			alert("删除失败！");	  
	    		}
	    	},
	     error:function(e){
	    	 alert("请联系管理员！");	  
	    }
	});
}
function renderTime(date) {
	  var dateee = new Date(date).toJSON();
	  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
}
function overtimeadd()//添加加班单
{
	var staff_id=$("#staff_id").val();
	var staff_name=$("#staff_name").val();
	var overtime_time=renderTime($("#overtime_time").val());
	var overtime_times=renderTime($("#overtime_times").val());
	var overtime_reason=$("#overtime_reason").val();
	var overtime_type=$("#overtime_type").val();
	var overtime_state=$('input[type=radio]:checked').val();
    if(overtime_times>=overtime_time)
    {  
	$.ajax({
		 url:"overtime",
		 type:"POST",
		 data:{"action":"overtimeadd",
			    "staff_id":staff_id,
			    "staff_name":staff_name,
			    "overtime_time":overtime_time,
			    "overtime_times":overtime_times,
			    "overtime_reason":overtime_reason,
			    "overtime_type":overtime_type,
			    "overtime_state":overtime_state},
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
	    	 alert("请检查填写内容！");
	    }
	});
    }
    else
    	{
    	    alert("填写时间有误！");
    	}
}
function selecttype(){//显示加班标准内容
	$.ajax({
		url:'overtime',
		type:'post',
		data:{"action":"qjselecttype"},
		dataType:"json",
		success:function(data){
			var htmlstr="";
			totalCount = data.length;
			 for(var i=0;i<data.length;i++){
						var o = data[i];
						var str = 
						"<option>"+o.overtimetype_type+"</option>"
						htmlstr+=str; 
			}
			$("#overtime_type").html(htmlstr); 
			$("#update_type").html(htmlstr); 
		},
		error:function(){
			alert('服务器执行错误！');
		}
	});
}
function updateovertime(id)
{
	var update_time=renderTime($("#update_time").val());
	var update_times=renderTime($("#update_times").val());
	var update_reason=$("#update_reason").val();
	var update_type=$("#update_type").val();
	if(update_times>=update_time)
	{ 
	$.ajax({
		 url:"overtime",
		 type:"POST",
		 data:{"action":"updateovertime","id":id,"update_time":update_time,"update_times":update_times,"update_reason":update_reason,"update_type":update_type},
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
	else
	{
		alert("填写时间有误！");
	}
}
function editorselect(id){
	$.ajax({
		url:"overtime",
		type:"POST",
		data:{"action":"selecteditor","id":id},
		dataType:"json",
		success:function(data){
			var htmlstr="";
			totalCount = data.length;
			selecttype();
			 for(var i=0;i<data.length;i++){
						var o = data[i];
						var str ="<form action='#'><div class='form-group'><label for='overtime_time'>加班起始时间</label><input type='datetime-local' id='update_time' class='form-control'></div><div class='form-group'><label for='overtime_times'>加班结束时间</label><input type='datetime-local' id='update_times' class='form-control'></div><div class='form-group'><label for='overtime_reason'>加班事由</label><input type='text' id='update_reason' class='form-control' value="+o.overtime_reason+"></div><div class='form-group'><label for='overtime_type'>加班类型</label><select class='form-control' id='update_type'></select></div></form><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button><button type='button' class='btn btn-primary' onclick='updateovertime("+id+")'>提交</button></div>"
						htmlstr+=str; 
			}
			$("#body2").html(htmlstr); 
		},
		error:function(){
			alert('服务器执行错误！');
		}
	});
}
function table1() {
	selecttype();
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
        search: true, //显示搜索框
        showHeader:true,
        showLoading: true,
        striped: true,       
        singleSelect:false,
        cacahe:true,
        url:"overtime?action=jbselect",
        dataType:"json",
        pagination:true,
        showColumns: true,                  //是否显示所有的列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        showRefresh: true,                  //是否显示刷新按钮
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
                field: 'staff_id',
                title:  '职员编号'
        }, {
                field: 'staff_name',
                title:  '职员姓名'
        }, {
                field: 'overtime_time',
                title:  '加班起始时间',
                formatter: function (value, row, index) {
                    return changeDateFormat(value)
                }
        },{
                field: 'overtime_times',
                title:  '加班结束时间',
                formatter: function (value, row, index) {
                    return changeDateFormat(value)
                }
        },{
                field: 'overtime_reason',
                title:  '加班事由'
        },{
                field: 'overtime_type',
                title:  '加班类型'
        },{
                field: 'overtime_state',
                title:  '状态'
        },{
                 title: '操作',
                 field: 'overtime_id',
             formatter: operateFormatter
          }
        ]
    });
    return InitTable;
};
function operateFormatter(value, row, index) {
	
    return [
         '<button type="button" class="btn btn-default"  onclick="editorselect('+value+')" style="margin-right:10px;"data-toggle="modal" data-target="#myModal"">修改</button><button type="button" class="btn btn-default"  onclick="overtimedelete('+value+')">删除</button>'
     ].join('');
   };

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
        	 overtimedelete(this.overtime_id)
         });
     }
 })

</script>
</body>
</html>