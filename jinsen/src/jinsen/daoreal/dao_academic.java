package jinsen.daoreal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.academic_title;
import jinsen.dao.academic_inter;
import jinsen.db.dbCon;


public class dao_academic implements academic_inter {

	@Override
	public List<academic_title> findall() {
		String st="select * from academic_title";
		List<academic_title> lis=new ArrayList<academic_title>();
		int i=0;
		try
		{
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);						
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				academic_title ed=new academic_title();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setGain_time(r.getDate("gain_time"));
				//ed.setManager(r.getString("infect_time"));
				ed.setInfect_time(r.getDate("infect_time"));
				ed.setProfession_titles(r.getString("profession_titles"));
				ed.setCertificate_of_title(r.getString("certificate_of_title"));
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
	public boolean insert_academic(academic_title ac) {
		String s="insert into academic_title(staff_id,gain_time,infect_time,profession_titles,certificate_of_title,input_time,manager) values(?,?,?,?,?,?,?)";
		Connection con=dbCon.getConnection();
		try {
			PreparedStatement pst=con.prepareStatement(s);	
			pst.setString(1, ac.getStaff_id());
			pst.setDate(2, ac.getGain_time());
			pst.setDate(3, ac.getInfect_time());
			pst.setString(4, ac.getProfession_titles());
			pst.setString(5, ac.getCertificate_of_title());
			pst.setDate(6, ac.getInput_time());
			pst.setString(7, ac.getManager());
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

	@Override
	public List<academic_title> find_id(String staff_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<academic_title> find_profession_titles(String profession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<academic_title> find_staff_name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String staff_id, Date start) {
		String st="delete from academic_title where staff_id=? and gain_time=?";
		Connection con=dbCon.getConnection();
		try {
			PreparedStatement pst=con.prepareStatement(st);	
			pst.setString(1, staff_id);
			pst.setDate(2, start);
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
