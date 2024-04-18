package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ai素材混剪视频任务-视频组设置 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AiClipVideoTaskGroupExcelVO {

    @ExcelProperty("ai视频任务主键")
    private Long aiTaskId;

    @ExcelProperty("内容标题对象")
    private Long contentTitleFontId;

    @ExcelProperty("内容标题对象")
    private Long copywritingFontId;

    @ExcelProperty("视频组名")
    private String name;

    @ExcelProperty("视频组时长(秒)")
    private Integer totalDuration;

    @ExcelProperty("序号")
    private Integer sort;

    @ExcelProperty("视频组数量")
    private Integer num;

    @ExcelProperty("是否原声")
    private Boolean original;

    @ExcelProperty("时长设置类型")
    private Integer durationType;

    @ExcelProperty("时长设置-时长")
    private Integer duration;

    @ExcelProperty("视频状态")
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
