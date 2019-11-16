package com.cdutcm.SchoolBus.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBCP配置类
 */
public class DBCPUtil {
    private static Properties properties = new Properties();
    private static DataSource dataSource;

    //加载DBCP配置文件
    static {
        try {
            InputStream inputStream = DBCPUtil.class.getClassLoader().getResourceAsStream("properties/dbcp.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从连接池中获取一个连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放连接
     *
     * @param conn
     */
    public static void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();//将Connection连接对象还给数据库连接池
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
