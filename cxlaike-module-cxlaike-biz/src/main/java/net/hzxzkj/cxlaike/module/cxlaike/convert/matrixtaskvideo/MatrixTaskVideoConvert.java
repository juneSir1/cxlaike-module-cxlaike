package net.hzxzkj.cxlaike.module.cxlaike.convert.matrixtaskvideo;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskVideoReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskvideo.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskvideo.MatrixTaskVideoDO;

/**
 * 矩阵任务视频 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskVideoConvert {

    MatrixTaskVideoConvert INSTANCE = Mappers.getMapper(MatrixTaskVideoConvert.class);

    MatrixTaskVideoDO convert(MatrixTaskVideoCreateReqVO bean);

    MatrixTaskVideoDO convert(MatrixTaskVideoUpdateReqVO bean);

    MatrixTaskVideoRespVO convert(MatrixTaskVideoDO bean);

    List<MatrixTaskVideoRespVO> convertList(List<MatrixTaskVideoDO> list);

    PageResult<MatrixTaskVideoRespVO> convertPage(PageResult<MatrixTaskVideoDO> page);

    List<MatrixTaskVideoExcelVO> convertList02(List<MatrixTaskVideoDO> list);

    List<MatrixTaskVideoDO> convertList01 (List<MatrixTaskVideoReqVO> list);

}
