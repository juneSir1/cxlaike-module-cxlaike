package net.hzxzkj.cxlaike.module.cxlaike.api.profession;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.profession.ProfessionDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.profession.ProfessionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * @author jianan.han
 * @date 2023-08-25 14:14
 * @description
 */
@Service
@Validated
public class ProfessionApiImpl implements ProfessionApi{

    @Resource
    private ProfessionService professionService;

    @Override
    public String getNameByCode(String code) {
        ProfessionDO professionDO = professionService.getByCode(code);
        return professionDO == null ? null:professionDO.getName();
    }

    @Override
    public String[] getCodesByCode(String code) {
        return professionService.getCodesByCode(code);
    }
}
