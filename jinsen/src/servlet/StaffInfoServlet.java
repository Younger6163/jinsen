package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinsen.bean.staff;
import jinsen.dao.StaffDao;
import jinsen.daoreal.StaffDaoImpl;




@WebServlet("/staffinfo")

public class StaffInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffDao staffdao = null;

	public StaffInfoServlet() {
		staffdao = new StaffDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String action = request.getParameter("action");
    	System.out.println(action);

            if(action.equals("addstaff")) {
        /*	System.out.println("addstaff，action接入完成,，尝试接入目标dao");
        	
            String id="123";
            
            String post_name=request.getParameter("post_name");
           	String staff_status=request.getParameter("staff_status");	
			String staff_name=request.getParameter("staff_name");
			String staff_sex=request.getParameter("staff_sex");
			String staff_born=request.getParameter("staff_born");
			String staff_province=request.getParameter("staff_province");
			String staff_city=request.getParameter("staff_city");
			String staff_county=request.getParameter("staff_county");
			String staff_address=request.getParameter("staff_address");
			String staff_idnumber=request.getParameter("staff_idnumber");
			String staff_time=request.getParameter("staff_time");
			String staff_phone=request.getParameter("staff_phone");

			//查找岗位的id作为staff_id
			
	      	 
			String sql=null;
		    sql="insert into staff(staff_id,staff_name,dp_id,staff_sex,cm_id,ps_id,staff_born,staff_province,staff_city,"
		    		+ "staff_county,staff_address,staff_idnumber,staff_time,staff_phone,staff_pic,staff_dangan) values"
		    		+ "('"+id+"','"+staff_name+"','"+dp_id+"','"+staff_sex+"','"+staff_company+"','"+post+"','"+born+"','"+sheng+"',"
		    				+ "'"+shi+"','"+xian+"','"+address+"','"+sfz+"', '"+rzsj+"','"+phone+"','"+zp+"','"+da+"')";
		    boolean ifadd=false;
			
			try {
				if(ifadd=staffdao.addStaff(sql)) {
				System.out.println("sql成功完成,，succeed");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				else {
					System.out.println("sql成功完成,，error");
				request.getRequestDispatcher("reg.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}*/
				
		}else if(action.equals("allstaff")) {
			System.out.println("staffServlet接入成功");
			List<staff> staff = new ArrayList<staff>();
			System.out.println("尝试接入数据访问层");
			try {
				staff = staffdao.allStaff();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staff);
			
		}else if(action.equals("getstaff")) {
			//获取某个员工的信息
			String staff_id = request.getParameter("staff_id");
			staff staff = new staff();
			try {
				staff = staffdao.getOneStaff(staff_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(),staff);
		}else if(action.equals("lizhi")) {
			//办理员工离职
        	System.out.println("lizhi离职，action接入完成,，尝试接入目标dao");

			String staff_id = request.getParameter("staff_id");
			String lizhireason=request.getParameter("lizhireason");
			String ps_id = request.getParameter("ps_id");

			String sql1=null;
			String sql2=null;
        	System.out.println(staff_id);
//修改员工就职状态
			 sql1="update staff set staff_status='已离职'  where staff_id="+staff_id+"";	
//记录员工离职情况
			 sql2="insert into inauguration(staff_id,it_reason,it_time,it_status,it_pic,admin_id,it_redate,it_oldpost) values('"+staff_id+"','"+lizhireason+"',now(),'已离职','暂无','员工id暂未接入',now(),'"+ps_id+"')";
		

			 boolean ifadd1=false;
			 boolean ifadd2=false;
				
				try {
					if(ifadd2=staffdao.addStaff(sql2)) {
					System.out.println("离职记录成功，正在修改员工就职状态，接入sql中");
					if(ifadd1=staffdao.addStaff(sql1)) {
						System.out.println("成功离职，就职状态已更变，员工已离职");
						PrintWriter out = response.getWriter();
						out.write("1");
					}
					}
					else {
						System.out.println("离职失败，error，sql语句未能成功完成，员工离职未能成功记录");
						PrintWriter out = response.getWriter();
						out.write("0");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if(action.equals("tuixiu")) {
			//办理员工离职
        	System.out.println("正在进行退休操作，action接入完成,，尝试接入目标dao");

			String staff_id = request.getParameter("staff_id");
			String lizhireason=request.getParameter("lizhireason");
			String ps_id = request.getParameter("ps_id");

			String sql1=null;
			String sql2=null;
        	System.out.println(staff_id);
//修改员工就职状态
			 sql1="update staff set staff_status='已退休'  where staff_id="+staff_id+"";	
//记录员工离职情况
			 sql2="insert into inauguration(staff_id,it_reason,it_time,it_status,it_pic,admin_id,it_redate,it_oldpost) values('"+staff_id+"','"+lizhireason+"',now(),'已退休','暂无','员工id暂未接入',now(),'"+ps_id+"')";
		

			 boolean ifadd1=false;
			 boolean ifadd2=false;
				
				try {
					if(ifadd2=staffdao.addStaff(sql2)) {
					System.out.println("退休记录成功，正在修改员工就职状态，接入sql中");
					if(ifadd1=staffdao.addStaff(sql1)) {
						System.out.println("成功退休，就职状态已更变，员工已离职");
						PrintWriter out = response.getWriter();
						out.write("1");
					}
					}
					else {
						System.out.println("退休失败，error，sql语句未能成功完成，员工离职未能成功记录");
						PrintWriter out = response.getWriter();
						out.write("0");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if(action.equals("update")) {
			//修改员工个人基本信息
        	System.out.println("修改员工基本信息，action接入完成,，尝试接入目标dao");
			String staff_id = request.getParameter("staff_id");
			String staff_name = request.getParameter("staff_name");
			String staff_sex = request.getParameter("staff_sex");
			String staff_born = request.getParameter("staff_born");
			String staff_province = request.getParameter("staff_province");
			String staff_city = request.getParameter("staff_city");
			String staff_county = request.getParameter("staff_county");
			String staff_address = request.getParameter("staff_address");
			String staff_idnumber = request.getParameter("staff_idnumber");
			String staff_time = request.getParameter("staff_time");
			String staff_phone = request.getParameter("staff_phone");
		//	String staff_pic = request.getParameter("staff_pic");
			//String staff_dangan = request.getParameter("staff_dangan");

			
			String sql=null;
        	System.out.println(staff_id);
//更新员工个人信息
			 sql="update staff set staff_name='"+staff_name+"',staff_sex='"+staff_sex+"',staff_born='"+staff_born+"',staff_province='"+staff_province+"',staff_city='"+staff_city+"',staff_county='"+staff_county+"',staff_address='"+staff_address+"',staff_idnumber='"+staff_idnumber+"',staff_time='"+staff_time+"',staff_phone='"+staff_phone+"' where staff_id='"+staff_id+"'";	
			 boolean ifadd=false;
				try {
					if(ifadd=staffdao.addStaff(sql)) {
						System.out.println("成功修改员工信息，员工信息已更变");
						PrintWriter out = response.getWriter();
						out.write("1");
					}
					else {
						System.out.println("发生SQL语句错误，SQL语句未能成功完成，员工信息修改未生效");
						PrintWriter out = response.getWriter();
						out.write("0");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
            
	}

	public void init() throws ServletException {
		System.out.println("服务器启动，servlet接入完成");


	}
}
