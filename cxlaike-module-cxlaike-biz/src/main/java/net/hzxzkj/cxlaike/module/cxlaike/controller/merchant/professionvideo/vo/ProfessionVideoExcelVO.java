package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 精选行业视频 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class ProfessionVideoExcelVO {

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("播放量")
    private String playCount;

    @ExcelProperty("封面url")
    private String coverUrl;

    @ExcelProperty("视频url")
    private String videoUrl;

    @ExcelProperty("时长")
    private String duration;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
