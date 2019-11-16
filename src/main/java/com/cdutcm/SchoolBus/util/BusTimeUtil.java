package com.cdutcm.SchoolBus.util;

import java.sql.Time;
import java.util.Calendar;

public class BusTimeUtil {
    public static Time[] getBusTime() {
        Time[] times = new Time[2];
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        Calendar first = Calendar.getInstance();
        first.set(year, month, day, 8, 10);
        Calendar second = Calendar.getInstance();
        second.set(year, month, day, 20, 40);
        if (now.before(first) || now.equals(first) || now.after(second)) {
            times[0] = new Time(7, 40, 0);
            times[1] = new Time(8, 10, 0);
        } else if ((now.after(first) && now.before(second)) || now.equals(second)) {
            times[0] = new Time(12, 15, 0);
            times[1] = new Time(20, 40, 0);
        } else {

        }
        return times;
    }

    public static void main(String[] args) {
        getBusTime();
    }
}
