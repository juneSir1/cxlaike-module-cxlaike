package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotemplate;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo.*;

/**
 * ai视频模板 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTemplateMapper extends BaseMapperX<AiVideoTemplateDO> {

    default PageResult<AiVideoTemplateDO> selectPage(AiVideoTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoTemplateDO>()
                .eqIfPresent(AiVideoTemplateDO::getType, reqVO.getType())
                .eqIfPresent(AiVideoTemplateDO::getValue, reqVO.getValue())
                .betweenIfPresent(AiVideoTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoTemplateDO::getId));
    }

    default List<AiVideoTemplateDO> selectList(AiVideoTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiVideoTemplateDO>()
                .eqIfPresent(AiVideoTemplateDO::getType, reqVO.getType())
                .eqIfPresent(AiVideoTemplateDO::getValue, reqVO.getValue())
                .betweenIfPresent(AiVideoTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoTemplateDO::getId));
    }

}
