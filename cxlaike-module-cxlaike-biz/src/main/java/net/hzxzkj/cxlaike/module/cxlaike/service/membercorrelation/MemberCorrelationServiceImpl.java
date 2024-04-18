package net.hzxzkj.cxlaike.module.cxlaike.service.membercorrelation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.membercorrelation.MemberCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.membercorrelation.MemberCorrelationMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商户与达人视频账号(抖音账号)的任务关联 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class MemberCorrelationServiceImpl implements MemberCorrelationService {

    @Resource
    private MemberCorrelationMapper memberCorrelationMapper;

    @Override
    public void createCorrelation(Long merchantId, Long bindId) {
        boolean exists = memberCorrelationMapper.exists(new LambdaQueryWrapper<MemberCorrelationDO>()
                .eq(MemberCorrelationDO::getMerchantId, merchantId)
                .eq(MemberCorrelationDO::getDyBindId, bindId));
        if(!exists){
            MemberCorrelationDO memberCorrelationDO = new MemberCorrelationDO();
            memberCorrelationDO.setDyBindId(bindId);
            memberCorrelationDO.setMerchantId(merchantId);
            memberCorrelationMapper.insert(memberCorrelationDO);
        }
    }

    @Override
    public List<MemberCorrelationDO> getList() {
        return memberCorrelationMapper.selectList();
    }
}
