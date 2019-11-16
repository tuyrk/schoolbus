package com.cdutcm.SchoolBus.controller;

import com.cdutcm.SchoolBus.model.Bus;
import com.cdutcm.SchoolBus.model.Order;
import com.cdutcm.SchoolBus.model.UserInfo;
import com.cdutcm.SchoolBus.service.dao.OrderDao;
import com.cdutcm.SchoolBus.service.dao.UserInfoDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class OrderController {
    @RequestMapping("/order")//校车预约
    public ModelAndView order(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        String username = (String) request.getSession().getAttribute("username");
        UserInfo userInfo = UserInfoDao.getUserInfo(username);
        Bus[] buses = OrderDao.busStatus(username);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("buses", buses);
        modelAndView.setViewName("order/order");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/bespeak", method = RequestMethod.POST)//提交,现在预约
    public String bespeak(@RequestParam String id, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (OrderDao.bespeak(Integer.parseInt(id), username)) {
            return "success";//预约成功
        }
        return "fail";//预约失败
    }

    @RequestMapping("/seeOrder")
    public ModelAndView seeOrder(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String username = (String) request.getSession().getAttribute("username");
        Order nowOrder = OrderDao.nowOrder(username);
        modelAndView.addObject("nowOrder", nowOrder);
        Order[] historyOrder = OrderDao.historyOrder(username);
        modelAndView.addObject("historyOrder", historyOrder);
        modelAndView.setViewName("order/checkOrder");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public String cancelOrder(@RequestParam String id, @RequestParam String busId, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (OrderDao.cancelOrder(username, id, busId)) {
            return "success";//取消成功
        }
        return "fail";//取消失败
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam String id) {
        if (OrderDao.deleteOrder(id)) {
            return "success";//删除成功
        }
        return "fail";//删除失败
    }
}
