package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 抖音私信用户管理 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class DyPersonalLetterUserExcelVO {

    @ExcelProperty("用户open_id")
    private String openId;

    @ExcelProperty("昵称")
    private String nickName;

    @ExcelProperty("头像")
    private String avatar;

    @ExcelProperty("姓名")
    private String realName;

    @ExcelProperty("省code")
    private Integer provinceCode;

    @ExcelProperty("省")
    private String provinceName;

    @ExcelProperty("市code")
    private Integer cityCode;

    @ExcelProperty("市")
    private String cityName;

    @ExcelProperty("区code")
    private Integer countyCode;

    @ExcelProperty("区")
    private String countyName;

    @ExcelProperty("状态 0-未沟通 1-已沟通 2-无意向 3-有意向 4-已合作")
    private Integer communicationStatus;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("微信")
    private String wx;

    @ExcelProperty("公司")
    private String company;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
