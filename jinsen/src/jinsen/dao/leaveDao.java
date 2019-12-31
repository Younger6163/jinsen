package jinsen.dao;

import java.sql.SQLException;
import java.util.List;

import jinsen.bean.leave;
import jinsen.bean.leavetype;



public interface leaveDao {
	public int inserttype(leavetype s);
	public List<leavetype> gettypeSelect() throws SQLException;
	public int typedelete(int id);
	public List<leavetype> getidSelect(int id) throws SQLException;
	public int updatetype(leavetype lea);
	public List<leave> getqjSelect() throws SQLException;
	public int leavedelete(int id);
	public int leaveinsert(leave s);
	public List<leavetype> typeSelect() throws SQLException;
	public int updateleave(leave lea);
	public List<leave> leavebyidSelect(int id) throws SQLException;
}
