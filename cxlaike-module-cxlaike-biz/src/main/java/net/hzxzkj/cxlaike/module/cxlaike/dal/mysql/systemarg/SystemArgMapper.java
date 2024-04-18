package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.systemarg;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systemarg.SystemArgDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systemarg.vo.*;

/**
 * 来客系统参数 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface SystemArgMapper extends BaseMapperX<SystemArgDO> {

    default PageResult<SystemArgDO> selectPage(SystemArgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SystemArgDO>()
                .eqIfPresent(SystemArgDO::getYunDayOrderNum, reqVO.getYunDayOrderNum())
                .eqIfPresent(SystemArgDO::getEntityDayOrderNum, reqVO.getEntityDayOrderNum())
                .eqIfPresent(SystemArgDO::getYunDayNum, reqVO.getYunDayNum())
                .eqIfPresent(SystemArgDO::getEntityDayNum, reqVO.getEntityDayNum())
                .eqIfPresent(SystemArgDO::getYunOrderNum, reqVO.getYunOrderNum())
                .eqIfPresent(SystemArgDO::getEntityOrderNum, reqVO.getEntityOrderNum())
                .eqIfPresent(SystemArgDO::getYunAiClipFee, reqVO.getYunAiClipFee())
                .eqIfPresent(SystemArgDO::getYunDigitalClipFee, reqVO.getYunDigitalClipFee())
                .eqIfPresent(SystemArgDO::getEntityOnCamera, reqVO.getEntityOnCamera())
                .eqIfPresent(SystemArgDO::getEntityNotOnCamera, reqVO.getEntityNotOnCamera())
                .eqIfPresent(SystemArgDO::getPopularCities, reqVO.getPopularCities())
                .eqIfPresent(SystemArgDO::getDetail, reqVO.getDetail())
                .betweenIfPresent(SystemArgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SystemArgDO::getId));
    }

    default List<SystemArgDO> selectList(SystemArgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SystemArgDO>()
                .eqIfPresent(SystemArgDO::getYunDayOrderNum, reqVO.getYunDayOrderNum())
                .eqIfPresent(SystemArgDO::getEntityDayOrderNum, reqVO.getEntityDayOrderNum())
                .eqIfPresent(SystemArgDO::getYunDayNum, reqVO.getYunDayNum())
                .eqIfPresent(SystemArgDO::getEntityDayNum, reqVO.getEntityDayNum())
                .eqIfPresent(SystemArgDO::getYunOrderNum, reqVO.getYunOrderNum())
                .eqIfPresent(SystemArgDO::getEntityOrderNum, reqVO.getEntityOrderNum())
                .eqIfPresent(SystemArgDO::getYunAiClipFee, reqVO.getYunAiClipFee())
                .eqIfPresent(SystemArgDO::getYunDigitalClipFee, reqVO.getYunDigitalClipFee())
                .eqIfPresent(SystemArgDO::getEntityOnCamera, reqVO.getEntityOnCamera())
                .eqIfPresent(SystemArgDO::getEntityNotOnCamera, reqVO.getEntityNotOnCamera())
                .eqIfPresent(SystemArgDO::getPopularCities, reqVO.getPopularCities())
                .eqIfPresent(SystemArgDO::getDetail, reqVO.getDetail())
                .betweenIfPresent(SystemArgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SystemArgDO::getId));
    }

}
