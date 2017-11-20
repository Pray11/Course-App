package edu.zjgsu.courseapp;

import edu.zjgsu.courseapp.utils.NetworkUtility;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ForumAPITest {

    @BeforeClass
    public static void getUrlStarter() {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(new File("conf/test.conf")));
            urlStarter = (String) properties.get("url-starter");
            logger.info("Forum API Test is using url-starter: " + urlStarter);
            authCode = (String) properties.get("auth-code");
        } catch (IOException e) {
            e.printStackTrace();
            urlStarter = "http://localhost:8080";
            logger.info("Test configuration file is not found, " +
                    "using default [http://localhost:8080]");
            authCode = "zjgsu";
        }
    }

    @Test
    public void testPost() {
        String response = NetworkUtility.postJson(POST_URL, jsonObject1.toString());
        assertEquals("Done", response);
    }

    /**
     * Post one comment and expect to get only one comment
     */
    @Test
    public void testGet() {
        // clear the database
        NetworkUtility.doPostRequest(POST_URL + "&clear=true&authCode=" + authCode);
        NetworkUtility.postJson(POST_URL, jsonObject1.toString());
        // check number 1
        JSONArray jsonArray = NetworkUtility.getJson(GET_URL);
        if (jsonArray == null) {
            fail("jsonArray is null");
        } else
            assertEquals(1, jsonArray.size());
    }

    @Test
    public void testGetMultipleComments() {
        // clear the database
        NetworkUtility.doPostRequest(POST_URL + "&clear=true&authCode=" + authCode);
        NetworkUtility.postJson(POST_URL, jsonObject1.toString());
        NetworkUtility.postJson(POST_URL, jsonObject2.toString());
        JSONArray jsonArray = NetworkUtility.getJson(GET_URL);
        if (jsonArray == null) {
            fail("jsonArray is null");
        } else {
            assertEquals(2, jsonArray.size());
            // also the sequence matters
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            // counter order by time
            assertEquals("Yunfeng", jsonObject.get("commentBy"));
        }
    }

    private static String urlStarter;
    private static final Logger logger = Logger.getLogger(ForumAPITest.class);
    private final String COURSE_ID = "1";
    private String POST_URL = urlStarter + "/forum?courseId=" + COURSE_ID;
    private String GET_URL = urlStarter + "/forum?courseId=" + COURSE_ID;
    private static String authCode;

    private JsonObject jsonObject1 = Json.createObjectBuilder()
            .add("commentTime", "2017-12-08 11:11:11")
            .add("commentBy", "Yunfeng")
            .add("content", "I don't wanna")
            .build();

    private JsonObject jsonObject2 = Json.createObjectBuilder()
            .add("commentTime", "2017-11-17 12:36:15")
            .add("commentBy", "Puzhen")
            .add("content", "This course is really bad")
            .build();
}
