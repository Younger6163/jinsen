package jinsen.bean;

import java.sql.Timestamp;
import java.util.Date;

public class leavef {
	private int leave_id;
	private String staff_id;
	private String staff_name;
	private Timestamp leave_time;
	private Timestamp leave_times;
	private String leave_reason;
	private String leave_type;
	private String leave_state;
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
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

	public Timestamp getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(Timestamp leave_time) {
		this.leave_time = leave_time;
	}
	public Timestamp getLeave_times() {
		return leave_times;
	}
	public void setLeave_times(Timestamp leave_times) {
		this.leave_times = leave_times;
	}
	public String getLeave_reason() {
		return leave_reason;
	}
	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public String getLeave_state() {
		return leave_state;
	}
	public void setLeave_state(String leave_state) {
		this.leave_state = leave_state;
	}
	
	

}
