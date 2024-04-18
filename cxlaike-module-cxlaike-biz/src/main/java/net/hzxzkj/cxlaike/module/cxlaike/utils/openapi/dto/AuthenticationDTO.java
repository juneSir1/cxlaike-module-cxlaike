package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023-08-09 18:08
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {

    private String id;

    private String object;

    private Long created;

    private String model;

    private List<Choices> choices;

    @Data
    public static class Choices{

        private String text;

        private Integer index;

        private Integer logprobs;

        private String finish_reason;
    }
}

