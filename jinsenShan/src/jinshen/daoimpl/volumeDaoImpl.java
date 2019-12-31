package jinshen.daoimpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinshen.bean.order;
import jinshen.bean.volume;
import jinshen.dao.volumeDao;
import jinshen.db.DBcon;

public class volumeDaoImpl implements volumeDao{
	private DBcon dbc=new DBcon();
	
	@Override
	public int addVolume(volume cp) {
		String sql="insert into volume values(?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {cp.getTlong(),cp.getTradius(),cp.getMvolume()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public int updateVolume(volume cp) {
		int res=0;
		try {
			String sql="update volume set mvolume=? where tlong=?,tradius=?";
			res=dbc.doUpdate(sql, new Object[] {cp.getMvolume(),cp.getTlong(),cp.getTradius()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}
	
	@Override
	public volume findVolumeSingle(String sql)
	{
		volume addr=new volume();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				addr.setTlong(rs.getDouble(1));
				addr.setTradius(rs.getDouble(2));
				addr.setMvolume(rs.getDouble(3));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}
	
	@Override
	public List<volume> findVolume(String sql) {
		List<volume> addrList=new ArrayList<volume>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				volume addr=new volume();
				addr.setTlong(rs.getDouble(1));
				addr.setTradius(rs.getDouble(2));
				addr.setMvolume(rs.getDouble(3));
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
	public int delVolume(String sql) {
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
		String sql="select max(countid) from order";
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

}
