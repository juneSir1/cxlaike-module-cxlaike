package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.store;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StorePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store.StoreDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 门店管理 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface StoreMapper extends BaseMapperX<StoreDO> {

    default PageResult<StoreDO> selectPage(StorePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StoreDO>()
                .likeIfPresent(StoreDO::getName, reqVO.getName())
                .likeIfPresent(StoreDO::getAddress, reqVO.getAddress())
                .eqIfPresent(StoreDO::getStatus,reqVO.getStatus())
                .orderByDesc(StoreDO::getStatus,StoreDO::getCreateTime));
    }

    @Select("SELECT COUNT(*) FROM  cxlaike_task  WHERE JSON_CONTAINS(shop_ids, #{id}) AND `status` in (1)")
    int selectTaskByStoreId (@Param("id") String id);

}
