package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.memberdybind;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 达人扩展 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MemberDyBindMapper extends BaseMapperX<MemberDyBindDO> {

    List<MemberDyBindPageRespVO> selectListForAudit(Page page, @Param("form") MemberDyBindPageReqVO reqVO);
}
