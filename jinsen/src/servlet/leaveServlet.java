package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinsen.bean.leave;
import jinsen.bean.leavetype;
import jinsen.dao.leaveDao;
import jinsen.daoreal.leaveImpl;



/**
 * Servlet implementation class leaveovertimeServlet
 */
@WebServlet("/leaveovertimeServlet")
public class leaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private leaveDao leaveovertimedao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public leaveServlet() {
		super();
		leaveovertimedao = new leaveImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		String action=request.getParameter("action");
		if(action.equals("typeadd"))//添加请假标准
		{
			String type=request.getParameter("types");
			String st=request.getParameter("stand");
			Double stand=Double.valueOf(st);
			PrintWriter out=response.getWriter();
			leavetype lea=new leavetype();
	    	lea.setLeavetype_type(type);
	    	lea.setLeavetype_stand(stand);
	    	int i=leaveovertimedao.inserttype(lea);
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
		else if(action.equals("typeselect"))//请假标准查询
		{
			try {
				List<leavetype> s=leaveovertimedao.gettypeSelect();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}
		else if(action.equals("typedelete"))//请假标准删除
	    {
				PrintWriter out=response.getWriter();
				String Id=request.getParameter("id");
				int id=Integer.parseInt(Id);
		    	int i=leaveovertimedao.typedelete(id);
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
		else if(action.equals("editselect"))//请假标准显示在动态框
		{
			try {
				PrintWriter out=response.getWriter();
				String Id=request.getParameter("id");
				int id=Integer.parseInt(Id);
				List<leavetype> s=leaveovertimedao.getidSelect(id);
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}
		else if(action.equals("updatetype"))//修改请假标准
	    {
				PrintWriter out=response.getWriter();
				String Id=request.getParameter("id");
				System.out.print(Id);
				int id=Integer.parseInt(Id);
				String types=request.getParameter("types");
				String stands=request.getParameter("stands");
				Double stand=Double.valueOf(stands);
				leavetype lea=new leavetype();
				lea.setLeavetype_id(id);
				lea.setLeavetype_type(types);
				lea.setLeavetype_stand(stand);
		    	int i=leaveovertimedao.updatetype(lea);
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
		else if(action.equals("qjselect"))//查询请假单
	    {
			try {
				PrintWriter out=response.getWriter();
				List<leave> s=leaveovertimedao.getqjSelect();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	    }
		else if(action.equals("leavedelete"))//请假标准删除
	    {
				PrintWriter out=response.getWriter();
				String Id=request.getParameter("id");
				int id=Integer.parseInt(Id);
		    	int i=leaveovertimedao.leavedelete(id);
				if(i>0)
				{
					out.write("true");
				}   
				else
				{
					out.write("false");
			}
		}
		else if(action.equals("leaveadd"))//添加请假标准
		{
			String id=request.getParameter("staff_id");
			String name=request.getParameter("staff_name");
			String time=request.getParameter("leave_time");
			String times=request.getParameter("leave_times");
			String reason=request.getParameter("leave_reason");
			String type=request.getParameter("leave_type");
			String state=request.getParameter("leave_state");
			Timestamp Time=Timestamp.valueOf(time);
			Timestamp Times=Timestamp.valueOf(times);
			PrintWriter out=response.getWriter();
			leave lea=new leave();
	    	lea.setStaff_id(id);
	    	lea.setStaff_name(name);
	    	lea.setLeave_time(Time);
	    	lea.setLeave_times(Times);
	    	lea.setLeave_reason(reason);
	    	lea.setLeave_type(type);
	    	lea.setLeave_state(state);
	    	int i=leaveovertimedao.leaveinsert(lea);
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
				List<leavetype> q=leaveovertimedao.typeSelect();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), q);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
	    }
		else if(action.equals("updateleave"))//修改请假标准
	    {
				PrintWriter out=response.getWriter();
				String Id=request.getParameter("id");
				int id=Integer.parseInt(Id);
				String time=request.getParameter("update_time");
				String times=request.getParameter("update_times");
				String reason=request.getParameter("update_reason");
				String type=request.getParameter("update_type");
				Timestamp Time=Timestamp.valueOf(time);
				Timestamp Times=Timestamp.valueOf(times);
				leave lea=new leave();
				lea.setLeave_id(id);
				lea.setLeave_time(Time);
				lea.setLeave_times(Times);
				lea.setLeave_reason(reason);
				lea.setLeave_type(type);
		    	int i=leaveovertimedao.updateleave(lea);
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
		else if(action.equals("selecteditor"))//请假单显示在动态框
		{
			try {
				PrintWriter out=response.getWriter();
				String Id=request.getParameter("id");
				int id=Integer.parseInt(Id);
				List<leave> s=leaveovertimedao.leavebyidSelect(id);
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}
}
}
