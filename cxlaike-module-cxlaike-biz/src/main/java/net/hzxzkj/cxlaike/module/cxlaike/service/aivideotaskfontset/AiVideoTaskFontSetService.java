package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotaskfontset;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset.AiVideoTaskFontSetDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * ai视频任务-文字设置 Service 接口
 *
 * @author 宵征源码
 */
public interface AiVideoTaskFontSetService {

    /**
     * 创建ai视频任务-文字设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAiVideoTaskFontSet(@Valid AiVideoTaskFontSetCreateReqVO createReqVO);

    /**
     * 更新ai视频任务-文字设置
     *
     * @param updateReqVO 更新信息
     */
    void updateAiVideoTaskFontSet(@Valid AiVideoTaskFontSetUpdateReqVO updateReqVO);

    /**
     * 删除ai视频任务-文字设置
     *
     * @param id 编号
     */
    void deleteAiVideoTaskFontSet(Long id);

    /**
     * 获得ai视频任务-文字设置
     *
     * @param id 编号
     * @return ai视频任务-文字设置
     */
    AiVideoTaskFontSetDO getAiVideoTaskFontSet(Long id);

    /**
     * 获得ai视频任务-文字设置列表
     *
     * @param ids 编号
     * @return ai视频任务-文字设置列表
     */
    List<AiVideoTaskFontSetDO> getAiVideoTaskFontSetList(Collection<Long> ids);

    /**
     * 获得ai视频任务-文字设置分页
     *
     * @param pageReqVO 分页查询
     * @return ai视频任务-文字设置分页
     */
    PageResult<AiVideoTaskFontSetDO> getAiVideoTaskFontSetPage(AiVideoTaskFontSetPageReqVO pageReqVO);

    /**
     * 获得ai视频任务-文字设置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ai视频任务-文字设置列表
     */
    List<AiVideoTaskFontSetDO> getAiVideoTaskFontSetList(AiVideoTaskFontSetExportReqVO exportReqVO);

}
