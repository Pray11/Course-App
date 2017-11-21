package edu.zjgsu.CourseApp.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0ConnectionProvider {
    private static DataSource dataSource;
    private static final String DRIVER_NAME;
    private static final String URL;
    private static final String UNAME;
    private static final String PWD;
    private static final String dbName;

    static {

        dbName="courseapp_db";
        DRIVER_NAME = "com.mysql.jdbc.Driver";
        URL = "jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=utf8&useSSL=false";
        UNAME = "root";
        PWD = "";
        dataSource = setupDataSource();
    }
    public static synchronized Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static DataSource setupDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        
        // do it in c3p0.properties
        /*try {
            cpds.setDriverClass(DRIVER_NAME);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(URL);
        cpds.setUser(UNAME);
        cpds.setPassword(PWD);
        cpds.setMinPoolSize(3);
        cpds.setMaxPoolSize(10);
        */

        
        return cpds;
    }
}
