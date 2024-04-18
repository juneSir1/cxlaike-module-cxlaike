package net.hzxzkj.cxlaike.module.cxlaike.api.accountgroup;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.dygroup.DyGroupService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jianan.han
 * @date 2023/9/11 下午5:23
 * @description
 */
@Service
@Validated
public class AccountGroupApiImpl implements AccountGroupApi {

    @Resource
    private DyGroupService dyGroupService;

    @Override
    public void createGroup(String groupName, List<Integer> platformTypes) {
        for(Integer platformType:platformTypes){
            AccountGroupCreateReqVO createReqVO = new AccountGroupCreateReqVO();
            createReqVO.setName(groupName);
            createReqVO.setPlatformType(platformType);
            dyGroupService.createDyGroup(createReqVO);
        }

    }
}
