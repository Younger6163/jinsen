package jinsen.daoreal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import jinsen.bean.Position;
import jinsen.dao.PositionDao;
import jinsen.db.dbCon;



public class PositionDaoImpl implements PositionDao {

	@Override
	public List<Position> allPosition() throws SQLException {
		System.out.println("已接入数据访问层PositonDao,职位维护Dao已介入");
		List<Position> lst = new ArrayList<Position>();
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="SELECT c.cm_id,c.cm_name,c.cm_info,d.dp_id,d.dp_name,d.dp_info,p.ps_id,p.ps_name,p.ps_info,p.all_id FROM company c,department d,post p WHERE c.cm_id=d.cm_id and d.dp_id=p.dp_id";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Position pst = new Position();
            	pst.setCm_id(rs.getString("cm_id"));
            	pst.setCm_name(rs.getString("cm_name"));
            	pst.setCm_info(rs.getString("cm_info"));
            	pst.setDp_id(rs.getString("dp_id"));
            	pst.setDp_name(rs.getString("dp_name"));
            	pst.setDp_info(rs.getString("dp_info"));
            	pst.setPs_id(rs.getString("ps_id"));
            	pst.setPs_name(rs.getString("ps_name"));
            	pst.setPs_info(rs.getString("ps_info"));
            	pst.setAll_id(rs.getString("all_id"));
            	lst.add(pst);
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
	public Position getOnePosition(String id) throws SQLException {
		System.out.println("正在获取职位信息........");
		Position pst = new Position();
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="SELECT c.cm_id,c.cm_name,c.cm_info,d.dp_id,d.dp_name,d.dp_info,p.ps_id,p.ps_name,p.ps_info,p.all_id FROM company c,department d,post p WHERE c.cm_id=d.cm_id and d.dp_id=p.dp_id AND p.all_id='"+id+"'";
        try {
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               	pst.setCm_id(rs.getString("cm_id"));
            	pst.setCm_name(rs.getString("cm_name"));
            	pst.setCm_info(rs.getString("cm_info"));
            	pst.setDp_id(rs.getString("dp_id"));
            	pst.setDp_name(rs.getString("dp_name"));
            	pst.setDp_info(rs.getString("dp_info"));
            	pst.setPs_id(rs.getString("ps_id"));
            	pst.setPs_name(rs.getString("ps_name"));
            	pst.setPs_info(rs.getString("ps_info"));
            	pst.setAll_id(rs.getString("all_id"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return pst;
	}

	@Override
	public boolean addPosition(String sql) throws SQLException {
		System.out.println("PositionDao接入完成,，尝试运行sql语句");
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
	public String getDp_id(String dp_name) throws SQLException {
		System.out.println("getDp_id DAO");
		Position pst = new Position();
		Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        String dp_id="";
        sql="select dp_id from department where dp_name='"+dp_name+"'";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	pst.setDp_id(rs.getString("dp_id"));
            }
            dp_id=pst.getDp_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return dp_id;
	}

	@Override
	public String getPs_id() throws SQLException {
		System.out.println("getPs_id DAO");
		Position pst = new Position();
		Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        String ps_id="";
        sql="select ps_id from post ORDER BY ps_id ASC";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	pst.setPs_id(rs.getString("ps_id"));
            }
            ps_id=pst.getPs_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return ps_id;
	}

	@Override
	public String getCm_id(String dp_name) throws SQLException {
		System.out.println("getCm_id DAO");
		Position pst = new Position();
		Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        String cm_id="";
        sql="select cm_id from department where dp_name='"+dp_name+"'";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	pst.setCm_id(rs.getString("cm_id"));
            }
            cm_id=pst.getCm_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return cm_id;
	}

	@Override
	public String getCm_id_cm(String cm_name) throws SQLException {
		System.out.println("getCm_id_cm DAO");
		Position pst = new Position();
		Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        String cm_id="";
        sql="select cm_id from company where cm_name='"+cm_name+"'";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	pst.setCm_id(rs.getString("cm_id"));
            }
            cm_id=pst.getCm_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return cm_id;
	}

	@Override
	public String getDp_id_dp() throws SQLException {
		System.out.println("getdp_id_dp DAO");
		Position pst = new Position();
		Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        String dp_id="";
        sql="select dp_id from department ORDER BY dp_id ASC";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	pst.setDp_id(rs.getString("dp_id"));
            }
            dp_id=pst.getDp_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return dp_id;
	}

	@Override
	public String getCm_id_end() throws SQLException {
		System.out.println("getcm_id_end DAO");
		Position pst = new Position();
		Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        String cm_id="";
        sql="select cm_id from company ORDER BY cm_id ASC";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	pst.setCm_id(rs.getString("cm_id"));
            }
            cm_id=pst.getCm_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return cm_id;
	
	}

	@Override
	public List<Position> allcompany() throws SQLException {
		System.out.println("已接入数据访问层PositonDao,职位维护Dao已介入");
		List<Position> lst = new ArrayList<Position>();
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="SELECT cm_name FROM company";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Position pst = new Position();
            	pst.setCm_name(rs.getString("cm_name"));
            	lst.add(pst);
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
	public List<Position> alldp(String cm_id) throws SQLException {
		System.out.println("已接入数据访问层PositonDao,职位维护Dao已介入"+cm_id);
		List<Position> lst = new ArrayList<Position>();
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="SELECT dp_name FROM department where cm_id='"+cm_id+"'";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Position pst = new Position();
            	pst.setDp_name(rs.getString("dp_name"));
            	lst.add(pst);
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
	public List<Position> allps(String dp_id) throws SQLException {
		System.out.println("已接入数据访问层PositonDao,职位维护Dao已介入"+dp_id);
		List<Position> lst = new ArrayList<Position>();
        Connection conn = dbCon.getConnection();
	    PreparedStatement st;
        String sql=null;
        sql="SELECT ps_name FROM post where dp_id='"+dp_id+"'";
        try {
    		System.out.println("MySQL接入SQL语句");
        	st = (PreparedStatement) conn.prepareStatement(sql);  
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Position pst = new Position();
            	pst.setPs_name(rs.getString("ps_name"));
            	lst.add(pst);
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
	}


