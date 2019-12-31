package jinsen.dao;

import java.sql.Date;
import java.util.List;

import jinsen.bean.academic_title;



public interface academic_inter {
	public List<academic_title> findall();//查询所有的职工职称信息
	public boolean insert_academic(academic_title ac);//插入职工信息
	public List<academic_title> find_id(String staff_id);//根据员工编号查找个人的职工信息
	public List<academic_title> find_profession_titles(String profession);//根据职工的职称查询
	public List<academic_title> find_staff_name(String name);//根据职工姓名进行查询
	public boolean delete(String staff_id,Date statrt);
	
	

}
