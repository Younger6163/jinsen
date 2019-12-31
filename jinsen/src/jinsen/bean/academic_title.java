package jinsen.bean;

import java.sql.Date;

public class academic_title {
	private String staff_id;//员工工号
	private Date gain_time;//获取职称的时间
	private Date infect_time;//生效时间
	private String profession_titles;//职称名称
	private String certificate_of_title;//职称证明
	private Date input_time;//录入时间
	private String manager;//录入人
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public Date getGain_time() {
		return gain_time;
	}
	public void setGain_time(Date gain_time) {
		this.gain_time = gain_time;
	}
	public Date getInfect_time() {
		return infect_time;
	}
	public void setInfect_time(Date infect_time) {
		this.infect_time = infect_time;
	}
	public String getProfession_titles() {
		return profession_titles;
	}
	public void setProfession_titles(String profession_titles) {
		this.profession_titles = profession_titles;
	}
	public String getCertificate_of_title() {
		return certificate_of_title;
	}
	public void setCertificate_of_title(String certificate_of_title) {
		this.certificate_of_title = certificate_of_title;
	}
	public Date getInput_time() {
		return input_time;
	}
	public void setInput_time(Date inut_time) {
		this.input_time = inut_time;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	

}
