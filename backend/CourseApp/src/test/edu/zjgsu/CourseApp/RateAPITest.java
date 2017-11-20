package edu.zjgsu.courseapp;

import edu.zjgsu.courseapp.utils.NetworkUtility;
import org.apache.log4j.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

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
            logger.info("Rate API Test is using url-starter: " + urlStarter);
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
        assertEquals(RATE, rate);
    }

    private String doPostRequest(String urlString) {
        return NetworkUtility.doPostRequest(urlString);
    }
    
    private String doGetRequest(String urlString) {
        return NetworkUtility.doGetRequest(urlString);
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
