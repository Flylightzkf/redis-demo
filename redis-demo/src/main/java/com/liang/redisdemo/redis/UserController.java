package com.liang.redisdemo.redis;

import com.liang.redisdemo.model.UserModel;
import com.liang.redisdemo.service.UserServer;
import com.liang.redisdemo.utils.ajax.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiangErLe
 * @Date 2022/7/31 15:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    /**
     * 把数存放在缓存里面
     * 查询数据的时候先去判断缓存中是否存在数据
     * 手动去存储
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public AjaxResult findById(String id) {
        return AjaxResult.success(userServer.findById(id));
    }

    /**
     * 使用注解查询缓存
     * 如果缓存中没有数据，查询数据库
     *
     * @param id
     * @return
     */
    @GetMapping("/findById1")
    public AjaxResult findById1(String id) {
        return AjaxResult.success(userServer.findById1(id));
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

    /**
     * 在数据发生修改时
     *
     * @param userModel
     * @return
     */
    @PutMapping("/update")
    public AjaxResult update(UserModel userModel) {
        userServer.update(userModel);
        return AjaxResult.success();
    }

    @PutMapping("/update1")
    public AjaxResult update1(UserModel userModel) {
        userServer.update1(userModel);
        return AjaxResult.success();
    }
    @PutMapping("/update2")
    public AjaxResult update2(UserModel userModel) {
        userServer.update2(userModel);
        return AjaxResult.success();
    }

    public AjaxResult add(){
        return AjaxResult.success();
    }


}
