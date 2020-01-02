package jinsen.daoreal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.overtime;
import jinsen.bean.overtimetype;
import jinsen.dao.overtimeDao;
import jinsen.db.dbCon;



public class overtimeImpl implements overtimeDao{
	Connection connection = null;
	public List<overtimetype> gettypeSelect() throws SQLException {//查询加班类型
		List<overtimetype> oveList = null;
		try {
			connection = dbCon.getConnection();
			oveList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from overtimetype";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				overtimetype ove = new overtimetype();
				ove.setOvertimetype_id(rs.getInt("overtimetype_id"));
			    ove.setOvertimetype_type(rs.getString("overtimetype_type"));
			    ove.setOvertimetype_stand(rs.getDouble("overtimetype_stand"));
				oveList.add(ove);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return oveList;
	}
	public int inserttype(overtimetype s){//添加加班类型
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="insert into overtimetype values(null,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,s.getOvertimetype_type());
            ps.setDouble(2,s.getOvertimetype_stand());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    } 
	public int typedelete(int id) {//删除加班类型
		String sql = "delete from overtimetype where overtimetype_id='" + id + "'";
		connection = dbCon.getConnection();
		int i = 0;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			i = ps.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	public List<overtimetype> getidSelect(int id) throws SQLException {//通过ID查询加班类型
		List<overtimetype> oveList = null;
		try {
			connection = dbCon.getConnection();
			oveList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from overtimetype where overtimetype_id='"+ id +"'";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				overtimetype ove = new overtimetype();
				ove.setOvertimetype_id(rs.getInt("overtimetype_id"));
				ove.setOvertimetype_type(rs.getString("overtimetype_type"));
				ove.setOvertimetype_stand(rs.getDouble("overtimetype_stand"));
				oveList.add(ove);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return oveList;
	}
	public int updatetype(overtimetype lea){//更新加班类型
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="update overtimetype set overtimetype_type=?,overtimetype_stand=? where overtimetype_id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,lea.getOvertimetype_type());
            ps.setDouble(2,lea.getOvertimetype_stand());		
            ps.setInt(3,lea.getOvertimetype_id());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    }
	public List<overtime> getqjSelect() throws SQLException {//查询加班信息
		List<overtime> oveList = null;
		try {
			connection = dbCon.getConnection();
			oveList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from overtime";
			pstmt =  connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				overtime ove = new overtime();
				ove.setOvertime_id(rs.getInt("overtime_id"));
				ove.setStaff_id(rs.getString("staff_id"));
				ove.setStaff_name(rs.getString("staff_name"));
				ove.setOvertime_time(rs.getTimestamp("overtime_time"));
				ove.setOvertime_times(rs.getTimestamp("overtime_times"));
				ove.setOvertime_reason(rs.getString("overtime_reason"));
				ove.setOvertime_type(rs.getString("overtime_type"));
				ove.setOvertime_state(rs.getString("overtime_state"));
				oveList.add(ove);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			connection.close();
		}
		return oveList;
	}
	public int overtimedelete(int id) {//删除加班信息
		String sql = "delete from overtime where overtime_id='" + id + "'";
		connection = dbCon.getConnection();
		int i = 0;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			i = ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	public int overtimeinsert(overtime s){//添加加班信息
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="insert into overtime values(null,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,s.getStaff_id());
            ps.setString(2,s.getStaff_name());
            ps.setTimestamp(3, s.getOvertime_time());
            ps.setTimestamp(4, s.getOvertime_times());
            ps.setString(5, s.getOvertime_reason());
            ps.setString(6, s.getOvertime_type());
            ps.setString(7,s.getOvertime_state());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    }
	public List<overtimetype> typeSelect() throws SQLException {//查询加班类型
		List<overtimetype> oveList = null;
		try {
			connection = dbCon.getConnection();
			oveList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select  * from overtimetype";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				overtimetype ove = new overtimetype();
				ove.setOvertimetype_type(rs.getString("overtimetype_type"));
				
				oveList.add(ove);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return oveList;
	}
	public int updateovertime(overtime lea){//更新加班信息
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="update overtime set overtime_time=?,overtime_times=?,overtime_reason=?,overtime_type=? where overtime_id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, lea.getOvertime_time());
            ps.setTimestamp(2, lea.getOvertime_times());
            ps.setString(3, lea.getOvertime_reason());
            ps.setString(4, lea.getOvertime_type());
            ps.setInt(5, lea.getOvertime_id());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    }
	public List<overtime> overtimebyidSelect(int id) throws SQLException {//通过ID查询加班信息
		List<overtime> oveList = null;
		try {
			connection = dbCon.getConnection();
			oveList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from overtime where overtime_id='"+ id +"'";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				overtime ove = new overtime();
				ove.setOvertime_time(rs.getTimestamp("overtime_time"));
				ove.setOvertime_times(rs.getTimestamp("overtime_times"));
				ove.setOvertime_reason(rs.getString("overtime_reason"));
				ove.setOvertime_type(rs.getString("overtime_type"));
			    oveList.add(ove);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return oveList;
	}
}
