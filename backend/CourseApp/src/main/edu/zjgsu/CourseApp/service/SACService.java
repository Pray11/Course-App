package edu.zjgsu.CourseApp.service;

import java.util.List;

import edu.zjgsu.CourseApp.bean.SACBean;

public interface SACService {
	List<SACBean> searchCourseRate(String courseId, String studentId);
	int insertCourseRate(String courseId, String studentId, String rate);
	int updateCourseRate(String courseId, String studentId, String rate);
}
