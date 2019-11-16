package com.cdutcm.SchoolBus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class JumpController {
    @RequestMapping("/")//登录
    public String login() {
        return "login";
    }

    @RequestMapping("/retrieve")//找回密码
    public String retrieve(HttpServletRequest request) {
        try {
            String forgetUsername = request.getParameter("username");
            if (forgetUsername != null) {
                request.getSession().setAttribute("forgetUsername", forgetUsername);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forgetPassword-1";
    }

    @RequestMapping("/confirm")//确认密码
    public String confirm() {
        return "forgetPassword-2";
    }

    @RequestMapping("/modifyPassword")//修改密码
    public String modifyPassword() {
        return "order/modifyPassword";
    }
}