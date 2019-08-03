package cn.itsource.commons.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.basic.util.RedisUtils;
import cn.itsource.client.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient{

    @PostMapping("/redis")
    public AjaxResult setRedis(@RequestParam("key")String key,@RequestParam("value")String value){
        try {
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.getAjax().setSuccess(true).setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjax().setSuccess(false).setMessage("操作失败"+e.getMessage());
        }
    }

    @GetMapping("/redis")
    public AjaxResult getRedis(@RequestParam("key")String key){
        try {
            String s = RedisUtils.INSTANCE.get(key);
            return AjaxResult.getAjax().setSuccess(true).setMessage("操作成功").setRestObj(s);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjax().setSuccess(false).setMessage("操作失败"+e.getMessage());
        }
    }
}
