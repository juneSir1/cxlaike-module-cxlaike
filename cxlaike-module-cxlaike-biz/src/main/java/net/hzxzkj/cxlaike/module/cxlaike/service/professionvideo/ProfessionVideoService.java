package net.hzxzkj.cxlaike.module.cxlaike.service.professionvideo;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.professionvideo.ProfessionVideoDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 精选行业视频 Service 接口
 *
 * @author 宵征源码
 */
public interface ProfessionVideoService {

    /**
     * 创建精选行业视频
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProfessionVideo(@Valid ProfessionVideoCreateReqVO createReqVO);

    /**
     * 更新精选行业视频
     *
     * @param updateReqVO 更新信息
     */
    void updateProfessionVideo(@Valid ProfessionVideoUpdateReqVO updateReqVO);

    /**
     * 删除精选行业视频
     *
     * @param id 编号
     */
    void deleteProfessionVideo(Long id);

    /**
     * 获得精选行业视频
     *
     * @param id 编号
     * @return 精选行业视频
     */
    ProfessionVideoDO getProfessionVideo(Long id);

    /**
     * 获得精选行业视频列表
     *
     * @param ids 编号
     * @return 精选行业视频列表
     */
    List<ProfessionVideoDO> getProfessionVideoList();

    /**
     * 获得精选行业视频分页
     *
     * @param pageReqVO 分页查询
     * @return 精选行业视频分页
     */
    PageResult<ProfessionVideoDO> getProfessionVideoPage(ProfessionVideoPageReqVO pageReqVO);

    /**
     * 获得精选行业视频列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 精选行业视频列表
     */
    List<ProfessionVideoDO> getProfessionVideoList(ProfessionVideoExportReqVO exportReqVO);

}
