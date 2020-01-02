package jinsen.dao;

import java.sql.Date;
import java.util.List;

import jinsen.bean.train;


public interface train_inter {
	public List<train> find_all();//查询所有的员工培训信息
	public  train find_staff_id(String staff_id);//根据员工编号来查
	public  List<train> find_staff_name(String name);//根据员工姓名来查询
	public List<train> train_time(Date date);//根据培训时间查询职工培训
	public boolean insert_train(train tr);//添加培训信息
	public boolean update_train(train tr);//更新培训信息
	public boolean delete_train(String staff_id,Date date);
	public train find_train(String id,Date d);

}
