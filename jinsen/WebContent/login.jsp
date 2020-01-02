<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Loding font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

    <!-- Custom Styles -->
    <link rel="stylesheet" type="text/css" href="css/styles.css">
	 <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/moment.js"></script>
    <title>Login</title>
    <script type="text/javascript">
    function login()
    {

    	var staff_id=document.getElementById('staff_id').value;
    	var password=document.getElementById('password').value;
    	//var par="action="+login+"&username="+username+"&password="+password;
    	//alert("66666");
    	 $.ajax({
    	        url:  "managerServlet",
    	        type: "POST",
    	        data:{
    	            "staff_id":staff_id,
    	            "action":"login",
    	            "password":password
    	        },
    	        dataType:"text",
    	        success : function (data) {
    	        	alert(data);
    	        	if(data=="fail")
    	        		{
    	        		
    	        		alert("用户名或密码错误");
    	        		window.location.href='login.jsp';
    	        		}
    	        	else if(data=="supermanager")
    	        		{
    	               alert("超级管理员登录成功");
    	               window.location.href='managerindex.jsp';   
    	        		}
    	        	else if(data=="manager")
    	        		{
    	        		 alert("管理员登录成功");
      	               window.location.href='index.html';
    	        		}
    	        },
    	        error:function(e){
    	        	alert("dsds");
    	        }
    	    })
    }
    
    </script>
    <script src="js/jquery.js"></script>
	<script src="js/jquery.singlePageNav.min.js"></script>
	<script src="js/bstable/js/bootstrap.min.js"></script>
	<script src="js/bstable/js/bootstrap-table.js"></script>
	<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="js/province.js"></script>
	<script src="js/wow.min.js"></script>
  </head>
  <body>

    <!-- Backgrounds -->

    <div id="login-bg" class="container-fluid">

      <div class="bg-img"></div>
      <div class="bg-color"></div>
    </div>

    <!-- End Backgrounds -->

    <div class="container" id="login">
        <div class="row justify-content-center">
        <div class="col-lg-8">
          <div class="login">

           
            <h1>欢迎进入金森管理系统</h1>
            <!-- Loging form -->
                 
                    <div class="form-group">
                      <input type="text" class="form-control" id="staff_id"  placeholder="请输入员工号" style="width:500px; height:20px;"></div>
                    <div class="form-group">
                      <input type="password" class="form-control" id="password" placeholder="请输入密码" style="width:500px; height:20px;"></div>

                      <div class="form-check">
                      <label class="switch">
                      <input type="checkbox">
                      <!--span class="slider round"></span-->
                    </label>
                      <!--label class="form-check-label" for="exampleCheck1">记住密码</label-->
                      <!--label class="form-check-label" for="">记住密码</label-->
                      <label class="forgot-password"><a href="#">忘记密码</a></label>
                      <!-- label class="forgot-password"><a href="#">忘记密码<a></label-->

                    </div>
                  
                    <br>
                    <!-- button type="submit" class="btn btn-lg btn-block btn-success">确认登录</button-->
                    <button  class="btn btn-lg btn-block btn-success" id="yes" onclick="login()" style="width:400px; height:50px;">确认登录</button>
                 
             <!-- End Loging form -->

          </div>
        </div>
        </div>
    </div>


  </body>
</html>