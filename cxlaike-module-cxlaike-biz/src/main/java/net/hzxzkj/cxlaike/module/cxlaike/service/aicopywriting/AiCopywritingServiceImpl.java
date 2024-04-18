package net.hzxzkj.cxlaike.module.cxlaike.service.aicopywriting;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.profession.ProfessionDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting.AiStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting.AiContentTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting.AiQuantityRestrictEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting.AiWordsCountRestrictEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.aicopywritingrecord.AiCopywritingRecordService;
import net.hzxzkj.cxlaike.module.cxlaike.service.profession.ProfessionService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.AzureOpenAIUtil;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.config.WryProperties;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageRespDTO;
import net.hzxzkj.cxlaike.module.merchant.api.user.MerchantUserApi;
import net.hzxzkj.cxlaike.module.pay.api.wallet.WalletApi;
import net.hzxzkj.cxlaike.module.pay.api.wallet.dto.WalletDTO;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletLogTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywriting.AiCopywritingDO;

import net.hzxzkj.cxlaike.module.cxlaike.convert.aicopywriting.AiCopywritingConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aicopywriting.AiCopywritingMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * ai文案 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
@Slf4j
public class AiCopywritingServiceImpl implements AiCopywritingService {

    @Resource
    private AiCopywritingMapper aiCopywritingMapper;
    @Resource
    private AzureOpenAIUtil azureOpenAIUtil;
    @Resource
    private ProfessionService professionService;
    @Resource
    private WryProperties wryProperties;
    @Resource
    private AiCopywritingRecordService recordService;
    @Resource
    private WalletApi walletApi;

//    @Override
//    public List<String> createAiCopywriting(AiCopywritingCreateReqVO createReqVO) {
//        Long userId = SecurityFrameworkUtils.getLoginUserId();
//        // 1.校验金币
//        this.checkGold(userId);
//        AiCopywritingDO aiCopywriting = AiCopywritingConvert.INSTANCE.convert(createReqVO);
//        //行业数据
//        if(StringUtils.isNotBlank(createReqVO.getProfessionCode())){
//            ProfessionDO professionDO = professionService.getByCode(createReqVO.getProfessionCode());
//            if (professionDO == null) {
//                throw exception(DATA_ERROR);
//            }
//            aiCopywriting.setProfessionName(professionDO.getName());
//            createReqVO.setProfessionName(professionDO.getName());
//        }
//        Integer aiStatus = AiStatusEnum.FAIL.getStatus();
//        String aiContent = "生成失败...";
//        Integer wordsCount = createReqVO.getWordsCount();
//        Integer quantity = createReqVO.getQuantity();
//        if (!AiWordsCountRestrictEnum.TWENTY.getCode().equals(createReqVO.getWordsCountRestrict())) {
//            wordsCount = AiWordsCountRestrictEnum.getEnumByCode(createReqVO.getWordsCountRestrict()).getCount();
//        }
//        if (!AiQuantityRestrictEnum.FIFTEEN.getCode().equals(createReqVO.getQuantityRestrict())) {
//            quantity = AiQuantityRestrictEnum.getEnumByCode(createReqVO.getQuantityRestrict()).getCount();
//        }
//        String contentTypeStr = AiContentTypeEnum.getEnumByCode(createReqVO.getContentType()).getContent();
//        // ai描述
//        String content = this.getContent(createReqVO, wordsCount, quantity, contentTypeStr);
//
//        Long promptTokens = 0L;
//        Long completionTokens = 0L;
//        List<String> aiContents = new ArrayList<>();
//        for(int i = 1;i <= quantity; i++ ){
//            //生成ai文案
//            MessageRespDTO messageRespDTO = this.generateAiCopywriting(content);
//            if (messageRespDTO != null) {
//                messageRespDTO.setInputContent(content);
//                if(messageRespDTO.getChoices() != null && messageRespDTO.getChoices().size() > 0){
//                    aiStatus = AiStatusEnum.COMPLETE.getStatus();
//                    aiContent = messageRespDTO.getChoices().get(0).getMessage().getContent();
//                    aiCopywriting.setAiCopywriting(aiContent);
//                    aiCopywriting.setExpendQuantity(messageRespDTO.getExpendQuantity());
//
//                    //amount = new BigDecimal(messageRespDTO.getUsage().getTotal_tokens()).divide(new BigDecimal(500)).setScale(0, RoundingMode.CEILING).longValue();
//                    promptTokens = messageRespDTO.getUsage().getPrompt_tokens();
//                    completionTokens = completionTokens + messageRespDTO.getUsage().getCompletion_tokens();
//                }else if(messageRespDTO.getError() != null){
//                    aiContent = messageRespDTO.getError().getMessage();
//                    aiCopywriting.setAiCopywriting(aiContent);
//                }
//            }
//            aiCopywriting.setAiStatus(aiStatus);
//            aiCopywritingMapper.insert(aiCopywriting);
//            //插入记录
//            if(messageRespDTO != null){
//                recordService.createAiCopywritingRecord(messageRespDTO,aiCopywriting.getId(),1);
//            }
//            aiContents.add(aiContent);
//        }
//        // 扣除金币数
//        Long amount = new BigDecimal(promptTokens+completionTokens).divide(new BigDecimal(500)).setScale(0, RoundingMode.CEILING).longValue();
//        //成功之后扣金币
//        if(AiStatusEnum.COMPLETE.getStatus().equals(aiStatus)){
//            this.deductGold(userId,amount,aiCopywriting.getId());
//        }
//        return aiContents;
//    }

    @Override
    public String createAiCopywriting(AiCopywritingCreateReqVO createReqVO) {
        Long userId =  TenantContextHolder.getTenantId();
        // 1.校验金币
        this.checkGold(userId);
        AiCopywritingDO aiCopywriting = AiCopywritingConvert.INSTANCE.convert(createReqVO);
        //行业数据
        if(StringUtils.isNotBlank(createReqVO.getProfessionCode())){
            ProfessionDO professionDO = professionService.getByCode(createReqVO.getProfessionCode());
            if (professionDO == null) {
                throw exception(DATA_ERROR);
            }
            aiCopywriting.setProfessionName(professionDO.getName());
            createReqVO.setProfessionName(professionDO.getName());
        }
        Integer aiStatus = AiStatusEnum.FAIL.getStatus();
        String aiContent = "生成失败...";
        Integer wordsCount = createReqVO.getWordsCount();
        Integer quantity = createReqVO.getQuantity();
        //暂时不要枚举了 @update by june
//        if (!AiWordsCountRestrictEnum.TWENTY.getCode().equals(createReqVO.getWordsCountRestrict())) {
//            wordsCount = AiWordsCountRestrictEnum.getEnumByCode(createReqVO.getWordsCountRestrict()).getCount();
//        }
//        if (!AiQuantityRestrictEnum.FIFTEEN.getCode().equals(createReqVO.getQuantityRestrict())) {
//            quantity = AiQuantityRestrictEnum.getEnumByCode(createReqVO.getQuantityRestrict()).getCount();
//        }
//        String contentTypeStr = AiContentTypeEnum.getEnumByCode(createReqVO.getContentType()).getContent();
        // ai描述
        String content = this.getContent(createReqVO, wordsCount, quantity);
        //生成ai文案
        MessageRespDTO messageRespDTO = this.generateAiCopywriting(content);
        Long amount = 0L;
        if (messageRespDTO != null) {
            messageRespDTO.setInputContent(content);
            if(messageRespDTO.getChoices() != null && messageRespDTO.getChoices().size() > 0){
                aiStatus = AiStatusEnum.COMPLETE.getStatus();
                aiContent = messageRespDTO.getChoices().get(0).getMessage().getContent();
                aiCopywriting.setAiCopywriting(aiContent);
                aiCopywriting.setExpendQuantity(messageRespDTO.getUsage().getTotal_tokens());
                // 扣除金币数
                amount = new BigDecimal(messageRespDTO.getUsage().getTotal_tokens()).divide(new BigDecimal(500)).setScale(0, RoundingMode.CEILING).longValue();
                aiCopywriting.setExpendGold(amount);
            }else if(messageRespDTO.getError() != null){
                aiContent = messageRespDTO.getError().getMessage();
                aiCopywriting.setAiCopywriting(aiContent);
            }
        }
        aiCopywriting.setAiStatus(aiStatus);
        aiCopywritingMapper.insert(aiCopywriting);
        //插入记录
        if(messageRespDTO != null){
            recordService.createAiCopywritingRecord(messageRespDTO,aiCopywriting.getId(),1);
        }
        //成功之后扣金币
        if(AiStatusEnum.COMPLETE.getStatus().equals(aiStatus)){
            this.deductGold(userId,amount,aiCopywriting.getId());
        }
        return aiContent;
    }

    private String getContent(AiCopywritingCreateReqVO createReqVO, Integer wordsCount, Integer quantity) {
        String content="";
        if(AiContentTypeEnum.WA.getCode().equals(createReqVO.getContentType())){
            if(wordsCount==1){
                wordsCount = 50;//如果是1，默认50个字
            }
            if(StringUtils.isBlank(createReqVO.getReferenceCopy())){
                content = String.format(wryProperties.getGenerateContentTwo(),
                        createReqVO.getContentDesc(), createReqVO.getContentKeyword(),
                        quantity, wordsCount);
            }else {
                content = String.format(wryProperties.getGenerateContentSeven(),
                        createReqVO.getContentDesc(), createReqVO.getContentKeyword(),
                        quantity, wordsCount,createReqVO.getReferenceCopy());
            }
        }else if(AiContentTypeEnum.NORMAL.getCode().equals(createReqVO.getContentType())){
            content = createReqVO.getContentDesc();
        }else if(AiContentTypeEnum.LB.getCode().equals(createReqVO.getContentType())){
            content = String.format(wryProperties.getGenerateContentThree(),
                    createReqVO.getContentDesc(), quantity);
        }else if(AiContentTypeEnum.CT.getCode().equals(createReqVO.getContentType())){
            if(wordsCount==1){
                wordsCount = 30;//如果是1，默认30个字
            }
            content = String.format(wryProperties.getGenerateContentFour(),
                     createReqVO.getContentDesc(), quantity,wordsCount);
        }else if(AiContentTypeEnum.JB.getCode().equals(createReqVO.getContentType())){
            if(wordsCount==1){
                wordsCount = 100;//如果是1，默认100个字
            }
            content = String.format(wryProperties.getGenerateContentOne(),
                    createReqVO.getContentDesc(), quantity, wordsCount);
        }
        return content;
    }

    @Override
    public void updateAiCopywriting(AiCopywritingUpdateReqVO updateReqVO) {
        // 校验存在
        validateAiCopywritingExists(updateReqVO.getId());
        // 更新
        AiCopywritingDO updateObj = AiCopywritingConvert.INSTANCE.convert(updateReqVO);
        aiCopywritingMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiCopywriting(Long id) {
        // 校验存在
        validateAiCopywritingExists(id);
        // 删除
        aiCopywritingMapper.deleteById(id);
    }

    private void validateAiCopywritingExists(Long id) {
        if (aiCopywritingMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public AiCopywritingRespVO getAiCopywriting(Long id) {
        AiCopywritingDO aiCopywritingDO = aiCopywritingMapper.selectById(id);
        if (aiCopywritingDO == null) {
            throw exception(DATA_ERROR);
        }
        AiCopywritingRespVO convert = AiCopywritingConvert.INSTANCE.convert(aiCopywritingDO);
        if(StringUtils.isNotBlank(aiCopywritingDO.getProfessionCode())){
            String[] codes = professionService.getCodesByCode(aiCopywritingDO.getProfessionCode());
            convert.setProfessionCodes(codes);
        }
        return convert;
    }

    @Override
    public List<AiCopywritingRespVO> getAiCopywritingList() {
        List<AiCopywritingDO> aiCopywritingDOS = aiCopywritingMapper.selectList(new LambdaQueryWrapper<AiCopywritingDO>()
                    .eq(AiCopywritingDO::getAiStatus,AiStatusEnum.COMPLETE.getStatus()));
        return AiCopywritingConvert.INSTANCE.convertList(aiCopywritingDOS);
    }

    @Override
    public PageResult<AiCopywritingDO> getAiCopywritingPage(AiCopywritingPageReqVO pageReqVO) {
        return aiCopywritingMapper.selectPage(pageReqVO);
    }


    private MessageRespDTO generateAiCopywriting(String content) {
        List<MessageDTO> messageDTOS = new ArrayList<>();
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setRole("user");
        messageDTO.setContent(content);
        messageDTOS.add(messageDTO);
        MessageRespDTO completions = azureOpenAIUtil.completions(messageDTOS);
        if (completions == null || completions.getError() != null) {
            return completions;
        }
        //消耗数量
        completions.setExpendQuantity(completions.getUsage().getTotal_tokens());
        return completions;
    }

    @Override
    public String optimizeCopywriting(String content,String code) {
        Long userId =  TenantContextHolder.getTenantId();
        // 1.校验金币
        this.checkGold(userId);
        String contentStr = String.format(wryProperties.getOptimizeContent(),content);
        // 2.调用GPT
        MessageRespDTO messageRespDTO = this.generateAiCopywriting(contentStr);
        Long id = null;
        if(StringUtils.isNotBlank(code)){
            id = Long.valueOf(code);
        }
        //插入记录
        if(messageRespDTO != null){
            recordService.createAiCopywritingRecord(messageRespDTO,id,2);
        }
        String aiContent = "生成失败...";
        if (messageRespDTO != null) {
            if(messageRespDTO.getChoices() != null && messageRespDTO.getChoices().size() > 0){
                aiContent = messageRespDTO.getChoices().get(0).getMessage().getContent();
                Long amount = new BigDecimal(messageRespDTO.getUsage().getTotal_tokens()).divide(new BigDecimal(500)).setScale(0, RoundingMode.CEILING).longValue();
                // 3.扣金币
                this.deductGold(userId,amount,id);
            }else if(messageRespDTO.getError() != null){
                aiContent = messageRespDTO.getError().getMessage();
            }
        }
        return aiContent;
    }
    /**
     * 校验用户金币余额
     * @author hjn
     * @date 2023/9/18 下午4:19
     * @param userId
     */
    private void checkGold(Long userId){
        WalletDTO walletDTO = walletApi.get(userId, WalletTypeEnum.GOLD);
        if(walletDTO.getStatus() != 1){
            throw exception(new ErrorCode(400,"钱包异常"));
        }
        if(walletDTO.getBalance() <= 0){
            throw exception(new ErrorCode(400,"金币余额不足"));
        }
    }
    /**
     * 扣金币
     * @author hjn
     * @date 2023/9/18 下午4:19
     * @param userId
     */
    private void deductGold(Long userId,Long amount,Long id){
        walletApi.deduct(userId,amount, WalletLogTypeEnum.AI_COPYWRITING_EXPEND,id,"",WalletLogTypeEnum.AI_COPYWRITING_EXPEND.getName());
    }

}
