package com.cdutcm.SchoolBus.service.dao;

import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class ClearDBDao {
    public static boolean clearBus(Time start, Time end) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE busstatus SET RESERVATION = 0 WHERE TIME >= ? AND TIME <= ?;");
            preparedStatement.setTime(1, start);
            preparedStatement.setTime(2, end);
            int result = preparedStatement.executeUpdate();
            connection.commit();
            if (result >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean clearOrder(String timeStr, Time time) {
        // time = "18:30"/"06:00";
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE userinfo INNER JOIN (SELECT NUMBER n FROM ORDERINFO WHERE TIME LIKE ? AND STATE = '预约成功') p SET STATE = 1 WHERE NUMBER = n;");
            preparedStatement.setString(1, "%" + timeStr);
            int result1 = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("UPDATE orderinfo SET STATE = '已发车' WHERE TIME LIKE ? AND STATE = '预约成功';");
            preparedStatement.setString(1, "%" + timeStr);
            int result2 = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("UPDATE busstatus SET RESERVATION = TOTAL WHERE TIME = ?;");
            preparedStatement.setTime(1, time);
            int result3 = preparedStatement.executeUpdate();
            connection.commit();
            if (result1 >= 1 && result2 >= 1 && result3 >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
