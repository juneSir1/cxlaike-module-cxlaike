package net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig.AiVideoConfigDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * ai视频配置 Service 接口
 *
 * @author 宵征源码
 */
public interface AiVideoConfigService {

    /**
     * 创建ai视频配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAiVideoConfig(@Valid AiVideoConfigCreateReqVO createReqVO);

    /**
     * 更新ai视频配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAiVideoConfig(@Valid AiVideoConfigUpdateReqVO updateReqVO);

    /**
     * 删除ai视频配置
     *
     * @param id 编号
     */
    void deleteAiVideoConfig(Long id);

    /**
     * 获得ai视频配置
     *
     * @param id 编号
     * @return ai视频配置
     */
    AiVideoConfigDO getAiVideoConfig(Long id);

    /**
     * 获得ai视频配置列表
     *
     * @param ids 编号
     * @return ai视频配置列表
     */
    List<AiVideoConfigDO> getAiVideoConfigList(Collection<Long> ids);

    /**
     * 获得ai视频配置分页
     *
     * @param pageReqVO 分页查询
     * @return ai视频配置分页
     */
    PageResult<AiVideoConfigDO> getAiVideoConfigPage(AiVideoConfigPageReqVO pageReqVO);

    /**
     * 获得ai视频配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ai视频配置列表
     */
    List<AiVideoConfigDO> getAiVideoConfigList(AiVideoConfigExportReqVO exportReqVO);


    /**
     * 根据类型和状态获取配置列表
     * @param type
     * @return
     */
    List<String> getAiVideoConfigList(Integer type);

}
