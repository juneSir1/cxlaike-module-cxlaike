package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aicopywriting;


import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywriting.AiCopywritingDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting.AiStatusEnum;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai文案 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiCopywritingMapper extends BaseMapperX<AiCopywritingDO> {

    default PageResult<AiCopywritingDO> selectPage(AiCopywritingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiCopywritingDO>()
                .eqIfPresent(AiCopywritingDO::getContentType, reqVO.getContentType())
                .likeIfPresent(AiCopywritingDO::getContentDesc, reqVO.getContentDesc())
                .ne(AiCopywritingDO::getAiStatus, AiStatusEnum.FAIL.getStatus())
                .orderByDesc(AiCopywritingDO::getId));
    }

}
