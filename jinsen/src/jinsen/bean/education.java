package jinsen.bean;

import java.sql.Date;

public class education {

	private String staff_id;//员工id；
	private Date enrollment_time;//入学时间
	private Date graduation_time;//毕业时间
	private String certificate;//学历大小
	private String graduation_unit;//毕业学校
	private String staff_certificate;//毕业证明
	private Date input_time;//录入时间
	private String manager;//录入人
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public Date getEnrollment_time() {
		return enrollment_time;
	}
	public void setEnrollment_time(Date enrollment_time) {
		this.enrollment_time = enrollment_time;
	}
	public Date getGraduation_time() {
		return graduation_time;
	}
	public void setGraduation_time(Date graduation_time) {
		this.graduation_time = graduation_time;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getGraduation_unit() {
		return graduation_unit;
	}
	public void setGraduation_unit(String graduation_unit) {
		this.graduation_unit = graduation_unit;
	}
	public String getStaff_certificate() {
		return staff_certificate;
	}
	public void setStaff_certificate(String staff_certificate) {
		this.staff_certificate = staff_certificate;
	}
	public Date getInput_time() {
		return input_time;
	}
	public void setInput_time(Date input_time) {
		this.input_time = input_time;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
}
