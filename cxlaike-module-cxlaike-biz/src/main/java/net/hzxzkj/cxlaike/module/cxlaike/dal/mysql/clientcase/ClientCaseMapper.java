package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.clientcase;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.clientcase.ClientCaseDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo.*;

/**
 * 客户案例 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface ClientCaseMapper extends BaseMapperX<ClientCaseDO> {

    default PageResult<ClientCaseDO> selectPage(ClientCasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClientCaseDO>()
                .eqIfPresent(ClientCaseDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ClientCaseDO::getLabel, reqVO.getLabel())
                .eqIfPresent(ClientCaseDO::getCoverUrl, reqVO.getCoverUrl())
                .eqIfPresent(ClientCaseDO::getVideoUrl, reqVO.getVideoUrl())
                .eqIfPresent(ClientCaseDO::getContent, reqVO.getContent())
                .betweenIfPresent(ClientCaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClientCaseDO::getId));
    }

    default List<ClientCaseDO> selectList(ClientCaseExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ClientCaseDO>()
                .eqIfPresent(ClientCaseDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ClientCaseDO::getLabel, reqVO.getLabel())
                .eqIfPresent(ClientCaseDO::getCoverUrl, reqVO.getCoverUrl())
                .eqIfPresent(ClientCaseDO::getVideoUrl, reqVO.getVideoUrl())
                .eqIfPresent(ClientCaseDO::getContent, reqVO.getContent())
                .betweenIfPresent(ClientCaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClientCaseDO::getId));
    }

}
