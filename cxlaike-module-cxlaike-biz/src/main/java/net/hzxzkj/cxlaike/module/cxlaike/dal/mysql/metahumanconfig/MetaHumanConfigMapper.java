package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.metahumanconfig;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.*;

/**
 * 来客数字人配置 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MetaHumanConfigMapper extends BaseMapperX<MetaHumanConfigDO> {

    default PageResult<MetaHumanConfigDO> selectPage(MetaHumanConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MetaHumanConfigDO>()
                .eqIfPresent(MetaHumanConfigDO::getType, reqVO.getType())
                .eqIfPresent(MetaHumanConfigDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MetaHumanConfigDO::getDubCode, reqVO.getDubCode())
                .eqIfPresent(MetaHumanConfigDO::getMetaHumanCode, reqVO.getMetaHumanCode())
                .betweenIfPresent(MetaHumanConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MetaHumanConfigDO::getId));
    }

    default List<MetaHumanConfigDO> selectList(MetaHumanConfigExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MetaHumanConfigDO>()
                .eqIfPresent(MetaHumanConfigDO::getType, reqVO.getType())
                .eqIfPresent(MetaHumanConfigDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MetaHumanConfigDO::getDubCode, reqVO.getDubCode())
                .eqIfPresent(MetaHumanConfigDO::getMetaHumanCode, reqVO.getMetaHumanCode())
                .betweenIfPresent(MetaHumanConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MetaHumanConfigDO::getId));
    }

}
