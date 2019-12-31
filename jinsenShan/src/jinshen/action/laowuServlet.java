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

import jinshen.bean.order;
import jinshen.bean.tree;
import jinshen.bean.volume;
import jinshen.bean.work;
import jinshen.bean.worktree;
import jinshen.dao.codepageDao;
import jinshen.dao.treeDao;
import jinshen.dao.volumeDao;
import jinshen.dao.workDao;
import jinshen.daoimpl.codepageDaoImpl;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.volumeDaoImpl;
import jinshen.daoimpl.workDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/laowuServlet")
public class laowuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public laowuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql="";
        workDao wd=new workDaoImpl();
        treeDao td=new treeDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        if(action.equals("mysave"))
        {
        	double workid = Double.parseDouble(request.getParameter("workid"));
        	Map map=new HashMap();
        	List<work> list=new ArrayList<work>();
        	sql="select count(*) from work where workid="+workid;
        	int flag=wd.findMaxid(sql);
        	String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	if(flag>0 && json.length()==0)
        	{
        		sql="select codeid,checkNum,cutNum,forperson,manageUnit from codepage where workid="+workid;
        		list=wd.findWorkList(sql);
        		sql="select treetype,sum(totalnum) as total,unitprice,(sum(totalnum)*unitprice) as totalprice  from tree a join codepage b on a.codeid=b.codeid where b.workid="+workid+" group by treetype";
        		List<worktree> worktree=wd.findworktree(sql);
        		map.put("work", list);
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
        		 sql="SELECT codeid,checkNum,cutNum,forperson,manageUnit from codepage where codeid="+each+"";
        		 work w=wd.findWorkSingle(sql);
        		 list.add(w);
        		 str+=each;
        	}
        	str+=")";
        	sql="select treetype,sum(totalnum) as total,unitprice,(sum(totalnum)*unitprice) as totalprice  from tree a join codepage b on a.codeid=b.codeid where a.codeid in"+str+" group by treetype";
        	List<worktree> worktree=wd.findworktree(sql);
        	map.put("work", list);
            map.put("tree", worktree);
    		mapper.writeValue(response.getWriter(), map);
    		}
        }
        else if(action.equals("savelaowu"))
        {
        	double workid = Double.parseDouble(request.getParameter("workid"));
        	String mygroup = request.getParameter("mygroup");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	int count=0;
        	sql="update codepage set workid=0 where workid="+workid+"";
   		    int res=wd.update(sql);
        	for(int i=0;i<json.length();i++)
        	{
        		 each=json.getDouble(i);
        		 sql="update codepage set workid="+workid+" where codeid="+each+"";
        		 res=wd.update(sql);
        		 count+=res;
        	}
        	sql="insert into work values("+workid+")";
        	if(count>0)
        	{
        		res=wd.addWork(sql);
        		out.print("提交成功");
        	}
        	else
        	{
        		out.print("提交失败");
        	}
        }
        else if("treeAdd".equals(action))
        {

        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);            	
        	double workid=Double.parseDouble(request.getParameter("workid"));
            for(int i=0;i<id;i++)
            {
            	JSONArray s=jb.getJSONArray(String.valueOf(i));
            	String type=s.getString(0);
            	double volume=Double.parseDouble(s.getString(1));
            	double unitprice=Double.parseDouble(s.getString(2));
            	double price=Double.parseDouble(s.getString(3));
            	sql="update tree set unitprice="+unitprice+",totalnum=unitprice*tvolume where  tree.codeid in "
            			+ "(select codeid from codepage where workid="+workid+") and treetype='"+type+"'";
            	int flag=td.updateTree(sql);
            }
            out.print("更新完成！");
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
