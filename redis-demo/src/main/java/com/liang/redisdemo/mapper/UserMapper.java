package com.liang.redisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.redisdemo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LiangErLe
 * @Date  2022/8/20 16:13
 */
@Mapper
public interface UserMapper extends BaseMapper<UserModel> {
}
