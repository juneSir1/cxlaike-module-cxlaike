package net.hzxzkj.cxlaike.module.cxlaike.service.aicopywritingrecord;

import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageRespDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywritingrecord.AiCopywritingRecordDO;

import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aicopywritingrecord.AiCopywritingRecordMapper;


/**
 * ai文案记录 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiCopywritingRecordServiceImpl implements AiCopywritingRecordService {

    @Resource
    private AiCopywritingRecordMapper aiCopywritingRecordMapper;

    @Override
    public Long createAiCopywritingRecord(MessageRespDTO messageRespDTO,Long id,Integer type) {
        // 插入
        AiCopywritingRecordDO aiCopywritingRecord = new AiCopywritingRecordDO();
        aiCopywritingRecord.setReturnId(messageRespDTO.getId());
        aiCopywritingRecord.setBId(id);
        aiCopywritingRecord.setType(type);
        if(messageRespDTO.getUsage()!= null){
            aiCopywritingRecord.setPromptTokens(messageRespDTO.getUsage().getPrompt_tokens());
            aiCopywritingRecord.setCompletionTokens(messageRespDTO.getUsage().getCompletion_tokens());
        }
        if(messageRespDTO.getChoices() != null && messageRespDTO.getChoices().size()>0){
            aiCopywritingRecord.setOutputContent(messageRespDTO.getChoices().get(0).getMessage().getContent());
            aiCopywritingRecord.setFinishReason(messageRespDTO.getChoices().get(0).getFinish_reason());
        }
        aiCopywritingRecord.setInputContent(messageRespDTO.getInputContent());
        aiCopywritingRecord.setReturnBody(JsonUtils.toJsonString(messageRespDTO));
        aiCopywritingRecordMapper.insert(aiCopywritingRecord);
        // 返回
        return aiCopywritingRecord.getId();
    }



}
