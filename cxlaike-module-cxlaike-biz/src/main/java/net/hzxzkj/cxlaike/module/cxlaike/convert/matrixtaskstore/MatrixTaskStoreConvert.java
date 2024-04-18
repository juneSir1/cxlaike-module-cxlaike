package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtaskstore;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskStoreReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStoreCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStoreExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStoreRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStoreUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskstore.MatrixTaskStoreDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 矩阵任务门店 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskStoreConvert {

    MatrixTaskStoreConvert INSTANCE = Mappers.getMapper(MatrixTaskStoreConvert.class);

    List<MatrixTaskStoreDO> convertList01(List<MatrixTaskStoreReqVO> list);

    MatrixTaskStoreDO convert(MatrixTaskStoreCreateReqVO bean);

    MatrixTaskStoreDO convert(MatrixTaskStoreUpdateReqVO bean);

    MatrixTaskStoreRespVO convert(MatrixTaskStoreDO bean);

    List<MatrixTaskStoreRespVO> convertList(List<MatrixTaskStoreDO> list);

    PageResult<MatrixTaskStoreRespVO> convertPage(PageResult<MatrixTaskStoreDO> page);

    List<MatrixTaskStoreExcelVO> convertList02(List<MatrixTaskStoreDO> list);
}
