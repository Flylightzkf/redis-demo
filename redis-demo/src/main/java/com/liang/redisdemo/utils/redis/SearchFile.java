package com.liang.redisdemo.utils.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SearchFile {
    public static void main(String[] args) {
        String searchStr = "http://ehall.njts.edu.cn"; // 指定要查找的字符串
        String dirPath = "D:\\苏迪科技相关下载\\logs"; // 指定要遍历的目录

        try {
            // 连接本地数据库
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdn", "root", "password");

            // 遍历目录下的所有文件
            File dir = new File(dirPath);
            File[] files = dir.listFiles();
            for (File file : files) {
                // 读取文件内容
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = "";
                int lineNumber = 0;
                while ((line = br.readLine()) != null) {
                    lineNumber++;
                    if (line.contains(searchStr)) {
                        // 找到了指定字符串，记录该字符串在文件中所在的行数
                        int prevLine = lineNumber - 1;
                        int nextLine = lineNumber + 3;
                        String prevStr = "", nextStr = "";
                        if (prevLine > 0) {
                            // 读取上一行内容
                            br.mark(1024);
                            for (int i = 0; i < prevLine - 1; i++) {
                                br.readLine();
                            }
                            prevStr = br.readLine();
                            br.reset();
                        }
                        if (nextLine > lineNumber) {
                            // 读取下一行内容
                            for (int i = 0; i < nextLine - lineNumber - 1; i++) {
                                br.readLine();
                            }
                            nextStr = br.readLine();
                        }

                        // 将记录存储到本地数据库的一个表下的三个字段中
                        PreparedStatement ps = conn.prepareStatement("INSERT INTO my_table (search_str, prev_str, next_str) VALUES (?, ?, ?)");
                        ps.setString(1, searchStr);
                        ps.setString(2, prevStr);
                        ps.setString(3, nextStr);
                        ps.execute();
                    }
                }
                br.close();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
