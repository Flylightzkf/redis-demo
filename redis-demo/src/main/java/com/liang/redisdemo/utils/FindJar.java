package com.liang.redisdemo.utils;

import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author kfz
 * @create 2023-02-14 10:23
 */
public class FindJar {
    public static void main(String[] args) throws Exception {


        //C:\\Users\\Administrator\\Desktop\\mids_jar	ids1.2	ids2.1
//        String path = "C:\\Users\\Administrator\\Desktop\\任务\\南外\\lib";

        //String path = "E:\\git_project\\02src\\lib2";
        //C:\Users\kfZ\Desktop\lib
        //D:\SDNJ\ids-imp\02src\imp\target\ids-imp-2.0\WEB-INF\lib
        String path = "C:\\Users\\kfZ\\Desktop\\123232323232343434\\lib";
        System.out.println("---");
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            String name = file.getName();
            if (name.endsWith(".jar")) {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> jarEntryList = jarFile.entries();
                while (jarEntryList.hasMoreElements()) {
                    JarEntry jarEntry = jarEntryList.nextElement();
                    String jarName = jarEntry.getName().replace("/", ".");
                    if (jarName.endsWith(".class")) {
                        if (jarName.contains("com.sudytech.ids.data.sync.impl.ids.IdsTarDataHandler_Jdbc_BugRepair")) {
                            System.out.println(name);
                            System.out.println(jarName);
                        }
                    }
                }
            }
        }

    }
}
