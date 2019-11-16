package com.cdutcm.SchoolBus.service.servlet;

import com.cdutcm.SchoolBus.util.SendEmailByQQ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/sendCode")
public class SendCode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendCode(req, resp);
    }

    public void sendCode(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String code = "";
        String result = "fail";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        String content = "<div style=\"background-color:#d0d0d0;background-image:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg.png);text-align:center;padding:20px\"><div class=\"x_mmsgLetter\"style=\"max-width:350px;margin:0 auto;color:rgb(51,51,51);background-color:rgb(255,255,255);border:0 solid rgb(170,170,170);border-radius:5px;font-family:Verdana,sans-serif,serif,EmojiFont;\"><div class=\"x_mmsgLetterHeader\"style=\"height:23px;background:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg_topline.png) repeat-x 0 0\"></div><div class=\"x_mmsgLetterContent\"style=\"text-align:left;padding:30px;font-size:14px;line-height:1.5;background:url(http://cdutcm.edu.cn/images/index_top_1.gif) no-repeat top center;background-size:65% 10.5%;\"><div><br><p>您好!</p><p>感谢您使用成都中医药大学校车预约系统<br>您正在找回您的密码。请回填如下6位验证码：</p><p class=\"x_mmsgLetterDigital\"style=\"font-size:22px;letter-spacing:10px;\">" + code + "</p><p>验证码在30分钟内有效，30分钟后需要重新发送验证码</p></div><div class=\"x_mmsgLetterInscribe\"style=\"padding:40px 0 0\"><img data-imagetype=\"External\"src=\"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=328327349,855323749&fm=27&gp=0.jpg\"class=\"x_mmsgAvatar\"style=\"float:left\"height=\"45px\"width=\"45px\"><div class=\"x_mmsgSender\"style=\"margin:0 0 0 54px\"><p class=\"x_mmsgName\"style=\"margin:0 0 10px\">TYK</p><p class=\"x_mmsgInfo\"style=\"font-size:12px;margin:0;line-height:1.2\">开发人员<br><a href=\"mailto:766564616@qq.com\"target=\"_blank\"rel=\"noopener noreferrer\"style=\"color:#407700\">766564616@qq.com</a></p></div></div></div><div class=\"x_mmsgLetterFooter\"style=\"margin:16px;text-align:center;font-size:12px;color:#888\"><img data-imagetype=\"External\"data-imageerror=\"RelWithoutBase\"originalsrc=\"/cgi-bin/readtemplate?sid=$SID$&amp;loc=pushmail1,weixiniphone,show,44\"src=\"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAEALAAAAAABAAEAAAIBTAA7\"onload=\"InlineImageLoader.GetLoader().Load(this)\"style=\"width:1px;height:1px\"crossorigin=\"anonymous\"><img data-imagetype=\"External\"src=\"http://weixin.qq.com/cgi-bin/reportforpromote?uin=$RCPTUIN$&amp;sdate=$SDATE$&amp;tver=$TVER$\"style=\"width:1px;height:1px\"></div></div></div>";
        if (SendEmailByQQ.SendEmail(email, code, content)) {
            request.getSession().setAttribute("code", code);
            request.getSession().setMaxInactiveInterval(1800);//session有效期半个小时
            System.out.println("发送成功");
            result = "success";//发送成功
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
