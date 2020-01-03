package jinsen.daoreal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.staff;
import jinsen.dao.StaffDao;
import jinsen.db.dbCon;




public class StaffDaoImpl implements StaffDao {

	@Override
	public boolean addStaff(String sql) throws SQLException {
    	System.out.println("StaffDao接入完成,，尝试运行sql语句");
		Connection conn = dbCon.getConnection();
        try {          
            Statement st = conn.createStatement();     
            int cnt = st.executeUpdate(sql);
            if(cnt>0) {
            return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (conn!=null)
                conn.close();
        }
        return false;
	}

	@Override
	public List<staff> allStaff() throws SQLException {
		System.out.println("OrderDao接入成功");

		List<staff> lst = new ArrayList<staff>();
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="select * from staff";
        try {
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	staff sf = new staff();
            	sf.setStaff_id(rs.getString("Staff_id"));
            	sf.setStaff_name(rs.getString("staff_name"));
            	sf.setDp_id(rs.getString("dp_id"));
            	sf.setStaff_sex(rs.getString("staff_sex"));
            	sf.setCm_id(rs.getString("cm_id"));
            	sf.setPs_id(rs.getString("ps_id"));
            	sf.setStaff_born(rs.getDate("staff_born"));
	
            	sf.setStaff_province(rs.getString("staff_province"));
            	sf.setStaff_city(rs.getString("staff_city"));
            	sf.setStaff_county(rs.getString("staff_county"));
            	sf.setStaff_address(rs.getString("staff_address"));
            	sf.setStaff_time(rs.getDate("staff_time"));
            	sf.setStaff_idnumber(rs.getString("staff_idnumber"));
            	sf.setStaff_phone(rs.getString("staff_phone"));
            	sf.setStaff_pic(rs.getString("staff_pic"));
            	sf.setStaff_dangan(rs.getString("staff_dangan"));
            	sf.setStaff_status(rs.getString("staff_status"));

                lst.add(sf);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return lst;
	}

	@Override
	public staff getOneStaff(String staff_id) throws SQLException {
		System.out.println("获取一个员工的信息");
    	staff sf = new staff();
    	
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="select * from staff where staff_id='"+staff_id+"'";
        try {
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	sf.setStaff_id(rs.getString("Staff_id"));
            	sf.setStaff_name(rs.getString("staff_name"));
            	sf.setDp_id(rs.getString("dp_id"));
            	sf.setStaff_sex(rs.getString("staff_sex"));
            	sf.setCm_id(rs.getString("cm_id"));
            	sf.setPs_id(rs.getString("ps_id"));
            	sf.setStaff_born(rs.getDate("staff_born"));
            	sf.setStaff_province(rs.getString("staff_province"));
            	sf.setStaff_city(rs.getString("staff_city"));
            	sf.setStaff_county(rs.getString("staff_county"));
            	sf.setStaff_address(rs.getString("staff_address"));
            	sf.setStaff_time(rs.getDate("staff_time"));
            	sf.setStaff_idnumber(rs.getString("staff_idnumber"));
            	sf.setStaff_phone(rs.getString("staff_phone"));
            	sf.setStaff_pic(rs.getString("staff_pic"));
            	sf.setStaff_dangan(rs.getString("staff_dangan"));
            	sf.setStaff_status(rs.getString("staff_status"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return sf;
	}

	
	
	
	
}



