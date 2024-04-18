package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Resource;
import javax.validation.Valid;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.idempotent.core.annotation.Idempotent;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPauseReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.task.TaskService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "商户后台 - 商家发布任务")
@RestController
@RequestMapping("/cxlaike/task")
@Validated
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/create")
    @Operation(summary = "创建商家发布任务")
    @PreAuthorize("@mss.hasPermission('cxlaike:task')")
    @Idempotent(message = "请勿重复提交")
    public CommonResult<Long> createTask(@Valid @RequestBody TaskCreateReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商家发布任务")
    @PreAuthorize("@mss.hasPermission('cxlaike:task')")
    @Idempotent(message = "请勿重复提交")
    public CommonResult<Boolean> updateTask(@Valid @RequestBody TaskUpdateReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停商家发布任务")
    @PreAuthorize("@mss.hasPermission('cxlaike:task')")
    @Idempotent(message = "请勿重复提交")
    public CommonResult<Boolean> updateTask(@Valid @RequestBody TaskPauseReqVO taskPauseReqVO) {
        taskService.pauseTask(taskPauseReqVO);
        return success(true);
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除商家发布任务")
    @PreAuthorize("@mss.hasPermission('cxlaike:task')")
    @Idempotent(message = "请勿重复提交")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商家发布任务")
    @PreAuthorize("@mss.hasPermission('cxlaike:task')")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<TaskRespVO> getTask(@RequestParam("id") Long id) {
        TaskRespVO task = taskService.getTask(id);
        return success(task);
    }


    @GetMapping("/page")
    @Operation(summary = "获得商家发布任务分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:task')")
    public CommonResult<PageResult<TaskPageRespVO>> getTaskPage(@Valid @ParameterObject TaskPageReqVO pageVO) {
        PageResult<TaskPageRespVO> pageResult = taskService.getTaskPage(pageVO);
        return success(pageResult);
    }

}
