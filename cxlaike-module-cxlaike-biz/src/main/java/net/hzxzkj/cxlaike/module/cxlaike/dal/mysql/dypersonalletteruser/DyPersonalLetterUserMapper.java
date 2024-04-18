package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dypersonalletteruser;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser.DyPersonalLetterUserDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo.*;

/**
 * 抖音私信用户管理 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface DyPersonalLetterUserMapper extends BaseMapperX<DyPersonalLetterUserDO> {

    default PageResult<DyPersonalLetterUserDO> selectPage(DyPersonalLetterUserPageReqVO reqVO) {

        LambdaQueryWrapper<DyPersonalLetterUserDO> queryWrapper = new LambdaQueryWrapper<DyPersonalLetterUserDO>()
                .orderByDesc(DyPersonalLetterUserDO::getId);

        if(reqVO.getCommunicationStatus()!= null){
            queryWrapper.eq(DyPersonalLetterUserDO::getCommunicationStatus,reqVO.getCommunicationStatus());
        }
        if(reqVO.getNickName()!= null){
            queryWrapper.like(DyPersonalLetterUserDO::getNickName,reqVO.getNickName());
        }
        if(reqVO.getContactMark() != null && reqVO.getContactMark()==1){
            queryWrapper.and(wq->{
                wq.ne(DyPersonalLetterUserDO::getMobile,"");
                wq.or().ne(DyPersonalLetterUserDO::getWx,"");
            });
        }


        return selectPage(reqVO, queryWrapper);
    }

    default List<DyPersonalLetterUserDO> selectList(DyPersonalLetterUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DyPersonalLetterUserDO>()
                .eqIfPresent(DyPersonalLetterUserDO::getOpenId, reqVO.getOpenId())
                .likeIfPresent(DyPersonalLetterUserDO::getNickName, reqVO.getNickName())
                .eqIfPresent(DyPersonalLetterUserDO::getAvatar, reqVO.getAvatar())
                .likeIfPresent(DyPersonalLetterUserDO::getRealName, reqVO.getRealName())
                .eqIfPresent(DyPersonalLetterUserDO::getProvinceCode, reqVO.getProvinceCode())
                .likeIfPresent(DyPersonalLetterUserDO::getProvinceName, reqVO.getProvinceName())
                .eqIfPresent(DyPersonalLetterUserDO::getCityCode, reqVO.getCityCode())
                .likeIfPresent(DyPersonalLetterUserDO::getCityName, reqVO.getCityName())
                .eqIfPresent(DyPersonalLetterUserDO::getCountyCode, reqVO.getCountyCode())
                .likeIfPresent(DyPersonalLetterUserDO::getCountyName, reqVO.getCountyName())
                .eqIfPresent(DyPersonalLetterUserDO::getCommunicationStatus, reqVO.getCommunicationStatus())
                .eqIfPresent(DyPersonalLetterUserDO::getMobile, reqVO.getMobile())
                .eqIfPresent(DyPersonalLetterUserDO::getWx, reqVO.getWx())
                .eqIfPresent(DyPersonalLetterUserDO::getCompany, reqVO.getCompany())
                .eqIfPresent(DyPersonalLetterUserDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DyPersonalLetterUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DyPersonalLetterUserDO::getId));
    }

}
