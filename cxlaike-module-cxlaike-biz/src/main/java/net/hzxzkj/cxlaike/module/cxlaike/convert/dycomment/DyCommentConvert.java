package net.hzxzkj.cxlaike.module.cxlaike.convert.dycomment;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dycomment.DyCommentDO;

/**
 * 抖音评论管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface DyCommentConvert {

    DyCommentConvert INSTANCE = Mappers.getMapper(DyCommentConvert.class);

    DyCommentDO convert(DyCommentCreateReqVO bean);

    DyCommentDO convert(DyCommentUpdateReqVO bean);

    DyCommentRespVO convert(DyCommentDO bean);

    List<DyCommentRespVO> convertList(List<DyCommentDO> list);

    PageResult<DyCommentRespVO> convertPage(PageResult<DyCommentDO> page);

    List<DyCommentExcelVO> convertList02(List<DyCommentDO> list);

}
