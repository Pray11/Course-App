package edu.zjgsu.CourseApp;

import org.apache.log4j.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.*;
import java.io.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class RateAPITest {

    @BeforeClass
    public static void getUrlStarter() {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(new File("conf/test.conf")));
            urlStarter = (String) properties.get("url-starter");
            logger.info("Using url-starter: " + urlStarter);
        } catch (IOException e) {
            e.printStackTrace();
            urlStarter = "http://localhost:8080";
            logger.info("Test configuration file is not found, " +
                    "using default [http://localhost:8080]");
        }
    }

    /**
     * Post request should return a "Done" message as sanity check
     */
    @Test
    public void testPost() {
        assertEquals("Done", doPostRequest(POST_URL));
    }

    @Test
    public void testGet() {
        doPostRequest(POST_URL);
        String rate = doGetRequest(GET_URL);
        assertEquals("RATE", rate);
    }

    private String doPostRequest(String urlString) {
        return doRequest(urlString, "POST");
    }
    
    private String doGetRequest(String urlString) {
        return doRequest(urlString, "GET");
    }

    /**
     * This method assumes that the response from some host contains only
     * one line of message
     * @param urlString
     * @return one line response from the host
     */
    private String doRequest(String urlString, String method) {
        try {
            HttpURLConnection conn = (HttpURLConnection)
                    (new URL(urlString)).openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod(method);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String response = rd.readLine();
            rd.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String urlStarter;
    private final String COURSE_ID = "1";
    private final String STUDENT_ID = "1512190417";
    private final String RATE = "5";
    private String POST_URL = urlStarter + "/rate?rate=" + RATE +
            "&courseId=" + COURSE_ID + "&studentId=" + STUDENT_ID;
    private String GET_URL = urlStarter + "/rate?" +
            "&courseId=" + COURSE_ID + "&studentId=" + STUDENT_ID;

    private static final Logger logger = Logger.getLogger(RateAPITest.class);
}
