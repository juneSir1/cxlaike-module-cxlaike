package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ai视频配置 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AiVideoConfigExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("类型(1.AaiMotionInEffect,2.SubType,3.Fonts,4.Keywords,5.EffectColorStyle,6.Colors,7.Symbol)")
    private Integer type;

    @ExcelProperty("内容值")
    private String value;

    @ExcelProperty("0.关闭,1.开启)")
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
