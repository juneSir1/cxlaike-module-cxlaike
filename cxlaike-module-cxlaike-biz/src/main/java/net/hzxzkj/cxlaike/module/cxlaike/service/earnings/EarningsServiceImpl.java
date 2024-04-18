package net.hzxzkj.cxlaike.module.cxlaike.service.earnings;

import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.earnings.EarningsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.earnings.EarningsMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 代理商每日汇总 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class EarningsServiceImpl implements EarningsService {

    @Resource
    private EarningsMapper earningsMapper;
    @Override
    public List<EarningsDO> getEarningsByUserIdAndTimeRange(Long userId,  LocalDate[] date) {
        return  earningsMapper.selectList(new LambdaQueryWrapperX<EarningsDO>().eq(EarningsDO::getUserId, userId).betweenIfPresent(EarningsDO::getDate, date).orderByAsc(EarningsDO::getDate));
    }
}
