package net.hzxzkj.cxlaike.module.cxlaike.convert.aisystemarg;

import java.util.List;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 来客系统参数 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiSystemArgConvert {

    AiSystemArgConvert INSTANCE = Mappers.getMapper(AiSystemArgConvert.class);

    AiSystemArgDO convert(AiSystemArgCreateReqVO bean);

    AiSystemArgDO convert(AiSystemArgUpdateReqVO bean);

    AiSystemArgRespVO convert(AiSystemArgDO bean);

    List<AiSystemArgRespVO> convertList(List<AiSystemArgDO> list);

    PageResult<AiSystemArgRespVO> convertPage(PageResult<AiSystemArgDO> page);

    List<AiSystemArgExcelVO> convertList02(List<AiSystemArgDO> list);

}
