package com.cdutcm.SchoolBus.service.servlet;

import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/retrieveService")
public class RetrieveService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        retrieveService(req, resp);
    }

    public void retrieveService(HttpServletRequest request, HttpServletResponse response) {
        String securityCode = request.getParameter("securityCode");
        String code = (String) WebUtils.getSessionAttribute(request, "code");
        String result = "fail";
        if (securityCode.equals(code)) {
            request.getSession().removeAttribute("code");
            System.out.println("验证码正确");
            result = "success";//验证码正确
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
