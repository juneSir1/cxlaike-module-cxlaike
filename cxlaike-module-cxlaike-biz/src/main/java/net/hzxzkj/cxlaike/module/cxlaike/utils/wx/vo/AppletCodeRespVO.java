package net.hzxzkj.cxlaike.module.cxlaike.utils.wx.vo;

import lombok.Data;

import java.io.InputStream;
import java.nio.Buffer;

/**
 * @author jianan.han
 * @date 2023/10/8 上午10:22
 * @description
 */
@Data
public class AppletCodeRespVO {

    private Buffer buffer;

    private Long errcode;

    private String errmsg;
}
