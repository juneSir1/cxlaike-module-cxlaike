package net.hzxzkj.cxlaike.module.cxlaike.dal;

import net.hzxzkj.cxlaike.framework.redis.core.RedisKeyDefine;
import org.redisson.api.RLock;

import java.time.Duration;

import static net.hzxzkj.cxlaike.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author 宵征源码
 */
public interface RedisKeyConstants {

    RedisKeyDefine PROFESSION = new RedisKeyDefine("行业的缓存",
            "cxlaike_profession:",
            STRING, String.class, Duration.ofDays(30));

    RedisKeyDefine REFRESH_TOKEN_USERS = new RedisKeyDefine("抖音刷新token的缓存",
            "refresh_token_users:%d",
            STRING, String.class, Duration.ofDays(365));

    RedisKeyDefine ACCESS_TOKEN_USERS = new RedisKeyDefine("抖音access_token的缓存",
            "access_token_users:%d",
            STRING, String.class, Duration.ofDays(365));

    RedisKeyDefine TRANSFER_MONEY_NO = new RedisKeyDefine("转账批次号",
            "transfer_money_no:",
            STRING, String.class, Duration.ofDays(365));

    RedisKeyDefine APPLET_TOKEN_KEY = new RedisKeyDefine("小程序tokenKey",
            "applet_key:",
            STRING, String.class, Duration.ofSeconds(1));

    RedisKeyDefine CLIENT_TOKEN_KEY = new RedisKeyDefine("client_token",
            "client_token_key:",
            STRING, String.class, Duration.ofHours(1));

    RedisKeyDefine JSB_TICKET_KEY = new RedisKeyDefine("jsb_ticket",
            "jsb_ticket_key:",
            STRING, String.class, Duration.ofHours(1));

    RedisKeyDefine OPEN_TICKET_KEY = new RedisKeyDefine("open_ticket",
            "open_ticket_key:",
            STRING, String.class, Duration.ofHours(1));
}
