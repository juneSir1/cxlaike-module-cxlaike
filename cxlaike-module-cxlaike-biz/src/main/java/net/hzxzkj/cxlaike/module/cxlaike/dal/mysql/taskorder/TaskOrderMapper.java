package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskorder;

import java.time.LocalDate;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.TaskOrderPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.TaskOrderRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.TaskOrderAuditListRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.TaskOrderAuditListReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.TaskOrderDetailListReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.TaskOrderDetailListRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 达人任务订单 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskOrderMapper extends BaseMapperX<TaskOrderDO> {


    List<TaskOrderAuditListRespVO> selectListForAudit(Page page, @Param("form") TaskOrderAuditListReqVO reqVO);

    List<TaskOrderDetailListRespVO> selectListForDetail(Page page, @Param("form") TaskOrderDetailListReqVO reqVO);

    List<TaskOrderRespVO> selectListForApp(Page page, @Param("form") TaskOrderPageReqVO reqVO);

    Integer selectListCountForApp(@Param("userId") Long userId, @Param("status") Integer status);

    Long selectCountForAudit(@Param("status") Integer status);

    Long selectTaskEarningsOfToday(@Param("userId") Long userId,@Param("date") LocalDate date);

    Long selectTaskEarningsOfBeforeToDay(@Param("userId") Long userId,@Param("date") LocalDate date);

    Long selectTaskEarningsOfPredict(@Param("userId") Long userId);

    Long selectTaskCountOfAll(@Param("userId")Long userId);

    Long selectTaskCountOfMonth(@Param("userId") Long userId,@Param("date") LocalDate date);
}
