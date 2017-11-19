package edu.zjgsu.CourseApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.zjgsu.CourseApp.bean.SACBean;
import edu.zjgsu.CourseApp.service.SACService;
import edu.zjgsu.CourseApp.service.impl.SACServiceImpl;

/**
 * Servlet implementation class CourseRateServlet
 */
@WebServlet("/CourseRate")
public class CourseRateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SACService sacService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseRateServlet() {
        super();
        // TODO Auto-generated constructor stub
        sacService = new SACServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
	    String courseId = request.getParameter("courseid");
	    String studentId = request.getParameter("studentid");
	    
	    List<SACBean> sacRs = sacService.searchCourseRate(courseId, studentId);
	    
	    try{
	    	if (sacRs.size()>0) {
	    		for (int i=0; i<sacRs.size(); i++) {
	    			out.println(sacRs.get(i).getStudent_id());
	    			out.println(sacRs.get(i).getCourse_id());
	    			out.println(sacRs.get(i).getRate());
	    		}
	    	} else {
	    		out.println("未查询到结果");
	    	}
		    
	    } catch (Exception e) {
	    	out.println("ooooooah wrong!");
	    } finally {
	    	
	    	out.close();
	    }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
	    String courseId = request.getParameter("courseid");
	    String studentId = request.getParameter("studentid");
	    String rate = request.getParameter("rate");
	    String type = request.getParameter("type");
		
	    if (type.equals("insert")) {
			int changedLines = sacService.insertCourseRate(courseId, studentId, rate);
			if (changedLines!=0) {
				out.println("插入成功 affected rows:" + changedLines);
			} else {
				out.println("oooooooah wrong!");
			}
	    } else if (type.equals("update")) {
			int changedLines = sacService.insertCourseRate(courseId, studentId, rate);
			if (changedLines!=0) {
				out.println("更新成功 affected rows:" + changedLines);
			} else {
				out.println("oooooooah wrong!");
			}
	    } else {
	    	out.println("oooooooah wrong!");
	    }
		
	}

}
