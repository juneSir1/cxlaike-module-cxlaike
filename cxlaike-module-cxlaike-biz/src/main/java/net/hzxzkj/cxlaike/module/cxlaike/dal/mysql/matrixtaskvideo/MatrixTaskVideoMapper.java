package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskvideo;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskvideo.MatrixTaskVideoDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskvideo.vo.*;

/**
 * 矩阵任务视频 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskVideoMapper extends BaseMapperX<MatrixTaskVideoDO> {

    default PageResult<MatrixTaskVideoDO> selectPage(MatrixTaskVideoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatrixTaskVideoDO>()
                .eqIfPresent(MatrixTaskVideoDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskVideoDO::getVideoId, reqVO.getVideoId())
                .betweenIfPresent(MatrixTaskVideoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskVideoDO::getId));
    }

    default List<MatrixTaskVideoDO> selectList(MatrixTaskVideoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MatrixTaskVideoDO>()
                .eqIfPresent(MatrixTaskVideoDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskVideoDO::getVideoId, reqVO.getVideoId())
                .betweenIfPresent(MatrixTaskVideoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskVideoDO::getId));
    }

}
