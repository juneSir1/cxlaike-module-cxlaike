package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtaskcover;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskCoverReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskcover.vo.MatrixTaskCoverCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskcover.vo.MatrixTaskCoverExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskcover.vo.MatrixTaskCoverRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskcover.vo.MatrixTaskCoverUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskcover.MatrixTaskCoverDO;

/**
 * 矩阵任务封面 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskCoverConvert {

    MatrixTaskCoverConvert INSTANCE = Mappers.getMapper(MatrixTaskCoverConvert.class);

    MatrixTaskCoverDO convert(MatrixTaskCoverCreateReqVO bean);

    MatrixTaskCoverDO convert(MatrixTaskCoverUpdateReqVO bean);

    MatrixTaskCoverRespVO convert(MatrixTaskCoverDO bean);

    List<MatrixTaskCoverRespVO> convertList(List<MatrixTaskCoverDO> list);

    PageResult<MatrixTaskCoverRespVO> convertPage(PageResult<MatrixTaskCoverDO> page);

    List<MatrixTaskCoverExcelVO> convertList02(List<MatrixTaskCoverDO> list);

    List<MatrixTaskCoverDO> convertList01(List<MatrixTaskCoverReqVO> list);
}
