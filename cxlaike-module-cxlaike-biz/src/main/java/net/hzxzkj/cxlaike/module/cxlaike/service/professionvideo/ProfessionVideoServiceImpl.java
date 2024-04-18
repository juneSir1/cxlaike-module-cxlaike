package net.hzxzkj.cxlaike.module.cxlaike.service.professionvideo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.professionvideo.ProfessionVideoDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.professionvideo.ProfessionVideoConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.professionvideo.ProfessionVideoMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 精选行业视频 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class ProfessionVideoServiceImpl implements ProfessionVideoService {

    @Resource
    private ProfessionVideoMapper professionVideoMapper;

    @Override
    public Long createProfessionVideo(ProfessionVideoCreateReqVO createReqVO) {
        // 插入
        ProfessionVideoDO professionVideo = ProfessionVideoConvert.INSTANCE.convert(createReqVO);
        professionVideoMapper.insert(professionVideo);
        // 返回
        return professionVideo.getId();
    }

    @Override
    public void updateProfessionVideo(ProfessionVideoUpdateReqVO updateReqVO) {
        // 校验存在
        validateProfessionVideoExists(updateReqVO.getId());
        // 更新
        ProfessionVideoDO updateObj = ProfessionVideoConvert.INSTANCE.convert(updateReqVO);
        professionVideoMapper.updateById(updateObj);
    }

    @Override
    public void deleteProfessionVideo(Long id) {
        // 校验存在
        validateProfessionVideoExists(id);
        // 删除
        professionVideoMapper.deleteById(id);
    }

    private void validateProfessionVideoExists(Long id) {
        if (professionVideoMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public ProfessionVideoDO getProfessionVideo(Long id) {
        return professionVideoMapper.selectById(id);
    }

    @Override
    public List<ProfessionVideoDO> getProfessionVideoList() {
        return professionVideoMapper.selectList();
    }

    @Override
    public PageResult<ProfessionVideoDO> getProfessionVideoPage(ProfessionVideoPageReqVO pageReqVO) {
        return professionVideoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProfessionVideoDO> getProfessionVideoList(ProfessionVideoExportReqVO exportReqVO) {
        return professionVideoMapper.selectList(exportReqVO);
    }

}
