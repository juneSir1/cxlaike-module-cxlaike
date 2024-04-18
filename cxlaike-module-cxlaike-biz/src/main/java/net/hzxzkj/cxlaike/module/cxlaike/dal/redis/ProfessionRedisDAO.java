package net.hzxzkj.cxlaike.module.cxlaike.dal.redis;

import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo.ProfessionRespVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.PROFESSION;


@Repository
public class ProfessionRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public List<ProfessionRespVO> get() {
        String redisKey = PROFESSION.getKeyTemplate();
        return JsonUtils.parseArray(stringRedisTemplate.opsForValue().get(redisKey), ProfessionRespVO.class);
    }

    public void set(List<ProfessionRespVO> professionRespVO) {
        String redisKey = PROFESSION.getKeyTemplate();
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(professionRespVO), 30, TimeUnit.DAYS);
    }


//    private static String formatKey() {
//        return String.format(PROFESSION.getKeyTemplate());
//    }

}
