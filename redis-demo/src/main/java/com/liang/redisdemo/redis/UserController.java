package com.liang.redisdemo.redis;

import com.liang.redisdemo.model.UserModel;
import com.liang.redisdemo.service.UserServer;
import com.liang.redisdemo.utils.NumberPatter;
import com.liang.redisdemo.utils.ajax.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @PostMapping("/add")
    public AjaxResult add(@RequestBody UserModel userModel) {

        String agestr=userModel.getAge();
        if(NumberPatter.isNumeric(agestr)){
           if(Integer.valueOf(agestr)>0&&Integer.valueOf(agestr)<150){
                UserModel user= userServer.getByNameOrSort(userModel.getName(),userModel.getSort());
                if(user!=null){
                       return AjaxResult.error("name和sort存在重复，请检查数据！");
                }else{
                    if(userServer.save(userModel)){
                        return AjaxResult.success();
                    }else{
                        return AjaxResult.error("保存数据错误！");
                    }
                }
           }else{
               return AjaxResult.error("请输入0-150之间的年龄区间！");
           }
        }else{
            return AjaxResult.error("请输入数字格式的年龄！");
        }
    }

    @GetMapping("/page")
    public AjaxResult page(UserModel userModel) {
        return AjaxResult.success(userServer.page(userModel));
    }




    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable String id) {
        userServer.deleteById(id);
        return AjaxResult.success("删除数据成功!");
    }



    /**
     * 把数存放在缓存里面
     * 查询数据的时候先去判断缓存中是否存在数据
     *
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
     * 根据置顶key删除缓存数据
     *
     * @param userModel
     * @return
     */
    @PutMapping("/update")
    public AjaxResult update(@RequestBody UserModel userModel) {
        userServer.update1(userModel);
        return AjaxResult.success();
    }

    /**
     * 删除全部缓存
     *
     * @param userModel
     * @return
     */
    @PutMapping("/update1")
    public AjaxResult update1(@RequestBody UserModel userModel) {
        userServer.update1(userModel);
        return AjaxResult.success();
    }

    /**
     * 在方法执行前和执行后执行删除缓存
     *
     * @param userModel
     * @return
     */
    @PutMapping("/update2")
    public AjaxResult update2(@RequestBody UserModel userModel) {
        userServer.update2(userModel);
        return AjaxResult.success();
    }



}
