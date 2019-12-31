package jinshen.dao;

import java.util.List;

import jinshen.bean.count;
import jinshen.bean.order;


public interface orderDao {

	public List<order> findOrder(String sql);
	public int addOrder(order c);
	public order findOrderSingle(String sql);
	public int delById(String sql);
	public int updateOrder(order c);
	public int updateOrder(String sql);
	public int findMaxid(String sql);
	public count findListCount(String sql);
	public List<count> findListCount1(String sql);
	//public List<cat> findByNameList(String sql);
}
