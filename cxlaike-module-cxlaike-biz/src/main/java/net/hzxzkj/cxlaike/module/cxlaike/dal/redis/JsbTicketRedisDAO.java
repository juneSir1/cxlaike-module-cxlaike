package net.hzxzkj.cxlaike.module.cxlaike.dal.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.CLIENT_TOKEN_KEY;
import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.JSB_TICKET_KEY;


@Repository
public class JsbTicketRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get() {
        String redisKey = JSB_TICKET_KEY.getKeyTemplate();
        return stringRedisTemplate.opsForValue().get(redisKey);
    }


    public void set(String token,Long expiresIn){
        String redisKey = JSB_TICKET_KEY.getKeyTemplate();
        stringRedisTemplate.opsForValue().set(redisKey, token, expiresIn-10, TimeUnit.SECONDS);
    }

}
