package net.hzxzkj.cxlaike.module.cxlaike.convert.aitriallisten;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aitriallisten.AiTrialListenDO;

/**
 * ai语音试听功能 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiTrialListenConvert {

    AiTrialListenConvert INSTANCE = Mappers.getMapper(AiTrialListenConvert.class);

    AiTrialListenDO convert(AiTrialListenCreateReqVO bean);

    AiTrialListenDO convert(AiTrialListenUpdateReqVO bean);

    AiTrialListenRespVO convert(AiTrialListenDO bean);

    List<AiTrialListenRespVO> convertList(List<AiTrialListenDO> list);

    PageResult<AiTrialListenRespVO> convertPage(PageResult<AiTrialListenDO> page);


}
