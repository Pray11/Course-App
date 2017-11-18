package edu.zjgsu.CourseApp.dao.impl;

import java.util.List;

import edu.zjgsu.CourseApp.dao.BaseDao;
import edu.zjgsu.CourseApp.utils.DBHelper;

public class BaseDaoImpl implements BaseDao{
	private DBHelper db = new DBHelper();
	
	public BaseDaoImpl() {
		
	}
	
    @Override
    public List<Object> query(String sql,Object[] params) {
        return db.query(sql, params);
    }

    @Override
    public int update(String sql,Object[] params) {
        return db.update(sql, params);
    }
}
