package net.hzxzkj.cxlaike.module.cxlaike.service.dycomment;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dycomment.DyCommentDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 抖音评论管理 Service 接口
 *
 * @author 宵征源码
 */
public interface DyCommentService {

    /**
     * 发布新的评论
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    void createDyComment(@Valid DyCommentCreateReqVO createReqVO);

    /**
     * 更新抖音评论管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDyComment(@Valid DyCommentUpdateReqVO updateReqVO);

    /**
     * 删除抖音评论管理
     *
     * @param id 编号
     */
    void deleteDyComment(Long id);

    /**
     * 获得抖音评论管理
     *
     * @param id 编号
     * @return 抖音评论管理
     */
    DyCommentDO getDyComment(Long id);

    /**
     * 获得抖音评论管理列表
     *
     * @param ids 编号
     * @return 抖音评论管理列表
     */
    List<DyCommentDO> getDyCommentList(Collection<Long> ids);

    /**
     * 获得抖音评论管理分页
     *
     * @param pageReqVO 分页查询
     * @return 抖音评论管理分页
     */
    PageResult<DyCommentDO> getDyCommentPage(DyCommentPageReqVO pageReqVO);

    /**
     * 获得抖音评论管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 抖音评论管理列表
     */
    List<DyCommentDO> getDyCommentList(DyCommentExportReqVO exportReqVO);

    void commentTop(DyCommentTopReqVO topReqVO);
    void commentReply(DyCommentReplyReqVO replyReqVO);

    void pullCommentList(Long tenantId);

}
