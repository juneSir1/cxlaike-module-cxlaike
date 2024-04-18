package net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import net.hzxzkj.cxlaike.framework.test.core.ut.BaseDbUnitTest;

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig.AiVideoConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideoconfig.AiVideoConfigMapper;
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
 * {@link AiVideoConfigServiceImpl} 的单元测试类
 *
 * @author 宵征源码
 */
@Import(AiVideoConfigServiceImpl.class)
public class AiVideoConfigServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AiVideoConfigServiceImpl aiVideoConfigService;

    @Resource
    private AiVideoConfigMapper aiVideoConfigMapper;

    @Test
    public void testCreateAiVideoConfig_success() {
        // 准备参数
        AiVideoConfigCreateReqVO reqVO = randomPojo(AiVideoConfigCreateReqVO.class);

        // 调用
        Long aiVideoConfigId = aiVideoConfigService.createAiVideoConfig(reqVO);
        // 断言
        assertNotNull(aiVideoConfigId);
        // 校验记录的属性是否正确
        AiVideoConfigDO aiVideoConfig = aiVideoConfigMapper.selectById(aiVideoConfigId);
        assertPojoEquals(reqVO, aiVideoConfig);
    }

    @Test
    public void testUpdateAiVideoConfig_success() {
        // mock 数据
        AiVideoConfigDO dbAiVideoConfig = randomPojo(AiVideoConfigDO.class);
        aiVideoConfigMapper.insert(dbAiVideoConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AiVideoConfigUpdateReqVO reqVO = randomPojo(AiVideoConfigUpdateReqVO.class, o -> {
            o.setId(dbAiVideoConfig.getId()); // 设置更新的 ID
        });

        // 调用
        aiVideoConfigService.updateAiVideoConfig(reqVO);
        // 校验是否更新正确
        AiVideoConfigDO aiVideoConfig = aiVideoConfigMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, aiVideoConfig);
    }

    @Test
    public void testUpdateAiVideoConfig_notExists() {
        // 准备参数
        AiVideoConfigUpdateReqVO reqVO = randomPojo(AiVideoConfigUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> aiVideoConfigService.updateAiVideoConfig(reqVO), AI_VIDEO_CONFIG_NOT_EXISTS);
    }

    @Test
    public void testDeleteAiVideoConfig_success() {
        // mock 数据
        AiVideoConfigDO dbAiVideoConfig = randomPojo(AiVideoConfigDO.class);
        aiVideoConfigMapper.insert(dbAiVideoConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAiVideoConfig.getId();

        // 调用
        aiVideoConfigService.deleteAiVideoConfig(id);
       // 校验数据不存在了
       assertNull(aiVideoConfigMapper.selectById(id));
    }

    @Test
    public void testDeleteAiVideoConfig_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> aiVideoConfigService.deleteAiVideoConfig(id), AI_VIDEO_CONFIG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAiVideoConfigPage() {
       // mock 数据
       AiVideoConfigDO dbAiVideoConfig = randomPojo(AiVideoConfigDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setValue(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       aiVideoConfigMapper.insert(dbAiVideoConfig);
       // 测试 type 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setType(null)));
       // 测试 value 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setValue(null)));
       // 测试 status 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setCreateTime(null)));
       // 准备参数
       AiVideoConfigPageReqVO reqVO = new AiVideoConfigPageReqVO();
       reqVO.setType(null);
       reqVO.setValue(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AiVideoConfigDO> pageResult = aiVideoConfigService.getAiVideoConfigPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAiVideoConfig, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAiVideoConfigList() {
       // mock 数据
       AiVideoConfigDO dbAiVideoConfig = randomPojo(AiVideoConfigDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setValue(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       aiVideoConfigMapper.insert(dbAiVideoConfig);
       // 测试 type 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setType(null)));
       // 测试 value 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setValue(null)));
       // 测试 status 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       aiVideoConfigMapper.insert(cloneIgnoreId(dbAiVideoConfig, o -> o.setCreateTime(null)));
       // 准备参数
       AiVideoConfigExportReqVO reqVO = new AiVideoConfigExportReqVO();
       reqVO.setType(null);
       reqVO.setValue(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<AiVideoConfigDO> list = aiVideoConfigService.getAiVideoConfigList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbAiVideoConfig, list.get(0));
    }

}
