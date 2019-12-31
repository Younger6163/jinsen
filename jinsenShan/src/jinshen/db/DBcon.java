package jinshen.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBcon {
	public static Connection getConnection()
	{
		
		String URL = "jdbc:mysql://localhost:3306/jinsen?Unicode=true&characterEncoding=UTF-8";	// 访问数据库路径
	    String NAME = "root";							// 数据库用户名
	    String PASSWORD = "123456";	
		// 数据库密码
		Connection connection = null;
		//第一步：加载驱动类(如果有异常，则抛出异常或者使用try..catch..语句块处理)
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
            System.out.println("驱动类加载失败。");
        }
        
        
        try {
        	connection = DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接对象获取失败。");
        }
        //返回一个连接对象
        return connection;
	}
	
	public ResultSet doQuery(String sql,Object[] params) 
	{
		ResultSet rs=null;
		Connection conn = getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				pstmt.setObject(i+1, params[i]);
			}
			rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return rs;
	}
	public int doUpdate(String sql,Object[] params) 
	{
		int res=0;
		Connection conn = getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				pstmt.setObject(i+1, params[i]);
			}
			res=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return res;
	}
	
	public void close() 
	{
		Connection conn = getConnection();
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
 
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
 
	public static void closePreparedStatement(PreparedStatement pStatement) {
		try {
			pStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
 
	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
