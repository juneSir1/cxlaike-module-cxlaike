package net.hzxzkj.cxlaike.module.cxlaike.service.video;


import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipBase;

/**
 * 描   述:视频剪辑服务接口
 *
 * @author ace
 * @version [版本号, 2023/10/8]
 * @see [相关类/方法]
 * 创建日期: 2023/10/8
 */
public interface VideoClipService {

  VideoClipOrderDO getVideoClipOrder(Long id);

  void callback(ProduceMediaCompleteVO complete);

  void checkDeal() throws Exception;

}
