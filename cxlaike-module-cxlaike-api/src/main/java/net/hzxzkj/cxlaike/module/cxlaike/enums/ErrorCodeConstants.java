package net.hzxzkj.cxlaike.module.cxlaike.enums;

import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;

/**
 * Merchant 错误码枚举类
 *
 * merchant 系统，使用 1-004-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 系统相关  1004001000============
    ErrorCode DATA_ERROR = new ErrorCode(1004001002, "数据异常");
    ErrorCode PARAMS_ERROR = new ErrorCode(1004001003, "参数缺失");
    //系统异常请联系客服
    ErrorCode SYSTEM_ERROR = new ErrorCode(1004001004, "系统异常请联系客服");
    ErrorCode STATUS_ERROR = new ErrorCode(1004001007, "状态不予许此操作");







    // ========== 抖音相关  1004003000============
    ErrorCode ACCREDIT_ERROR = new ErrorCode(1004003001, "授权失败");


    // ========== ai文案  1004004000============
    ErrorCode AI_CREATE_ERROR = new ErrorCode(1004004001, "文案生成失败，请稍后再试");

    // ========== 门店管理 1004005001 ==========
    ErrorCode STORE_NOT_EXISTS = new ErrorCode(1004005001, "门店不存在");
    ErrorCode STORE_NOT_UPDATE = new ErrorCode(1004005002, "门店存在未结束任务，无法停用");
    ErrorCode STORE_NOT_UPDATE_2 = new ErrorCode(1004005003, "门店存在进行中的任务，无法编辑");
    ErrorCode STORE_NOT_DELETE = new ErrorCode(1004005004, "门店存在进行中的任务，无法删除");
    //门店已关闭
    ErrorCode STORE_CLOSED = new ErrorCode(1004005005, "门店已关闭");



    // ========== 订单 1004006001 ==========
    ErrorCode ORDER_AUTHORITY_ERROR = new ErrorCode(1004006009, "无权操作");
    ErrorCode ORDER_STATUS_ERROR = new ErrorCode(1004006002, "当前状态不允许此操作");
    ErrorCode ORDER_VIDEO_LINK = new ErrorCode(1004006003, "回填的视频链接格式错误");


    // ========== ai视频配置 1004007001 ==========
    ErrorCode AI_VIDEO_CONFIG_NOT_EXISTS = new ErrorCode(1004007001, "ai视频配置不存在");

    ErrorCode  META_HUMAN_CONFIG_NOT_EXISTS = new ErrorCode(1004007002, "数字人配置不存在");
    ErrorCode SYS_TAKE_LEVEL_CONFIG_NOT_EXISTS = new ErrorCode(1004007003, "接单等级配置不存在");
    ErrorCode SYSTEM_ARG_NOT_EXISTS = new ErrorCode(1004007004, "系统参数不存在");
    ErrorCode AI_VIDEO_TEMPLATE_NOT_EXISTS= new ErrorCode(1004007005, "ai视频模板不存在");

    // ========== 探店任务相关 1004008000 ==========
    ErrorCode TASK_NOT_EXISTS = new ErrorCode(1004008000, "任务不存在");

    //当前任务不支持该等级接单
    ErrorCode TASK_NOT_SUPPORT_LEVEL = new ErrorCode(1004008001, "当前任务不支持该等级接单");
    //当前任务等级接单已满
    ErrorCode TASK_LEVEL_FULL = new ErrorCode(1004008002, "当前任务等级接单已满");
    //当前任务需要定制数字人,请联系客服!
    ErrorCode TASK_NEED_CUSTOM = new ErrorCode(1004008003, "当前任务需要定制数字人,请联系客服!");
    //当天云实探接单数已达上限
    ErrorCode TASK_DAY_FULL = new ErrorCode(1004008004, "当天云探接单数已达上限");
    //当天实探接单数已达上限
    ErrorCode TASK_DAY_FULL_2 = new ErrorCode(1004008005, "当天实探接单数已达上限");
    //有正在进行中的当前任务的订单
    ErrorCode TASK_HAS_ORDER = new ErrorCode(1004008006, "有正在进行中的当前任务的订单");
    //当前任务,不可接单,超出接单次数限制
    ErrorCode TASK_NOT_ACCEPT = new ErrorCode(1004008007, "当前任务,不可接单,超出接单次数限制");
    //当前会员未进行实名认证!
    ErrorCode TASK_NOT_REAL_NAME = new ErrorCode(1004008008, "当前会员未进行实名认证!");
    //当前会员带货等级不满足任务要求
    ErrorCode TASK_NOT_LEVEL = new ErrorCode(1004008009, "当前会员带货等级不满足任务要求");
    //当前会员上个月取消订单次数超过限制
    ErrorCode TASK_NOT_CANCEL = new ErrorCode(1004008010, "当前会员上个月取消订单次数超过限制");
    //报名订单不存在
    ErrorCode TASK_SIGN_ORDER_NOT_EXISTS = new ErrorCode(1004008011, "报名订单不存在");
    //支付订单不存在!
    ErrorCode TASK_PAY_ORDER_NOT_EXISTS = new ErrorCode(1004008012, "支付订单不存在!");
    //用户所在地不可接单
    ErrorCode TASK_NOT_ACCEPT_2 = new ErrorCode(1004008013, "用户所在地不可接单");
    //用户粉丝数量不足,不可接单
    ErrorCode TASK_NOT_ACCEPT_3 = new ErrorCode(1004008014, "用户粉丝数量不足,不可接单");
    //当前会员当月取消订单次数超过限制
    ErrorCode TASK_NOT_CANCEL_2 = new ErrorCode(1004008015, "当前会员当月取消订单次数超过限制");

    // ========== 任务创建相关 1004009000 ==========
    //创建试听任务失败
    ErrorCode TASK_CREATE_AUDITION_ERROR = new ErrorCode(1004009000, "创建试听任务失败");
    //当前有生成中的视频,请等待!
    ErrorCode TASK_CREATE_VIDEO_ING = new ErrorCode(1004009001, "当前有生成中的视频,请等待!");
    //素材文件id不能为空
    ErrorCode TASK_CREATE_MATERIAL_ID_NULL = new ErrorCode(1004009002, "素材文件id不能为空");
    //视频组名不能为空
    ErrorCode TASK_CREATE_VIDEO_GROUP_NAME_NULL = new ErrorCode(1004009003, "视频组名不能为空");
    //当前剪辑类型素材不可为空
    ErrorCode TASK_CREATE_MATERIAL_NULL = new ErrorCode(1004009004, "当前剪辑类型素材不可为空");
    //ai剪辑视频失败
    ErrorCode TASK_CREATE_AI_VIDEO_ERROR = new ErrorCode(1004009005, "ai剪辑视频失败");
    //当前用户余额不足,请充值!
    ErrorCode TASK_CREATE_BALANCE_NOT_ENOUGH = new ErrorCode(1004009006, "当前用户余额不足,请充值!");
    //当前素材有误,请检查!
    ErrorCode TASK_CREATE_MATERIAL_ERROR = new ErrorCode(1004009007, "当前素材有误,请检查!");
    //数字人背景图不能为空
    ErrorCode TASK_CREATE_HUMAN_BG_NULL = new ErrorCode(1004009008, "数字人背景图不能为空");
    //配乐选择有误请查看
    ErrorCode TASK_CREATE_MUSIC_ERROR = new ErrorCode(1004009009, "配乐选择有误请查看");
    //数字人不存在
    ErrorCode TASK_CREATE_HUMAN_NOT_EXISTS = new ErrorCode(1004009010, "数字人不存在");
    //选择数字人有误
    ErrorCode TASK_CREATE_HUMAN_ERROR = new ErrorCode(1004009011, "选择数字人有误");
    //图片不存在
    ErrorCode TASK_CREATE_IMAGE_NOT_EXISTS = new ErrorCode(1004009012, "图片不存在");
    //文件类型不正确
    ErrorCode TASK_CREATE_FILE_TYPE_ERROR = new ErrorCode(1004009013, "文件类型不正确");
    //视频任务不存在
    ErrorCode TASK_CREATE_VIDEO_NOT_EXISTS = new ErrorCode(1004009014, "视频任务不存在");
    //文件上传失败
    ErrorCode TASK_CREATE_FILE_UPLOAD_ERROR = new ErrorCode(1004009015, "文件上传失败");
    //计算金币数量失败
    ErrorCode TASK_CREATE_GOLD_ERROR = new ErrorCode(1004009016, "计算金币数量失败");
    //结果不存在
    ErrorCode TASK_CREATE_RESULT_NOT_EXISTS = new ErrorCode(1004009017, "结果不存在");
    //结果状态不正确,不可删除
    ErrorCode TASK_CREATE_RESULT_STATUS_ERROR = new ErrorCode(1004009018, "结果状态不正确,不可删除");
    //当前模版视频背景不可为空
    ErrorCode TASK_CREATE_TEMPLATE_BG_NULL = new ErrorCode(1004009019, "当前模版视频背景不可为空");
    //口播文案不可为空
    ErrorCode   TASK_CREATE_COPYWRITING_NULL= new ErrorCode(1004009020, "口播文案不可为空");
    //内容标题不可为空
    ErrorCode   TASK_CREATE_CONTENT_TITLE_NULL= new ErrorCode(1004009021, "内容标题不可为空");
    //视频时长不可为空
    ErrorCode   TASK_CREATE_VIDEO_DURATION_NULL= new ErrorCode(1004009022, "视频时长不可为空");
    //封面图片不可为空
    ErrorCode   TASK_CREATE_COVER_IMAGE_NULL= new ErrorCode(1004009023, "封面图片不可为空");
    // ========== 探店任务创建相关 1004010000 ==========
    //口播文案不能为空
    ErrorCode TASK_CREATE_KOUBO_CONTENT_NULL = new ErrorCode(1004010000, "口播文案不能为空");
    //结束时间不能小于当前时间
    ErrorCode TASK_CREATE_END_TIME_ERROR = new ErrorCode(1004010001, "结束时间不能小于当前时间");
    //创建任务有误,任务数量不可为空!
    ErrorCode TASK_CREATE_NUM_NULL = new ErrorCode(1004010002, "创建任务有误,任务数量不可为空!");
    //当前钱包余额不足请先充值
    ErrorCode TASK_CREATE_WALLET_NOT_ENOUGH = new ErrorCode(1004010003, "当前钱包余额不足请先充值");
    //当前任务进行中或已结束，不可删除
    ErrorCode TASK_CREATE_DELETE_ERROR = new ErrorCode(1004010004, "当前任务进行中或已结束，不可删除");
    //云探任务需上传视频素材
    ErrorCode TASK_CREATE_VIDEO_NULL = new ErrorCode(1004010005, "云探任务需上传视频素材");
    //除了实探-真人出镜任务外所有任务都需上传视频素材!
    ErrorCode TASK_CREATE_VIDEO_NULL_2 = new ErrorCode(1004010006, "除了实探-现场拍摄任务外所有任务都需上传视频素材!");
    //素材文件id不能为空
    ErrorCode TASK_CREATE_MATERIAL_ID_NULL_2 = new ErrorCode(1004010007, "素材文件id不能为空");
    //视频组名不能为空
    ErrorCode TASK_CREATE_VIDEO_GROUP_NAME_NULL_2 = new ErrorCode(1004010008, "视频组名不能为空");
    //当前任务不存在
    ErrorCode TASK_CREATE_NOT_EXISTS = new ErrorCode(1004010009, "当前任务不存在");
    //当前任务进行中或已结束，不可变更状态
    ErrorCode TASK_CREATE_STATUS_ERROR = new ErrorCode(1004010010, "当前任务进行中或已结束，不可变更状态");
    //当前任务未到开始时间，不可变更状态
    ErrorCode TASK_CREATE_STATUS_ERROR_2 = new ErrorCode(1004010011, "当前任务未到开始时间，不可变更状态");
    //当前任务未到结束时间，不可变更状态
    ErrorCode TASK_CREATE_STATUS_ERROR_3 = new ErrorCode(1004010012, "当前任务未到结束时间，不可变更状态");
    //任务不在进行中不可暂停
    ErrorCode TASK_CREATE_STATUS_ERROR_4 = new ErrorCode(1004010013, "任务不在进行中不可暂停");
    //任务不在暂停中不可恢复
    ErrorCode TASK_CREATE_STATUS_ERROR_5 = new ErrorCode(1004010014, "任务不在暂停中不可恢复");
    //当前任务没有扩展信息
    ErrorCode TASK_CREATE_EXT_NOT_EXISTS = new ErrorCode(1004010015, "当前任务没有扩展信息");

    // ========== 邀请 1004011000 ==========
    ErrorCode INVITE_CODE_ERROR = new ErrorCode(1004011001, "获取小程序码失败");
    ErrorCode INVITE_BACKDROP_ERROR = new ErrorCode(1004011002, "背景图缺失");
    ErrorCode INVITE_APPLET_ERROR = new ErrorCode(1004011002, "小程序码获取失败");

    // ========== 视频剪辑订单相关错误码 1004012000 ==========
    //订单不存在
    ErrorCode VIDEO_ORDER_NOT_EXISTS = new ErrorCode(1004012000, "订单不存在");



    // ========== 矩阵任务相关错误码 1004013000 ==========
    ErrorCode MATRIX_TASK_PUBLISH_TIME_ERROR = new ErrorCode(1004013000, "定时发布需填写发布时间");
    ErrorCode MATRIX_TASK_ALLOT_ERROR = new ErrorCode(1004013001, "任务分发还未完成,请稍后再试");

    ErrorCode MATRIX_TASK_VIDEO_ERROR = new ErrorCode(1004013002, "无发布视频任务");
    ErrorCode MATRIX_TASK_USER_VIDEO_EXIST = new ErrorCode(1004013003, "您已发布过该任务");
    ErrorCode MATRIX_TASK_TERMINATE = new ErrorCode(1004013004, "该任务已终止");
    ErrorCode MATRIX_TASK_ORDER_FF = new ErrorCode(1004013005, "您还有未发布的视频");




    // ========== 相关商家vip套餐错误码 1004014000 ==========
    //支付订单不存在
    ErrorCode VIP_ORDER_NOT_EXISTS = new ErrorCode(1004014000, "支付订单不存在");
    //商家vip套餐不存在
    ErrorCode VIP_NOT_EXISTS = new ErrorCode(1004014001, "商家vip套餐不存在");
    //当前套餐不可开通
    ErrorCode VIP_NOT_OPEN = new ErrorCode(1004014002, "当前套餐不可开通");



    // ========== 用户钱包错误码 1004015000 ==========
    ErrorCode USER_GOLD_BALANCE_ERROR = new ErrorCode(1004015001, "金币余额不足");
    ErrorCode USER_WALLET_ERROR = new ErrorCode(1004015002, "用户钱包异常");

    // ========== 私信错误码 1004016000 ==========
    ErrorCode CONVERSATION_ERROR = new ErrorCode(1004016001, "该会话不存在");
    ErrorCode CONVERSATION_SEND_ERROR = new ErrorCode(1004016002, "发送失败");

    // ========== 评论错误码 1004017000 ==========
    ErrorCode COMMENT_TOP_ERROR = new ErrorCode(1004017001, "置顶失败");
    ErrorCode COMMENT_REPLY_ERROR = new ErrorCode(1004017002, "回复失败");

}
