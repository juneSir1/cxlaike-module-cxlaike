package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 来客系统参数 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AiSystemArgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("系统参数类型")
    private Integer sysType;

    @ExcelProperty("具体类型")
    private Integer type;

    @ExcelProperty("名字")
    private String name;

    @ExcelProperty("描述")
    private String describe;

    @ExcelProperty("编号")
    private String code;

    @ExcelProperty("url")
    private String url;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
