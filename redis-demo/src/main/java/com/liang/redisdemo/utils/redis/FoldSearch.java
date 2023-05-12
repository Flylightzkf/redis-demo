package com.liang.redisdemo.utils.redis;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FoldSearch {
    public static void main(String[] args) throws IOException {
        // 目标文件夹路径
        String folderPath = "D:\\苏迪科技相关下载\\logs";
        // 目标字符串
        String targetStr = "http://ehall.njts.edu.cn";
        // 输出文件路径
        String outputPath = "D:\\苏迪科技相关下载\\a.txt";

        File folder = new File(folderPath);
        // 创建输出文件
        File outputFile = new File(outputPath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(outputFile, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // 遍历文件夹下的所有文件和子文件夹
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                // 如果是文件，则在文件中查找目标字符串，并输出上下文到输出文件
                searchInFile(file, targetStr, bufferedWriter);
            } else {
                // 如果是文件夹，则递归遍历子文件夹
                searchInFolder(file, targetStr, bufferedWriter);
            }
        }

        bufferedWriter.close();
        fileWriter.close();
    }

    // 在文件中查找目标字符串，并输出上下文到输出文件
    private static void searchInFile(File file, String targetStr, BufferedWriter bufferedWriter) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int lineCount = 0;
        List<String> lines = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            lineCount++;
            // 判断是否包含目标字符串，如果有，缓存该行及周围行
            if (line.contains(targetStr)) {
                lines.add(getLine(file, lineCount - 1));
                lines.add(line);
                for (int i = 1; i <= 3 && (line = bufferedReader.readLine()) != null; i++) {
                    lineCount++;
                    lines.add(line);
                }
            }
        }

        // 输出缓存的所有行到输出文件
        for (String l : lines) {
            bufferedWriter.write("文件名：" + file.getName() + "，" + l + "\n");
        }
        if (!lines.isEmpty()) {
            bufferedWriter.write("\n");
        }

        bufferedReader.close();
        fileReader.close();
    }

    // 递归遍历文件夹下的所有文件和子文件夹
    private static void searchInFolder(File folder, String targetStr, BufferedWriter bufferedWriter) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                searchInFile(file, targetStr, bufferedWriter);
            } else {
                searchInFolder(file, targetStr, bufferedWriter);
            }
        }
    }

    // 获取文件中指定行的内容
    private static String getLine(File file, int lineNumber) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int lineCount = 0;
        while ((line = bufferedReader.readLine()) != null) {
            lineCount++;
            if (lineCount == lineNumber) {
                bufferedReader.close();
                fileReader.close();
                return "行号：" + lineNumber + "，" + line;
            }
        }

        bufferedReader.close();
        fileReader.close();
        return null;
    }
}