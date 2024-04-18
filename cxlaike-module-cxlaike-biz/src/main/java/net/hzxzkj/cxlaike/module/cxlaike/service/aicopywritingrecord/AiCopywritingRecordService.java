package net.hzxzkj.cxlaike.module.cxlaike.service.aicopywritingrecord;

import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageRespDTO;


/**
 * ai文案记录 Service 接口
 *
 * @author 宵征源码
 */
public interface AiCopywritingRecordService {

    /**
     * 创建ai文案记录
     *
     * @param messageRespDTO 创建信息
     * @param id 业务id
     * @param type 业务类型 1-ai生成文案 2-ai优化文案
     * @return 编号
     */
    Long createAiCopywritingRecord(MessageRespDTO messageRespDTO,Long id,Integer type);


}
