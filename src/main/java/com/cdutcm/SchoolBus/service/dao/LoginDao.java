package com.cdutcm.SchoolBus.service.dao;

import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public static boolean checkLogin(String username, String password) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM userinfo WHERE NUMBER = ? AND PASSWORD = ? LIMIT 1;");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement("UPDATE orderinfo INNER JOIN (SELECT ID AS i FROM orderinfo WHERE NUMBER = ? AND STATE LIKE '已%' ORDER BY ID desc LIMIT 2,1) AS p INNER JOIN (SELECT count(ID) AS c FROM orderinfo WHERE NUMBER = ? AND STATE LIKE '已%') AS q SET FLAG = 0 WHERE ID = i AND c > 2;");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
                return true;
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
