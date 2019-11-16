package com.cdutcm.service;

import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.*;

public class DbcpTest {
    public static void main(String[] args) {
        try {
            Connection connection = DBCPUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ?");
            statement.setString(1, "userinfo");
            ResultSet result = statement.executeQuery();
            System.out.println(result);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
