package edu.zjgsu.courseapp.servlet;

import edu.zjgsu.courseapp.bean.ForumRecord;
import edu.zjgsu.courseapp.service.ForumRecordService;
import edu.zjgsu.courseapp.service.impl.ForumRecordServiceImpl;
import edu.zjgsu.courseapp.utils.NetworkUtility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ForumServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonString = NetworkUtility.exhaustBufferedReader(request.getReader());
        String id = request.getParameter("courseId");
        PrintWriter writer = response.getWriter();
        if (id == null) {
            response.setStatus(400);
            writer.write("You didn't specify the right id");
            return;
        }

//        String jsonString = NetworkUtility.exhaustBufferedReader(
//                new BufferedReader(new InputStreamReader(request.getInputStream())));
        System.out.println("jsonstring [" + jsonString + "]");
        try {
            JSONObject jsonObject = (JSONObject) (new JSONParser()).parse(jsonString);
            ForumRecord forumRecord = new ForumRecord(id,
                    (String) jsonObject.get("commentTime"),
                    (String) jsonObject.get("commentBy"),
                    (String) jsonObject.get("content"));
            int effectedCount = forumRecordService.insert(forumRecord);
            if (effectedCount >= 1) {
                writer.write("Done");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.write("Error");
    }

    ForumRecordService forumRecordService = new ForumRecordServiceImpl();
}
