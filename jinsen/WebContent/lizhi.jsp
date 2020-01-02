<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>就职状态变更</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">

</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>绩效管理</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面主要是员工离职退休办理界面</p>
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

    })
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
            search: true, //显示搜索框
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
                    field: 'cm_id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门',
                    field: 'dp_id',
                    align: 'center'
                },
                {
                    title: '岗位',
                    field: 'ps_id',
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
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var  d = '<button  id="lizhi" data-id="98" class="btn btn-xs" onclick="fn_getUserlizhi(\''+row.staff_id+'\')" >离职</button><button  id="lizhi" data-id="98"  class="btn btn-xs btn-primary" onclick="fn_getUsertuixiu(\''+row.staff_id+'\')">退休</button> ';
                        
                        return  d;
                    }
                }
            ]
        });
    }

  //进行离职操作,根据id获取指定用户
  function fn_getUserlizhi(id){
      alert(id);
  	 //$("#type").val("edit");
  	 var type="getstaff";
  	 $.ajax({
  		url:"staffinfo",
  		type:"POST",
  		data:{"action":type,"staff_id":id},
  		dataType:"json",
  		success:function(data){
  			 $("#staff_id").val(data.staff_id);
  			 $("#staff_name").val(data.staff_name);
  	 	     $("#dp_id").val(data.dp_id);
  			 $("#staff_sex").val(data.staff_sex);
  			 $("#cm_id").val(data.cm_id);
  			 $("#ps_id").val(data.ps_id);
  			 $("#staff_born").val(data.staff_born);
 			 $("#staff_province").val(data.staff_province);
 		     $("#staff_city").val(data.staff_city);
 			 $("#staff_county").val(data.staff_county);
 			 $("#staff_address").val(data.staff_address);
 			 $("#staff_idnumber").val(data.staff_idnumber);
  			 $("#staff_time").val(data.staff_time);
  		     $("#staff_phone").val(data.staff_phone);
 			 $("#staff_pic").val(data.staff_pic);
 		     $("#staff_dangan").val(data.staff_dangan);
 			 $("#staff_status").val(data.staff_status); 


			//显示
			$('#lizhiModal').modal({
				backdrop:"static"		
			});
		},
		error:function(){
			alert("失败！");
		}
	 });
}
  //进行退休操作,根据id获取指定用户
  function fn_getUsertuixiu(id){
      alert(id);
  	 //$("#type").val("edit");
  	 var type="getstaff";
  	 $.ajax({
  		url:"staffinfo",
  		type:"POST",
  		data:{"action":type,"staff_id":id},
  		dataType:"json",
  		success:function(data){
  			 $("#tstaff_id").val(data.staff_id);
  			 $("#tstaff_name").val(data.staff_name);
  	 	     $("#tdp_id").val(data.dp_id);
  			 $("#tstaff_sex").val(data.staff_sex);
  			 $("#tcm_id").val(data.cm_id);
  			 $("#tps_id").val(data.ps_id);
  			 $("#tstaff_born").val(data.staff_born);
 			 $("#tstaff_province").val(data.staff_province);
 		     $("#tstaff_city").val(data.staff_city);
 			 $("#tstaff_county").val(data.staff_county);
 			 $("#tstaff_address").val(data.staff_address);
 			 $("#tstaff_idnumber").val(data.staff_idnumber);
  			 $("#tstaff_time").val(data.staff_time);
  		     $("#tstaff_phone").val(data.staff_phone);
 			 $("#tstaff_pic").val(data.staff_pic);
 		     $("#tstaff_dangan").val(data.staff_dangan);
 			 $("#tstaff_status").val(data.staff_status); 


			//显示
			$('#tuixiuModal').modal({
				backdrop:"static"		
			});
		},
		error:function(){
			alert("失败！");
		}
	 });
}

//用户离职确认按钮
function fn_lizhi_staff(){
	//发送ajax，更新用户信息
	 var id = document.getElementById('staff_id').value;
	 var lizhireason = document.getElementById('lizhireason').value;
	 var ps_id = document.getElementById('ps_id').value;


	 alert("正在对"+id+"进行操作");
  	 var type="lizhi";
	 $.ajax({
		url:"staffinfo",
		type:"POST",
		data:{"action":type,"staff_id":id,"lizhireason":lizhireason,"ps_id":ps_id},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(id+"成功离职");
			$("#lizhiModal").modal('hide');
			table1();
			}else{
			    alert(id+"无法完成操作");
			}
		},
		error:function(){
			alert("发生未知错误");
		}
	 });
}

//用户退休确认按钮
function fn_tuixiu_staff(){
	//发送ajax，更新用户信息
	 var id = document.getElementById('tstaff_id').value;
	 var lizhireason = document.getElementById('tlizhireason').value;
	 var ps_id = document.getElementById('tps_id').value;
	 alert("正在对"+id+"进行操作");
  	 var type="tuixiu";
	 $.ajax({
		url:"staffinfo",
		type:"POST",
		data:{"action":type,"staff_id":id,"lizhireason":lizhireason,"ps_id":ps_id},
		dataType:"json",
		success:function(data){
			if(data==1){
		    alert(id+"成功退休");
			$("#tuixiuModal").modal('hide');
			table1();
			}else{
			    alert(id+"用户已经离职或者退休，请在退休离职界面中查看是否有误");
			}
		},
		error:function(){
			alert("用户已经离职或者退休，请在退休离职界面中查看是否有误");
		}
	 });
}
</script>
	<!-- 离职模态框 -->
	<div class="modal fade" id="lizhiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
							<label class="col-sm-2 control-label">员工编号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_id"
									id="staff_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_name"
									id="staff_name" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">性别：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_sex"
									id="staff_sex" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">所属公司：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="cm_id"
									id="cm_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">所属部门：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="dp_id"
									id="dp_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">岗位：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="ps_id"
									id="ps_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
						
							<div class="form-group">
							<label class="col-sm-2 control-label">出生日期：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_born"
									id="staff_born" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">身份证号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_idnumber"
									id="staff_idnumber" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">入职时间：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_time"
									id="staff_time" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">联系电话：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_phone"
									id="staff_phone" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">照片：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_pic"
									id="staff_pic" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">地址:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_address" 
								id="staff_address" readonly="value" placeholder="员工信息丢失">
							</div>
						</div>
                         <div class="form-group">
							<label class="col-sm-2 control-label">就职状态:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_status" 
								id="staff_status" readonly="value" placeholder="员工信息丢失">
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">离职原因:</label>
							<div class="col-sm-10">
											<textarea id="lizhireason" name="lizhireason" rows="3" cols="20"></textarea>
							</div>
						</div>
						  <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">请勾选以确认离职</a>
                        </label>
                    </div>
			
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="staff_lizhi_btn" onclick="fn_lizhi_staff()">确认离职</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
	
	<!-- 退休模态框 -->
	<div class="modal fade" id="tuixiuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
							<label class="col-sm-2 control-label">员工编号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_id"
									id="tstaff_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_name"
									id="tstaff_name" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">性别：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_sex"
									id="tstaff_sex" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">所属公司：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tcm_id"
									id="tcm_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">所属部门：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tdp_id"
									id="tdp_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">岗位：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tps_id"
									id="tps_id" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
						
							<div class="form-group">
							<label class="col-sm-2 control-label">出生日期：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_born"
									id="tstaff_born" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">身份证号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_idnumber"
									id="tstaff_idnumber" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">入职时间：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_time"
									id="tstaff_time" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">联系电话：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_phone"
									id="tstaff_phone" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">照片：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="tstaff_pic"
									id="tstaff_pic" readonly="value"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">地址:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="tstaff_address" 
								id="tstaff_address" readonly="value" placeholder="员工信息丢失">
							</div>
						</div>
                         <div class="form-group">
							<label class="col-sm-2 control-label">就职状态:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="tstaff_status" 
								id="tstaff_status" readonly="value" placeholder="员工信息丢失">
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">退休原因:</label>
							<div class="col-sm-10">
											<textarea id="tlizhireason" name="tlizhireason" rows="3" cols="20"></textarea>
							</div>
						</div>
						  <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">请勾选以确认退休</a>
                        </label>
                    </div>
			
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="staff_tuixiu_btn" onclick="fn_tuixiu_staff()">确认退休</button>
				</div>
			</div>
		</div> 
	</div>

	<!-- 模组框结束-->
</body>
</html>