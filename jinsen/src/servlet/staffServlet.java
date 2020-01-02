package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinsen.bean.staff;
import jinsen.dao.usermanageDao;
import jinsen.daoreal.usermanageDaoImpl;



/**
 * Servlet implementation class staffServlet
 */
@WebServlet("/StaffServlet")
public class staffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static usermanageDao db=null;
    private usermanageDao db1=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public staffServlet() {
        super();
        db=new usermanageDaoImpl();
        db1=new usermanageDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/json"); 
		response.setContentType("textml;charset=UTF-8");
		String action=request.getParameter("action");
		System.out.println("in servlet");
		if(action.equals("all_staff"))
		{
			System.out.println("in all");
			List<staff> s=new ArrayList<staff>();
			s=db.find_allstaff();
//			for(shops s1:s)
//			{
//				System.out.println(s1.getName());//utf-8s
//			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), s);//返回一个JSON
		}
		else if(action.equals("delete_staff"))
		{
			System.out.println("in delete");
			String staff_id=request.getParameter("staff_id");
			
			System.out.println(staff_id);
			if(db.delete_staff(staff_id))
			{
				response.sendRedirect("usermanage.jsp");
			}
			else 
			{
				//response.sendRedirect("usermanage.jsp");
				System.out.println("fail");
			}
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/json"); 
		response.setContentType("textml;charset=UTF-8");
		String action=request.getParameter("action");
		System.out.println("in POST");
		if(action.equals("add_staff"))
		{
			System.out.println("dadadfadfadfas");
			String staff_id=request.getParameter("staff_id");
			String staff_name=request.getParameter("staff_name");
			String staff_sex=request.getParameter("staff_sex");
			String staff_born=request.getParameter("staff_born");
			//System.out.println(staff_born);
			String staff_department=request.getParameter("staff_department");
			String staff_post=request.getParameter("staff_post");
			String staff_province=request.getParameter("staff_province");
			String staff_city=request.getParameter("staff_city");
			String staff_county=request.getParameter("staff_county");
			String staff_address=request.getParameter("staff_address");
			String staff_idnumber=request.getParameter("staff_idnumber");
			String staff_time=request.getParameter("staff_time");
			String staff_company=request.getParameter("staff_company");
			String staff_phone=request.getParameter("staff_phone");
			String staff_pic=request.getParameter("staff_pic");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			Date d = null;
			try {
				d = format.parse(staff_born);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date Time = new java.sql.Date(d.getTime()); 
			Date t = null;
			try {
				t = format.parse(staff_time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date Times = new java.sql.Date(t.getTime()); 
//			java.sql.Date Time_born=new java.sql.Date(date_born.getTime());
//			java.sql.Date Time_time=new java.sql.Date(date_time.getTime());
			//System.out.println(id);
			staff s=new staff();
			s.setStaff_id(staff_id);
			s.setStaff_name(staff_name);
			s.setStaff_sex(staff_sex);
			s.setStaff_born(Time);
			s.setStaff_department(staff_department);
			s.setStaff_post(staff_post);
			s.setStaff_province(staff_province);
			s.setStaff_city(staff_city);
			s.setStaff_county(staff_county);
			s.setStaff_address(staff_address);
			s.setStaff_idnumber(staff_idnumber);
			s.setStaff_phone(staff_phone);
			s.setStaff_pic(staff_pic);
			s.setStaff_time(Times);
			s.setStaff_company(staff_company);
			if(db.add_staff(s))
			{
				PrintWriter out = response.getWriter();
				out.write("success");
			}
			else {
				PrintWriter out = response.getWriter();
				out.write("fail");
			}
		}
		else if(action.equals("delete_staff"))
		{
			System.out.println("in delete");
//			String staff_id=request.getParameter("row.ID");
			//String staff_id=request.getParameter("v");
			String staff_id=request.getParameter("staff_id");
			
			System.out.println(staff_id);
			if(db.delete_staff(staff_id))
			{
				response.sendRedirect("usermanage.jsp");
			}
			else 
			{
				//response.sendRedirect("usermanage.jsp");
				System.out.println("fail");
			}
		}
	}
	

}
