package net.hzxzkj.cxlaike.module.cxlaike.service.metahumanconfig;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.META_HUMAN_CONFIG_NOT_EXISTS;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.MetaHumanConfigCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.MetaHumanConfigExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.MetaHumanConfigPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.MetaHumanConfigUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.metahumanconfig.MetaHumanConfigConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.metahumanconfig.MetaHumanConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 来客数字人配置 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class MetaHumanConfigServiceImpl implements MetaHumanConfigService {

  @Resource
  private MetaHumanConfigMapper metaHumanConfigMapper;

  @Override
  public Long createMetaHumanConfig(MetaHumanConfigCreateReqVO createReqVO) {
    // 插入
    MetaHumanConfigDO metaHumanConfig = MetaHumanConfigConvert.INSTANCE.convert(createReqVO);
    metaHumanConfigMapper.insert(metaHumanConfig);
    // 返回
    return metaHumanConfig.getId();
  }

  @Override
  public void updateMetaHumanConfig(MetaHumanConfigUpdateReqVO updateReqVO) {
    // 校验存在
    validateMetaHumanConfigExists(updateReqVO.getId());
    // 更新
    MetaHumanConfigDO updateObj = MetaHumanConfigConvert.INSTANCE.convert(updateReqVO);
    metaHumanConfigMapper.updateById(updateObj);
  }

  @Override
  public void deleteMetaHumanConfig(Long id) {
    // 校验存在
    validateMetaHumanConfigExists(id);
    // 删除
    metaHumanConfigMapper.deleteById(id);
  }

  private void validateMetaHumanConfigExists(Long id) {
    if (metaHumanConfigMapper.selectById(id) == null) {
      throw exception(META_HUMAN_CONFIG_NOT_EXISTS);
    }
  }

  @Override
  public MetaHumanConfigDO getMetaHumanConfig(Long id) {
    return metaHumanConfigMapper.selectById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<MetaHumanConfigDO> getMetaHumanConfig(Integer type, Long userId) {
    return metaHumanConfigMapper.selectList(
        new LambdaQueryWrapperX<MetaHumanConfigDO>().eq(MetaHumanConfigDO::getType, type)
            .eq(MetaHumanConfigDO::getUserId, userId));
  }

  @Override
  public PageResult<MetaHumanConfigDO> getMetaHumanConfigPage(MetaHumanConfigPageReqVO pageReqVO) {
    return metaHumanConfigMapper.selectPage(pageReqVO);
  }

  @Override
  @Transactional(readOnly = true)
  public List<MetaHumanConfigDO> getMetaHumanConfig(Integer type) {
    return metaHumanConfigMapper.selectList(
        new LambdaQueryWrapper<MetaHumanConfigDO>().eq(MetaHumanConfigDO::getType, type));
  }

  @Override
  @Transactional(readOnly = true)
  public MetaHumanConfigDO getMetaHumanConfigById(Integer type, Long id) {
    return metaHumanConfigMapper
        .selectOne(new LambdaQueryWrapperX<MetaHumanConfigDO>().eq(MetaHumanConfigDO::getType, type)
            .eq(MetaHumanConfigDO::getId, id));
  }


}
