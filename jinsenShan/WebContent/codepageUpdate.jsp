<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>主页</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="css/style.css" rel='stylesheet' type='text/css' />

    <link href="css/font-awesome.css" rel="stylesheet">
    <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <style>
    body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 18px;color: #1d1007; line-height:24px}
    </style>
    <link href="css/registe.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
    </style>
    
<script type="text/javascript">
var k=100;

function aaad()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">树材种<span></span>"
          +"<select style='width: 180px' name='treetype' id='sss"+k+"'>"
           +"<option>--请选择--</option>"
          +"<option value='shan'>--杉原木--</option>"
          +"<option value='song'>--松原木--</option>"
          +"<option value='za'>--杂原木--</option></select>"
        +"检尺长(m)<span></span><input type='text' style='width: 180px' name='tdouble' id='td"+k+"'>检尺径<span></span>"
          +"<input type='text' style='width: 180px' name='tradius' id='tr"+k+"'>根数<span></span><input type='text' style='width: 180px' name='num' id='n"+k+"'>"
             +"材积(m^3、T、根)<span></span><input type='text' style='width: 180px' name='tvolume' id='tv"+k+"' onclick='vvvolume("+k+")'></td></tr>";
             k=k+1;
            $("#ttt5").append(str); 
             
}
function dddelete()
{
	var ck= $("input[type='checkbox']:checked");
	for (var i=0;i<ck.length;i++)
		{
		     var j=$(ck[i]).val();
		     alert(j);
		     $("#"+j+"").empty();
		}
}
function deleteAll()
{
	var mk=$("input[type='checkbox']:checked");
	var index=$("input[type='checkbox']:not(:checked)");
	for (var i=0;i<mk.length;i++)
    {
	    var h=$(mk[i]).val();
	    
	     $("#"+h+"").empty();
	}
   for (var a=0;a<index.length;a++)
	{
	    var hh=$(index[a]).val();
	    $("#"+hh+"").empty();
	}
}
function vvvolume(id)
{
	var td=$("#td"+id+"").val();
	var tr=$("#tr"+id+"").val();
	var num=$("#n"+id+"").val();
	if(num!="" && td!="" && tr!="")
	{
	$.ajax({
        url:"codepageSevrlet",
        data:{
            "action":"volume",
            "td":td,
            "tr":tr 
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	document.getElementById("tv"+id+"").value=data*num;
        }
    })}
}
function makecount()
{
	var mk=$("input[type='checkbox']:checked");
	var index=$("input[type='checkbox']:not(:checked)");
	var totalt;
	var totalst;
	var total;
	var tot;
	for (var i=0;i<mk.length;i++)
    {
	     var h=$(mk[i]).val();
	     totalst+=document.getElementById("tv"+h+"").value;
		 totalt+=document.getElementById("n"+h+"").value;
	}
   for (var a=0;a<index.length;a++)
	{
	    var hk=$(index[a]).val();
	    total+=document.getElementById("tv"+hk+"").value;
	    tot+=document.getElementById("n"+hk+"").value;
	}
   document.getElementById("toltree").value=(totalt+tot);
   document.getElementById("tolstere").value=(totalst+total);
}
function treeAdd()
{
	var map={};
	var kk=0,ki=0;
	var codeid=$("#cccodeid").val();
	if(codeid=="")
		{
		alert("请先输入码单号！");
		}
	else{
    $("input[type='checkbox']:checked").each(function(i){
    	var group=[];
    	var id=$(this).val();
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("td"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("n"+id+"").value;
    	    group[4]=document.getElementById("tv"+id+"").value;
    	    if(grounp[0]==""|| grounp[1]==""|| grounp[2]=="" || grounp[3]==""|| grounp[4]=="")
    	    	{
    	    	alert("请将信息填写完整！");
    	    	}
    	      else{
               map[id]=group;
               kk=i+1;
    	    }
    });
    $("input[type='checkbox']:not(:checked)").each(function(j){
    	var group=[];
    	var id=$(this).val();
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("td"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("n"+id+"").value;
    	    group[4]=document.getElementById("tv"+id+"").value;
    	    if(grounp[0]==""|| grounp[1]=="" ||grounp[2]==""||grounp[3]==""||grounp[4]=="")
	    	{
	    	alert("请将信息填写完整！");
	    	}
	          else{
        map[id]=group;
        ki=j+1;
	    }
    }); 
    var mymap=JSON.stringify(map);
    $.ajax({
        url:"codepageSevrlet",
        data:{
            "action":"treeAdd",
            "newtree":mymap,
            "id":ki+kk,
            "codeid":codeid,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	if(data>0)
        		{
        	        alert("添加成功！");
        		}
        }
    })
    }
}
function inputNull(form){
	for(i=0;i<form.length;i++){
            //form属性的elements的首字母e要小写
		if(form.elements[i].value == ""){ 
			//alert("" + form.elements[i].placeholder + "不能为空");
			alert("" + form.elements[i].placeholder + "不能为空");
			form.elements[i].focus();	
			return false;
		}
	}
	}
</script>
<title>码单查看</title>
</head>
<body>
<% codepage c=(codepage)request.getAttribute("codepage"); 
   List<tree> t=null;
   t=(List<tree>)request.getAttribute("tree");
%>
<main class="all">

    <article class="artlce">
        <header class="aside-head">
            <ul class="nav-ul">
                <li class="active-li"><a href="index.html" target="fraName">码单录入</a> </li>
                <li><a href="writeshenhe.html" target="fraName">码单审核</a> </li>
                <li><a href="laowu.html" target="fraName">劳务结算单</a> </li>
                <li><a href="xiaoshou.html" target="fraName">销售结算单</a> </li>
                <li><a href="tongji.html" target="fraName">统计报表</a> </li>
            </ul>
        </header>

        <div class="book_con01">
            <h1 class="book_h01">码单录入</h1>
            <form  action="codepageSevrlet?action=updateCodepage" method="POST" onSubmit="return inputNull(this)">
                <div class="top">
                    <div>
                        <div class="top_out">
                            <table class="table">
                                <tbody>
                                <p class="table_p"><span>信息一</span></p>
                                <tr>
                                    <td>码单号<span></span></td>
                                    <td>
                                        <input type="text" name="codeid" id="cccodeid" value="<%=c.getCodeid()%>" placeholder="码单号">
                                    </td>
                                    <td>发货单位<span></span></td>
                                    <td>
                                        <input type="text" name="shipping" id="ssshipping" value="<%=c.getShipping()%>" placeholder="发货单位">
                                    </td>
                                    <td>收货单位<span></span></td>
                                    <td>
                                        <input type="text" name="takein" id="tttakein" value="<%=c.getTakein()%>" placeholder="收货单位">
                                    </td>
                                    <td>林权单位<span></span></td>
                                    <td>
                                         <input type="text" name="command" id="cccomand" value="福建金森公司"  placeholder="林权单位">
                                    </td>
                                </tr>
                                <tr>
                                <td>采伐证号<span></span></td>
                                    <td>
                                        <input type="text" name="cutnum" id="cccutnum" value="<%=c.getCutNum()%>" placeholder="采伐证号">
                                    </td>
                                    <td>标品号<span></span></td>
                                    <td>
                                        <input type="text" name="checknum" id="ccchecknum" value="<%=c.getCheckNum()%>" placeholder="标品号">
                                    </td>
                                    <td>检验地点<span></span></td>
                                    <td>
                                        <input type="text" name="checksite" id="ccchecksite" value="<%=c.getCheckSite()%>" placeholder="检验地点">
                                    </td>
                                    <td>委托人<span></span></td>
                                    <td>
                                        <input type="text" name="entrust" id="eeentrust" value="<%=c.getEntrust()%>"  placeholder="委托人">
                                    </td>
                                </tr>
                                <tr>
                                    <td>伐区管理单位<span></span></td>
                                    <td>
                                        <input type="text" name="manageunit" id="mmmanageunit" value="<%=c.getManageUnit()%>" placeholder="伐区管理单位">
                                    </td>
                                    <td>分管领导<span></span></td>
                                    <td>
                                        <input type="text" name="leader" id="llleader" value="<%=c.getLeader()%>" placeholder="分管领导">
                                    </td>
                                    <td>生产经营部负责人<span></span></td>
                                    <td>
                                        <input type="text" name="procharge" id="ppprocharge" value="<%=c.getProcharge()%>"  placeholder="生产经营部负责人">
                                    </td>
                                </tr>
                                <tr>
                                 <td>劳务承包人<span></span></td>
                                    <td>
                                        <input type="text" name="forperson" id="ffforperson" value="<%=c.getForperson()%>" placeholder="劳务承包人">
                                    </td>
                                  <td>中标人<span></span></td>
                                    <td>
                                        <input type="text" name="getperson" id="gggetperson" value="<%=c.getGetPerson()%>" placeholder="中标人">
                                    </td>
                               <td>供货地点<span></span></td>
                                    <td>
                                        <input type="text" name="shipplace" id="ssshipplace" value="<%=c.getShipplace()%>" placeholder="供货地点">
                                    </td>
                                    <td>终端购货人<span></span></td>
                                    <td>
                                        <input type="text" name="finbuyer" id="fffinbuyer" value="<%=c.getFinbuyer()%>" placeholder="终端购货人">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <table class="table">
                                <tbody>
                                <p class="table_p"><span>对应表</span></p>
                                <tr>
                                    <td>劳务表号<span></span></td>
                                    <td>
                                        <input type="text" name="workid" id="wwworkid" value="<%=c.getWorkid()%>" placeholder="劳务表号">
                                    </td>
                                    <td>结算单号<span></span></td>
                                    <td>
                                        <input type="text" name="countid" id="cccountid" value="<%=c.getCountid()%>" placeholder="结算单号">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <table class="table" id="mytable">
                                <tbody>
                                <p class="table_p"><span>有效时间</span></p>
                                <tr>

                                    <td>开始时间<span></span></td>
                                    <td><input type="date" value="2020-01-01" id="begindate" name="begindate" value="<%=c.getBegintime()%>" placeholder="开始时间"/></td>
                                    <td>结束时间<span></span></td>
                                    <td><input type="date" value="2020-01-01" id="enddate" name="enddate" value="<%=c.getEndtime()%>" placeholder="结束时间"></td>
                               <td>车(船)号<span></span></td>
                                    <td><input type="text" name="transportnum" id="tttrransportnum" value="<%=c.getTransportNum()%>" placeholder="车(船)号"></td>
                                </tr>

                                </tbody>
                            </table>
                               <p class="table_p"><span>树材信息录入</span></p>
                            <table class="table" id="table5" style="width:1500px;height:auto">
                                <tbody id="ttt5">
                                <% int i=1;%>
                                <c:forEach items="${tree}" var="b">
                            <tr id="<%=i%>" display:block;><td style='font-size:20px;'>
                           <input type="checkbox" style='width:20px;height:20px;' value="<%=i%>">树材种<span></span>
                            <select style="width: 180px" name="treetype" id="sss<%=i%>">
                            <option>--请选择--</option>
                            <option value='shan'>--杉原木--</option>
                            <option value='song'>--松原木--</option>
                            <option value='za'>--杂原木--</option></select>
                            检尺长(m)<input type='text' style='width: 180px' name='tdouble' id='td<%=i%>' value="${b.getTlong()}">检尺径<span></span>
                            <input type='text' style='width: 180px' name='tradius' id='tr<%=i%>' value="${b.getTradius()}">根数<span></span>
                             <input type='text' style='width: 180px' name='num' id='n<%=i%>' value="${b.getNum()}">
                                            材积(m^3、T、根)<span></span>
                         <input type='text' style='width: 180px' name='tvolume' id='tv<%=i%>' value="${b.getTvolume()}"></td></tr>
                         <%i++; %>
                         </c:forEach>
                                </tbody>
                            </table>
                            <table>
                            <tr>
               <div style="float:left;"><button class="btn btn-warning" type="button"  onclick="aaad()" value="添加">添加</button>
               <button class="btn btn-default" type="button"  onclick="dddelete()" value="删除">删除</button>
          <button class="btn btn-default" type="button"  onclick="deleteAll()" value="清空">清空</button>
          <button class="btn btn-default" type="button"  onclick="treeAdd()" value="提交">提交</button>
          </div>
               </tr>
                        </table>     
                            <table class="table" >
                                <tbody>
                                <p class="table_p" style="margin-top: 65px;"><span>合计信息</span></p>
                                <tr >
                                    <td style="font-size:20px;width:auto">合计树材<span></span>
                                        <input type="text" style="width:120px; font-size:20px" name="toltreesmall" id="toltree" value="<%=c.getTolTree()%>" onclick="makecount()">
                                    根(块、件)<span></span></td>
                                    <td style="font-size:20px">合计树材<span></span>
                                        <input type="text" style="width:120px; font-size:20px" name="tolsteresmall" id="tolstere" value="<%=c.getTolStere()%>">
                                   立方米(吨、根)<span></span></td>
                                </tr>
                                </tbody>
                            </table>
                            <table class="table" >
                            <p class="table_p"><span>审核人员信息</span></p>
                                <tbody>
                                <tr>
                                <td>复核员1号<span></span></td>
                                   <td><input type="text" name="checkone" id="cccheckone" value="<%=c.getStaff_id_1()%>" placeholder="复核员1号"></td>
                                    <td>复核员2号<span></span></td>
                                   <c:if test="${c.getStaff_id_2()==0} "><td><input type="text" name="checktwo" id="ccchecktwo" value="" disabled="disabled"></td></c:if>
                                   <c:if test="${c.getStaff_id_2()!=0} "><td><input type="text" name="checktwo" id="ccchecktwo" value="<%=c.getStaff_id_2()%>" disabled="disabled"></td></c:if>
                                    <td>录入员<span></span></td>
                                    <td><input type="text" name="write" id="wwwrite" value="<%=c.getStaff_id_3()%>"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class=" but_p" style="float:left;"><button class="but_save" type="submit" id="mybutton" value="更新码单">更新码单</button>
                <button class="but_save" type="button" value="返回" onclick="javascrtpt:window.location.href='index.html'">返回添加</button></div>
                <div style="clear: both;padding-bottom: 40px"></div>
            </form>
        </div>
    </article>
</main>
</body>
</html>