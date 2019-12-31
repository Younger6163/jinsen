package jinshen.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinshen.bean.totaltree;
import jinshen.bean.yeartree;
import jinshen.bean.codejson;
import jinshen.bean.codepage;
import jinshen.bean.monthtree;
import jinshen.bean.newtree;
import jinshen.bean.producetree;
import jinshen.bean.statetree;


public interface codepageDao {

	public int addCodePage(codepage cp);
	public int updateCodePage(codepage cp);
	public List<codepage> findCodePage(String sql);
	public int delCodePage(String sql);
	public int findMaxid();
	public List<codejson> findcodeJson(String sql);
	public codepage findCodeSingle(String sql);
	public int updateCode(String sql);
	public double findcount(String sql);
	public int addCodeStatus(String sql);
	
	public List<newtree> eachcompany(String sql);
	
	public List<totaltree> findTotaltree();
	public totaltree findSale(int year);//金森公司2019年1-12月销售情况表
	
	
	public List<yeartree> findYeartree();
	public yeartree printYeartree(int year,int month);//打印金森结算签收单
	
	public List<statetree> findState();
	public statetree printStatetree(int year);//打印金森结算登记表
	
	public List<producetree> findProduce();//木材生产工资结算单
	public producetree printProduce(int year);
	
	public List<monthtree> findMonth();//月销售情况表
	public monthtree printMonth(int year,int month);
	
}
