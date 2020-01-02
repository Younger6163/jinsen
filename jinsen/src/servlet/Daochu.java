package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jinsen.bean.train;
import jinsen.daoreal.dao_train;




@WebServlet("/Daochu")
public class Daochu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Daochu() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.print("成功！");
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
        		
        		//tr=ac.find_train(id[i], d);
        		lis.add(tr);
        	
        		
	       }
        	 String[] title = {"员工工号","培训课程","培训开始时间","培训结束时间","培训证明","录入人","录入时间"};
        	 String fileName = "员工培训表"+System.currentTimeMillis()+".xls";

        		

        	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
        	 
	          //sheet名

	          /*String sheetName = "员工培训表";
	          String [][] temp=new String [title.length][title.length];
	          for(int i=0;i<lis.size();i++)
	          {
	        	  train t=lis.get(i);
	        	  temp[i][0]=t.getStaff_id();
	        	  temp[i][1]=t.getTrain_name();
	        	  
	        	  temp[i][2]=sdf.format(t.getTrain_start_time());
	        	
	        	  temp[i][3]=sdf.format(t.getTrain_end_time());
	        	  temp[i][4]=t.getTrain_certificate();
	        	  temp[i][5]=t.getManager();
	        	  temp[i][6]=sdf.format(t.getInput_time());
	        	  
	        	  
	          }
	         ExcelUtil ex=new ExcelUtil();
	          HSSFWorkbook wb = ex.getHSSFWorkbook(sheetName, title, temp);	
	          //响应到客户端
	              
	          try
	          {	    
	        	  this.setResponseHeader(response, fileName);	    
	        	  OutputStream os = response.getOutputStream();	  
	        	  //wb.write();
	        	  wb.write(os);	      
	        	  os.flush();	    
	        	  os.close();	   
	        	  } 
	          catch (Exception e) 
	          {	    	 
	        	  
	        	  
	          } 		
           finally
           { 	
    	   
		
	       } 
	        
	      */
	       
	    }
	
	 public void setResponseHeader(HttpServletResponse response, String fileName) {     
		 try {
			 
			 try {             
				     fileName = new String(fileName.getBytes(),"utf-8");       
				 } 
			 catch (UnsupportedEncodingException e)
			 {            
				 // TODO Auto-generated catch block               
				 e.printStackTrace();      
			}           
			 response.setContentType("application/octet-stream;charset=utf-8");        
			 response.setHeader("Content-Disposition", "attachment;filename="+ fileName);        
			 response.addHeader("Pargam", "no-cache");       
			 response.addHeader("Cache-Control", "no-cache");      
			 }
		 
		   catch (Exception ex)
		      {            
			     ex.printStackTrace();     
		      }
		 
		 }
		
	

}

