package net.hzxzkj.cxlaike.module.cxlaike.dal.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class DyTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;



    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public Long incr(String key){
        return stringRedisTemplate.opsForValue().increment(key);
    }

}
