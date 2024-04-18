package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtaskorder;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.matrixtaskorder.vo.AppMatrixTaskOrderRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskorder.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder.MatrixTaskOrderDO;

/**
 * 用户矩阵任务 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskOrderConvert {

    MatrixTaskOrderConvert INSTANCE = Mappers.getMapper(MatrixTaskOrderConvert.class);

    MatrixTaskOrderRespVO convert(MatrixTaskOrderDO bean);

    List<MatrixTaskOrderRespVO> convertList(List<MatrixTaskOrderDO> list);

    PageResult<MatrixTaskOrderRespVO> convertPage(PageResult<MatrixTaskOrderDO> page);

    PageResult<AppMatrixTaskOrderRespVO> convertPage01(PageResult<MatrixTaskOrderDO> page);


}
