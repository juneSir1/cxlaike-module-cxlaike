package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.contenttitlemanagement;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement.ContentTitleManagementDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务内容标题管理 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface ContentTitleManagementMapper extends BaseMapperX<ContentTitleManagementDO> {

    default PageResult<ContentTitleManagementDO> selectPage(ContentTitleManagementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ContentTitleManagementDO>()
                .eqIfPresent(ContentTitleManagementDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(ContentTitleManagementDO::getVideoGroupId, reqVO.getVideoGroupId())
                .eqIfPresent(ContentTitleManagementDO::getContentTitle, reqVO.getContentTitle())
                .betweenIfPresent(ContentTitleManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ContentTitleManagementDO::getId));
    }

    default List<ContentTitleManagementDO> selectList(ContentTitleManagementExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ContentTitleManagementDO>()
                .eqIfPresent(ContentTitleManagementDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(ContentTitleManagementDO::getVideoGroupId, reqVO.getVideoGroupId())
                .eqIfPresent(ContentTitleManagementDO::getContentTitle, reqVO.getContentTitle())
                .betweenIfPresent(ContentTitleManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ContentTitleManagementDO::getId));
    }

}
