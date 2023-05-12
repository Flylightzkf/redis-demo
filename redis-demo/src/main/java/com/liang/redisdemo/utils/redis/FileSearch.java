package com.liang.redisdemo.utils.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSearch {
    public static void main(String[] args) {
        String targetString = "http://ehall.njts.edu.cn"; // 目标字符串
        File directory = new File("D:\\苏迪科技相关下载\\logs"); // 文件夹路径

        // 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/csdn";
        String username = "root";
        String password = "password";

        try {
            // 创建数据库连接
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            // 遍历文件夹下的文件
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    int lineNumber = 0;
                    List<String> list=new ArrayList<>();

                    while ((line = reader.readLine()) != null) {
                        lineNumber++;
                        list.add(line);
//                        if (line.contains(targetString)) {
//                            // 存储到数据库
//                            String pre=list.get(lineNumber-1);
//                            String next=getNextLine(reader, lineNumber + 1);
//                            stmt.executeUpdate(String.format("INSERT INTO my_table (file_name, line_number, line_content, prev_line, next_line) " +
//                                            "VALUES ('%s', %d, '%s', '%s', '%s')", file.getName(), lineNumber, line,
//                                    getPrevLine(reader, lineNumber - 1), getNextLine(reader, lineNumber + 1)));
//                        }
                    }
                    for(int i = 0;i<list.size();i++){         //同for(int i = 0;i<list.size();i++)
                       // System.out.println(str);
                        if(list.get(i).contains(targetString)){
                            String pre=list.get(i-1);
                            String cur=list.get(i);
                            String next=list.get(i+3);
                            stmt.executeUpdate(String.format("INSERT INTO my_table (file_name, line_number, line_content, prev_line, next_line) " +
                                           "VALUES ('%s', %d, '%s', '%s', '%s')", file.getName(), i, cur,
                                    pre, next));
                        }
                    }
                    reader.close();
                }
            }

            // 关闭数据库连接
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取上一行
    private static String getPrevLine(BufferedReader reader, int lineNumber) throws Exception {
        for (int i = 0; i < lineNumber; i++) {
            reader.readLine();
        }
        return reader.readLine();
    }

    // 获取下第三行
    private static String getNextLine(BufferedReader reader, int lineNumber) throws Exception {
        for (int i = 0; i < lineNumber; i++) {
            reader.readLine();
        }
        String nextLine = reader.readLine();
        if (nextLine == null) {
            return "";
        }
        return nextLine;
    }
}