package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aisystemarg;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 来客系统参数 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiSystemArgMapper extends BaseMapperX<AiSystemArgDO> {

    default PageResult<AiSystemArgDO> selectPage(AiSystemArgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiSystemArgDO>()
                .eqIfPresent(AiSystemArgDO::getSysType, reqVO.getSysType())
                .eqIfPresent(AiSystemArgDO::getType, reqVO.getType())
                .betweenIfPresent(AiSystemArgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiSystemArgDO::getId));
    }

    default List<AiSystemArgDO> selectList(AiSystemArgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiSystemArgDO>()
                .eqIfPresent(AiSystemArgDO::getSysType, reqVO.getSysType())
                .eqIfPresent(AiSystemArgDO::getType, reqVO.getType())
                .betweenIfPresent(AiSystemArgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiSystemArgDO::getId));
    }

}
