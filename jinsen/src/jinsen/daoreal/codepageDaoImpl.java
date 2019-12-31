package jinsen.daoreal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.codejson;
import jinsen.bean.codepage;
import jinsen.bean.monthtree;
import jinsen.bean.newtree;
import jinsen.bean.producetree;
import jinsen.bean.statetree;
import jinsen.bean.totaltree;
import jinsen.bean.yeartree;
import jinsen.dao.codepageDao;
import jinsen.db.dbCon;
public class codepageDaoImpl implements codepageDao{
	private dbCon dbc=new dbCon();
    Connection connection = null;
    PreparedStatement pStatement = null;

	@Override
	public int addCodePage(codepage cp) {
		String sql="insert into codepage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getCodeid(),cp.getShipping(),cp.getCommand(),cp.getCutNum(),cp.getCheckNum(),cp.getTakein(),cp.getCheckSite(),cp.getGetPerson(),cp.getBegintime(),cp.getEndtime(),cp.getTransportNum(),cp.getTolTree(),
					cp.getTolStere(),cp.getEntrust(),cp.getForperson(),cp.getStaff_id_1(),cp.getStaff_id_2(),cp.getStaff_id_3(),cp.getWorkid(),cp.getCountid(),cp.getManageUnit(),cp.getLeader(),cp.getProcharge(),cp.getShipplace(),cp.getFinbuyer()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int updateCodePage(codepage cp){
		int res=0;
		try {
			String sql="update codepage set shipping=?,command=?,cutNum=?,checkNum=?,takeIn=?,checkSite=?,getPerson=?,begintime=?,endtime=?,transportNum=?,tolTree=?,tolStere=?,entrust=?,forperson=?,staff_id_1=?,staff_id_2=?,staff_id_3=?,workid=?,countid=?,manageUnit=?,leader=?,procharge=?,shipplace=?,finbuyer=? where codeid=?";
			res=dbc.doUpdate(sql, new Object[] {cp.getShipping(),cp.getCommand(),cp.getCutNum(),cp.getCheckNum(),cp.getTakein(),cp.getCheckSite(),cp.getGetPerson(),cp.getBegintime(),cp.getEndtime(),cp.getTransportNum(),cp.getTolTree(),
					cp.getTolStere(),cp.getEntrust(),cp.getForperson(),cp.getStaff_id_1(),cp.getStaff_id_2(),cp.getStaff_id_3(),cp.getWorkid(),cp.getCountid(),cp.getManageUnit(),cp.getLeader(),cp.getProcharge(),cp.getShipplace(),cp.getFinbuyer(),cp.getCodeid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<codepage> findCodePage(String sql) {
		List<codepage> addrList=new ArrayList<codepage>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				codepage addr=new codepage();
				addr.setCodeid(rs.getDouble(1));
				addr.setShipping(rs.getString(2));
				addr.setCommand(rs.getString(3));
				addr.setCutNum(rs.getDouble(4));
				addr.setCheckNum(rs.getDouble(5));
				addr.setTakein(rs.getString(6));
				addr.setCheckSite(rs.getString(7));
				addr.setGetPerson(rs.getString(8));
				addr.setBegintime(rs.getDate(9));
				addr.setEndtime(rs.getDate(10));
				addr.setTransportNum(rs.getDouble(11));
				addr.setTolTree(rs.getDouble(12));
				addr.setTolStere(rs.getDouble(13));
				addr.setEntrust(rs.getString(14));
				addr.setForperson(rs.getString(15));
				addr.setStaff_id_1(rs.getString(16));
				addr.setStaff_id_2(rs.getString(17));
				addr.setStaff_id_3(rs.getString(18));
				addr.setWorkid(rs.getDouble(19));
				addr.setCountid(rs.getDouble(20));
				addr.setManageUnit(rs.getString(21));
				addr.setLeader(rs.getString(22));
				addr.setProcharge(rs.getString(23));
				addrList.add(addr);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addrList;
	}

	@Override
	public int delCodePage(String sql){
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int findMaxid() {
		String sql="select max(countid) from codepage";
		int num=1;
		try {
			ResultSet rs=dbc.doQuery(sql,new Object[]{});
			while(rs.next()) {
				num=rs.getInt(1)+1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	@Override
	public double findcount(String sql) {
		double num=1;
		try {
			ResultSet rs=dbc.doQuery(sql,new Object[]{});
			while(rs.next()) {
				num=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<codejson> findcodeJson(String sql) {
		List<codejson> work=new ArrayList<codejson>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				codejson w=new codejson();
				w.setCodeid(rs.getDouble(1));
				w.setSome(rs.getString(2));
				w.setCutnum(rs.getDouble(3));
				w.setStaff_id_1(rs.getString(4));
				work.add(w);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return work;
	}

	@Override
	public int updateCode(String sql)
	{
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int addCodeStatus(String sql)
	{
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public codepage findCodeSingle(String sql)
	{
		codepage addr=new codepage();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setCodeid(rs.getDouble(1));
				addr.setShipping(rs.getString(2));
				addr.setCommand(rs.getString(3));
				addr.setCutNum(rs.getDouble(4));
				addr.setCheckNum(rs.getDouble(5));
				addr.setTakein(rs.getString(6));
				addr.setCheckSite(rs.getString(7));
				addr.setGetPerson(rs.getString(8));
				addr.setBegintime(rs.getDate(9));
				addr.setEndtime(rs.getDate(10));
				addr.setTransportNum(rs.getDouble(11));
				addr.setTolTree(rs.getDouble(12));
				addr.setTolStere(rs.getDouble(13));
				addr.setEntrust(rs.getString(14));
				addr.setForperson(rs.getString(15));
				addr.setStaff_id_1(rs.getString(16));
				addr.setStaff_id_2(rs.getString(17));
				addr.setStaff_id_3(rs.getString(18));
				addr.setWorkid(rs.getDouble(19));
				addr.setCountid(rs.getDouble(20));
				addr.setManageUnit(rs.getString(21));
				addr.setLeader(rs.getString(22));
				addr.setProcharge(rs.getString(23));
				addr.setShipplace(rs.getString(24));
				addr.setFinbuyer(rs.getString(25));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public List<newtree> eachcompany(String sql)
	{
		List<newtree> list=new ArrayList<newtree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
					newtree addr=new newtree();
					addr.setYear(rs.getDouble(1));
					addr.setMonth(rs.getDouble(2));
					addr.setTreetype(rs.getString(3));
					addr.setNum(rs.getDouble(4));
					addr.setTotalnum(rs.getDouble(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return list;
	}
	
	@Override
    public List<totaltree> findTotaltree()
    {
    	//String sql = "select * from totaltree";
    	String sql="select year(endtime),month(endtime),treetype,unitprice,totalnum  from codepage join tree  on codepage.codeid=tree.codeid";
	    ResultSet rs = null;
	    List<totaltree> ts = new ArrayList<totaltree>();	
	    try {
	        connection = dbCon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        //System.out.println("...." +sql + "...");
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	totaltree s = new totaltree();
	        	//s.setCodeid(rs.getDouble(1));
	        	s.setSyear(rs.getDouble(1));
	        	s.setSmonth(rs.getDouble(2));
	        	s.setTreetype(rs.getString(3));
	        	s.setUnitprice(rs.getDouble(4));
				s.setTotalnum(rs.getDouble(5));
	        	ts.add(s);
	    }
	     return ts;
      }catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
      	dbCon.closeResultSet(rs);
      	dbCon.closePreparedStatement(pStatement);
      	dbCon.closeConnection(connection);	
     }
    	
    }
    
    @Override
    public totaltree findSale(int year  )
    {
    	//String sql = "select syear,smonth,treetype,unitprice,totalnum  from totaltree";
	   String sql = "select year(endtime),month(endtime),treetype,unitprice,totalnum  from codepage join tree  on codepage.codeid=tree.codeid";
    	//String sql = "INSERT INTO twotree(endtime,treetype,unitprice,totalnum) SELECT codepage.endtime,tree.treetype,tree.unitprice,tree.totalnum FROM codepage,tree WHERE codepage.codeid = tree.codeid";
    	//String sql = "INSERT INTO totaltree(syear,smonth,treetype,unitprice,totalnum) SELECT YEAR(codepage.endtime),MONTH(codepage.endtime),tree.treetype,tree.unitprice,tree.totalnum FROM codepage,tree WHERE codepage.codeid = tree.codeid";
    	ResultSet rs = null;
        totaltree d = new totaltree();

    	try {
    		connection = dbCon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			//tree d1 = new tree();
    			//d.setCodeid(rs.getDouble(1));
    			d.setSyear(rs.getDouble(1));
    			d.setSmonth(rs.getDouble(2));
    			System.out.println("...." + rs.getDouble(2) + "...");
    			d.setTreetype(rs.getString(3));
    			d.setUnitprice(rs.getDouble(4));
    			d.setTotalnum(rs.getDouble(5));
    			//d.add(d1);
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);	
       }
    }
       
    @Override
    public List<yeartree> findYeartree()
    {
    	String sql="select year(endtime),month(endtime),cutNum,checkNum,entrust,treetype,tvolume,unitprice,totalnum  from codepage join tree  on codepage.codeid=tree.codeid";
	    ResultSet rs = null;
	    List<yeartree> ts = new ArrayList<yeartree>();	
	    try {
	        connection = dbCon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        //System.out.println("...." +sql + "...");
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	yeartree s = new yeartree();
	        	//s.setCodeid(rs.getDouble(1));
	        	s.setSyear(rs.getDouble(1));
	        	s.setSmonth(rs.getDouble(2));
				s.setCutNum(rs.getDouble(3));
				s.setCheckNum(rs.getDouble(4));
				s.setEntrust(rs.getString(5));
				//System.out.println("...." + rs.getString(4) + "...");
	        	s.setTreetype(rs.getString(6));
				s.setVolume(rs.getDouble(7));
	        	s.setUnitprice(rs.getDouble(8));
				s.setTotalnum(rs.getDouble(9));
	        	ts.add(s);
	    }
	     return ts;
      }catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
      	dbCon.closeResultSet(rs);
      	dbCon.closePreparedStatement(pStatement);
      	dbCon.closeConnection(connection);	
     }
    	
    }
    
    @Override
    public yeartree printYeartree(int year,int month)
    {
    	String sql = "select year(endtime),month(endtime),cutNum,checkNum,entrust,treetype,tvolume,unitprice,totalnum  from codepage join tree  on codepage.codeid=tree.codeid";
    	ResultSet rs = null;
        yeartree d = new yeartree();

    	try {
    		connection = dbCon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			//tree d1 = new tree();
    			//d.setCodeid(rs.getDouble(1));
    			d.setSyear(rs.getDouble(1));
    			d.setSmonth(rs.getDouble(2));
    			//System.out.println("...." + rs.getDouble(2) + "...");
    			d.setCutNum(rs.getDouble(3));
				d.setCheckNum(rs.getDouble(4));
				System.out.println("...." + rs.getDouble(2) + "...");
				d.setEntrust(rs.getString(5));
	        	d.setTreetype(rs.getString(6));
				d.setVolume(rs.getDouble(7));
    			d.setUnitprice(rs.getDouble(8));
    			d.setTotalnum(rs.getDouble(9));
    			//d.add(d1);
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);	
       }
    }
    
    @Override
    public List<statetree> findState()
    {
    	String sql="select cutNum,checkNum,entrust,getPerson,year(endtime),month(endtime),day(endtime),takeIn,treetype,unitprice  from codepage join tree  on codepage.codeid=tree.codeid";
	    ResultSet rs = null;
	    List<statetree> ts = new ArrayList<statetree>();	
	    try {
	        connection = dbCon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        //System.out.println("...." +sql + "...");
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	statetree s = new statetree();
	        	s.setCutNum(rs.getDouble(1));
				s.setCheckNum(rs.getDouble(2));
				s.setEntrust(rs.getString(3));
				s.setGetPerson(rs.getString(4));
				s.setSyear(rs.getDouble(5));
    			s.setSmonth(rs.getDouble(6));
    			s.setSday(rs.getDouble(7));
    			s.setTakein(rs.getString(8));
    			//System.out.println("...." + rs.getDouble(2) + "...");
	        	s.setTreetype(rs.getString(9));
    			s.setUnitprice(rs.getDouble(10));
	        	ts.add(s);
	    }
	     return ts;
      }catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
      	dbCon.closeResultSet(rs);
      	dbCon.closePreparedStatement(pStatement);
      	dbCon.closeConnection(connection);	
     }
    	
    }
    
    //木材销售结算登记表
    @Override
    public statetree printStatetree(int year)
    {
    	String sql = "select cutNum,checkNum,entrust,getPerson,year(endtime),month(endtime),day(endtime),takeIn,treetype,unitprice  from codepage join tree  on codepage.codeid=tree.codeid";
    	ResultSet rs = null;
    	statetree d = new statetree();

    	try {
    		connection = dbCon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			//tree d1 = new tree();
    			//d.setCodeid(rs.getDouble(1));
    			d.setCutNum(rs.getDouble(1));
				d.setCheckNum(rs.getDouble(2));
				d.setEntrust(rs.getString(3));
				d.setGetPerson(rs.getString(4));
				d.setSyear(rs.getDouble(5));
    			d.setSmonth(rs.getDouble(6));
    			d.setSday(rs.getDouble(7));
    			d.setTakein(rs.getString(8));
    			//System.out.println("...." + rs.getDouble(2) + "...");
	        	d.setTreetype(rs.getString(9));
    			d.setUnitprice(rs.getDouble(10));
    			//d.add(d1);
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);	
       }
    }
    @Override
    public List<producetree> findProduce()
    {
    	String sql="select cutNum,checkNum,year(endtime),month(endtime),day(endtime),forperson,treetype,unitprice  from codepage join tree  on codepage.codeid=tree.codeid";
	    ResultSet rs = null;
	    List<producetree> ts = new ArrayList<producetree>();	
	    try {
	        connection = dbCon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        //System.out.println("...." +sql + "...");
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	producetree s = new producetree();
	        	s.setCutNum(rs.getDouble(1));
				s.setCheckNum(rs.getDouble(2));
				s.setSyear(rs.getDouble(3));
    			s.setSmonth(rs.getDouble(4));
    			s.setSday(rs.getDouble(5));
    			s.setForperson(rs.getString(6));
	        	s.setTreetype(rs.getString(7));
    			s.setUnitprice(rs.getDouble(8));
	        	ts.add(s);
	    }
	     return ts;
      }catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
      	dbCon.closeResultSet(rs);
      	dbCon.closePreparedStatement(pStatement);
      	dbCon.closeConnection(connection);	
     }
    	
    }
    
    //木材销售结算登记表
    @Override
    public producetree printProduce(int year)
    {
    	String sql = "select cutNum,checkNum,year(endtime),month(endtime),day(endtime),forperson,treetype,unitprice  from codepage join tree  on codepage.codeid=tree.codeid";
    	ResultSet rs = null;
    	producetree d = new producetree();

    	try {
    		connection = dbCon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			//tree d1 = new tree();
    			//d.setCodeid(rs.getDouble(1));
    			d.setCutNum(rs.getDouble(1));
				d.setCheckNum(rs.getDouble(2));
				d.setSyear(rs.getDouble(3));
    			d.setSmonth(rs.getDouble(4));
    			d.setSday(rs.getDouble(5));
    			d.setForperson(rs.getString(6));
	        	d.setTreetype(rs.getString(7));
    			d.setUnitprice(rs.getDouble(8));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);	
       }
    }

    @Override
    public List<monthtree> findMonth()
    {
    	String sql="select cutNum,shipplace,year(endtime),month(endtime),day(endtime),treetype,unitprice  from codepage join tree  on codepage.codeid=tree.codeid";
	    ResultSet rs = null;
	    List<monthtree> ts = new ArrayList<monthtree>();	
	    try {
	        connection = dbCon.getConnection();
	        pStatement = connection.prepareStatement(sql);
	        //System.out.println("...." +sql + "...");
	        rs = pStatement.executeQuery();
	        while (rs.next()) {
	        	monthtree s = new monthtree();
	        	s.setCutNum(rs.getDouble(1));
	        	s.setShipplace(rs.getString(2));
				s.setSyear(rs.getDouble(3));
    			s.setSmonth(rs.getDouble(4));
    			s.setSday(rs.getDouble(5));
	        	s.setTreetype(rs.getString(6));
    			s.setUnitprice(rs.getDouble(7));
	        	ts.add(s);
	    }
	     return ts;
      }catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
      	dbCon.closeResultSet(rs);
      	dbCon.closePreparedStatement(pStatement);
      	dbCon.closeConnection(connection);	
     }
    	
    }
    
    //木材销售结算登记表
    @Override
    public monthtree printMonth(int year,int month)
    {
    	String sql = "select cutNum,shipplace,year(endtime),month(endtime),day(endtime),treetype,unitprice  from codepage join tree  on codepage.codeid=tree.codeid";
    	ResultSet rs = null;
    	monthtree d = new monthtree();

    	try {
    		connection = dbCon.getConnection();
    		pStatement = connection.prepareStatement(sql);
    		rs = pStatement.executeQuery();
    		while (rs.next()) {
    			d.setCutNum(rs.getDouble(1));
	        	d.setShipplace(rs.getString(2));
				d.setSyear(rs.getDouble(3));
    			d.setSmonth(rs.getDouble(4));
    			d.setSday(rs.getDouble(5));
	        	d.setTreetype(rs.getString(6));
    			d.setUnitprice(rs.getDouble(7));
    		}
    		return d;
    	}catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);	
       }
    }
    
}
