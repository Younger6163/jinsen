package jinsen.bean;

import java.sql.Timestamp;

public class staff_jobsalary {
	private String staff_id;
	private String staff_post;
	private Double staff_jobsalary_basicpay;
	private String admin_id;
	private Timestamp staff_jobsalary_changetime;
	private String staff_name;
	private String staff_department;
	private String admin_name;
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_department() {
		return staff_department;
	}
	public void setStaff_department(String staff_department) {
		this.staff_department = staff_department;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_post() {
		return staff_post;
	}
	public void setStaff_post(String staff_post) {
		this.staff_post = staff_post;
	}
	public Double getStaff_jobsalary_basicpay() {
		return staff_jobsalary_basicpay;
	}
	public void setStaff_jobsalary_basicpay(Double staff_jobsalary_basicpay) {
		this.staff_jobsalary_basicpay = staff_jobsalary_basicpay;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public Timestamp getStaff_jobsalary_changetime() {
		return staff_jobsalary_changetime;
	}
	public void setStaff_jobsalary_changetime(Timestamp staff_jobsalary_changetime) {
		this.staff_jobsalary_changetime = staff_jobsalary_changetime;
	}
	
	

}
