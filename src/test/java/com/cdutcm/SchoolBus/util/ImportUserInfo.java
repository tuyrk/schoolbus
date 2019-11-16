package com.cdutcm.SchoolBus.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ImportUserInfo {

    private boolean importUserInfo() {
        Connection connection = DBCPUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into userinfo (NAME, SEX, DEPARTMENT, NUMBER, PASSWORD, STATE) values (?,?,?,?,?,?);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String file = "2017秋在籍职工.xls";
        String[][] result = getData(file, 1);
        int rowLength = result.length;
        for (int i = 0; i < rowLength; i++) {
            try {
                String department = result[i][0];
                if (department.contains("/")) {
                    department = department.substring(0, department.indexOf('/'));//部门去第一个'/'前的名称
                }
                String password = result[i][1];//username
                if (password.length() > 6) {
                    password = password.substring(2);
                } else {//工号不足六位,则在前面加一个'0'
                    password = "0" + password;
                }
                preparedStatement.setString(1, result[i][2]);
                preparedStatement.setString(2, result[i][3]);
                preparedStatement.setString(3, department);
                preparedStatement.setString(4, result[i][1]);
                preparedStatement.setString(5, password);//后六位作为密码
                preparedStatement.setInt(6, 1);//可预订校车状态,1
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 读取Excel的内容,第一位数组存储的是一行中格列的值,二维数组存储的是多少各行
     *
     * @param file       读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数,比如行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     */
    private String[][] getData(String file, int ignoreRows) {
        String[][] returnArray = null;
        List<String[]> result = new ArrayList<>();
        int rowSize = 0;
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(input);
            //打开HSSFWorkbook
            POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            HSSFCell cell = null;
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                //第一行为标题,不取
                for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    HSSFRow row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    String[] values = new String[rowSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                        String value = "";
                        cell = row.getCell(columnIndex);
                        if (cell != null) {
                            //注意:一定要设置成这个,否则可能会出现乱码
                            //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                            switch (cell.getCellTypeEnum()) {
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        if (date != null) {
                                            value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                        }
                                    } else {
                                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                    }
                                    break;
                                case FORMULA:
                                    //导入时如果为公式生成的数据则无值
                                    if (!cell.getStringCellValue().equals("")) {
                                        value = cell.getStringCellValue();
                                    } else {
                                        value = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case BLANK:
                                    break;
                                case ERROR:
                                    break;
                                case BOOLEAN:
                                    value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                                    break;
                            }
                        }
                        if (columnIndex == 0 && value.trim().equals("")) {
                            break;
                        }
                        values[columnIndex] = rightTrim(value);
                        hasValue = true;
                    }
                    if (hasValue) {
                        result.add(values);
                    }
                }
            }
            inputStream.close();
            returnArray = new String[result.size()][rowSize];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = result.get(i);
            }
            return returnArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArray;
    }

    /**
     * 去掉字符串右边的空格
     *
     * @param string 要处理的字符串
     * @return 处理后的字符串
     */
    public String rightTrim(String string) {
        if (string == null) {
            return "";
        }
        int length = string.length();
        for (int i = length - 1; i >= 0; i--) {
            if (string.charAt(i) != ' ') {
                break;
            }
            length--;
        }
        return string.substring(0, length);
    }

    public static void main(String[] args) {
        ImportUserInfo userInfo = new ImportUserInfo();
        if (userInfo.importUserInfo()) {
            System.out.println("教师信息导入成功");
        }
    }
}
