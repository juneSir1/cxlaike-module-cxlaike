package net.hzxzkj.cxlaike.module.cxlaike.service.drrepresentative;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.drrepresentative.DrRepresentativeDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.drrepresentative.DrRepresentativeConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.drrepresentative.DrRepresentativeMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 达人探店代 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class DrRepresentativeServiceImpl implements DrRepresentativeService {

    @Resource
    private DrRepresentativeMapper drRepresentativeMapper;

    @Override
    public Long createDrRepresentative(DrRepresentativeCreateReqVO createReqVO) {
        // 插入
        DrRepresentativeDO drRepresentative = DrRepresentativeConvert.INSTANCE.convert(createReqVO);
        drRepresentativeMapper.insert(drRepresentative);
        // 返回
        return drRepresentative.getId();
    }

    @Override
    public void updateDrRepresentative(DrRepresentativeUpdateReqVO updateReqVO) {
        // 校验存在
        validateDrRepresentativeExists(updateReqVO.getId());
        // 更新
        DrRepresentativeDO updateObj = DrRepresentativeConvert.INSTANCE.convert(updateReqVO);
        drRepresentativeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDrRepresentative(Long id) {
        // 校验存在
        validateDrRepresentativeExists(id);
        // 删除
        drRepresentativeMapper.deleteById(id);
    }

    private void validateDrRepresentativeExists(Long id) {
        if (drRepresentativeMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public DrRepresentativeDO getDrRepresentative(Long id) {
        return drRepresentativeMapper.selectById(id);
    }

    @Override
    public List<DrRepresentativeDO> getDrRepresentativeList() {
        return drRepresentativeMapper.selectList();
    }

    @Override
    public PageResult<DrRepresentativeDO> getDrRepresentativePage(DrRepresentativePageReqVO pageReqVO) {
        return drRepresentativeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DrRepresentativeDO> getDrRepresentativeList(DrRepresentativeExportReqVO exportReqVO) {
        return drRepresentativeMapper.selectList(exportReqVO);
    }

}
