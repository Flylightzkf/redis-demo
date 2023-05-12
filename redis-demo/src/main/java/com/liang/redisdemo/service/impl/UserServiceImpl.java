package com.liang.redisdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.liang.redisdemo.mapper.UserMapper;
import com.liang.redisdemo.model.BaseRequest;
import com.liang.redisdemo.model.UserModel;
import com.liang.redisdemo.service.UserServer;
import com.liang.redisdemo.utils.Md5PassWord;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserServer {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @SneakyThrows
    @Override
    public boolean save(UserModel userModel) {
        userModel.setPassword(Md5PassWord.md5(userModel.getPassword()));
        return userMapper.save(userModel);
    }

    @Override
    public UserModel getByNameOrSort(String name,String sort) {
        return userMapper.getByNameOrSort(name,sort);
    }



    @Override
    public Object findById(String id) {
        //先去缓存中查
        Object userOj = redisTemplate.opsForValue().get(id);
        //如果不存在，去sql中查询
        if (userOj == null) {
            Object userModel = userMapper.getById(id);
            redisTemplate.opsForValue().set(id, userModel);
            return userModel;
        }
        return userOj;
    }


    @Override
    @Cacheable(key = "#id")
    public UserModel findById1(String id) {
        return userMapper.getById(id);
    }

    @Override
    @Cacheable(value = "redis-user", key = "#id", condition = "#id != null")
    public UserModel findById2(String id) {
        return userMapper.getById(id);
    }

    @Override
    @CacheEvict(value = "redis-user", key = "#userModel.getId()")
    public void update(UserModel userModel) {
        userMapper.updateById(userModel);
    }

    @Override
    @CacheEvict(value = "redis-user", allEntries = true)
    public void update1(UserModel userModel) {
        userMapper.updateById(userModel);
        redisTemplate.delete(userModel.getId());
        System.out.println("测试");
    }

    @Override
    @CacheEvict(value = "redis-user", allEntries = true, beforeInvocation = false)
    public void update2(UserModel userModel) {
        userMapper.updateById(userModel);
        System.out.println("测试");
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }


    @Override
    public PageInfo<UserModel> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<UserModel> UserModels = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(UserModels);
    }
}
