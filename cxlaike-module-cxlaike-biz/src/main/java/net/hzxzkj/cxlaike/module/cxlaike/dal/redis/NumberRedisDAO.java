package net.hzxzkj.cxlaike.module.cxlaike.dal.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class NumberRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public Long incr(String key){
        return stringRedisTemplate.opsForValue().increment(key);
    }

}
