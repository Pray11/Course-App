package edu.zjgsu.courseapp.dao.impl;

import java.util.List;

import edu.zjgsu.courseapp.dao.BaseDao;
import edu.zjgsu.courseapp.utils.DBHelper;

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
