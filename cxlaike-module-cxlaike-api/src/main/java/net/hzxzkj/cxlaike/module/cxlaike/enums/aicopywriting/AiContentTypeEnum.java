package net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ai内容类型枚举
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum AiContentTypeEnum {

    TC(1, "团购推广套餐"),
    XC(2, "产品宣传"),
    TG(3, "门店推广"),
    CZ(4, "剧本创作"),

    NORMAL(5, "通用"),
    LB(6, "内容裂变"),
    CT(7, "生成标题"),
    JB(8, "短视频脚本"),
    WA(9, "字幕文案"),

    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static AiContentTypeEnum getEnumByCode(Integer code) {
        for (AiContentTypeEnum enm : AiContentTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return AiContentTypeEnum.TC;
    }
}
