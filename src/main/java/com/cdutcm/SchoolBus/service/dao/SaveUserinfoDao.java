package com.cdutcm.SchoolBus.service.dao;

import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveUserinfoDao {
    //保存用户信息：电话号码、邮箱、用户名、头像
    public static boolean saveUserinfo(String phone, String email, String username, String contentType) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE userinfo SET PHONE = ?,EMAIL = ?,HEADIMG = ? WHERE NUMBER = ?");
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, "statics/head/" + username + "." + contentType);
            preparedStatement.setString(4, username);
            int result = preparedStatement.executeUpdate();
            connection.commit();
            if (result == 1) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
