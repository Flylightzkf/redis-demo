package com.liang.redisdemo.utils;

/**
 * @author kfz
 * @create 2023-02-23 15:53
 */
public class tryCatch {
    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            try {
                int i= 1/0;
                System.out.println("i"+i);
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println(111+j);
        }

    }
}
