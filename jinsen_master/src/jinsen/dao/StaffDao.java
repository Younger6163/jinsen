package jinsen.dao;

import java.sql.SQLException;
import java.util.List;

import jinsen.bean.staff;


public interface StaffDao {
	//员工入职，增加员工
	public boolean addStaff(String sql) throws SQLException;
	//显示所有员工信息
	public List<staff> allStaff() throws SQLException;
    //获取一个员工的信息
	public staff getOneStaff(String id) throws SQLException;

}
