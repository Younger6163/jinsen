package jinsen.dao;

import java.sql.SQLException;
import java.util.List;

import jinsen.bean.Position;


public interface PositionDao {
    //显示查询所有职位信息
	public List<Position> allPosition() throws SQLException;
	//获取某个职位信息
	public Position getOnePosition(String id) throws SQLException;
	//增删改通用职位维护
	public boolean addPosition(String sql) throws SQLException;
	//获取部门的编号，通过部门名称定位
	public String getDp_id(String dp_name) throws SQLException;
	//获取公司的编号，通过部门名称定位
	public String getCm_id(String dp_name) throws SQLException;
	//定位ps_id的末端位置
	public String getPs_id() throws SQLException;
	//获取公司编号，通过公司名称定位
	public String getCm_id_cm(String cm_name) throws SQLException;
    //定位dp_id末端位置
	public String getDp_id_dp() throws SQLException;
	//定位cm_id末端位置
	public String getCm_id_end() throws SQLException;
	//获取所有公司信息
	public List<Position> allcompany() throws SQLException;
	//获取所有部门名，通过公司ID
	public List<Position> alldp(String cm_id) throws SQLException;
    //获取岗位名称，通过部门id
	public List<Position> allps(String dp_id) throws SQLException;



}
