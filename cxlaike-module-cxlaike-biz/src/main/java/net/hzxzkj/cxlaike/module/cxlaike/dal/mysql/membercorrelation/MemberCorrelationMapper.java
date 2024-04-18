package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.membercorrelation;

import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.membercorrelation.MemberCorrelationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户与达人视频账号(抖音账号)的任务关联 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MemberCorrelationMapper extends BaseMapperX<MemberCorrelationDO> {

}
