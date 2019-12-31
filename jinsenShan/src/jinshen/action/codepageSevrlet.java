package jinshen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinshen.bean.codejson;
import jinshen.bean.codepage;
import jinshen.bean.tree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import jinshen.bean.volume;
import jinshen.dao.codepageDao;
import jinshen.dao.treeDao;
import jinshen.dao.volumeDao;
import jinshen.daoimpl.codepageDaoImpl;
import jinshen.daoimpl.treeDaoImpl;
import jinshen.daoimpl.volumeDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;


@WebServlet("/codepageSevrlet")
public class codepageSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

    public codepageSevrlet() {
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
        volumeDao vl=new volumeDaoImpl();
        treeDao trd=new treeDaoImpl();
        codepageDao cpd=new codepageDaoImpl();
        ObjectMapper mapper = new ObjectMapper();
        session.setAttribute("staff_id", "123");  //登录得员工id
        if("volume".equals(action))
        {
        	double td=Double.parseDouble(request.getParameter("td"));
        	double tr=Double.parseDouble(request.getParameter("tr"));
        	sql="select * from volume where tlong ="+td+" and tradius="+tr;
			volume vll=vl.findVolumeSingle(sql);
			if(vll!=null)
			{
			   out.print(vll.getMvolume());
			}
			else
			{
				out.print(" ");
			}
        }
        else if("addCodepage".equals(action))
        {
        	double codeid=Double.parseDouble(request.getParameter("codeid"));
        	String shipping=request.getParameter("shipping");
        	String takein=request.getParameter("takein");
        	String command=request.getParameter("command");
        	double cutnum=Double.parseDouble(request.getParameter("cutnum"));
        	double checknum=Double.parseDouble(request.getParameter("checknum"));
        	String checksite=request.getParameter("checksite");
        	String getperson=request.getParameter("getperson");
        	String manageunit=request.getParameter("manageunit");
        	String leader=request.getParameter("leader");
        	String procharge=request.getParameter("procharge");
        	double workid=0;
        	double countid=0;
        	String begintime=request.getParameter("begindate");
        	String endtime=request.getParameter("enddate");
        	String forperson=request.getParameter("forperson");
        	String entrust=request.getParameter("entrust");
        	double transportnum=Double.parseDouble(request.getParameter("transportnum"));
        	String checkone=request.getParameter("checkone");
        	String checktwo="0";
        	String write=request.getParameter("write");
        	String shipplace=request.getParameter("shipplace");
        	String finbuyer=request.getParameter("finbuyer");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			Date d = null;
			Date dd = null;
			try {
				d = format.parse(begintime);
				dd = format.parse(endtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date begin = new java.sql.Date(d.getTime()); 
			java.sql.Date end = new java.sql.Date(dd.getTime()); 
        	codepage cp=new codepage();
        	cp.setCodeid(codeid);
        	cp.setBegintime(begin);
        	cp.setCheckNum(checknum);
        	cp.setCheckSite(checksite);
        	cp.setCommand(command);
        	cp.setCountid(countid);
        	cp.setCutNum(cutnum);
        	cp.setEndtime(end);
        	cp.setEntrust(entrust);
        	cp.setForperson(forperson);
        	cp.setGetPerson(getperson);
        	cp.setLeader(leader);
        	cp.setManageUnit(manageunit);
        	cp.setProcharge(procharge);
        	cp.setShipping(shipping);
        	cp.setStaff_id_1(checkone);
        	cp.setStaff_id_2(checktwo);
        	cp.setStaff_id_3(write);
        	cp.setTakein(takein);
        	cp.setTransportNum(transportnum);
        	cp.setWorkid(workid);
        	cp.setShipplace(shipplace);
        	cp.setFinbuyer(finbuyer);
        	sql="select sum(num) from tree where codeid="+cp.getCodeid()+"";
        	double totree=cpd.findcount(sql);
        	cp.setTolTree(totree);
        	sql="select sum(tvolume) from tree where codeid="+cp.getCodeid()+"";
        	double tostere=cpd.findcount(sql);
        	cp.setTolStere(tostere);
        	sql="select count(*) from codepage where codeid="+cp.getCodeid()+"";
        	int flag=0;
        	double f=cpd.findcount(sql);
        	if(f==1) {
        		flag=cpd.updateCodePage(cp);
        	}
        	else
        	{
        	flag=cpd.addCodePage(cp);
        	}
        	if(flag==1)
        	{
        		sql="select * from codepage where codeid="+codeid+"";
        		codepage codepage=cpd.findCodeSingle(sql);
        		sql="select * from tree where codeid="+codeid;
        		List<tree> tree=trd.findTree(sql);
        		request.setAttribute("codepage", codepage);
        		request.setAttribute("tree", tree);
    		    request.getRequestDispatcher("codepageUpdate.jsp").forward(request, response);
        	}
        	else
        	{
        		request.getRequestDispatcher("index.html").forward(request, response);
        	}
        }
        else if("treeAdd".equals(action))
        {

        	String rebate = request.getParameter("newtree");
        	int id=Integer.parseInt(request.getParameter("id"));
        	JSONObject jb = JSONObject.fromObject(rebate);
            for(int i=0;i<id;i++)
            {
            	JSONArray s=jb.getJSONArray(String.valueOf(i));
            	tree t=new tree();
            	double codeid=Double.parseDouble(request.getParameter("codeid"));
            	t.setCodeid(codeid);
            	sql="delete from tree where codeid="+codeid+"";
            	int ii=trd.delById(sql);
            	t.setTreetype(s.getString(0));
            	t.setTlong(Double.parseDouble(s.getString(1)));
            	t.setTradius(Double.parseDouble(s.getString(2)));
            	t.setNum(Double.parseDouble(s.getString(3)));
            	sql="select * from volume where tlong ="+Double.parseDouble(s.getString(1))+" and tradius="+Double.parseDouble(s.getString(1));
    			volume vll=vl.findVolumeSingle(sql);
            	double tvolume=vll.getMvolume();
            	t.setTvolume(tvolume*t.getNum());
            	//材积单价还没有
            	t.setUnitprice(1);
            	t.setTotalnum(1);
            	int flag=trd.addTree(t);
            	out.print(flag);
            }
        }
        else if("searchstatus".equals(action))
        {
        	sql="select codeid,manageunit,cutnum,staff_id_1 from codepage where staff_id_2 like '0'";
        	List<codejson> work=cpd.findcodeJson(sql);
        	sql="select a.codeid,manageunit,cutnum,staff_id_1 from codepage a join codestatus b on a.codeid=b.codeid where mystatus not like 'back'";
        	List<codejson> work1=cpd.findcodeJson(sql);
        	for(int i=0;i<work1.size();i++)
        	{
        		work.add(work1.get(i));
        	}
        	mapper.writeValue(response.getWriter(), work);
        }
        else if("mypass".equals(action))
        {
        	double codeid=Double.parseDouble(request.getParameter("codeid"));
        	double staff_id=Double.parseDouble((String)session.getAttribute("staff_id"));
        	//看这里，此处缺少用户权限得审核
        	sql="delete from codestatus where codeid="+codeid+"";
        	int i=cpd.delCodePage(sql);
        	sql="update codepage set staff_id_2="+staff_id+" where codeid="+codeid+"";
        	i=cpd.updateCode(sql);
        	if(i==1)
        	{
        		out.print("审核成功！");
        	}
        	else
        	{
        		out.print("失败！");
        	}
        }
        else if("allpass".equals(action))
        {
        	String mygroup = request.getParameter("codeid");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	for(int i=0;i<json.length();i++)
        	{
        		 each=Double.parseDouble(json.getString(i));
        		 double staff_id=Double.parseDouble((String)session.getAttribute("staff_id"));
        		 sql="update codepage set staff_id_2="+staff_id+" where codeid="+each+"";
        		 int j=cpd.updateCode(sql);
        		 sql="delete from codestatus where codeid="+each+"";
             	int k=cpd.delCodePage(sql);
        		 if(j==0)
        		 {
        			 out.print("更新失败！");
        			 break;
        		 }
        	}
        	out.print("操作成功");
        }
        else if("allback".equals(action))
        {
        	String mygroup = request.getParameter("codeid");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	int j=-1;
        	for(int i=0;i<json.length();i++)
        	{
        		 each=json.getDouble(i);
        		 sql="select count(*) from codestatus where codeid="+each+" and mystatus='again'";
         		double ff=cpd.findcount(sql);
         		if(ff>0)
         		{
         		sql="update codestatus set mystatus='back' where codeid="+each;
         		int flag=cpd.updateCode(sql);
         		}
         		else {
        		 sql="insert into codestatus values("+each+",'back')";
        		 j=cpd.addCodeStatus(sql);
         		}
        		if(j==0)
        		 {
        			 out.print("该码单已经被退回");
        			 break;
        		 }
        	}
        	out.print("退回成功");
        }
        else if("alldelete".equals(action))
        {
        	String mygroup = request.getParameter("codeid");
        	JSONArray json=JSONArray.fromObject(mygroup); 
        	double each=0;
        	for(int i=0;i<json.length();i++)
        	{
        		 each=json.getDouble(i);
        		 sql="delete from codepage where codeid="+each+"";
        		 int j=cpd.updateCode(sql);
        		 if(j==0)
        		 {
        			 out.print("删除失败！");
        			 break;
        		 }
        	}
        }
        else if("writecode".equals(action))
        {
        	String mytype = request.getParameter("type");
        	if(mytype.equals("all"))
        	{
        		sql="select codeid,manageunit,cutnum,staff_id_1 from codepage";
            	List<codejson> work=cpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	else if(mytype.equals("notpass"))
        	{
        		sql="select codeid,manageunit,cutnum,staff_id_1 from codepage where staff_id_2 like '0' or codeid in (select codeid from codestatus)";
            	List<codejson> work=cpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	else if(mytype.equals("didpass"))
        	{
        		sql="select codeid,manageunit,cutnum,staff_id_1 from codepage where staff_id_2 not like '0' and codeid not in (select codeid from codestatus)";
            	List<codejson> work=cpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	else if(mytype.equals("notshenhe"))
        	{
        		sql="select codeid,manageunit,cutnum,staff_id_1 from codepage where staff_id_2 like '0' and codeid not in (select codeid from codestatus)";
            	List<codejson> work=cpd.findcodeJson(sql);
            	mapper.writeValue(response.getWriter(), work);
        	}
        	else if(mytype.equals("alldelete"))
        	{
        		String mygroup = request.getParameter("codeid");
            	JSONArray json=JSONArray.fromObject(mygroup); 
            	double each=0;
            	for(int i=0;i<json.length();i++)
            	{
            		each=Double.parseDouble(json.getString(i));
            		 sql="delete from codepage where codeid="+each+"";
            		 int j=cpd.updateCode(sql);
            		 if(j==0)
            		 {
            			 out.print("删除失败！");
            			 break;
            		 }
            	}
        	}
        }
    	else if(action.equals("single"))
    	{
    		String str=request.getParameter("codeid");
    		str=str.replace("'", "");
    		double codeid=Double.parseDouble(str);
    		sql="select * from codepage where codeid="+codeid+"";
    		codepage codepage=cpd.findCodeSingle(sql);
    		sql="select * from tree where codeid="+codeid;
    		List<tree> tree=trd.findTree(sql);
    		request.setAttribute("codepage", codepage);
    		request.setAttribute("tree", tree);
		    request.getRequestDispatcher("codepageUpdate.jsp").forward(request, response);
    	}
    	else if(action.equals("mysingle"))
    	{
    		String str=request.getParameter("codeid");
    		str=str.replace("'", "");
    		double codeid=Double.parseDouble(str);
    		sql="select * from codepage where codeid="+codeid+"";
    		codepage codepage=cpd.findCodeSingle(sql);
    		sql="select * from tree where codeid="+codeid;
    		List<tree> tree=trd.findTree(sql);
    		System.out.println(tree.size());
    		request.setAttribute("codepage", codepage);
    		request.setAttribute("tree", tree);
		    request.getRequestDispatcher("codepageShenhe.jsp").forward(request, response);
    	}
    	else if("updateCodepage".equals(action))
    	{
    		double codeid=Double.parseDouble(request.getParameter("codeid"));
        	String shipping=request.getParameter("shipping");
        	String takein=request.getParameter("takein");
        	String command=request.getParameter("command");
        	double cutnum=Double.parseDouble(request.getParameter("cutnum"));
        	double checknum=Double.parseDouble(request.getParameter("checknum"));
        	String checksite=request.getParameter("checksite");
        	String getperson=request.getParameter("getperson");
        	String manageunit=request.getParameter("manageunit");
        	String leader=request.getParameter("leader");
        	String procharge=request.getParameter("procharge");
        	double workid=Double.parseDouble(request.getParameter("workid"));
        	double countid=Double.parseDouble(request.getParameter("countid"));
        	String begintime=request.getParameter("begindate");
        	String endtime=request.getParameter("enddate");
        	String forperson=request.getParameter("forperson");
        	String entrust=request.getParameter("entrust");
        	double transportnum=Double.parseDouble(request.getParameter("transportnum"));
        	String checkone=request.getParameter("checkone");
        	String checktwo=request.getParameter("checktwo");
        	String write=request.getParameter("write");
        	String shipplace=request.getParameter("shipplace");
        	String finbuyer=request.getParameter("finbuyer");
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			Date d = null;
			Date dd = null;
			try {
				d = format.parse(begintime);
				dd = format.parse(endtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Date begin = new java.sql.Date(d.getTime()); 
			java.sql.Date end = new java.sql.Date(dd.getTime()); 
        	codepage cp=new codepage();
        	cp.setCodeid(codeid);
        	cp.setBegintime(begin);
        	cp.setCheckNum(checknum);
        	cp.setCheckSite(checksite);
        	cp.setCommand(command);
        	cp.setCountid(countid);
        	cp.setCutNum(cutnum);
        	cp.setEndtime(end);
        	cp.setEntrust(entrust);
        	cp.setForperson(forperson);
        	cp.setGetPerson(getperson);
        	cp.setLeader(leader);
        	cp.setManageUnit(manageunit);
        	cp.setProcharge(procharge);
        	cp.setShipping(shipping);
        	cp.setStaff_id_1(checkone);
        	cp.setStaff_id_2(checktwo);
        	cp.setStaff_id_3(write);
        	cp.setTakein(takein);
        	cp.setTransportNum(transportnum);
        	cp.setWorkid(workid);
        	cp.setCountid(countid);
        	cp.setShipplace(shipplace);
        	cp.setFinbuyer(finbuyer);
        	//这个有问题，需要斟酌一下
        	sql="select sum(num) from tree where codeid="+cp.getCodeid()+"";
        	double totree=cpd.findcount(sql);
        	cp.setTolTree(totree);
        	sql="select sum(tvolume) from tree where codeid="+cp.getCodeid()+"";
        	double tostere=cpd.findcount(sql);
        	cp.setTolStere(tostere);
        	sql="select count(*) from codepage where codeid="+cp.getCodeid()+"";
        	int flag=0;
        	double f=cpd.findcount(sql);
        	if(f==1) {
        		flag=cpd.updateCodePage(cp);
        		sql="select count(*) from codestatus where codeid="+codeid+" and mystatus='back'";
        		double ff=cpd.findcount(sql);
        		if(ff>0)
        		{
        		sql="update codestatus set mystatus='again' where codeid="+codeid;
        		flag=cpd.updateCode(sql);
        		}
        	}
        	else
        	{
        	flag=cpd.addCodePage(cp);
        	}
        	if(flag==1)
        	{
        		sql="select * from codepage where codeid="+codeid+"";
        		codepage codepage=cpd.findCodeSingle(sql);
        		sql="select * from tree where codeid="+codeid;
        		List<tree> tree=trd.findTree(sql);
        		request.setAttribute("codepage", codepage);
        		request.setAttribute("tree", tree);
    		    request.getRequestDispatcher("codepageUpdate.jsp").forward(request, response);
        	}
        	else
        	{
        		sql="select * from codepage where codeid="+codeid+"";
        		codepage codepage=cpd.findCodeSingle(sql);
        		sql="select * from tree where codeid="+codeid;
        		List<tree> tree=trd.findTree(sql);
        		request.setAttribute("codepage", codepage);
        		request.setAttribute("tree", tree);
    		    request.getRequestDispatcher("codepageUpdate.jsp").forward(request, response);
        	}
    	}
	}

}
