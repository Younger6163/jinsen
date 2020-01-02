package jinsen.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jinsen.bean.treeprice;



public interface treepriceDao {
	public int addtprice(treeprice c);
	public List<treeprice> findTprice(String sql);
	public treeprice findTreeprice(String sql);
	public boolean updateTprice(treeprice c) throws SQLException;
	
	/*
	 * public List<catUseCar> findCUCBY(int userid);
	 * 
	 * public HashMap<String,catUse> findcar(String sql);
	 */
} 
