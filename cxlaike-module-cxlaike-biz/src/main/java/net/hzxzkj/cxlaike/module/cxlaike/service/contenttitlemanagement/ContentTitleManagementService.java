package net.hzxzkj.cxlaike.module.cxlaike.service.contenttitlemanagement;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement.ContentTitleManagementDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 任务内容标题管理 Service 接口
 *
 * @author 宵征源码
 */
public interface ContentTitleManagementService {

    /**
     * 创建任务内容标题管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createContentTitleManagement(@Valid ContentTitleManagementCreateReqVO createReqVO);

    /**
     * 更新任务内容标题管理
     *
     * @param updateReqVO 更新信息
     */
    void updateContentTitleManagement(@Valid ContentTitleManagementUpdateReqVO updateReqVO);

    /**
     * 删除任务内容标题管理
     *
     * @param id 编号
     */
    void deleteContentTitleManagement(Long id);

    /**
     * 获得任务内容标题管理
     *
     * @param id 编号
     * @return 任务内容标题管理
     */
    ContentTitleManagementDO getContentTitleManagement(Long id);

    /**
     * 获得任务内容标题管理列表
     *
     * @param ids 编号
     * @return 任务内容标题管理列表
     */
    List<ContentTitleManagementDO> getContentTitleManagementList(Collection<Long> ids);

    /**
     * 获得任务内容标题管理分页
     *
     * @param pageReqVO 分页查询
     * @return 任务内容标题管理分页
     */
    PageResult<ContentTitleManagementDO> getContentTitleManagementPage(ContentTitleManagementPageReqVO pageReqVO);

    /**
     * 获得任务内容标题管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 任务内容标题管理列表
     */
    List<ContentTitleManagementDO> getContentTitleManagementList(ContentTitleManagementExportReqVO exportReqVO);

}
