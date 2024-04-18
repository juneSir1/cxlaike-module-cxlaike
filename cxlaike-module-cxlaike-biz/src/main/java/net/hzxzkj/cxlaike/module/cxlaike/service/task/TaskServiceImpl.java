package net.hzxzkj.cxlaike.module.cxlaike.service.task;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.enums.CommonStatusEnum;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppPOIRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.TaskExtRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiVideoSliceVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.ShopRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPauseReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo.MerchantTaskExtRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo.TaskExtBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.store.StoreConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.task.TaskConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial.AiVideoTaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.copymanagement.CopyManagementDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.region.RegionDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store.StoreDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig.SysTakeLevelConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskext.TaskExtDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskfile.TaskFileDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterial.TaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialslice.TaskMaterialSliceDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videoslice.VideoSliceDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.copymanagement.CopyManagementMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.region.RegionMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.store.StoreMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.systakelevelconfig.SysTakeLevelConfigMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.task.TaskMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskext.TaskExtMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskfile.TaskFileMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterial.TaskMaterialMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterialslice.TaskMaterialSliceMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskorder.TaskOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videoslice.VideoSliceMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AppTaskOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.CopywritingType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SwitchType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SysTakeLevelType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskMaterialFileStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskMaterialFileType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskMaterialSliceStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.OrderStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.SettleStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.material.MaterialService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.FileUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.VideoSlicer;
import net.hzxzkj.cxlaike.module.infra.api.file.dto.MaterialRespVO;
import net.hzxzkj.cxlaike.module.infra.enums.MaterialTypeEnum;
import net.hzxzkj.cxlaike.module.merchant.api.user.MerchantUserApi;
import net.hzxzkj.cxlaike.module.merchant.api.user.dto.MerchantUserExtRespDTO;
import net.hzxzkj.cxlaike.module.merchant.api.user.dto.MerchantUserRespDTO;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.pay.api.wallet.WalletApi;
import net.hzxzkj.cxlaike.module.pay.api.wallet.dto.WalletDTO;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletLogTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletTypeEnum;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

/**
 * 商家发布任务 Service 实现类
 *
 * @author 宵征源码
 */
@Slf4j
@Service
@Validated
public class TaskServiceImpl implements TaskService {

  @Resource
  private TaskMapper taskMapper;
  @Resource
  private TaskExtMapper taskExtMapper;
  @Resource
  private SysTakeLevelConfigMapper sysTakeLevelConfigMapper;
  @Resource
  private MaterialService materialService;
  @Resource
  private TaskMaterialMapper taskMaterialMapper;
  @Resource
  private TaskFileMapper taskFileMapper;
  @Resource
  private CopyManagementMapper copyManagementMapper;
  //  @Resource
//  private TaskMaterialSliceMapper taskMaterialSliceMapper;
  @Resource
  private WalletApi walletApi;
  @Resource
  @Lazy
  private TaskClient taskClient;
  @Resource
  private TaskOrderMapper taskOrderMapper;
  @Resource
  private StoreMapper storeMapper;
  @Resource
  private RegionMapper regionMapper;
  @Resource
  private MerchantUserApi merchantUserApi;
  @Resource
  private VideoSliceMapper videoSliceMapper;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long createTask(TaskCreateReqVO createReqVO) {
    //用户id
    Long loginUserId = TenantContextHolder.getTenantId();
    //判断当前门店是否存在
    Set<Long> shopIds = createReqVO.getShopIds();

    //当前任务结束时间是否小于当前时间
    verifyEndTime(createReqVO.getEndTime());

    verifyCopy(createReqVO.getCopywritingList());

    verifyShop(shopIds);

    //判断当前是否需要上传视频素材
    List<TaskMaterialVO> materialList = validateMaterialList(createReqVO.getIsShop(),
        createReqVO.getTaskType(),
        createReqVO.getMaterialList());

    // 插入
    TaskDO task = TaskConvert.INSTANCE.convert(createReqVO);

    task.setMerchantUserId(loginUserId);
    task.setStatus(TaskStatus.NOT_STARTED.getStatus());
    task.setIsAudit(Boolean.TRUE);
    task.setIdempotentId(IdWorker.getIdStr());
    taskMapper.insert(task);
    //插入口播文案
    insertCopywriting(createReqVO.getCopywritingList(), task.getId());
    //插入素材
    insertMaterial(loginUserId, materialList, task.getId());
    //插入拓展数据,冻结金额
    insertExt(createReqVO.getTaskExtList(), task);
    //添加任务开始调度任务
    JSONObject object = new JSONObject();
    object.put("taskId", task.getId());
    object.put("switchType", SwitchType.ON);
    taskClient
        .createTask(TaskHandlerEnum.UPDATE_MERCHANT_TASK_STATUS.getValue(), task.getStartTime(),
            object.toString());

    return task.getId();
  }

  private void verifyCopy(List<String> copywritingList) {
    if (copywritingList == null || copywritingList.isEmpty()) {
      return;
    }
    copywritingList.forEach(copy -> {
      if (StringUtils.isEmpty(copy)) {
        throw exception(TASK_CREATE_KOUBO_CONTENT_NULL);
      }
    });
  }

  private void verifyEndTime(LocalDateTime endTime) {
    if (endTime.isBefore(LocalDateTime.now())) {
      throw exception(TASK_CREATE_END_TIME_ERROR);
    }
  }

  private void verifyShop(Set<Long> shopIds) {
    shopIds.forEach(shopId -> {
      StoreDO storeDO = storeMapper.selectById(shopId);
      if (storeDO == null) {
        throw exception(STORE_NOT_EXISTS);
      }
      if (!CommonStatusEnum.ENABLE.getStatus().equals(storeDO.getStatus())) {
        throw exception(STORE_CLOSED);
      }
    });
  }

  private void insertExt(List<TaskExtBaseVO> taskExtBaseList, TaskDO task) {
    //获取系统配置的等级费率
    List<SysTakeLevelConfigDO> systemLevelList = getSystemLevelConfig(task);
    if (systemLevelList == null || systemLevelList.isEmpty()) {
      log.error("系统等级参数未设置!");
      throw exception(SYSTEM_ERROR);
    }

    List<TaskExtDO> taskExtList = taskExtBaseList.stream().map(
        taskExtBaseVO -> {
          if (taskExtBaseVO.getNumber() == null || taskExtBaseVO.getNumber() <= 0) {
            throw exception(TASK_CREATE_NUM_NULL);
          }
          TaskExtDO taskExtDO = new TaskExtDO();
          SysTakeLevelConfigDO configDO = systemLevelList.stream().filter(config ->
              config.getLevelType().equals(taskExtBaseVO.getLevel())).findFirst()
              .orElse(null);
          if (configDO == null) {
            log.error("系统等级参数未设置!");
            throw exception(SYSTEM_ERROR);
          }
          taskExtDO.setTaskId(task.getId());
          taskExtDO.setLevel(taskExtBaseVO.getLevel());
          taskExtDO.setMemberRatio(configDO.getMemberRatio());
          taskExtDO.setMemberOneRatio(configDO.getMemberOneRatio());
          taskExtDO.setMemberTwoRatio(configDO.getMemberTwoRatio());
          taskExtDO.setMerchantRatio(configDO.getMerchantRatio());
          taskExtDO.setSystemRatio(configDO.getSystemRatio());
          taskExtDO.setFee(configDO.getFee());
          //设置达人服务费
          taskExtDO.setMemberFee(taskExtDO.getFee() * taskExtDO.getMemberRatio() / 100);
          taskExtDO.setTotalNum(taskExtBaseVO.getNumber());
          taskExtDO.setTotalFee(taskExtDO.getFee() * taskExtDO.getTotalNum());
          taskExtDO.setSurplusNum(taskExtBaseVO.getNumber());
          return taskExtDO;
        }
    ).collect(Collectors.toList());
    //当前任务需要冻结的金额
    Long taskFreezeAmount = 0L;
    for (TaskExtDO taskExtDO : taskExtList) {
      taskFreezeAmount += taskExtDO.getTotalFee();
    }
    MerchantUserRespDTO user = merchantUserApi.getUser(task.getMerchantUserId());
    WalletDTO walletDTO = walletApi.get(user.getTenantId(), WalletTypeEnum.MERCHANT);

    if (walletDTO == null || walletDTO.getBalance() < taskFreezeAmount) {
      log.error("当前钱包余额不足或钱包不存在!");
      throw exception(TASK_CREATE_WALLET_NOT_ENOUGH);
    }
    taskExtMapper.insertBatch(taskExtList);
    //冻结任务金额
    walletApi.freeze(user.getTenantId(), taskFreezeAmount, WalletTypeEnum.MERCHANT,
        WalletLogTypeEnum.TASK_FREEZE_MERCHANT, task.getId(),
        task.getIdempotentId(), task.getTitle());

  }

  private void insertCopywriting(List<String> copywritingList, Long taskId) {
    //插入口播文案
    List<CopyManagementDO> copyManagementDOList = copywritingList.stream()
        .map(copywriting -> {
          CopyManagementDO copyManagementDO = new CopyManagementDO();
          copyManagementDO.setTaskId(taskId);
          copyManagementDO.setCopywriting(copywriting);
          copyManagementDO.setType(CopywritingType.EXPLORE_TASK.getType());
          return copyManagementDO;
        }).collect(Collectors.toList());

    copyManagementMapper.insertBatch(copyManagementDOList);
  }

  /**
   * 插入素材
   *
   * @param materialList 素材列表
   * @param taskId       任务Id
   */
  private void insertMaterial(Long memberUserId, List<TaskMaterialVO> materialList, Long taskId) {
    //查询文件夹类型一级下的所有文件
    List<TaskMaterialDO> taskMaterialList = Lists.newArrayList();

    if (materialList == null || materialList.isEmpty()) {
      return;
    }
    List<TaskFileDO> taskFileList = Lists.newArrayList();
    materialList.forEach(taskFile -> {
      TaskFileDO taskFileDO = new TaskFileDO();
      taskFileDO.setTaskId(taskId);
      taskFileDO.setFileId(taskFile.getFileId());
      taskFileDO.setName(taskFile.getFolderName());
      taskFileDO.setSort(taskFile.getSort());
      taskFileDO.setTaskType(ActiveTaskType.SHOPPING.getType());
      taskFileList.add(taskFileDO);
      //查询文件信息
      List<MaterialRespVO> materialRespList = materialService
          .getVideoMaterialByFileId(taskFile.getFileId());
      if (materialRespList != null && !materialRespList.isEmpty()) {
        List<TaskMaterialDO> newTaskMaterialList = materialRespList.stream().map(
            resp -> {
              TaskMaterialDO taskMaterialDO = new TaskMaterialDO();
              taskMaterialDO.setTaskId(taskId);
              taskMaterialDO.setMemberUserId(memberUserId);
              taskMaterialDO.setType(TaskMaterialFileType.MATERIAL.getType());
              taskMaterialDO.setMaterialUrl(resp.getUrl());
              taskMaterialDO.setFolderName(taskFile.getFolderName());
              taskMaterialDO.setFileName(resp.getName());
              taskMaterialDO.setStatus(TaskMaterialFileStatus.SUCCESS.getType());
              taskMaterialDO.setSort(taskFile.getSort());
              return taskMaterialDO;
            }
        ).collect(Collectors.toList());
        taskMaterialList.addAll(newTaskMaterialList);
      }
    });
    taskFileMapper.insertBatch(taskFileList);
    //插入任务素材
    taskMaterialMapper.insertBatch(taskMaterialList);

  }

  private List<SysTakeLevelConfigDO> getSystemLevelConfig(TaskDO task) {
    //系统任务类型
    SysTakeLevelType sysTakeLevelType = null;
    //任务类型
    Integer taskType = task.getTaskType();
    switch (taskType) {
      case 1:
        //实探
        if (!task.getIsShop()) {
          //真人出镜
          sysTakeLevelType = SysTakeLevelType.ENTITY_REAL_SHOOTING;
        } else {
          //现场拍摄
          sysTakeLevelType = SysTakeLevelType.ENTITY_SHOOTING;
        }
        break;
      case 2:
        //云探
        if (AiClipType.AI_CLIP.getType().equals(task.getClipType())) {
          sysTakeLevelType = SysTakeLevelType.YUN_AI_CLIP;
        } else {
          sysTakeLevelType = SysTakeLevelType.YUN_DIGITAL_MIX_CLIP;
        }
        break;
      default:
        return null;
    }

    return sysTakeLevelConfigMapper
        .selectList(new LambdaQueryWrapper<SysTakeLevelConfigDO>()
            .eq(SysTakeLevelConfigDO::getType, sysTakeLevelType.getType()));

  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateTask(TaskUpdateReqVO updateReqVO) {

    Long loginUserId = TenantContextHolder.getTenantId();
    // 校验存在
    TaskDO taskDO = validateTaskExists(updateReqVO.getId());

    if (!TaskStatus.NOT_STARTED.getStatus().equals(taskDO.getStatus())) {
      throw exception(TASK_CREATE_DELETE_ERROR);
    }

    verifyEndTime(updateReqVO.getEndTime());

    //判断当前是否需要上传视频素材
    List<TaskMaterialVO> materialList = validateMaterialList(updateReqVO.getIsShop(),
        updateReqVO.getTaskType(),
        updateReqVO.getMaterialList());

    // 更新
    TaskDO updateTask = TaskConvert.INSTANCE.convert(updateReqVO);

    Long taskId = updateTask.getId();

    // 删除之前的文案插入新的口播文案
    copyManagementMapper
        .delete(
            new LambdaQueryWrapper<CopyManagementDO>().eq(CopyManagementDO::getTaskId, taskId));
    insertCopywriting(updateReqVO.getCopywritingList(), taskId);

    //删除之前素材插入新素材
    taskFileMapper
        .delete(new LambdaQueryWrapper<TaskFileDO>().eq(TaskFileDO::getTaskId, taskId));
    taskMaterialMapper.delete(
        new LambdaQueryWrapper<TaskMaterialDO>().eq(TaskMaterialDO::getTaskId, taskId));
    insertMaterial(loginUserId, materialList, taskId);

    //查询之前的任务扩展信息,
    List<TaskExtDO> taskExtList = taskExtMapper
        .selectList(new LambdaQueryWrapper<TaskExtDO>().eq(TaskExtDO::getTaskId, taskId));
    if (taskExtList != null && !taskExtList.isEmpty()) {
      // 获取应该解冻金额
      Long unfreezeAmount = taskExtList.stream().mapToLong(TaskExtDO::getTotalFee).sum();
      MerchantUserRespDTO user = merchantUserApi.getUser(taskDO.getMerchantUserId());
      //解冻金额
      walletApi.unfreeze(user.getTenantId(), unfreezeAmount, WalletTypeEnum.MERCHANT,
          WalletLogTypeEnum.TASK_UNFREEZE_MERCHANT, taskId,
          taskDO.getIdempotentId(), WalletLogTypeEnum.TASK_UNFREEZE_MERCHANT.getName());
      //删除之前的任务扩展信息
      taskExtMapper.delete(new LambdaQueryWrapper<TaskExtDO>().eq(TaskExtDO::getTaskId, taskId));
    }
    updateTask.setMerchantUserId(loginUserId);
    updateTask.setIdempotentId(IdWorker.getIdStr());
    updateTask.setIsAudit(Boolean.TRUE);
    taskMapper.updateById(updateTask);
    insertExt(updateReqVO.getTaskExtList(), updateTask);
    //添加任务开始调度任务
    JSONObject object = new JSONObject();
    object.put("taskId", updateTask.getId());
    object.put("switchType", SwitchType.ON);
    taskClient
        .createTask(TaskHandlerEnum.UPDATE_MERCHANT_TASK_STATUS.getValue(),
            updateTask.getStartTime(),
            object.toString());
  }

  private List<TaskMaterialVO> validateMaterialList(Boolean isShop, Integer taskType,
      List<TaskMaterialVO> materialList) {
    if (TaskType.YUN.getType().equals(taskType)) {
      if (materialList == null || materialList.isEmpty()) {
        throw exception(TASK_CREATE_VIDEO_NULL);
      }
      validateMaterialList(materialList);
    } else {
      if (isShop == null || !isShop) {
        if (materialList == null || materialList.isEmpty()) {
          throw exception(TASK_CREATE_VIDEO_NULL_2);
        }
        validateMaterialList(materialList);
      }
    }

    return materialList;
  }

  private void validateMaterialList(List<TaskMaterialVO> materialList) {
    for (TaskMaterialVO taskMaterialVO : materialList) {
      if (taskMaterialVO.getFileId() == null) {
        throw exception(TASK_CREATE_MATERIAL_ID_NULL_2);
      }
      if (taskMaterialVO.getFolderName() == null || taskMaterialVO.getFolderName().isEmpty()) {
        throw exception(TASK_CREATE_VIDEO_GROUP_NAME_NULL_2);
      }
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteTask(Long id) {
    // 校验存在
    TaskDO taskDO = validateTaskExists(id);

    if (!TaskStatus.NOT_STARTED.getStatus().equals(taskDO.getStatus())) {
      throw exception(TASK_CREATE_DELETE_ERROR);
    }
    // 删除
    taskMapper.deleteById(id);
  }

  private TaskDO validateTaskExists(Long id) {
    TaskDO taskDO = taskMapper.selectById(id);
    if (taskDO == null) {
      throw exception(TASK_CREATE_NOT_EXISTS);
    }
    return taskDO;
  }

  @Override
  @Transactional(readOnly = true)
  public TaskRespVO getTask(Long id) {
    TaskDO taskDO = taskMapper.selectById(id);
    TaskRespVO resp = TaskConvert.INSTANCE.convert(taskDO);
    if (resp == null) {
      return null;
    }
    List<ShopRespVO> shopRespList = getShopList(resp.getShopIds());
    resp.setShopList(shopRespList);
    resp.setMerchantId(taskDO.getMerchantUserId());
    //素材展示
    List<TaskMaterialVO> fileList =
        getTaskMaterialList(id);

    resp.setMaterialList(fileList);

    //口播文案
    List<String> copyList = getCopywritingList(id);
    resp.setCopywritingList(copyList);

    List<String[]> areaRequireList = getAreaRequireList(resp.getAreaRequire());
    resp.setAreaRequireList(areaRequireList);

    //任务扩展展示
    List<MerchantTaskExtRespVO> extBaseList = taskExtMapper
        .selectList(new LambdaQueryWrapper<TaskExtDO>().eq(TaskExtDO::getTaskId, id))
        .stream().map(ext -> {
          MerchantTaskExtRespVO taskExtBaseVO = new MerchantTaskExtRespVO();
          taskExtBaseVO.setLevel(ext.getLevel());
          taskExtBaseVO.setTotalNum(ext.getTotalNum());
          taskExtBaseVO.setNumber(ext.getSurplusNum());
          return taskExtBaseVO;
        }).collect(
            Collectors.toList());

    resp.setTaskExtList(extBaseList);
    //设置任务状态
    if (TaskStatus.SETTLED.getStatus().equals(resp.getStatus())) {
      resp.setStatus(TaskStatus.COMPLETED.getStatus());
    }
    return resp;
  }

  private List<TaskMaterialVO> getTaskMaterialList(Long taskId) {

    List<TaskFileDO> taskFileDOS = taskFileMapper.selectList(
        new LambdaQueryWrapper<TaskFileDO>().eq(TaskFileDO::getTaskId, taskId)
            .eq(TaskFileDO::getTaskType, ActiveTaskType.SHOPPING.getType()).orderByAsc(
            TaskFileDO::getSort));

    List<TaskMaterialVO> fileList = taskFileDOS.stream().map(taskFileDO -> {
      TaskMaterialVO taskMaterialVO = new TaskMaterialVO();
      taskMaterialVO.setFolderName(taskFileDO.getName());
      taskMaterialVO.setFileId(taskFileDO.getFileId());
      taskMaterialVO.setSort(taskFileDO.getSort());
      MaterialRespVO material = materialService.getMaterial(taskFileDO.getFileId());
      if (material != null) {
        taskMaterialVO.setMaterialPath(material.getMaterialPath()
            .replace(TenantContextHolder.getTenantId() + "/" + MaterialTypeEnum
                .getEnumByCode(material.getMaterialType()), ""));
      }
      return taskMaterialVO;
    }).collect(Collectors.toList());
    return fileList;
  }

  private List<String> getCopywritingList(Long id) {
    List<String> copyList = copyManagementMapper.selectList(
        new LambdaQueryWrapper<CopyManagementDO>().eq(CopyManagementDO::getTaskId, id)
            .eq(CopyManagementDO::getType, CopywritingType.EXPLORE_TASK.getType())).stream()
        .map(CopyManagementDO::getCopywriting).collect(Collectors.toList());
    return copyList;
  }

  private List<ShopRespVO> getShopList(Set<Long> shopIds) {
    if (shopIds == null || shopIds.isEmpty()) {
      return null;
    }
    List<StoreDO> storeList = storeMapper.selectList(
        new LambdaQueryWrapperX<StoreDO>().in(StoreDO::getId, shopIds).eq(StoreDO::getStatus,
            CommonStatusEnum.ENABLE.getStatus()));
    if (storeList == null || storeList.isEmpty()) {
      return null;
    }
    return storeList.stream().map(storeDO -> {
      ShopRespVO shop = new ShopRespVO();
      shop.setId(storeDO.getId());
      shop.setName(storeDO.getName());
      return shop;
    }).collect(Collectors.toList());

  }

  @Override
  @Transactional(readOnly = true)
  public PageResult<TaskPageRespVO> getTaskPage(TaskPageReqVO pageReqVO) {
    PageResult<TaskPageRespVO> page = TaskConvert.INSTANCE
        .convertPage1(taskMapper.selectPage(pageReqVO));
    List<TaskPageRespVO> list = page.getList();
    if (list == null || list.isEmpty()) {
      return page;
    }
    //设置发布人数,报名人数,总费用
    list.forEach(taskPageRespVO -> {
      List<TaskExtDO> taskExtList = taskExtMapper.selectList(
          new LambdaQueryWrapper<TaskExtDO>().eq(TaskExtDO::getTaskId, taskPageRespVO.getId()));
      int publishNum = 0;
      int applyNum = 0;
      long totalFee = 0L;
      for (TaskExtDO ext : taskExtList) {
        publishNum = publishNum + ext.getTotalNum();
        totalFee = totalFee + ext.getTotalFee();
        applyNum = applyNum + (ext.getTotalNum() - ext.getSurplusNum());
      }
      taskPageRespVO.setPublishNum(publishNum);
      taskPageRespVO.setApplyNum(applyNum);
      taskPageRespVO.setTotalFee(totalFee);

      //设置任务状态
      if (TaskStatus.SETTLED.getStatus().equals(taskPageRespVO.getStatus())) {
        taskPageRespVO.setStatus(TaskStatus.COMPLETED.getStatus());
      }
    });

    return page;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateTaskStatus(Long id, SwitchType switchType) {

    //查询当前任务状态是否为未开始
    TaskDO taskDO = taskMapper.selectById(id);

    if (taskDO == null) {
      throw exception(TASK_CREATE_NOT_EXISTS);
    }
    Integer status;
    if (SwitchType.ON.equals(switchType)) {
      if (!TaskStatus.NOT_STARTED.getStatus().equals(taskDO.getStatus())) {
        throw exception(TASK_CREATE_STATUS_ERROR);
      }

      //查看当前时间是否已经到可以开始的时间
      if (taskDO.getStartTime().compareTo(LocalDateTime.now()) > 0) {
        throw exception(TASK_CREATE_STATUS_ERROR_2);
      }

      //查看当前任务是否需要生成视频 云探需要ai剪辑
      if (TaskType.YUN.getType().equals(taskDO.getTaskType())) {
        //切割片段
        //获取任务素材
        List<TaskMaterialDO> materialList = getMaterialList(id);
        videoSlice(materialList, taskDO.getId());

      }
      status = TaskStatus.IN_PROGRESS.getStatus();

    } else {
      if (!(TaskStatus.IN_PROGRESS.getStatus().equals(taskDO.getStatus()) || TaskStatus.SUSPENDED
          .getStatus().equals(taskDO.getStatus()))) {
        throw exception(TASK_CREATE_STATUS_ERROR);
      }
      //查看当前时间是否已经到可以结束的时间
      if (taskDO.getEndTime().compareTo(LocalDateTime.now()) > 0) {
        throw exception(TASK_CREATE_STATUS_ERROR_3);
      }
      status = TaskStatus.COMPLETED.getStatus();
    }

    //更新任务状态
    TaskDO updateTask = new TaskDO();
    updateTask.setId(id);
    updateTask.setStatus(status);
    taskMapper.updateById(updateTask);
    //如果是进行中的任务需要发送关闭任务通知
    if (TaskStatus.IN_PROGRESS.getStatus().equals(status)) {
      JSONObject object = new JSONObject();
      object.put("taskId", taskDO.getId());
      object.put("switchType", SwitchType.OFF);
      //添加任务结束调度任务
      taskClient
          .createTask(TaskHandlerEnum.UPDATE_MERCHANT_TASK_STATUS.getValue(), taskDO.getEndTime(),
              object.toString());
    }

    if (TaskStatus.COMPLETED.getStatus().equals(status)) {
      //任务结束后需要将任务金额结算
      taskClient
          .createTask(TaskHandlerEnum.SETTLEMENT_MERCHANT_TASK.getValue(), null,
              taskDO.getId().toString());
    }

  }

  /**
   * 创建视频切片
   *
   * @param doList
   * @param taskId
   */
  private void videoSlice(List<TaskMaterialDO> doList, Long taskId) {
    List<VideoSliceDO> sliceList = com.google.common.collect.Lists.newArrayList();
    for (TaskMaterialDO taskMaterial : doList) {
      List<AiVideoSliceVO> sliceVOList = VideoSlicer
          .videoSlice(taskMaterial.getMaterialUrl(), null);
      for (AiVideoSliceVO aiVideoSliceVO : sliceVOList) {
        VideoSliceDO videoSliceDO = new VideoSliceDO();
        videoSliceDO.setTaskType(ActiveTaskType.SHOPPING.getType());
        videoSliceDO.setTaskId(taskId);
        videoSliceDO.setMaterialId(taskMaterial.getId());
        videoSliceDO.setSlice(JSONUtil.toJsonStr(aiVideoSliceVO));
        videoSliceDO.setSliceDuration(aiVideoSliceVO.getDuration());
        videoSliceDO.setNum(0);
        sliceList.add(videoSliceDO);
      }
    }
    videoSliceMapper.insertBatch(sliceList);
  }

  @Override
  @Transactional(readOnly = true)
  public PageResult<AppTaskPageRespVO> getTaskAppPage(AppTaskPageReqVO pageVO) {
    Page<AppTaskPageRespVO> page = new Page<>(pageVO.getPageNo(),
        pageVO.getPageSize());
    Page<AppTaskPageRespVO> resp = taskMapper
        .selectAppPage(pageVO.getTitle(), pageVO.getTaskType(), pageVO.getLevelType(),
            pageVO.getServiceFee(),
            pageVO.getFansRequire(), pageVO.getArea(), pageVO.getIsDesc(),
            pageVO.getProfessionCode(), page);
    return new PageResult<>(resp.getRecords(), resp.getTotal());
  }

  @Override
  @Transactional(readOnly = true)
  public AppTaskRespVO getAppTask(Long id) {
    TaskDO taskDO = taskMapper.selectById(id);

    AppTaskRespVO resp = TaskConvert.INSTANCE.convert2(taskDO);

    if (resp == null) {
      return null;
    }

    Long maxServiceFee = 0L;
//    Long maxShareEarn = 0L;
    List<TaskExtRespVO> extList = Lists.newArrayList();
    //设置任务扩展
    List<TaskExtDO> taskExtLost = taskExtMapper
        .selectList(new LambdaQueryWrapper<TaskExtDO>().eq(TaskExtDO::getTaskId, id));
    int totalApplyNum = 0;
    int currentApplyNum = 0;
    for (TaskExtDO ext : taskExtLost) {
      TaskExtRespVO extResp = new TaskExtRespVO();
      extResp.setLevelType(ext.getLevel());
      extResp.setServiceFee(ext.getMemberFee());
      extResp.setCurrentApplyNum(ext.getTotalNum() - ext.getSurplusNum());
      //获取最大服务费用
      if (ext.getMemberFee() > maxServiceFee) {
        maxServiceFee = ext.getMemberFee();
//        maxShareEarn = ext.getMemberFee() * ext.getMemberTwoRatio() / 100;
      }
      extResp.setTotalApplyNum(ext.getTotalNum());
      extList.add(extResp);
      totalApplyNum = totalApplyNum + ext.getTotalNum();
      currentApplyNum = currentApplyNum + (ext.getTotalNum() - ext.getSurplusNum());
    }
    //设置一级地区code
    List<String[]> areaRequireList = getAreaRequireList(resp.getAreaRequire());
    resp.setAreaRequireList(areaRequireList);
    //设置口播文案
    List<String> copywritingList = getCopywritingList(taskDO.getId());
    resp.setCopywritingList(copywritingList);
    resp.setAreaRequire(this.getAreaRequireName(resp.getAreaRequire()));
    resp.setTaskExtList(extList);
    resp.setMaxServiceFee(maxServiceFee);
    resp.setTotalApplyNum(totalApplyNum);
    resp.setCurrentApplyNum(currentApplyNum);
    resp.setProfessions(getProfessions(taskDO.getMerchantUserId()));

    resp.setTaskStatus(getTaskStatus(taskDO.getStatus()));
    Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
    if (loginUserId != null) {
      //判断当前用户是否有已接单的任务
      if (AppTaskOrderStatus.UNDERWAY.getStatus().equals(resp.getTaskStatus())) {
        List<TaskOrderDO> orderList = taskOrderMapper
            .selectList(new LambdaQueryWrapper<TaskOrderDO>()
                .eq(TaskOrderDO::getMemberUserId, loginUserId)
                .eq(TaskOrderDO::getTaskId, id)
                .notIn(TaskOrderDO::getStatus, OrderStatusEnum.CANCEL.getCode(),
                    OrderStatusEnum.PASS.getCode()));
        if (orderList != null && orderList.size() > 0) {
          resp.setTaskStatus(AppTaskOrderStatus.SIGN_UP.getStatus());
        }
      }
    }
    return resp;
  }

  private Integer getTaskStatus(Integer status) {
    //任务状态 (0.进行中,1.已结束,2.已暂停,5.已报名)
    if (TaskStatus.IN_PROGRESS.getStatus().equals(status)) {
      return AppTaskOrderStatus.UNDERWAY.getStatus();
    }
    if (TaskStatus.COMPLETED.getStatus().equals(status)) {
      return AppTaskOrderStatus.FINISH.getStatus();
    }
    if (TaskStatus.SETTLED.getStatus().equals(status)) {
      return AppTaskOrderStatus.FINISH.getStatus();
    }
    if (TaskStatus.SUSPENDED.getStatus().equals(status)) {
      return AppTaskOrderStatus.PAUSE.getStatus();
    }
    return null;
  }

  private String[] getProfessions(Long merchantUserId) {
    MerchantUserExtRespDTO userExt = merchantUserApi.getUserExtById(merchantUserId);
    if (userExt == null) {
      return null;
    }
    return new String[]{userExt.getProfessionOne(), userExt.getProfessionTwo(),
        userExt.getProfessionThree()};
  }

  private String getAreaRequireName(String areaRequire) {
    if (StringUtils.isBlank(areaRequire)) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    String[] split = areaRequire.split(";");
    for (String area : split) {
      RegionDO regionDO = regionMapper
          .selectOne(new LambdaQueryWrapperX<RegionDO>().eq(RegionDO::getCode, area));
      sb.append(regionDO.getName());
      sb.append(";");
    }
    return sb.toString();
  }

  private List<String[]> getAreaRequireList(String areaRequire) {
    List<String[]> list = Lists.newArrayList();
    if (!StringUtils.isEmpty(areaRequire)) {
      String[] split = areaRequire.split(";");
      for (String area : split) {
        //更具二级code获取一级code
        RegionDO regionDO = regionMapper
            .selectOne(new LambdaQueryWrapperX<RegionDO>().eq(RegionDO::getCode, area));
        if (regionDO != null) {
          String[] areaArr = {String.valueOf(regionDO.getPid()),
              String.valueOf(regionDO.getCode())};
          list.add(areaArr);
        }
      }
    }
    return list;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void settlementMerchantTask(Long taskId) {

    //查询任务信息
    TaskDO task = taskMapper.selectById(taskId);

    if (task == null || !(TaskStatus.COMPLETED.getStatus().equals(task.getStatus()))) {
      log.error("任务不存在或任务状态不正确:taskId:{}", taskId);
      return;
    }

    //查看当前进行中的订单信息
    List<TaskOrderDO> taskOrderList = taskOrderMapper.selectList(
        new LambdaQueryWrapperX<TaskOrderDO>().eq(TaskOrderDO::getTaskId, taskId)
            .in(TaskOrderDO::getStatus,
                OrderStatusEnum.UNDERWAY.getCode(), OrderStatusEnum.TO_BE_REVIEWED.getCode(),
                OrderStatusEnum.TURN_DOWN.getCode()));

    if (!CollectionUtils.isEmpty(taskOrderList)) {
      log.error("任务存在进行中的订单:taskId:{}", taskId);
      return;
    }

    //查看待结算的订单
    List<TaskOrderDO> taskPassOrderList = taskOrderMapper.selectList(
        new LambdaQueryWrapperX<TaskOrderDO>().eq(TaskOrderDO::getTaskId, taskId)
            .eq(TaskOrderDO::getStatus, OrderStatusEnum.PASS.getCode())
            .eq(TaskOrderDO::getSettleStatus,
                SettleStatusEnum.BE_SETTLE.getCode()));

    if (!CollectionUtils.isEmpty(taskPassOrderList)) {
      log.error("任务存在待结算的订单:taskId:{}", taskId);
      return;
    }

    //结算任务
    taskMapper.updateById(new TaskDO().setId(taskId).setStatus(TaskStatus.SETTLED.getStatus()));

    //查看当前剩余任务金额
    List<TaskExtDO> taskExtList = taskExtMapper
        .selectList(new LambdaQueryWrapperX<TaskExtDO>().eq(TaskExtDO::getTaskId, taskId));

    Long totalFee = 0L;
    for (TaskExtDO taskExt : taskExtList) {
      if (taskExt.getSurplusNum() > 0) {
        totalFee = totalFee + (taskExt.getSurplusNum() * taskExt.getFee());
      }
    }

    //结算任务金额
    if (totalFee == 0) {
      log.info("当前任务没有剩余任务:taskId:{}", taskId);
      return;
    }
    MerchantUserRespDTO user = merchantUserApi.getUser(task.getMerchantUserId());
    //解冻金额
    walletApi.unfreeze(user.getTenantId(), totalFee,
        WalletTypeEnum.MERCHANT, WalletLogTypeEnum.TASK_UNFREEZE_MERCHANT, task.getId(),
        task.getIdempotentId(), WalletLogTypeEnum.TASK_UNFREEZE_MERCHANT.getName());


  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<AppPOIRespVO> getPOIList(Long taskId) {
    TaskDO taskDO = taskMapper.selectById(taskId);
    if (taskDO == null) {
      throw exception(new ErrorCode(400, "任务不存在"));
    }
    Set<Long> shopIds = taskDO.getShopIds();
    List<StoreDO> storeList = storeMapper.selectList(
        new LambdaQueryWrapperX<StoreDO>().in(StoreDO::getId, shopIds).eq(StoreDO::getStatus,
            CommonStatusEnum.ENABLE.getStatus()));

    return StoreConvert.INSTANCE.convertList1(storeList);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void pauseTask(TaskPauseReqVO taskPauseReqVO) {
    // 校验存在
    TaskDO taskDO = validateTaskExists(taskPauseReqVO.getId());

    Boolean isPause = taskPauseReqVO.getIsPause();
    Integer status;
    if (isPause) {
      // 校验状态
      if (!TaskStatus.IN_PROGRESS.getStatus().equals(taskDO.getStatus())) {
        throw exception(TASK_CREATE_STATUS_ERROR_4);
      }
      status = TaskStatus.SUSPENDED.getStatus();
    } else {
      // 校验状态
      if (!TaskStatus.SUSPENDED.getStatus().equals(taskDO.getStatus())) {
        throw exception(TASK_CREATE_STATUS_ERROR_5);
      }
      status = TaskStatus.IN_PROGRESS.getStatus();
    }
    taskMapper.updateById(new TaskDO().setId(taskPauseReqVO.getId()).setStatus(status));
  }

  @Override
  public Long getMerchantCountOfPublishTask(List<Long> merchantIds) {
    return taskMapper.selectMerchantCountOfPublishTask(merchantIds);
  }

  /**
   * 获取视频素材
   *
   * @param taskId 任务Id
   * @return
   */
  private List<TaskMaterialDO> getMaterialList(Long taskId) {
    return taskMaterialMapper
        .selectList(
            new LambdaQueryWrapper<TaskMaterialDO>().eq(TaskMaterialDO::getTaskId, taskId).eq(
                TaskMaterialDO::getType, TaskMaterialFileType.MATERIAL.getType()));
  }

  private List<TaskMaterialSliceDO> aiClip(Integer
      videoNum, List<TaskMaterialDO> materialList,
      Long taskId) {
    return digitalClip(videoNum, materialList, taskId);
  }

  private List<TaskMaterialSliceDO> digitalClip(Integer videoNum,
      List<TaskMaterialDO> materialList, Long taskId) {

    List<TaskMaterialSliceDO> taskMaterialSliceList = Lists.newArrayList();

    //获取口播文案集合
    List<CopyManagementDO> copyManagementList = copyManagementMapper.selectList(
        new LambdaQueryWrapperX<CopyManagementDO>().eq(CopyManagementDO::getTaskId, taskId));

    if (copyManagementList == null || copyManagementList.isEmpty()) {
      throw exception(new ErrorCode(400, "当前任务未配置口播文案,任务Id:" + taskId));
    }

    List<List<AiVideoSliceVO>> materialSliceList = getVideoSliceList(materialList);
    VideoSlicer videoSlicer = new VideoSlicer(materialSliceList);
    for (int i = 0; i < videoNum; i++) {
      List<AiVideoSliceVO> sliceList = Lists.newArrayList();

      //随机获取一条口播文案
      String copywrite = getRandomCopywrite(copyManagementList);
      //口播长度
      Integer textReadDuration = FileUtils.getTextReadDuration(copywrite);
      //需要视频切片数量
      Integer sliceNum = FileUtils.getSliceNum(textReadDuration);
      int j = 0;
      while (true) {
        sliceList = videoSlicer.getSlice(sliceList, sliceNum);
        if (sliceList.size() < sliceNum) {
          videoSlicer = new VideoSlicer(materialSliceList);
          if (j >= 500) {
            throw exception(new ErrorCode(400, "视频切片失败,任务Id:" + taskId));
          }
        } else {
          break;
        }
        j++;
      }
      List<VideoTrackClip> videoTrackClips = sliceList.stream().map(slice -> {
        VideoTrackClip videoTrackClip = new VideoTrackClip();
        videoTrackClip.setMediaURL(slice.getUrl());
        videoTrackClip.setIn(slice.getIn());
        videoTrackClip.setOut(slice.getOut());
        return videoTrackClip;
      }).collect(Collectors.toList());
      TaskMaterialSliceDO taskMaterialSlice = new TaskMaterialSliceDO();
      taskMaterialSlice.setTaskId(taskId);
      taskMaterialSlice.setParam(JSONUtil.toJsonStr(videoTrackClips));
      taskMaterialSlice.setStatus(TaskMaterialSliceStatus.UNUSED.getStatus());
      taskMaterialSlice.setCopy(copywrite);
      taskMaterialSliceList.add(taskMaterialSlice);
    }

    return taskMaterialSliceList;
  }

  private String getRandomCopywrite(List<CopyManagementDO> copyManagementList) {
    //随机获取一条口播文案
    if (copyManagementList != null && !copyManagementList.isEmpty()) {
      int index = RandomUtils.nextInt(0, copyManagementList.size());
      return copyManagementList.get(index).getCopywriting();
    }
    return null;
  }

  private List<List<AiVideoSliceVO>> getVideoSliceList(List<TaskMaterialDO> materialList) {
    List<List<AiVideoSliceVO>> sliceVOList = Lists.newArrayList();
    materialList.forEach(material -> {
      sliceVOList.add(VideoSlicer.videoSlice(material.getMaterialUrl(), null));
    });
    return sliceVOList;
  }

  /**
   * 获取当前任务的总发布人数
   *
   * @param taskId 任务Id
   * @return
   */
  private Integer getVideoNum(Long taskId) {
    List<TaskExtDO> taskExtList = taskExtMapper
        .selectList(new LambdaQueryWrapper<TaskExtDO>().eq(TaskExtDO::getTaskId, taskId));

    if (taskExtList == null || taskExtList.isEmpty()) {
      throw exception(TASK_CREATE_EXT_NOT_EXISTS);
    }

    //获取当前任务的总发布人数
    Integer totalPublishNum = taskExtList.stream().mapToInt(TaskExtDO::getTotalNum).sum();
    return totalPublishNum;
  }


  @Override
  public List<TaskDO> getTaskListByMerchantIds(List<Long> merchantIds) {
    return taskMapper.selectList(new LambdaQueryWrapper<TaskDO>()
          .in(TaskDO::getMerchantUserId,merchantIds));
  }

}
