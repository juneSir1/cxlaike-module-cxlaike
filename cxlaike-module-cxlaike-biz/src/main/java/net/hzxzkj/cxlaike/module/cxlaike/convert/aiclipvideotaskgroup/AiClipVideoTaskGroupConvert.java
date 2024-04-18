package net.hzxzkj.cxlaike.module.cxlaike.convert.aiclipvideotaskgroup;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup.AiClipVideoTaskGroupDO;

/**
 * ai素材混剪视频任务-视频组设置 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiClipVideoTaskGroupConvert {

    AiClipVideoTaskGroupConvert INSTANCE = Mappers.getMapper(AiClipVideoTaskGroupConvert.class);

    AiClipVideoTaskGroupDO convert(AiClipVideoTaskGroupCreateReqVO bean);

    AiClipVideoTaskGroupCreateReqVO convertToVO(AiClipVideoTaskGroupDO bean);

    AiClipVideoTaskGroupDO convert(AiClipVideoTaskGroupUpdateReqVO bean);

    AiClipVideoTaskGroupRespVO convert(AiClipVideoTaskGroupDO bean);

    List<AiClipVideoTaskGroupRespVO> convertList(List<AiClipVideoTaskGroupDO> list);

    PageResult<AiClipVideoTaskGroupRespVO> convertPage(PageResult<AiClipVideoTaskGroupDO> page);

    List<AiClipVideoTaskGroupExcelVO> convertList02(List<AiClipVideoTaskGroupDO> list);

}
