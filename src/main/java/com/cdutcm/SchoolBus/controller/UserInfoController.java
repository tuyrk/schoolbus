package com.cdutcm.SchoolBus.controller;

import com.cdutcm.SchoolBus.model.UserInfo;
import com.cdutcm.SchoolBus.service.dao.SaveUserinfoDao;
import com.cdutcm.SchoolBus.service.dao.UserInfoDao;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class UserInfoController {
    @RequestMapping("/userinfo")//个人信息
    public ModelAndView userinfo(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/perIS");
        String username = (String) request.getSession().getAttribute("username");
        UserInfo userInfo = UserInfoDao.getUserInfo(username);
        modelAndView.addObject("userInfo", userInfo);
        return modelAndView;
    }

    @RequestMapping("/modifyUserinfo")//修改个人信息
    public ModelAndView modify(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/perIM");
        String username = (String) request.getSession().getAttribute("username");
        UserInfo userInfo = UserInfoDao.getUserInfo(username);
        modelAndView.addObject("userInfo", userInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/saveUserinfo", method = RequestMethod.POST)//保存用户信息
    public ModelAndView saveUserinfo(MultipartFile file, String phone, String email, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String username = (String) request.getSession().getAttribute("username");
        String contentType = file.getContentType().substring(file.getContentType().indexOf(File.separator) + 1);
        //头像地址
        String url = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "statics" + File.separator + "head" + File.separator + username + "." + contentType;
        if (!file.isEmpty()) {
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SaveUserinfoDao.saveUserinfo(phone, email, username, contentType)) {
            modelAndView.setViewName("order/perIS");
        } else {
            modelAndView.setViewName("order/perIM");
        }
        UserInfo userInfo = UserInfoDao.getUserInfo(username);
        modelAndView.addObject("userInfo", userInfo);
        return modelAndView;
    }
}
