package com.liang.redisdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.redisdemo.model.UserModel;

/**
 * @author LiangErLe
 * @Date 2022/7/31 16:12
 */
public interface UserServer extends IService<UserModel> {
    Object findById(String id);

    UserModel findById1(String id);

    UserModel findById2(String id);

    void update(UserModel userModel);
    void update1(UserModel userModel);
    void update2(UserModel userModel);
}
