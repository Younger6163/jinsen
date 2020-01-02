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

import jinsen.bean.Position;
import jinsen.dao.PositionDao;
import jinsen.daoreal.PositionDaoImpl;




@WebServlet("/position")

public class PositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PositionDao positiondao = null;

	public PositionServlet() {
		positiondao = new PositionDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 	    //解决中文乱码问题
		    request.setCharacterEncoding("utf-8");
		    response.setContentType("text/html;charset=UTF-8");

		    String action = request.getParameter("action");
    	    System.out.println(action);
            if(action.equals("allposition")) {
			System.out.println("PositionServlet接入成功");
			List<Position> position = new ArrayList<Position>();
			System.out.println("尝试接入数据访问层");
			try {
				position = positiondao.allPosition();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), position);
		}else if(action.equals("getposition")){
			//获取某个职位的信息
			String all_id = request.getParameter("all_id");
			Position position = new Position();
			try {
				position = positiondao.getOnePosition(all_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(),position);
		}else if(action.equals("updateposition")) {
			
        	System.out.println("职位信息修改，action接入完成,，尝试接入目标dao");

			String all_id = request.getParameter("all_id");
			String cm_name=request.getParameter("cm_name");
			String dp_name = request.getParameter("dp_name");
			String ps_name = request.getParameter("ps_name");
			String cm_id=request.getParameter("cm_id");
			String dp_id = request.getParameter("dp_id");
			String ps_id = request.getParameter("ps_id");
			String sql1=null;
			String sql2=null;
			String sql3=null;
        	System.out.println(all_id);
            //修改员工就职状态
			 sql1="update company set cm_name='"+cm_name+"' where cm_id='"+cm_id+"'";	
			 sql2="update department set dp_name='"+dp_name+"' where dp_id='"+dp_id+"' and cm_id='"+cm_id+"'";	
			 sql3="update post set ps_name='"+ps_name+"' where all_id='"+all_id+"'";	
			 boolean ifadd1=false;
			 boolean ifadd2=false;
			 boolean ifadd3=false;
				try {
					if(ifadd1=positiondao.addPosition(sql3)) {
					    System.out.println("修改公司信息成功");
					}
					if(ifadd2=positiondao.addPosition(sql2)){
					    System.out.println("修改部门信息成功");
						}
					if(ifadd3=positiondao.addPosition(sql1)){
					        System.out.println("修改岗位信息成功");
						   
						}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 PrintWriter out = response.getWriter();
					out.write("1");
		}else if(action.equals("delposition")) {
			//删除职位信息
        	System.out.println("职位信息修改，action接入完成,，尝试接入目标dao");
			String all_id = request.getParameter("all_id");
			String sql=null;
        	System.out.println(all_id);
            //修改员工就职状态
			 sql="DELETE FROM post WHERE all_id = '"+all_id+"'";	
			 boolean ifadd=false;
				try {
					if(ifadd=positiondao.addPosition(sql)) {
						System.out.println("成功");
					PrintWriter out = response.getWriter();
					out.write("1");
					}
					else {
						System.out.println("失败");
						PrintWriter out = response.getWriter();
						out.write("0");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}else if(action.equals("addposition")) {
			//增加岗位信息信息
        	System.out.println("增加岗位信息，action接入完成,，尝试接入目标dao");
			String dp_name = request.getParameter("dp_name");
			String ps_name = request.getParameter("ps_name");
			String sql=null;
        	System.out.println(dp_name);
        	System.out.println(ps_name);
        	//读取dp_id
			 String dp_id="";
			 try {
				dp_id=positiondao.getDp_id(dp_name);
				//已解决dp_id的问题
				System.out.println("dp_id"+dp_id);
				
				//解决ps_id的问题,自动递增
				String ps_id="";
				int intps_id;
				ps_id=positiondao.getPs_id();
				intps_id=Integer.valueOf(ps_id);
				System.out.println("ps_id"+ps_id);
				if(intps_id<=9) {
					intps_id=intps_id+1;
					ps_id="0"+intps_id;
				}else {
					ps_id=String.valueOf(intps_id+1);
				}
				System.out.println("ps_id"+ps_id);
				//已解决ps_id的问题
				
				//解决cm_id的问题
				String cm_id="";
				cm_id=positiondao.getCm_id(dp_name);
				System.out.println("cm_id"+cm_id);
				//已解决cm_id的问题
				
				//合并生成代码
				String position="";
				position=cm_id+dp_id+ps_id;
				System.out.println("position:"+position);
				
			     //新增岗位信息
			    sql="insert into post(ps_id,ps_name,dp_id,all_id) values('"+ps_id+"','"+ps_name+"','"+dp_id+"','"+position+"')";
			    boolean ifadd=false;
			    if(ifadd=positiondao.addPosition(sql)) {
			    	System.out.println("岗位新增成功");
					  PrintWriter out = response.getWriter();
					  out.write("1"); 
			    }
				else
					  {
					  System.out.println("失败"); 
					  PrintWriter out = response.getWriter();
					  out.write("0");
					  }
  	
						
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}else if(action.equals("adddepartment")) {
		//增加部门信息信息
    	System.out.println("增加岗位信息，action接入完成,，尝试接入目标dao");
		String dp_name = request.getParameter("dp_name");
		String cm_name = request.getParameter("cm_name");
		String sql=null;
    	System.out.println(dp_name);
    	System.out.println(cm_name);
    	//读取cm_id
		 String dp_id="";
		 try {
			dp_id=positiondao.getDp_id_dp();
			//解决dp_id的问题，自动递增
			int intdp_id=Integer.valueOf(dp_id);
			if(intdp_id<=9) {
				intdp_id=intdp_id+1;
				dp_id="0"+intdp_id;
			}else {
				dp_id=String.valueOf(dp_id+1);
			}
			System.out.println("dp_id"+dp_id);

			//已解决dp_id问题
			
			//解决cm_id问题
			String cm_id="";	
			cm_id=positiondao.getCm_id_cm(cm_name);
			System.out.println("cm_id"+cm_id);
			//已解决cm_id的问题
            dp_name=dp_name+"("+cm_id+dp_id+")";
		     //新增部门信息
		    sql="insert into department(dp_id,dp_name,cm_id) values('"+dp_id+"','"+dp_name+"','"+cm_id+"')";
		    boolean ifadd=false;
		    if(ifadd=positiondao.addPosition(sql)) {
		    	System.out.println("岗位新增成功");
				  PrintWriter out = response.getWriter();
				  out.write("1"); 
		    }
			else
				  {
				  System.out.println("失败"); 
				  PrintWriter out = response.getWriter();
				  out.write("0");
				  }
	
					
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}else if(action.equals("addcompany")) {
	//增加部门信息信息
	System.out.println("增加公司信息，action接入完成,，尝试接入目标dao");
	String cm_name = request.getParameter("cm_name");
	String sql=null;
	System.out.println(cm_name);
	//读取cm_id
	 String cm_id="";
	 try {
		 cm_id=positiondao.getCm_id_end();
		//解决cm_id的问题，自动递增
		int intcm_id=Integer.valueOf(cm_id);
			cm_id=String.valueOf(intcm_id+1);

		System.out.println("dp_id"+cm_id);
		//已解决cm_id问题

	     //新增部门信息
	    sql="insert into company(cm_id,cm_name) values('"+cm_id+"','"+cm_name+"')";
	    boolean ifadd=false;
	    if(ifadd=positiondao.addPosition(sql)) {
	    	System.out.println("新增成功");
			  PrintWriter out = response.getWriter();
			  out.write("1"); 
	    }
		else
			  {
			  System.out.println("失败"); 
			  PrintWriter out = response.getWriter();
			  out.write("0");
			  }

				
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}else if(action.equals("allcompany")) {
	System.out.println("PositionServlet接入成功");
	List<Position> position = new ArrayList<Position>();
	System.out.println("尝试接入数据访问层");
	try {
		position = positiondao.allcompany();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ObjectMapper map = new ObjectMapper();
	map.writeValue(response.getWriter(), position);
}else if(action.equals("alldpa")) {
	System.out.println("PositionServlet接入成功");
	String cm_name = request.getParameter("cm_name");
	System.out.println(cm_name);
	List<Position> position = new ArrayList<Position>();
	System.out.println("尝试接入数据访问层");
	try {
		String cm_id=positiondao.getCm_id_cm(cm_name);
		System.out.println("公司ID："+cm_id);
		position = positiondao.alldp(cm_id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ObjectMapper map = new ObjectMapper();
	map.writeValue(response.getWriter(), position);
}else if(action.equals("alldps")) {
	System.out.println("PositionServlet接入成功");
	String dp_name = request.getParameter("dp_name");
	System.out.println(dp_name);
	List<Position> position = new ArrayList<Position>();
	System.out.println("尝试接入数据访问层");
	try {
		String dp_id=positiondao.getDp_id(dp_name);
		position = positiondao.allps(dp_id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ObjectMapper map = new ObjectMapper();
	map.writeValue(response.getWriter(), position);
}
	}


			


	public void init() throws ServletException {
		System.out.println("服务器启动，Positionservlet接入完成");


	}
}
