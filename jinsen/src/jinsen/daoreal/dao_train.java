package jinsen.daoreal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jinsen.bean.train;
import jinsen.dao.train_inter;
import jinsen.db.dbCon;



public class dao_train implements train_inter {

	@Override
	public List<train> find_all() {
		String st="select * from train";
		List<train> lis=new ArrayList<train>();
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);						
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				
				train ed=new train();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setTrain_name(r.getString("train_name"));
				ed.setTrain_start_time(r.getDate("train_start_time"));
				//ed.setManager(r.getString("infect_time"));
				ed.setTrain_end_time(r.getDate("train_end_time"));
				//ed.setProfession_titles(r.getString("profession_titles"));
				ed.setTrain_certificate(r.getString("train_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				lis.add(i, ed);
				i++;
			}
			if(i==0)
			{
				System.out.print("没有培训记录！");
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
	public train find_staff_id(String staff_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<train> find_staff_name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<train> train_time(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert_train(train tr) {
		String s="insert into train(staff_id,train_name,train_start_time,train_end_time,train_certificate,input_time,manager) values(?,?,?,?,?,?,?)";
		Connection con=dbCon.getConnection();
		
		try
		{
			PreparedStatement pst=con.prepareStatement(s);						
			pst.setString(1,tr.getStaff_id());
			pst.setString(2,tr.getTrain_name());
			pst.setDate(3, tr.getTrain_start_time());
			pst.setDate(4, tr.getTrain_end_time());
			pst.setString(5,tr.getTrain_certificate());			
			pst.setDate(6, tr.getInput_time());
			pst.setString(7,tr.getManager());
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
	public boolean update_train(train tr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete_train(String staff_id, Date date) {
		String st="delete from train where staff_id=? and train_start_time=?";
		Connection con=dbCon.getConnection();
		try {
			PreparedStatement pst=con.prepareStatement(st);	
			pst.setString(1, staff_id);
			pst.setDate(2, date);
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
	public train find_train(String id, Date d) {
		String st="select * from train where staff_id=? and train_start_time=?";
		int i=0;
		try
		{
			
			Connection con=dbCon.getConnection();
			PreparedStatement pst=con.prepareStatement(st);		
			pst.setString(1,id);
			pst.setDate(2, d);
			ResultSet r=pst.executeQuery();
			if(r.next())
			{
				
				train ed=new train();
				ed.setStaff_id(r.getString("staff_id"));
				ed.setTrain_name(r.getString("train_name"));
				ed.setTrain_start_time(r.getDate("train_start_time"));
				//ed.setManager(r.getString("infect_time"));
				ed.setTrain_end_time(r.getDate("train_end_time"));
				//ed.setProfession_titles(r.getString("profession_titles"));
				ed.setTrain_certificate(r.getString("train_certificate"));
				ed.setInput_time(r.getDate("input_time"));
				ed.setManager(r.getString("manager"));
				return ed;
			}
			
			else
			{
			
				return null;
			}
			
		}
		catch (Exception e)
		{
			return null;
		}
		finally {
			
		}
	
	}

}
