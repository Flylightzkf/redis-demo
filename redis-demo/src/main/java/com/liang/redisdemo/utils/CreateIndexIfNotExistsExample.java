package com.liang.redisdemo.utils;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class CreateIndexIfNotExistsExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://170.18.10.97:3306/IDSPLUS_RWANG?characterEncoding=UTF-8";
        String user = "root";
        String password = "12344";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // 判断索引是否存在
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getIndexInfo(null, null, "T_USER", false, false);
            boolean indexExists = false;
            while (rs.next()) {
                if (rs.getString("COLUMN_NAME").equals("Auxid")
                        && rs.getString("INDEX_NAME").equals("Auxid_index")) {
                    indexExists = true;
                    break;
                }
            }

            // 如果索引不存在则创建索引
            if (!indexExists) {
                String indexQuery = "CREATE INDEX Auxid_index ON T_USER (Auxid)";
                stmt.execute(indexQuery);
                System.out.println("索引创建成功");
            } else {
                System.out.println("索引已存在");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}