package com.liang.redisdemo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author kfz
 * @create 2023-01-16 14:23
 */
public class Md5PassWord {

        public static String md5(String str) throws Exception {
            String algorithm = "MD5";
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes("UTF-8"));
            byte[]  r = md.digest();
            BigInteger a = new BigInteger(1,r);
            //转化为16进制
            System.out.println(String.format("%032x",a));
            return String.format("%032x",a);
    }
}
