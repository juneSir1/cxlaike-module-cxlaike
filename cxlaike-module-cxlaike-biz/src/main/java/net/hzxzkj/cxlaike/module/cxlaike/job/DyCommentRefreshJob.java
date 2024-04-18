package net.hzxzkj.cxlaike.module.cxlaike.job;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.quartz.core.handler.JobHandler;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.framework.tenant.core.job.TenantJob;
import net.hzxzkj.cxlaike.module.cxlaike.service.dycomment.DyCommentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
//@TenantJob // 标记多租户
@Slf4j
public class DyCommentRefreshJob implements JobHandler {

    @Resource
    private DyCommentService dyCommentService;

    @Override
    @TenantIgnore
    public String execute(String param) {
        log.info("【抖音评论数据刷新】开始++++++++++++++++++");

        dyCommentService.pullCommentList(null);

        log.info("【抖音评论数据刷新】结束++++++++++++++++++");
        return "";
    }
}
