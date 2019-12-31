package jinshen.action;

import java.io.IOException;
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

import jinshen.bean.codepage;
import jinshen.bean.monthtree;
import jinshen.bean.producetree;
import jinshen.bean.statetree;
import jinshen.bean.totaltree;
import jinshen.bean.yeartree;
import jinshen.dao.codepageDao;
import jinshen.daoimpl.codepageDaoImpl;


@WebServlet("/baobiao")
public class baobiao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private codepageDao sdao = new codepageDaoImpl();
	
	
    public baobiao() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		
        if(action.equals("salary_tree"))
		{
        	String year = request.getParameter("year");
			String month = request.getParameter("month");
			
			List<totaltree> dp = sdao.findTotaltree();
			List<totaltree> dplist = new ArrayList<totaltree>();
			if(dp != null)
			{		
				for(int i = 0;i<dp.size();i ++)
				{
					totaltree d = sdao.findSale(Integer.valueOf(year));
					/*if ((Integer.valueOf(year) == dp.get(i).getSyear() && (Integer.valueOf(month) == dp.get(i).getSmonth())))
					{  
						d.setSmonth(dp.get(i).getSmonth());
					    d.setTreetype(dp.get(i).getTreetype());
					    d.setUnitprice(dp.get(i).getUnitprice());
					    d.setTotalnum(dp.get(i).getTotalnum());
					    dplist.add(d);}*/
					 if (Integer.valueOf(year) == dp.get(i).getSyear())
					{
						d.setSmonth(dp.get(i).getSmonth());
					    d.setTreetype(dp.get(i).getTreetype());
					    d.setUnitprice(dp.get(i).getUnitprice());
					    d.setTotalnum(dp.get(i).getTotalnum());
					    dplist.add(d);
					}

				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
		}
        else if(action.equals("salary_month_year"))
		{
        	String year = request.getParameter("year");
			String month = request.getParameter("month");			
			List<yeartree> dp = sdao.findYeartree();
			List<yeartree> dplist = new ArrayList<yeartree>();
			if(dp != null)
			{		
				for(int i = 0;i<dp.size();i ++)
				{
					yeartree d = sdao.printYeartree(Integer.valueOf(year), Integer.valueOf(month));
					if ((Integer.valueOf(year) == dp.get(i).getSyear() && (Integer.valueOf(month) == dp.get(i).getSmonth())))
					{  
						d.setSyear(dp.get(i).getSyear());
						d.setSmonth(dp.get(i).getSmonth());
						d.setCutNum(dp.get(i).getCutNum());
						d.setCheckNum(dp.get(i).getCheckNum());
						d.setEntrust(dp.get(i).getEntrust());
					    d.setTreetype(dp.get(i).getTreetype());
					    d.setVolume(dp.get(i).getVolume());
					    d.setUnitprice(dp.get(i).getUnitprice());
					    d.setTotalnum(dp.get(i).getTotalnum());
					    dplist.add(d);}
					/*else if (Integer.valueOf(year) == dp.get(i).getSyear())
					{
						d.setSmonth(dp.get(i).getSmonth());
						d.setCutNum(dp.get(i).getCutNum());
						d.setCheckNum(dp.get(i).getCheckNum());
						d.setEntrust(dp.get(i).getEntrust());
					    d.setTreetype(dp.get(i).getTreetype());
					    d.setVolume(dp.get(i).getVolume());
					    d.setUnitprice(dp.get(i).getUnitprice());
					    d.setTotalnum(dp.get(i).getTotalnum());
					    dplist.add(d);
					}*/

				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
		}
        else if (action.equals("salary_statement"))
        {
        	String year = request.getParameter("year");
        	List<statetree> dp = sdao.findState();
			List<statetree> dplist = new ArrayList<statetree>();
			if(dp != null)
			{		
				for(int i = 0;i<dp.size();i ++)
				{
					statetree d = sdao.printStatetree(Integer.valueOf(year));
					 if (Integer.valueOf(year) == dp.get(i).getSyear())
					{
						 d.setCutNum(dp.get(i).getCutNum());
						 d.setCheckNum(dp.get(i).getCheckNum());
						 d.setEntrust(dp.get(i).getEntrust());
						 d.setGetPerson(dp.get(i).getGetPerson());
						 d.setSyear(dp.get(i).getSyear());
						 d.setSmonth(dp.get(i).getSmonth());
						 d.setSday(dp.get(i).getSday());
						 d.setTakein(dp.get(i).getTakein());
					     d.setTreetype(dp.get(i).getTreetype());
					     d.setUnitprice(dp.get(i).getUnitprice());
					    dplist.add(d);
					}

				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);
        	
        }
        else if (action.equals("salary_produce"))
        {
        	String year = request.getParameter("year");
        	List<producetree> dp = sdao.findProduce();
			List<producetree> dplist = new ArrayList<producetree>();
			if(dp != null)
			{		
				for(int i = 0;i<dp.size();i ++)
				{
					producetree d = sdao.printProduce(Integer.valueOf(year));
					 if (Integer.valueOf(year) == dp.get(i).getSyear())
					{
						 d.setCutNum(dp.get(i).getCutNum());
						 d.setCheckNum(dp.get(i).getCheckNum());
						 d.setSyear(dp.get(i).getSyear());
						 d.setSmonth(dp.get(i).getSmonth());
						 d.setSday(dp.get(i).getSday());
						 d.setForperson(dp.get(i).getForperson());
					     d.setTreetype(dp.get(i).getTreetype());
					     d.setUnitprice(dp.get(i).getUnitprice());
					    dplist.add(d);
					}

				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);	
        }
        else if (action.equals("salary_monthtree"))
        {
        	String year = request.getParameter("year");
        	String month = request.getParameter("month");
        	List<monthtree> dp = sdao.findMonth();
			List<monthtree> dplist = new ArrayList<monthtree>();
			if(dp != null)
			{		
				for(int i = 0;i<dp.size();i ++)
				{
					monthtree d = sdao.printMonth(Integer.valueOf(year),Integer.valueOf(month));
					 if (Integer.valueOf(year) == dp.get(i).getSyear()&& Integer.valueOf(month) == dp.get(i).getSmonth())
					{
						 d.setCutNum(dp.get(i).getCutNum());
						 d.setShipplace(dp.get(i).getShipplace());
						 d.setSyear(dp.get(i).getSyear());
						 d.setSmonth(dp.get(i).getSmonth());
						 d.setSday(dp.get(i).getSday());
					     d.setTreetype(dp.get(i).getTreetype());
					     d.setUnitprice(dp.get(i).getUnitprice());
					    dplist.add(d);
					}

				}
			}
			ObjectMapper map = new ObjectMapper();
			map.writeValue(response.getWriter(), dplist);	
        }
        
		}
	

}
