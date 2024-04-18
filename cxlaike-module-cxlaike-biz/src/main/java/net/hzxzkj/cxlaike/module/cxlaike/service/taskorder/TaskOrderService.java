package net.hzxzkj.cxlaike.module.cxlaike.service.taskorder;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.*;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterial.TaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.CancelTypeEnum;

import java.time.LocalDate;
import java.util.List;
import net.hzxzkj.cxlaike.module.pay.api.walletorder.dto.UpMemberCashOrderDTO;

/**
 * 达人任务订单 Service 接口
 *
 * @author 宵征源码
 */
public interface TaskOrderService {

    /**
     * 个人取消订单
     *
     * @param id 订单id
     */
    void cancelOrder(Long id,Long memberId,String month);
//    /**
//     * 商家驳回
//     *
//     * @param reqVO 驳回参数
//     */
//    void turnDownOrder(TaskOrderTurnDownReqVO reqVO);
    /**
     * 达人回填抖音链接
     *
     * @param reqVO 驳回参数
     */
    void backFillVideoLink(TaskOrderBackFillReqVO reqVO);
    /**
     * 获得达人作品审核列表
     *
     * @param reqVO 查询条件
     * @return
     */
    PageResult<TaskOrderAuditListRespVO> getListForAudit(TaskOrderAuditListReqVO reqVO);
    TaskOrderAuditListCountRespVO getCountForAudit();
    /**
     * 商家审核
     *
     * @param reqVO
     */
    void auditOrder(TaskOrderAuditReqVO reqVO,Long memberId,LocalDate date,TaskOrderDO taskOrderDO);
    /**
     * 获得达人视频发布明细列表
     *
     * @param reqVO 查询条件
     * @return
     */
    PageResult<TaskOrderDetailListRespVO> getListForDetail(TaskOrderDetailListReqVO reqVO);

    /**
     * 系统取消订单
     *
     * @param id 订单id
     */
    void cancelOrderBySystem(Long id, CancelTypeEnum cancelTypeEnum,Long memberId,String month);
    /**
     * 系统结算(订单完成)
     *
     * @param id 订单id
     */
    void settleForTaskOrder(Long id,Long memberId,LocalDate date);

    /**
     * 系统审核
     *
     * @param id
     */
    void auditOrderBySystem(Long id,Long memberId,LocalDate date);

    PageResult<TaskOrderRespVO> getListForApp(TaskOrderPageReqVO reqVO);
    TaskOrderCountRespVO getListCountForApp(Long userId);

    /**
     * 删除订单
     *
     * @param id 订单id
     */
    void deleteTaskOrder(Long id,Long userId);

    List<TaskOrderDO> getListForTask();

    void updateById(TaskOrderDO taskOrderDO);

    /**
     * 系统取消订单(抖音数据问题)
     *
     * @param id 订单id
     */
    void cancelOrderByDy(Long id,String cancelReason);
    /**
     * 获取达人上个月取消订单次数
     *
     * @param memberId 用户id
     */
    long getLastMonthCancelCount(Long memberId,LocalDate date);

    /**
     * 用户接单
     */
    Long memberAcceptOrder(TaskOrderAcceptReqVO reqVO);

    /**
     * 获取达人当月取消订单次数
     *
     * @param memberId 用户id
     */
    long getMonthCancelCount(Long memberId,String month);

    /**
     * 处理达人接单支付结果
     * @param order
     */
    void processOrder(UpMemberCashOrderDTO order,LocalDate date);

    /**
     * 创建达人探店任务
     * @param taskOrderId
     * @throws Exception
     */
    void createMemberTaskVideo(Long taskOrderId) throws Exception;

    /**
     * 检查达人探店视频生成结果
     * @throws Exception
     */
    void checkTaskVideoResults()throws Exception;

    /**
     * 上传生成视频文件到oss
     * @param taskOrderId
     * @param oldMediaUrl
     * @throws Exception
     */
    void uploadFileTask(Long taskOrderId,String oldMediaUrl) throws Exception;

    /**
     * 处理数字人视频剪辑
     * @param taskOrderId
     * @throws Exception
     */
    void digitalClipHandle(Long taskOrderId) throws Exception;



    /**
     * 获取达人任务素材
     */
    List<TaskMaterialDO> getMemberTaskMaterial(Long taskId);

    List<TaskMaterialRespVO> getTaskMaterial(Long taskId,Long userId);

    List<TaskMaterialRespVO> getTaskMaterialOfDownload(Long taskId,Long userId);

    void materialDownload(Long taskMaterialId,Long userId);
    /**
     * 今日任务收益
     */
    long getTaskEarningsOfToday(Long memberId, LocalDate date);
    /**
     * 今日之前任务收益
     */
    long getTaskEarningsOfBeforeToDay(Long memberId, LocalDate date);

    /**
     * 预计任务收益
     */
    long getTaskEarningsOfPredict(Long memberId, LocalDate date);

    /**
     * 累计任务数量
     */
    long getTaskCountOfAll(Long memberId,LocalDate date);

    /**
     * 本月任务数量
     */
    long getTaskCountOfMonth(Long memberId,LocalDate date);

    TaskOrderPaymentResultRespVO getTaskOrderPaymentResult(Long payOrderId,Long userId);

    TaskOrderDO getById(Long id);

    void aiClipVideo(Long taskOrderId);

    void updateAiClipVideoStatus(Long materialId, Integer duration, Integer status);

    void updateDigitalClipStatus(Long materialId, Integer videoType, Integer duration, Integer status);

    void newDigitalClipHandle(Long materialId) throws Exception ;

    List<TaskOrderDO> getListForTaskId(List<Long> taskId);
}
