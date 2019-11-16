package com.cdutcm.SchoolBus.controller;

import com.cdutcm.SchoolBus.service.dao.PasswordDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AuthorityController {

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/modifyPasswordService")
    public String modifyPassword(@RequestParam String oldPassword, @RequestParam String newPassword, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (PasswordDao.checkOldPassword(username, oldPassword)) {//旧密码正确
            if (PasswordDao.modifyPassword(username, newPassword)) {//修改成功
                return "success";//修改成功
            }
            return "fail";//修改失败
        }
        return "error";//旧密码错误
    }
}
