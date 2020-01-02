<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>

<%@ page import="jinsen.bean.Position" %>
<%@ page import="jinsen.dao.PositionDao" %>
<%@ page import="jinsen.db.dbCon" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>入职登记</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    

</head>
<body>
<main>
<!-- 下拉框代码 -->
				


</main>
<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>

    $(function(){
        table1();
        getIntype();
    })
    function getIntype(){
    $.ajax({
        type:"post",
        dataType:"json",
        url:"position?action=allcompany",
        success:function(data){
            var len=data.length;
            for(var i=0;i<len;i++){
                var option=document.createElement("option");
                $("#companyselect").append(('<option value='+data[i].cm_name+'>'+data[i].cm_name+'</option>'));
            }
        },
        error:function(data){
            console.log(data);
        }
    });
}
//获取部门信息
    function xialacompany(obj){
    	$("#dpselect").empty();
    	var test = $(obj).val();
   	    var type="alldpa";
    	$.ajax({
    		url:"position",
    		type:"POST",
    		data:{"action":type,"cm_name":test},
            dataType:"json",
            success:function(data){
            	alert("1");
                var len=data.length;
                for(var i=0;i<len;i++){
                    var option=document.createElement("option");
                    $("#dpselect").append(('<option value='+data[i].dp_name+'>'+data[i].dp_name+'</option>'));
                }
				
            },
            error:function(data){
            	alert("0");

            }
        });
    }
    //获取岗位信息
    function xialadp(obj){
    	$("#psselect").empty();
    	var test = $(obj).val();
    	var type="alldps";
    	$.ajax({
    		url:"position",
    		type:"POST",
    		data:{"action":type,"dp_name":test},
            dataType:"json",
            success:function(data){
            	alert("1");
                var len=data.length;
                for(var i=0;i<len;i++){
                    var option=document.createElement("option");
                    $("#psselect").append(('<option value='+data[i].ps_name+'>'+data[i].ps_name+'</option>'));
                }
				
            },
            error:function(data){
            	alert("0");

            }
        });
    }
 
    
    
    function table1(){
        $('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            url: "staffinfo?action=allstaff", 
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, 
            showColumns:true,
            sortable:true,
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    title: "员工编号",
                    field: 'staff_id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '员工姓名',
                    field: 'staff_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '所属公司',
                    field: 'staff_company',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门',
                    field: 'staff_department',
                    align: 'center'
                },
                {
                    title: '岗位',
                    field: 'staff_post',
                    align: 'center'
                },
                {
                    title: '入职时间',
                    field: 'staff_time',
                    align: 'center'
                },
                {
                    title: '联系电话',
                    field: 'staff_phone',
                    align: 'center'
                },

            ]
        });
    }
    //增加员工
    function addstaff(){
      	//发送ajax，更新用户信息
      	 var post_name = document.getElementById('psselect').value;
      	 var staff_status = "在职";
     	 var staff_name = document.getElementById('name').value;
       	 var staff_sex = document.getElementById('sex').value;
      	 var staff_born = document.getElementById('born').value;
      	 var staff_province = document.getElementById('sheng').value;
      	 var staff_city = document.getElementById('shi').value;
      	 var staff_county = document.getElementById('xian').value;
      	 var staff_address = document.getElementById('address').value;
      	 var staff_idnumber = document.getElementById('sfz').value;
      	 var staff_time = document.getElementById('rzsj').value;
      	 var staff_phone = document.getElementById('phone').value; 
      	 
      	 alert("正在对"+id+"进行操作");
         var type="addstaff";
      	 $.ajax({
      		url:"staffinfo",
      		type:"POST",
      		data:{"action":type,"staff_name":staff_name,
      			"staff_sex":staff_sex,"staff_born":staff_born,"staff_province":staff_province,"staff_city":staff_city,
      			"staff_county":staff_county,"staff_address":staff_address,"staff_idnumber":staff_idnumber,"staff_time":staff_time,
      			"staff_phone":staff_phone,"staff_status":staff_status,"post_name":post_name},
      		dataType:"json",
      		success:function(data){
      			if(data==1){
      		    alert(id+"已经成功添加");
      			}else{
      			    alert(id+"无法完成操作");
      			}
      		},
      		error:function(){
      			alert("发生未知错误");
      		}
      	 });
      }
</script>
</head>
<body>
<main>
	
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>入职管理登录</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面主要是对入职的员工进行登记</p>
        
      
        
        
    </div>
    <div class="find-top1">
    
 <form id="ruzhi">
      <table class="top-table">
          <tr><td class="top-table-label">职员姓名：</td><td><input type="text" name="name" id="name"></td><td class="top-table-label">出生年月：</td><td><input type="date"  name="born" id="born"></td><td class="top-table-label">性别：</td><td><input type="radio" checked="checked" name="sex" value="男"><span>男</span><input type="radio" name="sex"  value="女"><span>女</span></tr>      
          <tr><td class="top-table-label">入职公司：</td><td>
<select id="companyselect" onchange="xialacompany(this)">
     <option value="0">请选择</option>
</select></td><td class="top-table-label">入职部门：</td><td><select id="dpselect" name="dpselect" onchange="xialadp(this)">
     <option value="0">请选择</option>
</select></td><td class="top-table-label">工作岗位：</td><td><select id="psselect" onchange="xialaps(this.options[this.selectedIndex])">
     <option value="0">请选择</option>
</select></td></tr>
          <tr><td class="top-table-label">省：</td><td><input type="text"  name="sheng" id="shi"></td><td class="top-table-label">市：</td><td><input type="text"  name="shi" id="shi"></td><td class="top-table-label">县：</td><td><input type="text" name="xian" id="xian"></td></tr>
          <tr><td class="top-table-label">详细地址：</td><td colspan="5"><input type="text" name="address" id="address"></td></tr>
          <tr><td class="top-table-label">身份证号码：</td><td><input type="text" name="sfz" id="sfz"></td><td class="top-table-label">手机号码：</td><td><input type="text" name="phone" id="phone"></td></tr>
          <tr><td class="top-table-label">入职时间：</td><td><input type="date"  name="rzsj" id="rzsj"></td></tr>
          <tr><td class="top-table-label">上传照片</td><td colspan="5"><input type="file" name="zp" id="zp"></td></tr>
          <tr><td class="top-table-label">上传档案</td><td colspan="5"><input type="file" name="da" id="da"></td></tr>
          <tr><td colspan="6" style="text-align: center"> <button class="add-but"  onclick="addstaff()"><i class="glyphicon glyphicon-edit"></i> 提交</button> <button class="add-del" type="button"><i class="glyphicon glyphicon-remove"></i> 清空</button></td></tr>
      </table>
   </form>
    </div>
<!-- 显示最近添加的员工 -->
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>

</main>

<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>