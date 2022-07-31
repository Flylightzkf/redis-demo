package com.liang.redisdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.redisdemo.mapper.UserMapper;
import com.liang.redisdemo.model.UserModel;
import com.liang.redisdemo.service.UserServer;
import org.springframework.stereotype.Service;

/**
 * @author LiangErLe
 * @Date 2022/7/31 16:13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserServer {
}
