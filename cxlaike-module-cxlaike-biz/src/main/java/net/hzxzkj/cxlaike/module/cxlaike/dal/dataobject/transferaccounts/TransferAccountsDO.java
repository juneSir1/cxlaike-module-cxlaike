package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.transferaccounts;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 转账账户 DO
 *
 * @author 宵征源码
 */
@TableName("pay_transfer_accounts")
@KeySequence("pay_transfer_accounts_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferAccountsDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 开启状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 渠道费率，单位：百分比
     */
    private Object feeRate;
    /**
     * 公众号或者小程序的 appid
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * apiV3 密钥值
     */
    private String apiV3Key;
    /**
     * apiclient_key.perm 证书
     */
    private String privateKeyContent;
    /**
     * apiclient_cert.pem
     */
    private String privateCertContent;

}
