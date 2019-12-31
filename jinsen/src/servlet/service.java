package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinsen.bean.academic_title;
import jinsen.bean.education;
import jinsen.bean.train;
import jinsen.daoreal.dao_academic;
import jinsen.daoreal.dao_education;
import jinsen.daoreal.dao_train;



/**
 * Servlet implementation class service
 */
@WebServlet("/service")
public class service extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static dao_education dao_edu;
    public service() {
        super();
        dao_edu=new dao_education();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");	
		
		String action=request.getParameter("action");
		
		
		 if(action.equals("select")) {
			List <education>lis=new ArrayList<education>();
			dao_education edu=new dao_education();
			lis=edu.find_all();
			ObjectMapper mapper = new ObjectMapper();
	    	mapper.writeValue(response.getWriter(),lis);
	    	
		 }
		 else if(action.equals("add_education")) {
			 String staff_id=request.getParameter("name");
			 String enroll=request.getParameter("start");
			 String end=request.getParameter("end");
			 String end_kind=request.getParameter("end_kind");
			 String manage=request.getParameter("manage");
			 String place=request.getParameter("place");
			 String certificate=request.getParameter("certificate");
			 try {
				 long s=Long.valueOf(enroll);//入学日期的long类型
				 long e=Long.valueOf(end);//毕业日期的long类型
				 System.out.print("日期：");
				 //System.out.print(l);
		 
				 
				 SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
				 Date start=new Date(s);
				 Date end_time=new Date(e);
				 long now=System.currentTimeMillis();
				 Date nowtime=new Date(now);
				 education edu=new education();
   			     edu.setStaff_id(staff_id);
   			     edu.setEnrollment_time(start);
                 edu.setGraduation_time(end_time);
                 edu.setCertificate(end_kind);
                 edu.setGraduation_unit(place);
                 edu.setStaff_certificate(certificate);
                 edu.setInput_time(nowtime);
                 edu.setManager(manage);
                 boolean b=dao_edu.insert_education(edu);
                 ObjectMapper mapper = new ObjectMapper();
         	     mapper.writeValue(response.getWriter(),b); 
                 System.out.print(now);
			 }
			catch(Exception e)
			 {
				e.printStackTrace();
			 }
			 finally {
				 
			 }
			 boolean b=false;
			 ObjectMapper mapper=new ObjectMapper();
			 mapper.writeValue(response.getWriter(),b);	 
		 }
		 else if(action.equals("certificate"))
		 {
			    List <academic_title>lis=new ArrayList<academic_title>();
				dao_academic ac=new dao_academic();
				lis=ac.findall();
				ObjectMapper mapper = new ObjectMapper();
		    	mapper.writeValue(response.getWriter(),lis);
		    	//System.out.println(lis.get(0).getCertificate_of_title());
		 }
		 else if(action.equals("add_certificate"))
		 {
			 boolean b=false;
			 String staff_id=request.getParameter("staff_id1");
			 System.out.print(staff_id);
			 String gain_time=request.getParameter("gain_time");
			 String infect_time=request.getParameter("effect");
			 String positional_titles=request.getParameter("positional_titles");
			 String certificate_of_title=request.getParameter("certificate_of_title");
			 long gain=Long.valueOf(gain_time);
			 long infect=Long.valueOf(infect_time);
			 Date g1=new Date(gain);
			 Date infect1=new Date(infect);
			 long now=System.currentTimeMillis();
			 Date now1=new Date(now);
			 academic_title ac=new academic_title();
			 ac.setStaff_id(staff_id);
			 ac.setGain_time(g1);
			 ac.setInfect_time(infect1);
			 ac.setInput_time(now1);
			 ac.setProfession_titles(positional_titles);
			 ac.setCertificate_of_title(certificate_of_title);
			 ac.setManager("小猴");
			 dao_academic dao=new dao_academic();
			 b=dao.insert_academic(ac);
			 ObjectMapper mapper=new ObjectMapper();
			 mapper.writeValue(response.getWriter(),b);	
			 //"staff_id1":staff_id1,"gain_time":gain_time,"effect":effect_time,"positional_titles":positional_titles,"certificate_of_title"
		 }
		 else if(action.equals("train"))
		 {
			 List<train> lis=new ArrayList<train>();
			 dao_train train=new dao_train();
			 lis=train.find_all();
			 
			 ObjectMapper mapper=new ObjectMapper();
			 mapper.writeValue(response.getWriter(),lis);
			 
		 }
		 else if(action.equals("add_train"))
		 {
			 String staff_id=request.getParameter("staff_id1");
			 String train_name=request.getParameter("train_name");
			 String start_time=request.getParameter("start_time");
			 String end_time=request.getParameter("end_time");
			 String train_certificate=request.getParameter("train_certificate");
			 String manager="小王";
			 long l=Long.valueOf(start_time);
			 long e=Long.valueOf(end_time);
			 Date train_start_time=new Date(l);
			 Date train_end_time=new Date(e);
			 long n=System.currentTimeMillis();
			 Date now=new Date(n);
			 train tr=new train();
			dao_train dao=new dao_train();
			tr.setStaff_id(staff_id);
			tr.setTrain_name(train_name);
			tr.setTrain_start_time(train_start_time);
			tr.setTrain_end_time(train_end_time);
			tr.setTrain_certificate(train_certificate);
			tr.setManager(manager);
			tr.setInput_time(now);
			boolean b=dao.insert_train(tr);
			ObjectMapper mapper=new ObjectMapper();
			mapper.writeValue(response.getWriter(),b);
			 
		 }
		 else if(action.equals("delete_zhicheng"))
		 {
			
//			   String staff = request.getParameter("staff_id");
//			    String date=request.getParameter("date");
//	        	JSONArray json=JSONArray.fromObject(staff); 
//	        	JSONArray dat=JSONArray.fromObject(date);
			 String group = request.getParameter("group");
			 String datestr = request.getParameter("date");
			 
			 String[] id = group.split(",");
			 String[] date = datestr.split(",");
	        	Long start;
	        	int flag=0;
	        	for(int i=0;i<id.length;i++)
	        	{
	        		System.out.println("id="+id[i]);
	        		System.out.println(date[i]);
	        		Long l=Long.parseLong(date[i]);
	        		Date d=new Date(l);
	        		dao_academic ac=new dao_academic();
	        		boolean b=ac.delete(id[i], d);
	        		if(!b)
	        		{
	        			ObjectMapper mapper=new ObjectMapper();
	        			mapper.writeValue(response.getWriter(),b);
	        			 break;
	        		}
	        		flag++;
	        		if(flag==id.length)
	        		{
	        			b=true;
	        			ObjectMapper mapper=new ObjectMapper();
	        			mapper.writeValue(response.getWriter(),b);
	        		}
//	        		id=json.getString(i);
//	        		start=dat.getLong(i);
//	        		Date gain=new Date(start);
//	        		dao_academic d=new dao_academic();
//	        		boolean b=d.delete(id, gain);
//	        		if(!b)
//	        		{
//	        			ObjectMapper mapper=new ObjectMapper();
//	        			mapper.writeValue(response.getWriter(),b);
//	        			 break;
//	        		}
//	        		flag++;
//	        		if(flag==json.size())
//	        		{
//	        			ObjectMapper mapper=new ObjectMapper();
//	        			mapper.writeValue(response.getWriter(),b);
//	        		}
	        		
	        		
	        		
	        	}
		 }
		 else if(action.equals("delete_staff_zhicheng"))
		 {
			 String staff_id=request.getParameter("staff_id");
			String gain_time=request.getParameter("gain_time");
			Long l=Long.parseLong(gain_time);
			Date d=new Date(l);
			dao_academic ac=new dao_academic();
    		boolean b=ac.delete(staff_id, d);

    		ObjectMapper mapper=new ObjectMapper();
    		mapper.writeValue(response.getWriter(),b);
			
		 }
		 else if(action.equals("delete_education"))
		 {
			 String group = request.getParameter("group");
			 String datestr = request.getParameter("date");
			 
			 String[] id = group.split(",");
			 String[] date = datestr.split(",");
	        	Long start;
	        	int flag=0;
	        	for(int i=0;i<id.length;i++)
	        	{
	        		System.out.println("id="+id[i]);
	        		System.out.println(date[i]);
	        		Long l=Long.parseLong(date[i]);
	        		Date d=new Date(l);
	        		dao_education ac=new dao_education();
	        		boolean b=ac.delete_education(id[i], d);
	        		if(!b)
	        		{
	        			ObjectMapper mapper=new ObjectMapper();
	        			mapper.writeValue(response.getWriter(),b);
	        			 break;
	        		}
	        		flag++;
	        		if(flag==id.length)
	        		{
	        			b=true;
	        			ObjectMapper mapper=new ObjectMapper();
	        			mapper.writeValue(response.getWriter(),b);
	        		}
		         }
		 
	    }
		 else if(action.equals("delete_staff_education"))
		 {
			    String staff_id=request.getParameter("staff_id");
				String enrollment_time=request.getParameter("enrollment_time");
				Long l=Long.parseLong(enrollment_time);
				Date d=new Date(l);
				dao_education ac=new dao_education();
        		boolean b=ac.delete_education(staff_id, d);

	    		ObjectMapper mapper=new ObjectMapper();
	    		mapper.writeValue(response.getWriter(),b);
		 }
		 else if(action.equals("delete_train"))
		 {
			 String group = request.getParameter("group");
			 String datestr = request.getParameter("date");
			 
			 String[] id = group.split(",");
			 String[] date = datestr.split(",");
	        	Long start;
	        	int flag=0;
	        	for(int i=0;i<id.length;i++)
	        	{
	        		System.out.println("id="+id[i]);
	        		System.out.println(date[i]);
	        		Long l=Long.parseLong(date[i]);
	        		Date d=new Date(l);
	        		dao_train ac=new dao_train();
	        		boolean b=ac.delete_train(id[i], d);
	        		if(!b)
	        		{
	        			ObjectMapper mapper=new ObjectMapper();
	        			mapper.writeValue(response.getWriter(),b);
	        			 break;
	        		}
	        		flag++;
	        		if(flag==id.length)
	        		{
	        			b=true;
	        			ObjectMapper mapper=new ObjectMapper();
	        			mapper.writeValue(response.getWriter(),b);
	        		}
		         }
		 }
		 else if(action.equals("delete_staff_train"))
		 {
			  String staff_id=request.getParameter("staff_id");
				String train_start_time=request.getParameter("train_start_time");
				Long l=Long.parseLong(train_start_time);
				Date d=new Date(l);
				dao_train ac=new dao_train();
      		    boolean b=ac.delete_train(staff_id, d);

	    		ObjectMapper mapper=new ObjectMapper();
	    		mapper.writeValue(response.getWriter(),b);
		 }
		 else if(action.equals("exce_train"))
		 {
			 String group = request.getParameter("group");
			 String datestr = request.getParameter("date");
			 List<train> lis=new ArrayList<train>();
			 String[] id = group.split(",");
			 String[] date = datestr.split(",");
	        	Long start;
	        	int flag=0;
	        	
	        	dao_train ac=new dao_train();
	        	for(int i=0;i<id.length;i++)
	        	{
	        		train tr=new train();
	        		System.out.println("id="+id[i]);
	        		System.out.println(date[i]);
	        		Long l=Long.parseLong(date[i]);
	        		Date d=new Date(l);
	        		
	        	//	tr=ac.delete_train(id[i], d);
	        		tr=ac.find_train(id[i], d);
	        		lis.add(tr);
	        	
	        		
		       }
		 }

     }
}
