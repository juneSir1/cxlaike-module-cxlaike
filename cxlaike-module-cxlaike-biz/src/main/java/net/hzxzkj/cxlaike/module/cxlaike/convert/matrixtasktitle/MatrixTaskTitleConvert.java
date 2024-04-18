package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtasktitle;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskTitleReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtasktitle.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtasktitle.MatrixTaskTitleDO;

/**
 * 矩阵任务标题 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskTitleConvert {

    MatrixTaskTitleConvert INSTANCE = Mappers.getMapper(MatrixTaskTitleConvert.class);

    MatrixTaskTitleDO convert(MatrixTaskTitleCreateReqVO bean);

    MatrixTaskTitleDO convert(MatrixTaskTitleUpdateReqVO bean);

    MatrixTaskTitleRespVO convert(MatrixTaskTitleDO bean);

    List<MatrixTaskTitleRespVO> convertList(List<MatrixTaskTitleDO> list);

    PageResult<MatrixTaskTitleRespVO> convertPage(PageResult<MatrixTaskTitleDO> page);

    List<MatrixTaskTitleExcelVO> convertList02(List<MatrixTaskTitleDO> list);

    List<MatrixTaskTitleDO> convertList01(List<MatrixTaskTitleReqVO> list);

}
