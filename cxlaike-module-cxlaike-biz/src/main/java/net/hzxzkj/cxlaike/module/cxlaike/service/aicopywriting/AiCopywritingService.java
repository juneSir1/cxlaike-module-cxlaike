package net.hzxzkj.cxlaike.module.cxlaike.service.aicopywriting;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywriting.AiCopywritingDO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageRespDTO;

/**
 * ai文案 Service 接口
 *
 * @author 宵征源码
 */
public interface AiCopywritingService {

    /**
     * 创建ai文案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createAiCopywriting(@Valid AiCopywritingCreateReqVO createReqVO);

    /**
     * 更新ai文案
     *
     * @param updateReqVO 更新信息
     */
    void updateAiCopywriting(@Valid AiCopywritingUpdateReqVO updateReqVO);

    /**
     * 删除ai文案
     *
     * @param id 编号
     */
    void deleteAiCopywriting(Long id);

    /**
     * 获得ai文案
     *
     * @param id 编号
     * @return ai文案
     */
    AiCopywritingRespVO getAiCopywriting(Long id);

    /**
     * 获得ai文案列表
     *
     * @param ids 编号
     * @return ai文案列表
     */
    List<AiCopywritingRespVO> getAiCopywritingList();

    /**
     * 获得ai文案分页
     *
     * @param pageReqVO 分页查询
     * @return ai文案分页
     */
    PageResult<AiCopywritingDO> getAiCopywritingPage(AiCopywritingPageReqVO pageReqVO);

    String optimizeCopywriting(String content,String code);


}
