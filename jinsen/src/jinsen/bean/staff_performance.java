package jinsen.bean;

import java.sql.Timestamp;

public class staff_performance {
	private String staff_id;
	private String staff_performance_remark;
	private double staff_perfotmance_coefficient;
	private double staff_performance_kpi;
	private int staff_performance_year;
	private Timestamp staff_performance_changetime;
	private String admin_id;
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_performance_remark() {
		return staff_performance_remark;
	}
	public void setStaff_performance_remark(String staff_performance_remark) {
		this.staff_performance_remark = staff_performance_remark;
	}
	public double getStaff_perfotmance_coefficient() {
		return staff_perfotmance_coefficient;
	}
	public void setStaff_perfotmance_coefficient(double staff_perfotmance_coefficient) {
		this.staff_perfotmance_coefficient = staff_perfotmance_coefficient;
	}
	public double getStaff_performance_kpi() {
		return staff_performance_kpi;
	}
	public void setStaff_performance_kpi(double staff_performance_kpi) {
		this.staff_performance_kpi = staff_performance_kpi;
	}
	public int getStaff_performance_year() {
		return staff_performance_year;
	}
	public void setStaff_performance_year(int staff_performance_year) {
		this.staff_performance_year = staff_performance_year;
	}
	public Timestamp getStaff_performance_changetime() {
		return staff_performance_changetime;
	}
	public void setStaff_performance_changetime(Timestamp staff_performance_changetime) {
		this.staff_performance_changetime = staff_performance_changetime;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	
}
