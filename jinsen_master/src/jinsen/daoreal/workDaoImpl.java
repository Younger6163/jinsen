package jinsen.daoreal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.order;
import jinsen.bean.work;
import jinsen.bean.worktree;
import jinsen.dao.workDao;
import jinsen.db.dbCon;



public class workDaoImpl implements workDao {

	private dbCon dbc=new dbCon();
	@Override
	public int addWork(work cp) {
		String sql="insert into work values(?,?,?,?)";
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
	public int addWork(String sql)
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
	public int updateWork(work cp) {
		int res=0;
		try {
			String sql="update order set shipping=?,shipplace=?,finbuyer=? where countid=?";
			res=dbc.doUpdate(sql, new Object[] {});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<work> findWork(String sql) {
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
		return null;
	}

	@Override
	public int delWork(String sql) {
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
	public work findWorkSingle(String sql)
	{
		work addr=new work();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setWorkid(rs.getDouble(1));
				addr.setCheckNum(rs.getDouble(2));
				addr.setCutNum(rs.getDouble(3));
				addr.setForperson(rs.getString(4));
				addr.setManageUnit(rs.getString(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return addr;
	}
	
	@Override
	public List<work> findWorkList(String sql)
	{
		List<work> work=new ArrayList<work>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				work addr=new work();
				addr.setWorkid(rs.getDouble(1));
				addr.setCheckNum(rs.getDouble(2));
				addr.setCutNum(rs.getDouble(3));
				addr.setForperson(rs.getString(4));
				addr.setManageUnit(rs.getString(5));
				work.add(addr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.close();
		}
		return work;
	}
	
	@Override
	public int update(String sql)
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
	public List<worktree> findworktree(String sql)
	{
		List<worktree> work=new ArrayList<worktree>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()){
				worktree w=new worktree();
				w.setType(rs.getString(1));
				w.setTvolume(rs.getDouble(2));
				w.setUnitprice(rs.getDouble(3));
				w.setPrice(rs.getDouble(4));
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
	public double findcount(String sql)
	{
		double res=0;
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			res=rs.getDouble(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

}
