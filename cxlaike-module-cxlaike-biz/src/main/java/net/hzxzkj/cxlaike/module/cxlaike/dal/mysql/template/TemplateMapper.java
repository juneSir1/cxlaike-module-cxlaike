package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.template;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplatePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template.TemplateDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai素材混剪视频模板 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TemplateMapper extends BaseMapperX<TemplateDO> {

    default PageResult<TemplateDO> selectPage(TemplatePageReqVO reqVO) {
        LambdaQueryWrapper<TemplateDO> lambdaQueryWrapper =new LambdaQueryWrapper<TemplateDO>().orderBy((reqVO.getSort()==null || reqVO.getSort()==1),true,TemplateDO::getCreateTime)
                .orderBy((reqVO.getSort()!=null && reqVO.getSort()==2),false,TemplateDO::getCreateTime);
        if(reqVO.getAiTaskId()!=null){
            lambdaQueryWrapper.eq(TemplateDO::getAiTaskId, reqVO.getAiTaskId());
        }
        if(StringUtils.isNotEmpty(reqVO.getTempName())){
            lambdaQueryWrapper.like(TemplateDO::getTempName, reqVO.getTempName());
        }
        if(reqVO.getVideoId() != null){
            lambdaQueryWrapper.eq(TemplateDO::getVideoId, reqVO.getVideoId());
        }
        if(reqVO.getAspectRatioType() != null){
            lambdaQueryWrapper.eq(TemplateDO::getAspectRatioType, reqVO.getAspectRatioType());
        }
        if(reqVO.getGroupNum() != null){
            lambdaQueryWrapper.eq(TemplateDO::getGroupNum, reqVO.getGroupNum());
        }
        if(reqVO.getDurationEnd() != null){
            lambdaQueryWrapper.le(TemplateDO::getDuration, reqVO.getDurationEnd());
        }
        if(reqVO.getDurationBegin() != null){
            lambdaQueryWrapper.ge(TemplateDO::getDuration, reqVO.getDurationBegin());
        }
        Long tenantId = TenantContextHolder.getTenantId();
        if(reqVO.getIsSystem()==null){
            //展示全部 我的全部加系统
            lambdaQueryWrapper.eq(TemplateDO::getIsSystem, 1).or().eq(TemplateDO::getTenantId,tenantId);
        }else if(reqVO.getIsSystem()==1){
            lambdaQueryWrapper.eq(TemplateDO::getIsSystem, 1);
        }else if(reqVO.getIsSystem()==2){
            lambdaQueryWrapper.eq(TemplateDO::getTenantId,tenantId);
        }

        return selectPage(reqVO, lambdaQueryWrapper);

    }

    default List<TemplateDO> selectList(TemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TemplateDO>()
                .eqIfPresent(TemplateDO::getAiTaskId, reqVO.getAiTaskId())
                .likeIfPresent(TemplateDO::getTempName, reqVO.getTempName())
                .eqIfPresent(TemplateDO::getVideoUrl, reqVO.getVideoUrl())
                .eqIfPresent(TemplateDO::getVideoId, reqVO.getVideoId())
                .eqIfPresent(TemplateDO::getDuration, reqVO.getDuration())
                .eqIfPresent(TemplateDO::getAspectRatioType, reqVO.getAspectRatioType())
                .eqIfPresent(TemplateDO::getGroupNum, reqVO.getGroupNum())
                .eqIfPresent(TemplateDO::getIsSystem, reqVO.getIsSystem())
                .betweenIfPresent(TemplateDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(TemplateDO::getTempIndustry, reqVO.getTempIndustry())
                .orderByDesc(TemplateDO::getId));
    }

}
