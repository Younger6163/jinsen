package jinsen.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jinsen.bean.attence;
import jinsen.bean.depart_performance;
import jinsen.bean.department;
import jinsen.bean.education;
import jinsen.bean.leavef;
import jinsen.bean.overtime;
import jinsen.bean.post;
import jinsen.bean.staff;
import jinsen.bean.staff_jobsalary;
import jinsen.bean.staff_performance;
import jinsen.bean.staff_performance_list;
import jinsen.bean.staff_salary_list;


public interface salary_inter {
	
	public List<staff> find_staff_all();//查询所有员工信息
	
	public List<staff> find_staff_all_nosalary();//查询所有未录入薪资信息的员工
	
	public List<staff> find_staff_id(String id);
	
	public Double find_staff_basicpay(String id);
	
	public int insert_staff_salary(String id,String post,Double salary,String admin_id);

	public List<staff_jobsalary> find_staff_salary();
	
	public List<staff_jobsalary> find_staff_salary_adjust_name(String name);
	
	public int update_salary_basicpay(Double salary,String id);
	
	public List<staff_jobsalary> find_staff_salary_name(String name);
	
	public List<staff_jobsalary> find_staff_salary_table();//查询员工薪资信息
	
	public String find_admin_name(String admin_id);
	
	public int update_staff_salary(String staff_id,double staffsalary);//调整员工工资
	
	public staff_jobsalary find_jobsalary_sa(String staff_id);
	
	public List<staff_salary_list> find_staff_salary_list();//查询所有员工工资单
	
	public List<staff_salary_list> find_staff_salary_list_m_y(int year,int month);//根据年月查询工资单
	
	public List<attence> find_attence_month(int month);//按月份查询考勤表
	
	public int insert_staff_salary_list(List<staff_salary_list> salarylist);//添加工资单
	
	public List<leavef> find_leave_staff_id(String staff_id);//按员工号查询请假信息
	
	public List<staff_salary_list> find_staff_list_wff();//查询所有未发放工资的员工工资单
	
	public List<department> find_department_all();//查询所有部门
	
	public double find_ready_salary(int year);//按年份查询待发放薪资
	
	public double find_ready_salary_three(int year,String department,int month);//按年份、部门、月份查询待发放薪资
	
	public int update_salary(String staff_id,int year,int month,double salary,int flag);//按员工编号，结算时间发放工资
	
	public int update_salary_list_status(String staff_id,int year,int month,String str);//更新工资单状态
	
	public List<staff_salary_list> find_ready_salary_three_1(int year,String department,int month);//按年份、部门、月份查询未清算工资的工资表
	
	public education find_education_all(String staff_id);//查询员工学历表
	
	public List<staff_performance> find_staff_perfor_all(int year);//查询该年份的员工绩效信息
	
	public List<staff> find_staff_not_perfor(int year);//查询该年份未录入绩效的员工
	
	public int find_staff_work_num(String id,int year);//查询员工某一年的工作月份数
	
	public int insert_staff_perfor(staff_performance staffperfor);//录入员工绩效信息
	
	public List<department> find_depart(int year);//查询该年份未录入绩效的部门
	
	public department find_depart_id(String dp_id);//根据部门id查询部门信息
	
	public department find_depart_name(String dp_name);//根据部门名称查询部门信息
	
	public double find_dp_perfor(String dp_id,int year);//查询部门的绩效系数
	
	public List<overtime> find_overtime_id(String staff_id);//根据员工编号查询加班表
	
	public double find_stand_type(String type);//根据加班类型查询费用
	
	public int insert_dp_perfor(depart_performance depart);//录入部门绩效信息
	
	public post find_post_psid(String ps_id);//根据岗位id查询岗位信息
	
	public List<department> find_dp_name_all();//查询所有部门
	
	public List<department> find_dp_performan_isall(int year);//查询该年份是否所有部门都已录入绩效信息
	
	public double find_sum_depart_perfor_all(int year);//查询所有部门的绩效分值总和
	
	public depart_performance find_depart_perfor_name(String dp_name,int year);//查询该年份部门绩效信息
	
	public int update_depart_perfor(double depart_perfor_money,String dp_name,int year);//录入部门绩效金额
	
	public List<depart_performance> find_depart_perfor(int year);//查询该年份部门的绩效信息
	
	public List<staff> find_staff_dp(String dp_id);//查询该部门的员工信息
	
	public staff_performance find_perfor_score_all(String dp_id,int year,String staff_id);//查询该年份该部门该员工的绩效信息
	
	public int insert_staff_perforlist(staff_performance_list s);//录入员工绩效确认单
	
	public List<staff_performance_list> find_staff_perforlist_dp(String dp_name,int year);//查询该年份该部门的员工绩效单
	
	public List<staff_performance> find_staff_perfor_dp(String dp_name,int year);//查询该年份该部门的员工绩效信息
	
	public List<staff> find_staff_dp_name(String dp_name);//查询该部门的员工信息
	
	public List<attence> find_attence_month(String staff_id,int year);//查询该年份该员工的考勤信息
	
	public List<staff_performance_list> find_staff_performance_list_all();//查询所有员工所有年份未发放或是未发完的绩效信息单
	
	public List<staff_performance> find_staff_perf_all(String staff_id,int year);//查询该年份该员工的绩效信息
	
	public double find_ready_perfor(int year);//查询该年份待发放的绩效
	
	public double find_ready_perfor_depart_year(int year,String department);//根据部门和年份查询剩余绩效
	
	public List<staff_performance_list> find_payperfor_list(int year,String department);//查询需要发放绩效的绩效单
	
	public int update_perfor_list_id(String staff_id,int year,double money);//更新绩效金额
	
	public int update_perfor_list_status(String str,String staff_id,int year);//更新绩效单状态
	
	public department find_salary_month_table(int year,int month,String department);//生成薪酬月报表
	
	public department find_salary_year_table(int year,String department);//生成薪酬年度报表
	
}
