package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtask;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtask.MatrixTaskDO;

/**
 * 矩阵任务 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskConvert {

    MatrixTaskConvert INSTANCE = Mappers.getMapper(MatrixTaskConvert.class);

    MatrixTaskDO convert(MatrixTaskCreateReqVO bean);
    MatrixTaskDO convert(MatrixTaskUpdateOfScanReqVO bean);

    MatrixTaskDO convert(MatrixTaskCreateOfScanReqVO bean);

    MatrixTaskDO convert(MatrixTaskUpdateReqVO bean);

    MatrixTaskRespVO convert(MatrixTaskDO bean);

    List<MatrixTaskRespVO> convertList(List<MatrixTaskDO> list);

    PageResult<MatrixTaskRespVO> convertPage(PageResult<MatrixTaskDO> page);

    List<MatrixTaskExcelVO> convertList02(List<MatrixTaskDO> list);

    MatrixTaskCreateReqVO convert01(MatrixTaskUpdateReqVO updateReqVO);

    MatrixTaskDetailRespVO convert01(MatrixTaskDO bean);

    List<MatrixTaskPageRespExcelVO> convertList03(List<MatrixTaskPageRespVO> list);

}
