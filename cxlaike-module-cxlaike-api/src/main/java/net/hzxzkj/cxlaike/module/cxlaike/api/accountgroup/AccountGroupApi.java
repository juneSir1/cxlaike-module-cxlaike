package net.hzxzkj.cxlaike.module.cxlaike.api.accountgroup;


import java.util.List;

/**
 * 会员抖音审核 API 接口
 *
 * @author 宵征源码
 */
public interface AccountGroupApi {

   void createGroup(String groupName, List<Integer> platformTypes);

}
