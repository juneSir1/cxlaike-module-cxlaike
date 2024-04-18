package net.hzxzkj.cxlaike.module.cxlaike.convert.dygroup;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商户抖音分组管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface DyGroupConvert {

    DyGroupConvert INSTANCE = Mappers.getMapper(DyGroupConvert.class);

    AccountGroupDO convert(AccountGroupCreateReqVO bean);

    AccountGroupDO convert(AccountGroupUpdateReqVO bean);

    AccountGroupRespVO convert(AccountGroupDO bean);

    List<AccountGroupRespVO> convertList(List<AccountGroupDO> list);

    PageResult<AccountGroupRespVO> convertPage(PageResult<AccountGroupDO> page);

    List<AccountGroupExcelVO> convertList02(List<AccountGroupDO> list);

}
