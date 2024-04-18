package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 用户视频集合 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class TaskOrderGatherExcelVO {

    @ExcelProperty("类型 1-达人 2-商家")
    private Integer orderType;

    @ExcelProperty("任务id")
    private Long taskId;

    @ExcelProperty("封面id(素材id)")
    private Long orderId;

    @ExcelProperty("用户绑定id(抖音)")
    private Long accountBindId;

    @ExcelProperty("外部视频id(抖音)")
    private String videoId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
