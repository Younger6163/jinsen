package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import jinsen.bean.attence;
import jinsen.bean.depart_performance;
import jinsen.bean.department;
import jinsen.bean.education;
import jinsen.bean.leavef;
import jinsen.bean.overtime;
import jinsen.bean.post;
import jinsen.bean.staff;
import jinsen.bean.staff_jobsalary;
import jinsen.bean.staff_performance;
import jinsen.bean.staff_performance_list;
import jinsen.bean.staff_salary_list;
import jinsen.dao.salary_inter;
import jinsen.daoreal.daoreal;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.biff.File;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private salary_inter sdao = new daoreal();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		JOptionPane.showMessageDialog(null, action);
		if(action.equals("salary_adjust_name"))
		{
			String name = request.getParameter("name");
			JOptionPane.showMessageDialog(null, name);
			System.out.println("name="+name);
			List<staff_jobsalary> staff_job = sdao.find_staff_salary_adjust_name(name);

			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staff_job);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		if(action.equals("find_staff_all"))
		{
			List<staff> staff = new ArrayList<staff>();
			
			staff = sdao.find_staff_all_nosalary();
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staff);
		}
		else if(action.equals("find_staff_id"))
		{
			String id = request.getParameter("id");
			List<staff> staff = new ArrayList<staff>();
			if(id.equals("")==false) {
			staff = sdao.find_staff_id(id);
			department d = sdao.find_depart_id(staff.get(0).getDp_id());
			staff.get(0).setDp_name(d.getDp_name());
			education e = sdao.find_education_all(id);
			staff.get(0).setStudy(e.getCertificate());
			post p = sdao.find_post_psid(staff.get(0).getPs_id());
			staff.get(0).setPs_name(p.getPs_name());
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staff);
		}
		else if(action.equals("find_staff_basicpay"))
		{
			String id = request.getParameter("id");
			Double basicpay = sdao.find_staff_basicpay(id);
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), basicpay);
		}
		else if(action.equals("sclass"))
		{
			String s = request.getParameter("salary");
			Double sa = Double.parseDouble(s);
			PrintWriter out = response.getWriter();
			if(sa > 0 && sa < 500)
				out.print("二级");
			else
				out.print("一级");
			
		}
		else if(action.equals("insert_staff_salary"))
		{
			int i = 0;
			String id = request.getParameter("id");
			String post = request.getParameter("post");
			Double salary = Double.parseDouble(request.getParameter("salary"));
			String admin_id = request.getParameter("admin_id");
			i = sdao.insert_staff_salary(id, post, salary, admin_id);
			PrintWriter out = response.getWriter();
			if(i > 0)
				out.print("录入成功！");
			else
				out.println("录入失败！");
		}
		else if(action.equals("update_salary_basicpay"))
		{
			String salary = request.getParameter("salary");
			String id = request.getParameter("id");
			HttpSession session=request.getSession();
			int i = sdao.update_salary_basicpay(Double.parseDouble(salary), id);
			
			PrintWriter out = response.getWriter();
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "调整薪资成功！");
				request.getRequestDispatcher("adjust_salary.jsp").forward(request, response);}
			else {
				JOptionPane.showMessageDialog(null, "调整薪资失败！");
				request.getRequestDispatcher("adjust_salary.jsp").forward(request, response);
				}
		}
		else if(action.equals("salary_all"))
		{
			
			List<staff_jobsalary> staff_job = sdao.find_staff_salary();

			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staff_job);
		}
		else if(action.equals("find_staff_salary_name"))
		{
			String name = request.getParameter("name");
			List<staff_jobsalary> staff_job = sdao.find_staff_salary_name(name);
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staff_job);
		}
		else if(action.equals("excel"))
		{
	            SmartUpload su = new SmartUpload();
	            su.initialize(this.getServletConfig(), request, response);
	            // 设定允许上传的文件（通过扩展名限制）
	            su.setAllowedFilesList("xls,xlsx");
	            try {
	                su.upload();
	                com.jspsmart.upload.Files files = su.getFiles();
	                String temp = "";
	                for (int i = 0; i < files.getCount(); i++) {
	                    com.jspsmart.upload.File file = files.getFile(i);
	                    temp = "C:\\Users\\94397\\Desktop\\大三上\\web\\upload\\staff.xls";//将上传的文件放在根目录下的upload文件夹下并命名为固定的文件名
	                    file.saveAs(temp, SmartUpload.SAVE_PHYSICAL);//将文件进行上传
	                }
	                System.out.println("上传成功!");
	                servlet es = new servlet();//创建自身对象;
	                es.getDate(request, response);//调用导入Excel数据的方法;

	                // ExcelToMySql(request, response);
	            } catch (SmartUploadException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
		}
		else if(action.equals("excel_perfor"))
		{
            SmartUpload su = new SmartUpload();
            su.initialize(this.getServletConfig(), request, response);
            // 设定允许上传的文件（通过扩展名限制）
            su.setAllowedFilesList("xls,xlsx");
            try {
                su.upload();
                com.jspsmart.upload.Files files = su.getFiles();
                String temp = "";
                for (int i = 0; i < files.getCount(); i++) {
                    com.jspsmart.upload.File file = files.getFile(i);
                    temp = "C:\\Users\\94397\\Desktop\\大三上\\web\\upload\\staff_perfor.xls";//将上传的文件放在根目录下的upload文件夹下并命名为固定的文件名
                    file.saveAs(temp, SmartUpload.SAVE_PHYSICAL);//将文件进行上传
                }
                System.out.println("上传成功!");
                servlet es = new servlet();//创建自身对象;
                es.getDate_perfor(request, response);//调用导入Excel数据的方法;

                // ExcelToMySql(request, response);
            } catch (SmartUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
		else if(action.equals("find_staff_salary_table"))
		{
			List<staff_jobsalary> staffjob = sdao.find_staff_salary_table();
			
			if(staffjob!=null) {
			for(int i =0;i<staffjob.size();i ++) {
				String adname = sdao.find_admin_name(staffjob.get(i).getAdmin_id());
				staffjob.get(i).setAdmin_name(adname);
			}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffjob);
		}
		else if(action.equals("js_buy_1"))
		{
			String staffsalary = request.getParameter("adjust_salary");
			String staffidstr = request.getParameter("staffidstr");
			String[] staffid = staffidstr.split(",");
			int i = 0;
			for(int y = 0;y < staffid.length;y ++)
			{
				i = sdao.update_staff_salary(staffid[y], Double.valueOf(staffsalary));
			}
			
			if(i > 0)
			{
				JOptionPane.showMessageDialog(null, "工资调整成功");
				request.getRequestDispatcher("adjust_salary.jsp").forward(request, response);
			}

		}
		else if(action.equals("find_staff_s"))
		{
			String staff_id = request.getParameter("staff_id");
			
			staff_jobsalary staffjob = sdao.find_jobsalary_sa(staff_id);
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffjob);
		}
		else if(action.equals("js_buy_2"))
		{
			String staff_id = request.getParameter("salaryid");
			String staff_salary = request.getParameter("ad_salary");
			
			int  i = sdao.update_staff_salary(staff_id, Double.valueOf(staff_salary));
			
			if(i > 0)
			{
				JOptionPane.showMessageDialog(null, "工资调整成功");
				request.getRequestDispatcher("adjust_salary.jsp").forward(request, response);
			}
		}
		else if(action.equals("salarylist_all"))
		{
			List<staff_salary_list> staffs = sdao.find_staff_salary_list();
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffs);
		}
		else if(action.equals("salarylist_all_m_y"))
		{
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			
			List<staff_salary_list> staffs = sdao.find_staff_salary_list_m_y(Integer.valueOf(year), Integer.valueOf(month));
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffs);
		}
		else if(action.equals("find_staff_salarylistl"))
		{
			String month = request.getParameter("month");
			String year = request.getParameter("year");

			List<staff_salary_list> salarylists = new ArrayList<staff_salary_list>();
			List<attence> attencelist = sdao.find_attence_month(Integer.valueOf(month));
			List<attence> attencelist_year = new ArrayList<attence>();
			if(attencelist!=null) {
			for(int i = 0;i<attencelist.size();i ++)//筛选出该年份和月份的考勤信息
			{
				Date d = attencelist.get(i).getAttence_date();
				Calendar calendar = Calendar.getInstance();  
			    calendar.setTime(d);  
			    int ayear = calendar.get(Calendar.YEAR);
				if(ayear == Integer.valueOf(year))
					attencelist_year.add(attencelist.get(i));
			}}
			
			List<staff_jobsalary> staffs = sdao.find_staff_salary();
			for(int i = 0;i<staffs.size();i ++)
			{
				staff_salary_list salarylist = new staff_salary_list();
				salarylist.setStaff_id(staffs.get(i).getStaff_id());
				double cqday = 0;
				for(int j = 0;j < attencelist_year.size(); j ++)
				{
					if(attencelist_year.get(j).getStaff_id().equals(staffs.get(i).getStaff_id()))
					{
						Date d1 = attencelist_year.get(j).getAttence_date();
						String dstr = d1.toString();
						Calendar calendar = Calendar.getInstance();  
					    calendar.setTime(d1);
					    int mon = calendar.get(Calendar.MONTH);
					    mon = mon + 1;
					    if(mon == Integer.valueOf(month))
					    {
					    	Timestamp m = Timestamp.valueOf(dstr+" 08:00:00");
					    	Timestamp m1 = Timestamp.valueOf(dstr+" 11:50:00");
					    	Timestamp n = Timestamp.valueOf(dstr+" 14:00:00");
					    	Timestamp n1 = Timestamp.valueOf(dstr+" 17:30:00");
					    	if((attencelist_year.get(j).getAttence_mwtime().before(m) || attencelist_year.get(j).getAttence_mwtime().equals(m)) && (attencelist_year.get(j).getAttence_mwtimes().after(m1) || attencelist_year.get(j).getAttence_mwtimes().equals(m1)) && (attencelist_year.get(j).getAttence_awtime().before(n) || attencelist_year.get(j).getAttence_awtime().equals(n)) && (attencelist_year.get(j).getAttence_awtimes().after(n1) || attencelist_year.get(j).getAttence_awtimes().equals(n1)))
						    	cqday = cqday + 1;
					    	else if(((attencelist_year.get(j).getAttence_mwtime().before(m) || attencelist_year.get(j).getAttence_mwtime().equals(m)) && (attencelist_year.get(j).getAttence_mwtimes().after(m1) || attencelist_year.get(j).getAttence_mwtimes().equals(m1))) || ((attencelist_year.get(j).getAttence_awtime().before(n) || attencelist_year.get(j).getAttence_awtime().equals(n)) && (attencelist_year.get(j).getAttence_awtimes().after(n1) || attencelist_year.get(j).getAttence_awtimes().equals(n1))))
					    		cqday = cqday + 0.5;
					    }
					}
				}
				salarylist.setStaff_salary_list_attendday(cqday);
				List<leavef> staff_leave = sdao.find_leave_staff_id(staffs.get(i).getStaff_id());
				double qjcs = 0;
				for(int i1 = 0;i1 <staff_leave.size();i1 ++)
				{
					
					Date leave_date = staff_leave.get(i1).getLeave_time();
					Calendar calendar = Calendar.getInstance();  
				    calendar.setTime(leave_date);
				    int leave_year = calendar.get(Calendar.YEAR);
				    int leave_mon = calendar.get(Calendar.MONTH);
				    leave_mon = leave_mon + 1;
				    String str = String.valueOf(calendar.get(Calendar.YEAR)) + "-" +String.valueOf(calendar.get(Calendar.MONTH)+1) +"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
			    	Date leave_date_1 = staff_leave.get(i1).getLeave_times();
			    	Calendar calendar1 = Calendar.getInstance();  
			    	calendar1.setTime(leave_date_1);
		    		String dstr = String.valueOf(calendar1.get(Calendar.YEAR)) + "-" +String.valueOf(calendar1.get(Calendar.MONTH)+1) +"-"+String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH));
				    int leave_year_1 = calendar1.get(Calendar.YEAR);
				    int leave_mon_1 = calendar1.get(Calendar.MONTH);
				    leave_mon_1 = leave_mon_1 + 1;
		    		
				    if(leave_year == Integer.valueOf(year) && leave_mon == Integer.valueOf(month) && leave_year_1 == Integer.valueOf(year) && leave_mon_1 == Integer.valueOf(month))
				    {

			    		
				    	Timestamp m = Timestamp.valueOf(dstr+" 08:00:00");
				    	Timestamp m1 = Timestamp.valueOf(dstr+" 11:50:00");
				    	Timestamp n = Timestamp.valueOf(dstr+" 14:00:00");
				    	Timestamp n1 = Timestamp.valueOf(dstr+" 17:30:00");
				    	if(calendar.get(Calendar.DAY_OF_MONTH) == calendar1.get(Calendar.DAY_OF_MONTH))
				    	{
				    		if(((staff_leave.get(i1).getLeave_time().after(m) || staff_leave.get(i1).getLeave_time().equals(m)) && staff_leave.get(i1).getLeave_time().before(m1)) && (staff_leave.get(i1).getLeave_times().before(n) || staff_leave.get(i1).getLeave_times().equals(n)))
				    			qjcs = qjcs + 0.5;
				    		else if(((staff_leave.get(i1).getLeave_time().after(m) || staff_leave.get(i1).getLeave_time().equals(m)) && staff_leave.get(i1).getLeave_time().before(m1)) && ((staff_leave.get(i1).getLeave_times().before(n1) || staff_leave.get(i1).getLeave_times().equals(n1)) && staff_leave.get(i1).getLeave_times().after(n)))
				    			qjcs = qjcs + 1;
				    		else if(staff_leave.get(i1).getLeave_time().after(n) || staff_leave.get(i1).getLeave_time().equals(n))
				    			qjcs = qjcs + 0.5;
				    	}
				    	else
				    	{
					    	Timestamp time_m = Timestamp.valueOf(str+" 08:00:00");
					    	Timestamp time_m1 = Timestamp.valueOf(str+" 11:50:00");
					    	Timestamp time_n = Timestamp.valueOf(str+" 14:00:00");
					    	Timestamp time_n1 = Timestamp.valueOf(str+" 17:30:00");
				    		if(staff_leave.get(i1).getLeave_time().equals(time_m))
				    		{
				    			if(staff_leave.get(i1).getLeave_times().equals(m))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
				    			else if(staff_leave.get(i1).getLeave_times().equals(m1) || staff_leave.get(i1).getLeave_times().equals(n))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) + 0.5;
				    			else if(staff_leave.get(i1).getLeave_times().equals(n1))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) + 1;
				    		}
				    		else if(staff_leave.get(i1).getLeave_time().equals(time_m1) || staff_leave.get(i1).getLeave_time().equals(time_n))
				    		{
				    			if(staff_leave.get(i1).getLeave_times().equals(m))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) - 0.5;
				    			else if(staff_leave.get(i1).getLeave_times().equals(m1) || staff_leave.get(i1).getLeave_times().equals(n))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
				    			else if(staff_leave.get(i1).getLeave_times().equals(n1))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) + 0.5;
				    		}
				    		else if(staff_leave.get(i1).getLeave_time().equals(time_n1))
				    		{
				    			if(staff_leave.get(i1).getLeave_times().equals(m))
				    				qjcs = 0;
				    			else if(staff_leave.get(i1).getLeave_times().equals(m1) || staff_leave.get(i1).getLeave_times().equals(n))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) + 0.5;
				    			else if(staff_leave.get(i1).getLeave_times().equals(n1))
				    				qjcs = calendar1.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
				    		}
				    	}
				    }
				}
				salarylist.setStaff_salary_list_offnum(qjcs);
				
				double absentn = 0.0;
				salarylist.setStaff_salary_list_absentnum(0);
				
				List<overtime> overtimes = sdao.find_overtime_id(staffs.get(i).getStaff_id());
				double jbts = 0.0;
				double money = 0.0;
				for(int i1 = 0;i1 <overtimes.size();i1 ++)
				{
					
					Date jb_date = overtimes.get(i1).getOvertime_time();
					Date jb_date_j = overtimes.get(i1).getOvertime_times();
					Calendar calendar = Calendar.getInstance();  
					Calendar calendar1 = Calendar.getInstance();  
				    calendar.setTime(jb_date);
				    calendar1.setTime(jb_date_j);
				    int jb_year_q = calendar.get(Calendar.YEAR);
				    int jb_mon_q = calendar.get(Calendar.MONTH);
				    jb_mon_q = jb_mon_q + 1;
				    int jb_year_j = calendar.get(Calendar.YEAR);
				    int jb_mon_j = calendar.get(Calendar.MONTH);
				    jb_mon_j = jb_mon_j + 1;
				    
				    if(jb_year_q == Integer.valueOf(year) && jb_mon_q == Integer.valueOf(month) && jb_year_j == Integer.valueOf(year) && jb_mon_j == Integer.valueOf(month))
				    {
				    	int day_q = calendar.get(Calendar.DAY_OF_MONTH);
				    	int day_j = calendar1.get(Calendar.DAY_OF_MONTH);
				    	int hour_q = calendar.get(Calendar.HOUR_OF_DAY);
				    	int hour_j = calendar1.get(Calendar.HOUR_OF_DAY);
				    	if(day_j - day_q > 0)
				    	{
				    		jbts = jbts + (day_j - day_q);
				    		double m = sdao.find_stand_type(overtimes.get(i1).getOvertime_type());
				    		money = money + (m * (day_j - day_q));
				    	}
				    	else
				    	{
				    		jbts = jbts + 0.5;
				    		double m = sdao.find_stand_type(overtimes.get(i1).getOvertime_type());
				    		money = money + 0.5*m;
				    	}
				    }
				}
				salarylist.setStaff_salary_list_overtime(jbts);
				salarylist.setStaff_salary_list_overtimesalary(money);
				double deductm = 0.0;
				deductm = qjcs * 50 + absentn * 100;
				salarylist.setStaff_salary_list_deductmoney(deductm);
				double ssa = 0.0;
				ssa = staffs.get(i).getStaff_jobsalary_basicpay() - deductm;
				salarylist.setStaff_salary_list_ssalary(ssa);
				salarylist.setStaff_salary_list_netpayroll(ssa);
				salarylist.setStaff_salary_list_residue(ssa);
				salarylist.setMonth(Integer.valueOf(month));
				salarylist.setYear(Integer.valueOf(year));
				salarylists.add(salarylist);
			}
			int count = sdao.insert_staff_salary_list(salarylists);
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), salarylists);
		}
		else if(action.equals("salarylist_all_wff"))
		{
			List<staff_salary_list> salarylist = sdao.find_staff_list_wff();
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), salarylist);
			
		}
		else if(action.equals("find_department"))
		{
			List<department> departmentlist = sdao.find_department_all();
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), departmentlist);
		}
		else if(action.equals("find_ready_salary"))
		{
			String year = request.getParameter("year");
			
			double ready_salary = sdao.find_ready_salary(Integer.valueOf(year));
			
			PrintWriter out = response.getWriter();
			out.print(ready_salary);
		}
		else if(action.equals("find_ready_salary_three"))
		{
			String year = request.getParameter("year");
			String department = request.getParameter("department");
			String month = request.getParameter("month");
			//department = new String(department.getBytes("ISO-8859-1"),"UTF-8");
			
			int mon = -1;
			if(month.equals("所有月份") == false)
				mon = Integer.valueOf(month);
			double ready_salary_three = sdao.find_ready_salary_three(Integer.valueOf(year), department, mon);
			
			PrintWriter out = response.getWriter();
			out.print(ready_salary_three);
		}
		else if(action.equals("ff_salary_1"))
		{
			String bl = request.getParameter("bl_salary");
			bl = bl.substring(0, bl.length()-1);
			String year = request.getParameter("year_salary");
			String department = request.getParameter("department_select");
			String month = request.getParameter("month_salary");
			month = new String(month.getBytes("ISO-8859-1"),"UTF-8");
			department = new String(department.getBytes("ISO-8859-1"),"UTF-8");
			int mon = -1;
			if(month.equals("所有月份") == false)
				mon = Integer.valueOf(month);
			
			List<staff_salary_list> stafflists = sdao.find_ready_salary_three_1(Integer.valueOf(year), department, mon);
			double bll = (double)Integer.valueOf(bl)/100;
			int count = 0;
			for(int i =0 ;i <stafflists.size();i ++)
			{
				double salary = 0.0;
				int flag = -1;
				if(bll == 1)
					{
						salary = stafflists.get(i).getStaff_salary_list_residue();
						flag = 1;
					}
				else
				{
					salary = stafflists.get(i).getStaff_salary_list_residue() * bll;
					flag = -1;
				}
				
				if(flag == 1) {
					count = sdao.update_salary_list_status(stafflists.get(i).getStaff_id(), Integer.valueOf(year), stafflists.get(i).getMonth(), "已发完");
				}
				else
					count = sdao.update_salary_list_status(stafflists.get(i).getStaff_id(), Integer.valueOf(year), stafflists.get(i).getMonth(), "未发完");
				
				count = sdao.update_salary(stafflists.get(i).getStaff_id(), Integer.valueOf(year),mon ,salary, flag);
			}
			if(count > 0)
				JOptionPane.showMessageDialog(null, "工资发放成功");
			request.getRequestDispatcher("paysalary.jsp").forward(request, response);
			
		}
		else if(action.equals("find_ready_salary_bl"))
		{
			String bl = request.getParameter("bl_salary");
			bl = bl.substring(0, bl.length()-1);
			
			String year = request.getParameter("year");
			String department = request.getParameter("department");
			String month = request.getParameter("month");
			int mon = -1;
			if(month.equals("所有月份") == false)
				mon = Integer.valueOf(month);
			
			List<staff_salary_list> stafflists = sdao.find_ready_salary_three_1(Integer.valueOf(year), department, mon);
			double count = 0.0;
			double bll = (double)Integer.valueOf(bl)/100;
			for(int i = 0;i<stafflists.size();i ++)
				count = count + (stafflists.get(i).getStaff_salary_list_residue() * bll);
			
			PrintWriter out = response.getWriter();
			out.print(count);
		}
		else if(action.equals("find_staff_perfor_all"))
		{
			String year = request.getParameter("year");
			List<staff> staffs = new ArrayList<staff>();
			staffs = sdao.find_staff_not_perfor(Integer.valueOf(year));
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffs);
		}
		else if(action.equals("find_staff_all_perfor"))
		{
			String id = request.getParameter("id");
			String year = request.getParameter("year");
			
			List<staff> staffs = sdao.find_staff_id(id);
			
			if(staffs!=null) {
			for(int i = 0;i<staffs.size();i ++)
			{
				department d = sdao.find_depart_id(staffs.get(i).getDp_id());
				staffs.get(i).setDp_name(d.getDp_name());
				post p =sdao.find_post_psid(staffs.get(i).getPs_id());
				staffs.get(i).setPs_name(p.getPs_name());
			}
			}
			int count = sdao.find_staff_work_num(id, Integer.valueOf(year));
			
			if(staffs!=null)
				staffs.get(0).setWork_num(count);
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffs);
		}
		else if(action.equals("perfor_submit"))
		{
			String adminid = request.getParameter("admin_id");
			String staffid = request.getParameter("staffid");
			String remark_perf = request.getParameter("remark_perf");
			String perfor_month = request.getParameter("perfor_month");
			String kpi = request.getParameter("kpi");
			String year = request.getParameter("year");
			remark_perf = new String(remark_perf.getBytes("ISO-8859-1"),"UTF-8");
			
			staff_performance staffperfor = new staff_performance();
			
			staffperfor.setStaff_id(staffid);
			staffperfor.setAdmin_id(adminid);
			staffperfor.setStaff_performance_kpi(Double.valueOf(kpi));
			staffperfor.setStaff_performance_remark(remark_perf);
			staffperfor.setStaff_performance_year(Integer.valueOf(year));
			staffperfor.setStaff_perfotmance_coefficient(Double.valueOf(perfor_month));
			
			int count = sdao.insert_staff_perfor(staffperfor);
			
			if(count > 0)
				JOptionPane.showMessageDialog(null, "录入成功");
			request.getRequestDispatcher("in_performance.jsp").forward(request, response);
		}
		else if(action.equals("find_depart_perfor_all"))
		{
			String year = request.getParameter("year");
			
			List<department> departs = sdao.find_depart(Integer.valueOf(year));
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), departs);
		}
		else if(action.equals("find_depart_message"))
		{
			String dp_name = request.getParameter("name");
			String year = request.getParameter("year");
			
			department d = sdao.find_depart_name(dp_name);
			
			double count = sdao.find_dp_perfor(dp_name,Integer.valueOf(year));
			
			d.setDp_perfor(count);
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), d);
		}
		else if(action.equals("dp_perfor_submit"))
		{
			String dp_id = request.getParameter("dp_id");
			String dp_perfor = request.getParameter("dp_perfor");
			String dp_kpi = request.getParameter("dp_kpi");
			String year = request.getParameter("year");
			String dp_perfor_score = request.getParameter("dp_perfor_zhi");
			String dp_remark = request.getParameter("dp_remark");
			dp_remark = new String(dp_remark.getBytes("ISO-8859-1"),"UTF-8");
			
			depart_performance dp = new depart_performance();
			
			dp.setDp_id(dp_id);
			dp.setDepart_performance(Double.valueOf(dp_perfor));
			dp.setDepart_kpi(Double.valueOf(dp_kpi));
			dp.setDepart_perfor_year(Integer.valueOf(year));
			dp.setDepart_perfor_score(Double.valueOf(dp_perfor_score));
			dp.setDepart_remark(dp_remark);
			
			int count = sdao.insert_dp_perfor(dp);
			
			if(count > 0)
				JOptionPane.showMessageDialog(null, "部门绩效信息录入成功");
			request.getRequestDispatcher("stat_department.jsp").forward(request, response);
		}
		else if(action.equals("find_dp_name"))
		{
			List<department> departs = sdao.find_department_all();
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), departs);
		}
		else if(action.equals("find_dp_performance_isall"))
		{
			String year = request.getParameter("year");
			
			List<department> departs = sdao.find_dp_performan_isall(Integer.valueOf(year));
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), departs);
		}
		else if(action.equals("find_depart_performance"))
		{
			String year = request.getParameter("year");
			String company_perfor = request.getParameter("company_perfor");
			List<depart_performance> dp_perf = sdao.find_depart_perfor(Integer.valueOf(year));
			if(dp_perf.get(0).getDepart_perfor_total() == 0) {
				
			List<department> departs = sdao.find_department_all();
			List<depart_performance> departlist = new ArrayList<depart_performance>();
			if(departs!=null) {
				double sum_depart_perfor_score = sdao.find_sum_depart_perfor_all(Integer.valueOf(year));
				double perfor_avg = Double.valueOf(company_perfor) / sum_depart_perfor_score;
				for(int i = 0;i<departs.size();i ++)
				{
					depart_performance depart_perfor = sdao.find_depart_perfor_name(departs.get(i).getDp_name(), Integer.valueOf(year));
					
					double depart_perfor_money = perfor_avg * depart_perfor.getDepart_perfor_score();
					
					int count = sdao.update_depart_perfor(depart_perfor_money, departs.get(i).getDp_name(), Integer.valueOf(year));
					
					List<staff> staffs = sdao.find_staff_dp(departs.get(i).getDp_id());
					if(staffs!=null) {
						double count_perfor = 0.0;
						double[] r = new double[staffs.size()];
						for(int j = 0;j<staffs.size();j ++)
						{
							int c = sdao.find_staff_work_num(staffs.get(j).getStaff_id(), Integer.valueOf(year));
							
							staff_performance staff_perfor = sdao.find_perfor_score_all(departs.get(i).getDp_id(), Integer.valueOf(year), staffs.get(j).getStaff_id());
							
							double staff_perfor_year = c * staff_perfor.getStaff_perfotmance_coefficient();
							double score_zhi = staff_perfor.getStaff_performance_kpi() * 10 / 100;
							
							r[j] = staff_perfor_year * score_zhi;
							count_perfor = count_perfor + r[j];
						}
						for(int u = 0;u < staffs.size(); u ++)
						{
							double staff_perfor_bl = 0.0;
							if(count_perfor != 0)
								staff_perfor_bl  = r[u] / count_perfor;
							
							double staff_perfor_money = staff_perfor_bl * depart_perfor_money;
							System.out.println("money="+staff_perfor_money);
							
							staff_performance_list staff_perfor_list = new staff_performance_list();
							staff_perfor_list.setStaff_id(staffs.get(u).getStaff_id());
							staff_perfor_list.setStaff_performance_list_residue(staff_perfor_money);
							staff_perfor_list.setStaff_performance_list_year(Integer.valueOf(year));
							staff_perfor_list.setAdmin_id("admin_id");
							staff_perfor_list.setStaff_performance_list_total(staff_perfor_money);
							
							int count1 = sdao.insert_staff_perforlist(staff_perfor_list);
							if(count1 == 0)
								System.out.println("fail");
						}
					}
				}
				departlist = sdao.find_depart_perfor(Integer.valueOf(year));
				if(departlist!=null) {
					for(int y = 0;y<departlist.size();y ++)
					{
						department d = sdao.find_depart_id(departlist.get(y).getDp_id());
						departlist.get(y).setDp_name(d.getDp_name());
					}
				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), departlist);}
			else
			{
				ObjectMapper map = new ObjectMapper();
				map.writeValue(response.getWriter(), dp_perf);
			}
		}
		else if(action.equals("find_dp_all"))
		{
			List<department> dp = sdao.find_department_all();
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dp);
		}
		else if(action.equals("find_staff_perfor_qr"))
		{
			String year = request.getParameter("year");
			String dp_name = request.getParameter("dp_name");
			dp_name = new String(dp_name.getBytes("ISO-8859-1"),"UTF-8");
			List<staff_performance_list> staff_perfor_list = sdao.find_staff_perforlist_dp(dp_name, Integer.valueOf(year));
			List<staff_performance> staff_perfor = sdao.find_staff_perfor_dp(dp_name, Integer.valueOf(year));
			List<staff> staffs = sdao.find_staff_dp_name(dp_name);
			
			if(staffs != null)
			{
				for(int i = 0;i<staffs.size();i ++)
				{
					staffs.get(i).setStaff_performance_coefficient(staff_perfor.get(i).getStaff_perfotmance_coefficient());
					staffs.get(i).setStaff_performance_kpi(staff_perfor.get(i).getStaff_performance_kpi());
					staffs.get(i).setStaff_performance_list_residue(staff_perfor_list.get(i).getStaff_performance_list_residue());
					staffs.get(i).setStaff_performance_list_total(staff_perfor_list.get(i).getStaff_performance_list_total());
					staffs.get(i).setStaff_performance_list_yetpay(staff_perfor_list.get(i).getStaff_performance_list_yetpay());
					staffs.get(i).setStaff_performance_remark(staff_perfor.get(i).getStaff_performance_remark());
					List<attence> a = sdao.find_attence_month(staffs.get(i).getStaff_id(), Integer.valueOf(year));
					int start = 0;
					int end = 0;
					int num = 0;
					String str = "";
					if(a!=null)
					{
						staffs.get(i).setWork_num(a.size());
						start = a.get(0).getAttence_month();
						for(int o = 1;o < a.size(); o ++)
						{
							System.out.println("size"+a.size());
							if((a.get(o).getAttence_month() - a.get(o-1).getAttence_month()) == 1)
								end = a.get(o).getAttence_month();
							else
							{
								num = num + 1;
								start = a.get(o).getAttence_month();
								if(num == 1)
									str = str + String.valueOf(start) + "-" + String.valueOf(end);
								else
									str = str + "," + String.valueOf(start) + "-" + String.valueOf(end);
							}
							if(o == a.size() - 1)
							{
								num = num + 1;
								if(num == 1)
									str = str + String.valueOf(start) + "-" + String.valueOf(end);
								else
									str = str + "," + String.valueOf(start) + "-" + String.valueOf(end);
							}
						}
						if(a.size()==1)
							str = String.valueOf(start);
					}
					str = str + "月份";
					staffs.get(i).setWork_month(str);
					
					post p = sdao.find_post_psid(staffs.get(i).getPs_id());
					staffs.get(i).setPs_name(p.getPs_name());
				}
			}
			
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), staffs);
		}
		else if(action.equals("find_staff_performance_list_all"))
		{
			List<staff> stafflist = new ArrayList<staff>();
			List<staff_performance_list> staff_perfor_list = sdao.find_staff_performance_list_all();
			if(staff_perfor_list!=null)
			{
				for(int i = 0;i<staff_perfor_list.size(); i ++)
				{
					List<staff> staffs = sdao.find_staff_id(staff_perfor_list.get(i).getStaff_id());
					List<staff_performance> staff_perfor = sdao.find_staff_perf_all(staff_perfor_list.get(i).getStaff_id(), staff_perfor_list.get(i).getStaff_performance_list_year());
					staffs.get(0).setStaff_performance_coefficient(staff_perfor.get(0).getStaff_perfotmance_coefficient());
					staffs.get(0).setStaff_performance_kpi(staff_perfor.get(0).getStaff_performance_kpi());
					staffs.get(0).setStaff_performance_list_residue(staff_perfor_list.get(i).getStaff_performance_list_residue());
					staffs.get(0).setStaff_performance_list_total(staff_perfor_list.get(i).getStaff_performance_list_total());
					staffs.get(0).setStaff_performance_list_yetpay(staff_perfor_list.get(i).getStaff_performance_list_yetpay());
					staffs.get(0).setStaff_performance_remark(staff_perfor.get(0).getStaff_performance_remark());
					List<attence> a = sdao.find_attence_month(staff_perfor_list.get(i).getStaff_id(), staff_perfor_list.get(i).getStaff_performance_list_year());
					int start = 0;
					int end = 0;
					int num = 0;
					String str = "";
					if(a!=null)
					{
						staffs.get(0).setWork_num(a.size());
						start = a.get(0).getAttence_month();
						for(int o = 1;o < a.size(); o ++)
						{
							if((a.get(o).getAttence_month() - a.get(o-1).getAttence_month()) == 1)
								end = a.get(o).getAttence_month();
							else
							{
								num = num + 1;
								start = a.get(o).getAttence_month();
								if(num == 1)
									str = str + String.valueOf(start) + "-" + String.valueOf(end);
								else
									str = str + "," + String.valueOf(start) + "-" + String.valueOf(end);
							}
							if(o == a.size() - 1)
							{
								num = num + 1;
								if(num == 1)
									str = str + String.valueOf(start) + "-" + String.valueOf(end);
								else
									str = str + "," + String.valueOf(start) + "-" + String.valueOf(end);
							}
						}
						if(a.size()==1)
							str = String.valueOf(start);
					}
					str = str + "月份";
					staffs.get(0).setWork_month(str);
					
					post p = sdao.find_post_psid(staffs.get(0).getPs_id());
					staffs.get(0).setPs_name(p.getPs_name());
					stafflist.add(staffs.get(0));
				}
				ObjectMapper map = new ObjectMapper();
				map.writeValue(response.getWriter(), stafflist);
			}
		}
		else if(action.equals("find_ready_perfor"))
		{
			String year = request.getParameter("year");
			double count = sdao.find_ready_perfor(Integer.valueOf(year));
			
			PrintWriter out = response.getWriter();
			out.print(count);
		}
		else if(action.equals("find_ready_perfor_depart"))
		{
			String year = request.getParameter("year");
			String department = request.getParameter("department");
			
			double count = sdao.find_ready_perfor_depart_year(Integer.valueOf(year), department);
			System.out.println(count);
			PrintWriter out = response.getWriter();
			out.print(count);
		}
		else if(action.equals("find_ready_perfor_depart_year_bl"))
		{
			String year = request.getParameter("year");
			String department = request.getParameter("department");
			String bl = request.getParameter("bl_perfor");
			double count = sdao.find_ready_perfor_depart_year(Integer.valueOf(year), department);
			bl = bl.substring(0, bl.length()-1);
			double bll = (double)Integer.valueOf(bl) / 100;
			double result = bll * count;
			
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		else if(action.equals("ff_perfor_1"))
		{
			String year = request.getParameter("year_perfor");
			String department = request.getParameter("department_select");
			String bl = request.getParameter("bl_perfor");
			bl = bl.substring(0, bl.length()-1);
			double bll = (double)Integer.valueOf(bl) / 100;
			department = new String(department.getBytes("ISO-8859-1"),"UTF-8");
			List<staff_performance_list> perforlist = sdao.find_payperfor_list(Integer.valueOf(year), department);
			int y = 0;
			if(perforlist!=null)
			{
				for(int i = 0;i <perforlist.size();i ++)
				{
					double money = perforlist.get(i).getStaff_performance_list_residue() * bll;

					if(perforlist.get(i).getStaff_performance_list_residue() != 0)
					{
						if(money == perforlist.get(i).getStaff_performance_list_residue())
							y = sdao.update_perfor_list_status("已发完", perforlist.get(i).getStaff_id(), Integer.valueOf(year));
						else
							y = sdao.update_perfor_list_status("未发完", perforlist.get(i).getStaff_id(), Integer.valueOf(year));
						y = sdao.update_perfor_list_id(perforlist.get(i).getStaff_id(), Integer.valueOf(year), money);
					}
				}
				if(y > 0)
					JOptionPane.showMessageDialog(null, "绩效发放成功");
				else
					JOptionPane.showMessageDialog(null, "绩效发放失败");
			}
			request.getRequestDispatcher("payperfor.jsp").forward(request, response);
		}
		else if(action.equals("salary_month"))
		{
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			
			List<department> dp = sdao.find_dp_name_all();
			List<department> dplist = new ArrayList<department>();
			if(dp != null)
			{
				for(int i = 0;i<dp.size();i ++)
				{
					department d = sdao.find_salary_month_table(Integer.valueOf(year), Integer.valueOf(month), dp.get(i).getDp_name());
					d.setDp_name(dp.get(i).getDp_name());
					List<staff> staff = sdao.find_staff_dp(dp.get(i).getDp_id());
					if(staff!=null)
						d.setStaff_num(staff.size());
					if(d.getSalary_all() == d.getSalary_yetpay() && d.getSalary_all()!=0)
						d.setStaff_status("已发完");
					else if(d.getSalary_all() > d.getSalary_yetpay() && d.getSalary_yetpay() != 0)
						d.setStaff_status("未发完");
					else if(d.getSalary_yetpay() == 0 && d.getSalary_all()!=0)
						d.setStaff_status("未发放");
					else
						d.setStaff_status("-");
					
					dplist.add(d);
				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
		}
		else if(action.equals("salary_year"))
		{
			String year = request.getParameter("year");

			List<department> dp = sdao.find_dp_name_all();
			List<department> dplist = new ArrayList<department>();
			if(dp != null)
			{
				for(int i = 0;i<dp.size();i ++)
				{
					department d = sdao.find_salary_year_table(Integer.valueOf(year), dp.get(i).getDp_name());
					d.setDp_name(dp.get(i).getDp_name());
					List<staff> staff = sdao.find_staff_dp(dp.get(i).getDp_id());
					if(staff!=null)
						d.setStaff_num(staff.size());
					
					if(d.getSalary_all() == d.getSalary_yetpay() && d.getSalary_all()!=0)
						d.setStaff_status("已发完");
					else if(d.getSalary_all() > d.getSalary_yetpay() && d.getSalary_yetpay() != 0)
						d.setStaff_status("未发完");
					else if(d.getSalary_yetpay() == 0 && d.getSalary_all()!=0)
						d.setStaff_status("未发放");
					else
						d.setStaff_status("-");
					
					dplist.add(d);
				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
		}
		
		
		
	    }
	    public void getDate(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String errMessage = null;
	        int flag=0;

	        List liststu = new ArrayList();
	        // 找到上传后的文件目录,并创建IO流
	        java.io.File f = new java.io.File("C:\\Users\\94397\\Desktop\\大三上\\web\\upload\\staff.xls");
	        
	        InputStream is = new FileInputStream(f);
	        try {
	            // 创建工作簿
	            Workbook wb = Workbook.getWorkbook(is);
	            
	            // 创建工作表
	            jxl.Sheet sheet = wb.getSheet(0);
	            String content = null;
	            for (int i = 1; i < sheet.getRows(); i++) {
	                staff_jobsalary staff = new staff_jobsalary();
	                for (int j = 0; j < sheet.getColumns(); j++) {
	                    content = sheet.getCell(j, i).getContents();
	                    // System.out.print(content);
	                    if (staff.getStaff_id()== null)//
	                    {
	                        staff.setStaff_id(sheet.getCell(j, i).getContents());
	                        continue;
	                    }
	                    if (staff.getStaff_post() == null) {
	                        staff.setStaff_post(sheet.getCell(j, i).getContents());
	                        continue;
	                    }
	                    if (staff.getStaff_jobsalary_basicpay() == null) {
	                        staff.setStaff_jobsalary_basicpay(Double.parseDouble(sheet.getCell(j, i).getContents()));
	                        continue;
	                    }
	                    if (staff.getAdmin_id() == null) {
	                        staff.setAdmin_id(sheet.getCell(j, i).getContents());
	                        continue;
	                    }
	                    if (staff.getStaff_jobsalary_changetime() == null) {
	                    	
	                    	Format f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                        Date d = null;
							try {
								d = ((DateFormat) f1).parse(sheet.getCell(j, i).getContents());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        Timestamp ts = new Timestamp(d.getTime());
	                        staff.setStaff_jobsalary_changetime(ts);
	                        continue;
	                    }
	                }
	                flag=sdao.insert_staff_salary(staff.getStaff_id(), staff.getStaff_post(), staff.getStaff_jobsalary_basicpay(), staff.getAdmin_id());
	            }
	            /*
	             * 文件导入成功后进入提示界面
	             */
	            if(flag>0){
	                System.out.println("总表导入成功!");
	                JOptionPane.showMessageDialog(null, "导入成功！");
	                request.getRequestDispatcher("in_salary.jsp").forward(request,
	                        response);
	            }else{
	                System.out.println("总表未导入成功!");
	                JOptionPane.showMessageDialog(null, "导入失败！");
	                request.getRequestDispatcher("in_salary.jsp").forward(request,
	                        response);
	            }
	        } catch (BiffException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    
	    public void getDate_perfor(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String errMessage = null;
	        int flag=0;

	        List liststu = new ArrayList();
	        // 找到上传后的文件目录,并创建IO流
	        java.io.File f = new java.io.File("C:\\Users\\94397\\Desktop\\大三上\\web\\upload\\staff_perfor.xls");
	        
	        InputStream is = new FileInputStream(f);
	        try {
	            // 创建工作簿
	            Workbook wb = Workbook.getWorkbook(is);
	            
	            // 创建工作表
	            jxl.Sheet sheet = wb.getSheet(0);
	            String content = null;
	            for (int i = 1; i < sheet.getRows(); i++) {
	                staff_performance staff = new staff_performance();
	                for (int j = 0; j < sheet.getColumns(); j++) {
	                    content = sheet.getCell(j, i).getContents();
	                    // System.out.print(content);
	                    if (staff.getStaff_id()== null)//
	                    {
	                        staff.setStaff_id(sheet.getCell(j, i).getContents());
	                        continue;
	                    }
	                    if (staff.getStaff_performance_remark() == null) {
	                        staff.setStaff_performance_remark(sheet.getCell(j, i).getContents());
	                        continue;
	                    }
	                    if (staff.getStaff_perfotmance_coefficient()==0) {
	                        staff.setStaff_perfotmance_coefficient(Double.parseDouble(sheet.getCell(j, i).getContents()));
	                        continue;
	                    }
	                    if (staff.getStaff_performance_kpi() == 0) {
	                        staff.setStaff_performance_kpi(Double.parseDouble(sheet.getCell(j, i).getContents()));
	                        continue;
	                    }
	                    if (staff.getStaff_performance_year() == 0) {
	                        staff.setStaff_performance_year(Integer.parseInt(sheet.getCell(j, i).getContents()));
	                        continue;
	                    }
	                    if (staff.getStaff_performance_changetime() == null) {
	                    	
	                    	Format f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                        Date d = null;
							try {
								d = ((DateFormat) f1).parse(sheet.getCell(j, i).getContents());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        Timestamp ts = new Timestamp(d.getTime());
	                        staff.setStaff_performance_changetime(ts);
	                        continue;
	                    }
	                    if (staff.getAdmin_id()== null) {
	                        staff.setAdmin_id(sheet.getCell(j, i).getContents());
	                        continue;
	                    }
	                }
	                flag=sdao.insert_staff_perfor(staff);
	            }
	            /*
	             * 文件导入成功后进入提示界面
	             */
	            if(flag>0){
	                System.out.println("总表导入成功!");
	                JOptionPane.showMessageDialog(null, "导入成功！");
	                request.getRequestDispatcher("in_performance.jsp").forward(request,
	                        response);
	            }else{
	                System.out.println("总表未导入成功!");
	                JOptionPane.showMessageDialog(null, "导入失败！");
	                request.getRequestDispatcher("in_performance.jsp").forward(request,
	                        response);
	            }
	        } catch (BiffException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	    }
	}

