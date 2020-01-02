package jinsen.dao;

import java.util.List;

import jinsen.bean.role;
import jinsen.bean.staff;
import jinsen.bean.usermanage;


public interface usermanageDao 
{
	public List<staff> find_allstaff();
	
	public List<usermanage> find_allusermanager();
	
	public boolean add_staff(staff s);
	
	public boolean add_manager(usermanage s);
	
	public boolean delete_staff(String staff_id);
	
	public boolean delete_manager(String staff_id);
	
	public boolean delete_managerstatic(String staff_id);
	
	public boolean lock_manager(String staff_id);
	
	public boolean islock(String staff_id);
	
	public boolean unlock_manager(String staff_id);
	
	public boolean recode(String staff_id);
	
	public usermanage login(usermanage u);
	
	public List<role> find_allrole();
	
	public boolean add_role(role s);
	
	public boolean logintime(usermanage s);
	
	public role find_onerole(String role_num);
	
	public boolean delete_role(int role_num);
	
	public boolean update_role(role s);
}
