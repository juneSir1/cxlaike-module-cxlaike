package net.hzxzkj.cxlaike.module.cxlaike.service.aisystemarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aisystemarg.AiSystemArgConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aisystemarg.AiSystemArgMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 来客系统参数 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiSystemArgServiceImpl implements AiSystemArgService {

  @Resource
  private AiSystemArgMapper aiSystemArgMapper;

  @Override
  @Transactional(readOnly = true)
  public List<AiSystemArgDO> getAiSystemArgList(Integer sysType) {
    return aiSystemArgMapper.selectList(new LambdaQueryWrapperX<AiSystemArgDO>().eqIfPresent(AiSystemArgDO::getSysType, sysType));
  }

  @Override
  public List<AiSystemArgDO> getAiSystemArgList(Integer sysType, Set<Long> ids) {
    return aiSystemArgMapper.selectList(
        new LambdaQueryWrapperX<AiSystemArgDO>().eqIfPresent(AiSystemArgDO::getSysType, sysType)
            .inIfPresent(AiSystemArgDO::getId, ids));
  }

}
