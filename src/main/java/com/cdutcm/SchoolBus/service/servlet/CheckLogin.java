package com.cdutcm.SchoolBus.service.servlet;

import com.cdutcm.SchoolBus.service.dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkLogin")
public class CheckLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        checkLogin(req, resp);
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = "fail";
        if (LoginDao.checkLogin(username, password)) {
            request.getSession().setAttribute("username", username);
            result = "success";//登录成功
        }
        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(result);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
