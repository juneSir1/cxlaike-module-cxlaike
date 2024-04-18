package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商户抖音分组管理 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AccountGroupExcelVO {

    @ExcelProperty("分组名称")
    private String name;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
