package com.liang.redisdemo.utils.redis;

import java.io.*;

public class SearchString {

    public static void main(String[] args) {
        String folderPath = "/path/to/folder"; // 遍历的文件夹路径
        String keyword = "example"; // 要匹配的关键字
        File folder = new File(folderPath); // 创建一个File实例表示文件夹
        File[] files = folder.listFiles(); // 获取文件夹下所有文件
        for (File file : files) {
            if (file.isFile()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    int lineNumber = 0; // 行号
                    while ((line = br.readLine()) != null) {
                        lineNumber++;
                        if (line.contains(keyword)) {
                            // 匹配成功，获取上一行和下三行的内容
                            StringBuilder sb = new StringBuilder();
                            for (int i = lineNumber - 1; i <= lineNumber + 3; i++) {
                                if (i > 0) {
                                    String nextLine = br.readLine();
                                    if (nextLine != null) {
                                        sb.append(nextLine).append("\n");
                                    } else {
                                        break;
                                    }
                                }
                            }
                            // 将匹配到的内容保存到数据库
                            saveToDatabase(file.getPath(), sb.toString(), lineNumber);
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void saveToDatabase(String filePath, String content, int lineNumber) {
        // 保存到数据库的逻辑
        // ...
    }

}
