package jinsen.dao;

import java.sql.SQLException;
import java.util.List;

import jinsen.bean.overtime;
import jinsen.bean.overtimetype;



public interface overtimeDao {
	public List<overtimetype> gettypeSelect() throws SQLException;//查询加班类型
	public int inserttype(overtimetype s);//添加加班类型
	public List<overtimetype> getidSelect(int id) throws SQLException ;//通过ID查询加班类型
	public int updatetype(overtimetype lea);//更新加班类型
	public int typedelete(int id);//删除加班类型
	public List<overtime> getqjSelect() throws SQLException;//查询加班单
	public int overtimedelete(int id);//删除加班信息
	public int overtimeinsert(overtime s);//添加加班信息
	public List<overtimetype> typeSelect() throws SQLException ;//查询加班类型
	public int updateovertime(overtime lea);//更新加班信息
	public List<overtime> overtimebyidSelect(int id) throws SQLException ;//通过ID查询加班信息
}
