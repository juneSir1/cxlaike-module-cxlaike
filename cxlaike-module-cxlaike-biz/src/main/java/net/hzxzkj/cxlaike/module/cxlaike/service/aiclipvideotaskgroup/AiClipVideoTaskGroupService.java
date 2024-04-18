package net.hzxzkj.cxlaike.module.cxlaike.service.aiclipvideotaskgroup;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup.AiClipVideoTaskGroupDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * ai素材混剪视频任务-视频组设置 Service 接口
 *
 * @author 宵征源码
 */
public interface AiClipVideoTaskGroupService {

    /**
     * 创建ai素材混剪视频任务-视频组设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAiClipVideoTaskGroup(@Valid AiClipVideoTaskGroupCreateReqVO createReqVO);

    /**
     * 更新ai素材混剪视频任务-视频组设置
     *
     * @param updateReqVO 更新信息
     */
    void updateAiClipVideoTaskGroup(@Valid AiClipVideoTaskGroupUpdateReqVO updateReqVO);

    /**
     * 删除ai素材混剪视频任务-视频组设置
     *
     * @param id 编号
     */
    void deleteAiClipVideoTaskGroup(Long id);

    /**
     * 获得ai素材混剪视频任务-视频组设置
     *
     * @param id 编号
     * @return ai素材混剪视频任务-视频组设置
     */
    AiClipVideoTaskGroupDO getAiClipVideoTaskGroup(Long id);

    /**
     * 获得ai素材混剪视频任务-视频组设置列表
     *
     * @param ids 编号
     * @return ai素材混剪视频任务-视频组设置列表
     */
    List<AiClipVideoTaskGroupDO> getAiClipVideoTaskGroupList(Collection<Long> ids);

    /**
     * 获得ai素材混剪视频任务-视频组设置分页
     *
     * @param pageReqVO 分页查询
     * @return ai素材混剪视频任务-视频组设置分页
     */
    PageResult<AiClipVideoTaskGroupDO> getAiClipVideoTaskGroupPage(AiClipVideoTaskGroupPageReqVO pageReqVO);

    /**
     * 获得ai素材混剪视频任务-视频组设置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ai素材混剪视频任务-视频组设置列表
     */
    List<AiClipVideoTaskGroupDO> getAiClipVideoTaskGroupList(AiClipVideoTaskGroupExportReqVO exportReqVO);

}
