package org.lkw.user.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc 工具类
 * @author liangkangwen 
 *
 */
public class JDBCUtil {
	
    
    public static Connection getConnection() {
    	Connection conn = null;
    	try {
    		InputStream inputStream = new FileInputStream("config/db.properties");
    		Properties dbProperties = new Properties();
    		dbProperties.load(inputStream);
    		String name = dbProperties.getProperty("mysql.driver");  
    		String url = dbProperties.getProperty("mysql.url");  
            String user = dbProperties.getProperty("mysql.user");  
            String password = dbProperties.getProperty("mysql.password");  
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return conn;
    }
    
    /**
     * 释放资源
     * @param conn
     * @param statement
     * @param resultSet
     */
    public static void close(Connection conn, Statement statement, ResultSet resultSet) {  
    	if (resultSet != null) {
    		try {
    			resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if (statement != null) {
    		try {
    			statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if (conn != null) {
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }  
}
