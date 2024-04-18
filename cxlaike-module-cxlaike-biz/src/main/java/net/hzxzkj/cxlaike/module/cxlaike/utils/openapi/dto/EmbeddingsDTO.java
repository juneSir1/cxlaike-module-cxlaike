package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023-08-10 09:28
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddingsDTO {

    private String object;

    private List<EData> data;

    private String model;



    @Data
    public static class EData {
        private String object;
        private String embedding;
        private Integer index;
    }
//    @Data
//    public static class Embedding {
//
//
//    }


}
