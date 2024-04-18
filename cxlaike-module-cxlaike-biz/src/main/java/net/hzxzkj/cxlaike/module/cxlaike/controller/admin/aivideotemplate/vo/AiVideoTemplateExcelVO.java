package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ai视频模板 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AiVideoTemplateExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("类型")
    private Integer type;

    @ExcelProperty("内容值")
    private String value;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
