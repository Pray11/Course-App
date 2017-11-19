package edu.zjgsu.CourseApp.bean;

public class SACBean {
	private String student_id;
	private long course_id;
	private String rate;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public void setCourse_id(long course_id) {
		// TODO Auto-generated method stub
		this.course_id = course_id;
	}
}
