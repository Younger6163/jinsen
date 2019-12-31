package jinshen.daoimpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import jinshen.bean.tree;
import jinshen.dao.treeDao;
import jinshen.db.DBcon;

public class treeDaoImpl implements treeDao {
	private DBcon dbc=new DBcon();
	
	@Override
	public List<tree> findTree(String sql) {
		List<tree> addrList=new ArrayList<tree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				tree addr=new tree();
				addr.setCodeid(rs.getDouble(1));
				addr.setTreetype(rs.getString(2));
				addr.setTlong(rs.getDouble(3));
				addr.setTradius(rs.getDouble(4));
				addr.setNum(rs.getDouble(5));
				addr.setTvolume(rs.getDouble(6));
				addr.setUnitprice(rs.getDouble(7));
				addr.setTotalnum(rs.getDouble(8));
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
	public int addTree(tree c) {
		String sql="insert into tree values(?,?,?,?,?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getCodeid(),c.getTreetype(),c.getTlong(),c.getTradius(),c.getNum(),c.getTvolume(),c.getUnitprice(),c.getTotalnum()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public tree findTreeSingle(String sql) {
		tree addr=new tree();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setCodeid(rs.getDouble(1));
				addr.setTreetype(rs.getString(2));
				addr.setTlong(rs.getDouble(3));
				addr.setTradius(rs.getDouble(4));
				addr.setNum(rs.getDouble(5));
				addr.setTvolume(rs.getDouble(6));
				addr.setUnitprice(rs.getDouble(7));
				addr.setTotalnum(rs.getDouble(8));
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
	public int updateTree(tree c) {
		int res=0;
		try {
			String sql="update tree set treetype=?,tlong=?,tradius=?,num=?,tvolume=?,untiprice=?,totalnum=? where codeid=?";
			res=dbc.doUpdate(sql, new Object[] {c.getTreetype(),c.getTlong(),c.getTradius(),c.getNum(),c.getTvolume(),c.getUnitprice(),c.getTotalnum(),c.getCodeid()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int findMaxid() {
		String sql="select max(codeid) from tree";
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
	public int updateTree(String sql)
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

}
