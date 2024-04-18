package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 客户案例 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class ClientCaseExcelVO {

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("标签")
    private String label;

    @ExcelProperty("封面url")
    private String coverUrl;

    @ExcelProperty("视频url")
    private String videoUrl;

    @ExcelProperty("时长")
    private String content;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
