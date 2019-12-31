package jinshen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.count;
import jinshen.bean.order;
import jinshen.bean.tree;
import jinshen.bean.work;
import jinshen.bean.worktree;
import jinshen.dao.codepageDao;
import jinshen.dao.orderDao;
import jinshen.dao.treeDao;
import jinshen.dao.workDao;
import jinshen.daoimpl.codepageDaoImpl;
import jinshen.daoimpl.orderDaoImpl;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.workDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/countServlet")
public class countServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public countServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String sql="";
        orderDao od=new orderDaoImpl();
        treeDao td=new treeDaoImpl();
        codepageDao cpd=new codepageDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        if(action.equals("mysave"))
        {
        	double countid = Double.parseDouble(request.getParameter("countid"));
        	Map map=new HashMap();
        	List<count> list=new ArrayList<count>();
        	String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	sql="select count(*) from orderpage where countid="+countid;
        	int flag=od.findMaxid(sql);
        	if(flag>0 && json.length()==0)
        	{
        		sql="SELECT codeid,checkNum,cutNum,shipping,shipplace,finbuyer,getperson from codepage where countid="+countid+"";
        		list=od.findListCount1(sql);
        		sql="select * from tree a join codepage b on a.codeid=b.codeid where b.countid="+countid;
        		List<tree> worktree=td.findTree(sql);
       		    map.put("code", list);
       		    map.put("tree", worktree);
    			mapper.writeValue(response.getWriter(), map);
        	}else
        	{
        	double each=0;
        	int count=0;
        	String str="(";
        	for(int i=0;i<json.length();i++)
        	{
        		if(i>0)
        		str+=",";
        		 each=json.getDouble(i);
        		 sql="SELECT codeid,checkNum,cutNum,shipping,shipplace,finbuyer,getperson from codepage where codeid="+each+"";
        		 count cc=od.findListCount(sql);
        		 list.add(cc);
        		 str+=each;
        	}
        	str+=")";
            sql="select * from tree a join codepage b on a.codeid=b.codeid where a.codeid in"+str;
   		    List<tree> worktree=td.findTree(sql);
   		    map.put("code", list);
   		    map.put("tree", worktree);
			 mapper.writeValue(response.getWriter(), map);
			 }
        }
        else if(action.equals("finsave"))
        {
        	double countid = Double.parseDouble(request.getParameter("countid"));
        	String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	int count=0;
        	sql="update codepage set countid=0 where countid="+countid+"";
   		    int res=od.updateOrder(sql);
        	for(int i=0;i<json.length();i++)
        	{
        		 each=json.getDouble(i);
        		 sql="update codepage set countid="+countid+" where codeid="+each+"";
        		 res=od.updateOrder(sql);
        		 count+=res;
        	}
        	sql="insert into orderpage values("+countid+")";
        	if(count>0)
        	{
        		order c=new order();
        		c.setCountid(countid);
        		od.addOrder(c);
        		out.print("提交成功");
        	}
        	else
        	{
        		out.print("提交失败");
        	}
        }

	}

}
