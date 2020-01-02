package jinsen.bean;

import java.sql.Timestamp;

public class overtime {
	 private int overtime_id;
	 private String staff_id;
	 private String staff_name;
	 private Timestamp overtime_time;
	 private Timestamp overtime_times;
	 public Timestamp getOvertime_time() {
		return overtime_time;
	}
	public void setOvertime_time(Timestamp overtime_time) {
		this.overtime_time = overtime_time;
	}
	public Timestamp getOvertime_times() {
		return overtime_times;
	}
	public void setOvertime_times(Timestamp overtime_times) {
		this.overtime_times = overtime_times;
	}
	private String overtime_reason;
	 private String overtime_type;
	 private String overtime_state;
	 public int getOvertime_id() {
		return overtime_id;
	}
	public void setOvertime_id(int overtime_id) {
		this.overtime_id = overtime_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getOvertime_reason() {
		return overtime_reason;
	}
	public void setOvertime_reason(String overtime_reason) {
		this.overtime_reason = overtime_reason;
	}
	public String getOvertime_type() {
		return overtime_type;
	}
	public void setOvertime_type(String overtime_type) {
		this.overtime_type = overtime_type;
	}
	public String getOvertime_state() {
		return overtime_state;
	}
	public void setOvertime_state(String overtime_state) {
		this.overtime_state = overtime_state;
	}
}
