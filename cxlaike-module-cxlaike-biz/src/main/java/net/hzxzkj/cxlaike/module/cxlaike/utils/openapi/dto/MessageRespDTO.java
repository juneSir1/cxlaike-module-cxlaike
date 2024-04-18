package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023-08-19 13:16
 * @description
 */
@Data
public class MessageRespDTO {

    private String id;

    private String object;

    private Long created;

    private String model;

    private Usage usage;

    private List<Choices> choices;

    private Long expendQuantity;

    private ErrorDTO error;

    private String inputContent;

}
