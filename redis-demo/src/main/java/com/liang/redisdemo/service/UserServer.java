package com.liang.redisdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.liang.redisdemo.model.BaseRequest;
import com.liang.redisdemo.model.UserModel;


public interface UserServer extends IService<UserModel> {
    Object findById(String id);

    UserModel getByNameOrSort(String name,String sort);

    UserModel findById1(String id);

    UserModel findById2(String id);

    boolean save(UserModel userModel);

    PageInfo<UserModel> page(BaseRequest baseRequest);

    void update(UserModel userModel);
    void update1(UserModel userModel);
    void update2(UserModel userModel);


    void deleteById(String id);
}
