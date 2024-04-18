package net.hzxzkj.cxlaike.module.cxlaike.enums.wallet;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 全局用户类型枚举
 */
@AllArgsConstructor
@Getter
public enum WalletOrderUserTypeEnum implements IntArrayValuable {

    MEMBER(1, "用户钱包"),
    MERCHANT(2, "商户钱包"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(WalletOrderUserTypeEnum::getValue).toArray();

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static WalletOrderUserTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getValue().equals(value), WalletOrderUserTypeEnum.values());
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
