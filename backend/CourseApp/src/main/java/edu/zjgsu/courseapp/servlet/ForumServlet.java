package edu.zjgsu.courseapp.servlet;

import edu.zjgsu.courseapp.bean.ForumRecord;
import edu.zjgsu.courseapp.service.ForumRecordService;
import edu.zjgsu.courseapp.service.impl.ForumRecordServiceImpl;
import edu.zjgsu.courseapp.utils.NetworkUtility;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Properties;

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

        if (Boolean.valueOf(request.getParameter("clear"))) {
            logger.info("User wants to clear the database, authenticating him..");
            String userAuthCode = request.getParameter("authCode");
            if (userAuthCode.equals(authCode)) {
                logger.info("User passes authentication, going to clear database...");
                List<ForumRecord> list = forumRecordService.select("1");
                for (ForumRecord forumRecord : list)
                    forumRecordService.delete(forumRecord);
                logger.info("Deleted " + list.size() + " entries in this clear command.");
            } else {
                logger.info("User fails the authentication.");
            }
        }
        logger.info("Incoming jsonstring: [" + jsonString + "]");
        if (jsonString != null && jsonString.length() > 1) {
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
                writer.write("Error");
                return;
            }
        }

        writer.write("No JSON is found posted to this servlet.");
    }

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        try {
            // TODO this properties should not read from test.conf, change it
            properties.loadFromXML(new FileInputStream(new File("conf/test.conf")));
            authCode = (String) properties.get("auth-code");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Test configuration file is not found, auth code may not work.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("courseId");
        PrintWriter writer = response.getWriter();
        if (id == null) {
            response.setStatus(400);
            writer.write("You didn't specify course id");
            return;
        }

        List<ForumRecord> records = forumRecordService.select(id);
        if (records.size() == 0) {
            writer.write("Not found");
            return;
        }
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (ForumRecord record : records) {
            builder.add(Json.createObjectBuilder()
                    .add("commentTime", record.getCommentTime())
                    .add("commentBy", record.getCommentBy())
                    .add("content", record.getContent())
                    .build());
        }
        JsonArray array = builder.build();
        response.setHeader("Content-Type", "application/json");
        writer.write(array.toString());
        writer.flush();
    }

    private ForumRecordService forumRecordService = new ForumRecordServiceImpl();

    private static final Logger logger = Logger.getLogger(ForumServlet.class);
    private String authCode;
}
