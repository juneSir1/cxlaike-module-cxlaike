package net.hzxzkj.cxlaike.module.cxlaike.redis.order;

import static net.hzxzkj.cxlaike.framework.common.exception.enums.GlobalErrorCodeConstants.LOCKED;
import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import net.hzxzkj.cxlaike.module.cxlaike.redis.RedisKeyConstants;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;


/**
 * 支付通知的锁 Redis DAO
 *
 * @author 宵征源码
 */
@Repository
public class OrderLockRedisDAO {

  @Resource
  private RedissonClient redissonClient;

  public void lock(Long id, Runnable runnable) {
    RLock lock = redissonClient.getLock(formatKey(id));
    try {
      if (!lock.tryLock(10, 10, TimeUnit.SECONDS)) {
        throw exception(LOCKED);
      }
      runnable.run();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }

  private static String formatKey(Long id) {
    return String.format(RedisKeyConstants.ORDER_LOCK.getKeyTemplate(), id);
  }

}
