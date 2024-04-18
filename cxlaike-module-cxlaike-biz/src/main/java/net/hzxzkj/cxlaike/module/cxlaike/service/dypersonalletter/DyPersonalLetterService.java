package net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletter;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.douyin.vo.DealWebhooksReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter.DyPersonalLetterDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 抖音私信管理 Service 接口
 *
 * @author 宵征源码
 */
public interface DyPersonalLetterService {

    /**
     * 创建抖音私信管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDyPersonalLetter(@Valid DyPersonalLetterCreateReqVO createReqVO);

    /**
     * 更新抖音私信管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDyPersonalLetter(@Valid DyPersonalLetterUpdateReqVO updateReqVO);

    /**
     * 删除抖音私信管理
     *
     * @param id 编号
     */
    void deleteDyPersonalLetter(Long id);

    /**
     * 获得抖音私信管理
     *
     * @param id 编号
     * @return 抖音私信管理
     */
    DyPersonalLetterDO getDyPersonalLetter(Long id);

    /**
     * 获得抖音私信管理列表
     *
     * @param ids 编号
     * @return 抖音私信管理列表
     */
    List<DyPersonalLetterRespVO> getDyPersonalLetterList(String conversationId);

    /**
     * 获得抖音私信管理分页
     *
     * @param pageReqVO 分页查询
     * @return 抖音私信管理分页
     */
    PageResult<DyPersonalLetterDO> getDyPersonalLetterPage(DyPersonalLetterPageReqVO pageReqVO);

    /**
     * 获得抖音私信管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 抖音私信管理列表
     */
    List<DyPersonalLetterDO> getDyPersonalLetterList(DyPersonalLetterExportReqVO exportReqVO);

    void createPersonalLetter(DealWebhooksReqVO reqVO);

}
