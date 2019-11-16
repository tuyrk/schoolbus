package com.cdutcm.SchoolBus.service.dao;

import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordDao {
    public static boolean checkOldPassword(String username, String oldPassword) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String password = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT password FROM userinfo WHERE NUMBER = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
            if (password.equals(oldPassword)) {
                return true;
            }
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

    public static boolean modifyPassword(String username, String newPassword) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE userinfo SET PASSWORD = ? WHERE NUMBER = ?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            int result=preparedStatement.executeUpdate();
            connection.commit();
            if (result==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
