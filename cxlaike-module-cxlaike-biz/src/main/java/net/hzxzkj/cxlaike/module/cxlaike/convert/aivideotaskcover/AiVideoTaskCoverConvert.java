package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskcover;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskcover.AiVideoTaskCoverDO;

/**
 * ai素材混剪视频封面图片关联 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskCoverConvert {

    AiVideoTaskCoverConvert INSTANCE = Mappers.getMapper(AiVideoTaskCoverConvert.class);

    AiVideoTaskCoverDO convert(AiVideoTaskCoverCreateReqVO bean);

    AiVideoTaskCoverDO convert(AiVideoTaskCoverUpdateReqVO bean);

    AiVideoTaskCoverRespVO convert(AiVideoTaskCoverDO bean);

    List<AiVideoTaskCoverRespVO> convertList(List<AiVideoTaskCoverDO> list);

    PageResult<AiVideoTaskCoverRespVO> convertPage(PageResult<AiVideoTaskCoverDO> page);

    List<AiVideoTaskCoverExcelVO> convertList02(List<AiVideoTaskCoverDO> list);

}
