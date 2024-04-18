package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 商家任务与素材关联 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class TaskMaterialCorrelationExcelVO {

    @ExcelProperty("任务类型 1-实探 2-云探 3-矩阵")
    private Integer taskType;

    @ExcelProperty("平台类型 1-抖音")
    private Integer platformType;

    @ExcelProperty("商家任务id")
    private Long taskId;

    @ExcelProperty("素材id")
    private Long materialId;

    @ExcelProperty("任务状态 1-未开始 2-进行中 3-已结束 4-终止")
    private Integer taskStatus;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
