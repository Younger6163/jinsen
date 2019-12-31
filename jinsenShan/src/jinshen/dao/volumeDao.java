package jinshen.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinshen.bean.volume;


public interface volumeDao {

	public int addVolume(volume cp);
	public int updateVolume(volume cp);
	public List<volume> findVolume(String sql);
	public int delVolume(String sql);
	public int findMaxid();
	public volume findVolumeSingle(String sql);
	//public List<CodePage> findByUser(String sql);
	
	//public HashMap<String,cat> findcarrr(String sql);
	//public int findcount(String sql);
}
