package jinsen.bean;

import java.sql.Timestamp;

public class staff_performance_list {
	private String staff_id;
	private double staff_performance_list_yetpay;
	private double staff_performance_list_residue;
	private String staff_performance_list_status;
	private int staff_performance_list_year;
	private Timestamp staff_performance_list_paytime;
	private String admin_id;
	private double staff_performance_list_total;
	
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public double getStaff_performance_list_yetpay() {
		return staff_performance_list_yetpay;
	}
	public void setStaff_performance_list_yetpay(double staff_performance_list_yetpay) {
		this.staff_performance_list_yetpay = staff_performance_list_yetpay;
	}
	public double getStaff_performance_list_residue() {
		return staff_performance_list_residue;
	}
	public void setStaff_performance_list_residue(double staff_performance_list_residue) {
		this.staff_performance_list_residue = staff_performance_list_residue;
	}
	public String getStaff_performance_list_status() {
		return staff_performance_list_status;
	}
	public void setStaff_performance_list_status(String staff_performance_list_status) {
		this.staff_performance_list_status = staff_performance_list_status;
	}
	public int getStaff_performance_list_year() {
		return staff_performance_list_year;
	}
	public void setStaff_performance_list_year(int staff_performance_list_year) {
		this.staff_performance_list_year = staff_performance_list_year;
	}
	public Timestamp getStaff_performance_list_paytime() {
		return staff_performance_list_paytime;
	}
	public void setStaff_performance_list_paytime(Timestamp staff_performance_list_paytime) {
		this.staff_performance_list_paytime = staff_performance_list_paytime;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public double getStaff_performance_list_total() {
		return staff_performance_list_total;
	}
	public void setStaff_performance_list_total(double staff_performance_list_total) {
		this.staff_performance_list_total = staff_performance_list_total;
	}

}
