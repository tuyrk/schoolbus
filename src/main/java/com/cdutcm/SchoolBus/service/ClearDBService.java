package com.cdutcm.SchoolBus.service;

import com.cdutcm.SchoolBus.service.dao.ClearDBDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

@Component("clearDBDao")
public class ClearDBService {
    //晚上2点执行操作 设置车辆为12:15-20:40的已预约人数为0
    @Scheduled(cron = "0 0 2 * * ?")
    public void job1() {
        Time start = new Time(12, 15, 0);
        Time end = new Time(20, 40, 0);
        if (!ClearDBDao.clearBus(start, end)) {
            System.out.println(new Date() + "清理失败");
        }
    }

    //下午2点半执行操作 设置车辆为7:40到8:10的已预约人数为0
    @Scheduled(cron = "0 30 14 * * ?")
    public void job2() {
        Time start = new Time(7, 40, 0);
        Time end = new Time(8, 10, 0);
        if (!ClearDBDao.clearBus(start, end)) {
            System.out.println(new Date() + "清理失败");
        }
    }

    @Scheduled(cron = "0 40 7 * * ?")
    public void time7_40() {
        ClearDBDao.clearOrder("07:40",new Time(7,40,0));
    }

    @Scheduled(cron = "0 50 7 * * ?")
    public void time7_50() {
        ClearDBDao.clearOrder("07:50",new Time(7,50,0));
    }

    @Scheduled(cron = "0 10 8 * * ?")
    public void time8_10() {
        ClearDBDao.clearOrder("08:10",new Time(8,10,0));
    }

    @Scheduled(cron = "0 15 12 * * ?")
    public void time12_15() {
        ClearDBDao.clearOrder("12:15",new Time(12,15,0));
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void time14_00() {
        ClearDBDao.clearOrder("14:00",new Time(14,00,0));
    }

    @Scheduled(cron = "0 50 16 * * ?")
    public void time16_50() {
        ClearDBDao.clearOrder("16:50",new Time(16,50,0));
    }

    @Scheduled(cron = "0 30 12 * * ?")
    public void time12_30() {
        ClearDBDao.clearOrder("12:30",new Time(12,30,0));
    }

    @Scheduled(cron = "0 30 15 * * ?")
    public void time15_30() {
        ClearDBDao.clearOrder("15:30",new Time(15,30,0));
    }

    @Scheduled(cron = "0 0 17 * * ?")
    public void time17_00() {
        ClearDBDao.clearOrder("17:00",new Time(17,00,0));
    }

    @Scheduled(cron = "0 40 20 * * ?")
    public void time20_40() {
        ClearDBDao.clearOrder("20:40",new Time(20,40,0));
    }
}
