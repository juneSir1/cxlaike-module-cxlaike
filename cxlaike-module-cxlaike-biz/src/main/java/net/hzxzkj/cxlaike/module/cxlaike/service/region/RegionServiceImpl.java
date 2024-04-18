package net.hzxzkj.cxlaike.module.cxlaike.service.region;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionThreeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionTwoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionThreeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionTwoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.region.RegionConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.region.RegionDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.region.RegionMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 地区 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
@Slf4j
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionMapper regionMapper;

    @Getter
    private volatile List<RegionDO> regionCache;

    @PostConstruct
    public void initLocalCache() {
        //第一步：查询数据
        List<RegionDO> regionDOList = regionMapper.selectList();
        log.info("[initLocalCache][缓存地区，数量为:{}]", regionDOList.size());
        //第二步：构建缓存
        regionCache = regionDOList;

    }

    @Override
    public  List<RegionRespVO> getRegionList(){
        //从缓存regionCache里面遍历出pid=0的对象组成一个provinceList集合，并且从DO转换成VO
        List<RegionRespVO> provinceList=RegionConvert.INSTANCE.convertList(regionCache.stream()
                .filter(region -> Integer.valueOf(0).equals(region.getPid())).collect(Collectors.toList()));

        for(RegionRespVO province:provinceList){
            List<RegionTwoRespVO> cityList=RegionConvert.INSTANCE.convertList02(regionCache.stream()
                    .filter(region -> region.getPid().equals(province.getId())).collect(Collectors.toList()));
            province.setChildren(cityList);

            for(RegionTwoRespVO city:cityList){
                List<RegionThreeRespVO> countyList=RegionConvert.INSTANCE.convertList03(regionCache.stream()
                        .filter(region -> region.getPid().equals(city.getId())).collect(Collectors.toList()));
                city.setChildren(countyList);
            }
        }
        return provinceList;
    }


    @Override
    public  List<AppRegionRespVO> getAppRegionList(){
        //从缓存regionCache里面遍历出pid=0的对象组成一个provinceList集合，并且从DO转换成VO
        List<AppRegionRespVO> provinceList=RegionConvert.INSTANCE.converApptList(regionCache.stream()
                .filter(region -> Integer.valueOf(0).equals(region.getPid())).collect(Collectors.toList()));

        for(AppRegionRespVO province:provinceList){
            List<AppRegionTwoRespVO> cityList=RegionConvert.INSTANCE.convertAppList02(regionCache.stream()
                    .filter(region -> region.getPid().equals(province.getId())).collect(Collectors.toList()));
            province.setChildren(cityList);

            for(AppRegionTwoRespVO city:cityList){
                List<AppRegionThreeRespVO> countyList=RegionConvert.INSTANCE.convertAppList03(regionCache.stream()
                        .filter(region -> region.getPid().equals(city.getId())).collect(Collectors.toList()));
                city.setChildren(countyList);
            }
        }
        return provinceList;
    }


}
