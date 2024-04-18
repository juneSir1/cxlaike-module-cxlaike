package net.hzxzkj.cxlaike.module.cxlaike.dal.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.CLIENT_TOKEN_KEY;


@Repository
public class ClientTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get() {
        String redisKey = CLIENT_TOKEN_KEY.getKeyTemplate();
        return stringRedisTemplate.opsForValue().get(redisKey);
    }


    public void set(String token){
        String redisKey = CLIENT_TOKEN_KEY.getKeyTemplate();
        stringRedisTemplate.opsForValue().set(redisKey, token, 1, TimeUnit.HOURS);
    }

}
