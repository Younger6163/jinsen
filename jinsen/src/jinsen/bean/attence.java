package jinsen.bean;

import java.sql.Timestamp;
import java.util.Date;

public class attence {
	private String staff_id;
	private int attence_month;
	private Date attence_date;
	private Timestamp attence_mwtime;
	private Timestamp attence_mwtimes;
	private Timestamp attence_awtime;
	private Timestamp attence_awtimes;
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public int getAttence_month() {
		return attence_month;
	}
	public void setAttence_month(int attence_month) {
		this.attence_month = attence_month;
	}
	public Date getAttence_date() {
		return attence_date;
	}
	public void setAttence_date(Date attence_date) {
		this.attence_date = attence_date;
	}
	public Timestamp getAttence_mwtime() {
		return attence_mwtime;
	}
	public void setAttence_mwtime(Timestamp attence_mwtime) {
		this.attence_mwtime = attence_mwtime;
	}
	public Timestamp getAttence_mwtimes() {
		return attence_mwtimes;
	}
	public void setAttence_mwtimes(Timestamp attence_mwtimes) {
		this.attence_mwtimes = attence_mwtimes;
	}
	public Timestamp getAttence_awtime() {
		return attence_awtime;
	}
	public void setAttence_awtime(Timestamp attence_awtime) {
		this.attence_awtime = attence_awtime;
	}
	public Timestamp getAttence_awtimes() {
		return attence_awtimes;
	}
	public void setAttence_awtimes(Timestamp attence_awtimes) {
		this.attence_awtimes = attence_awtimes;
	}


}
