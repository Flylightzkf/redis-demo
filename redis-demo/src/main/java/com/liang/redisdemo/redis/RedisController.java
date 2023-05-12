package com.liang.redisdemo.redis;

import com.liang.redisdemo.utils.ajax.AjaxResult;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存数据
     *
     * @param id
     * @param value
     * @return
     */
    @PostMapping("/add")
    public AjaxResult add(String id, String value) {
        redisTemplate.opsForValue().set(id, value);
        return AjaxResult.success();
    }

    /**
     * 保存一个时效性数据
     *
     * @param id
     * @param time
     */

    @PostMapping("/addExpirationData")
    public AjaxResult add(String id, Integer time) {
        redisTemplate.opsForValue().set(id, null, time, TimeUnit.SECONDS);
        return AjaxResult.success();
    }

    /**
     * 查询数据
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public AjaxResult get(String id) {
        return AjaxResult.success(redisTemplate.opsForValue().get(id));
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public AjaxResult delete(String id) {
        return AjaxResult.success(redisTemplate.delete(id));
    }


}
