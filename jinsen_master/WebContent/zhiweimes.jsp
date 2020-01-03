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
    <title>职位信息维护</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">

</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>绩效管理</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面主要是修改职位信息界面，请谨慎操作</p>
    </div>
   
       <div class="find-top">
<button  id="addps" data-id="98" class="btn btn-xs btn-primary" onclick="fn_addposition()" >新增岗位信息</button>
<button  id="addps" data-id="98" class="btn btn-xs btn-primary" onclick="fn_adddp()" >新增部门信息</button>
<button  id="addps" data-id="98" class="btn btn-xs btn-primary" onclick="fn_addcompany()" >新增公司信息</button>
    </div> 


   
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>

</main>
<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>

    $(function(){
        table1();
        $("#pstest").hide();
        $("#dptest").hide();
        $("#test").change(function(){
            //要触发的事件
            var test = $("#test").val()
             $("#addps_name").val(test);
        	alert(test);
           });
    })
    

    function table1(){
        $('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            url: "position?action=allposition", 
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            showColumns:true,
            sortable:true,
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
            	 {
                     title: "代号",
                     field: 'all_id',
                     align: 'center',
                     valign: 'middle'
                 },
                {
                    title: "公司",
                    field: 'cm_name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门',
                    field: 'dp_name',
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
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var  d = '<button  id="positonup" data-id="98" class="btn btn-xs btn-primary" onclick="fn_getPositionUpdate(\''+row.all_id+'\')" >修改</button><button  id="lizhi" data-id="98"  class="btn btn-xs" onclick="fn_getPositionDel(\''+row.all_id+'\')">删除岗位</button>';
                        return  d;
                    }
                }
            ]
        });
    }
    
  //增加职位信息
//进行修改职位信息操作,根据id获取指定用户
  function fn_addposition(){
      alert("nice");
		$('#addpositionModal').modal({
			backdrop:"static"		
		});
}
  function fn_addcompany(){
      alert("nice");
		$('#addcompanyModal').modal({
			backdrop:"static"		
		});
}
  function fn_adddp(){
      alert("nice");
		$('#adddpModal').modal({
			backdrop:"static"		
		});
}

  function psmenu(){
      alert("nice");
      $("#pstest").show();
}
  
  function dpmenu(){
      alert("nice");
      $("#dptest").show();
}
  

  //改变
//增加岗位按钮
function addpost(){
	//发送ajax，更新用户信息
	 var dp_name = document.getElementById('pstest').value;
	 var ps_name = document.getElementById('addps_name').value;

	 alert("控制台：正在对代号"+all_id+"增加职位信息操作");
  	 var type="addposition";
	 $.ajax({
		url:"position",
		type:"POST",
		data:{"action":type,"dp_name":dp_name,"ps_name":ps_name},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(all_id+"新增岗位成功完成");
			$("#addpositionModal").modal('hide');
			table1();
			location.reload();

			}else{
			    alert(id+"无法完成此操作");
			}
		},
		error:function(){
			alert("发生未知错误");
		}
	 }); 
}
//增加部门按钮
function adddepat(){
	//发送ajax，更新用户信息
	 var cm_name = document.getElementById('dptest').value;
	 var dp_name = document.getElementById('adddp_name').value;

	 alert("控制台：正在对代号"+all_id+"增加职位信息操作");
 	 var type="adddepartment";
	 $.ajax({
		url:"position",
		type:"POST",
		data:{"action":type,"cm_name":cm_name,"dp_name":dp_name},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(all_id+"新增部门成功完成");
			$("#adddpModal").modal('hide');
			table1();
			location.reload();

			}else{
			    alert(id+"无法完成此操作");
			}
		},
		error:function(){
			alert("发生未知错误");
		}
	 }); 
}
//增加公司按钮
function addcompany(){
	//发送ajax，更新用户信息
	 var cm_name = document.getElementById('addcm_name').value;

	 alert("控制台：正在对代号"+all_id+"增加职位信息操作");
	 var type="addcompany";
	 $.ajax({
		url:"position",
		type:"POST",
		data:{"action":type,"cm_name":cm_name},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(all_id+"新增公司成功完成");
			$("#addcompanyModal").modal('hide');
			table1();
			location.reload();

			}else{
			    alert(id+"无法完成此操作");
			}
		},
		error:function(){
			alert("发生未知错误");
		}
	 }); 
}
  
  //进行修改职位信息操作,根据id获取指定用户
  function fn_getPositionUpdate(id){
      alert(id);
  	 //$("#type").val("edit");
  	 var type="getposition";
  	 $.ajax({
  		url:"position",
  		type:"POST",
  		data:{"action":type,"all_id":id},
  		dataType:"json",
  		success:function(data){
  			 $("#cm_id").val(data.cm_id);
  			 $("#cm_name").val(data.cm_name);
  	 	     $("#cm_info").val(data.cm_info);
  			 $("#dp_id").val(data.dp_id);
  			 $("#dp_name").val(data.dp_name);
  	 	     $("#dp_info").val(data.dp_info);
  			 $("#ps_id").val(data.ps_id);
  			 $("#ps_name").val(data.ps_name);
  	 	     $("#ps_info").val(data.ps_info);
  	 	     $("#all_id").val(data.all_id);

			//显示
			$('#positionModal').modal({
				backdrop:"static"		
			});
		},
		error:function(){
			alert("失败！");
		}
	 });
}
  //进行删除职位信息操作,根据id获取指定用户
  function fn_getPositionDel(id){
      alert(id);
  	 //$("#type").val("edit");
  	 var type="getposition";
  	 $.ajax({
  		url:"position",
  		type:"POST",
  		data:{"action":type,"all_id":id},
  		dataType:"json",
  		success:function(data){
  			 $("#delcm_id").val(data.cm_id);
  			 $("#delcm_name").val(data.cm_name);
  	 	     $("#delcm_info").val(data.cm_info);
  			 $("#deldp_id").val(data.dp_id);
  			 $("#deldp_name").val(data.dp_name);
  	 	     $("#deldp_info").val(data.dp_info);
  			 $("#delps_id").val(data.ps_id);
  			 $("#delps_name").val(data.ps_name);
  	 	     $("#delps_info").val(data.ps_info);
  	 	     $("#delall_id").val(data.all_id);

			//显示
			$('#delpositionModal').modal({
				backdrop:"static"		
			});
		},
		error:function(){
			alert("失败！");
		}
	 });
}

//职位信息修改确认按钮
function fn_update_position(){
	//发送ajax，更新用户信息
	 var all_id = document.getElementById('all_id').value;
	 var cm_name = document.getElementById('cm_name').value;
	 var dp_name = document.getElementById('dp_name').value;
	 var ps_name = document.getElementById('ps_name').value;
	 var cm_id = document.getElementById('cm_id').value;
	 var dp_id = document.getElementById('dp_id').value;
	 var ps_id = document.getElementById('ps_id').value;
	 
	 alert("控制台：正在对代号"+all_id+"进行修改职位信息操作");
  	 var type="updateposition";
	 $.ajax({
		url:"position",
		type:"POST",
		data:{"action":type,"all_id":all_id,"cm_name":cm_name,"dp_name":dp_name,"ps_name":ps_name,"cm_id":cm_id,"dp_id":dp_id,"ps_id":ps_id},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(all_id+"职位信息修改完成");
			$("#positionModal").modal('hide');
			table1();
			location.reload();

			}else{
			    alert(id+"无法完成此操作");
			}
		},
		error:function(){
			alert("发生未知错误");
		}
	 });
}

//删除职位信息确认按钮
function fn_del_position(){
	//发送ajax，更新用户信息
	 var all_id = document.getElementById('delall_id').value;
 
	 alert("控制台：正在对代号"+all_id+"进行修改职位信息操作");
  	 var type="delposition";
	 $.ajax({
		url:"position",
		type:"POST",
		data:{"action":type,"all_id":all_id},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(all_id+"职位信息修改完成");
			$("#delpositionModal").modal('hide');
			table1();
			location.reload();
			}else{
			    alert(id+"无法完成此操作");
			}
		},
		error:function(){
			alert("发生未知错误");
		}
	 });
}

</script>
	<!-- 修改职位信息模态框 -->
	<div class="modal fade" id="positionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
					   
						<div class="form-group">
							<label class="col-sm-2 control-label">职位代号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="all_id"
									id="all_id" readonly="value" placeholder="职位信息丢失"> 					
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">公司代号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="cm_id"
									id="cm_id"  readonly="value" placeholder="职位信息丢失"> 					
						    </div>
						</div>
												<div class="form-group">
							<label class="col-sm-2 control-label">公司名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="cm_name"
									id="cm_name"  placeholder="职位信息丢失"> 					
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">部门代号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="dp_id"
									id="dp_id" readonly="value"  placeholder="职位信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">部门名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="dp_name"
									id="dp_name"   placeholder="职位信息丢失"> 					
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">岗位代号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="ps_id"
									id="ps_id" readonly="value"  placeholder="职位信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">岗位名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="ps_name"
									id="ps_name"   placeholder="职位信息丢失"> 					
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">岗位员工数：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name=""
									id="" readonly="value"  placeholder="测试中"> 					
						    </div>
						</div>		
							<div class="form-group">
							<label class="col-sm-2 control-label">介绍:</label>
							<div class="col-sm-10">
											<textarea id="lizhireason" name="lizhireason" rows="3" cols="20" readonly="value" ></textarea>
							</div>
						</div>
						  <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">请确认：修改部门会将所有员工信息下的所属信息进行修改，请谨慎修改!!</a>
                        </label>
                    </div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="position_update_btn" onclick="fn_update_position()">确认修改职位信息</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
<!-- 删除职位信息模态框 -->
	<div class="modal fade" id="delpositionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
					   
						<div class="form-group">
							<label class="col-sm-2 control-label">职位代号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="delall_id"
									id="delall_id" readonly="value" placeholder="职位信息丢失"> 					
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">所在公司：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="delcm_name"
									id="delcm_name" readonly="value" placeholder="职位信息丢失"> 					
						    </div>
						</div>

							<div class="form-group">
							<label class="col-sm-2 control-label">所在部门名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="deldp_name"
									id="deldp_name" readonly="value"  placeholder="职位信息丢失"> 					
						    </div>
						</div>

							<div class="form-group">
							<label class="col-sm-2 control-label">岗位名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="delps_name"
									id="delps_name"   placeholder="职位信息丢失"> 					
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">岗位员工数：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name=""
									id="" readonly="value"  placeholder="测试中"> 					
						    </div>
						</div>		
							
						  <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">请确认：请谨慎修改!!</a>
                        </label>
                    </div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="position_update_btn" onclick="fn_del_position()">删除岗位</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
	<!-- 增加岗位信息模态框 -->
	<div class="modal fade" id="addpositionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">


						<div class="form-group">
							<label class="col-sm-3 control-label">新增岗位名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="addps_name"
									id="addps_name"   placeholder="请输入"> 	
							<div class="form-group">
							<!-- 留白 -->
							<div class="col-sm-6"> 
							    </div>
						</div>
						<button type="button" class="btn btn-primary" id="mmqwe" onclick="psmenu()">确定</button>
								
						    </div>
						    </div>
							<div class="form-group">
							<label class="col-sm-3 control-label">选择所属部门：</label>
							<div class="col-sm-6"> 
					<%
					System.out.println("下拉框获取数据库");
					List<Position> lst = new ArrayList<Position>();
					Connection conn = dbCon.getConnection();
					PreparedStatement st;
					String sql=null;
					sql="SELECT dp_name from department";
					try {
						System.out.println("MySQL接入SQL语句");
						st = (PreparedStatement) conn.prepareStatement(sql);  
					    ResultSet rs = st.executeQuery(sql);
					    while(rs.next()){
					    	Position pst = new Position();
					    	pst.setDp_name(rs.getString("dp_name"));
					    	lst.add(pst);
					    }
					}catch (Exception e){
					    System.out.println(e.getMessage());
					}finally {
					    if (conn!=null){
					        conn.close();
					    }
					}
					%>
					<select id="pstest" class="form-control"> 
					<option>--请选择--</option>
					<%for(int i=0;i<lst.size();i++){%>
					<option><%=lst.get(i).getDp_name()%></option>
					<%}%>
					</select>				
						    </div>
						</div>

						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="aps" onclick="addpost()">新增岗位</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
	
		<!-- 增加部门信息模态框 -->
	<div class="modal fade" id="adddpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">


						<div class="form-group">
							<label class="col-sm-2 control-label">新增部门名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="adddp_name"
									id="adddp_name"   placeholder="请输入"> 	
							<div class="form-group">
							<!-- 留白 -->
							<div class="col-sm-6"> 
							    </div>
						</div>		
						<button type="button" class="btn btn-primary" id="mm" onclick="dpmenu()">确定</button>
													
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">选择所属公司：</label>
				
							<div class="col-sm-6"> 
						
				<%
				List<Position> lstcm = new ArrayList<Position>();
				Connection conncm = dbCon.getConnection();
				PreparedStatement stcm;
				String sql1=null;
					sql1="select cm_name from company";
					try {
						System.out.println("MySQL接入SQL语句");
						stcm = (PreparedStatement) conncm.prepareStatement(sql);  
					    ResultSet rsm = stcm.executeQuery(sql1);
					    while(rsm.next()){
					    	Position pstcm = new Position();
					    	pstcm.setCm_name(rsm.getString("cm_name"));
					    	lstcm.add(pstcm);
					    }
					}catch (Exception e){
					    System.out.println(e.getMessage());
					}finally {
					    if (conncm!=null){
		    		    	conncm.close();
					    }
					}
					%>
					<select id="dptest" class="form-control"> 
					<option>--请选择--</option>
					<%for(int i=0;i<lstcm.size();i++){%>
					<option><%=lstcm.get(i).getCm_name()%></option>
					<%}%>
					</select>				
						    </div>
						</div>

						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="" onclick="adddepat()">新增部门</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
		<!-- 增加公司信息模态框 -->
	<div class="modal fade" id="addcompanyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">


						<div class="form-group">
							<label class="col-sm-2 control-label">新增公司名称：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="addcm_name"
									id="addcm_name"   placeholder="请输入"> 	
					
						    </div>
						</div>


						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="position_update_btn" onclick="addcompany()">新增公司</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
</body>
</html>