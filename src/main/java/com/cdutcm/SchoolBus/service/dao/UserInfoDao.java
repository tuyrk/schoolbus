package com.cdutcm.SchoolBus.service.dao;

import com.cdutcm.SchoolBus.model.UserInfo;
import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDao {
    public static UserInfo getUserInfo(String username) {
        UserInfo userInfo = new UserInfo();
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM userinfo WHERE NUMBER = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userInfo.setName(resultSet.getString("name"));
                userInfo.setSex(resultSet.getString("sex"));
                userInfo.setDepartment(resultSet.getString("department"));
                userInfo.setNumber(resultSet.getString("number"));
                userInfo.setPhone(resultSet.getString("phone"));
                userInfo.setEmail(resultSet.getString("email"));
                userInfo.setHeadimg(resultSet.getString("headimg"));
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
        return userInfo;
    }
}
