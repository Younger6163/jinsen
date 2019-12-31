package jinsen.daoreal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.treeprice;
import jinsen.dao.treepriceDao;
import jinsen.db.dbCon;


public class treepriceDaoImpl implements treepriceDao{
	private dbCon dbc=new dbCon();
	
	@Override
	public int addtprice(treeprice c) {
		String sql="insert into treeprice values(?,?,?,?)";
		int res=0;
		try {
			res=dbc.doUpdate(sql, new Object[] {c.getTname(),c.getTreeradius(),c.getPrice(),c.getSesson()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return res;
	}

	@Override
	public List<treeprice> findTprice(String sql) {
		List<treeprice> addrList=new ArrayList<treeprice>();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			while(rs.next()) {
				treeprice addr=new treeprice();
				addr.setTname(rs.getString(1));
				addr.setTreeradius(rs.getDouble(2));
				addr.setPrice(rs.getDouble(3));
				addr.setSesson(rs.getDouble(4));
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
	public treeprice findTreeprice(String sql) {
		treeprice addr=new treeprice();
		try {
			ResultSet rs=dbc.doQuery(sql, new Object[] {});
			if(rs.next()) {
				addr.setTname(rs.getString(1));
				addr.setTreeradius(rs.getDouble(2));
				addr.setPrice(rs.getDouble(3));
				addr.setSesson(rs.getDouble(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		return addr;
	}

	@Override
	public boolean updateTprice(treeprice c) throws SQLException {
		int res=0;
		try {
			String sqll="update treeprice set treeradius=?,price=?,sesson=? where tname=?";
			res=dbc.doUpdate(sqll, new Object[] {c.getTreeradius(),c.getPrice(),c.getSesson(),c.getTname()});
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbc.close();
		}
		if(res>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
