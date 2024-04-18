package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ai素材混剪视频封面图片关联 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class AiVideoTaskCoverExcelVO {

    @ExcelProperty("ai素材混剪任务id")
    private Long taskId;

    @ExcelProperty("封面图片文件id")
    private Long fileId;

    @ExcelProperty("封面url")
    private String url;

    @ExcelProperty("排序")
    private Integer sort;

}
