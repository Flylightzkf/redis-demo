package com.liang.redisdemo.redis;

import com.liang.redisdemo.model.UserModel;
import com.liang.redisdemo.service.UserServer;
import com.liang.redisdemo.utils.ajax.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangErLe
 * @Date 2022/7/31 15:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserServer userServer;

    @GetMapping("/findById")
    public AjaxResult findById(String id) {
        return AjaxResult.success(userServer.getById(id));
    }

    /**
     * 使用注解查询缓存
     * 如果缓存中没有数据，查询数据库
     *
     * @param id
     * @return
     */
    @GetMapping("/findById1")
    @Cacheable(value = "user", key = "#id")
    public AjaxResult findById1(String id) {
        return AjaxResult.success(userServer.getById(id));
    }

    /**
     * 使用注解查询缓存
     * 如果缓存中没有数据，并把返回值添加到缓存中
     *
     * @param id
     * @return
     */
    @GetMapping("/findById2")
    public AjaxResult findById2(String id) {
        return AjaxResult.success(userServer.findById2(id));
    }
}
