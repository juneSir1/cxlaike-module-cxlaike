package net.hzxzkj.cxlaike.module.cxlaike.api.profession;

/**
 * @author jianan.han
 * @date 2023-08-25 14:13
 * @description
 */
public interface ProfessionApi {

    String getNameByCode(String code);

    String[] getCodesByCode(String code);
}
