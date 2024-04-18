package net.hzxzkj.cxlaike.module.cxlaike.utils;


import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants;
import net.hzxzkj.cxlaike.module.cxlaike.dal.redis.NumberRedisDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 编号生成工具类
 */
@Slf4j
@Component
public class NumbersUtil{

    private final String FINANCE_TRANSFER_MONEY = "Z";//转账批次号

    @Resource
    private NumberRedisDAO numberRedisDAO;

    /**
     * 转账批次号：
     * T+2位年+2位月+2位日+5位自增
     */
    public String getOutBatchNo() {
        return getNo(FINANCE_TRANSFER_MONEY, RedisKeyConstants.TRANSFER_MONEY_NO.getKeyTemplate(), getDateYYMMDD(), 5);
    }


    /**
     * 生成订单号 PREFIX+date+通过前缀获取的自增id
     *
     * @param PREFIX    前缀
     * @param redis_Key redis的主键
     * @param date      日期格式字符串
     * @param num       前缀自增补全位数
     * @return
     */
    public String getNo(String PREFIX, String redis_Key, String date, Integer num) {
        StringBuffer orderNO = new StringBuffer();
        //long ext = DateUtil.todayAddOf(1,0,0,1);

        long no = numberRedisDAO.incr(date + redis_Key);
        String number = this.lpad(num, (int) no);
        orderNO.append(PREFIX);
        orderNO.append(date);
        orderNO.append(number);

        log.info("编号生成：{}", orderNO.toString());
        return orderNO.toString();
    }



    //生成格式YYMMDD
    public String getDateYYMMDD() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.format(DateTimeFormatter.ofPattern("yyMMdd"));
    }


    private String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
}
