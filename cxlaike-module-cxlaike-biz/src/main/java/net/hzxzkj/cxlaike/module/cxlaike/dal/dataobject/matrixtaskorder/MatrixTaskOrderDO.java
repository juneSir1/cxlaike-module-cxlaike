package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.type.StringListTypeHandler;

/**
 * 用户矩阵任务 DO
 *
 * @author 宵征源码
 */
@TableName(value = "cxlaike_matrix_task_order", autoResultMap = true)
@KeySequence("cxlaike_matrix_task_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MatrixTaskOrderDO extends TenantBaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 订单类型1-矩阵 2-扫码
     */
    private Integer orderType;
    /**
     * 任务id
     */
    private Long matrixTaskId;
    /**
     * 封面id(素材id)
     */
    private Long coverId;
    /**
     * 视频id(素材id)
     */
    private Long videoId;
    /**
     * 标题内容
     */
    private String title;
    /**
     * at 用户 openId
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> openIds;
    /**
     * 用户绑定id(抖音)
     */
    private Long accountBindId;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 视频状态 1-未上传 2-不适宜公开 4-审核中 5-公开视频
     */
    private Integer videoStatus;
    /**
     * 视频链接
     */
    private String videoLink;
    /**
     * 外部视频id(抖音)
     */
    private String externalVideoId;
    /**
     * 外部视频id(抖音)
     */
    private String externalItemId;
    /**
     * 抖音分享id
     */
    private String shareId;
    /**
     * 播放数
     */
    private Integer playCount;
    /**
     * 点赞数
     */
    private Integer diggCount;
    /**
     * 分享数
     */
    private Integer shareCount;
    /**
     * 下载数
     */
    private Integer downloadCount;
    /**
     * 评价数
     */
    private Integer commentCount;
    /**
     * 订单状态 1-分发 5-已上传 10-终止
     */
    private Integer orderStatus;

    private String poiId;
    private String poiName;


    @Schema(description = "抖音小程序id", requiredMode = Schema.RequiredMode.REQUIRED, example = "ttef9b112671b152ba")
    private String microAppId;

    @Schema(description = "抖音开发者在小程序中生成该页面时写的 path 地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "pages/xxx/ox")
    private String microAppUrl;

    @Schema(description = "抖音小程序标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "标题xX")
    private String microAppTitle;

    @Schema(description = "快手需要挂载小黄车的商品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://open.kwaixiaodian.com/docs/api?categoryId=44&apiName=open.item.list.get&version=1")
    private Long merchantProductId ;


}
