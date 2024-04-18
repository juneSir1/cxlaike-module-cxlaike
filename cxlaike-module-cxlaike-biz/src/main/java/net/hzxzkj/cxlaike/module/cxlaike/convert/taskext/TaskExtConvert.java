package net.hzxzkj.cxlaike.module.cxlaike.convert.taskext;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商家任务扩展 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskExtConvert {

    TaskExtConvert INSTANCE = Mappers.getMapper(TaskExtConvert.class);


}
