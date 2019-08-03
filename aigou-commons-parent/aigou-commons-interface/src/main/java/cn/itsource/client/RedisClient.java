package cn.itsource.client;

import cn.itsource.basic.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "commons-service",fallback = FeignFallbackFactory.class)
public interface RedisClient {
    @PostMapping("/redis")
    public AjaxResult setRedis(@RequestParam("key")String key, @RequestParam("value")String value);

    @GetMapping("/redis")
    public AjaxResult getRedis(@RequestParam("key")String key);
}
