package net.hzxzkj.cxlaike.module.cxlaike.service.aisystemarg;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 来客系统参数 Service 接口
 *
 * @author 宵征源码
 */
public interface AiSystemArgService {


  List<AiSystemArgDO> getAiSystemArgList(Integer sysType);

  List<AiSystemArgDO> getAiSystemArgList(Integer sysType, Set<Long> ids);


}
