package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.drrepresentative;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.drrepresentative.DrRepresentativeDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo.*;

/**
 * 达人探店代 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface DrRepresentativeMapper extends BaseMapperX<DrRepresentativeDO> {

    default PageResult<DrRepresentativeDO> selectPage(DrRepresentativePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DrRepresentativeDO>()
                .eqIfPresent(DrRepresentativeDO::getPlatformType, reqVO.getPlatformType())
                .likeIfPresent(DrRepresentativeDO::getNickname, reqVO.getNickname())
                .eqIfPresent(DrRepresentativeDO::getFansCount, reqVO.getFansCount())
                .eqIfPresent(DrRepresentativeDO::getSales, reqVO.getSales())
                .eqIfPresent(DrRepresentativeDO::getSellGrade, reqVO.getSellGrade())
                .eqIfPresent(DrRepresentativeDO::getContentGrade, reqVO.getContentGrade())
                .eqIfPresent(DrRepresentativeDO::getCreditScore, reqVO.getCreditScore())
                .eqIfPresent(DrRepresentativeDO::getProfession, reqVO.getProfession())
                .eqIfPresent(DrRepresentativeDO::getUserInfo, reqVO.getUserInfo())
                .eqIfPresent(DrRepresentativeDO::getCity, reqVO.getCity())
                .betweenIfPresent(DrRepresentativeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DrRepresentativeDO::getId));
    }

    default List<DrRepresentativeDO> selectList(DrRepresentativeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DrRepresentativeDO>()
                .eqIfPresent(DrRepresentativeDO::getPlatformType, reqVO.getPlatformType())
                .likeIfPresent(DrRepresentativeDO::getNickname, reqVO.getNickname())
                .eqIfPresent(DrRepresentativeDO::getFansCount, reqVO.getFansCount())
                .eqIfPresent(DrRepresentativeDO::getSales, reqVO.getSales())
                .eqIfPresent(DrRepresentativeDO::getSellGrade, reqVO.getSellGrade())
                .eqIfPresent(DrRepresentativeDO::getContentGrade, reqVO.getContentGrade())
                .eqIfPresent(DrRepresentativeDO::getCreditScore, reqVO.getCreditScore())
                .eqIfPresent(DrRepresentativeDO::getProfession, reqVO.getProfession())
                .eqIfPresent(DrRepresentativeDO::getUserInfo, reqVO.getUserInfo())
                .eqIfPresent(DrRepresentativeDO::getCity, reqVO.getCity())
                .betweenIfPresent(DrRepresentativeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DrRepresentativeDO::getId));
    }

}
