package net.hzxzkj.cxlaike.module.cxlaike.redis.dypersonalletteruser;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static net.hzxzkj.cxlaike.framework.common.exception.enums.GlobalErrorCodeConstants.LOCKED;
import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.redis.RedisKeyConstants.LETTER_USER_LOCK;


/**
 * 支付通知的锁 Redis DAO
 *
 * @author 宵征源码
 */
@Repository
public class LetterUserLockRedisDAO {

    @Resource
    private RedissonClient redissonClient;

    public void lock(String name,Runnable runnable) {
        RLock lock = redissonClient.getLock(formatKey(name));
        try {
            if(!lock.tryLock( 10, 10, TimeUnit.SECONDS)){
                throw exception(LOCKED);
            }
            runnable.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    private static String formatKey(String name) {
        return String.format(LETTER_USER_LOCK.getKeyTemplate(), name);
    }

}
