package jinsen.bean;

import java.sql.Date;

public class train {
	private String staff_id;
	private String train_name;
	public String getTrain_name() {
		return train_name;
	}
	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}
	private Date train_start_time;
	private Date train_end_time;
	private String train_certificate;
	private Date input_time;
	private String manager;
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public Date getTrain_start_time() {
		return train_start_time;
	}
	public void setTrain_start_time(Date train_start_time) {
		this.train_start_time = train_start_time;
	}
	public Date getTrain_end_time() {
		return train_end_time;
	}
	public void setTrain_end_time(Date train_end_time) {
		this.train_end_time = train_end_time;
	}
	public String getTrain_certificate() {
		return train_certificate;
	}
	public void setTrain_certificate(String train_certificate) {
		this.train_certificate = train_certificate;
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
