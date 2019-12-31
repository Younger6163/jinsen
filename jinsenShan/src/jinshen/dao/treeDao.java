package jinshen.dao;

import java.util.List;
import jinshen.bean.tree;

public interface treeDao {

	public List<tree> findTree(String sql);
	public int addTree(tree c);
	public tree findTreeSingle(String sql);
	public int delById(String sql);
	public int updateTree(tree c);
	public int findMaxid();
	public int updateTree(String sql);
	//public List<cat> findByNameList(String sql);
}
