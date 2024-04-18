package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.diboot.core.binding.annotation.BindField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@Data
public class VideoDyDataStatisticsAnalyzeExcelVO {

    @ExcelProperty("昵称")
    private String dyNickname;

    @ExcelProperty("定位")
    private String poiName;

    @ExcelProperty("发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime publishTime;

    @ExcelProperty("新增播放")
    private Integer playCountIncrement;

    @ExcelProperty("新增点赞")
    private Integer diggCountIncrement;

    @ExcelProperty("新增评论")
    private Integer commentCountIncrement;

    @ExcelProperty("新增转发")
    private Integer shareCountIncrement;

    @ExcelProperty("新增收藏")
    private Integer collectCountIncrement;

    @ExcelProperty("总播放量")
    private Integer playCount;

    @ExcelProperty("30天平均播放时长")
    private Double playDuration;

    @ExcelProperty("视频链接")
    private String videoLink;
}
