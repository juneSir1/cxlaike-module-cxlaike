package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店管理 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_store")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 门店名称
     */
    private String name;
    private String poiId;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区
     */
    private String countyName;

    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 市code
     */
    private String cityCode;
    /**
     * 区code
     */
    private String countryCode;

}
