package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotaskcover;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskcover.AiVideoTaskCoverDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * ai素材混剪视频封面图片关联 Service 接口
 *
 * @author 宵征源码
 */
public interface AiVideoTaskCoverService {

    /**
     * 创建ai素材混剪视频封面图片关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAiVideoTaskCover(@Valid AiVideoTaskCoverCreateReqVO createReqVO);

    /**
     * 更新ai素材混剪视频封面图片关联
     *
     * @param updateReqVO 更新信息
     */
    void updateAiVideoTaskCover(@Valid AiVideoTaskCoverUpdateReqVO updateReqVO);

    /**
     * 删除ai素材混剪视频封面图片关联
     *
     * @param id 编号
     */
    void deleteAiVideoTaskCover(Long id);

    /**
     * 获得ai素材混剪视频封面图片关联
     *
     * @param id 编号
     * @return ai素材混剪视频封面图片关联
     */
    AiVideoTaskCoverDO getAiVideoTaskCover(Long id);

    /**
     * 获得ai素材混剪视频封面图片关联列表
     *
     * @param ids 编号
     * @return ai素材混剪视频封面图片关联列表
     */
    List<AiVideoTaskCoverDO> getAiVideoTaskCoverList(Collection<Long> ids);

    /**
     * 获得ai素材混剪视频封面图片关联分页
     *
     * @param pageReqVO 分页查询
     * @return ai素材混剪视频封面图片关联分页
     */
    PageResult<AiVideoTaskCoverDO> getAiVideoTaskCoverPage(AiVideoTaskCoverPageReqVO pageReqVO);

    /**
     * 获得ai素材混剪视频封面图片关联列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ai素材混剪视频封面图片关联列表
     */
    List<AiVideoTaskCoverDO> getAiVideoTaskCoverList(AiVideoTaskCoverExportReqVO exportReqVO);

}
