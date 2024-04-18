package net.hzxzkj.cxlaike.module.cxlaike.service.metahumanconfig;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 来客数字人配置 Service 接口
 *
 * @author 宵征源码
 */
public interface MetaHumanConfigService {

  /**
   * 创建来客数字人配置
   *
   * @param createReqVO 创建信息
   * @return 编号
   */
  Long createMetaHumanConfig(@Valid MetaHumanConfigCreateReqVO createReqVO);

  /**
   * 更新来客数字人配置
   *
   * @param updateReqVO 更新信息
   */
  void updateMetaHumanConfig(@Valid MetaHumanConfigUpdateReqVO updateReqVO);

  /**
   * 删除来客数字人配置
   *
   * @param id 编号
   */
  void deleteMetaHumanConfig(Long id);

  /**
   * 获得来客数字人配置
   *
   * @param id 编号
   * @return 来客数字人配置
   */
  MetaHumanConfigDO getMetaHumanConfig(Long id);

  /**
   * 获得来客数字人配置
   *
   * @param type   类型
   * @param userId 用户id
   * @return
   */
  List<MetaHumanConfigDO> getMetaHumanConfig(Integer type, Long userId);

  /**
   * 获得来客数字人配置分页
   *
   * @param pageReqVO 分页查询
   * @return 来客数字人配置分页
   */
  PageResult<MetaHumanConfigDO> getMetaHumanConfigPage(MetaHumanConfigPageReqVO pageReqVO);

  /**
   * 根据类型查询 来客数字人配置
   *
   * @param type
   * @return
   */
  List<MetaHumanConfigDO> getMetaHumanConfig(Integer type);

  /**
   * 根据类型查询 来客数字人配置
   *
   * @param type
   * @return
   */
  MetaHumanConfigDO getMetaHumanConfigById(Integer type, Long id);
}
