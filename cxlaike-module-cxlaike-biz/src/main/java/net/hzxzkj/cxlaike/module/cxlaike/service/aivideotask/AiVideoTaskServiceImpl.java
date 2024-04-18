package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.ice.core.client.VideoService;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.*;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.BaseEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.AIASREffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.TextEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.VolumeEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.property.IceProperties;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiMaterialClipVideoTaskMaterialCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aiclipvideotaskgroup.AiClipVideoTaskGroupConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideoresult.AiVideoResultConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotask.AiVideoTaskConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskmaterial.AiVideoTaskMaterialConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.copymanagement.CopyManagementConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskfile.TaskFileConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup.AiClipVideoTaskGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoresult.AiVideoResultDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask.AiVideoTaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset.AiVideoTaskFontSetDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial.AiVideoTaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement.ContentTitleManagementDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.copymanagement.CopyManagementDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskfile.TaskFileDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template.TemplateDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videoslice.VideoSliceDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aiclipvideotaskgroup.AiClipVideoTaskGroupMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideoresult.AiVideoResultMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotask.AiVideoTaskMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskfontset.AiVideoTaskFontSetMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskmaterial.AiVideoTaskMaterialMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.contenttitlemanagement.ContentTitleManagementMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.copymanagement.CopyManagementMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskfile.TaskFileMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videoslice.VideoSliceMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.*;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateVirtualTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.aisystemarg.AiSystemArgService;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig.AiVideoConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.material.MaterialService;
import net.hzxzkj.cxlaike.module.cxlaike.service.metahumanconfig.MetaHumanConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.TemplateService;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.VideoClipHandlerService;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.*;
import net.hzxzkj.cxlaike.module.cxlaike.utils.VideoCreateUtil;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.FileUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.VideoSlicer;
import net.hzxzkj.cxlaike.module.infra.api.file.FileApi;
import net.hzxzkj.cxlaike.module.infra.api.file.dto.MaterialRespVO;
import net.hzxzkj.cxlaike.module.infra.enums.MaterialTypeEnum;
import net.hzxzkj.cxlaike.module.merchant.api.user.MerchantUserApi;
import net.hzxzkj.cxlaike.module.merchant.api.user.dto.MerchantUserRespDTO;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.pay.api.wallet.WalletApi;
import net.hzxzkj.cxlaike.module.pay.api.wallet.dto.WalletDTO;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletLogTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletTypeEnum;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.VideoDurationType.*;

/**
 * ai视频任务 Service 实现类
 *
 * @author 宵征源码
 */
@Slf4j
@Service
@Validated
public class AiVideoTaskServiceImpl implements AiVideoTaskService {

    @Resource
    private AiVideoTaskMapper aiVideoTaskMapper;
    @Resource
    private AiVideoTaskMaterialMapper aiVideoTaskMaterialMapper;
    @Resource
    private AiVideoResultMapper aiVideoResultMapper;
    @Resource
    private IceService iceService;
    @Resource
    private IceProperties iceProperties;
    @Resource
    private MaterialService materialService;
    @Resource
    private VideoService videoService;
    @Lazy
    @Resource
    private TaskClient taskClient;
    @Resource
    private WalletApi walletApi;
    @Resource
    private CopyManagementMapper copyManagementMapper;
    @Resource
    private TaskFileMapper taskFileMapper;
    @Resource
    private AiSystemArgService aiSystemArgService;
    @Resource
    private MetaHumanConfigService metaHumanConfigService;
    @Resource
    private VideoSliceMapper videoSliceMapper;

    private final VideoClipHandlerService digitalOnlyVideoClipService;
    private final VideoClipHandlerService metaHumanVideoClipService;
    private final VideoClipHandlerService aiVideoClipService;
    @Resource
    private AiVideoTaskFontSetMapper aiVideoTaskFontSetMapper;
    @Resource
    private AiClipVideoTaskGroupMapper aiClipVideoTaskGroupMapper;

    @Resource
    private ContentTitleManagementMapper contentTitleManagementMapper;
    @Resource
    private TemplateService templateService;

    @Resource
    private MerchantUserApi merchantUserApi;

    @Resource
    private FileApi fileApi;

    //设置转场时长
    private static final Float transitionDuration = 0.8f;

    //字幕位置等比例换算
    private static final Float  copywriteConversion= 1f;

    //标题位置等比例换算
    private static final Float  titleConversion= 1f;

    //title Size等比例换算
    private static final Float  fontConversion= 1f;

    //copywrite Size等比例换算
    private static final Float  copywritefontConversion= 1f;

    public AiVideoTaskServiceImpl(
            @Qualifier("digitalOnlyVideoClipService") VideoClipHandlerService digitalOnlyVideoClipService,
            @Qualifier("metaHumanVideoClipService") VideoClipHandlerService metaHumanVideoClipService,
            @Qualifier("aiVideoClipService") VideoClipHandlerService aiVideoClipService) {
        this.digitalOnlyVideoClipService = digitalOnlyVideoClipService;
        this.metaHumanVideoClipService = metaHumanVideoClipService;
        this.aiVideoClipService = aiVideoClipService;
    }

    private void validateMaterialList(List<AiVideoTaskMaterialBaseVO> materialList) {
        for (AiVideoTaskMaterialBaseVO taskMaterialVO : materialList) {
            if (taskMaterialVO.getFileId() == null) {
                throw exception(TASK_CREATE_MATERIAL_ID_NULL);
            }
            if (taskMaterialVO.getName() == null || taskMaterialVO.getName().isEmpty()) {
                throw exception(TASK_CREATE_VIDEO_GROUP_NAME_NULL);
            }
        }
    }

    /**
     * 验证当前用户金币是否足够
     *
     * @param userId 商户Id
     */
    private void checkUserWallet(Long userId) {
        WalletDTO walletDTO = walletApi.get(userId, WalletTypeEnum.GOLD);
        if (walletDTO.getBalance() <= 0) {
            throw exception(TASK_CREATE_BALANCE_NOT_ENOUGH);
        }
    }

    private void insertCopywriting(List<String> copywritingList, Long taskId) {
        //插入口播文案
        List<CopyManagementDO> copyManagementDOList = copywritingList.stream()
                .map(copywriting -> {
                    CopyManagementDO copyManagementDO = new CopyManagementDO();
                    copyManagementDO.setTaskId(taskId);
                    copyManagementDO.setCopywriting(copywriting);
                    copyManagementDO.setType(CopywritingType.AI_VIDEO.getType());
                    return copyManagementDO;
                }).collect(Collectors.toList());

        copyManagementMapper.insertBatch(copyManagementDOList);
    }

    private void insertContentTitleForAiMaterialClip(List<String> contentTitleList, Long taskId, Long videoGroupId) {
        //插入内容标题
        List<ContentTitleManagementDO> contentTitleManagementDOList = contentTitleList.stream()
                .map(contentTitle -> {
                    ContentTitleManagementDO contentTitleManagementDO = new ContentTitleManagementDO();
                    contentTitleManagementDO.setTaskId(taskId);
                    contentTitleManagementDO.setVideoGroupId(videoGroupId);
                    contentTitleManagementDO.setContentTitle(contentTitle);
                    return contentTitleManagementDO;
                }).collect(Collectors.toList());

        contentTitleManagementMapper.insertBatch(contentTitleManagementDOList);
    }

    private void insertCopywritingForAiMaterialClip(List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList, Long taskId, Long videoGroupId) {
        //插入口播文案
        List<CopyManagementDO> copyManagementDOList = copywritingList.stream()
                .map(copywriting -> {
                    CopyManagementDO copyManagementDO = new CopyManagementDO();
                    copyManagementDO.setTaskId(taskId);
                    copyManagementDO.setVideoGroupId(videoGroupId);
                    copyManagementDO.setCopywriting(copywriting.getCopywriting());
                    copyManagementDO.setFileId(copywriting.getFileId());
                    copyManagementDO.setFileName(copywriting.getFileName());
                    copyManagementDO.setUrl(copywriting.getUrl());
                    copyManagementDO.setType(CopywritingType.AI_VIDEO.getType());
                    return copyManagementDO;
                }).collect(Collectors.toList());

        copyManagementMapper.insertBatch(copyManagementDOList);
    }

    private List<AiVideoTaskMaterialDO> getMaterialList(List<AiVideoTaskMaterialBaseVO> materialList,
                                                        Long taskId) {
        List<AiVideoTaskMaterialDO> doList = Lists.newArrayList();

        List<TaskFileDO> taskFileList = TaskFileConvert.INSTANCE.convertList2(materialList);

        if (taskFileList == null) {
            return doList;
        }

        taskFileList.forEach(taskFile -> {
            taskFile.setTaskType(ActiveTaskType.CLIP.getType());
            taskFile.setTaskId(taskId);
            // 查询文件信息
            List<MaterialRespVO> materialRespList = materialService
                    .getVideoMaterialByFileId(taskFile.getFileId());

            if (materialRespList.size() < 1) {
                throw exception(TASK_CREATE_MATERIAL_ERROR);
            }
            List<AiVideoTaskMaterialDO> newTaskMaterialList = materialRespList.stream().map(
                    resp -> {
                        AiVideoTaskMaterialDO taskMaterialDO = new AiVideoTaskMaterialDO();
                        taskMaterialDO.setAiTaskId(taskId);
                        taskMaterialDO.setName(taskFile.getName());
                        taskMaterialDO.setType(AiVideoTaskMaterialType.MATERIAL.getType());
                        taskMaterialDO.setUrl(resp.getUrl());
                        taskMaterialDO.setFileName(resp.getName());
                        taskMaterialDO.setStatus(TaskMaterialFileStatus.SUCCESS.getType());
                        taskMaterialDO.setDuration(resp.getFileLength());
                        taskMaterialDO.setSort(taskFile.getSort());
                        return taskMaterialDO;
                    }
            ).collect(Collectors.toList());
            doList.addAll(newTaskMaterialList);
        });

        //插入数据
        taskFileMapper.insertBatch(taskFileList);

        return doList;
    }


    private List<AiVideoTaskMaterialDO> getMaterialListForAiMaterialClip(List<AiMaterialClipVideoTaskMaterialCreateReqVO> materialList,
                                                                         Long taskId, Long videoGroupId) {
        List<AiVideoTaskMaterialDO> doList = Lists.newArrayList();

        List<TaskFileDO> taskFileList = TaskFileConvert.INSTANCE.convertList4(materialList);

        if (taskFileList == null) {
            return doList;
        }

        taskFileList.forEach(taskFile -> {
            taskFile.setTaskType(ActiveTaskType.CLIP.getType());
            taskFile.setTaskId(taskId);
            // 查询文件信息
            List<MaterialRespVO> materialRespList = materialService
                    .getVideoMaterialByFileId(taskFile.getFileId());

            if (materialRespList.size() < 1) {
                throw exception(TASK_CREATE_MATERIAL_ERROR);
            }
            List<AiVideoTaskMaterialDO> newTaskMaterialList = materialRespList.stream().map(
                resp -> {
                    AiVideoTaskMaterialDO taskMaterialDO = new AiVideoTaskMaterialDO();
                    taskMaterialDO.setAiTaskId(taskId);
                    taskMaterialDO.setVideoGroupId(videoGroupId);
                    taskMaterialDO.setName(taskFile.getName());
                    taskMaterialDO.setType(AiVideoTaskMaterialType.MATERIAL.getType());
                    taskMaterialDO.setFileId(resp.getId());
                    taskMaterialDO.setFileName(resp.getName());
                    taskMaterialDO.setStatus(TaskMaterialFileStatus.SUCCESS.getType());
                    AiMaterialClipVideoTaskMaterialCreateReqVO vo = materialList.stream().filter(m ->
                            m.getFileId().equals(resp.getId())).collect(Collectors.toList()).get(0);
                    taskMaterialDO.setUrl(vo.getUrl());
                    taskMaterialDO.setDuration(vo.getDuration());
                    taskMaterialDO.setSort(taskFile.getSort());
                    return taskMaterialDO;
                    }
            ).collect(Collectors.toList());
            doList.addAll(newTaskMaterialList);
        });

        //插入数据
        taskFileMapper.insertBatch(taskFileList);

        return doList;
    }

    private TemplateTypeEnum getTemplateType(AiVideoTaskDO aiVideoTask) {
        Integer clipType = aiVideoTask.getClipType();
        Integer aspectRatioType = aiVideoTask.getAspectRatioType();
        TemplateTypeEnum templateTypeEnum;
        Integer videoLayoutType = aiVideoTask.getVideoLayoutType();

        if (VideoLayoutType.RANDOM.getType().equals(videoLayoutType)) {
            //ai混剪
            if (AiClipType.AI_CLIP.getType().equals(clipType)) {
                templateTypeEnum = TemplateTypeEnum
                        .getRandomEnumByWeight(TemplateVirtualTypeEnum.NONE, aspectRatioType);
            } else {
                //数字人混剪
                TemplateVirtualTypeEnum templateVirtualType = getTemplateVirtualType(
                        aiVideoTask.getMetaHumanLayoutType());
                templateTypeEnum = TemplateTypeEnum
                        .getRandomEnumByWeight(templateVirtualType, aspectRatioType);
            }
        } else {
            //不是随机
            if (AiClipType.AI_CLIP.getType().equals(clipType)) {
                templateTypeEnum = TemplateTypeEnum
                        .valueOf(videoLayoutType + TemplateVirtualTypeEnum.NONE.getType());
            } else {
                //数字人混剪
                TemplateVirtualTypeEnum templateVirtualType = getTemplateVirtualType(
                        aiVideoTask.getMetaHumanLayoutType());
                templateTypeEnum = TemplateTypeEnum
                        .valueOf(videoLayoutType + templateVirtualType.getType());
            }
        }

        return templateTypeEnum;
    }


    private TemplateTypeEnum getTemplateTypeForMaterialClip(AiMaterialClipVideoTaskVO aiVideoTask) {
        Integer aspectRatioType = aiVideoTask.getAspectRatioType();
        TemplateTypeEnum templateTypeEnum;
        Integer videoLayoutType = aiVideoTask.getVideoLayoutType();

        if (VideoLayoutType.RANDOM.getType().equals(videoLayoutType)) {
            templateTypeEnum = TemplateTypeEnum
                    .getRandomEnumByWeight(TemplateVirtualTypeEnum.NONE, aspectRatioType);
        } else {
            templateTypeEnum = TemplateTypeEnum
                    .valueOf(videoLayoutType + TemplateVirtualTypeEnum.NONE.getType());
        }

        return templateTypeEnum;
    }

    private TemplateVirtualTypeEnum getTemplateVirtualType(Integer metaHumanLayoutType) {
        if (MetaHumanLayoutType.RANDOM.getType().equals(metaHumanLayoutType)) {
            //随机获取一个数字人布局
            return TemplateVirtualTypeEnum.getRandomEnumByWeight();
        } else if (MetaHumanLayoutType.FIXED_UPPER_RIGHT_CORNER.getType().equals(metaHumanLayoutType)) {
            return TemplateVirtualTypeEnum.TOP_RIGHT_CORNER;
        } else {
            return TemplateVirtualTypeEnum.DEFAULT;
        }
    }

    /**
     * 根据配乐IdList随机获取配乐地址
     *
     * @param pipedMusicIdList
     * @return
     */
    private String getRandomPipedMusic(Set<Long> pipedMusicIdList) {
        if (pipedMusicIdList == null || pipedMusicIdList.size() == 0) {
            return null;
        }
        List<MaterialRespVO> materialList = materialService.getMaterialList(pipedMusicIdList);

        if (materialList == null || materialList.size() == 0) {
            throw exception(TASK_CREATE_MUSIC_ERROR);
        }

        materialList.forEach(
                materialRespVO -> {
                    if (!MaterialTypeEnum.isAudio(materialRespVO.getMaterialType())) {
                        throw exception(TASK_CREATE_MUSIC_ERROR);
                    }
                }
        );

        //随机获取
        int index = new Random().nextInt(materialList.size());
        return materialList.get(index).getUrl();
    }

    /**
     * 根据配音员IdList随机获取配音员code
     *
     * @param dubIdList
     * @return
     */
    private String getRandomDubCode(Set<Long> dubIdList) {
        if (dubIdList == null || dubIdList.size() == 0) {
            return null;
        }
        List<AiSystemArgDO> aiSystemArgList = aiSystemArgService.getAiSystemArgList(2, dubIdList);

        if (aiSystemArgList.size() != dubIdList.size()) {
            throw exception(TASK_CREATE_MUSIC_ERROR);
        }
        //随机获取
        int index = new Random().nextInt(aiSystemArgList.size());
        return aiSystemArgList.get(index).getCode();
    }

    /**
     * 根据图片Id查询图片地址
     *
     * @param coverId
     * @return
     */
    private String getCoverUrl(Long coverId) {
        return getImageUrl(coverId);
    }

    /**
     * 根据图片Id查询图片地址
     *
     * @param coverIds
     * @return
     */
    private String getCoverUrlForMaterialClip(List<Long> coverIds) {
        if(CollectionUtil.isEmpty(coverIds)){
            return null;
        }
        Collections.shuffle(coverIds);
        return getImageUrl(coverIds.get(0));
    }

    /**
     * 根据数字人Id查询数字人code
     *
     * @param metaHumanId
     * @return
     */
    private MetaHumanConfigDO getMetaHumanDO(Long metaHumanId) {
        if (metaHumanId == null) {
            throw exception(TASK_CREATE_HUMAN_NOT_EXISTS);
        }
        MetaHumanConfigDO metaHumanConfig = metaHumanConfigService
                .getMetaHumanConfigById(UserTypeEnum.MERCHANT.getValue(), metaHumanId);
        if (metaHumanConfig == null) {
            throw exception(TASK_CREATE_HUMAN_ERROR);
        }
        return metaHumanConfig;
    }

    private MetaHumanConfigDO getMetaHuman(Long metaHumanId) {
        if (metaHumanId == null) {
            return new MetaHumanConfigDO();
        }
        return metaHumanConfigService
                .getMetaHumanConfigById(UserTypeEnum.MERCHANT.getValue(), metaHumanId);
    }

    /**
     * 根据图片Id查询图片地址
     *
     * @param metaHumanBackgroundId
     * @return
     */
    private String getBackgroundImageUrl(Long metaHumanBackgroundId) {
        return getImageUrl(metaHumanBackgroundId);
    }

    private String getImageUrl(Long imageId) {
        if (imageId == null) {
            return null;
        }
        MaterialRespVO material = materialService.getMaterial(imageId);
        if (material == null) {
            throw exception(TASK_CREATE_IMAGE_NOT_EXISTS);
        }
        if (!MaterialTypeEnum.isImage(material.getMaterialType())) {
            throw exception(TASK_CREATE_FILE_TYPE_ERROR);
        }
        return material.getUrl();
    }

    @Override
    @Transactional(readOnly = true)
    public AiVideoTaskRespVO getAiVideoTask(Long id) {
        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(id);
        if (aiVideoTaskDO == null) {
            return null;
        }
        AiVideoTaskRespVO respVO = AiVideoTaskConvert.INSTANCE
                .convert(aiVideoTaskDO);
        MetaHumanConfigDO metaHuman = getMetaHuman(aiVideoTaskDO.getMetaHumanId());
        if (metaHuman != null) {
            MetaHumanRespVO metaHumanRespVO = new MetaHumanRespVO();
            metaHumanRespVO.setId(metaHuman.getId());
            metaHumanRespVO.setName(metaHuman.getName());
            metaHumanRespVO.setPicture(metaHuman.getPicture());
            metaHumanRespVO.setMetaHumanBackgroundId(aiVideoTaskDO.getMetaHumanBackgroundId());
            metaHumanRespVO.setMetaHumanBackgroundUrl(
                    getBackgroundImageUrl(aiVideoTaskDO.getMetaHumanBackgroundId()));
            respVO.setMetaHuman(metaHumanRespVO);
        }
        //设置视频素材
        List<AiVideoTaskMaterialBaseVO> materialList = getAiVideoTaskMaterial(respVO.getId());
        respVO.setMaterialList(materialList);
        //设置口播文案
        List<String> copyManagement = getCopyManagement(respVO.getId());
        respVO.setCopywritingList(copyManagement);
        respVO.setDubList(getDubList(aiVideoTaskDO.getDubIdList()));
        respVO.setPipedMusicList(getPipedMusicList(aiVideoTaskDO.getPipedMusicIdList()));
        respVO.setCoverUrl(getCoverUrl(aiVideoTaskDO.getCoverId()));
        respVO.setBackgroundImageUrl(getBackgroundImageUrl(aiVideoTaskDO.getBackgroundImageId()));

        //获取结果
        if (AiTaskStatus.COMPLETE.getStatus().equals(aiVideoTaskDO.getStatus())) {
            List<AiVideoResultDO> resultDOList = aiVideoResultMapper.selectList(
                    new LambdaQueryWrapperX<AiVideoResultDO>().eq(AiVideoResultDO::getAiTaskId, id).eq(
                            AiVideoResultDO::getStatus, AiClipStatus.SUCCESS.getStatus()));
            respVO.setResultList(AiVideoResultConvert.INSTANCE.convertList(resultDOList));
        }
        return respVO;
    }

    @Override
    @Transactional(readOnly = true)
    public AiMaterialClipVideoTaskRespVO getAiMaterialClipVideoTask(Long id) {
        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(id);
        if (aiVideoTaskDO == null) {
            return null;
        }

        Long taskId = aiVideoTaskDO.getId();

        AiMaterialClipVideoTaskRespVO respVO = AiVideoTaskConvert.INSTANCE.convertToAiMaterialClipVideoTaskRespVO(aiVideoTaskDO);

        //获取全局内容标题及设置
        respVO.setContentTitleVO(getContentTitleVO(taskId, null, FontSetFieldNameType.AI_VIDEO_TASK_CONTENT.getValue()));
        //获取全局字幕文案及设置
        respVO.setCopywritingVO(getCopywritingVO(taskId, null, FontSetFieldNameType.AI_VIDEO_TASK_COPYINGWRITING.getValue()));

        List<AiClipVideoTaskGroupCreateReqVO> videoGroupList = new ArrayList<>();

        //获取视频组
        List<AiClipVideoTaskGroupDO> aiClipVideoTaskGroupDOList = aiClipVideoTaskGroupMapper.selectListByAiTaskId(taskId);
        for (int i = 1; i <= aiClipVideoTaskGroupDOList.size(); i++){
            AiClipVideoTaskGroupDO aiClipVideoTaskGroupDO = aiClipVideoTaskGroupDOList.get(i-1);
            Long videoGroupId = aiClipVideoTaskGroupDO.getId();
            AiClipVideoTaskGroupCreateReqVO aiClipVideoTaskGroupCreateReqVO = AiClipVideoTaskGroupConvert.INSTANCE.convertToVO(aiClipVideoTaskGroupDO);
            //获取视频组内容标题及设置
            aiClipVideoTaskGroupCreateReqVO.setContentTitleVO(getContentTitleVO(taskId, videoGroupId, FontSetFieldNameType.AI_VIDEO_TASK_GROUP_CONTENT.getValue()));
            //获取视频组字幕文案及设置
            aiClipVideoTaskGroupCreateReqVO.setCopywritingVO(getCopywritingVO(taskId, videoGroupId, FontSetFieldNameType.AI_VIDEO_TASK_GROUP_COPYINGWRITING.getValue()));
            //获取视频组素材
            List<AiVideoTaskMaterialDO> aiVideoTaskMaterialDOS = aiVideoTaskMaterialMapper.selectListByVideoGroupId(taskId, videoGroupId);

            aiClipVideoTaskGroupCreateReqVO.setMaterialList(AiVideoTaskMaterialConvert.INSTANCE.convertList1(aiVideoTaskMaterialDOS));

            videoGroupList.add(aiClipVideoTaskGroupCreateReqVO);

        }

        respVO.setVideoGroupList(videoGroupList);

        //设置视频素材
//    List<AiVideoTaskMaterialBaseVO> materialList = getAiVideoTaskMaterial(respVO.getId());
//    respVO.setMaterialList(materialList);
        //设置口播文案
//    List<String> copyManagement = getCopyManagement(respVO.getId());
//    respVO.setCopywritingList(copyManagement);
        respVO.setDubList(getDubList(aiVideoTaskDO.getDubIdList()));
        respVO.setPipedMusicList(getPipedMusicList(aiVideoTaskDO.getPipedMusicIdList()));
        respVO.setCoverList(getCoverList(aiVideoTaskDO.getCoverIds()));
        respVO.setBackgroundImageUrl(getBackgroundImageUrl(aiVideoTaskDO.getBackgroundImageId()));

        //获取结果
        if (AiTaskStatus.COMPLETE.getStatus().equals(aiVideoTaskDO.getStatus())) {
            List<AiVideoResultDO> resultDOList = aiVideoResultMapper.selectList(
                    new LambdaQueryWrapperX<AiVideoResultDO>().eq(AiVideoResultDO::getAiTaskId, id));
            if(CollectionUtil.isNotEmpty(resultDOList)){
                respVO.setResultList(AiVideoResultConvert.INSTANCE.convertList1(resultDOList));

            }
        }
        return respVO;
    }

    private List<AiVideoTaskMaterialBaseVO> getAiVideoTaskMaterial(Long taskId) {

        List<TaskFileDO> taskFileList = taskFileMapper.selectList(new LambdaQueryWrapperX<TaskFileDO>()
                .eq(TaskFileDO::getTaskType, ActiveTaskType.CLIP.getType()).eq(TaskFileDO::getTaskId,
                        taskId));

        return taskFileList.stream().map(
                taskFile -> {
                    AiVideoTaskMaterialBaseVO materialBaseVO = new AiVideoTaskMaterialBaseVO();
                    materialBaseVO.setFileId(taskFile.getFileId());
                    materialBaseVO.setSort(taskFile.getSort());
                    materialBaseVO.setName(taskFile.getName());

                    MaterialRespVO material = materialService.getMaterial(taskFile.getFileId());
                    if (material != null) {
                        materialBaseVO.setFileUrl(material.getUrl());
                        materialBaseVO.setFileLength(material.getFileLength());
                    }

                    return materialBaseVO;
                }
        ).collect(Collectors.toList());


    }

    /**
     * 根据图片Id查询图片地址
     *
     * @param coverIds
     * @return
     */
    private List<CoverRespVO> getCoverList(List<Long> coverIds) {
        if (CollectionUtil.isEmpty(coverIds)) {
            return Lists.newArrayList();
        }
        List<MaterialRespVO> materialList = materialService.getCoverList(coverIds);
        if (materialList == null || materialList.isEmpty()) {
            return Lists.newArrayList();
        }
        return materialList.stream().map(material -> {
            CoverRespVO coverRespVO = new CoverRespVO();
            coverRespVO.setCoverId(material.getId());
            coverRespVO.setCoverUrl(material.getUrl());
            return coverRespVO;
        }).collect(Collectors.toList());
    }

    private List<MusicRespVO> getPipedMusicList(Set<Long> pipedMusicIdList) {
        if (pipedMusicIdList == null || pipedMusicIdList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<MaterialRespVO> materialList = materialService.getMaterialList(pipedMusicIdList);
        if (materialList == null || materialList.isEmpty()) {
            return Lists.newArrayList();
        }
        return materialList.stream().map(material -> {
            MusicRespVO musicRespVO = new MusicRespVO();
            musicRespVO.setId(material.getId());
            musicRespVO.setName(material.getName());
            return musicRespVO;
        }).collect(Collectors.toList());
    }

    private List<VoiceRespVO> getDubList(Set<Long> dubIdList) {
        if (dubIdList == null || dubIdList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<AiSystemArgDO> aiSystemArgList = aiSystemArgService.getAiSystemArgList(2, dubIdList);
        if (aiSystemArgList == null || aiSystemArgList.isEmpty()) {
            return Lists.newArrayList();
        }
        return aiSystemArgList.stream().map(systemArg -> {
            VoiceRespVO voiceRespVO = new VoiceRespVO();
            voiceRespVO.setId(systemArg.getId());
            voiceRespVO.setName(systemArg.getName());
            return voiceRespVO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<AiVideoTaskPageRespVO> getAiVideoTaskPage(AiVideoTaskPageReqVO pageReqVO) {
        PageResult<AiVideoTaskDO> aiVideoTaskDOPageResult = aiVideoTaskMapper.selectPage(pageReqVO);
        PageResult<AiVideoTaskPageRespVO> page = new PageResult<>(aiVideoTaskDOPageResult.getTotal());
        List<AiVideoTaskPageRespVO> respList = Lists.newArrayList();
        List<AiVideoTaskDO> list = aiVideoTaskDOPageResult.getList();

        if (list == null || list.isEmpty()) {
            return page;
        }

        for (AiVideoTaskDO aiVideoTaskDO : list) {
            AiVideoTaskPageRespVO respVO = AiVideoTaskConvert.INSTANCE.convert1(aiVideoTaskDO);

            //设置口播文案
            List<String> copyManagement = getCopyManagement(respVO.getId());
            respVO.setCopywritingList(copyManagement);

            //设置内容标题
            if(AiClipType.AI_MATERIAL_CLIP.getType().equals(aiVideoTaskDO.getClipType())){
                List<String> contentTitleManagement = getContentTitleManagement(respVO.getId());
                respVO.setContentTitleList(contentTitleManagement);
            }else{
                respVO.setContentTitleList(Arrays.asList(aiVideoTaskDO.getContentTitle()));
            }

            Set<Long> pipedMusicIdList = aiVideoTaskDO.getPipedMusicIdList();
            List<String> pipedMusicNameList = getPipedMusicNameList(pipedMusicIdList);
            //背景音乐
            respVO.setBackgroundMusicList(pipedMusicNameList);
            Set<Long> dubIdList = aiVideoTaskDO.getDubIdList();
            List<String> dubNameList = getDubNameList(dubIdList);
            //配音
            respVO.setDubList(dubNameList);
            //数字人图片
            Long metaHumanId = aiVideoTaskDO.getMetaHumanId();
            MetaHumanConfigDO metaHuman = getMetaHuman(metaHumanId);
            respVO.setDigitalManImage(metaHuman.getPicture());
            respList.add(respVO);
        }
        page.setList(respList);
        return page;
    }

    private List<String> getCopyManagement(Long taskId) {
        List<String> copyList = Lists.newArrayList();
        List<CopyManagementDO> copyManagementDOList = copyManagementMapper
                .selectList(new LambdaQueryWrapperX<CopyManagementDO>()
                        .eq(CopyManagementDO::getType, CopywritingType.AI_VIDEO.getType())
                        .eq(CopyManagementDO::getTaskId,
                                taskId).isNull(CopyManagementDO::getVideoGroupId));
        if (copyManagementDOList == null || copyManagementDOList.isEmpty()) {
            return copyList;
        }
        copyList = copyManagementDOList.stream().map(CopyManagementDO::getCopywriting)
                .collect(Collectors.toList());
        return copyList;

    }

    private List<String> getContentTitleManagement(Long taskId) {
        List<String> contentTitleList = Lists.newArrayList();
        List<ContentTitleManagementDO> contentTitleManagementDOList = contentTitleManagementMapper
                .selectList(new LambdaQueryWrapperX<ContentTitleManagementDO>()
                        .eq(ContentTitleManagementDO::getTaskId,
                                taskId).isNull(ContentTitleManagementDO::getVideoGroupId));
        if (contentTitleManagementDOList == null || contentTitleManagementDOList.isEmpty()) {
            return contentTitleList;
        }
        contentTitleList = contentTitleManagementDOList.stream().map(ContentTitleManagementDO::getContentTitle)
                .collect(Collectors.toList());
        return contentTitleList;

    }

    private List<String> getPipedMusicNameList(Set<Long> pipedMusicIdList) {
        if (pipedMusicIdList == null || pipedMusicIdList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<MaterialRespVO> materialList = materialService.getMaterialList(pipedMusicIdList);
        if (materialList == null || materialList.isEmpty()) {
            return Lists.newArrayList();
        }
        return materialList.stream().map(MaterialRespVO::getName).collect(Collectors.toList());
    }

    private List<String> getDubNameList(Set<Long> dubIdList) {
        if (dubIdList == null || dubIdList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<AiSystemArgDO> aiSystemArgList = aiSystemArgService.getAiSystemArgList(2, dubIdList);
        if (aiSystemArgList == null || aiSystemArgList.isEmpty()) {
            return Lists.newArrayList();
        }
        return aiSystemArgList.stream().map(AiSystemArgDO::getName).collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadFileTask(Long taskId, String oldMediaUrl) {
        //查询当前任务
        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(taskId);
        if (aiVideoTaskDO == null) {
            log.error("任务不存在,taskId:{}", taskId);
            return;
        }

        Integer clipType = aiVideoTaskDO.getClipType();

        if (AiClipType.DIGITAL_CLIP_ONLY.getType().equals(clipType)) {
            //纯数字人口播,将生成素材上传至oss用户对应桶中
            List<AiVideoResultDO> aiVideoResultList = aiVideoResultMapper
                    .selectList(new LambdaQueryWrapperX<AiVideoResultDO>()
                            .eq(AiVideoResultDO::getAiTaskId, taskId));

            if (aiVideoResultList == null || aiVideoResultList.isEmpty()) {
                log.info("当前任务没有生成中的素材,taskId:{}", taskId);
                return;
            }
            AiVideoResultDO aiVideoResultDO = aiVideoResultList.get(0);
            String mediaUrl = null;
            try {
                //根据地址上传文件
                byte[] fileBytes = getFileBytes(oldMediaUrl);
                String extension = FileTypeUtil.getType(new ByteArrayInputStream(fileBytes));
                mediaUrl = materialService
                        .createFile(aiVideoTaskDO.getTitle() + "-" + aiVideoResultDO.getId() + "." + extension,
                                "/",
                                fileBytes, 4, aiVideoTaskDO.getUserId());
            } catch (Exception e) {
                log.error("上传文件失败,taskId:{}", taskId, e);
                throw exception(TASK_CREATE_FILE_UPLOAD_ERROR);
            }
            //更新任务状态
            aiVideoResultMapper
                    .updateById(new AiVideoResultDO()
                            .setMediaUrl(mediaUrl)
                            .setId(aiVideoResultDO.getId()));
            aiVideoTaskMapper.updateById(
                    new AiVideoTaskDO().setId(taskId)
                            .setStatus(AiTaskStatus.COMPLETE.getStatus()));
            //上传成功,扣除用户数字人口播任务金币
            //计算需要扣除的金币
            Long coinNumber = countCoinNumber(aiVideoResultDO.getDuration(),
                    aiVideoTaskDO.getTenantId());
            if (coinNumber == null) {
                log.error("计算金币数量失败,taskId:{}", taskId);
                throw exception(TASK_CREATE_GOLD_ERROR);
            }
            //扣除金币
            walletApi.deduct(aiVideoTaskDO.getUserId(), coinNumber,
                    WalletLogTypeEnum.AI_CLIP_MERCHANT,
                    aiVideoTaskDO.getId(), aiVideoTaskDO.getId().toString(),
                    WalletLogTypeEnum.AI_CLIP_MERCHANT.getName());

        } else {
            //查询当前任务的素材结果
            List<AiVideoTaskMaterialDO> doList = aiVideoTaskMaterialMapper
                    .selectList(new LambdaQueryWrapperX<AiVideoTaskMaterialDO>()
                            .eq(AiVideoTaskMaterialDO::getType, AiVideoTaskMaterialType.DIGITAL.getType()).eq(
                                    AiVideoTaskMaterialDO::getAiTaskId, taskId));

            //数字人素材只有一个
            if (doList == null || doList.isEmpty()) {
                log.info("当前任务没有生成中的素材,taskId:{}", taskId);
                return;
            }

            AiVideoTaskMaterialDO aiVideoTaskMaterialDO = doList.get(0);

            //上传素材
            try {
                //根据地址上传文件
                byte[] fileBytes = getFileBytes(oldMediaUrl);
                String extension = FileTypeUtil.getType(new ByteArrayInputStream(fileBytes));
                String fileUrl = materialService
                        .createFile(
                                aiVideoTaskDO.getTitle() + "-" + aiVideoTaskMaterialDO.getId() + "." + extension,
                                "/",
                                fileBytes, 4, aiVideoTaskDO.getUserId());
                //修改之前的文件地址
                aiVideoTaskMaterialMapper.updateById(
                        new AiVideoTaskMaterialDO().setId(aiVideoTaskMaterialDO.getId()).setUrl(fileUrl));

            } catch (Exception e) {
                log.error("上传文件失败,taskId:{}", taskId, e);
                throw exception(TASK_CREATE_FILE_UPLOAD_ERROR);
            }

            //调用数字人混剪handler,创建任务
            JSONObject object = new JSONObject();
            object.put("id", taskId);
            object.put("taskType", UploadFileTaskType.AI_VIDEO);
            taskClient.createTask(TaskHandlerEnum.DIGITAL_CLIP.getValue(), null, object.toString());

        }

    }

    private byte[] getFileBytes(String oldMediaUrl) throws IOException {
        URL url = new URL(oldMediaUrl);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        InputStream inputStream = url.openStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

    /**
     * 计算ai视频剪辑需要扣除的金币
     *
     * @param duration
     * @param tenantId
     * @return
     */
    private Long countCoinNumber(Integer duration, Long tenantId) {
        if (duration == null || duration <= 0) {
            return 0L;
        }
        MerchantUserRespDTO merchantUserRespDTO = merchantUserApi.getUser(tenantId);
        if(merchantUserRespDTO==null){
            log.error("======AiVideoTaskServiceImpl发生了扣费异常======{}",tenantId);
            return 2L;
        }
        return Long.valueOf(merchantUserRespDTO.getVideoUnit());
    }


    /**
     * 除余进1
     *
     * @param a
     * @param b
     * @return
     */
    public static long mm(int a, int b) {
        return (a % b == 0) ? a / b : (a / b + 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newDigitalClipHandle(Long taskId) throws Exception {
        //将数字人及素材混剪
        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(taskId);
        if (aiVideoTaskDO == null) {
            log.error("任务不存在,taskId:{}", taskId);
            return;
        }
        //查询当前任务的素材结果
        List<AiVideoTaskMaterialDO> digitalList = aiVideoTaskMaterialMapper
                .selectList(new LambdaQueryWrapperX<AiVideoTaskMaterialDO>()
                        .eq(AiVideoTaskMaterialDO::getType, AiVideoTaskMaterialType.DIGITAL.getType()).eq(
                                AiVideoTaskMaterialDO::getAiTaskId, taskId).eq(AiVideoTaskMaterialDO::getStatus,
                                TaskMaterialFileStatus.SUCCESS.getType()));

        if (digitalList == null || digitalList.isEmpty()) {
            log.info("当前任务没有素材,taskId:{}", taskId);
            return;
        }
        AiVideoTaskMaterialDO virtualVideo = digitalList.get(0);

        //查看是否已经生成结果
        List<AiVideoResultDO> aiVideoResultList = aiVideoResultMapper
                .selectList(new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getAiTaskId, taskId));

        if (!aiVideoResultList.isEmpty()) {
            log.info("当前任务已经生成结果,taskId:{}", taskId);
            return;
        }

        //查素材切片
        List<VideoSliceDO> sliceDOList = videoSliceMapper
                .selectList(new LambdaQueryWrapperX<VideoSliceDO>().eq(VideoSliceDO::getTaskType,
                        ActiveTaskType.CLIP.getType()).eq(VideoSliceDO::getTaskId, taskId));
        String userId = aiVideoTaskDO.getUserId().toString();
        Integer videoNum = aiVideoTaskDO.getVideoNum();
        for (int i = 0; i < videoNum; i++) {
            AiVideoResultDO digitalResult = new AiVideoResultDO();
            digitalResult.setAiTaskId(taskId);
            digitalResult.setCreator(userId);
            digitalResult.setUpdater(userId);
            digitalResult.setStatus(AiClipStatus.GENERATING.getStatus());
            aiVideoResultMapper.insert(digitalResult);
            //生成切片
            List<VideoTrackClip> videoTrackClipList = VideoSlicer.getVideoTrackClipList(sliceDOList,
                    virtualVideo.getDuration());
            VideoClipOrderDTO videoClipOrder = newDigitalClip(aiVideoTaskDO, virtualVideo,
                    digitalResult.getId(),
                    videoTrackClipList);

            AiVideoResultDO newDigitalResult = new AiVideoResultDO();
            newDigitalResult.setId(digitalResult.getId());
            newDigitalResult.setJobId(videoClipOrder.getJobId());
            newDigitalResult.setVideoOrderId(videoClipOrder.getId());
            newDigitalResult.setMediaUrl(videoClipOrder.getMediaUrl());
            aiVideoResultMapper.updateById(newDigitalResult);
        }

        //更新使用次数
        sliceDOList.forEach(videoSliceDO -> {
            if (videoSliceDO.getNum() > 0) {
                videoSliceMapper.updateById(new VideoSliceDO().setId(videoSliceDO.getId())
                        .setNum(videoSliceDO.getNum()));
            }
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createAiClipVideoTask(AiClipVideoTaskCreateReqVO createReqVO) {

        //校验参数
        checkParam(false);
        //查看当前类型是否为必须传背景图的
        Integer videoLayoutType = createReqVO.getVideoLayoutType();
        if (VideoLayoutType.BACKGROUND_IMAGE.getType().equals(videoLayoutType) || VideoLayoutType.RANDOM
                .getType().equals(videoLayoutType)) {
            Long backgroundImageId = createReqVO.getBackgroundImageId();
            if (backgroundImageId == null) {
                throw exception(TASK_CREATE_TEMPLATE_BG_NULL);
            }
        }
        // 插入
        AiVideoTaskDO aiVideoTask = AiVideoTaskConvert.INSTANCE.convert4(createReqVO);
        //登录用户id
        Long loginUserId = createReqVO.getUserId();
        aiVideoTask.setUserId(loginUserId);
        aiVideoTask.setClipType(AiClipType.AI_MATERIAL_CLIP.getType());
        //设置状态
        aiVideoTask.setStatus(AiTaskStatus.GENERATING.getStatus());
        //添加素材
        List<AiVideoTaskMaterialBaseVO> materialList = createReqVO.getMaterialList();
        validateMaterialList(materialList);
        aiVideoTaskMapper.insert(aiVideoTask);
        Long taskId = aiVideoTask.getId();
        List<String> copywritingList = createReqVO.getCopywritingList();
        //口播文案不为空的情况

        Boolean hasCopywriting = createReqVO.getHasCopywriting();

        if (hasCopywriting != null && hasCopywriting) {
            if (copywritingList != null) {
                insertCopywriting(copywritingList, taskId);
            }else {
                throw exception(TASK_CREATE_COPYWRITING_NULL);
            }
        }


        List<AiVideoTaskMaterialDO> doList = getMaterialList(materialList, taskId);
        aiVideoTaskMaterialMapper.insertBatch(doList);
        //生成视频切片
        videoSlice(doList, taskId);
        //生成任务
        JSONObject param = new JSONObject();
        param.set("id", taskId);
        param.set("type", ActiveTaskType.CLIP.getType());
        taskClient.createTask(TaskHandlerEnum.AI_CLIP.getValue(), null, param.toString());

        return taskId;
    }

    public static void main(String[] args) {

        Integer totalDuration = 5;
        Float duration = 3f;
        System.out.println(NumberUtil.div(totalDuration, duration, 1, RoundingMode.HALF_UP).floatValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AiVideoTaskPreviewRespVO previewAiMaterialClipVideoTask(AiMaterialClipVideoTaskCreateReqVO createReqVO) {

        return createAiMaterialClipVideoTask(createReqVO, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createAiMaterialClipVideoTask(AiMaterialClipVideoTaskCreateReqVO createReqVO) {
        AiVideoTaskPreviewRespVO respVO = createAiMaterialClipVideoTask(createReqVO, false);
        return respVO.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public AiVideoTaskPreviewRespVO createAiMaterialClipVideoTask(AiMaterialClipVideoTaskCreateReqVO createReqVO, boolean preView) {
        AiVideoTaskPreviewRespVO respVO = new AiVideoTaskPreviewRespVO();
        //校验参数
        checkParamForAiMaterialClip(createReqVO,preView);
        //查看当前类型是否为必须传背景图的
        Integer videoLayoutType = createReqVO.getVideoLayoutType();
        if (VideoLayoutType.BACKGROUND_IMAGE.getType().equals(videoLayoutType) || VideoLayoutType.RANDOM
                .getType().equals(videoLayoutType)) {
            Long backgroundImageId = createReqVO.getBackgroundImageId();
            if (backgroundImageId == null) {
                throw exception(TASK_CREATE_TEMPLATE_BG_NULL);
            }
        }

        // 插入
        AiVideoTaskDO aiVideoTask = AiVideoTaskConvert.INSTANCE.convert(createReqVO);
        //登录用户id
        Long loginUserId = createReqVO.getUserId();
        aiVideoTask.setUserId(loginUserId);
        aiVideoTask.setClipType(AiClipType.AI_MATERIAL_CLIP.getType());
        //预览视频,只生成一条视频
        if(preView){
            aiVideoTask.setIsPreview(true);
            aiVideoTask.setVideoNum(1);
            aiVideoTask.setTitle("预览_"+aiVideoTask.getTitle());
        }
        //判断是否生成模板
        if(StrUtil.isNotEmpty(createReqVO.getTempName()) && null != createReqVO.getTempIndustry()){
            aiVideoTask.setIsTemplate(true);
        }
        //设置状态
        aiVideoTask.setStatus(AiTaskStatus.GENERATING.getStatus());
        aiVideoTaskMapper.insert(aiVideoTask);
        Long taskId = aiVideoTask.getId();

        //插入全局内容标题
        AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitle = createReqVO.getContentTitleVO();
        if(null != contentTitle){

            List<String> contentTitleList = contentTitle.getContentTitleList();
            if (contentTitleList != null) {
                insertContentTitleForAiMaterialClip(contentTitleList, taskId, null);
                //插入全局内容标题文字设置
                insertAiVideoTaskFontSet(contentTitle, taskId, null, FontSetFieldNameType.AI_VIDEO_TASK_CONTENT.getValue());
            }else {
                throw exception(TASK_CREATE_COPYWRITING_NULL);
            }
        }

        //插入全局字幕文案
        AiMaterialClipVideoTaskCopywritingCreateReqVO copywriting = createReqVO.getCopywritingVO();
        if(null != copywriting){

            List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList = copywriting.getCopywritingList();
            if (copywritingList != null) {
                insertCopywritingForAiMaterialClip(copywritingList, taskId, null);
                //插入全局字幕文案文字设置
                insertAiVideoTaskFontSet(copywriting, taskId, null, FontSetFieldNameType.AI_VIDEO_TASK_COPYINGWRITING.getValue());
            }else {
                throw exception(TASK_CREATE_COPYWRITING_NULL);
            }
        }

        //插入视频组排序
        List<AiClipVideoTaskGroupCreateReqVO> videoGroupList = createReqVO.getVideoGroupList().stream()
                .sorted(Comparator.comparing(AiClipVideoTaskGroupCreateReqVO::getSort, Comparator.naturalOrder())).collect(Collectors.toList());

        for (int i = 0 ; i < videoGroupList.size(); i++) {
            AiClipVideoTaskGroupCreateReqVO videoGroup = videoGroupList.get(i);

            AiClipVideoTaskGroupDO aiClipVideoTaskGroupDO = AiClipVideoTaskGroupConvert.INSTANCE.convert(videoGroup);
            aiClipVideoTaskGroupDO.setAiTaskId(taskId);
            aiClipVideoTaskGroupMapper.insert(aiClipVideoTaskGroupDO);
            Long videoGroupId = aiClipVideoTaskGroupDO.getId();
            //插入视频组内容标题
            AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVideoGroup = videoGroup.getContentTitleVO();
            if (null != contentTitleVideoGroup) {

                List<String> contentTitleList = contentTitleVideoGroup.getContentTitleList();
                if (contentTitleList != null) {
                    insertContentTitleForAiMaterialClip(contentTitleList, taskId, videoGroupId);
                    //插入视频组内容标题文字设置
                    insertAiVideoTaskFontSet(contentTitleVideoGroup, taskId, videoGroupId, FontSetFieldNameType.AI_VIDEO_TASK_GROUP_CONTENT.getValue());
                } else {
                    throw exception(TASK_CREATE_COPYWRITING_NULL);
                }

            }

            //插入视频组字幕文案
            AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVideoGroup = videoGroup.getCopywritingVO();
            if (null != copywritingVideoGroup) {

                List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList = copywritingVideoGroup.getCopywritingList();
                if (copywritingList != null) {
                    insertCopywritingForAiMaterialClip(copywritingList, taskId, videoGroupId);
                    //插入视频组字幕文案文字设置
                    insertAiVideoTaskFontSet(copywritingVideoGroup, taskId, videoGroupId, FontSetFieldNameType.AI_VIDEO_TASK_GROUP_COPYINGWRITING.getValue());
                } else {
                    throw exception(TASK_CREATE_COPYWRITING_NULL);
                }
            }

            //插入视频素材
            List<AiVideoTaskMaterialDO> doList = getMaterialListForAiMaterialClip(videoGroup.getMaterialList(), taskId, videoGroupId);
            aiVideoTaskMaterialMapper.insertBatch(doList);

        }

        //设置为模板
        if(null != aiVideoTask.getIsTemplate() && aiVideoTask.getIsTemplate()){
            TemplateCreateReqVO templateCreateReqVO = new TemplateCreateReqVO();
            templateCreateReqVO.setAiTaskId(taskId);
            templateCreateReqVO.setTempName(createReqVO.getTempName());
            templateCreateReqVO.setTempIndustry(createReqVO.getTempIndustry());
            templateCreateReqVO.setAspectRatioType(aiVideoTask.getAspectRatioType());
            templateCreateReqVO.setGroupNum(createReqVO.getVideoGroupList().size());
            templateCreateReqVO.setUserId(createReqVO.getUserId());

            templateService.createTemplate(templateCreateReqVO);
        }
        //生成任务
        JSONObject param = new JSONObject();
        param.set("id", taskId);
        param.set("type", ActiveTaskType.AI_MATERIAL_CLIP.getType());
        taskClient.createTask(TaskHandlerEnum.AI_CLIP.getValue(), null, param.toString());

        respVO.setId(taskId);
        return respVO;
    }


    @Override
    public List<Integer> previewCount(AiMaterialClipVideoTaskCreateReqVO createReqVO) {
        //校验参数
        checkParamForAiMaterialClip(createReqVO, true);

        //插入视频组排序
        List<AiClipVideoTaskGroupCreateReqVO> videoGroupList = createReqVO.getVideoGroupList().stream()
                .sorted(Comparator.comparing(AiClipVideoTaskGroupCreateReqVO::getSort, Comparator.naturalOrder())).collect(Collectors.toList());

        Integer copywritingSourceType = createReqVO.getCopywritingSourceType();
        AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO = createReqVO.getCopywritingVO();

        List<List<Map<String,Integer>>>  listarr = new ArrayList<>();
        for (int i = 0 ; i < videoGroupList.size(); i++) {
            AiClipVideoTaskGroupCreateReqVO videoGroup = videoGroupList.get(i);

            List<Map<String, Integer>> listarr1 = new ArrayList<>();
            List<AiMaterialClipVideoTaskMaterialCreateReqVO> materialList = videoGroup.getMaterialList();

            if(!GLOBAL_SUBTITLE_DUBBING.getType().equals(createReqVO.getVideoDurationType())){
                copywritingVO = videoGroup.getCopywritingVO();
                copywritingSourceType = videoGroup.getCopywritingSourceType();
            }

            for (int j = 1; j <= materialList.size(); j++) {
                AiMaterialClipVideoTaskMaterialCreateReqVO taskMaterialVO = materialList.get(j - 1);
                Map<String, Integer> map = new HashMap<>();
                Integer duration = calMaterialDuration(createReqVO.getVideoDurationType(), copywritingSourceType, copywritingVO, videoGroup, taskMaterialVO);
                map.put((i + 1) + "_" + j + "_" + taskMaterialVO.getFileId(), duration);
                listarr1.add(map);
            }
            listarr.add(listarr1);
        }

        //预览视频,返回预估生成数量
        return VideoCreateUtil.preViewCount(listarr);
    }

    /**
     * 获取算法所需的时长
     * @param videoDurationType 全局、随镜头组、还是固定时长
     * @param copywritingSourceType 手工还是自配音
     * @param copywritingVO
     * @param videoGroup
     * @param materialVO
     * @return
     */
    private Integer calMaterialDuration(Integer videoDurationType, Integer copywritingSourceType, AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO, AiClipVideoTaskGroupCreateReqVO videoGroup, AiMaterialClipVideoTaskMaterialCreateReqVO materialVO) {

        if(GLOBAL_SUBTITLE_DUBBING.getType().equals(videoDurationType)){
//            List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList = copywritingVO.getCopywritingList();
//            int copywritingIndex = copywritingVO.getCopywritingIndex();
//            if (copywritingIndex >= copywritingList.size()) {
//                copywritingVO.setCopywritingIndex(0);
//                copywritingIndex = 0;
//            } else {
//                copywritingVO.setCopywritingIndex(copywritingIndex + 1);
//            }
//            AiMaterialClipVideoTaskCopyingwritingContentVO copyingwritingContentVO = copywritingList.get(copywritingIndex);
//
//            String copywriting = copyingwritingContentVO.getCopywriting();
//            //判断字幕手工还是自配音
//            if (CopywritingSourceType.HAND.getType().equals(copywritingSourceType)) {
//                //手工字幕
//                return FileUtils.getTextReadDuration(copywriting);
//            } else {
//                //自配音
//                String audioUrl = copyingwritingContentVO.getUrl();
//                return FileUtils.getFileLengthAudio(audioUrl);
//            }
            //全局字幕，只能是随视频时长
            return materialVO.getDuration();
        } else if (FIXED_DURATION.getType().equals(videoDurationType)) {
            return videoGroup.getDuration();
        } else if (SET_WITH_LENS_GROUP.getType().equals(videoDurationType)) {
            Integer durationType = videoGroup.getDurationType();
            //随音频时长或者视频变速
            if (MaterialVideoDurationType.WITH_AUDIO.getType().equals(durationType) || MaterialVideoDurationType.WITH_AUDIO_AUTOMATIC_CHANGE_SPEED.getType().equals(durationType)) {
                List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList = copywritingVO.getCopywritingList();
                int copywritingIndex = copywritingVO.getCopywritingIndex();
                if (copywritingIndex >= copywritingList.size()) {
                    copywritingVO.setCopywritingIndex(0);
                    copywritingIndex = 0;
                } else {
                    copywritingVO.setCopywritingIndex(copywritingIndex + 1);
                }
                AiMaterialClipVideoTaskCopyingwritingContentVO copyingwritingContentVO = copywritingList.get(copywritingIndex);

                String copywriting = copyingwritingContentVO.getCopywriting();
                //判断字幕手工还是自配音
                if (CopywritingSourceType.HAND.getType().equals(copywritingSourceType)) {
                    //手工字幕
                    return FileUtils.getTextReadDuration(copywriting);
                } else {
                    //自配音
                    String audioUrl = copyingwritingContentVO.getUrl();
                    return FileUtils.getFileLengthAudio(audioUrl);
                }
            }
            //固定时长
            else if (MaterialVideoDurationType.FIXED_DURATION.getType().equals(durationType)) {
                return videoGroup.getDuration();
            }
            //视频原长
            else if (MaterialVideoDurationType.ORIGINAL_DURATION.getType().equals(durationType)) {
                return materialVO.getDuration();
            }
        }


        return materialVO.getDuration();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPreview(Long aiTaskId) {
        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(aiTaskId);
        if (aiVideoTaskDO == null) {
            throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
        }
        aiVideoTaskDO.setStatus(AiTaskStatus.COMPLETE.getStatus());

        aiVideoTaskMapper.updateById(aiVideoTaskDO);
    }

    private Long insertAiVideoTaskFontSet(BaseTextReqVO vo, Long taskId, Long videoGroupId, String fieldName) {
        AiVideoTaskFontSetDO aiVideoTaskFontSetDO = AiVideoTaskFontSetDO.builder().aiTaskId(taskId)
                .aiTaskId(taskId).videoGroupId(videoGroupId).fieldName(fieldName).value(JSONUtil.toJsonStr(vo)).build();
        aiVideoTaskFontSetMapper.insert(aiVideoTaskFontSetDO);

        return aiVideoTaskFontSetDO.getId();
    }

    private AiMaterialClipVideoTaskContentTitleCreateReqVO getContentTitleVO(Long taskId, Long videoGroupId, String fieldName) {
        AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO = null;

        //获取内容标题设置
        List<AiVideoTaskFontSetDO> contentTitleFontSetList = getAiVideoTaskFontSet(taskId, videoGroupId, fieldName);
        if(!CollectionUtil.isEmpty(contentTitleFontSetList)){
            AiVideoTaskFontSetDO contentTitleFontSetDO = contentTitleFontSetList.get(0);
            contentTitleVO = JSONUtil.toBean(contentTitleFontSetDO.getValue(), AiMaterialClipVideoTaskContentTitleCreateReqVO.class);
            //获取内容标题
            List<ContentTitleManagementDO> contentTitleList = getContentTitleList(taskId, videoGroupId);
            contentTitleVO.setContentTitleList(contentTitleList.stream().map(ContentTitleManagementDO::getContentTitle).collect(Collectors.toList()));
        }

        return contentTitleVO;
    }

    private AiMaterialClipVideoTaskCopywritingCreateReqVO getCopywritingVO(Long taskId, Long videoGroupId, String fieldName) {
        AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO = null;

        //获取字幕文案设置
        List<AiVideoTaskFontSetDO> copywritingFontSetList = getAiVideoTaskFontSet(taskId, videoGroupId, fieldName);
        if(!CollectionUtil.isEmpty(copywritingFontSetList)){
            AiVideoTaskFontSetDO copywritingFontSetDO = copywritingFontSetList.get(0);
            copywritingVO = JSONUtil.toBean(copywritingFontSetDO.getValue(), AiMaterialClipVideoTaskCopywritingCreateReqVO.class);
            //获取字幕文案
            List<CopyManagementDO> copywritingList = getCopywritingList(taskId, videoGroupId);
            copywritingVO.setCopywritingList(CopyManagementConvert.INSTANCE.convert(copywritingList));
        }
        return copywritingVO;
    }

    private List<AiVideoTaskFontSetDO> getAiVideoTaskFontSet(Long taskId, Long videoGroupId, String fieldName) {
        //获取文字设置
        LambdaQueryWrapperX<AiVideoTaskFontSetDO> qw = new LambdaQueryWrapperX<>();
        qw.eq(AiVideoTaskFontSetDO::getAiTaskId,taskId);
        qw.eq(AiVideoTaskFontSetDO::getFieldName,fieldName);
        if(null != videoGroupId){
            qw.eq(AiVideoTaskFontSetDO::getVideoGroupId, videoGroupId);
        } else {
            qw.isNull(AiVideoTaskFontSetDO::getVideoGroupId);
        }
        return aiVideoTaskFontSetMapper.selectList(qw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void aiClipVideo(Long taskId) {

        AiVideoTaskDO aiVideoTask = aiVideoTaskMapper.selectById(taskId);
        if (aiVideoTask == null) {
            log.error("任务不存在,taskId:{}", taskId);
            return;
        }

        //查看是否已经生成结果
        List<AiVideoResultDO> aiVideoResultList = aiVideoResultMapper
                .selectList(new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getAiTaskId, taskId));

        if (!aiVideoResultList.isEmpty()) {
            log.info("当前任务已经生成结果,taskId:{}", taskId);
            return;
        }
        Long userId = aiVideoTask.getUserId();

        //查素材切片
        List<VideoSliceDO> sliceDOList = videoSliceMapper
                .selectList(new LambdaQueryWrapperX<VideoSliceDO>().eq(VideoSliceDO::getTaskType,
                        ActiveTaskType.CLIP.getType()).eq(VideoSliceDO::getTaskId, taskId));

        List<CopyManagementDO> copyList = getCopyList(taskId);

        Integer videoNum = aiVideoTask.getVideoNum();
        for (int i = 0; i < videoNum; i++) {
            String copy = null;
            Integer duration;
            //生成的不带口播文案的视频
            if (copyList != null && !copyList.isEmpty()) {
                //随机获取一条
                copy = copyList.get(RandomUtils.nextInt(0, copyList.size())).getCopywriting();
                duration = FileUtils.getTextReadDuration(copy);
            } else {
                duration = aiVideoTask.getVideoDuration();
            }
            //生成切片
            List<VideoTrackClip> videoTrackClipList = VideoSlicer.getVideoTrackClipList(sliceDOList,
                    duration);

            AiVideoResultDO digitalResult = new AiVideoResultDO();
            digitalResult.setAiTaskId(taskId);
            digitalResult.setStatus(AiClipStatus.GENERATING.getStatus());
            digitalResult.setCreator(userId.toString());
            digitalResult.setUpdater(userId.toString());
            aiVideoResultMapper.insert(digitalResult);
            VideoClipOrderDTO videoClipOrder = createAiClipVideoTask(aiVideoTask, digitalResult.getId(),
                    copy, videoTrackClipList);
            AiVideoResultDO newDigitalResult = new AiVideoResultDO();
            newDigitalResult.setId(digitalResult.getId());
            newDigitalResult.setJobId(videoClipOrder.getJobId());
            newDigitalResult.setVideoOrderId(videoClipOrder.getId());
            newDigitalResult.setMediaUrl(videoClipOrder.getMediaUrl());
            aiVideoResultMapper.updateById(newDigitalResult);
        }

        //更新使用次数
        sliceDOList.forEach(videoSliceDO -> {
            if (videoSliceDO.getNum() > 0) {
                videoSliceMapper.updateById(new VideoSliceDO().setId(videoSliceDO.getId())
                        .setNum(videoSliceDO.getNum()));
            }
        });

    }

    @Override
    @Transactional
    public void aiMaterialVideoClipVideo(Long taskId) {

        AiVideoTaskDO aiVideoTask = aiVideoTaskMapper.selectById(taskId);
        if (aiVideoTask == null) {
            log.error("任务不存在,taskId:{}", taskId);
            return;
        }

        //查看是否已经生成结果
        List<AiVideoResultDO> aiVideoResultList = aiVideoResultMapper
                .selectList(new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getAiTaskId, taskId));

        if (!aiVideoResultList.isEmpty()) {
            log.info("当前任务已经生成结果,taskId:{}", taskId);
            return;
        }
        Long userId = aiVideoTask.getUserId();

        AiMaterialClipVideoTaskVO aiMaterialClipVideoTaskVO = AiVideoTaskConvert.INSTANCE.convertToAiMaterialClipVideoTaskVO(aiVideoTask);

        //获取全局内容标题及设置
        aiMaterialClipVideoTaskVO.setContentTitleVO(getContentTitleVO(taskId, null, FontSetFieldNameType.AI_VIDEO_TASK_CONTENT.getValue()));
        //获取全局字幕文案及设置
        aiMaterialClipVideoTaskVO.setCopywritingVO(getCopywritingVO(taskId, null, FontSetFieldNameType.AI_VIDEO_TASK_COPYINGWRITING.getValue()));
        AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO1 = aiMaterialClipVideoTaskVO.getCopywritingVO();
        Integer copywritingSourceType = aiMaterialClipVideoTaskVO.getCopywritingSourceType();
        List<AiClipVideoTaskGroupCreateReqVO> videoGroupList = new ArrayList<>();

        //获取视频组
        List<List<Map<String, Integer>>> listarr = new ArrayList<>();

        List<AiClipVideoTaskGroupDO> aiClipVideoTaskGroupDOList = aiClipVideoTaskGroupMapper.selectListByAiTaskId(taskId);
        for (int i = 1; i <= aiClipVideoTaskGroupDOList.size(); i++) {
            List<Map<String, Integer>> listarr1 = new ArrayList<>();
            AiClipVideoTaskGroupDO aiClipVideoTaskGroupDO = aiClipVideoTaskGroupDOList.get(i - 1);
            Long videoGroupId = aiClipVideoTaskGroupDO.getId();
            AiClipVideoTaskGroupCreateReqVO aiClipVideoTaskGroupCreateReqVO = AiClipVideoTaskGroupConvert.INSTANCE.convertToVO(aiClipVideoTaskGroupDO);
            //获取视频组内容标题及设置
            aiClipVideoTaskGroupCreateReqVO.setContentTitleVO(getContentTitleVO(taskId, videoGroupId, FontSetFieldNameType.AI_VIDEO_TASK_GROUP_CONTENT.getValue()));
            //获取视频组字幕文案及设置
            aiClipVideoTaskGroupCreateReqVO.setCopywritingVO(getCopywritingVO(taskId, videoGroupId, FontSetFieldNameType.AI_VIDEO_TASK_GROUP_COPYINGWRITING.getValue()));
            if(!GLOBAL_SUBTITLE_DUBBING.getType().equals(aiMaterialClipVideoTaskVO.getVideoDurationType())){
                copywritingVO1 = aiClipVideoTaskGroupCreateReqVO.getCopywritingVO();
                copywritingSourceType = aiClipVideoTaskGroupCreateReqVO.getCopywritingSourceType();
            }
            //获取视频组素材
            List<AiVideoTaskMaterialDO> aiVideoTaskMaterialDOS = aiVideoTaskMaterialMapper.selectListByVideoGroupId(taskId, videoGroupId);

            for (int j = 1; j <= aiVideoTaskMaterialDOS.size(); j++) {
                AiVideoTaskMaterialDO taskMaterialVO = aiVideoTaskMaterialDOS.get(j - 1);
                Map<String, Integer> map = new HashMap<>();

                //获取算法所需的时长
                Integer duration = calMaterialDuration(aiMaterialClipVideoTaskVO.getVideoDurationType(), copywritingSourceType, copywritingVO1, aiClipVideoTaskGroupCreateReqVO
                        , AiVideoTaskMaterialConvert.INSTANCE.convert1(taskMaterialVO));
                map.put(i + "_" + j + "_" + taskMaterialVO.getId(), duration);
                listarr1.add(map);
            }

            aiClipVideoTaskGroupCreateReqVO.setMaterialList(AiVideoTaskMaterialConvert.INSTANCE.convertList1(aiVideoTaskMaterialDOS));

            videoGroupList.add(aiClipVideoTaskGroupCreateReqVO);

            listarr.add(listarr1);
        }

        aiMaterialClipVideoTaskVO.setVideoGroupList(videoGroupList);

        //要生成的视频数量
        Integer videoNum = aiVideoTask.getVideoNum();
        int firstSize = 0;
        int secondSize = 0;
        Map<String, List<List<Map<String, Integer>>>> newMap = new HashMap<>();
        //获取不重复的视频组合
        List<List<Map<String, Integer>>> firstVideo = VideoCreateUtil.getFirstVideo(listarr);
        newMap.put("firstList", firstVideo);
        //要生成的视频数量大于不重复的视频
        firstSize = firstVideo.size();
        if (videoNum > firstSize) {
            //获取低重复的视频组合
            List<List<Map<String, Integer>>> secondVideo = VideoCreateUtil.getSecondVideo(listarr);
            secondSize = secondVideo.size();
            //获取低重复的视频组合
            newMap.put("secondList", secondVideo);
            if (videoNum > (firstSize + secondSize)) {
                //获取所有的视频组合
                List<List<Map<String, Integer>>> thirdVideo = VideoCreateUtil.getThirdVideoList(listarr);
                newMap.put("thirdList",thirdVideo);
            }
        }

        for (int i = 1; i <= videoNum; i++) {
            aiMaterialClipVideoTaskVO.setVideoTrackClipList(new ArrayList<>());
            aiMaterialClipVideoTaskVO.setAudioTrackClipList(new ArrayList<>());
            aiMaterialClipVideoTaskVO.setSubtitleTrackClipList(new ArrayList<>());
            String key = "firstList";
            RepeatRateType repeatRateType = RepeatRateType.NO;
            int index = i - 1;
            if (i > firstSize) {
                key = "secondList";
                repeatRateType = RepeatRateType.LOW;
                index = i - firstSize - 1;
                //如果视频生成数量超出，停止生成
                List<List<Map<String, Integer>>> secondList = newMap.get("secondList");
                //如果视频生成数量超出，停止生成
                List<List<Map<String, Integer>>> thirdList = newMap.get("thirdList");
                if(CollectionUtil.isEmpty(secondList) && CollectionUtil.isEmpty(thirdList)){
                    log.warn("当前素材混剪任务{},secondList、thirdList为空，停止生成！！！！！！！", aiVideoTask.getId());
                    break;
                }
                if (i > firstSize + secondSize) {
                    key = "thirdList";
                    repeatRateType = RepeatRateType.HIGH;
                    index = i - firstSize - secondSize - 1;

                    if(CollectionUtil.isEmpty(thirdList) || thirdList.size() <= index){
                        log.warn("当前素材混剪任务{},thirdList为空或者需要生成的视频数量大于能够生成的视频数量，停止生成！！！！！！！", aiVideoTask.getId());
                        break;
                    }
                }
            }
            //插入视频结果
            AiVideoResultDO digitalResult = new AiVideoResultDO();
            digitalResult.setAiTaskId(taskId);
            digitalResult.setStatus(AiClipStatus.GENERATING.getStatus());
            digitalResult.setVideoQuality(repeatRateType.getType());
            if(aiVideoTask.getIsTemplate() && i == 1){
                digitalResult.setIsTemplate(true);
            }
            digitalResult.setCreator(userId.toString());
            digitalResult.setUpdater(userId.toString());
            aiVideoResultMapper.insert(digitalResult);
            //插入单条视频剪切任务
            VideoClipOrderDTO videoClipOrder = null;
//            log.info("AI素材混剪视频taskId：{}, 组合：{}, key：{}, index：{}",taskId, newMap, key, index);
            Map<String, Integer> mapGroup = newMap.get(key).get(index).get(0);

            //全局内容文案
            AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVOGlobal = aiMaterialClipVideoTaskVO.getContentTitleVO();;
            //全局字幕配音
            AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO = aiMaterialClipVideoTaskVO.getCopywritingVO();

            //随机获取标题
            int indexTitle = -1;//-1取分镜头随机，否则取从全局统一下标
            String effectColor = null;
            if(CollectionUtil.isNotEmpty(contentTitleVOGlobal.getContentTitleList())) {
                Random rand = new Random();
                int tSize = contentTitleVOGlobal.getContentTitleList().size();
                indexTitle = rand.nextInt(tSize);
                if(contentTitleVOGlobal.getEffectColorSwitch()){
                    contentTitleVOGlobal.setEffectColorStyle(getEffectColor(new Random().nextInt(20)));//随机一个花字颜色
                }
            }

            //随机获取字幕
            int indexCopyWrite = -1;//-1取分镜头随机，否则取从全局统一下标
            String copyWriteEffectColor = null;
            if(CollectionUtil.isNotEmpty(copywritingVO.getCopywritingList())) {
                Random rand = new Random();
                int tSize = copywritingVO.getCopywritingList().size();
                indexCopyWrite = rand.nextInt(tSize);
                if(copywritingVO.getEffectColorSwitch()){
                    copyWriteEffectColor = getEffectColor(new Random().nextInt(20));//随机一个花字颜色
                }else{
                    copyWriteEffectColor = copywritingVO.getEffectColorStyle();
                }
            }

            for (String k : mapGroup.keySet()) {
                String[] arr = k.split("_");
                int indexOne = Integer.valueOf(arr[0]).intValue() - 1;
                int indexTwo = Integer.valueOf(arr[1]).intValue() - 1;

                AiClipVideoTaskGroupCreateReqVO aiClipVideoTaskGroupCreateReqVO = videoGroupList.get(indexOne);

                //如果不是全局字幕设置，则取各分镜的字幕配置
                if(!GLOBAL_SUBTITLE_DUBBING.getType().equals(aiMaterialClipVideoTaskVO.getVideoDurationType()) ){
                    //字幕配音
                    copywritingVO = aiClipVideoTaskGroupCreateReqVO.getCopywritingVO();
                }
                //内容标题,没有全局设置，则取镜头组标题设置
                AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO = aiClipVideoTaskGroupCreateReqVO.getContentTitleVO();
                if(CollectionUtil.isNotEmpty(contentTitleVOGlobal.getContentTitleList())){
                    contentTitleVO = contentTitleVOGlobal;
                }
                //循环各个分镜头组，并且给各分镜头组轨赋值
                createAiMaterialClipXTrackClip(aiClipVideoTaskGroupCreateReqVO.getMaterialList().get(indexTwo), copywritingVO, contentTitleVO, indexTitle,effectColor,indexCopyWrite, copyWriteEffectColor,aiMaterialClipVideoTaskVO, aiClipVideoTaskGroupCreateReqVO);
            }

            //创建视频合成任务
            videoClipOrder = createAiClipVideoTaskForMaterialClip(aiMaterialClipVideoTaskVO, digitalResult.getId());


            AiVideoResultDO newDigitalResult = new AiVideoResultDO();
            newDigitalResult.setId(digitalResult.getId());
            newDigitalResult.setJobId(videoClipOrder.getJobId());
            newDigitalResult.setVideoOrderId(videoClipOrder.getId());
            newDigitalResult.setMediaUrl(videoClipOrder.getMediaUrl());
            aiVideoResultMapper.updateById(newDigitalResult);

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAiClipVideoStatus(Long materialId, Integer duration, Integer status) {

        Integer aiClipStatus;
        if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
            aiClipStatus = AiClipStatus.SUCCESS.getStatus();
        } else {
            aiClipStatus = AiClipStatus.FAILURE.getStatus();
        }

        AiVideoResultDO aiVideoResult = aiVideoResultMapper
                .selectOne(new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getId, materialId));

        if (aiVideoResult == null) {
            throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
        }

        if (!AiClipStatus.GENERATING.getStatus().equals(aiVideoResult.getStatus())) {
            log.info("当前口播文案视频已经处理，materialId:{}", materialId);
            return;
        }

        //变更ai混剪视频结果
        updateVideoStatus(AiClipType.AI_CLIP, materialId, aiClipStatus,
                aiVideoResult.getAiTaskId(), duration,
                aiVideoResult.getMediaUrl(), aiVideoResult.getIsTemplate());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAiClipVideoStatusForMaterialClip(Long materialId, Integer duration, Integer status) {

        Integer aiClipStatus;
        if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
            aiClipStatus = AiClipStatus.SUCCESS.getStatus();
        } else {
            aiClipStatus = AiClipStatus.FAILURE.getStatus();
        }

        AiVideoResultDO aiVideoResult = aiVideoResultMapper
                .selectOne(new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getId, materialId));

        if (aiVideoResult == null) {
            throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
        }

        if (!AiClipStatus.GENERATING.getStatus().equals(aiVideoResult.getStatus())) {
            log.info("当前口播文案视频已经处理，materialId:{}", materialId);
            return;
        }

        //变更ai混剪视频结果
        updateVideoStatus(AiClipType.AI_MATERIAL_CLIP, materialId, aiClipStatus,
                aiVideoResult.getAiTaskId(), duration,
                aiVideoResult.getMediaUrl(), aiVideoResult.getIsTemplate());

    }

    private VideoClipOrderDTO createAiClipVideoTask(AiVideoTaskDO aiVideoTask, Long materialId,
                                                    String copy, List<VideoTrackClip> videoTrackClipList) {

        AiVideoClip aiVideoClip = new AiVideoClip();
        aiVideoClip.setUserId(aiVideoTask.getUserId());
        aiVideoClip.setMaterialId(materialId);
        aiVideoClip.setCopywriting(copy);

        aiVideoClip.setVideoTrackClips(videoTrackClipList);
        //获取模版
        TemplateTypeEnum templateType = getTemplateType(aiVideoTask);
        aiVideoClip.setTemplateTypeEnum(templateType);

        aiVideoClip.setOrderType(ActiveTaskType.CLIP.getType());
        //设置封面
        String coverUrl = getCoverUrl(aiVideoTask.getCoverId());
        aiVideoClip.setCoverImage(coverUrl);

        //背景图片
        String backgroundImageUrl = getBackgroundImageUrl(aiVideoTask.getBackgroundImageId());
        aiVideoClip.setBgImage(backgroundImageUrl);
        String voice = getRandomDubCode(aiVideoTask.getDubIdList());
        aiVideoClip.setDubCode(voice);

        //配音语速
        Integer speechRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubSpeechRate()).getValue();
        //配音语调
        Integer pitchRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubPitchRate()).getValue();
        aiVideoClip.setDubSpeechRate(speechRate);
        aiVideoClip.setDubPitchRate(pitchRate);
        aiVideoClip.setDubGain(aiVideoTask.getDubGain());
        //设置配音音乐
        String pipedMusicUrl = getRandomPipedMusic(aiVideoTask.getPipedMusicIdList());
        aiVideoClip.setPipedMusicUrl(pipedMusicUrl);
        aiVideoClip.setPipedMusicVolume(aiVideoTask.getPipedMusicVolume());
        aiVideoClip.setContentTitle(aiVideoTask.getContentTitle());
        aiVideoClip
                .setIsFlowerWord(SubtitleType.DYNAMIC.getType().equals(aiVideoTask.getSubtitleType()));
        return aiVideoClipService.firstClip(aiVideoClip);
    }

    /**
     * 各轨道赋值（含音轨、视频轨、字幕音频轨）
     * @param materialVO 单个视频的数据
     * @param copywritingCreateReqVO 字幕配音内容
     * @param contentTitleVO 内容标题
     * @param indexTitle 使用第几个标题,如果-1则随机取各分组标题，否则用全局
     * @param effectColor 全局花字
     * @param indexCopyWrite 使用第几个字幕,如果-1则随机取各分组字幕，否则用全局
     * @param copyWriteEffectColor 全局字幕花字
     * @param aiMaterialClipVideoTaskVO 整体页面提交的数据
     * @param videoGroupVO 单个镜头组组的视频组
     */
    private void createAiMaterialClipXTrackClip(AiMaterialClipVideoTaskMaterialCreateReqVO materialVO, AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingCreateReqVO,
                                                AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO, int indexTitle,String effectColor,int indexCopyWrite,String copyWriteEffectColor,
                                                AiMaterialClipVideoTaskVO aiMaterialClipVideoTaskVO,AiClipVideoTaskGroupCreateReqVO videoGroupVO) {
        //视频轨
        VideoTrackClip videoTrackClip = new VideoTrackClip();

        //音频轨
        BaseAudioTrackClip audioTrackClip = new AudioTrackClipAiForMaterialClip();
//        AudioTrackClip audioTrackClip = new AudioTrackClip();

        Integer totalDuration = 0;

        List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList = copywritingCreateReqVO.getCopywritingList();
        //传入的字幕不为空
        if(copywritingList.size()>0) {
            int copywritingIndex = copywritingCreateReqVO.getCopywritingIndex();
            if (copywritingIndex >= copywritingList.size()) {
                copywritingCreateReqVO.setCopywritingIndex(0);
                copywritingIndex = 0;
            } else {
                copywritingCreateReqVO.setCopywritingIndex(copywritingIndex + 1);
            }
            AiMaterialClipVideoTaskCopyingwritingContentVO copyingwritingContentVO = copywritingList.get(copywritingIndex);

            String copywriting = copyingwritingContentVO.getCopywriting();
            //字幕类型（分镜还是全局），自配音还是手工字幕文案，默认取视频组的字幕类型，如果是全局字幕文案，取全局
            Integer copywritingSourceType = videoGroupVO.getCopywritingSourceType();
            if(GLOBAL_SUBTITLE_DUBBING.getType().equals(aiMaterialClipVideoTaskVO.getVideoDurationType())){
                copywritingSourceType = aiMaterialClipVideoTaskVO.getCopywritingSourceType();
            }

            //设置字幕字体样式
            AIASREffect aiasrEffect = Convert.convert(AIASREffect.class, copywritingCreateReqVO);
            aiasrEffect.setY(null == aiasrEffect.getY() ? 0 : (aiasrEffect.getY() * copywriteConversion));
            aiasrEffect.setFontSize(null == aiasrEffect.getFontSize() ? 0 : (int) (aiasrEffect.getFontSize() * copywritefontConversion));
            aiasrEffect.setAlignment("TopCenter");//字幕居中展示，X轴会失效
            aiasrEffect.setAdaptMode("AutoWrap");
            aiasrEffect.setBackColour(null);//不需要背景色,设置和花字会冲突

            //随机花字开关开了，同时是分镜头的
            if( indexCopyWrite==-1){
                if(copywritingCreateReqVO.getEffectColorSwitch()) {
                    Random rand = new Random();
                    aiasrEffect.setEffectColorStyle(getEffectColor(rand.nextInt(20)));
                }
            }else {//使用全局花字
                aiasrEffect.setEffectColorStyle(copyWriteEffectColor);
            }

            //判断字幕手工还是自配音，设置对应的字体样式
            if (CopywritingSourceType.HAND.getType().equals(copywritingSourceType)) {
                //手工字幕
                totalDuration = FileUtils.getTextReadDuration(copywriting);
                audioTrackClip.setContent(copywriting);
                List<BaseEffect> baseEffects = audioTrackClip.getEffects();
                if (baseEffects == null) {
                    audioTrackClip.setEffects(new ArrayList<>());
                }
                audioTrackClip.getEffects().add(aiasrEffect);
            } else {
                //自配音
                String audioUrl = copyingwritingContentVO.getUrl();
                totalDuration = FileUtils.getFileLengthAudio(audioUrl);
                audioTrackClip = new AudioTrackClip();
                audioTrackClip.setMediaURL(audioUrl);
                List<BaseEffect> baseEffects = audioTrackClip.getEffects();
                if (baseEffects == null) {
                    audioTrackClip.setEffects(new ArrayList<>());
                }
                audioTrackClip.getEffects().add(aiasrEffect);
            }
        }

        //设置视频标题，跟随各视频轨走
        if(CollectionUtil.isNotEmpty(contentTitleVO.getContentTitleList())){
            TextEffect textEffect = Convert.convert(TextEffect.class, contentTitleVO);
            if(StringUtils.isNotEmpty(textEffect.getBackColour())){
                textEffect.setBorderStyle(3);
                textEffect.setOutline(3);
                textEffect.setOutlineColour(textEffect.getBackColour());
            }else{
                //字符串 "" 会导致花字失效
                textEffect.setBackColour(null);
                //随机花字开关开了，同时是分镜头的
                if(contentTitleVO.getEffectColorSwitch() && indexTitle==-1){
                    Random rand =new Random();
                    textEffect.setEffectColorStyle(getEffectColor(rand.nextInt(20)));
                }
            }

            List<String> tList = contentTitleVO.getContentTitleList();
            List<String> tList1 = new ArrayList<>();
            for(String s:tList){
                //去空操作，要不页面传了空字符串会被随机到
                if(StringUtils.isNotEmpty(s)){
                    tList1.add(s);
                }
            }
            int tSize = tList1.size();
            //分镜头标题随机获取标题
            if(indexTitle==-1){
                Random rand = new Random();
                indexTitle = rand.nextInt(tSize);
            }
            if(indexTitle>0 && indexTitle>=tSize){
                indexTitle=tSize-1;
            }
            textEffect.setContent(tList1.get(indexTitle));

            textEffect.setAdaptMode("AutoWrap");//自动换行
            if(StringUtils.isNotEmpty(contentTitleVO.getAlignment())){
                textEffect.setAlignment(contentTitleVO.getAlignment());//居中展示
            }else{
                textEffect.setFixedX(null == textEffect.getX() ? 0 :  (textEffect.getX() * titleConversion));
            }
            textEffect.setFixedY(null == textEffect.getY() ? 0 :  (textEffect.getY() * titleConversion));
            textEffect.setY(null);//FixedY 与Y不同时设置
            if(null != textEffect.getFontSize() ){
                textEffect.setFixedFontSize((int) (textEffect.getFontSize() * fontConversion));
                textEffect.setFontSize(null);
            }

            List<BaseEffect> baseEffects = videoTrackClip.getEffects();
            if(baseEffects == null){
                videoTrackClip.setEffects(new ArrayList<>());
            }
            videoTrackClip.getEffects().add(textEffect);
        }

        //生成轨道关联id
        String refId = IdWorker.getIdStr();

        if (materialVO.getDuration()!=null){
            videoTrackClip.setMaterialDuration(Float.valueOf(materialVO.getDuration()));
        }
        videoTrackClip.setMediaURL(materialVO.getUrl());

        Integer materialVideoDurationType = videoGroupVO.getDurationType();

        //如果不是全局字幕文案，全局字幕的走一个音频
        if(!GLOBAL_SUBTITLE_DUBBING.getType().equals(aiMaterialClipVideoTaskVO.getVideoDurationType())){
            //随音频时长
            if (MaterialVideoDurationType.WITH_AUDIO.getType().equals(materialVideoDurationType)) {
                audioTrackClip.setClipId(refId);
                videoTrackClip.setReferenceClipId(refId);
                int begintTime = getInAndOutTime(totalDuration,materialVO.getDuration());// 视频开始时间
                videoTrackClip.setIn(Float.valueOf(begintTime));// 视频开始时间
            }

            //固定时长
            else if (MaterialVideoDurationType.FIXED_DURATION.getType().equals(materialVideoDurationType)) {
                videoTrackClip.setClipId(refId);
                audioTrackClip.setReferenceClipId(refId);
                totalDuration = videoGroupVO.getDuration();// 用户输入的固定时长
                int begintTime = getInAndOutTime(totalDuration,materialVO.getDuration());// 视频开始时间
                videoTrackClip.setIn(Float.valueOf(begintTime));// 视频开始时间
                videoTrackClip.setOut(Float.valueOf(begintTime+totalDuration));// 视频结束时间
            }
            //视频原长
            else if (MaterialVideoDurationType.ORIGINAL_DURATION.getType().equals(materialVideoDurationType)) {
                videoTrackClip.setClipId(refId);
                audioTrackClip.setReferenceClipId(refId);
                if(totalDuration!=null && totalDuration>=3) {//不判断时长，太短会出现不能生成的问题
                    audioTrackClip.setNoReferTimelineOut(true);
                }
            }

            //视频变速
            else if (MaterialVideoDurationType.WITH_AUDIO_AUTOMATIC_CHANGE_SPEED.getType().equals(materialVideoDurationType)) {
                audioTrackClip.setClipId(refId);
                videoTrackClip.setReferenceClipId(refId);
                Float speed = NumberUtil.div(materialVO.getDuration(), totalDuration, 1, RoundingMode.HALF_UP).floatValue();
                videoTrackClip.setSpeed(speed);
            }
        }else{
            //固定时长
            if (MaterialVideoDurationType.FIXED_DURATION.getType().equals(materialVideoDurationType)) {
                videoTrackClip.setClipId(refId);
                totalDuration = videoGroupVO.getDuration();// 用户输入的固定时长
                int begintTime = getInAndOutTime(totalDuration,materialVO.getDuration());// 视频开始时间
                videoTrackClip.setIn((float) begintTime);// 视频开始时间
                videoTrackClip.setOut((float) (begintTime + totalDuration));// 视频结束时间

                //设置下全局字幕模式下，兼容分镜头固定时长的设置
                aiMaterialClipVideoTaskVO.setGroupVideoType(MaterialVideoDurationType.FIXED_DURATION.getType());
            }
        }

        //判断是否打开原声
        boolean isOriginal = videoGroupVO.getOriginal();
        VolumeEffect volumeEffect = new VolumeEffect();
        volumeEffect.setGain(isOriginal?1f:0f);//默认原声，不做放大
        List<BaseEffect> baseEffects = videoTrackClip.getEffects();
        if (baseEffects == null) {
            videoTrackClip.setEffects(new ArrayList<>());
        }
        videoTrackClip.getEffects().add(volumeEffect);

        //全局字幕文案,只有一条字幕音频轨，第二次循环的时候清掉
        if(GLOBAL_SUBTITLE_DUBBING.getType().equals(aiMaterialClipVideoTaskVO.getVideoDurationType())){
            if(CollectionUtil.isNotEmpty(aiMaterialClipVideoTaskVO.getAudioTrackClipList())){
                audioTrackClip = null;
                videoTrackClip.setReferenceClipId(null);
            }
        }

        //添加视频轨
        if(CollectionUtil.isNotEmpty(aiMaterialClipVideoTaskVO.getVideoTrackClipList())){
            aiMaterialClipVideoTaskVO.getVideoTrackClipList().add(videoTrackClip);
        }else {
            List<VideoTrackClip> videoTrackClips = new ArrayList<>();
            videoTrackClips.add(videoTrackClip);
            aiMaterialClipVideoTaskVO.setVideoTrackClipList(videoTrackClips);
        }

        //添加音频轨
        if(null != audioTrackClip && copywritingList.size()>0){
            if(CollectionUtil.isNotEmpty(aiMaterialClipVideoTaskVO.getAudioTrackClipList())){
                aiMaterialClipVideoTaskVO.getAudioTrackClipList().add(audioTrackClip);
            }else {
                List<BaseAudioTrackClip> audioTrackClips = new ArrayList<>();
                audioTrackClips.add(audioTrackClip);
                aiMaterialClipVideoTaskVO.setAudioTrackClipList(audioTrackClips);
            }
        }
    }

    /**
     * 获取视频截取开始时间
     * @param totalDur 要求总时长
     * @param videoDur 视频时长
     * @return
     */
    private int getInAndOutTime(int totalDur,int videoDur){
        if(totalDur==0 || videoDur==0){
            return 0;
        }
        if(videoDur<=totalDur){
            return 0;
        }
        Random rand = new Random();
        return rand.nextInt(videoDur-totalDur);
    }

    //随机取花字颜色
    private String getEffectColor(int index){
        String[] colors = new String[]{
                "CS0001-000001",
                "CS0001-000002",
                "CS0001-000003",
                "CS0001-000004",
                "CS0001-000005",
                "CS0001-000006",
                "CS0001-000007",
                "CS0001-000008",
                "CS0001-000009",
                "CS0001-000010",
                "CS0001-000011",
                "CS0001-000012",
                "CS0001-000013",
                "CS0001-000014",
                "CS0001-000015",
                "CS0001-000016",
                "CS0002-000001",
                "CS0002-000002",
                "CS0002-000003",
                "CS0002-000004",
        };
        return colors[index];
    }

    private VideoClipOrderDTO createAiClipVideoTaskForMaterialClip(AiMaterialClipVideoTaskVO aiVideoTask, Long materialId) {

        AiMaterialVideoClip aiVideoClip = new AiMaterialVideoClip();
        aiVideoClip.setUserId(aiVideoTask.getUserId());
        aiVideoClip.setMaterialId(materialId);

        aiVideoClip.setVideoTrackClips(aiVideoTask.getVideoTrackClipList());
        aiVideoClip.setAudioTrackClips(aiVideoTask.getAudioTrackClipList());
        aiVideoClip.setSubtitleTrackClipList(aiVideoTask.getSubtitleTrackClipList());

        //获取模版
        TemplateTypeEnum templateType = getTemplateTypeForMaterialClip(aiVideoTask);
        aiVideoClip.setTemplateTypeEnum(templateType);

        aiVideoClip.setOrderType(ActiveTaskType.CLIP.getType());
        //设置封面
        String coverUrl = getCoverUrlForMaterialClip(aiVideoTask.getCoverIds());
        aiVideoClip.setCoverImage(coverUrl);

        //背景图片
        String backgroundImageUrl = getBackgroundImageUrl(aiVideoTask.getBackgroundImageId());
        aiVideoClip.setBgImage(backgroundImageUrl);
        String voice = getRandomDubCode(aiVideoTask.getDubIdList());
        aiVideoClip.setDubCode(voice);

        //配音语速
        Integer speechRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubSpeechRate()).getValue();
        //配音语调
        Integer pitchRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubPitchRate()).getValue();
        aiVideoClip.setDubSpeechRate(speechRate);
        aiVideoClip.setDubPitchRate(pitchRate);
        aiVideoClip.setDubGain(aiVideoTask.getDubGain());
        //设置配音音乐
        String pipedMusicUrl = getRandomPipedMusic(aiVideoTask.getPipedMusicIdList());
        aiVideoClip.setPipedMusicUrl(pipedMusicUrl);
        aiVideoClip.setPipedMusicVolume(aiVideoTask.getPipedMusicVolume());

        aiVideoClip.setContentTrackClipList(aiVideoTask.getContentTrackClipList());
        if(GLOBAL_SUBTITLE_DUBBING.getType().equals(aiVideoTask.getVideoDurationType())){
            aiVideoClip.setIsGlobalSubtitleDubbing(true);
            aiVideoClip.setGroupVideoType(aiVideoTask.getGroupVideoType());
        }

        return aiVideoClipService.firstClipForMaterialClip(aiVideoClip);
    }

    private List<CopyManagementDO> getCopyList(Long taskId) {
        //获取口播文案
        return copyManagementMapper
                .selectList(new LambdaQueryWrapperX<CopyManagementDO>()
                        .eq(CopyManagementDO::getType, CopywritingType.AI_VIDEO.getType())
                        .eq(CopyManagementDO::getTaskId, taskId));
    }

    private List<CopyManagementDO> getCopywritingList(Long taskId, Long videoGroupId) {
        //获取口播文案
        LambdaQueryWrapperX<CopyManagementDO> qw = new LambdaQueryWrapperX<>();
        qw.eq(CopyManagementDO::getType, CopywritingType.AI_VIDEO.getType());
        qw.eq(CopyManagementDO::getTaskId,taskId);
        if(null != videoGroupId){
            qw.eq(CopyManagementDO::getVideoGroupId, videoGroupId);
        } else {
            qw.isNull(CopyManagementDO::getVideoGroupId);
        }
        return copyManagementMapper.selectList(qw);
    }

    private List<ContentTitleManagementDO> getContentTitleList(Long taskId, Long videoGroupId) {
        //获取内容标题
        LambdaQueryWrapperX<ContentTitleManagementDO> qw = new LambdaQueryWrapperX<>();
        qw.eq(ContentTitleManagementDO::getTaskId,taskId);
        if(null != videoGroupId){
            qw.eq(ContentTitleManagementDO::getVideoGroupId, videoGroupId);
        } else {
            qw.isNull(ContentTitleManagementDO::getVideoGroupId);
        }
        return contentTitleManagementMapper.selectList(qw);
    }

    private VideoClipOrderDTO newDigitalClip(AiVideoTaskDO aiVideoTask,
                                             AiVideoTaskMaterialDO virtualVideo, Long materialId, List<VideoTrackClip> videoTrackClipList)
            throws Exception {
        MetaHumanSecondVideoClip metaHumanSecondVideoClip = new MetaHumanSecondVideoClip();
        metaHumanSecondVideoClip.setUserId(aiVideoTask.getUserId());
        metaHumanSecondVideoClip.setMaterialId(materialId);
        metaHumanSecondVideoClip.setVideoTrackClips(videoTrackClipList);
        TemplateTypeEnum templateTypeEnum = getTemplateType(aiVideoTask);
        metaHumanSecondVideoClip.setTemplateTypeEnum(templateTypeEnum);
        metaHumanSecondVideoClip.setOrderType(ActiveTaskType.CLIP.getType());
        metaHumanSecondVideoClip.setVirtualVideoURL(virtualVideo.getUrl());
        String coverUrl = getCoverUrl(aiVideoTask.getCoverId());
        metaHumanSecondVideoClip.setCoverImage(coverUrl);

        String copywriter = getCopy(aiVideoTask.getId());
        metaHumanSecondVideoClip.setContent(copywriter);
        String randomDubCode = getRandomDubCode(aiVideoTask.getDubIdList());
        metaHumanSecondVideoClip.setVoice(randomDubCode);
        String randomPipedMusic = getRandomPipedMusic(aiVideoTask.getPipedMusicIdList());
        metaHumanSecondVideoClip.setBgMediaURL(randomPipedMusic);
        metaHumanSecondVideoClip.setBgMediaVolume(aiVideoTask.getPipedMusicVolume());
        String backgroundImageUrl = getBackgroundImageUrl(aiVideoTask.getBackgroundImageId());
        metaHumanSecondVideoClip.setBgImage(backgroundImageUrl);
        metaHumanSecondVideoClip.setTitle(aiVideoTask.getContentTitle());
        //配音语速
        Integer speechRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubSpeechRate()).getValue();
        //配音语调
        Integer pitchRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubPitchRate()).getValue();
        metaHumanSecondVideoClip.setSpeechRate(speechRate);
        metaHumanSecondVideoClip.setPitchRate(pitchRate);
        metaHumanSecondVideoClip.setVolume(aiVideoTask.getDubGain());
        metaHumanSecondVideoClip.setIsFlowerWord(
                SubtitleType.DYNAMIC.getType().equals(aiVideoTask.getSubtitleType()));

        return metaHumanVideoClipService
                .secondClip(metaHumanSecondVideoClip);
    }


    private String getMetaHumanVoiceCode(Long metaHumanId) {
        MetaHumanConfigDO metaHumanDO = getMetaHumanDO(metaHumanId);
        return metaHumanDO.getDubCode();
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<AiVideoResultRespVO>  getAiVideoTaskResultPage(
            AiVideoTaskResultPageReqVO pageVO) {
        return AiVideoResultConvert.INSTANCE.convertPage(
                aiVideoResultMapper.selectPage(pageVO, new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getAiTaskId, pageVO.getAiTaskId())
                        .eq(AiVideoResultDO::getStatus, AiClipStatus.SUCCESS.getStatus())
                        .orderByAsc(AiVideoResultDO::getId)));
    }

    @Override
    @Transactional(readOnly = true)
    public AiMaterialClipVideoResultRespVO  getAiMaterialClipVideoTaskResultPage(
            AiMaterialClipVideoResultPageReqVO pageVO) {
        AiMaterialClipVideoResultRespVO respVO = new AiMaterialClipVideoResultRespVO();
        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(pageVO.getAiTaskId());
        respVO.setAiTaskId(aiVideoTaskDO.getId());
        respVO.setVideoNum(aiVideoTaskDO.getVideoNum());

        //无重复视频
        Long noRepeatNum = aiVideoResultMapper.selectCount(new LambdaQueryWrapperX<AiVideoResultDO>()
                .eq(AiVideoResultDO::getAiTaskId, pageVO.getAiTaskId())
                .eq(AiVideoResultDO::getVideoQuality, RepeatRateType.NO.getType())
                .eq(AiVideoResultDO::getDeleted, false));
        respVO.setNoRepeatNum(Math.toIntExact(noRepeatNum));
        //低重复视频
        Long lowRepeatNum = aiVideoResultMapper.selectCount(new LambdaQueryWrapperX<AiVideoResultDO>()
                .eq(AiVideoResultDO::getAiTaskId, pageVO.getAiTaskId())
                .eq(AiVideoResultDO::getVideoQuality, RepeatRateType.LOW.getType())
                .eq(AiVideoResultDO::getDeleted, false));
        respVO.setLowRepeatNum(Math.toIntExact(lowRepeatNum));
        //普通视频
        Long highRepeatNum = aiVideoResultMapper.selectCount(new LambdaQueryWrapperX<AiVideoResultDO>()
                .eq(AiVideoResultDO::getAiTaskId, pageVO.getAiTaskId())
                .eq(AiVideoResultDO::getVideoQuality, RepeatRateType.HIGH.getType())
                .eq(AiVideoResultDO::getDeleted, false));
        respVO.setHighRepeatNum(Math.toIntExact(highRepeatNum));

        PageResult<AiVideoResultDO> resultDOPageList = aiVideoResultMapper.selectPage(pageVO, new LambdaQueryWrapperX<AiVideoResultDO>()
                .eq(AiVideoResultDO::getAiTaskId, pageVO.getAiTaskId())
                .eqIfPresent(AiVideoResultDO::getVideoQuality, pageVO.getVideoQuality())
                .eq(AiVideoResultDO::getStatus, AiClipStatus.SUCCESS.getStatus())
                .orderByAsc(AiVideoResultDO::getId));
        //已完成数量
        respVO.setCompletedNum(Math.toIntExact(resultDOPageList.getTotal()));

        respVO.setPageRespVO(AiVideoResultConvert.INSTANCE.convertPage1(resultDOPageList));

        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAiVideoTaskResult(List<Long> ids) {
        List<AiVideoResultDO> aiVideoResultList = aiVideoResultMapper
                .selectList(new LambdaQueryWrapperX<AiVideoResultDO>().in(AiVideoResultDO::getId, ids));
        if (aiVideoResultList == null || aiVideoResultList.isEmpty()) {
            throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
        }
        aiVideoResultList.forEach(aiVideoResultDO -> {
            if (!AiClipStatus.SUCCESS.getStatus().equals(aiVideoResultDO.getStatus())) {
                throw exception(TASK_CREATE_RESULT_STATUS_ERROR);
            }

            //同时根据文件地址删除file表里的记录 update by june
            try {
                fileApi.deleteFileByUrl(aiVideoResultDO.getMediaUrl());
            }catch (Exception ex){
                log.error("==================deleteAiVideoTaskResult:{}",ex);
            }
        });
        aiVideoResultMapper
                .delete(new LambdaQueryWrapperX<AiVideoResultDO>().in(AiVideoResultDO::getId, ids));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean templateAiVideoTaskResult(Long id) {
        AiVideoResultDO aiVideoResult = aiVideoResultMapper
                .selectById(id);
        if (aiVideoResult == null) {
            throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
        }

        if (!AiClipStatus.SUCCESS.getStatus().equals(aiVideoResult.getStatus())) {
            throw exception(TASK_CREATE_RESULT_STATUS_ERROR);
        }

        AiVideoResultDO newResult = new AiVideoResultDO();
        newResult.setAiTaskId(aiVideoResult.getAiTaskId());
        newResult.setIsTemplate(false);
        aiVideoResultMapper.updateByTaskId(newResult);

        aiVideoResult.setIsTemplate(true);
        return aiVideoResultMapper.updateById(aiVideoResult) == 1;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDigitalClipOnlyVideoTask(DigitalClipOnlyVideoCreateReqVO createReqVO) {
        //校验参数
        checkParam(false);

        AiVideoTaskDO aiVideoTask = AiVideoTaskConvert.INSTANCE.convert2(createReqVO);
        Long loginUserId = createReqVO.getUserId();
        aiVideoTask.setUserId(loginUserId);
        //设置数字人混剪类型
        aiVideoTask.setClipType(AiClipType.DIGITAL_CLIP_ONLY.getType());
        aiVideoTask.setStatus(AiTaskStatus.GENERATING.getStatus());
        aiVideoTask.setVideoNum(1);
        aiVideoTaskMapper.insert(aiVideoTask);

        Long taskId = aiVideoTask.getId();
        List<String> copywritingList = createReqVO.getCopywritingList();
        insertCopywriting(copywritingList, taskId);
        //获取随机口播文案
        String copy = copywritingList
                .get(RandomUtils.nextInt(0, copywritingList.size()));

        AiVideoResultDO digitalResult = new AiVideoResultDO();
        digitalResult.setAiTaskId(taskId);
        digitalResult.setStatus(AiClipStatus.GENERATING.getStatus());
        aiVideoResultMapper.insert(digitalResult);

        //创建数字人混剪任务
        VideoClipOrderDTO videoClipOrder = createDigitalClipOnlyVideoTask(aiVideoTask,
                digitalResult.getId(), copy);

        AiVideoResultDO newDigitalResult = new AiVideoResultDO();
        newDigitalResult.setId(digitalResult.getId());
        newDigitalResult.setJobId(videoClipOrder.getJobId());
        newDigitalResult.setVideoOrderId(videoClipOrder.getId());
        newDigitalResult.setMediaUrl(videoClipOrder.getMediaUrl());
        aiVideoResultMapper.updateById(newDigitalResult);

        return taskId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDigitalClipOnlyStatus(Long materialId, Integer duration, Integer status) {

        Integer aiClipStatus;
        if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
            aiClipStatus = AiClipStatus.SUCCESS.getStatus();
        } else {
            aiClipStatus = AiClipStatus.FAILURE.getStatus();
        }

        AiVideoResultDO aiVideoResult = aiVideoResultMapper
                .selectOne(new LambdaQueryWrapperX<AiVideoResultDO>()
                        .eq(AiVideoResultDO::getId, materialId));

        if (aiVideoResult == null) {
            throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
        }

        if (!AiClipStatus.GENERATING.getStatus().equals(aiVideoResult.getStatus())) {
            log.info("当前口播文案视频已经处理，materialId:{}", materialId);
            return;
        }

        //变更口播视频结果
        updateVideoStatus(AiClipType.DIGITAL_CLIP_ONLY, materialId,
                aiClipStatus,
                aiVideoResult.getAiTaskId(), duration, aiVideoResult.getMediaUrl(), aiVideoResult.getIsTemplate());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDigitalClipStatus(Long materialId, Integer videoType, Integer duration,
                                        Integer status) {

        //数字人视频素材
        if (VideoType.DIGITAL.getType().equals(videoType)) {

            AiVideoTaskMaterialDO aiVideoTaskMaterialDO = aiVideoTaskMaterialMapper
                    .selectById(materialId);
            if (aiVideoTaskMaterialDO == null) {
                throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
            }

            if (!TaskMaterialFileStatus.PROCESSING.getType().equals(aiVideoTaskMaterialDO.getStatus())) {
                log.info("当前数字人视频已经处理，materialId:{}", materialId);
                return;
            }

            if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
                //数字人素材视频生成成功
                aiVideoTaskMaterialMapper.updateById(new AiVideoTaskMaterialDO().setId(materialId)
                        .setDuration(duration)
                        .setStatus(TaskMaterialFileStatus.SUCCESS.getType()));
                //生成数字人混剪任务
                JSONObject object = new JSONObject();
                object.put("id", aiVideoTaskMaterialDO.getAiTaskId());
                object.put("taskType", UploadFileTaskType.AI_VIDEO);
                taskClient.createTask(TaskHandlerEnum.DIGITAL_CLIP.getValue(), null, object.toString());
            } else {
                //把任务变更为完成
                aiVideoTaskMapper.updateById(new AiVideoTaskDO().setId(aiVideoTaskMaterialDO.getAiTaskId())
                        .setStatus(AiTaskStatus.COMPLETE.getStatus()));
                //数字人素材视频生成失败
                aiVideoTaskMaterialMapper.updateById(new AiVideoTaskMaterialDO().setId(materialId)
                        .setStatus(TaskMaterialFileStatus.FAIL.getType()));
            }
        } else {
            //剪辑视频
            Integer aiClipStatus;
            if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
                aiClipStatus = AiClipStatus.SUCCESS.getStatus();
            } else {
                aiClipStatus = AiClipStatus.FAILURE.getStatus();
            }
            AiVideoResultDO aiVideoResult = aiVideoResultMapper
                    .selectOne(new LambdaQueryWrapperX<AiVideoResultDO>()
                            .eq(AiVideoResultDO::getId, materialId));

            if (aiVideoResult == null) {
                throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
            }

            if (!AiClipStatus.GENERATING.getStatus().equals(aiVideoResult.getStatus())) {
                log.info("当前数字人混剪视频已经处理，materialId:{}", materialId);
                return;
            }
            //变更数字人混剪视频结果
            updateVideoStatus(AiClipType.DIGITAL_CLIP, materialId,
                    aiClipStatus,
                    aiVideoResult.getAiTaskId(), duration, aiVideoResult.getMediaUrl(), aiVideoResult.getIsTemplate());

        }


    }

    private void updateVideoStatus(AiClipType clipType, Long materialId,
                                   Integer aiClipStatus,
                                   Long aiTaskId, Integer duration, String fileUrl, boolean isTemplate) {

        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectById(aiTaskId);
        if (aiVideoTaskDO == null) {
            throw exception(TASK_NOT_EXISTS);
        }
        Long userId = aiVideoTaskDO.getUserId();
        AiVideoResultDO newAiVideoResult = new AiVideoResultDO();
        newAiVideoResult.setId(materialId);
        newAiVideoResult.setStatus(aiClipStatus);
        newAiVideoResult.setDuration(duration);
        aiVideoResultMapper.updateById(newAiVideoResult);
        //视频剪辑成功,扣除用户金币
        Long coinNumber = 0L;
        if (AiClipStatus.SUCCESS.getStatus().equals(aiClipStatus)) {
            if (AiClipType.AI_CLIP.equals(clipType)) {
                //ai混剪视频
                //计算需要扣除的金币
                coinNumber = countCoinNumber(duration,
                        aiVideoTaskDO.getTenantId());
            } else if (AiClipType.AI_MATERIAL_CLIP.equals(clipType)) {
                //ai素材混剪视频
                coinNumber = countCoinNumber(duration,
                        aiVideoTaskDO.getTenantId());
                if(isTemplate){
                    //判断是否同时生成模板
                    templateService.updateTemplateByAiTaskId(TemplateDO.builder().
                            aiTaskId(aiTaskId).videoId(materialId).videoUrl(fileUrl).duration(duration).build());
                }
            } else if (AiClipType.DIGITAL_CLIP.equals(clipType)) {
                //数字人混剪视频
                coinNumber = countCoinNumber(duration,
                        aiVideoTaskDO.getTenantId());
            } else if (AiClipType.DIGITAL_CLIP_ONLY.equals(clipType)) {
                //数字人口播视频
                coinNumber = countCoinNumber(duration,
                        aiVideoTaskDO.getTenantId());
            }
        }
        aiVideoTaskMapper.addCoinNumAndStatus(aiTaskId, coinNumber,AiTaskStatus.COMPLETE.getStatus());

        if (coinNumber == 0L) {
            return;
        }
        //去除fileUrl里的域名
        String path = fileUrl.replace(iceProperties.getUrl(), "");
        String fileName = aiVideoTaskDO.getTitle() + "-" + materialId + ".mp4";
        materialService.createMaterial(fileName, path, fileUrl, 4, duration, userId.toString());

        if(!aiVideoTaskDO.getIsPreview()){
            //扣除金币
            walletApi.deduct(userId, coinNumber,
                    WalletLogTypeEnum.AI_CLIP_MERCHANT,
                    aiTaskId, materialId.toString(), WalletLogTypeEnum.AI_CLIP_MERCHANT.getName());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDigitalClipVideoTask(DigitalClipVideoCreateReqVO createReqVO) {
        //校验参数
        checkParam(false);
        //查看当前类型是否为必须传背景图的
        Integer videoLayoutType = createReqVO.getVideoLayoutType();
        if (VideoLayoutType.BACKGROUND_IMAGE.getType().equals(videoLayoutType) || VideoLayoutType.RANDOM
                .getType().equals(videoLayoutType)) {
            Long backgroundImageId = createReqVO.getBackgroundImageId();
            if (backgroundImageId == null) {
                throw exception(TASK_CREATE_TEMPLATE_BG_NULL);
            }
        }

        // 插入
        AiVideoTaskDO aiVideoTask = AiVideoTaskConvert.INSTANCE.convert3(createReqVO);
        //登录用户id
        Long loginUserId = createReqVO.getUserId();
        aiVideoTask.setUserId(loginUserId);
        aiVideoTask.setClipType(AiClipType.DIGITAL_CLIP.getType());
        //设置状态
        aiVideoTask.setStatus(AiTaskStatus.GENERATING.getStatus());
        //添加素材
        List<AiVideoTaskMaterialBaseVO> materialList = createReqVO.getMaterialList();
        validateMaterialList(materialList);
        aiVideoTaskMapper.insert(aiVideoTask);
        //添加口播文案
        Long taskId = aiVideoTask.getId();
        List<String> copywritingList = createReqVO.getCopywritingList();
        insertCopywriting(copywritingList, taskId);
        //获取随机口播文案
        String copy = copywritingList
                .get(RandomUtils.nextInt(0, copywritingList.size()));

        List<AiVideoTaskMaterialDO> doList = getMaterialList(materialList, taskId);
        aiVideoTaskMaterialMapper.insertBatch(doList);
        //生成视频切片
        videoSlice(doList, taskId);

        AiVideoTaskMaterialDO aiVideoTaskMaterialDO = new AiVideoTaskMaterialDO();
        aiVideoTaskMaterialDO.setAiTaskId(taskId);
        aiVideoTaskMaterialDO.setType(AiVideoTaskMaterialType.DIGITAL.getType());
        aiVideoTaskMaterialDO.setSort(0);
        aiVideoTaskMaterialDO.setStatus(TaskMaterialFileStatus.PROCESSING.getType());
        aiVideoTaskMaterialMapper.insert(aiVideoTaskMaterialDO);

        VideoClipOrderDTO videoClipOrderDTO = createDigitalClipVideoTask(aiVideoTask,
                aiVideoTaskMaterialDO.getId(), copy);

        AiVideoTaskMaterialDO updateAiVideoTaskMaterialDO = new AiVideoTaskMaterialDO();
        updateAiVideoTaskMaterialDO.setId(aiVideoTaskMaterialDO.getId());
        updateAiVideoTaskMaterialDO.setVideoOrderId(videoClipOrderDTO.getId());
        updateAiVideoTaskMaterialDO.setJobId(videoClipOrderDTO.getJobId());
        updateAiVideoTaskMaterialDO.setUrl(videoClipOrderDTO.getMediaUrl());
        aiVideoTaskMaterialMapper.updateById(updateAiVideoTaskMaterialDO);
        return taskId;
    }


    /**
     * 创建视频切片
     *
     * @param doList
     * @param taskId
     */
    private void videoSlice(List<AiVideoTaskMaterialDO> doList, Long taskId) {
        List<VideoSliceDO> sliceList = Lists.newArrayList();
        for (AiVideoTaskMaterialDO aiVideoTaskMaterialDO : doList) {
            List<AiVideoSliceVO> sliceVOList = VideoSlicer.videoSlice(aiVideoTaskMaterialDO.getUrl(),
                    aiVideoTaskMaterialDO.getDuration() != null ? aiVideoTaskMaterialDO.getDuration()
                            .floatValue() : null);
            for (AiVideoSliceVO aiVideoSliceVO : sliceVOList) {
                VideoSliceDO videoSliceDO = new VideoSliceDO();
                videoSliceDO.setTaskType(ActiveTaskType.CLIP.getType());
                videoSliceDO.setTaskId(taskId);
                videoSliceDO.setMaterialId(aiVideoTaskMaterialDO.getId());
                videoSliceDO.setSlice(JSONUtil.toJsonStr(aiVideoSliceVO));
                videoSliceDO.setSliceDuration(aiVideoSliceVO.getDuration());
                videoSliceDO.setNum(0);
                sliceList.add(videoSliceDO);
            }
        }
        videoSliceMapper.insertBatch(sliceList);
    }

    private VideoClipOrderDTO createDigitalClipVideoTask(AiVideoTaskDO aiVideoTask, Long materialId,
                                                         String copy) {

        MetaHumanVideoClip digitalClipVideo = new MetaHumanVideoClip();

        digitalClipVideo.setUserId(aiVideoTask.getUserId());
        digitalClipVideo.setMaterialId(materialId);
        digitalClipVideo.setCopywriting(copy);

        //配音语调
        Integer pitchRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubPitchRate()).getValue();
        digitalClipVideo.setDubPitchRate(pitchRate);
        //配音员code
        String dubCode = getRandomDubCode(aiVideoTask.getDubIdList());
        digitalClipVideo.setDubCode(dubCode);
        //配音语速
        Integer speechRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubSpeechRate()).getValue();
        digitalClipVideo.setDubSpeechRate(speechRate);
        //数字人code
        String metaHumanCode = getMetaHumanDO(aiVideoTask.getMetaHumanId()).getMetaHumanCode();
        digitalClipVideo.setMetaHumanCode(metaHumanCode);
        //数字人背景图
        String metaHumanBackgroundUrl = getBackgroundImageUrl(
                aiVideoTask.getMetaHumanBackgroundId());
        digitalClipVideo.setMetaHumanBackgroundUrl(metaHumanBackgroundUrl);
        digitalClipVideo.setOrderType(ActiveTaskType.CLIP.getType());
        return metaHumanVideoClipService.firstClip(digitalClipVideo);
    }

    private VideoClipOrderDTO createDigitalClipOnlyVideoTask(AiVideoTaskDO aiVideoTask,
                                                             Long materialId, String copy) {

        DigitalOnlyVideoClip digitalOnlyVideoClip = new DigitalOnlyVideoClip();
        digitalOnlyVideoClip.setUserId(aiVideoTask.getUserId());
        digitalOnlyVideoClip.setMaterialId(materialId);
        digitalOnlyVideoClip.setCopywriting(copy);
        digitalOnlyVideoClip.setDubGain(aiVideoTask.getDubGain());
        //配音语调
        Integer pitchRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubPitchRate()).getValue();
        digitalOnlyVideoClip.setDubPitchRate(pitchRate);
        //配音员code
        String dubCode = getRandomDubCode(aiVideoTask.getDubIdList());
        digitalOnlyVideoClip.setDubCode(dubCode);
        //配音语速
        Integer speechRate = AiSpeechRateAndPitchRateType
                .valueOfType(aiVideoTask.getDubSpeechRate()).getValue();
        digitalOnlyVideoClip.setDubSpeechRate(speechRate);
        //数字人code
        String metaHumanCode = getMetaHumanDO(aiVideoTask.getMetaHumanId()).getMetaHumanCode();
        digitalOnlyVideoClip.setMetaHumanCode(metaHumanCode);
        //数字人背景图
        String metaHumanBackgroundUrl = getBackgroundImageUrl(
                aiVideoTask.getMetaHumanBackgroundId());
        digitalOnlyVideoClip.setMetaHumanBackgroundUrl(metaHumanBackgroundUrl);
        //内容标题
        digitalOnlyVideoClip.setContentTitle(aiVideoTask.getContentTitle());

        digitalOnlyVideoClip
                .setIsFlowerWord(SubtitleType.DYNAMIC.getType().equals(aiVideoTask.getSubtitleType()));

        //设置配音音乐
        String pipedMusicUrl = getRandomPipedMusic(aiVideoTask.getPipedMusicIdList());
        digitalOnlyVideoClip.setPipedMusicUrl(pipedMusicUrl);
        digitalOnlyVideoClip.setPipedMusicVolume(aiVideoTask.getPipedMusicVolume());

        return digitalOnlyVideoClipService.firstClip(digitalOnlyVideoClip);
    }

    private void checkParam(boolean isPreview) {
        //判断当前是否有生成中的任务
        List<AiVideoTaskDO> oldList = aiVideoTaskMapper.selectList(
                new LambdaQueryWrapper<AiVideoTaskDO>()
                        .eq(AiVideoTaskDO::getStatus, AiTaskStatus.GENERATING.getStatus()));

        if (oldList != null && !oldList.isEmpty()) {
            throw exception(TASK_CREATE_VIDEO_ING);
        }

        if(!isPreview){
            //查看当前用户的金币资产是否为负
            checkUserWallet(TenantContextHolder.getTenantId());
        }
    }

    private void checkParamForAiMaterialClip(AiMaterialClipVideoTaskCreateReqVO createReqVO, boolean isPreview) {
        checkParam(isPreview);

        //时长设置类型判断
        VideoDurationType videoDurationType = valueOfType(createReqVO.getVideoDurationType());
        switch (videoDurationType) {
            case SET_WITH_LENS_GROUP:
                //随镜头组
                break;
            case GLOBAL_SUBTITLE_DUBBING:
                //全局字幕配音
                AiMaterialClipVideoTaskCopywritingCreateReqVO copywriting = createReqVO.getCopywritingVO();
                if (null == copywriting || CollectionUtil.isEmpty(copywriting.getCopywritingList())) {
                    throw exception(TASK_CREATE_COPYWRITING_NULL);
                }
                break;
            case FIXED_DURATION:
                //固定时长
                Integer videoDuration = createReqVO.getVideoDuration();
                if (null == videoDuration || videoDuration < 1) {
                    throw exception(TASK_CREATE_VIDEO_DURATION_NULL);
                }
                break;

            default:
                break;
        }

        //封面类型为自定义时判断用户是否上传了图片
        if(CoverSelectType.CUSTOMIZE.getType().equals(createReqVO.getCoverType()) && CollectionUtil.isEmpty(createReqVO.getCoverIds())){
            throw exception(TASK_CREATE_COVER_IMAGE_NULL);
        }

    }


    private String getCopy(Long taskId) {
        List<CopyManagementDO> copyList = getCopyList(taskId);
        //随机获取一条
        CopyManagementDO copyManagementDO = copyList
                .get(RandomUtils.nextInt(0, copyList.size()));
        return copyManagementDO.getCopywriting();
    }

}
