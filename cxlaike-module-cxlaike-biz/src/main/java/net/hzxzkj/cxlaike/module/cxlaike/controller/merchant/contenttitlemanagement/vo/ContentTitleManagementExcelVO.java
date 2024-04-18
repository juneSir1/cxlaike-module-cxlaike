package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 任务内容标题管理 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class ContentTitleManagementExcelVO {

    @ExcelProperty("任务id")
    private Long taskId;

    @ExcelProperty("视频组id")
    private Long videoGroupId;

    @ExcelProperty("口播文案")
    private String contentTitle;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
