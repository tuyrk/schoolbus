package com.cdutcm.test;

import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.*;

public class SelectByTime {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Time start = new Time(12, 15, 0);
        Time end = new Time(20, 40, 0);
        preparedStatement = connection.prepareStatement("SELECT * FROM busstatus WHERE TIME>=? AND TIME <=?");
        preparedStatement.setTime(1, start);
        preparedStatement.setTime(2, end);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.print(resultSet.getString(1) + " ");
            System.out.print(resultSet.getString(2) + " ");
            System.out.print(resultSet.getString(3) + " ");
            System.out.print(resultSet.getString(4) + " ");
            System.out.print(resultSet.getString(5) + " ");
            System.out.println(resultSet.getString(6));
        }
    }
}
