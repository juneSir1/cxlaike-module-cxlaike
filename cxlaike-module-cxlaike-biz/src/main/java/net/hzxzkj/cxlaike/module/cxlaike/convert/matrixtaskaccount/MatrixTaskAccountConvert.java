package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtaskaccount;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskAccountReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskaccount.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskaccount.MatrixTaskAccountDO;

/**
 * 矩阵任务账号 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskAccountConvert {

    MatrixTaskAccountConvert INSTANCE = Mappers.getMapper(MatrixTaskAccountConvert.class);

    MatrixTaskAccountDO convert(MatrixTaskAccountCreateReqVO bean);

    MatrixTaskAccountDO convert(MatrixTaskAccountUpdateReqVO bean);

    MatrixTaskAccountRespVO convert(MatrixTaskAccountDO bean);

    List<MatrixTaskAccountRespVO> convertList(List<MatrixTaskAccountDO> list);

    PageResult<MatrixTaskAccountRespVO> convertPage(PageResult<MatrixTaskAccountDO> page);

    List<MatrixTaskAccountExcelVO> convertList02(List<MatrixTaskAccountDO> list);

    List<MatrixTaskAccountDO> convertList01(List<MatrixTaskAccountReqVO> list);

}
