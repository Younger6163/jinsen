package jinsen.dao;

import java.sql.Date;
import java.util.List;

import jinsen.bean.education;


public interface education_inter {
	public List<education> find_all();//查询所有的学历信息
	public List<education> find_name(String name);//根据员工名字查询学历信息
	public List<education> find_certificate(String certificate);//根据学历多少来查询学历信息
	public List<education> find_staff_id(String staff_id);//根据员工编号来查询
	public List<education> find_staff_date(Date date);//根据入学和毕业时间来查询
	public boolean insert_education(education edu);//插入员工的学历信息
	public boolean delete_education(String staff_id,Date start_time);//根据职工的编号和入学时间来删除信息
	
	
}
