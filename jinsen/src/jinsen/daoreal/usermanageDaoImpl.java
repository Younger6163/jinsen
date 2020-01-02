package jinsen.daoreal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.role;
import jinsen.bean.staff;
import jinsen.bean.usermanage;
import jinsen.dao.usermanageDao;
import jinsen.db.dbCon;


public class usermanageDaoImpl implements usermanageDao
{	private dbCon db=null;
	
	@Override
	public List<staff> find_allstaff()
	{
		String sql = "select * from staff";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        List<staff> s = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            s = new ArrayList<staff>();
            while (rs.next()) {
            	staff s1=new staff();
            	s1.setStaff_id(rs.getString("staff_id"));
            	s1.setStaff_name(rs.getString("staff_name"));
            	s1.setStaff_department(rs.getString("staff_department"));
            	s1.setStaff_sex(rs.getString("staff_sex"));
            	s1.setStaff_company(rs.getString("staff_company"));
            	s1.setStaff_post(rs.getString("staff_post"));
            	s1.setStaff_born(rs.getDate("staff_born"));
            	s1.setStaff_province(rs.getString("staff_province"));
            	s1.setStaff_city(rs.getString("staff_city"));
            	s1.setStaff_county(rs.getString("staff_county"));
            	s1.setStaff_address(rs.getString("staff_address"));
            	s1.setStaff_idnumber(rs.getString("staff_idnumber"));
            	s1.setStaff_phone(rs.getString("staff_phone"));
            	s1.setStaff_pic(rs.getString("staff_pic"));
                s.add(s1);
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        finally 
        {
        	db.closeConnection(connection);
        	db.closePreparedStatement(pStatement);
        	
        }
	}

	@Override
	
	public boolean add_staff(staff s) 
	{
		Connection conn=dbCon.getConnection();
		String sql="insert into staff values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		System.out.println(s.getStaff_born());
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, s.getStaff_id());
			pstmt.setString(2, s.getStaff_name());
			pstmt.setString(4, s.getStaff_sex());
			pstmt.setDate(7, (Date) s.getStaff_born());
			pstmt.setString(3, s.getStaff_department());
			pstmt.setString(6, s.getStaff_post());
			pstmt.setString(8, s.getStaff_province());
			pstmt.setString(9, s.getStaff_city());
			pstmt.setString(10, s.getStaff_county());
			pstmt.setString(11, s.getStaff_address());
			pstmt.setString(12, s.getStaff_idnumber());
			pstmt.setDate(13, (Date) s.getStaff_time());
			pstmt.setString(5, s.getStaff_company());
			pstmt.setString(14, s.getStaff_phone());
			pstmt.setString(15, s.getStaff_pic());
			int count=pstmt.executeUpdate();
			return count>0 ? true :false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
		}
	}

	@Override
	public boolean delete_staff(String staff_id) 
	{
		String sql="delete from staff where staff_id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		System.out.println("id="+staff_id);
		try 
		{
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, staff_id);
			int count=pstmt.executeUpdate();
			return count>0 ? true : false;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closePreparedStatement(pstmt);
			db.closeConnection(conn);
		} 
	}

	@Override
	public List<usermanage> find_allusermanager() 
	{
		String sql = "select * from usermanage where is_delete=0";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        List<usermanage> s=null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            s = new ArrayList<usermanage>();
            while (rs.next()) {
            	usermanage s1=new usermanage();
            	s1.setStaff_id(rs.getString("staff_id"));
            	s1.setStaff_name(rs.getString("staff_name"));
            	s1.setPassword(rs.getString("password"));
            	s1.setCreatetime(rs.getTimestamp("createtime"));
            	s1.setBackups(rs.getString("backups"));
            	s1.setPower_type(rs.getString("power_type"));
            	s1.setIs_delete(rs.getInt("is_delete"));
            	s1.setIs_lock(rs.getInt("is_lock"));
            	s1.setLogintime(rs.getTimestamp("logintime"));
            	s1.setExittime(rs.getTimestamp("exittime"));
                s.add(s1);
            }
            
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        finally 
        {
        	db.closeConnection(connection);
        	db.closePreparedStatement(pStatement);
        	
        }
	}

	@Override
	public boolean add_manager(usermanage s) 
	{
		Connection conn=dbCon.getConnection();
		String sql="insert into usermanage values(?,?,?,?,?,?,0,0,null,null)";
		PreparedStatement pstmt=null;
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, s.getStaff_id());
			pstmt.setString(2, s.getStaff_name());
			pstmt.setString(3, s.getPassword());
			pstmt.setTimestamp(4, s.getCreatetime());
			pstmt.setString(5, s.getBackups());
			pstmt.setString(6, s.getPower_type());
			int count=pstmt.executeUpdate();
			return count>0 ? true :false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
		}
	}

	@Override
	public boolean delete_manager(String staff_id) 
	{
			String sql="delete from usermanage where staff_id=?";
			Connection conn=null;
			PreparedStatement pstmt=null;
			System.out.println("id="+staff_id);
			try 
			{
				conn=db.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, staff_id);
				int count=pstmt.executeUpdate();
				return count>0 ? true : false;
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			finally
			{
				db.closePreparedStatement(pstmt);
				db.closeConnection(conn);
			} 
	}

	@Override
	public boolean delete_managerstatic(String staff_id) 
	{
		String sql="update usermanage set is_delete=1 where staff_id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		System.out.println("id="+staff_id);
		try 
		{
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, staff_id);
			int count=pstmt.executeUpdate();
			return count>0 ? true : false;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closePreparedStatement(pstmt);
			db.closeConnection(conn);
		} 
	}

	@Override
	public boolean lock_manager(String staff_id) 
	{
		if(islock(staff_id))
		{
			System.out.println("无法重复锁定");
			return false;
		}
		else
		{
			System.out.println("进入锁定");
			Connection conn=db.getConnection();
			String sql="update usermanage set is_lock=1 where staff_id=?";
			PreparedStatement pstmt=null;
			try 
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, staff_id);
				int count=pstmt.executeUpdate();
				System.out.println(count);
		        return count>0 ? true : false;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				db.closeConnection(conn);
				db.closePreparedStatement(pstmt);
			}
		}
	}

	@Override
	public boolean islock(String staff_id) 
	{
		String sql="select * from usermanage where staff_id=?";
		usermanage u=new usermanage();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try 
		{
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, staff_id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				u.setIs_lock(rs.getInt("is_lock"));
			}
			if(u.getIs_lock()==1)
			{
				System.out.println("已锁定");
				return true;
			}
			else 
			{
				System.out.println("未锁定");
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
			db.closeResultSet(rs);
		}
	}

	@Override
	public boolean unlock_manager(String staff_id) 
	{
		if(islock(staff_id))
		{
			Connection conn=db.getConnection();
			String sql="update usermanage set is_lock=0 where staff_id=?";
			PreparedStatement pstmt=null;
			try 
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, staff_id);
				int count=pstmt.executeUpdate();
				System.out.println(count);
		        return count>0 ? true : false;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				db.closeConnection(conn);
				db.closePreparedStatement(pstmt);
			}
		}
		else 
		{
			System.out.println("未锁定");
			return false;
		}
	}

	@Override
	public boolean recode(String staff_id) 
	{
		System.out.println("进入重置");
		Connection conn=db.getConnection();
		String sql="update usermanage set password=123456 where staff_id=?";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, staff_id);
			int count=pstmt.executeUpdate();
			System.out.println(count);
	        return count>0 ? true : false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
		}
	}

	@Override
	public usermanage login(usermanage u) 
	{
		Connection conn=db.getConnection();
		String sql="select * from usermanage where staff_id=? and password=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try 
		{
			pstmt=conn.prepareStatement(sql);
//			System.out.println(u.getStaff_id());
//			System.out.println(u.getPassword());
			pstmt.setString(1, u.getStaff_id());
			pstmt.setString(2, u.getPassword());
			rs=pstmt.executeQuery();
			usermanage s=new usermanage();
			//System.out.println("333");
			while(rs.next())
			{
				//System.out.println("sss");
//				s.setStaff_id(rs.getString("staff_id"));
//            	s.setStaff_name(rs.getString("staff_name"));
//            	s.setPassword(rs.getString("password"));
//            	s.setCreatetime(rs.getDate("createtime"));
//            	s.setBackups(rs.getString("backups"));
            	s.setPower_type(rs.getString("power_type"));
//            	s.setIs_delete(rs.getInt("is_delete"));
//            	s.setIs_lock(rs.getInt("is_lock"));
            	s.setLogintime(rs.getTimestamp("logintime"));
//            	s.setExittime(rs.getDate("exittime"));
            	
            	System.out.println("kkk"+s.getPower_type());
			}
			return s;
		} 
		catch (Exception e) 
		{
			 e.printStackTrace();
	            return null;
		}
		finally 
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
			db.closeResultSet(rs);
		}
	}

	@Override
	public List<role> find_allrole() 
	{
		String sql = "select * from role";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        List<role> s = null;
        try {
            connection = dbCon.getConnection();
            pStatement = connection.prepareStatement(sql);
            rs = pStatement.executeQuery();
            s = new ArrayList<role>();
            while (rs.next()) {
            	role s1=new role();
            	s1.setRole_num(rs.getInt("role_num"));
            	s1.setRole_name(rs.getString("role_name"));
            	s1.setRemark(rs.getString("remark"));
                s.add(s1);
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        finally 
        {
        	db.closeConnection(connection);
        	db.closePreparedStatement(pStatement);
        	
        }
	}

	@Override
	public boolean add_role(role s) 
	{
		System.out.println("in addrole");
		Connection conn=dbCon.getConnection();
		String sql="insert into role values(?,?,?)";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, s.getRole_num());
			pstmt.setString(2, s.getRole_name());
			pstmt.setString(3, s.getRemark());
			int count=pstmt.executeUpdate();
			return count>0 ? true :false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
		}
	}

	@Override
	public boolean logintime(usermanage s) 
	{
		String sql="update usermanage set logintime=? where staff_id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		System.out.println("id="+s.getStaff_id());
		try 
		{
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setTimestamp(1, s.getLogintime());
			pstmt.setString(2, s.getStaff_id());
			int count=pstmt.executeUpdate();
			return count>0 ? true : false;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.closePreparedStatement(pstmt);
			db.closeConnection(conn);
		} 
	}

	@Override
	public role find_onerole(String role_num) 
	{
		Connection conn=db.getConnection();
		String sql="select * from role where role_num=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, role_num);
			rs=pstmt.executeQuery();
			role s=new role();
			while(rs.next())
			{
				//System.out.println("sss");
            	s.setRole_num(rs.getInt("role_num"));
            	s.setRole_name(rs.getString("role_name"));
            	s.setRemark(rs.getString("remark"));
            	//System.out.println("kkk"+s.getPower_type());
			}
			return s;
		} 
		catch (Exception e) 
		{
			 e.printStackTrace();
	            return null;
		}
		finally 
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
			db.closeResultSet(rs);
		}
	}

	@Override
	public boolean delete_role(int role_num) 
	{
		Connection conn=db.getConnection();
		String sql="delete from role where role_num=?";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, role_num);
			int count=pstmt.executeUpdate();
			return count>0 ? true : false; 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally 
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
		}
		
	}

	@Override
	public boolean update_role(role s) 
	{
		System.out.println("in updaterole");
		Connection conn=db.getConnection();
		String sql="update role set role_name=?,remark=? where role_num=? ";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, s.getRole_name());
			pstmt.setString(2, s.getRemark());
			pstmt.setInt(3, s.getRole_num());
			int count=pstmt.executeUpdate();
			System.out.println(count);
			return count>0 ? true : false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			// TODO: handle exception
			return false;
		}
		finally
		{
			db.closeConnection(conn);
			db.closePreparedStatement(pstmt);
		}
	}
}
