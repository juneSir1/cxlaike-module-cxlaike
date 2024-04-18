package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class AccountDyDataStatisticsExcelVO {

    @ExcelProperty("昵称")
    private String dyNickname;

    @ExcelProperty("新增内容发布数")
    private Integer publishCountIncrement;

    @ExcelProperty("新增播放量")
    private Integer playCountIncrement;

    @ExcelProperty("新增粉丝数")
    private Integer fansCountIncrement;

    @ExcelProperty("新增点赞数")
    private Integer diggCountIncrement;

    @ExcelProperty("新增评论数")
    private Integer commentCountIncrement;

    @ExcelProperty("新增转发数")
    private Integer shareCountIncrement;

    @ExcelProperty("新增收藏数")
    private Integer collectCountIncrement;

    @ExcelProperty("总发布数")
    private Integer publishCount;

    @ExcelProperty("总粉丝数")
    private Integer fansCount;

    @ExcelProperty("总播放量")
    private Integer playCount;

}
