package jinsen.daoreal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.count;
import jinsen.bean.order;
import jinsen.dao.orderDao;
import jinsen.db.dbCon;



public class orderDaoImpl implements orderDao{
	private dbCon dbc=new dbCon();
	@Override
	public List<order> findOrder(String sql) {
		List<order> addrList=new ArrayList<order>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				order addr=new order();
				addr.setCountid(rs.getDouble(1));
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
	public int addOrder(order c) {
		String sql="insert into orderpage values(?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getCountid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public order findOrderSingle(String sql) {
		order addr=new order();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setCountid(rs.getDouble(1));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public int delById(String sql) {
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
	public int updateOrder(order c) {
		int res=0;
		try {
			String sql="update order set shipping=?,shipplace=?,finbuyer=? where countid=?";
			res=dbc.doUpdate(sql, new Object[] {c.getCountid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public int updateOrder(String sql)
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
	public int findMaxid(String sql) {
		int num=0;
		try {
			ResultSet rs=dbc.doQuery(sql,new Object[]{});
			if(rs.next()) {
				num=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	@Override
	public count findListCount(String sql)
	{
		count c=new count();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				c.setCodeid(rs.getDouble("codeid"));
				c.setChecknum(rs.getDouble("checknum"));
				c.setCutnum(rs.getDouble("cutnum"));
				c.setFinbuyer(rs.getString("finbuyer"));
				c.setGetperson(rs.getString("getperson"));
				c.setShipping(rs.getString("shipping"));
				c.setShipplace(rs.getString("shipplace"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return c;
	}
	
	@Override
	public List<count> findListCount1(String sql)
	{
		List<count> cc=new ArrayList<count>();
			try {
				ResultSet rs=dbc.doQuery(sql, new Object[] {});
				while(rs.next()) {
					count c=new count();
					c.setCodeid(rs.getDouble("codeid"));
					c.setChecknum(rs.getDouble("checknum"));
					c.setCutnum(rs.getDouble("cutnum"));
					c.setFinbuyer(rs.getString("finbuyer"));
					c.setGetperson(rs.getString("getperson"));
					c.setShipping(rs.getString("shipping"));
					c.setShipplace(rs.getString("shipplace"));
					cc.add(c);
				}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				dbc.close();
			}
	return cc;
	}
}
