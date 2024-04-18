package net.hzxzkj.cxlaike.module.cxlaike.redis;

import java.time.Duration;
import net.hzxzkj.cxlaike.framework.redis.core.RedisKeyDefine;
import org.redisson.api.RLock;

/**
 * Redis Key 枚举类
 *
 * @author 宵征源码
 */
public interface RedisKeyConstants {

    RedisKeyDefine ORDER_LOCK = new RedisKeyDefine("接单的分布式锁",
            "order:lock:%s", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.HASH, RLock.class,  Duration.ofDays(30)); // Redisson 的 Lock 锁，使用 Hash 数据结构

    RedisKeyDefine LETTER_USER_LOCK = new RedisKeyDefine("抖音私信的分布式锁",
            "letter:user:lock:%s", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.HASH, RLock.class,  Duration.ofDays(30)); // Redisson 的 Lock 锁，使用 Hash 数据结构

}
