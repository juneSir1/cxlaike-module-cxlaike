package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskext;

import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskext.TaskExtDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商家任务扩展 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskExtMapper extends BaseMapperX<TaskExtDO> {


    void updateSurplusNumOfAdd(@Param("id") Long id);

    int updateSurplusNumOfSub(@Param("id") Long id);
}
