package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtask;

import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtask.MatrixTaskDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 矩阵任务 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface MatrixTaskMapper extends BaseMapperX<MatrixTaskDO> {

    void addScanCount(@Param("id") Long taskId);
}
