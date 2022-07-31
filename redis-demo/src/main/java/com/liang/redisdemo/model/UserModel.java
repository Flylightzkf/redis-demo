package com.liang.redisdemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiangErLe
 * @Date 2022/7/31 16:14
 */
@Data
@TableName("user")
public class UserModel {
    private String id;
    private String name;
    private String age;
    private String sex;
}
