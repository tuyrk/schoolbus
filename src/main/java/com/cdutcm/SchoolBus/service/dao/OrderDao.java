package com.cdutcm.SchoolBus.service.dao;

import com.cdutcm.SchoolBus.model.Bus;
import com.cdutcm.SchoolBus.model.Order;
import com.cdutcm.SchoolBus.util.BusTimeUtil;
import com.cdutcm.SchoolBus.util.DBCPUtil;

import java.sql.*;
import java.util.Calendar;

public class OrderDao {
    public static Bus[] busStatus(String username) {
        Bus[] buses = new Bus[20];
        for (int i = 0; i < buses.length; i++) {
            buses[i] = new Bus();
        }
        //获得当天年月日,并且月日以两位数显示
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        int m = now.get(Calendar.MONTH);
        String month = m < 10 ? "0" + m : "" + m;
        int d = now.get(Calendar.DAY_OF_MONTH);
        String day = d < 10 ? "0" + d : "" + d;

        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //查找当前用户的预约状态 STATE = 0为不可预约,STATE = 1为可预约
            preparedStatement = connection.prepareStatement("SELECT STATE FROM userinfo WHERE NUMBER = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            int state = 0;
            if (resultSet.next()) {
                state = resultSet.getInt("STATE");
            }

            //查找各个车辆的总座位数,已预约座位数
            //SELECT RESERVATION FROM busstatus
            Time[] times = BusTimeUtil.getBusTime();
            preparedStatement = connection.prepareStatement("SELECT * FROM busstatus WHERE TIME>=? AND TIME <=?");
            preparedStatement.setTime(1, times[0]);
            preparedStatement.setTime(2, times[1]);
            resultSet = preparedStatement.executeQuery();
            int index = 0;
            while (resultSet.next()) {
                buses[index].setId(resultSet.getInt("ID"));
                buses[index].setStart(resultSet.getString("START"));
                buses[index].setEnd(resultSet.getString("END"));
                int RESERVATION = resultSet.getInt("RESERVATION");
                int TOTAL = resultSet.getInt("TOTAL");
                int surplus = TOTAL - RESERVATION;
                buses[index].setReservation(RESERVATION);
                buses[index].setTotal(TOTAL);
                buses[index].setSurplus(surplus);
                if (state == 0 || surplus == 0) {
                    buses[index].setState("不可预约");
                } else {
                    buses[index].setState("可预约");
                }
                buses[index].setTime(year + "-" + month + "-" + day + " " + resultSet.getString("TIME").substring(0, 5));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return buses;
    }

    /**
     * 提交,现在预约
     *
     * @param id
     * @param number
     * @return
     */
    public static boolean bespeak(int id, String number) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //region 获得当天年月日,并且月日以两位数显示
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        int m = now.get(Calendar.MONTH);
        String month = m < 10 ? "0" + m : "" + m;
        int d = now.get(Calendar.DAY_OF_MONTH);
        String day = d < 10 ? "0" + d : "" + d;
        //endregion
        try {
            //region 根据id获得start,end,time
            String start = null;
            String end = null;
            String time = null;
            preparedStatement = connection.prepareStatement("SELECT START, END, TIME FROM busstatus WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                start = resultSet.getString("START");
                end = resultSet.getString("END");
                time = year + "-" + month + "-" + day + " " + resultSet.getString("TIME").substring(0, 5);
            }
            //endregion

            //添加订单记录
            preparedStatement = connection.prepareStatement("insert into orderinfo (NUMBER, START, END, TIME, STATE, BUS_ID, FLAG) values (?, ?, ?, ?, '预约成功', ?, 1);");//FLAG = 1 表示可以显示在历史订单里
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, start);
            preparedStatement.setString(3, end);
            preparedStatement.setString(4, time);
            preparedStatement.setInt(5, id);
            int result1 = preparedStatement.executeUpdate();
            //设置当前用户为已预约状态
            preparedStatement = connection.prepareStatement("update userinfo set STATE = 0 where NUMBER = ? LIMIT 1;");
            preparedStatement.setString(1, number);
            int result2 = preparedStatement.executeUpdate();
            //设置当前预约车辆已预约座位数+1
            preparedStatement = connection.prepareStatement("update busstatus set RESERVATION = RESERVATION + 1  where ID = ? LIMIT 1;");
            preparedStatement.setInt(1, id);
            int result3 = preparedStatement.executeUpdate();
            connection.commit();
            if (result1 == 1 && result2 == 1 && result3 == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 当前订单信息
     *
     * @param username
     * @return
     */
    public static Order nowOrder(String username) {
        Order order = new Order();
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM orderinfo WHERE NUMBER = ? AND STATE = '预约成功';");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getInt("ID"));
                order.setStart(resultSet.getString("START"));
                order.setEnd(resultSet.getString("END"));
                order.setTime(resultSet.getString("TIME"));
                order.setBusId(resultSet.getInt("BUS_ID"));
            } else {
                order.setState("未预约");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    public static Order[] historyOrder(String username) {
        Order[] orders = new Order[2];
        for (int i = 0; i < orders.length; i++) {
            orders[i] = new Order();
        }
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM orderinfo WHERE NUMBER = ? AND STATE LIKE '已%' AND FLAG = 1 ORDER BY ID DESC LIMIT 2;");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            int index = 0;
            while (resultSet.next()) {
                orders[index].setId(resultSet.getInt("ID"));
                orders[index].setStart(resultSet.getString("START"));
                orders[index].setEnd(resultSet.getString("END"));
                orders[index].setTime(resultSet.getString("TIME"));
                orders[index].setState(resultSet.getString("STATE"));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * 取消订单
     *
     * @param username 用户名
     * @param id       取消的订单是哪一班车
     * @return
     */
    public static boolean cancelOrder(String username, String id,String busId) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE orderinfo SET STATE = '已取消' WHERE ID = ? LIMIT 1;");
            preparedStatement.setInt(1, Integer.parseInt(id));
            int result1 = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("update userinfo set STATE = 1 where NUMBER = ? LIMIT 1;");
            preparedStatement.setString(1, username);
            int result2 = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("update busstatus set RESERVATION = RESERVATION - 1  where ID = ? LIMIT 1;");
            preparedStatement.setInt(1, Integer.parseInt(busId));
            int result3 = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("UPDATE orderinfo INNER JOIN (SELECT ID AS i FROM orderinfo WHERE NUMBER = ? AND STATE LIKE '已%' ORDER BY ID desc LIMIT 2,1) AS p INNER JOIN (SELECT count(ID) AS c FROM orderinfo WHERE NUMBER = ? AND STATE LIKE '已%') AS q SET FLAG = 0 WHERE ID = i AND c > 2;");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            connection.commit();
            if (result1 >= 1 && result2 >= 1 && result3 >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean deleteOrder(String id) {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update orderinfo set FLAG = 0 where ID = ?;");
            preparedStatement.setInt(1, Integer.parseInt(id));
            int result = preparedStatement.executeUpdate();
            connection.commit();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        busStatus("20161066");
    }
}
/*
设置为0
UPDATE orderinfo INNER JOIN (SELECT ID AS i FROM orderinfo WHERE NUMBER = 20161066 AND STATE LIKE '已%' ORDER BY ID desc LIMIT 2,1) AS p INNER JOIN (SELECT count(ID) AS c FROM orderinfo WHERE NUMBER = 20161066 AND STATE LIKE '已%') AS q SET FLAG = 0 WHERE ID = i AND c > 2;
 */