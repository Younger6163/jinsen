package jinsen.daoreal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.education;
import jinsen.dao.education_inter;
import jinsen.db.dbCon;


public class dao_education implements education_inter {

	@Override
	public List<education> find_all() {
		String st="select * from education";
		List<education> lis=new ArrayList<education>();
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);						
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				education ed=new education();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setEnrollment_time(r.getDate("enrollment_time"));
				ed.setGraduation_time(r.getDate("graduation_time"));
				ed.setGraduation_unit(r.getString("graduation_unit"));
				ed.setCertificate(r.getString("certificate"));
				ed.setStaff_certificate(r.getString("staff_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				lis.add(i, ed);
				i++;
			}
			if(i==0)
			{
				System.out.print("没有记录！");
				return null;
				
			}
			else
			{
			
				return lis;
			}
			
		}
		catch (Exception e)
		{
			return null;
		}
		finally {
			
		}
	}

	@Override
	public List<education> find_name(String name) {
		String st="select * from education where name=?";
		List<education> lis=new ArrayList<education>();
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);						
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				education ed=new education();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setEnrollment_time(r.getDate("enrollment_time"));
				ed.setGraduation_time(r.getDate("graduation_time"));
				ed.setGraduation_unit(r.getString("graduation_unit"));
				ed.setCertificate(r.getString("certificate"));
				ed.setStaff_certificate(r.getString("staff_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				lis.add(i, ed);
				i++;
			}
			if(i==0)
			{
				System.out.print("没有记录！");
				return null;
				
			}
			else
			{
			
				return lis;
			}
			
		
	}
	catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	}

	@Override
	public List<education> find_certificate(String certificate) {
		
		String st="select * from education where certificate=?";
		List<education> lis=new ArrayList<education>();
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);						
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				education ed=new education();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setEnrollment_time(r.getDate("enrollment_time"));
				ed.setGraduation_time(r.getDate("graduation_time"));
				ed.setGraduation_unit(r.getString("graduation_unit"));
				ed.setCertificate(r.getString("certificate"));
				ed.setStaff_certificate(r.getString("staff_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				lis.add(i, ed);
				i++;
			}
			if(i==0)
			{
				System.out.print("没有记录！");
				return null;
				
			}
			else
			{
			
				return lis;
			}
			
	}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			
		}
	}
	
	@Override
	public List<education> find_staff_id(String staff_id) {
		String st="select * from education where staff_id=?";
		List<education> lis=new ArrayList<education>();
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);	
			pst.setString(1, staff_id);
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				education ed=new education();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setEnrollment_time(r.getDate("enrollment_time"));
				ed.setGraduation_time(r.getDate("graduation_time"));
				ed.setGraduation_unit(r.getString("graduation_unit"));
				ed.setCertificate(r.getString("certificate"));
				ed.setStaff_certificate(r.getString("staff_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				lis.add(i, ed);
				i++;
			}
			if(i==0)
			{
				System.out.print("没有记录！");
				return null;
				
			}
			else
			{
			
				return lis;
			}
			
		}
		catch (Exception e)
		{
			return null;
		}
		finally {
			
		}	}

	@Override
	public List<education> find_staff_date(Date date) {
		String st="select * from education where graduation_time=? or enrollment_time=?";
		List<education> lis=new ArrayList<education>();
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);			
			pst.setDate(1, date);
			pst.setDate(2, date);
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				education ed=new education();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setEnrollment_time(r.getDate("enrollment_time"));
				ed.setGraduation_time(r.getDate("graduation_time"));
				ed.setGraduation_unit(r.getString("graduation_unit"));
				ed.setCertificate(r.getString("certificate"));
				ed.setStaff_certificate(r.getString("staff_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				lis.add(i, ed);
				i++;
			}
			if(i==0)
			{
				System.out.print("没有记录！");
				return null;
				
			}
			else
			{
			
				return lis;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally {
			
		}
	}

	@Override
	public boolean insert_education(education edu) {
		String s="insert into education(staff_id,enrollment_time,graduation_time,certificate,graduation_unit,staff_certificate,input_time,manager) values(?,?,?,?,?,?,?,?)";
		Connection con=dbCon.getConnection();
		
		try
		{
			PreparedStatement pst=con.prepareStatement(s);						
			pst.setString(1,edu.getStaff_id());
			pst.setDate(2, edu.getEnrollment_time());
			pst.setDate(3, edu.getGraduation_time());
			pst.setString(4, edu.getCertificate());
			pst.setString(5, edu.getGraduation_unit());
			pst.setString(6, edu.getStaff_certificate());
			pst.setDate(7, edu.getInput_time());
			pst.setString(8,edu.getManager());
			int i=pst.executeUpdate();	
			con.close();
			if(i>0)
			{
				return true;
				
			}
			else
			{
			
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		finally
		{
			
		}
	}

	@Override
	public boolean delete_education(String staff_id, Date start_time) {
		String st="delete from education where staff_id=? and enrollment_time=?";
		Connection con=dbCon.getConnection();
		try {
			PreparedStatement pst=con.prepareStatement(st);	
			pst.setString(1, staff_id);
			pst.setDate(2, start_time);
			int i=pst.executeUpdate();
			if(i>0)
			  return true;
			else
				return false;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			
		}
		
	}

}
