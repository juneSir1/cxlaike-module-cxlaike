package net.hzxzkj.cxlaike.module.cxlaike.convert.store;


import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppPOIRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreUpdateStatusReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store.StoreDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 门店管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface StoreConvert {

  StoreConvert INSTANCE = Mappers.getMapper(StoreConvert.class);

  StoreDO convert(StoreCreateReqVO bean);

  StoreDO convert(StoreUpdateReqVO bean);

  StoreRespVO convert(StoreDO bean);

  List<StoreRespVO> convertList(List<StoreDO> list);

  PageResult<StoreRespVO> convertPage(PageResult<StoreDO> page);

  StoreDO convert(StoreUpdateStatusReqVO bean);


  List<AppPOIRespVO> convertList1(List<StoreDO> storeList);


  List<AppPOIRespVO> convertList2(List<StoreDO> storeList);
}
