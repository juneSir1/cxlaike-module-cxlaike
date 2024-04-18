package net.hzxzkj.cxlaike.module.cxlaike.dal.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.APPLET_TOKEN_KEY;


@Repository
public class AppletTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get() {
        String redisKey = APPLET_TOKEN_KEY.getKeyTemplate();
        return stringRedisTemplate.opsForValue().get(redisKey);
    }


    public void set(String token,Long expiresIn){
        String redisKey = APPLET_TOKEN_KEY.getKeyTemplate();
        stringRedisTemplate.opsForValue().set(redisKey, token, expiresIn, TimeUnit.SECONDS);
    }

}
