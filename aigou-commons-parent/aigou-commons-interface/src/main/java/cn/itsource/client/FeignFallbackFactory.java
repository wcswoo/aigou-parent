package cn.itsource.client;

import cn.itsource.basic.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignFallbackFactory implements FallbackFactory<RedisClient>{
    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public AjaxResult setRedis(String key, String value) {
                return  AjaxResult.getAjax().setSuccess(true).setMessage("网络繁忙");
            }

            @Override
            public AjaxResult getRedis(String key) {
                return AjaxResult.getAjax().setSuccess(true).setMessage("网络繁忙");
            }
        };
    }
}
