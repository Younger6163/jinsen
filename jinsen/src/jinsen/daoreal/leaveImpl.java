package jinsen.daoreal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinsen.bean.leave;
import jinsen.bean.leavetype;
import jinsen.dao.leaveDao;
import jinsen.db.dbCon;



public class leaveImpl implements leaveDao{
	Connection connection = null;
	public int inserttype(leavetype s){//添加请假类型
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="insert into leavetype values(null,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,s.getLeavetype_type());
            ps.setDouble(2,s.getLeavetype_stand());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    } 
	public List<leavetype> gettypeSelect() throws SQLException {//查询请假类型
		List<leavetype> leaList = null;
		try {
			connection = dbCon.getConnection();
			leaList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from leavetype";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				leavetype lea = new leavetype();
				lea.setLeavetype_id(rs.getInt("leavetype_id"));
				lea.setLeavetype_type(rs.getString("leavetype_type"));
				lea.setLeavetype_stand(rs.getDouble("leavetype_stand"));
				leaList.add(lea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return leaList;
	}
	public int typedelete(int id) {//删除请假类型
		String sql = "delete from leavetype where leavetype_id='" + id + "'";
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
	public List<leavetype> getidSelect(int id) throws SQLException {//通过ID查询请假类型
		List<leavetype> leaList = null;
		try {
			connection = dbCon.getConnection();
			leaList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from leavetype where leavetype_id='"+ id +"'";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				leavetype lea = new leavetype();
				lea.setLeavetype_id(rs.getInt("leavetype_id"));
				lea.setLeavetype_type(rs.getString("leavetype_type"));
				lea.setLeavetype_stand(rs.getDouble("leavetype_stand"));
				leaList.add(lea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return leaList;
	}
	public int updatetype(leavetype lea){//更新请假类型
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="update leavetype set leavetype_type=?,leavetype_stand=? where leavetype_id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,lea.getLeavetype_type());
            ps.setDouble(2,lea.getLeavetype_stand());		
            ps.setInt(3,lea.getLeavetype_id());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    }
	public List<leave> getqjSelect() throws SQLException {//查询请假单
		List<leave> leaList = null;
		try {
			connection = dbCon.getConnection();
			leaList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from leavef";
			pstmt =  connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				leave lea = new leave();
				lea.setLeave_id(rs.getInt("leave_id"));
				lea.setStaff_id(rs.getString("staff_id"));
				lea.setStaff_name(rs.getString("staff_name"));
				lea.setLeave_time(rs.getTimestamp("leave_time"));
				lea.setLeave_times(rs.getTimestamp("leave_times"));
				lea.setLeave_reason(rs.getString("leave_reason"));
				lea.setLeave_type(rs.getString("leave_type"));
				lea.setLeave_state(rs.getString("leave_state"));
				leaList.add(lea);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			connection.close();
		}
		return leaList;
	}
	public int leavedelete(int id) {//删除请假单
		String sql = "delete from leavef where leave_id='" + id + "'";
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
	public int leaveinsert(leave s){//添加请假单
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="insert into leavef values(null,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,s.getStaff_id());
            ps.setString(2,s.getStaff_name());
            ps.setTimestamp(3, s.getLeave_time());
            ps.setTimestamp(4, s.getLeave_times());
            ps.setString(5, s.getLeave_reason());
            ps.setString(6, s.getLeave_type());
            ps.setString(7,s.getLeave_state());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    }
	public List<leavetype> typeSelect() throws SQLException {//查询请假表类型
		List<leavetype> leaList = null;
		try {
			connection = dbCon.getConnection();
			leaList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from leavetype";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				leavetype lea = new leavetype();
				lea.setLeavetype_type(rs.getString("leavetype_type"));
				leaList.add(lea);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return leaList;
	}
	public int updateleave(leave lea){//更新请假单
	    connection = dbCon.getConnection();
	    int i=0;
        String sql ="update leavef set leave_time=?,leave_times=?,leave_reason=?,leave_type=? where leave_id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, lea.getLeave_time());
            ps.setTimestamp(2, lea.getLeave_times());
            ps.setString(3, lea.getLeave_reason());
            ps.setString(4, lea.getLeave_type());
            ps.setInt(5, lea.getLeave_id());
            i = ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        return i;
    }
	public List<leave> leavebyidSelect(int id) throws SQLException {//通过ID查询请假单
		List<leave> leaList = null;
		try {
			connection = dbCon.getConnection();
			leaList = new ArrayList<>();
			PreparedStatement pstmt;
			String sql = "select * from leavef where leave_id='"+ id +"'";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				leave lea = new leave();
				lea.setLeave_time(rs.getTimestamp("leave_time"));
				lea.setLeave_times(rs.getTimestamp("leave_times"));
			    lea.setLeave_reason(rs.getString("leave_reason"));
			    lea.setLeave_type(rs.getString("leave_type"));
				leaList.add(lea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return leaList;
	}
}
