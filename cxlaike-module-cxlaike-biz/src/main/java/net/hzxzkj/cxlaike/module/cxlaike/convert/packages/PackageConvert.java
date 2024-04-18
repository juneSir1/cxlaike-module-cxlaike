package net.hzxzkj.cxlaike.module.cxlaike.convert.packages;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packages.PackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 商户套餐 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface PackageConvert {

    PackageConvert INSTANCE = Mappers.getMapper(PackageConvert.class);

    PackageDO convert(PackageCreateReqVO bean);

    PackageDO convert(PackageUpdateReqVO bean);

    PackageRespVO convert(PackageDO bean);

    List<PackageRespVO> convertList(List<PackageDO> list);

    PageResult<PackageRespVO> convertPage(PageResult<PackageDO> page);

    List<PackageExcelVO> convertList02(List<PackageDO> list);

}
