<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工人事信息维护</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">

</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>员工信息管理</span></div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 该页面是修改员工信息页面！主要实现修改员工个人信息</p>
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
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var  d = '<button  id="update" data-id="98"  class="btn btn-xs btn-primary" onclick="fn_getUserUpdate(\''+row.staff_id+'\')">修改信息</button> ';
                        return  d;
                    }
                }
            ]
        });
    }
    //进行离职操作,根据id获取指定用户
    function fn_getUserUpdate(id){
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
  			$('#updateiModal').modal({
  				backdrop:"static"		
  			});
  		},
  		error:function(){
  			alert("失败！");
  		}
  	 });
  }
    

  //用户修改确认按钮
  function fn_update_staff(){
  	//发送ajax，更新用户信息
  	 var id = document.getElementById('staff_id').value;
 	 var staff_name = document.getElementById('staff_name').value;
   	 var staff_sex = document.getElementById('staff_sex').value;
  	 var staff_born = document.getElementById('staff_born').value;
  	 var staff_province = document.getElementById('staff_province').value;
  	 var staff_city = document.getElementById('staff_city').value;
  	 var staff_county = document.getElementById('staff_county').value;
  	 var staff_address = document.getElementById('staff_address').value;
  	 var staff_idnumber = document.getElementById('staff_idnumber').value;
  	 var staff_time = document.getElementById('staff_time').value;
  	 var staff_phone = document.getElementById('staff_phone').value; 
   	// var staff_pic = document.getElementById('staff_pic').value;
  	// var staff_dangan = document.getElementById('staff_dangan').value;   
  	 alert("正在对"+id+"进行操作");
     var type="update";
  	 $.ajax({
  		url:"staffinfo",
  		type:"POST",
  		data:{"action":type,"staff_id":id,"staff_name":staff_name,
  			"staff_sex":staff_sex,"staff_born":staff_born,"staff_province":staff_province,"staff_city":staff_city,
  			"staff_county":staff_county,"staff_address":staff_address,"staff_idnumber":staff_idnumber,"staff_time":staff_time,
  			"staff_phone":staff_phone},
  		dataType:"json",
  		success:function(data){
  			if(data==1){
  		    alert(id+"已经成功修改");
  			$("#updateModal").modal('hide');
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
</script>
	<!-- 离职模态框 -->
	<div class="modal fade" id="updateiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
									id="staff_name"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">性别：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_sex"
									id="staff_sex"   placeholder="员工信息丢失"> 					
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
									id="staff_born" placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">身份证号：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_idnumber"
									id="staff_idnumber"   placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">入职时间：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_time"
									id="staff_time"  placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">联系电话：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_phone"
									id="staff_phone"   placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">照片：</label>
							<div class="col-sm-6"> 
                        <input type="text" class="form-control" name="staff_pic"
									id="staff_pic"   placeholder="员工信息丢失"> 					
						    </div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">省:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_province" 
								id="staff_province"  placeholder="员工信息丢失">
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">市:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_city" 
								id="staff_city"  placeholder="员工信息丢失">
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">县:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_county" 
								id="staff_county"  placeholder="员工信息丢失">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">详细地址:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_address" 
								id="staff_address"  placeholder="员工信息丢失">
							</div>
						</div>
                         <div class="form-group">
							<label class="col-sm-2 control-label">就职状态:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="staff_status" 
								id="staff_status" readonly="value" placeholder="员工信息丢失">
							</div>
						</div>			
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="staff_update_btn" onclick="fn_update_staff()">确认修改</button>
				</div>
			</div>
		</div> 
	</div>
	<!-- end -->
	<!-- 模组框结束-->
</body>
</html>