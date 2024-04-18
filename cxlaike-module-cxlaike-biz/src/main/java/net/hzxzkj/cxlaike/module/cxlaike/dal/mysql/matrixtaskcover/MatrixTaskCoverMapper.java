package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskcover;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskcover.vo.MatrixTaskCoverExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskcover.vo.MatrixTaskCoverPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskcover.MatrixTaskCoverDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 矩阵任务封面 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskCoverMapper extends BaseMapperX<MatrixTaskCoverDO> {

    default PageResult<MatrixTaskCoverDO> selectPage(MatrixTaskCoverPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatrixTaskCoverDO>()
                .eqIfPresent(MatrixTaskCoverDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskCoverDO::getCoverId, reqVO.getCoverId())
                .betweenIfPresent(MatrixTaskCoverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskCoverDO::getId));
    }

    default List<MatrixTaskCoverDO> selectList(MatrixTaskCoverExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MatrixTaskCoverDO>()
                .eqIfPresent(MatrixTaskCoverDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskCoverDO::getCoverId, reqVO.getCoverId())
                .betweenIfPresent(MatrixTaskCoverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskCoverDO::getId));
    }

}
