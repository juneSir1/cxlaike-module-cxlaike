package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import net.hzxzkj.cxlaike.framework.test.core.ut.BaseDbUnitTest;

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotemplate.AiVideoTemplateMapper;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;
import static net.hzxzkj.cxlaike.framework.test.core.util.AssertUtils.*;
import static net.hzxzkj.cxlaike.framework.test.core.util.RandomUtils.*;
import static net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils.*;
import static net.hzxzkj.cxlaike.framework.common.util.object.ObjectUtils.*;
import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link AiVideoTemplateServiceImpl} 的单元测试类
 *
 * @author 宵征源码
 */
@Import(AiVideoTemplateServiceImpl.class)
public class AiVideoTemplateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AiVideoTemplateServiceImpl aiVideoTemplateService;

    @Resource
    private AiVideoTemplateMapper aiVideoTemplateMapper;

    @Test
    public void testCreateAiVideoTemplate_success() {
        // 准备参数
        AiVideoTemplateCreateReqVO reqVO = randomPojo(AiVideoTemplateCreateReqVO.class);

        // 调用
        Long aiVideoTemplateId = aiVideoTemplateService.createAiVideoTemplate(reqVO);
        // 断言
        assertNotNull(aiVideoTemplateId);
        // 校验记录的属性是否正确
        AiVideoTemplateDO aiVideoTemplate = aiVideoTemplateMapper.selectById(aiVideoTemplateId);
        assertPojoEquals(reqVO, aiVideoTemplate);
    }

    @Test
    public void testUpdateAiVideoTemplate_success() {
        // mock 数据
        AiVideoTemplateDO dbAiVideoTemplate = randomPojo(AiVideoTemplateDO.class);
        aiVideoTemplateMapper.insert(dbAiVideoTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AiVideoTemplateUpdateReqVO reqVO = randomPojo(AiVideoTemplateUpdateReqVO.class, o -> {
            o.setId(dbAiVideoTemplate.getId()); // 设置更新的 ID
        });

        // 调用
        aiVideoTemplateService.updateAiVideoTemplate(reqVO);
        // 校验是否更新正确
        AiVideoTemplateDO aiVideoTemplate = aiVideoTemplateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, aiVideoTemplate);
    }

    @Test
    public void testUpdateAiVideoTemplate_notExists() {
        // 准备参数
        AiVideoTemplateUpdateReqVO reqVO = randomPojo(AiVideoTemplateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> aiVideoTemplateService.updateAiVideoTemplate(reqVO), AI_VIDEO_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteAiVideoTemplate_success() {
        // mock 数据
        AiVideoTemplateDO dbAiVideoTemplate = randomPojo(AiVideoTemplateDO.class);
        aiVideoTemplateMapper.insert(dbAiVideoTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAiVideoTemplate.getId();

        // 调用
        aiVideoTemplateService.deleteAiVideoTemplate(id);
       // 校验数据不存在了
       assertNull(aiVideoTemplateMapper.selectById(id));
    }

    @Test
    public void testDeleteAiVideoTemplate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> aiVideoTemplateService.deleteAiVideoTemplate(id), AI_VIDEO_TEMPLATE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAiVideoTemplatePage() {
       // mock 数据
       AiVideoTemplateDO dbAiVideoTemplate = randomPojo(AiVideoTemplateDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setValue(null);
           o.setCreateTime(null);
       });
       aiVideoTemplateMapper.insert(dbAiVideoTemplate);
       // 测试 type 不匹配
       aiVideoTemplateMapper.insert(cloneIgnoreId(dbAiVideoTemplate, o -> o.setType(null)));
       // 测试 value 不匹配
       aiVideoTemplateMapper.insert(cloneIgnoreId(dbAiVideoTemplate, o -> o.setValue(null)));
       // 测试 createTime 不匹配
       aiVideoTemplateMapper.insert(cloneIgnoreId(dbAiVideoTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       AiVideoTemplatePageReqVO reqVO = new AiVideoTemplatePageReqVO();
       reqVO.setType(null);
       reqVO.setValue(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AiVideoTemplateDO> pageResult = aiVideoTemplateService.getAiVideoTemplatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAiVideoTemplate, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAiVideoTemplateList() {
       // mock 数据
       AiVideoTemplateDO dbAiVideoTemplate = randomPojo(AiVideoTemplateDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setValue(null);
           o.setCreateTime(null);
       });
       aiVideoTemplateMapper.insert(dbAiVideoTemplate);
       // 测试 type 不匹配
       aiVideoTemplateMapper.insert(cloneIgnoreId(dbAiVideoTemplate, o -> o.setType(null)));
       // 测试 value 不匹配
       aiVideoTemplateMapper.insert(cloneIgnoreId(dbAiVideoTemplate, o -> o.setValue(null)));
       // 测试 createTime 不匹配
       aiVideoTemplateMapper.insert(cloneIgnoreId(dbAiVideoTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       AiVideoTemplateExportReqVO reqVO = new AiVideoTemplateExportReqVO();
       reqVO.setType(null);
       reqVO.setValue(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<AiVideoTemplateDO> list = aiVideoTemplateService.getAiVideoTemplateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbAiVideoTemplate, list.get(0));
    }

}
