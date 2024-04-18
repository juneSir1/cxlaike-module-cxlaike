package net.hzxzkj.cxlaike.module.cxlaike.service.systakelevelconfig;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systakelevelconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig.SysTakeLevelConfigDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 来客系统等级参数配置 Service 接口
 *
 * @author 宵征源码
 */
public interface SysTakeLevelConfigService {

    /**
     * 创建来客系统等级参数配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysTakeLevelConfig(@Valid SysTakeLevelConfigCreateReqVO createReqVO);

//    /**
//     * 更新来客系统等级参数配置
//     *
//     * @param updateReqVO 更新信息
//     */
//    void updateSysTakeLevelConfig(@Valid SysTakeLevelConfigUpdateReqVO updateReqVO);

    /**
     * 删除来客系统等级参数配置
     *
     * @param id 编号
     */
    void deleteSysTakeLevelConfig(Long id);

    /**
     * 获得来客系统等级参数配置
     *
     * @param id 编号
     * @return 来客系统等级参数配置
     */
    SysTakeLevelConfigDO getSysTakeLevelConfig(Long id);


    /**
     * 获得来客系统等级参数配置分页
     *
     * @param pageReqVO 分页查询
     * @return 来客系统等级参数配置分页
     */
    PageResult<SysTakeLevelConfigDO> getSysTakeLevelConfigPage(SysTakeLevelConfigPageReqVO pageReqVO);


    List<SysTakeLevelConfigDO> getSysTakeLevelConfigList(Integer type);

    Boolean updateSysTakeLevelConfig(List<SysTakeLevelConfigUpdateReqVO> list);
}
