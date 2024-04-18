package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ai视频任务-文字设置 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AiVideoTaskFontSetExcelVO {

    @ExcelProperty("ai视频任务id")
    private Long aiTaskId;

    @ExcelProperty("内容值")
    private String value;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
