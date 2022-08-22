package com.liang.redisdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.redisdemo.mapper.UserMapper;
import com.liang.redisdemo.model.UserModel;
import com.liang.redisdemo.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author LiangErLe
 * @Date 2022/8/20 16:13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserServer {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object findById(String id) {
        //先去缓存中查
        Object userOj = redisTemplate.opsForValue().get(id);
        //如果不存在，去sql中查询
        if (userOj == null) {
            Object userModel = getById(id);
            redisTemplate.opsForValue().set(id, userModel);
            return userModel;
        }
        return userOj;
    }


    @Override
    @Cacheable(key = "#id")
    public UserModel findById1(String id) {
        return getById(id);
    }

    @Override
    @Cacheable(value = "redis-user", key = "#id", condition = "#id != null")
    public UserModel findById2(String id) {
        return getById(id);
    }

    @Override
    @CacheEvict(value = "redis-user", key = "#userModel.getId()")
    public void update(UserModel userModel) {

    }

    @Override
    @CacheEvict(value = "redis-user", allEntries = true)
    public void update1(UserModel userModel) {
        System.out.println("测试");

    }

    @Override
    @CacheEvict(value = "redis-user", allEntries = true, beforeInvocation = false)
    public void update2(UserModel userModel) {
        System.out.println("测试");
    }
}
