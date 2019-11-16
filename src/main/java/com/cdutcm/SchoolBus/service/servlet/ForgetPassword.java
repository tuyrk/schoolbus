package com.cdutcm.SchoolBus.service.servlet;

import com.cdutcm.SchoolBus.service.dao.PasswordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/forgetPassword")
public class ForgetPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forgetPassword(req, resp);
    }

    public void forgetPassword(HttpServletRequest request, HttpServletResponse response) {
        String rePassword = request.getParameter("rePassword");
        String username = (String) request.getSession().getAttribute("forgetUsername");
        String result = "fail";
        if (PasswordDao.modifyPassword(username, rePassword)) {//修改成功
            result = "success";//保存成功
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
