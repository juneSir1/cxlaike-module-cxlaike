package net.hzxzkj.cxlaike.module.cxlaike.convert.transferaccounts;


import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.transferaccounts.TransferAccountsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 转账账户 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TransferAccountsConvert {

    TransferAccountsConvert INSTANCE = Mappers.getMapper(TransferAccountsConvert.class);

    TransferAccountsDO convert(TransferAccountsCreateReqVO bean);

    TransferAccountsDO convert(TransferAccountsUpdateReqVO bean);

    TransferAccountsRespVO convert(TransferAccountsDO bean);

    List<TransferAccountsRespVO> convertList(List<TransferAccountsDO> list);

    PageResult<TransferAccountsRespVO> convertPage(PageResult<TransferAccountsDO> page);

    List<TransferAccountsExcelVO> convertList02(List<TransferAccountsDO> list);

}
