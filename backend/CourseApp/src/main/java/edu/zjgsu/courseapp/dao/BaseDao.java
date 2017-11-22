package edu.zjgsu.courseapp.dao;

import java.util.List;

public interface BaseDao {

    List<Object> query(String sql,Object[] params);
    
    int update(String sql,Object[] params);
	
}
