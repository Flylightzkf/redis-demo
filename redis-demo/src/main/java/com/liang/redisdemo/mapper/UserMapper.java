package com.liang.redisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.redisdemo.model.BaseRequest;
import com.liang.redisdemo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserModel> {
    boolean save(UserModel userModel);

    UserModel getByNameOrSort(String name,String sort);

    void deleteById(Integer id);

    List<UserModel> listByCondition(BaseRequest baseRequest);

    int updateById(UserModel userModel);

    UserModel getById(String id);
}
