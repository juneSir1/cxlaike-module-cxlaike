package net.hzxzkj.cxlaike.module.cxlaike.service.systakelevelconfig;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.SYS_TAKE_LEVEL_CONFIG_NOT_EXISTS;

import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systakelevelconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig.SysTakeLevelConfigDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.systakelevelconfig.SysTakeLevelConfigConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.systakelevelconfig.SysTakeLevelConfigMapper;

/**
 * 来客系统等级参数配置 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class SysTakeLevelConfigServiceImpl implements SysTakeLevelConfigService {

  @Resource
  private SysTakeLevelConfigMapper sysTakeLevelConfigMapper;

  @Override
  public Long createSysTakeLevelConfig(SysTakeLevelConfigCreateReqVO createReqVO) {
    // 插入
    SysTakeLevelConfigDO sysTakeLevelConfig = SysTakeLevelConfigConvert.INSTANCE
        .convert(createReqVO);
    sysTakeLevelConfigMapper.insert(sysTakeLevelConfig);
    // 返回
    return sysTakeLevelConfig.getId();
  }
//
//  @Override
//  public void updateSysTakeLevelConfig(SysTakeLevelConfigUpdateReqVO updateReqVO) {
//    // 校验存在
//    validateSysTakeLevelConfigExists(updateReqVO.getId());
//    // 更新
//    SysTakeLevelConfigDO updateObj = SysTakeLevelConfigConvert.INSTANCE.convert(updateReqVO);
//    sysTakeLevelConfigMapper.updateById(updateObj);
//  }

  @Override
  public void deleteSysTakeLevelConfig(Long id) {
    // 校验存在
    validateSysTakeLevelConfigExists(id);
    // 删除
    sysTakeLevelConfigMapper.deleteById(id);
  }

  private void validateSysTakeLevelConfigExists(Long id) {
    if (sysTakeLevelConfigMapper.selectById(id) == null) {
      throw exception(SYS_TAKE_LEVEL_CONFIG_NOT_EXISTS);
    }
  }

  @Override
  public SysTakeLevelConfigDO getSysTakeLevelConfig(Long id) {
    return sysTakeLevelConfigMapper.selectById(id);
  }


  @Override
  public PageResult<SysTakeLevelConfigDO> getSysTakeLevelConfigPage(
      SysTakeLevelConfigPageReqVO pageReqVO) {
    return sysTakeLevelConfigMapper.selectPage(pageReqVO);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SysTakeLevelConfigDO> getSysTakeLevelConfigList(Integer type) {
    return sysTakeLevelConfigMapper.selectList(new LambdaQueryWrapperX<SysTakeLevelConfigDO>()
        .eqIfPresent(SysTakeLevelConfigDO::getType, type)
        .orderByAsc(SysTakeLevelConfigDO::getLevelType));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean updateSysTakeLevelConfig(List<SysTakeLevelConfigUpdateReqVO> list) {
    List<SysTakeLevelConfigDO> updateList = SysTakeLevelConfigConvert.INSTANCE.convertList1(list);
    if (updateList != null && updateList.size() > 0) {
      for (SysTakeLevelConfigDO sysTakeLevelConfigDO : updateList) {
        sysTakeLevelConfigMapper.update(sysTakeLevelConfigDO,
            new LambdaQueryWrapperX<SysTakeLevelConfigDO>()
                .eq(SysTakeLevelConfigDO::getLevelType, sysTakeLevelConfigDO.getLevelType())
                .eq(SysTakeLevelConfigDO::getType, sysTakeLevelConfigDO.getType()));
      }
      return true;
    }
    return false;
  }

}
