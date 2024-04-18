package net.hzxzkj.cxlaike.module.cxlaike.service.dygroup;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.service.impl.BaseServiceImpl;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.dygroup.DyGroupConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation.AccountGroupCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder.MatrixTaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountgroup.AccountGroupMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountgroupcorrelation.AccountGroupCorrelationMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskorder.MatrixTaskOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask.MatrixTaskOrderStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * 商户抖音分组管理 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class DyGroupServiceImpl extends BaseServiceImpl<AccountGroupMapper, AccountGroupDO> implements DyGroupService {

    @Resource
    private AccountGroupMapper dyGroupMapper;
    @Resource
    private AccountGroupCorrelationMapper dyGroupCorrelationMapper;
    @Resource
    private MatrixTaskOrderMapper matrixTaskOrderMapper;

    @Override
    public Long createDyGroup(AccountGroupCreateReqVO createReqVO) {
        // 插入
        AccountGroupDO dyGroup = DyGroupConvert.INSTANCE.convert(createReqVO);
        dyGroupMapper.insert(dyGroup);
        // 返回
        return dyGroup.getId();
    }

    @Override
    public void updateDyGroup(AccountGroupUpdateReqVO updateReqVO) {
        // 校验存在
        validateDyGroupExists(updateReqVO.getId());
        // 更新
        AccountGroupDO updateObj = DyGroupConvert.INSTANCE.convert(updateReqVO);
        dyGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteDyGroup(Long id) {
        // 校验存在
        validateDyGroupExists(id);
        // 删除
        dyGroupMapper.deleteById(id);
        dyGroupCorrelationMapper.delete(new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                .eq(AccountGroupCorrelationDO::getGroupId,id));
    }

    private void validateDyGroupExists(Long id) {
        if (dyGroupMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public AccountGroupDO getDyGroup(Long id) {
        return dyGroupMapper.selectById(id);
    }

    @Override
    public List<AccountGroupDO> getDyGroupList(Integer platform) {
        return dyGroupMapper.selectList(new LambdaQueryWrapperX<AccountGroupDO>()
                .eqIfPresent(AccountGroupDO::getPlatformType,platform));
    }

    @Override
    public PageResult<AccountGroupDO> getDyGroupPage(AccountGroupPageReqVO pageReqVO) {
        return dyGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AccountGroupDO> getDyGroupList(AccountGroupExportReqVO exportReqVO) {
        return dyGroupMapper.selectList(exportReqVO);
    }

    @Override
    public void transferDyGroup(@Valid AccountGroupTransferReqVO updateReqVO) {
        List<AccountGroupCorrelationDO> accountGroupCorrelationDOS = dyGroupCorrelationMapper.selectList(new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                .eq(AccountGroupCorrelationDO::getGroupId,updateReqVO.getFromId()));
        if(CollectionUtil.isEmpty(accountGroupCorrelationDOS)){
            return;
        }
        for(AccountGroupCorrelationDO correlationDO : accountGroupCorrelationDOS){
            boolean exists = dyGroupCorrelationMapper.exists(new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                    .eq(AccountGroupCorrelationDO::getGroupId, updateReqVO.getToId())
                    .eq(AccountGroupCorrelationDO::getDyBindId, correlationDO.getDyBindId()));
            if(exists){
                continue;
            }
            AccountGroupCorrelationDO updateDO = new AccountGroupCorrelationDO();
            updateDO.setGroupId(updateReqVO.getToId());
            dyGroupCorrelationMapper.update(updateDO,new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                    .eq(AccountGroupCorrelationDO::getDyBindId,correlationDO.getDyBindId())
                    .eq(AccountGroupCorrelationDO::getGroupId,updateReqVO.getFromId()));

        }
    }

    @Override
    public List<MatrixAccountListRespVO> getAccountByGroup(MatrixAccountListReqVO reqVO) {
        List<MatrixAccountListRespVO> matrixAccountListRespVOS = dyGroupMapper.selectAccountByGroup(reqVO);
        for(MatrixAccountListRespVO respVO : matrixAccountListRespVOS){
            List<AccountGroupCorrelationDO> accountGroupCorrelationDOS = dyGroupCorrelationMapper.selectList(new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                        .eq(AccountGroupCorrelationDO::getDyBindId,respVO.getAccountId()));
            respVO.setGroupId(accountGroupCorrelationDOS.stream().map(AccountGroupCorrelationDO::getGroupId).collect(Collectors.toList()));
            Long publishCount = matrixTaskOrderMapper.selectCount(new LambdaQueryWrapper<MatrixTaskOrderDO>()
                    .eq(MatrixTaskOrderDO::getAccountBindId, respVO.getAccountId())
                    .eq(MatrixTaskOrderDO::getOrderStatus, MatrixTaskOrderStatusEnum.YSC.getCode())
                    .between(MatrixTaskOrderDO::getPublishTime, LocalDateTimeUtils.getTodayStart(), LocalDateTimeUtils.getTodayEnd()));
            respVO.setPublishCount(publishCount);
        }
        return matrixAccountListRespVOS;
    }

    @Override
    public AccountGroupDO getByName(String name,Integer platform) {
        return dyGroupMapper.selectOne(new LambdaQueryWrapper<AccountGroupDO>()
                .eq(AccountGroupDO::getName,name)
                .eq(platform!=null,AccountGroupDO::getPlatformType,platform)
                .last("limit 1"));
    }

}
