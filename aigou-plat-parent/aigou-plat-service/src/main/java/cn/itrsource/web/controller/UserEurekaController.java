package cn.itrsource.web.controller;


import cn.itsource.domain.User;
import cn.itsource.util.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEurekaController {
 /*   @PostMapping("/login")   //@RequestBody接收json格式的数据
    public AjaxResult login( String username, String password){
        if("admin".equals(username)&&"admin".equals(password)){
            return AjaxResult.getAjax().setMessage("登陆成功").setSuccess(true);
        }
        return  AjaxResult.getAjax().setSuccess(false).setMessage("登陆失败请重试！！");
    }*/
    @PostMapping("/login")   //@RequestBody接收json格式的数据
    public AjaxResult login(@RequestBody User user){
        String username=user.getUsername();
        String password=user.getPassword();
        if("admin".equals(username)&&"admin".equals(password)){
            return AjaxResult.getAjax().setMessage("登陆成功").setSuccess(true).setRestObj(user);
        }
        return  AjaxResult.getAjax().setSuccess(false).setMessage("登陆失败请重试！！");
    }
}
