package com.liang.redisdemo.redis;

import com.liang.redisdemo.service.UserServer;
import com.liang.redisdemo.utils.ajax.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
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
}
