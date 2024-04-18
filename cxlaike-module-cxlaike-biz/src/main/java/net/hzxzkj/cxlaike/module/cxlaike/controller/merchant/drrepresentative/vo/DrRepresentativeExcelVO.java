package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 达人探店代 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class DrRepresentativeExcelVO {

    @ExcelProperty("平台类型 1-抖音")
    private Integer platformType;

    @ExcelProperty("昵称")
    private String nickname;

    @ExcelProperty("粉丝数")
    private String fansCount;

    @ExcelProperty("销售额")
    private String sales;

    @ExcelProperty("带货等级")
    private String sellGrade;

    @ExcelProperty("内容力")
    private String contentGrade;

    @ExcelProperty("信用分")
    private String creditScore;

    @ExcelProperty("行业")
    private String profession;

    @ExcelProperty("用户信息")
    private String userInfo;

    @ExcelProperty("城市")
    private String city;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
