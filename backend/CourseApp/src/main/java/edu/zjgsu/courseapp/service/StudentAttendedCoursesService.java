package edu.zjgsu.courseapp.service;

import java.util.List;

import edu.zjgsu.courseapp.bean.StudentAttendedCoursesBean;

public interface StudentAttendedCoursesService {
	List<StudentAttendedCoursesBean> searchCourseRate(String courseId, String studentId);
	int insertCourseRate(String courseId, String studentId, String rate);
	int updateCourseRate(String courseId, String studentId, String rate);
}
