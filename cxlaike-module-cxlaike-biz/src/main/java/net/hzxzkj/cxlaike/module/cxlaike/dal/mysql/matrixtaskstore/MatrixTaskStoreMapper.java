package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskstore;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStoreExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStorePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskstore.MatrixTaskStoreDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 矩阵任务门店 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskStoreMapper extends BaseMapperX<MatrixTaskStoreDO> {

    default PageResult<MatrixTaskStoreDO> selectPage(MatrixTaskStorePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatrixTaskStoreDO>()
                .eqIfPresent(MatrixTaskStoreDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskStoreDO::getStoreId, reqVO.getStoreId())
                .betweenIfPresent(MatrixTaskStoreDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskStoreDO::getId));
    }

    default List<MatrixTaskStoreDO> selectList(MatrixTaskStoreExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MatrixTaskStoreDO>()
                .eqIfPresent(MatrixTaskStoreDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskStoreDO::getStoreId, reqVO.getStoreId())
                .betweenIfPresent(MatrixTaskStoreDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskStoreDO::getId));
    }

}
