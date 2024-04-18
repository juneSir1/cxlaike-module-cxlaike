package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.systakelevelconfig;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig.SysTakeLevelConfigDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systakelevelconfig.vo.*;

/**
 * 来客系统等级参数配置 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface SysTakeLevelConfigMapper extends BaseMapperX<SysTakeLevelConfigDO> {

    default PageResult<SysTakeLevelConfigDO> selectPage(SysTakeLevelConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysTakeLevelConfigDO>()
                .eqIfPresent(SysTakeLevelConfigDO::getType, reqVO.getType())
                .orderByDesc(SysTakeLevelConfigDO::getId));
    }

    default List<SysTakeLevelConfigDO> selectList(SysTakeLevelConfigExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SysTakeLevelConfigDO>()
                .eqIfPresent(SysTakeLevelConfigDO::getType, reqVO.getType())
                .orderByDesc(SysTakeLevelConfigDO::getId));
    }

}
