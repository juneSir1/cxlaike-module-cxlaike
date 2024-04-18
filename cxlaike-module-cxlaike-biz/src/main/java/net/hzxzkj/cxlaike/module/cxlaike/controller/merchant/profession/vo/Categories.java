package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023-08-24 18:31
 * @description
 */
@Data
public class Categories {

    private String code;

    private Integer level;

    private String name;

    private List<Categories> sub_categories;
}
