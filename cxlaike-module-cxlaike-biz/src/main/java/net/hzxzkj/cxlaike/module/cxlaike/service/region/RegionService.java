package net.hzxzkj.cxlaike.module.cxlaike.service.region;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionRespVO;

import java.util.List;

/**
 * 地区 Service 接口
 *
 * @author 宵征源码
 */
public interface RegionService {

    /**
     * 获得地区列表
     *
     * @return 地区列表
     */
    List<RegionRespVO> getRegionList();

    /**
     * 获得地区列表
     *
     * @return 地区列表
     */
    List<AppRegionRespVO> getAppRegionList();



}
