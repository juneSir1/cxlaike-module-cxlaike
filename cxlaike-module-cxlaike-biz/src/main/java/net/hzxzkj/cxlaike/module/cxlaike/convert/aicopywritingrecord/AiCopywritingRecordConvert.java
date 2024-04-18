package net.hzxzkj.cxlaike.module.cxlaike.convert.aicopywritingrecord;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ai文案记录 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiCopywritingRecordConvert {

    AiCopywritingRecordConvert INSTANCE = Mappers.getMapper(AiCopywritingRecordConvert.class);

}
