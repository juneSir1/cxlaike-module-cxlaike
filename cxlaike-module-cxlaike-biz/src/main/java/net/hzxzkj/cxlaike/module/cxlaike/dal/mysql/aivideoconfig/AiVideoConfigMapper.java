package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideoconfig;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig.AiVideoConfigDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.*;

/**
 * ai视频配置 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoConfigMapper extends BaseMapperX<AiVideoConfigDO> {

    default PageResult<AiVideoConfigDO> selectPage(AiVideoConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoConfigDO>()
                .eqIfPresent(AiVideoConfigDO::getType, reqVO.getType())
                .eqIfPresent(AiVideoConfigDO::getValue, reqVO.getValue())
                .eqIfPresent(AiVideoConfigDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiVideoConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoConfigDO::getId));
    }

    default List<AiVideoConfigDO> selectList(AiVideoConfigExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiVideoConfigDO>()
                .eqIfPresent(AiVideoConfigDO::getType, reqVO.getType())
                .eqIfPresent(AiVideoConfigDO::getValue, reqVO.getValue())
                .eqIfPresent(AiVideoConfigDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiVideoConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoConfigDO::getId));
    }

}
