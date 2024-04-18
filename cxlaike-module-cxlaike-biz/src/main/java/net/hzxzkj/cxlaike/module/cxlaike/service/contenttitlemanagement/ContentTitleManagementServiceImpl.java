package net.hzxzkj.cxlaike.module.cxlaike.service.contenttitlemanagement;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.ContentTitleManagementUpdateReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement.ContentTitleManagementDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.contenttitlemanagement.ContentTitleManagementConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.contenttitlemanagement.ContentTitleManagementMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.merchant.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * 任务内容标题管理 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class ContentTitleManagementServiceImpl implements ContentTitleManagementService {

    @Resource
    private ContentTitleManagementMapper contentTitleManagementMapper;

    @Override
    public Long createContentTitleManagement(ContentTitleManagementCreateReqVO createReqVO) {
        // 插入
        ContentTitleManagementDO contentTitleManagement = ContentTitleManagementConvert.INSTANCE.convert(createReqVO);
        contentTitleManagementMapper.insert(contentTitleManagement);
        // 返回
        return contentTitleManagement.getId();
    }

    @Override
    public void updateContentTitleManagement(ContentTitleManagementUpdateReqVO updateReqVO) {
        // 校验存在
        validateContentTitleManagementExists(updateReqVO.getId());
        // 更新
        ContentTitleManagementDO updateObj = ContentTitleManagementConvert.INSTANCE.convert(updateReqVO);
        contentTitleManagementMapper.updateById(updateObj);
    }

    @Override
    public void deleteContentTitleManagement(Long id) {
        // 校验存在
        validateContentTitleManagementExists(id);
        // 删除
        contentTitleManagementMapper.deleteById(id);
    }

    private void validateContentTitleManagementExists(Long id) {
        if (contentTitleManagementMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public ContentTitleManagementDO getContentTitleManagement(Long id) {
        return contentTitleManagementMapper.selectById(id);
    }

    @Override
    public List<ContentTitleManagementDO> getContentTitleManagementList(Collection<Long> ids) {
        return contentTitleManagementMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ContentTitleManagementDO> getContentTitleManagementPage(ContentTitleManagementPageReqVO pageReqVO) {
        return contentTitleManagementMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ContentTitleManagementDO> getContentTitleManagementList(ContentTitleManagementExportReqVO exportReqVO) {
        return contentTitleManagementMapper.selectList(exportReqVO);
    }

}
