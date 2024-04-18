package net.hzxzkj.cxlaike.module.cxlaike.service.aitriallisten;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aitriallisten.AiTrialListenDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipStatus;

/**
 * ai语音试听功能 Service 接口
 *
 * @author 宵征源码
 */
public interface AiTrialListenService {

  /**
   * 创建ai语音试听功能
   *
   * @param createReqVO 创建信息
   * @return 编号
   */
  Long createAiTrialListen(@Valid AiTrialListenCreateReqVO createReqVO);

  /**
   * 获得ai语音试听功能
   *
   * @param id 编号
   * @return ai语音试听功能
   */
  AiTrialListenRespVO getAiTrialListen(Long id);

  void update(Long id, Integer status);

}
