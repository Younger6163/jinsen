package jinsen.daoreal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.attence;
import jinsen.bean.depart_performance;
import jinsen.bean.department;
import jinsen.bean.education;
import jinsen.bean.leavef;
import jinsen.bean.overtime;
import jinsen.bean.post;
import jinsen.bean.staff;
import jinsen.bean.staff_jobsalary;
import jinsen.bean.staff_performance;
import jinsen.bean.staff_performance_list;
import jinsen.bean.staff_salary_list;
import jinsen.dao.salary_inter;
import jinsen.db.dbCon;
public class daoreal implements salary_inter {
    Connection connection = null;
    PreparedStatement pStatement = null;
	
	@Override
	public List<staff> find_staff_all(){
		String sql = "select * from staff";
        ResultSet rs = null;
        List<staff> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff>();
            while (rs.next()) {
            	staff staffs = new staff();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setStaff_sex(rs.getString("staff_sex"));
                staffs.setCm_id(rs.getString("cm_id"));
                staffs.setPs_id(rs.getString("ps_id"));
                staffs.setStaff_born(rs.getDate("staff_born"));
                staffs.setStaff_province(rs.getString("staff_province"));
                staffs.setStaff_city(rs.getString("staff_city"));
                staffs.setStaff_county(rs.getString("staff_county"));
                staffs.setStaff_address(rs.getString("staff_address"));
                staffs.setStaff_idnumber(rs.getString("staff_idnumber"));
                staffs.setStaff_time(rs.getDate("staff_time"));
                staffs.setStaff_phone(rs.getString("staff_phone"));
                staffs.setStaff_pic(rs.getString("staff_pic"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
		
	}
	@Override
	public List<staff> find_staff_all_nosalary()
	{
		String sql = "select * from staff where staff_id not in (select staff_id from staff_jobsalary)";
        ResultSet rs = null;
        List<staff> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff>();
            while (rs.next()) {
            	staff staffs = new staff();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setStaff_sex(rs.getString("staff_sex"));
                staffs.setCm_id(rs.getString("cm_id"));
                staffs.setPs_id(rs.getString("ps_id"));
                staffs.setStaff_born(rs.getDate("staff_born"));
                staffs.setStaff_province(rs.getString("staff_province"));
                staffs.setStaff_city(rs.getString("staff_city"));
                staffs.setStaff_county(rs.getString("staff_county"));
                staffs.setStaff_address(rs.getString("staff_address"));
                staffs.setStaff_idnumber(rs.getString("staff_idnumber"));
                staffs.setStaff_time(rs.getDate("staff_time"));
                staffs.setStaff_phone(rs.getString("staff_phone"));
                staffs.setStaff_pic(rs.getString("staff_pic"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
		
	}
	
	
	@Override
	public List<staff> find_staff_id(String id){
		String sql = "select * from staff where staff_id='"+id+"'";
        ResultSet rs = null;
        List<staff> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff>();
            while (rs.next()) {
            	staff staffs = new staff();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setStaff_sex(rs.getString("staff_sex"));
                staffs.setCm_id(rs.getString("cm_id"));
                staffs.setPs_id(rs.getString("ps_id"));
                staffs.setStaff_born(rs.getDate("staff_born"));
                staffs.setStaff_province(rs.getString("staff_province"));
                staffs.setStaff_city(rs.getString("staff_city"));
                staffs.setStaff_county(rs.getString("staff_county"));
                staffs.setStaff_address(rs.getString("staff_address"));
                staffs.setStaff_idnumber(rs.getString("staff_idnumber"));
                staffs.setStaff_time(rs.getDate("staff_time"));
                staffs.setStaff_phone(rs.getString("staff_phone"));
                staffs.setStaff_pic(rs.getString("staff_pic"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
		
	}
	
	@Override
	public Double find_staff_basicpay(String id) {
		String sql = "select basicpay from basicpay where staff_post = (select staff_post from staff where staff_id='"+id+"')";
        ResultSet rs = null;
        Double basicpay = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	basicpay = rs.getDouble("basicpay");
            }
            return basicpay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int insert_staff_salary(String id,String post,Double salary,String admin_id) {
        String sql = "insert into staff_jobsalary values(?,?,?,?,NOW())";
        int count = -1;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, id);
            pStatement.setString(2, post);
            pStatement.setDouble(3, salary);
            pStatement.setString(4, admin_id);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_jobsalary> find_staff_salary(){
		String sql = "select staff_jobsalary.staff_id,staff.staff_name,staff.dp_id,staff_jobsalary.staff_post,staff_jobsalary.staff_jobsalary_basicpay,department.dp_name from staff INNER JOIN staff_jobsalary ON staff_jobsalary.staff_id = staff.staff_id INNER JOIN department ON staff.dp_id=department.dp_id";
        ResultSet rs = null;
        List<staff_jobsalary> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff_jobsalary>();
            while (rs.next()) {
            	staff_jobsalary staffs = new staff_jobsalary();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setStaff_department(rs.getString("dp_name"));
                staffs.setStaff_post(rs.getString("staff_post"));
                staffs.setStaff_jobsalary_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_jobsalary> find_staff_salary_adjust_name(String name)
	{
		String sql = "select staff_jobsalary.staff_id,staff.staff_name,staff.dp_id,staff_jobsalary.staff_post,staff_jobsalary.staff_jobsalary_basicpay,department.dp_name from staff INNER JOIN staff_jobsalary ON staff_jobsalary.staff_id = staff.staff_id INNER JOIN department ON staff.dp_id=department.dp_id where staff.staff_name='"+name+"'";
        ResultSet rs = null;
        List<staff_jobsalary> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff_jobsalary>();
            while (rs.next()) {
            	staff_jobsalary staffs = new staff_jobsalary();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setStaff_department(rs.getString("dp_name"));
                staffs.setStaff_post(rs.getString("staff_post"));
                staffs.setStaff_jobsalary_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_salary_basicpay(Double salary,String id) {
        String sql = "update staff_jobsalary set staff_jobsalary_basicpay = "+salary+" where staff_id='"+id+"'";
        int count = -1;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_jobsalary> find_staff_salary_name(String name){
		String sql = "select staff_jobsalary.staff_id,staff.staff_name,staff.,staff_jobsalary.staff_post,staff_jobsalary.staff_jobsalary_basicpay from staff_jobsalary INNER JOIN staff ON staff_jobsalary.staff_id = staff.staff_id where staff.staff_name='"+name+"'";
        ResultSet rs = null;
        List<staff_jobsalary> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff_jobsalary>();
            while (rs.next()) {
            	staff_jobsalary staffs = new staff_jobsalary();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setStaff_department(rs.getString(""));
                staffs.setStaff_post(rs.getString("staff_post"));
                staffs.setStaff_jobsalary_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_jobsalary> find_staff_salary_table()
	{
		String sql = "select staff_jobsalary.staff_id,staff.staff_name,staff.dp_id,staff_jobsalary.staff_post,staff_jobsalary.staff_jobsalary_basicpay,staff_jobsalary.admin_id,staff_jobsalary.staff_jobsalary_changetime,department.dp_name from staff INNER JOIN staff_jobsalary ON staff_jobsalary.staff_id = staff.staff_id INNER JOIN department ON staff.dp_id = department.dp_id";
        ResultSet rs = null;
        List<staff_jobsalary> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff_jobsalary>();
            while (rs.next()) {
            	staff_jobsalary staffs = new staff_jobsalary();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setStaff_department(rs.getString("dp_name"));
                staffs.setStaff_post(rs.getString("staff_post"));
                staffs.setStaff_jobsalary_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                staffs.setAdmin_id(rs.getString("admin_id"));
                staffs.setStaff_jobsalary_changetime(rs.getTimestamp("staff_jobsalary_changetime"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public String find_admin_name(String admin_id)
	{
		String sql = "select staff_name from staff where staff_id='"+admin_id+"'";
        ResultSet rs1 = null;
        String staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs1 = pStatement.executeQuery();
            while (rs1.next()) {
            	staff = rs1.getString(1);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs1);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_staff_salary(String staff_id,double staffsalary)
	{
		String sql = "update staff_jobsalary set staff_jobsalary_basicpay = "+staffsalary+" where staff_id='"+staff_id+"'";
		int count = -1;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public staff_jobsalary find_jobsalary_sa(String staff_id)
	{
		String sql = "select * from staff_jobsalary INNER JOIN staff ON staff.staff_id = staff_jobsalary.staff_id where staff_jobsalary.staff_id = '"+staff_id+"'";
        ResultSet rs = null;
        staff_jobsalary staffs = new staff_jobsalary();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_post(rs.getString("staff_post"));
                staffs.setStaff_jobsalary_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                staffs.setStaff_name(rs.getString("staff_name"));
            }
            return staffs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	@Override
	public List<staff_salary_list> find_staff_list_wff()
	{
		String sql = "select * from staff_salary_list INNER JOIN staff ON staff_salary_list.staff_id = staff.staff_id INNER JOIN staff_jobsalary ON staff_salary_list.staff_id = staff_jobsalary.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where staff_salary_list.staff_salary_list_status='未发放' or staff_salary_list.staff_salary_list_status='未发完'";
        ResultSet rs = null;
        List<staff_salary_list> stafflists = new ArrayList<staff_salary_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_salary_list stafflist = new staff_salary_list();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setStaff_salary_list_attendday(rs.getDouble("staff_salary_list_attendday"));
                stafflist.setStaff_salary_list_offnum(rs.getDouble("staff_salary_list_offnum"));
                stafflist.setStaff_salary_list_absentnum(rs.getDouble("staff_salary_list_absentnum"));
                stafflist.setStaff_salary_list_deductmoney(rs.getDouble("staff_salary_list_deductmoney"));
                stafflist.setStaff_salary_list_ssalary(rs.getDouble("staff_salary_list_ssalary"));
                stafflist.setStaff_salary_list_netpayroll(rs.getDouble("staff_salary_list_netpayroll"));
                stafflist.setStaff_salary_list_yetpay(rs.getDouble("staff_salary_list_yetpay"));
                stafflist.setStaff_salary_list_residue(rs.getDouble("staff_salary_list_residue"));
                stafflist.setStaff_salary_list_stockdater(rs.getDate("staff_salary_list_stockdater"));
                stafflist.setStaff_salary_list_status(rs.getString("staff_salary_list_status"));
                stafflist.setStaff_salary_list_remark(rs.getString("staff_salary_list_remark"));
                stafflist.setStaff_salary_list_paytime(rs.getTimestamp("staff_salary_list_paytime"));
                stafflist.setStaff_name(rs.getString("staff_name"));
                stafflist.setStaff_department(rs.getString("dp_name"));
                stafflist.setStaff_post(rs.getString("staff_post"));
                stafflist.setStaff_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                stafflist.setStaff_salary_list_overtime(rs.getDouble("staff_salary_list_overtime"));
                stafflist.setStaff_salary_list_overtimesalary(rs.getDouble("staff_salary_list_overtimesalary"));
                stafflist.setMonth(rs.getInt("staff_salary_list_month"));
                stafflist.setYear(rs.getInt("staff_salary_list_year"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_salary_list> find_staff_salary_list()
	{
		String sql = "select * from staff INNER JOIN staff_salary_list ON staff_salary_list.staff_id = staff.staff_id INNER JOIN staff_jobsalary ON staff_salary_list.staff_id = staff_jobsalary.staff_id INNER JOIN department ON department.dp_id = staff.dp_id";
        ResultSet rs = null;
        List<staff_salary_list> stafflists = new ArrayList<staff_salary_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_salary_list stafflist = new staff_salary_list();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setStaff_salary_list_attendday(rs.getDouble("staff_salary_list_attendday"));
                stafflist.setStaff_salary_list_offnum(rs.getDouble("staff_salary_list_offnum"));
                stafflist.setStaff_salary_list_absentnum(rs.getDouble("staff_salary_list_absentnum"));
                stafflist.setStaff_salary_list_deductmoney(rs.getDouble("staff_salary_list_deductmoney"));
                stafflist.setStaff_salary_list_ssalary(rs.getDouble("staff_salary_list_ssalary"));
                stafflist.setStaff_salary_list_netpayroll(rs.getDouble("staff_salary_list_netpayroll"));
                stafflist.setStaff_salary_list_yetpay(rs.getDouble("staff_salary_list_yetpay"));
                stafflist.setStaff_salary_list_residue(rs.getDouble("staff_salary_list_residue"));
                stafflist.setStaff_salary_list_stockdater(rs.getDate("staff_salary_list_stockdater"));
                stafflist.setStaff_salary_list_status(rs.getString("staff_salary_list_status"));
                stafflist.setStaff_salary_list_remark(rs.getString("staff_salary_list_remark"));
                stafflist.setStaff_salary_list_paytime(rs.getTimestamp("staff_salary_list_paytime"));
                stafflist.setStaff_name(rs.getString("staff_name"));
                stafflist.setStaff_department(rs.getString("dp_name"));
                stafflist.setStaff_post(rs.getString("staff_post"));
                stafflist.setStaff_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                stafflist.setStaff_salary_list_overtime(rs.getDouble("staff_salary_list_overtime"));
                stafflist.setStaff_salary_list_overtimesalary(rs.getDouble("staff_salary_list_overtimesalary"));
                stafflist.setYear(rs.getInt("staff_salary_list_year"));
                stafflist.setMonth(rs.getInt("staff_salary_list_month"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_salary_list> find_staff_salary_list_m_y(int year,int month)
	{
		String sql = "select * from staff INNER JOIN staff_salary_list ON staff_salary_list.staff_id = staff.staff_id INNER JOIN staff_jobsalary ON staff_salary_list.staff_id = staff_jobsalary.staff_id INNER JOIN department ON department.dp_id = staff.dp_id where staff_salary_list.staff_salary_list_year = "+year+" and staff_salary_list.staff_salary_list_month = "+month;
        ResultSet rs = null;
        List<staff_salary_list> stafflists = new ArrayList<staff_salary_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_salary_list stafflist = new staff_salary_list();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setStaff_salary_list_attendday(rs.getDouble("staff_salary_list_attendday"));
                stafflist.setStaff_salary_list_offnum(rs.getDouble("staff_salary_list_offnum"));
                stafflist.setStaff_salary_list_absentnum(rs.getDouble("staff_salary_list_absentnum"));
                stafflist.setStaff_salary_list_deductmoney(rs.getDouble("staff_salary_list_deductmoney"));
                stafflist.setStaff_salary_list_ssalary(rs.getDouble("staff_salary_list_ssalary"));
                stafflist.setStaff_salary_list_netpayroll(rs.getDouble("staff_salary_list_netpayroll"));
                stafflist.setStaff_salary_list_yetpay(rs.getDouble("staff_salary_list_yetpay"));
                stafflist.setStaff_salary_list_residue(rs.getDouble("staff_salary_list_residue"));
                stafflist.setStaff_salary_list_stockdater(rs.getDate("staff_salary_list_stockdater"));
                stafflist.setStaff_salary_list_status(rs.getString("staff_salary_list_status"));
                stafflist.setStaff_salary_list_remark(rs.getString("staff_salary_list_remark"));
                stafflist.setStaff_salary_list_paytime(rs.getTimestamp("staff_salary_list_paytime"));
                stafflist.setStaff_name(rs.getString("staff_name"));
                stafflist.setStaff_department(rs.getString("dp_name"));
                stafflist.setStaff_post(rs.getString("staff_post"));
                stafflist.setStaff_basicpay(rs.getDouble("staff_jobsalary_basicpay"));
                stafflist.setStaff_salary_list_overtime(rs.getDouble("staff_salary_list_overtime"));
                stafflist.setStaff_salary_list_overtimesalary(rs.getDouble("staff_salary_list_overtimesalary"));
                stafflist.setYear(rs.getInt("staff_salary_list_year"));
                stafflist.setMonth(rs.getInt("staff_salary_list_month"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<attence> find_attence_month(int month)
	{
		String sql = "select * from attence where attence_month = "+month;
        ResultSet rs = null;
        List<attence> stafflists = new ArrayList<attence>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	attence stafflist = new attence();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setAttence_month(rs.getInt("attence_month"));
                stafflist.setAttence_date(rs.getDate("attence_date"));
                stafflist.setAttence_mwtime(rs.getTimestamp("attence_mwtime"));
                stafflist.setAttence_mwtimes(rs.getTimestamp("attence_mwtimes"));
                stafflist.setAttence_awtime(rs.getTimestamp("attence_awtime"));
                stafflist.setAttence_awtimes(rs.getTimestamp("attence_awtimes"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int insert_staff_salary_list(List<staff_salary_list> salarylist)
	{
		int count = 0;
        try {
    		for(int i = 0;i<salarylist.size();i ++) {
    		String sql = "insert into staff_salary_list values(?,?,?,?,?,?,?,0,?,NOW(),'未发放',NULL,NULL,?,?,?,?)";
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, salarylist.get(i).getStaff_id());
            pStatement.setDouble(2, salarylist.get(i).getStaff_salary_list_attendday());
            pStatement.setDouble(3, salarylist.get(i).getStaff_salary_list_offnum());
            pStatement.setDouble(4, salarylist.get(i).getStaff_salary_list_absentnum());
            pStatement.setDouble(5, salarylist.get(i).getStaff_salary_list_deductmoney());
            pStatement.setDouble(6, salarylist.get(i).getStaff_salary_list_ssalary());
            pStatement.setDouble(7, salarylist.get(i).getStaff_salary_list_netpayroll());
            pStatement.setDouble(8, salarylist.get(i).getStaff_salary_list_residue());
            pStatement.setDouble(9, salarylist.get(i).getStaff_salary_list_overtime());
            pStatement.setDouble(10, salarylist.get(i).getStaff_salary_list_overtimesalary());
            pStatement.setInt(11, salarylist.get(i).getYear());
            pStatement.setInt(12, salarylist.get(i).getMonth());
            count = count + pStatement.executeUpdate();
    		}
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<leavef> find_leave_staff_id(String staff_id)
	{
		String sql = "select * from leavef where staff_id = '"+staff_id+"'";
        ResultSet rs = null;
        List<leavef> stafflists = new ArrayList<leavef>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	leavef stafflist = new leavef();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setLeave_id(rs.getInt("leave_id"));
                stafflist.setStaff_name(rs.getString("staff_name"));
                stafflist.setLeave_time(rs.getTimestamp("leave_time"));
                stafflist.setLeave_times(rs.getTimestamp("leave_times"));
                stafflist.setLeave_reason(rs.getString("leave_reason"));
                stafflist.setLeave_type(rs.getString("leave_type"));
                stafflist.setLeave_state(rs.getString("leave_state"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	@Override
	public List<department> find_department_all()
	{
		String sql = "select * from department";
        ResultSet rs = null;
        List<department> stafflists = new ArrayList<department>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	department stafflist = new department();
                stafflist.setDp_id(rs.getString("dp_id"));
                stafflist.setDp_name(rs.getString("dp_name"));
                stafflist.setDp_info(rs.getString("dp_info"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	@Override
	public double find_ready_salary(int year)
	{
		//System.out.println(year);
		String sql = "select sum(staff_salary_list_residue) from staff_salary_list where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year;
        ResultSet rs = null;
        double count = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	count = rs.getDouble(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public double find_ready_salary_three(int year,String department,int month)
	{
		String sql = "";
		if(department.equals("所有部门") && month==-1)
			sql = "select sum(staff_salary_list_residue) from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year;
		else if(department.equals("所有部门")==false && month!=-1)
			sql = "select sum(staff_salary_list_residue) from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year+" and department.dp_name='"+department+"' and staff_salary_list_month ="+month;
		else if(department.equals("所有部门") && month!=-1)
			sql = "select sum(staff_salary_list_residue) from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year+" and staff_salary_list_month ="+month;
		else
			sql = "select sum(staff_salary_list_residue) from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year+" and department.dp_name='"+department+"'";
        ResultSet rs = null;
        double count = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	count = rs.getDouble(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_salary(String staff_id,int year,int month,double salary,int flag)
	{
		String sql = "";
		if(flag == -1)
			sql = "update staff_salary_list set staff_salary_list_yetpay = staff_salary_list_yetpay +"+salary+",staff_salary_list_residue = staff_salary_list_residue - "+salary+",staff_salary_list_status='未发完',staff_salary_list_paytime=NOW() where staff_id = "+staff_id+" and staff_salary_list_year = "+year+" and staff_salary_list_month = "+month;
		else if(month == -1)
			sql = "update staff_salary_list set staff_salary_list_yetpay = staff_salary_list_yetpay +"+salary+",staff_salary_list_residue = staff_salary_list_residue - "+salary+",staff_salary_list_status='未发完',staff_salary_list_paytime=NOW() where staff_id = "+staff_id+" and staff_salary_list_year = "+year;
		else
			sql = "update staff_salary_list set staff_salary_list_yetpay = staff_salary_list_yetpay +"+salary+",staff_salary_list_residue = staff_salary_list_residue - "+salary+",staff_salary_list_status='已发完',staff_salary_list_paytime=NOW() where staff_id = "+staff_id+" and staff_salary_list_year = "+year+" and staff_salary_list_month = "+month;
		int count = -1;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_salary_list_status(String staff_id,int year,int month,String str)
	{
		String sql = "update staff_salary_list set staff_salary_list_status = '"+str+"' where staff_id = '"+staff_id+"' and staff_salary_list_year = "+year+" and staff_salary_list_month = "+month;
		int count  = -1;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_salary_list> find_ready_salary_three_1(int year,String department,int month)
	{
		String sql = "";
		if(department.equals("所有部门") && month==-1)
			sql = "select * from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year;
		else if(department.equals("所有部门")==false && month!=-1)
			sql = "select * from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year+" and department.dp_name='"+department+"' and staff_salary_list_month ="+month;
		else if(department.equals("所有部门") && month!=-1)
			sql = "select * from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year+" and staff_salary_list_month ="+month;
		else
			sql = "select * from staff_salary_list INNER JOIN staff ON staff.staff_id = staff_salary_list.staff_id INNER JOIN department ON staff.dp_id = department.dp_id where (staff_salary_list_status='未发放' or staff_salary_list_status='未发完') and staff_salary_list_year = "+year+" and department.dp_name='"+department+"'";
        ResultSet rs = null;
        List<staff_salary_list> stafflists = new ArrayList<staff_salary_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_salary_list stafflist = new staff_salary_list();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setStaff_salary_list_attendday(rs.getDouble("staff_salary_list_attendday"));
                stafflist.setStaff_salary_list_offnum(rs.getDouble("staff_salary_list_offnum"));
                stafflist.setStaff_salary_list_absentnum(rs.getDouble("staff_salary_list_absentnum"));
                stafflist.setStaff_salary_list_deductmoney(rs.getDouble("staff_salary_list_deductmoney"));
                stafflist.setStaff_salary_list_ssalary(rs.getDouble("staff_salary_list_ssalary"));
                stafflist.setStaff_salary_list_netpayroll(rs.getDouble("staff_salary_list_netpayroll"));
                stafflist.setStaff_salary_list_yetpay(rs.getDouble("staff_salary_list_yetpay"));
                stafflist.setStaff_salary_list_residue(rs.getDouble("staff_salary_list_residue"));
                stafflist.setStaff_salary_list_stockdater(rs.getDate("staff_salary_list_stockdater"));
                stafflist.setStaff_salary_list_status(rs.getString("staff_salary_list_status"));
                stafflist.setStaff_salary_list_remark(rs.getString("staff_salary_list_remark"));
                stafflist.setStaff_salary_list_paytime(rs.getTimestamp("staff_salary_list_paytime"));
                stafflist.setStaff_salary_list_overtime(rs.getDouble("staff_salary_list_overtime"));
                stafflist.setYear(rs.getInt("staff_salary_list_year"));
                stafflist.setMonth(rs.getInt("staff_salary_list_month"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return stafflists;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public education find_education_all(String staff_id)
	{
		String sql = "select * from education where staff_id='"+staff_id+"'";
        ResultSet rs = null;
        education education = new education();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	education.setStaff_id(rs.getString("staff_id"));
            	education.setEnrollment_time(rs.getDate("enrollment_time"));
            	education.setGraduation_time(rs.getDate("graduation_time"));
            	education.setCertificate(rs.getString("certificate"));
            	education.setGraduation_unit(rs.getString("graduation_unit"));
            	education.setStaff_certificate(rs.getString("staff_certificate"));
            	education.setInput_time(rs.getDate("input_time"));
            	education.setManager(rs.getString("manager"));
            }
            return education;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_performance> find_staff_perfor_all(int year)
	{
		String sql = "select * from staff_performance where staff_performance_year="+year;
        ResultSet rs = null;
        List<staff_performance> stafflists = new ArrayList<staff_performance>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_performance stafflist = new staff_performance();
                stafflist.setStaff_id(rs.getString("staff_id"));
                stafflist.setStaff_performance_remark(rs.getString("staff_performance_remark"));
                stafflist.setStaff_perfotmance_coefficient(rs.getDouble("staff_performance_coefficient"));
                stafflist.setStaff_performance_kpi(rs.getDouble("staff_performance_kpi"));
                stafflist.setStaff_performance_year(rs.getInt("staff_performance_year"));
                stafflist.setStaff_performance_changetime(rs.getTimestamp("staff_performance_changetime"));
                stafflist.setAdmin_id(rs.getString("admin_id"));
                stafflists.add(stafflist);
            }
            return stafflists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	@Override
	public List<staff> find_staff_not_perfor(int year)
	{
		String sql = "select staff.* from staff where staff_id not in (select staff_id from staff_performance where staff_performance_year = "+year+")";
        ResultSet rs = null;
        List<staff> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff>();
            while (rs.next()) {
            	staff staffs = new staff();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setStaff_sex(rs.getString("staff_sex"));
                staffs.setCm_id(rs.getString("cm_id"));
                staffs.setPs_id(rs.getString("ps_id"));
                staffs.setStaff_born(rs.getDate("staff_born"));
                staffs.setStaff_province(rs.getString("staff_province"));
                staffs.setStaff_city(rs.getString("staff_city"));
                staffs.setStaff_county(rs.getString("staff_county"));
                staffs.setStaff_address(rs.getString("staff_address"));
                staffs.setStaff_idnumber(rs.getString("staff_idnumber"));
                staffs.setStaff_time(rs.getDate("staff_time"));
                staffs.setStaff_phone(rs.getString("staff_phone"));
                staffs.setStaff_pic(rs.getString("staff_pic"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int find_staff_work_num(String id,int year)
	{
		String sql = "select count(distinct attence_month) from attence where staff_id = '"+id+"' and YEAR(attence_date) = "+year;
        ResultSet rs = null;
        int count = 0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<attence> find_attence_month(String staff_id,int year)
	{
		String sql = "select distinct attence_month from attence where staff_id = '"+staff_id+"' and YEAR(attence_date) = "+year;
        ResultSet rs = null;
        List<attence> as = new ArrayList<attence>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	attence a = new attence();
            	a.setAttence_month(rs.getInt("attence_month"));
            	as.add(a);
            }
            return as;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int insert_staff_perfor(staff_performance staffperfor)
	{
		int count = 0;
        try {
    		String sql = "insert into staff_performance values(?,?,?,?,?,NOW(),?)";
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, staffperfor.getStaff_id());
            pStatement.setString(2, staffperfor.getStaff_performance_remark());
            pStatement.setDouble(3, staffperfor.getStaff_perfotmance_coefficient());
            pStatement.setDouble(4, staffperfor.getStaff_performance_kpi());
            pStatement.setInt(5, staffperfor.getStaff_performance_year());
            pStatement.setString(6, staffperfor.getAdmin_id());
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<department> find_depart(int year)
	{
		String sql = "select * from department where dp_id not in (select dp_id from depart_performance where depart_perfor_year = "+year+")";
        ResultSet rs = null;
        List<department> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<department>();
            while (rs.next()) {
            	department staffs = new department();
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setDp_name(rs.getString("dp_name"));
                staffs.setDp_info(rs.getString("dp_info"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	@Override
	public department find_depart_id(String dp_id)
	{
		String sql = "select * from department where dp_id ='"+dp_id+"'";
        ResultSet rs = null;
        department staffs = new department();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setDp_name(rs.getString("dp_name"));
                staffs.setDp_info(rs.getString("dp_info"));
            }
            return staffs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public department find_depart_name(String dp_name)
	{
		String sql = "select * from department where dp_name ='"+dp_name+"'";
        ResultSet rs = null;
        department staffs = new department();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setDp_name(rs.getString("dp_name"));
                staffs.setDp_info(rs.getString("dp_info"));
            }
            return staffs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	@Override
	public double find_dp_perfor(String dp_id,int year)
	{
		String sql = "select sum(staff_performance_coefficient) from staff_performance where staff_id in (select staff_id from staff where dp_id= (select dp_id from department where dp_name ='"+dp_id+"')) and staff_performance_year = "+year;
        ResultSet rs = null;
        double count = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                count = rs.getDouble(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<overtime> find_overtime_id(String staff_id)
	{
		String sql = "select * from overtime where staff_id = '"+ staff_id +"' and overtime_state='通过'";
        ResultSet rs = null;
        List<overtime> overtimes = new ArrayList<overtime>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                overtime o = new overtime();
                o.setStaff_id(rs.getString("staff_id"));
                o.setOvertime_time(rs.getTimestamp("overtime_time"));
                o.setOvertime_times(rs.getTimestamp("overtime_times"));
                o.setOvertime_reason(rs.getString("overtime_reason"));
                o.setOvertime_type(rs.getString("overtime_type"));
                o.setOvertime_state(rs.getString("overtime_state"));
                overtimes.add(o);
            }
            return overtimes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public double find_stand_type(String type)
	{
		String sql = "select overtimetype_stand from overtimetype where overtimetype_type = '"+type+"'";
        ResultSet rs = null;
        double count = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                count = rs.getDouble(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int insert_dp_perfor(depart_performance depart)
	{
		int count = 0;
        try {
    		String sql = "insert into depart_performance values(?,?,?,0,0,0,?,?,?)";
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, depart.getDp_id());
            pStatement.setDouble(2, depart.getDepart_performance());
            pStatement.setDouble(3, depart.getDepart_kpi());
            pStatement.setInt(4, depart.getDepart_perfor_year());
            pStatement.setDouble(5, depart.getDepart_perfor_score());
            pStatement.setString(6, depart.getDepart_remark());
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public post find_post_psid(String ps_id)
	{
		String sql = "select * from post where ps_id = '"+ps_id+"'";
        ResultSet rs = null;
        post o = new post();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                o.setPs_id(rs.getString("ps_id"));
                o.setPs_name(rs.getString("ps_name"));
                o.setPs_info(rs.getString("ps_info"));
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<department> find_dp_name_all()
	{
		String sql = "select * from department ";
        ResultSet rs = null;
        List<department> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<department>();
            while (rs.next()) {
            	department staffs = new department();
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setDp_name(rs.getString("dp_name"));
                staffs.setDp_info(rs.getString("dp_info"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<department> find_dp_performan_isall(int year)
	{
		String sql = "select * from department where dp_id not in (select dp_id from depart_performance where depart_perfor_year = "+year +")";
        ResultSet rs = null;
        List<department> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<department>();
            while (rs.next()) {
            	department staffs = new department();
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setDp_name(rs.getString("dp_name"));
                staffs.setDp_info(rs.getString("dp_info"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public double find_sum_depart_perfor_all(int year)
	{
		String sql = "select sum(depart_perfor_score) from depart_performance where depart_perfor_year = "+year;
        ResultSet rs = null;
        double depart_perfor_score = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	depart_perfor_score = rs.getDouble(1);
            }
            return depart_perfor_score;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public depart_performance find_depart_perfor_name(String dp_name,int year)
	{
		String sql = "select * from depart_performance where depart_perfor_year = "+year+" and dp_id = (select dp_id from department where dp_name = '"+dp_name+"')";
        ResultSet rs = null;
        depart_performance depart = new depart_performance();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	depart.setDepart_perfor_score(rs.getDouble("depart_perfor_score"));
            }
            return depart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_depart_perfor(double depart_perfor_money,String dp_name,int year)
	{
		int count = 0;
        try {
    		String sql = "update depart_performance set depart_perfor_total = "+depart_perfor_money+",depart_perfor_residue = "+depart_perfor_money +" where depart_perfor_year="+year+" and dp_id = (select dp_id from department where dp_name='"+dp_name+"')";
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<depart_performance> find_depart_perfor(int year)
	{
		String sql = "select * from depart_performance where depart_perfor_year = "+year;
        ResultSet rs = null;
        List<depart_performance> depart = new ArrayList<depart_performance>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	depart_performance d = new depart_performance();
            	d.setDp_id(rs.getString("dp_id"));
            	d.setDepart_kpi(rs.getDouble("depart_kpi"));
            	d.setDepart_perfor_score(rs.getDouble("depart_perfor_score"));
            	d.setDepart_perfor_residue(rs.getDouble("depart_perfor_residue"));
            	d.setDepart_perfor_total(rs.getDouble("depart_perfor_total"));
            	d.setDepart_perfor_year(rs.getInt("depart_perfor_year"));
            	d.setDepart_perfor_yetpay(rs.getDouble("depart_perfor_yetpay"));
            	d.setDepart_performance(rs.getDouble("depart_performance"));
            	d.setDepart_remark(rs.getString("depart_remark"));
            	depart.add(d);
            }
            return depart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff> find_staff_dp(String dp_id)
	{
		String sql = "select * from staff where dp_id='"+dp_id+"'";
        ResultSet rs = null;
        List<staff> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff>();
            while (rs.next()) {
            	staff staffs = new staff();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setStaff_sex(rs.getString("staff_sex"));
                staffs.setCm_id(rs.getString("cm_id"));
                staffs.setPs_id(rs.getString("ps_id"));
                staffs.setStaff_born(rs.getDate("staff_born"));
                staffs.setStaff_province(rs.getString("staff_province"));
                staffs.setStaff_city(rs.getString("staff_city"));
                staffs.setStaff_county(rs.getString("staff_county"));
                staffs.setStaff_address(rs.getString("staff_address"));
                staffs.setStaff_idnumber(rs.getString("staff_idnumber"));
                staffs.setStaff_time(rs.getDate("staff_time"));
                staffs.setStaff_phone(rs.getString("staff_phone"));
                staffs.setStaff_pic(rs.getString("staff_pic"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public staff_performance find_perfor_score_all(String dp_id,int year,String staff_id)
	{
		String sql = "select * from staff_performance where staff_performance_year="+year+" and staff_id='"+staff_id+"' and staff_id in (select staff_id from staff where dp_id = '"+dp_id+"')";
        ResultSet rs = null;
        staff_performance s = new staff_performance();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	s.setStaff_id(rs.getString("staff_id"));
            	s.setStaff_perfotmance_coefficient(rs.getDouble("staff_performance_coefficient"));
            	s.setStaff_performance_kpi(rs.getDouble("staff_performance_kpi"));
            	s.setAdmin_id(rs.getString("admin_id"));
            	s.setStaff_performance_changetime(rs.getTimestamp("staff_performance_changetime"));
            	s.setStaff_performance_remark(rs.getString("staff_performance_remark"));
            	s.setStaff_performance_year(rs.getInt("staff_performance_year"));
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int insert_staff_perforlist(staff_performance_list s)
	{
		int count = 0;
        try {
    		String sql = "insert into staff_performance_list values(?,0,?,'未发放',?,NULL,?,?)";
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, s.getStaff_id());
            System.out.println(s.getStaff_performance_list_residue());
            pStatement.setDouble(2, s.getStaff_performance_list_residue());
            pStatement.setDouble(3, s.getStaff_performance_list_year());
            pStatement.setString(4, s.getAdmin_id());
            pStatement.setDouble(5, s.getStaff_performance_list_total());
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_performance_list> find_staff_perforlist_dp(String dp_name,int year)
	{
		String sql = "select * from staff_performance_list where staff_performance_list_year="+year+" and staff_id in (select staff_id from staff where dp_id = (select dp_id from department where dp_name = '"+dp_name+"'))";
        ResultSet rs = null;
        List<staff_performance_list> ts = new ArrayList<staff_performance_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_performance_list s = new staff_performance_list();
            	s.setAdmin_id(rs.getString("admin_id"));
            	s.setStaff_id(rs.getString("staff_id"));
            	s.setStaff_performance_list_paytime(rs.getTimestamp("staff_performance_list_paytime"));
            	s.setStaff_performance_list_residue(rs.getDouble("staff_performance_list_residue"));
            	s.setStaff_performance_list_status(rs.getString("staff_performance_list_status"));
            	s.setStaff_performance_list_total(rs.getDouble("staff_performance_list_total"));
            	s.setStaff_performance_list_year(rs.getInt("staff_performance_list_year"));
            	s.setStaff_performance_list_yetpay(rs.getDouble("staff_performance_list_yetpay"));
            	ts.add(s);
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_performance> find_staff_perfor_dp(String dp_name,int year)
	{
		String sql = "select * from staff_performance where staff_performance_year="+year+" and staff_id in (select staff_id from staff where dp_id  = (select dp_id from department where dp_name = '"+dp_name+"'))";
        ResultSet rs = null;
        List<staff_performance> ts = new ArrayList<staff_performance>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_performance s = new staff_performance();
            	s.setAdmin_id(rs.getString("admin_id"));
            	s.setStaff_id(rs.getString("staff_id"));
            	s.setStaff_performance_year(rs.getInt("staff_performance_year"));
            	s.setStaff_performance_changetime(rs.getTimestamp("staff_performance_changetime"));
            	s.setStaff_performance_kpi(rs.getDouble("staff_performance_kpi"));
            	s.setStaff_performance_remark(rs.getString("staff_performance_remark"));
            	s.setStaff_perfotmance_coefficient(rs.getDouble("staff_performance_coefficient"));
            	ts.add(s);
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff> find_staff_dp_name(String dp_name)
	{
		String sql = "select * from staff where dp_id = (select dp_id from department where dp_name = '"+dp_name+"')";
        ResultSet rs = null;
        List<staff> staff = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            staff = new ArrayList<staff>();
            while (rs.next()) {
            	staff staffs = new staff();
                staffs.setStaff_id(rs.getString("staff_id"));
                staffs.setStaff_name(rs.getString("staff_name"));
                staffs.setDp_id(rs.getString("dp_id"));
                staffs.setStaff_sex(rs.getString("staff_sex"));
                staffs.setCm_id(rs.getString("cm_id"));
                staffs.setPs_id(rs.getString("ps_id"));
                staffs.setStaff_born(rs.getDate("staff_born"));
                staffs.setStaff_province(rs.getString("staff_province"));
                staffs.setStaff_city(rs.getString("staff_city"));
                staffs.setStaff_county(rs.getString("staff_county"));
                staffs.setStaff_address(rs.getString("staff_address"));
                staffs.setStaff_idnumber(rs.getString("staff_idnumber"));
                staffs.setStaff_time(rs.getDate("staff_time"));
                staffs.setStaff_phone(rs.getString("staff_phone"));
                staffs.setStaff_pic(rs.getString("staff_pic"));
                staff.add(staffs);
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_performance_list> find_staff_performance_list_all()
	{
		String sql = "select * from staff_performance_list where staff_performance_list_status ='未发放' or staff_performance_list_status = '未发完'";
        ResultSet rs = null;
        List<staff_performance_list> ts = new ArrayList<staff_performance_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_performance_list s = new staff_performance_list();
            	s.setAdmin_id(rs.getString("admin_id"));
            	s.setStaff_id(rs.getString("staff_id"));
            	s.setStaff_performance_list_paytime(rs.getTimestamp("staff_performance_list_paytime"));
            	s.setStaff_performance_list_residue(rs.getDouble("staff_performance_list_residue"));
            	s.setStaff_performance_list_status(rs.getString("staff_performance_list_status"));
            	s.setStaff_performance_list_total(rs.getDouble("staff_performance_list_total"));
            	s.setStaff_performance_list_year(rs.getInt("staff_performance_list_year"));
            	s.setStaff_performance_list_yetpay(rs.getDouble("staff_performance_list_yetpay"));
            	ts.add(s);
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_performance> find_staff_perf_all(String staff_id,int year)
	{
		String sql = "select * from staff_performance where staff_performance_year="+year+" and staff_id = '"+staff_id+"'";
        ResultSet rs = null;
        List<staff_performance> ts = new ArrayList<staff_performance>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_performance s = new staff_performance();
            	s.setAdmin_id(rs.getString("admin_id"));
            	s.setStaff_id(rs.getString("staff_id"));
            	s.setStaff_performance_year(rs.getInt("staff_performance_year"));
            	s.setStaff_performance_changetime(rs.getTimestamp("staff_performance_changetime"));
            	s.setStaff_performance_kpi(rs.getDouble("staff_performance_kpi"));
            	s.setStaff_performance_remark(rs.getString("staff_performance_remark"));
            	s.setStaff_perfotmance_coefficient(rs.getDouble("staff_performance_coefficient"));
            	ts.add(s);
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public double find_ready_perfor(int year)
	{
		String sql = "select sum(staff_performance_list_residue) from staff_performance_list where staff_performance_list_status = '未发放' or staff_performance_list_status = '未发完' and staff_performance_list_year = "+year;
        ResultSet rs = null;
        double count = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	count = rs.getDouble(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public double find_ready_perfor_depart_year(int year,String department)
	{
		String sql = "";
		if(department.equals("所有部门")) 
			sql = "select sum(staff_performance_list_residue) from staff_performance_list where (staff_performance_list_status = '未发放' or staff_performance_list_status = '未发完') and staff_performance_list_year = "+year;
		else
			sql = "select sum(staff_performance_list_residue) from staff_performance_list where (staff_performance_list_status = '未发放' or staff_performance_list_status = '未发完') and staff_performance_list_year = "+year+" and staff_id in (select staff_id from staff where dp_id = (select dp_id from department where dp_name = '"+department+"'))";
        ResultSet rs = null;
        double count = 0.0;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	count = rs.getDouble(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public List<staff_performance_list> find_payperfor_list(int year,String department)
	{
		String sql = "";
		if(department.equals("所有部门"))
			sql = "select * from staff_performance_list where (staff_performance_list_status = '未发放' or staff_performance_list_status = '未发完') and staff_performance_list_year = "+year;
		else
			sql = "select * from staff_performance_list where (staff_performance_list_status = '未发放' or staff_performance_list_status = '未发完') and staff_performance_list_year = "+year+" and staff_id in (select staff_id from staff where dp_id = (select dp_id from department where dp_name = '"+department+"'))";
        ResultSet rs = null;
        List<staff_performance_list> perforlist = new ArrayList<staff_performance_list>();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	staff_performance_list s = new staff_performance_list();
            	s.setAdmin_id(rs.getString("admin_id"));
            	s.setStaff_id(rs.getString("staff_id"));
            	s.setStaff_performance_list_paytime(rs.getTimestamp("staff_performance_list_paytime"));
            	s.setStaff_performance_list_residue(rs.getDouble("staff_performance_list_residue"));
            	s.setStaff_performance_list_status(rs.getString("staff_performance_list_status"));
            	s.setStaff_performance_list_total(rs.getDouble("staff_performance_list_total"));
            	s.setStaff_performance_list_year(rs.getInt("staff_performance_list_year"));
            	s.setStaff_performance_list_yetpay(rs.getDouble("staff_performance_list_yetpay"));
            	perforlist.add(s);
            }
            return perforlist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_perfor_list_id(String staff_id,int year,double money)
	{
		int count = 0;
        try {
    		String sql = "update staff_performance_list set staff_performance_list_yetpay = staff_performance_list_yetpay + "+ money + ",staff_performance_list_residue = staff_performance_list_residue - "+money+" where staff_id = '"+staff_id+"' and staff_performance_list_year = "+year;
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public int update_perfor_list_status(String str,String staff_id,int year)
	{
		int count = 0;
        try {
    		String sql = "update staff_performance_list set staff_performance_list_status='"+str+"' where staff_id = '"+staff_id+"' and staff_performance_list_year = "+year;
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            count = pStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public department find_salary_month_table(int year,int month,String department)
	{
		String sql = "select sum(staff_salary_list_netpayroll),sum(staff_salary_list_yetpay),staff_salary_list_stockdater,staff_salary_list_paytime from staff_salary_list where staff_salary_list_year ="+year+" and staff_salary_list_month = "+month+" and staff_id in (select staff_id from staff where dp_id = (select dp_id from department where dp_name = '"+department+"'))";
        ResultSet rs = null;
        department d = new department();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	d.setSalary_all(rs.getDouble(1));
            	d.setSalary_yetpay(rs.getDouble(2));
            	d.setJs_time(rs.getTimestamp(3));
            	d.setPay_time(rs.getTimestamp(4));
            }
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
	
	@Override
	public department find_salary_year_table(int year,String department)
	{
		String sql = "select sum(staff_salary_list_netpayroll),sum(staff_salary_list_yetpay) from staff_salary_list where staff_salary_list_year ="+year+" and staff_id in (select staff_id from staff where dp_id = (select dp_id from department where dp_name = '"+department+"'))";
        ResultSet rs = null;
        department d = new department();
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            while (rs.next()) {
            	d.setSalary_all(rs.getDouble(1));
            	d.setSalary_yetpay(rs.getDouble(2));
            }
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	dbCon.closeResultSet(rs);
        	dbCon.closePreparedStatement(pStatement);
        	dbCon.closeConnection(connection);
        }
	}
}
