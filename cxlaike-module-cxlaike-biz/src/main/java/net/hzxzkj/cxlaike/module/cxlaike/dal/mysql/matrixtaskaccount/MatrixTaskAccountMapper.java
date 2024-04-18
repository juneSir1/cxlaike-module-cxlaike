package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskaccount;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskaccount.MatrixTaskAccountDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskaccount.vo.*;

/**
 * 矩阵任务账号 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskAccountMapper extends BaseMapperX<MatrixTaskAccountDO> {

    default PageResult<MatrixTaskAccountDO> selectPage(MatrixTaskAccountPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatrixTaskAccountDO>()
                .eqIfPresent(MatrixTaskAccountDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskAccountDO::getAccountBindId, reqVO.getAccountBindId())
                .betweenIfPresent(MatrixTaskAccountDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskAccountDO::getId));
    }

    default List<MatrixTaskAccountDO> selectList(MatrixTaskAccountExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MatrixTaskAccountDO>()
                .eqIfPresent(MatrixTaskAccountDO::getMatrixTaskId, reqVO.getMatrixTaskId())
                .eqIfPresent(MatrixTaskAccountDO::getAccountBindId, reqVO.getAccountBindId())
                .betweenIfPresent(MatrixTaskAccountDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatrixTaskAccountDO::getId));
    }

}
