package jinsen.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class usermanage 
{
	private String staff_id;
	private String staff_name;
	private String password;
	private Timestamp createtime;
	private String backups;
	private String power_type;
	private int is_delete;
	private int is_lock;
	private Timestamp logintime;
	private Timestamp exittime;
	public Timestamp getLogintime() {
		return logintime;
	}
	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}
	public Timestamp getExittime() {
		return exittime;
	}
	public void setExittime(Timestamp exittime) {
		this.exittime = exittime;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	public int getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getBackups() {
		return backups;
	}
	public void setBackups(String backups) {
		this.backups = backups;
	}
	public String getPower_type() {
		return power_type;
	}
	public void setPower_type(String power_type) {
		this.power_type = power_type;
	}
	
}
