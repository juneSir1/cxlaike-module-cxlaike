package net.hzxzkj.cxlaike.module.cxlaike.convert.contenttitlemanagement;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement.ContentTitleManagementDO;

/**
 * 任务内容标题管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface ContentTitleManagementConvert {

    ContentTitleManagementConvert INSTANCE = Mappers.getMapper(ContentTitleManagementConvert.class);

    ContentTitleManagementDO convert(ContentTitleManagementCreateReqVO bean);

    ContentTitleManagementDO convert(ContentTitleManagementUpdateReqVO bean);

    ContentTitleManagementRespVO convert(ContentTitleManagementDO bean);

    List<ContentTitleManagementRespVO> convertList(List<ContentTitleManagementDO> list);

    PageResult<ContentTitleManagementRespVO> convertPage(PageResult<ContentTitleManagementDO> page);

    List<ContentTitleManagementExcelVO> convertList02(List<ContentTitleManagementDO> list);

}
