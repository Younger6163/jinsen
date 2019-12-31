package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinsen.bean.overtime;
import jinsen.bean.overtimetype;
import jinsen.dao.overtimeDao;
import jinsen.daoreal.overtimeImpl;



/**
 * Servlet implementation class overtime
 */
@WebServlet("/overtime")
public class overtimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private overtimeDao overtimedao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public overtimeServlet() {
        super();
        overtimedao = new overtimeImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		String action=request.getParameter("action");
		if(action.equals("typeselect"))//加班标准查询
		{
			try {
				List<overtimetype> s=overtimedao.gettypeSelect();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}
		else if(action.equals("typeadd"))//加班请假标准
		{
			String type=request.getParameter("types");
			String st=request.getParameter("stand");
			Double stand=Double.valueOf(st);
			PrintWriter out=response.getWriter();
			overtimetype ove=new overtimetype();
			ove.setOvertimetype_stand(stand);
			ove.setOvertimetype_type(type);
	    	int i=overtimedao.inserttype(ove);
	    	System.out.print(i);
			if(i>0)
			{
				//request.getRequestDispatcher("addinf.jsp").forward(request, response);
				out.write("true");
			}
			else
			{
				//System.out.println("123");
				out.write("false");
			}
		}
	else if(action.equals("typedelete"))//加班标准删除
    {
			PrintWriter out=response.getWriter();
			String Id=request.getParameter("id");
			int id=Integer.parseInt(Id);
	    	int i=overtimedao.typedelete(id);
			if(i>0)
			{
				out.write("true");
			}   
			else
			{
				out.write("false");
		}
	}
	else if(action.equals("editselect"))//加班标准显示在动态框
	{
		try {
			PrintWriter out=response.getWriter();
			String Id=request.getParameter("id");
			int id=Integer.parseInt(Id);
			List<overtimetype> s=overtimedao.getidSelect(id);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	else if(action.equals("updatetype"))//修改加班标准
    {
			PrintWriter out=response.getWriter();
			String Id=request.getParameter("id");
			System.out.print(Id);
			int id=Integer.parseInt(Id);
			String types=request.getParameter("types");
			String stands=request.getParameter("stands");
			Double stand=Double.valueOf(stands);
			overtimetype ove=new overtimetype();
			ove.setOvertimetype_id(id);
			ove.setOvertimetype_type(types);
			ove.setOvertimetype_stand(stand);
	    	int i=overtimedao.updatetype(ove);
			if(i>0)
			{
				out.write("true");
			}   
			else
			{
				//System.out.println("123");
				out.write("false");
		}
	}
	else if(action.equals("jbselect"))//查询加班信息
    {
		try {
			PrintWriter out=response.getWriter();
			List<overtime> s=overtimedao.getqjSelect();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
    }
	else if(action.equals("overtimedelete"))//请假标准删除
    {
			PrintWriter out=response.getWriter();
			String Id=request.getParameter("id");
			int id=Integer.parseInt(Id);
	    	int i=overtimedao.overtimedelete(id);
			if(i>0)
			{
				out.write("true");
			}   
			else
			{
				out.write("false");
		}
	}
	else if(action.equals("overtimeadd"))//添加加班
	{
		String id=request.getParameter("staff_id");
		String name=request.getParameter("staff_name");
		String time=request.getParameter("overtime_time");
		String times=request.getParameter("overtime_times");
		String reason=request.getParameter("overtime_reason");
		String type=request.getParameter("overtime_type");
		String state=request.getParameter("overtime_state");
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date d = null;
		try {
			d = format.parse(time);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Date Time = new java.sql.Date(d.getTime()); */
		Timestamp Time=Timestamp.valueOf(time);
		Timestamp Times=Timestamp.valueOf(times);
		/*Date t = null;
		try {
			t = format.parse(times);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Date Times = new java.sql.Date(t.getTime()); */
		PrintWriter out=response.getWriter();
		overtime ove=new overtime();
		ove.setStaff_id(id);
		ove.setStaff_name(name);
		ove.setOvertime_time(Time);
		ove.setOvertime_times(Times);
    	ove.setOvertime_reason(reason);
    	ove.setOvertime_type(type);
    	ove.setOvertime_state(state);
    	int i=overtimedao.overtimeinsert(ove);
    	System.out.print(i);
		if(i>0)
		{
			//request.getRequestDispatcher("addinf.jsp").forward(request, response);
			out.write("true");
		}
		else
		{
			//System.out.println("123");
			out.write("false");
		}
	}
	else if(action.equals("qjselecttype"))//查询请假类型
    {
		try {
			PrintWriter out=response.getWriter();
			List<overtimetype> q=overtimedao.typeSelect();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}	
    }
	else if(action.equals("updateovertime"))//修改加班标准
    {
			PrintWriter out=response.getWriter();
			String Id=request.getParameter("id");
			int id=Integer.parseInt(Id);
			String time=request.getParameter("update_time");
			String times=request.getParameter("update_times");
			String reason=request.getParameter("update_reason");
			String type=request.getParameter("update_type");
			/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = null;
			try {
				d = format.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date Time = new java.sql.Date(d.getTime()); 
			Date t = null;
			try {
				t = format.parse(times);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date Times = new java.sql.Date(t.getTime()); */
			Timestamp Time=Timestamp.valueOf(time);
			Timestamp Times=Timestamp.valueOf(times);
			overtime lea=new overtime();
			lea.setOvertime_id(id);
			lea.setOvertime_time(Time);
			lea.setOvertime_times(Times);
			lea.setOvertime_reason(reason);
			lea.setOvertime_type(type);
	    	int i=overtimedao.updateovertime(lea);
			if(i>0)
			{
				out.write("true");
			}   
			else
			{
				//System.out.println("123");
				out.write("false");
		}
	}
	else if(action.equals("selecteditor"))//加班信息显示在动态框
	{
		try {
			PrintWriter out=response.getWriter();
			String Id=request.getParameter("id");
			int id=Integer.parseInt(Id);
			List<overtime> s=overtimedao.overtimebyidSelect(id);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}
	
}

