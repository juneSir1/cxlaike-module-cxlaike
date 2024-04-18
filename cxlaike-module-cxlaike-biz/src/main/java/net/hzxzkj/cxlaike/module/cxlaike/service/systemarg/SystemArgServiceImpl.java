package net.hzxzkj.cxlaike.module.cxlaike.service.systemarg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systemarg.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systemarg.SystemArgDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.systemarg.SystemArgConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.systemarg.SystemArgMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 来客系统参数 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class SystemArgServiceImpl implements SystemArgService {

  @Resource
  private SystemArgMapper systemArgMapper;

  @Override
  public Long createSystemArg(SystemArgCreateReqVO createReqVO) {
    // 插入
    SystemArgDO systemArg = SystemArgConvert.INSTANCE.convert(createReqVO);
    systemArgMapper.insert(systemArg);
    // 返回
    return systemArg.getId();
  }

  @Override
  public void updateSystemArg(SystemArgUpdateReqVO updateReqVO) {
    // 校验存在
    validateSystemArgExists(updateReqVO.getId());
    // 更新
    SystemArgDO updateObj = SystemArgConvert.INSTANCE.convert(updateReqVO);
    systemArgMapper.updateById(updateObj);
  }

  @Override
  public void deleteSystemArg(Long id) {
    // 校验存在
    validateSystemArgExists(id);
    // 删除
    systemArgMapper.deleteById(id);
  }

  private void validateSystemArgExists(Long id) {
    if (systemArgMapper.selectById(id) == null) {
      throw  exception(SYSTEM_ARG_NOT_EXISTS);
    }
  }

  @Override
  public SystemArgDO getSystemArg() {
    //查询最后一个
    return systemArgMapper.selectOne(null);
  }

  @Override
  public List<SystemArgDO> getSystemArgList(Collection<Long> ids) {
    return systemArgMapper.selectBatchIds(ids);
  }

  @Override
  public PageResult<SystemArgDO> getSystemArgPage(SystemArgPageReqVO pageReqVO) {
    return systemArgMapper.selectPage(pageReqVO);
  }

  @Override
  public List<SystemArgDO> getSystemArgList(SystemArgExportReqVO exportReqVO) {
    return systemArgMapper.selectList(exportReqVO);
  }

}
