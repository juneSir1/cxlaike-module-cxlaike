package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtasktitle;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtasktitle.MatrixTaskTitleDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtasktitle.vo.*;

/**
 * 矩阵任务标题 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskTitleMapper extends BaseMapperX<MatrixTaskTitleDO> {

    default PageResult<MatrixTaskTitleDO> selectPage(MatrixTaskTitlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatrixTaskTitleDO>()
                .eqIfPresent(MatrixTaskTitleDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskTitleDO::getTitle, reqVO.getContent())
                .betweenIfPresent(MatrixTaskTitleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskTitleDO::getId));
    }

    default List<MatrixTaskTitleDO> selectList(MatrixTaskTitleExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MatrixTaskTitleDO>()
                .eqIfPresent(MatrixTaskTitleDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskTitleDO::getTitle, reqVO.getContent())
                .betweenIfPresent(MatrixTaskTitleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskTitleDO::getId));
    }

}
