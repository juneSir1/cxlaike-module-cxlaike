package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.BaseEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.TextEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.framework.idempotent.core.annotation.Idempotent;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotask.AiVideoTaskConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask.AiVideoTaskService;
import net.hzxzkj.cxlaike.module.cxlaike.service.metahumanconfig.MetaHumanConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.VideoClipHandlerService;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import org.apache.commons.compress.utils.Lists;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

@Tag(name = "商家后台 - ai视频任务")
@RestController
@RequestMapping("/cxlaike/ai-video-task")
@Validated
public class AiVideoTaskController {

  @Lazy
  @Resource
  private TaskClient taskClient;
  @Resource
  private AiVideoTaskService aiVideoTaskService;
  @Resource
  private MetaHumanConfigService metaHumanConfigService;
  @Resource
  private VideoClipHandlerService aiVideoClipService;

  @GetMapping("/create/test")
  @Operation(summary = "创建ai混剪视频任务test")
//  @PreAuthenticated
  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> createAiVideoTaskTest() {
//    createReqVO.setUserId(TenantContextHolder.getTenantId());
    Timeline timeline = new Timeline();

    List<VideoTrack>  videoTracks = Lists.newArrayList();

    VideoTrack videoTrack = new VideoTrack();

    List<VideoTrackClip>  videoTrackClips = Lists.newArrayList();

    VideoTrackClip videoTrackClip = new VideoTrackClip();
    videoTrackClip.setMediaURL("https://cxlaike-dev.oss-cn-hangzhou.aliyuncs.com/1722525720071827457/2023-12-13/cdfb5a3f9dae9ff244cae744fd3bc374d2531e1b7c10187cb07db50c8f2b6ce6.mp4");
    videoTrackClip.setIn(10f);
    videoTrackClip.setOut(30f);

    List<BaseEffect>  Effects = Lists.newArrayList();

    TextEffect textEffect = new TextEffect();
    textEffect.setContent("test");
    textEffect.setEffectColorStyle("CS0001-000013");

    Effects.add(textEffect);
    videoTrackClip.setEffects(Effects);

    videoTrackClips.add(videoTrackClip);

    videoTrack.setVideoTrackClips(videoTrackClips);

    videoTracks.add(videoTrack);

    //音频轨
    List<AudioTrack> audioTracks = Lists.newArrayList();

    AudioTrack audioTrack = new AudioTrack();

    List<AudioTrackClip> audioTrackClips = Lists.newArrayList();

    AudioTrackClip audioTrackClip = new AudioTrackClip();
    audioTrackClip.setMediaURL("https://cxlaike-dev.oss-cn-hangzhou.aliyuncs.com/1722525720071827457/2023-12-13/cdfb5a3f9dae9ff244cae744fd3bc374d2531e1b7c10187cb07db50c8f2b6ce6.mp4");
//    audioTrackClip.setIn(5f);
//    audioTrackClip.setOut(17f);

    audioTrackClips.add(audioTrackClip);

    audioTrack.setAudioTrackClips(audioTrackClips);

    audioTracks.add(audioTrack);

    timeline.setVideoTracks(videoTracks);
//    timeline.setAudioTracks(audioTracks);

    return success(aiVideoClipService.placeOrderTest(timeline));
  }

  @GetMapping("/create/testQuery")
  @Operation(summary = "创建ai混剪视频任务test")
//  @PreAuthenticated
  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> testQuery(String jobId) {

    return success(aiVideoClipService.testQuery(jobId));
  }

  @PostMapping("/create/1")
  @Operation(summary = "创建ai混剪视频任务")
  @PreAuthenticated
  @Idempotent(message = "请勿重复提交")
  public CommonResult<Long> createAiVideoTask1(
      @Valid @RequestBody AiClipVideoTaskCreateReqVO createReqVO) {
    createReqVO.setUserId(TenantContextHolder.getTenantId());
    return success(aiVideoTaskService.createAiClipVideoTask(createReqVO));
  }

  @PostMapping("/create/2")
  @Operation(summary = "创建数字人混剪视频任务")
  @PreAuthenticated
  @Idempotent(message = "请勿重复提交")
  public CommonResult<Long> createAiVideoTask2(
      @Valid @RequestBody DigitalClipVideoCreateReqVO createReqVO) {
    createReqVO.setUserId(TenantContextHolder.getTenantId());
    return success(aiVideoTaskService.createDigitalClipVideoTask(createReqVO));
  }


  @PostMapping("/create/3")
  @Operation(summary = "创建ai数字人口播视频任务")
  @PreAuthenticated
  @Idempotent(message = "请勿重复提交")
  public CommonResult<Long> createAiVideoTask3(
      @Valid @RequestBody DigitalClipOnlyVideoCreateReqVO createReqVO) {
    createReqVO.setUserId(TenantContextHolder.getTenantId());
    return success(aiVideoTaskService.createDigitalClipOnlyVideoTask(createReqVO));
  }

  @PostMapping("/create/4")
  @Operation(summary = "创建ai素材混剪视频任务")
  @PreAuthenticated
  @Idempotent(message = "请勿重复提交")
  public CommonResult<Long> createAiVideoTask4(
          @Valid @RequestBody AiMaterialClipVideoTaskCreateReqVO createReqVO) {
    createReqVO.setUserId(TenantContextHolder.getTenantId());
    return success(aiVideoTaskService.createAiMaterialClipVideoTask(createReqVO));

  }

  @GetMapping("/materialClipTest")
  @Operation(summary = "创建ai素材混剪视频任务测试")
  @Idempotent(message = "请勿重复提交")
  @TenantIgnore
  public CommonResult<Long> materialClipTest(@RequestParam("id") Long id) {
    //生成任务
//    JSONObject param = new JSONObject();
//    param.set("id", id);
//    param.set("type", ActiveTaskType.AI_MATERIAL_CLIP.getType());
//    taskClient.createTask(TaskHandlerEnum.AI_CLIP.getValue(), null, param.toString());
    //合成视频
    aiVideoTaskService.aiMaterialVideoClipVideo(id);
    return success(System.currentTimeMillis());

  }

  @PostMapping("/preViewVideo")
  @Operation(summary = "预览视频")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  @Idempotent(message = "请勿重复提交")
  public CommonResult<AiVideoTaskPreviewRespVO> preViewVideo(
          @Valid @RequestBody AiMaterialClipVideoTaskCreateReqVO createReqVO) {
    createReqVO.setUserId(TenantContextHolder.getTenantId());
    return success(aiVideoTaskService.previewAiMaterialClipVideoTask(createReqVO));
  }

  @PostMapping("/preViewCount")
  @Operation(summary = "预估数量")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  @Idempotent(message = "请勿重复提交")
  public CommonResult<List<Integer>> preViewCount(
          @Valid @RequestBody AiMaterialClipVideoTaskCreateReqVO createReqVO) {
    return success(aiVideoTaskService.previewCount(createReqVO));
  }

  @GetMapping("/get")
  @Operation(summary = "获得ai视频任务")
  @Parameter(name = "id", description = "编号", required = true, example = "1024")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<AiVideoTaskRespVO> getAiVideoTask(@RequestParam("id") Long id) {
    AiVideoTaskRespVO aiVideoTask = aiVideoTaskService.getAiVideoTask(id);
    return success(aiVideoTask);
  }

  @PostMapping("/cancelPreview")
  @Operation(summary = "取消预览")
  @Parameter(name = "id", description = "编号", required = true, example = "1024")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<Boolean> cancelPreview(@RequestParam("id") Long id) {
    aiVideoTaskService.cancelPreview(id);
    return success(true);
  }

  @GetMapping("/getAiMaterialClipTask")
  @Operation(summary = "获得ai视频素材混剪任务")
  @Parameter(name = "id", description = "编号", required = true, example = "1024")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<AiMaterialClipVideoTaskRespVO> getAiMaterialClipTask(@RequestParam("id") Long id) {
    AiMaterialClipVideoTaskRespVO aiVideoTask = aiVideoTaskService.getAiMaterialClipVideoTask(id);
    return success(aiVideoTask);
  }

  @GetMapping("/getAiMaterialClipTaskWithOutTenantId")
  @Operation(summary = "获得ai视频素材混剪任务忽略tenantId")
  @Parameter(name = "id", description = "编号", required = true, example = "1024")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  @TenantIgnore
  public CommonResult<AiMaterialClipVideoTaskRespVO> getAiMaterialClipTaskWithOutTenantId(@RequestParam("id") Long id) {
    AiMaterialClipVideoTaskRespVO aiVideoTask = aiVideoTaskService.getAiMaterialClipVideoTask(id);
    return success(aiVideoTask);
  }

  @GetMapping("/result_page")
  @Operation(summary = "获得ai视频结果分页")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<PageResult<AiVideoResultRespVO>> getAiVideoTaskResultPage(
      @Valid @ParameterObject AiVideoTaskResultPageReqVO pageVO) {
    PageResult<AiVideoResultRespVO> pageResult = aiVideoTaskService
        .getAiVideoTaskResultPage(pageVO);
    return success(pageResult);
  }

  @GetMapping("/result_page1")
  @Operation(summary = "获得ai素材混剪视频结果分页")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<AiMaterialClipVideoResultRespVO> getAiMaterialClipVideoTaskResultPage(
      @Valid @ParameterObject AiMaterialClipVideoResultPageReqVO pageVO) {
    return success(aiVideoTaskService
            .getAiMaterialClipVideoTaskResultPage(pageVO));
  }

  @DeleteMapping("/result_delete")
  @Operation(summary = "删除ai视频结果")
  @Parameter(name = "ids", description = "编号", required = true)
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<Boolean> deleteAiSystemArg(@RequestParam("ids") List<Long> ids) {
    aiVideoTaskService.deleteAiVideoTaskResult(ids);
    return success(true);
  }

  @PostMapping("/result_template")
  @Operation(summary = "视频设置为模板视频")
  @Parameter(name = "id", description = "编号", required = true)
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<Boolean> templateAiVideoResult(@RequestParam("id") Long id) {

    return success(aiVideoTaskService.templateAiVideoTaskResult(id));
  }


  @GetMapping("/page")
  @Operation(summary = "获得ai视频任务分页")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")
  public CommonResult<PageResult<AiVideoTaskPageRespVO>> getAiVideoTaskPage(
      @Valid @ParameterObject AiVideoTaskPageReqVO pageVO) {
    PageResult<AiVideoTaskPageRespVO> pageResult = aiVideoTaskService.getAiVideoTaskPage(pageVO);
    return success(pageResult);
  }

  @GetMapping("/human_list")
  @Operation(summary = "获得数字人参数列表")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-video-task')")

  public CommonResult<List<MetaHumanConfigRespVO>> getHumanList() {
    List<MetaHumanConfigDO> metaHumanConfig = metaHumanConfigService
        .getMetaHumanConfig(UserTypeEnum.MERCHANT.getValue());
    return success(AiVideoTaskConvert.INSTANCE.convertList2(metaHumanConfig));
  }
  @GetMapping("/videoClip")
  @Operation(summary = "视频掐头去尾")
  @PreAuthenticated
//  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> videoClip(@RequestParam("url") String url,@RequestParam("in") Float in, @RequestParam("out") Float out) {
    Timeline timeline = new Timeline();

    //视频轨
    List<VideoTrack>  videoTracks = Lists.newArrayList();

    VideoTrack videoTrack = new VideoTrack();

    List<VideoTrackClip>  videoTrackClips = Lists.newArrayList();

    VideoTrackClip videoTrackClip = new VideoTrackClip();
//    videoTrackClip.setMediaURL("https://cxlaike-dev.oss-cn-hangzhou.aliyuncs.com/1722525720071827457/2023-12-13/cdfb5a3f9dae9ff244cae744fd3bc374d2531e1b7c10187cb07db50c8f2b6ce6.mp4");
    videoTrackClip.setMediaURL(url);
    videoTrackClip.setIn(in);
    videoTrackClip.setOut(out);

    videoTrackClips.add(videoTrackClip);

    videoTrack.setVideoTrackClips(videoTrackClips);

    videoTracks.add(videoTrack);

    timeline.setVideoTracks(videoTracks);

    return success(aiVideoClipService.videoClip(timeline));
  }

  @GetMapping("/getVideoClipResult")
  @Operation(summary = "获取视频掐头去尾结果")
  @PreAuthenticated
//  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> getVideoClipResult(@RequestParam("jobId") String jobId) {

    return success(aiVideoClipService.getVideoToAudioResult(jobId));
  }
  @GetMapping("/videoToAudio")
  @Operation(summary = "视频转音频")
  @PreAuthenticated
//  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> videoToAudio(@RequestParam("url") String url) {
    Timeline timeline = new Timeline();

    //音频轨
    List<AudioTrack> audioTracks = Lists.newArrayList();

    AudioTrack audioTrack = new AudioTrack();

    List<AudioTrackClip> audioTrackClips = Lists.newArrayList();

    AudioTrackClip audioTrackClip = new AudioTrackClip();
//    audioTrackClip.setMediaURL("https://cxlaike-dev.oss-cn-hangzhou.aliyuncs.com/1722525720071827457/2023-12-13/cdfb5a3f9dae9ff244cae744fd3bc374d2531e1b7c10187cb07db50c8f2b6ce6.mp4");
    audioTrackClip.setMediaURL(url);

    audioTrackClips.add(audioTrackClip);

    audioTrack.setAudioTrackClips(audioTrackClips);

    audioTracks.add(audioTrack);

    timeline.setAudioTracks(audioTracks);

    return success(aiVideoClipService.videoToAudio(timeline));
  }

  @GetMapping("/getVideoToAudioResult")
  @Operation(summary = "获取视频转音频结果")
  @PreAuthenticated
//  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> getVideoToAudioResult(@RequestParam("jobId") String jobId) {

    return success(aiVideoClipService.getVideoToAudioResult(jobId));
  }

  @GetMapping("/audioToText")
  @Operation(summary = "音频转文字")
  @PreAuthenticated
//  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> audioToText(@RequestParam("url") String url, @RequestParam("startTime") String startTime, @RequestParam("duration") String duration) {

    return success(aiVideoClipService.audioToText(url, startTime, duration));
  }

  @GetMapping("/getAudioToTextResult")
  @Operation(summary = "获取音频转文字结果")
  @PreAuthenticated
//  @Idempotent(message = "请勿重复提交")
  public CommonResult<String> getAudioToTextResult(@RequestParam("jobId") String jobId) {

    return success(aiVideoClipService.getAudioToTextResult(jobId));
  }

}
