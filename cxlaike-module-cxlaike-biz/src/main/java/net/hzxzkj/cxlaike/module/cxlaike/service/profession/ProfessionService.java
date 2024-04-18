package net.hzxzkj.cxlaike.module.cxlaike.service.profession;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo.ProfessionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.profession.ProfessionDO;

/**
 * 行业 Service 接口
 *
 * @author 宵征源码
 */
public interface ProfessionService {

    /**
     * 获得行业列表
     *
     * @return 行业列表
     */
    List<ProfessionRespVO> getProfessionList();

    ProfessionDO getByCode(String code);
    /**
     * 根据3级code获得code数组
     *
     * @return 行业列表
     */
    String[] getCodesByCode(String code);
}
