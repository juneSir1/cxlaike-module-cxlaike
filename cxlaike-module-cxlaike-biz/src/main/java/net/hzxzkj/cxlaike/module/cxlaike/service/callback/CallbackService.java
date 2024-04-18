package net.hzxzkj.cxlaike.module.cxlaike.service.callback;

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteVO;

/**
 * 描   述: 回调服务
 *
 * @author ace
 * @version [版本号, 2023/9/19]
 * @see [相关类/方法]
 * 创建日期: 2023/9/19
 */
public interface CallbackService {

  void aiVideoCallback(ProduceMediaCompleteVO complete);
}
