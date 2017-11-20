package edu.zjgsu.courseapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.zjgsu.courseapp.bean.StudentAttendedCoursesBean;
import edu.zjgsu.courseapp.dao.BaseDao;
import edu.zjgsu.courseapp.dao.impl.BaseDaoImpl;
import edu.zjgsu.courseapp.service.StudentAttendedCoursesService;

public class StudentAttendedCoursesServiceImpl implements StudentAttendedCoursesService{

	BaseDao baseDao = new BaseDaoImpl();
	
	@Override
	public List<StudentAttendedCoursesBean> searchCourseRate(String courseId, String studentId) {
		// TODO Auto-generated method stub
		String sql = "select * from student_attended_courses where (course_id="+courseId+" and student_id="+studentId+");";
		List<Object> rsList = baseDao.query(sql, null);
		List<StudentAttendedCoursesBean> sacBList = new ArrayList<StudentAttendedCoursesBean>();
		
		for (int i=0; i<rsList.size(); i++) {
			Map<String, Object> rsMap = (Map) rsList.get(i);
			
			StudentAttendedCoursesBean sacBean = new StudentAttendedCoursesBean();
			sacBean.setCourse_id((long) rsMap.get("course_id"));
			sacBean.setRate((String) rsMap.get("rate"));
			sacBean.setStudent_id((String) rsMap.get("student_id"));
			sacBList.add(sacBean);
		}
		
		return sacBList;
	}

	@Override
	public int insertCourseRate(String courseId, String studentId, String rate) {
		// TODO Auto-generated method stub
		String sql = "update student_attended_courses SET rate="+rate+" where (student_id="+studentId+" and course_id="+courseId+");";
		
		return baseDao.update(sql, null);
	}

	@Override
	public int updateCourseRate(String courseId, String studentId, String rate) {
		String sql = "insert into student_attended_courses (course_id, student_id, rate) value ("+courseId+", "+studentId+", "+rate+")";   
		
		return baseDao.update(sql, null);
	}
	
	

}
