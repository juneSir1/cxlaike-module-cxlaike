package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.professionvideo;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.professionvideo.ProfessionVideoDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo.*;

/**
 * 精选行业视频 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface ProfessionVideoMapper extends BaseMapperX<ProfessionVideoDO> {

    default PageResult<ProfessionVideoDO> selectPage(ProfessionVideoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProfessionVideoDO>()
                .eqIfPresent(ProfessionVideoDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ProfessionVideoDO::getPlayCount, reqVO.getPlayCount())
                .eqIfPresent(ProfessionVideoDO::getCoverUrl, reqVO.getCoverUrl())
                .eqIfPresent(ProfessionVideoDO::getVideoUrl, reqVO.getVideoUrl())
                .eqIfPresent(ProfessionVideoDO::getDuration, reqVO.getDuration())
                .betweenIfPresent(ProfessionVideoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProfessionVideoDO::getId));
    }

    default List<ProfessionVideoDO> selectList(ProfessionVideoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProfessionVideoDO>()
                .eqIfPresent(ProfessionVideoDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ProfessionVideoDO::getPlayCount, reqVO.getPlayCount())
                .eqIfPresent(ProfessionVideoDO::getCoverUrl, reqVO.getCoverUrl())
                .eqIfPresent(ProfessionVideoDO::getVideoUrl, reqVO.getVideoUrl())
                .eqIfPresent(ProfessionVideoDO::getDuration, reqVO.getDuration())
                .betweenIfPresent(ProfessionVideoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProfessionVideoDO::getId));
    }

}
