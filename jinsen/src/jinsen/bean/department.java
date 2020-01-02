package jinsen.bean;

import java.sql.Timestamp;

public class department {
	private String dp_id;
	private String dp_name;
	private String dp_info;
	private double dp_perfor;
	private int staff_num;
	private double salary_all;
	private double salary_yetpay;
	private Timestamp js_time;
	private Timestamp pay_time;
	private String staff_status;
	public int getStaff_num() {
		return staff_num;
	}
	public void setStaff_num(int staff_num) {
		this.staff_num = staff_num;
	}
	public double getSalary_all() {
		return salary_all;
	}
	public void setSalary_all(double salary_all) {
		this.salary_all = salary_all;
	}
	public double getSalary_yetpay() {
		return salary_yetpay;
	}
	public void setSalary_yetpay(double salary_yetpay) {
		this.salary_yetpay = salary_yetpay;
	}
	public Timestamp getJs_time() {
		return js_time;
	}
	public void setJs_time(Timestamp js_time) {
		this.js_time = js_time;
	}
	public Timestamp getPay_time() {
		return pay_time;
	}
	public void setPay_time(Timestamp pay_time) {
		this.pay_time = pay_time;
	}
	public String getStaff_status() {
		return staff_status;
	}
	public void setStaff_status(String staff_status) {
		this.staff_status = staff_status;
	}
	public double getDp_perfor() {
		return dp_perfor;
	}
	public void setDp_perfor(double dp_perfor) {
		this.dp_perfor = dp_perfor;
	}
	public String getDp_id() {
		return dp_id;
	}
	public void setDp_id(String dp_id) {
		this.dp_id = dp_id;
	}
	public String getDp_name() {
		return dp_name;
	}
	public void setDp_name(String dp_name) {
		this.dp_name = dp_name;
	}
	public String getDp_info() {
		return dp_info;
	}
	public void setDp_info(String dp_info) {
		this.dp_info = dp_info;
	}
	
}
