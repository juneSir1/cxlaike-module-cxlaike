package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountgroup;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.MatrixAccountListReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.MatrixAccountListRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户抖音分组管理 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AccountGroupMapper extends BaseMapperX<AccountGroupDO> {

    default PageResult<AccountGroupDO> selectPage(AccountGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AccountGroupDO>()
                .likeIfPresent(AccountGroupDO::getName, reqVO.getName())
                .orderByDesc(AccountGroupDO::getId));
    }

    default List<AccountGroupDO> selectList(AccountGroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AccountGroupDO>()
                .likeIfPresent(AccountGroupDO::getName, reqVO.getName())
                .betweenIfPresent(AccountGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AccountGroupDO::getId));
    }

    List<MatrixAccountListRespVO> selectAccountByGroup(@Param("form") MatrixAccountListReqVO reqVO);

}
