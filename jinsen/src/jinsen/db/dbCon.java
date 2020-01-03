package jinsen.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbCon {

	private static String DRIVER = "com.mysql.jdbc.Driver"; 		
	private static String URL = "jdbc:mysql://localhost:3306/jinsen?Unicode=true&characterEncoding=UTF-8";
	private static String NAME = "root";					
	private static String PASSWORD = "6163";						
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, NAME, PASSWORD);
			return connection;
		} catch (Exception e) {
			return null;
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
}
