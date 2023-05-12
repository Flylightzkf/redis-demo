package com.liang.redisdemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends BaseRequest{
    private String id;
    private String name;
    private String code;
    private String age;
    private String createTime;
    private String password;
    private String sort;
}
