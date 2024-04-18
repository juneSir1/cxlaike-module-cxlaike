package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ai素材混剪视频模板 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class TemplateExcelVO {

    @ExcelProperty("ai视频任务id")
    private Long aiTaskId;

    @ExcelProperty("模板名称")
    private String tempName;

    @ExcelProperty("mediaURL")
    private String videoUrl;

    @ExcelProperty("视频结果id")
    private Long videoId;

    @ExcelProperty("视频时长")
    private Integer duration;

    @ExcelProperty("画面比例类型")
    private Integer aspectRatioType;

    @ExcelProperty("视频段数")
    private Integer groupNum;

    @ExcelProperty("是否系统模板")
    private Byte isSystem;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("模板分类")
    private Integer tempIndustry;

}
