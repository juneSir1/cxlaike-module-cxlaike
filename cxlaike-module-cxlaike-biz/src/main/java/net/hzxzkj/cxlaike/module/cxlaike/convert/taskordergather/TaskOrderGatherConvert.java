package net.hzxzkj.cxlaike.module.cxlaike.convert.taskordergather;


import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo.TaskOrderGatherCreateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskordergather.TaskOrderGatherDO;

/**
 * 用户视频集合 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskOrderGatherConvert {

    TaskOrderGatherConvert INSTANCE = Mappers.getMapper(TaskOrderGatherConvert.class);

    TaskOrderGatherDO convert(TaskOrderGatherCreateReqVO bean);


}
