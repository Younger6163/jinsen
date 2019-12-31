<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假</title>
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
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>请假管理界面</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是请假管理的详情的页面！主要实现请假信息的增，删，改查等操作</p>
    </div>
    <div class="find-top1">
    <form>
        <table class="top-table">
            <tr><td class="top-table-label">职员编号：</td><td><input type="text" id="staff_id"></td><td class="top-table-label">职员姓名：</td><td><input type="text" id="staff_name"></td><td class="top-table-label">请假起始时间：</td><td><input type="datetime-local" id="leave_time"></td></tr>
            <tr><td class="top-table-label">请假结束时间：</td><td><input type="datetime-local" id="leave_times"></td><td class="top-table-label">请假事由：</td><td><input type="text" id="leave_reason"></td><td class="top-table-label">请假类型：</td><td><select id="leave_type"></select></td></tr>
            <tr><td class="top-table-label">审核状态：</td><td><input type="radio" checked="checked" name="leave_state" value="通过"><span>通过</span><input type="radio" name="leave_state" value="未通过"><span>未通过</span></td></tr>
            <tr><td colspan="6" style="text-align: center"> <button class="add-but" onclick="leaveadd()"><i class="glyphicon glyphicon-edit">提交</i></button> <button class="add-del"  type="reset" ><i class="glyphicon glyphicon-remove" >取消</i></button></td></tr>
        </table>
    </div>
    </form>
    <div class="table-con">
        <button class="btn btn-default">导入文件</button>
        <button class="btn btn-default" onclick="location.href='qingjiastand.jsp'">调整请假类型扣款标准</button>
        <button class='btn btn-default' id="remove">批量删除</button>
    </div>
        <div class="table-con">
          <table class="table" id="query">
               <!-- <thead>
                    <tr>
                        <th>职员编号</th>
                        <th>职员姓名</th>
                        <th>请假起始时间</th>
                        <th>请假结束时间</th>
                        <th>请假事由</th>
                        <th>请假类型</th>
                        <th>请假状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="body1">
                
               </tbody>
               -->
           </table>
           </div>
    
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改请假单信息</h4>
                
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
    	table1();
  
    })
    function leavedelete(id)
    {
    	$.ajax({
    		 url:"leaveovertimeServlet",
    		 type:"POST",
    		 data:{"action":"leavedelete","id":id},
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
    function renderTime(date) {
  	  var dateee = new Date(date).toJSON();
  	  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
  }
    function leaveadd()//添加请假单
    {
    	var staff_id=$("#staff_id").val();
    	var staff_name=$("#staff_name").val();
    	var leave_time=renderTime($("#leave_time").val());
    	var leave_times=renderTime($("#leave_times").val());
    	var leave_reason=$("#leave_reason").val();
    	var leave_type=$("#leave_type").val();
    	var leave_state=$('input[type=radio]:checked').val();
        if(leave_times>=leave_time)
        {  
    	$.ajax({
    		 url:"leaveovertimeServlet",
    		 type:"POST",
    		 data:{"action":"leaveadd",
    			    "staff_id":staff_id,
    			    "staff_name":staff_name,
    			    "leave_time":leave_time,
    			    "leave_times":leave_times,
    			    "leave_reason":leave_reason,
    			    "leave_type":leave_type,
    			    "leave_state":leave_state},
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
    function selecttype(){//显示请假标准内容
    	$.ajax({
    		url:'leaveovertimeServlet',
    		type:'post',
    		data:{"action":"qjselecttype"},
    		dataType:"json",
    		success:function(data){
    			var htmlstr="";
    			totalCount = data.length;
    			 for(var i=0;i<data.length;i++){
    						var o = data[i];
    						var str = 
    						"<option>"+o.leavetype_type+"</option>"
    						htmlstr+=str; 
    			}
    			$("#leave_type").html(htmlstr); 
    			$("#update_type").html(htmlstr); 
    		},
    		error:function(){
    			alert('服务器执行错误！');
    		}
    	});
    }
    function updateleave(id)
    {
    	var update_time=$("#update_time").val();
    	var update_times=$("#update_times").val();
    	var update_reason=$("#update_reason").val();
    	var update_type=$("#update_type").val();
    	$.ajax({
    		 url:"leaveovertimeServlet",
    		 type:"POST",
    		 data:{"action":"updateleave","id":id,"update_time":update_time,"update_times":update_times,"update_reason":update_reason,"update_type":update_type},
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
    function editorselect(id){
    	$.ajax({
    		url:"leaveovertimeServlet",
    		type:"POST",
    		data:{"action":"selecteditor","id":id},
    		dataType:"json",
    		success:function(data){
    			var htmlstr="";
    			totalCount = data.length;
    			selecttype();
    			 for(var i=0;i<data.length;i++){
    						var o = data[i];
    						var str ="<form action='#'><div class='form-group'><label for='leave_time'>请假起始时间</label><input type='datetime-local' id='update_time' class='form-control'></div><div class='form-group'><label for='leave_times'>请假结束时间</label><input type='datetime-local' id='update_times' class='form-control'></div><div class='form-group'><label for='leave_reason'>请假事由</label><input type='text' id='update_reason' class='form-control' value="+o.leave_reason+"></div><div class='form-group'><label for='leave_type'>请假类型</label><select class='form-control' id='update_type'></select></div></form><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button><button type='button' class='btn btn-primary' onclick='updateleave("+id+")'>提交</button></div>"
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
            showHeader:true,
            showLoading: true,
            search: true, //显示搜索框
            striped: true,       
            singleSelect:false,
            cacahe:false,
            url:"leaveovertimeServlet?action=qjselect",
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
                    field: 'leave_time',
                    title:  '请假起始时间',
                    formatter: function (value, row, index) {
                        return changeDateFormat(value)
                    }
            },{
                    field: 'leave_times',
                    title:  '请假结束时间',
                    formatter: function (value, row, index) {
                        return changeDateFormat(value)
                    }
            },{
                    field: 'leave_reason',
                    title:  '请假事由'
            },{
                    field: 'leave_type',
                    title:  '请假类型'
            },{
                    field: 'leave_state',
                    title:  '请假状态'
            },{
                     title: '操作',
                     field: 'leave_id',
                 formatter: operateFormatter
              }
            ]
        });
        return InitTable;
    };
    function operateFormatter(value, row, index) {
    	
       return [
            '<button type="button" class="btn btn-default"  onclick="editorselect('+value+')" style="margin-right:10px;"data-toggle="modal" data-target="#myModal"">修改</button><button type="button" class="btn btn-default"  onclick="leavedelete('+value+')">删除</button>'
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
            	 leavedelete(this.leave_id)
            });
        }
    })
</script>
</body>
</html>