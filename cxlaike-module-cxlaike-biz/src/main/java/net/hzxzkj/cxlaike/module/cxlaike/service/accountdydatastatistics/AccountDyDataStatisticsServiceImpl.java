package net.hzxzkj.cxlaike.module.cxlaike.service.accountdydatastatistics;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.binding.Binder;
import com.diboot.core.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.date.DateUtils;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.accountdydatastatistics.AccountDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation.AccountGroupCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.membercorrelation.MemberCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountdydatastatistics.AccountDyDataStatisticsMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountgroupcorrelation.AccountGroupCorrelationMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate.StatisticsDateOrderByEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate.StatisticsDateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.dydatastatistics.DataStatisticsService;
import net.hzxzkj.cxlaike.module.cxlaike.service.membercorrelation.MemberCorrelationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.PARAMS_ERROR;

/**
 * 账号纬度抖音数据统计 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
@Slf4j
public class AccountDyDataStatisticsServiceImpl extends BaseServiceImpl<AccountDyDataStatisticsMapper,AccountDyDataStatisticsDO> implements AccountDyDataStatisticsService {

    @Resource
    private AccountDyDataStatisticsMapper accountDyDataStatisticsMapper;
    @Resource
    private MemberCorrelationService memberCorrelationService;
    @Resource
    private DataStatisticsService dataStatisticsService;

    @Resource
    private AccountGroupCorrelationMapper accountGroupCorrelationMapper;


    @Override
    public AccountDyDataStatisticsRespVO getStatisticsByDate(AccountDyDataStatisticsReqVO reqVO) {
//        LocalDateTime[] statistics = {LocalDateTime.of(2023,11,8,00,00,00),LocalDateTime.of(2023,11,9,23,59,59)};
//        reqVO.setStatisticsTime(statistics);
        log.info("AccountDyDataStatisticsReqVO:{}", JsonUtils.toJsonString(reqVO));
        AccountDyDataStatisticsRespVO respVO = new AccountDyDataStatisticsRespVO();
        //昨日(单日数据统计)
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(reqVO.getDateType())) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(reqVO.getDateType());
            List<AccountDyDataStatisticsDO> accountDyDataStatisticsDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
                    .eq(AccountDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(AccountDyDataStatisticsDO::getDyType, reqVO.getDataType())
                    .eq(AccountDyDataStatisticsDO::getPlatformType, reqVO.getPlatformType()));

            if(DyUserTypeEnum.DR.getCode().equals(reqVO.getDataType())){
                List<MemberCorrelationDO> correlationDOS = memberCorrelationService.getList();
                if(CollectionUtil.isEmpty(correlationDOS)){
                    return respVO;
                }
                List<Long> bindIds = correlationDOS.stream().map(MemberCorrelationDO::getDyBindId).collect(Collectors.toList());
                accountDyDataStatisticsDOS = dataStatisticsService.getListAccountDyData(accountDyDataStatisticsDOS,statisticsTime,reqVO.getDataType(),bindIds);
            }
            if (CollectionUtil.isEmpty(accountDyDataStatisticsDOS)) {
                return respVO;
            }
            this.buildYesterdayData(respVO, statisticsTime, accountDyDataStatisticsDOS);
            return respVO;
        }
        this.buildMoreDaysData(reqVO, respVO);
        return respVO;
    }

    private void buildMoreDaysData(AccountDyDataStatisticsReqVO reqVO, AccountDyDataStatisticsRespVO respVO) {
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        if (reqVO.getDateType() != null) {
            startTime = LocalDateTimeUtils.getMinusDaysTime(LocalDateTime.now(),reqVO.getDateType());
            endTime = LocalDateTimeUtils.getMinusDaysTime(LocalDateTime.now(),1);
        }else {
            if(reqVO.getStatisticsTime() == null){
                throw exception(PARAMS_ERROR);
            }
            startTime = reqVO.getStatisticsTime()[0];
            endTime = reqVO.getStatisticsTime()[1];
        }

//        List<AccountDyDataStatisticsDO> dataStatisticsStartDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
//                .eq(AccountDyDataStatisticsDO::getStatisticsTime, startTime)
//                .eq(AccountDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode()));
//        List<AccountDyDataStatisticsDO> dataStatisticsEndDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
//                .eq(AccountDyDataStatisticsDO::getStatisticsTime, endTime)
//                .eq(AccountDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode()));
        List<AccountDyDataStatisticsDO> dataStatisticsAllDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
                .between(AccountDyDataStatisticsDO::getStatisticsTime,startTime, endTime)
                .eq(AccountDyDataStatisticsDO::getDyType, reqVO.getDataType())
                .eq(AccountDyDataStatisticsDO::getPlatformType, reqVO.getPlatformType())
                .orderByAsc(AccountDyDataStatisticsDO::getStatisticsTime));
        if(DyUserTypeEnum.DR.getCode().equals(reqVO.getDataType())){
            List<MemberCorrelationDO> correlationDOS = memberCorrelationService.getList();
            List<Long> bindIds = correlationDOS.stream().map(MemberCorrelationDO::getDyBindId).collect(Collectors.toList());
            if(CollectionUtil.isEmpty(bindIds)){
                return;
            }
            dataStatisticsAllDOS = dataStatisticsService.getListAccountDyData(dataStatisticsAllDOS,startTime,endTime,reqVO.getDataType(),bindIds);
        }
        respVO.setCountIncrement(dataStatisticsAllDOS.stream().mapToInt(AccountDyDataStatisticsDO::getFansCountIncrement).sum());
        respVO.setPublishCount(dataStatisticsAllDOS.stream().mapToInt(AccountDyDataStatisticsDO::getPublishCountIncrement).sum());
        respVO.setPlayCountIncrement(dataStatisticsAllDOS.stream().mapToInt(AccountDyDataStatisticsDO::getPlayCountIncrement).sum());
        respVO.setDiggCountIncrement(dataStatisticsAllDOS.stream().mapToInt(AccountDyDataStatisticsDO::getDiggCountIncrement).sum());
        respVO.setCommentCountIncrement(dataStatisticsAllDOS.stream().mapToInt(AccountDyDataStatisticsDO::getCommentCountIncrement).sum());
        respVO.setShareCountIncrement(dataStatisticsAllDOS.stream().mapToInt(AccountDyDataStatisticsDO::getShareCountIncrement).sum());

        Map<LocalDate, List<AccountDyDataStatisticsDO>> listMap = dataStatisticsAllDOS.stream().collect(Collectors.groupingBy(AccountDyDataStatisticsDO::getStatisticsTime));

        List<AccountDyDataDateRespVO> fanDateIncrement = new ArrayList<>();
        List<AccountDyDataDateRespVO> playDateIncrement = new ArrayList<>();
        List<AccountDyDataDateRespVO> diggDateIncrement = new ArrayList<>();
        List<AccountDyDataDateRespVO> commentDateIncrement = new ArrayList<>();
        List<AccountDyDataDateRespVO> shareDateIncrement = new ArrayList<>();
        List<AccountDyDataDateRespVO> publishDateIncrement = new ArrayList<>();
        for(Map.Entry<LocalDate, List<AccountDyDataStatisticsDO>> entry : listMap.entrySet()){
            AccountDyDataDateRespVO fanDateRespVO = new AccountDyDataDateRespVO();
            fanDateRespVO.setStatisticsTime(entry.getKey());
            fanDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(AccountDyDataStatisticsDO::getFansCountIncrement).sum());
            fanDateIncrement.add(fanDateRespVO);

            AccountDyDataDateRespVO playDateRespVO = new AccountDyDataDateRespVO();
            playDateRespVO.setStatisticsTime(entry.getKey());
            playDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(AccountDyDataStatisticsDO::getPlayCountIncrement).sum());
            playDateIncrement.add(playDateRespVO);

            AccountDyDataDateRespVO diggDateRespVO = new AccountDyDataDateRespVO();
            diggDateRespVO.setStatisticsTime(entry.getKey());
            diggDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(AccountDyDataStatisticsDO::getDiggCountIncrement).sum());
            diggDateIncrement.add(diggDateRespVO);

            AccountDyDataDateRespVO commentDateRespVO = new AccountDyDataDateRespVO();
            commentDateRespVO.setStatisticsTime(entry.getKey());
            commentDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(AccountDyDataStatisticsDO::getCommentCountIncrement).sum());
            commentDateIncrement.add(commentDateRespVO);

            AccountDyDataDateRespVO shareDateRespVO = new AccountDyDataDateRespVO();
            shareDateRespVO.setStatisticsTime(entry.getKey());
            shareDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(AccountDyDataStatisticsDO::getShareCountIncrement).sum());
            shareDateIncrement.add(shareDateRespVO);

            AccountDyDataDateRespVO publishDateRespVO = new AccountDyDataDateRespVO();
            publishDateRespVO.setStatisticsTime(entry.getKey());
            publishDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(AccountDyDataStatisticsDO::getPublishCountIncrement).sum());
            publishDateIncrement.add(publishDateRespVO);

        }

        respVO.setFanDateIncrement(fanDateIncrement.stream().sorted(Comparator.comparing(AccountDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setDiggDateIncrement(diggDateIncrement.stream().sorted(Comparator.comparing(AccountDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setPlayDateIncrement(playDateIncrement.stream().sorted(Comparator.comparing(AccountDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setCommentDateIncrement(commentDateIncrement.stream().sorted(Comparator.comparing(AccountDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setShareDateIncrement(shareDateIncrement.stream().sorted(Comparator.comparing(AccountDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setPublishCountDate(publishDateIncrement.stream().sorted(Comparator.comparing(AccountDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
    }

    private void buildYesterdayData(AccountDyDataStatisticsRespVO respVO, LocalDate statisticsTime, List<AccountDyDataStatisticsDO> accountDyDataStatisticsDOS) {
        //粉丝
        respVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getFansCountIncrement).sum());
        List<AccountDyDataDateRespVO> fanCountList = new ArrayList<>();
        AccountDyDataDateRespVO fanDateRespVO = new AccountDyDataDateRespVO();
        fanDateRespVO.setStatisticsTime(statisticsTime);
        fanDateRespVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getFansCountIncrement).sum());
        fanCountList.add(fanDateRespVO);
        respVO.setFanDateIncrement(fanCountList);


        //播放
        respVO.setPlayCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getPlayCountIncrement).sum());
        List<AccountDyDataDateRespVO> playCountList = new ArrayList<>();
        AccountDyDataDateRespVO playDateRespVO = new AccountDyDataDateRespVO();
        playDateRespVO.setStatisticsTime(statisticsTime);
        playDateRespVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getPlayCountIncrement).sum());
        playCountList.add(playDateRespVO);
        respVO.setPlayDateIncrement(playCountList);
        //点赞
        respVO.setDiggCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getDiggCountIncrement).sum());
        List<AccountDyDataDateRespVO> diggCountList = new ArrayList<>();
        AccountDyDataDateRespVO diggDateRespVO = new AccountDyDataDateRespVO();
        diggDateRespVO.setStatisticsTime(statisticsTime);
        diggDateRespVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getDiggCountIncrement).sum());
        diggCountList.add(diggDateRespVO);
        respVO.setDiggDateIncrement(diggCountList);
        //评论
        respVO.setCommentCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getCommentCountIncrement).sum());
        List<AccountDyDataDateRespVO> commentCountList = new ArrayList<>();
        AccountDyDataDateRespVO commentDateRespVO = new AccountDyDataDateRespVO();
        commentDateRespVO.setStatisticsTime(statisticsTime);
        commentDateRespVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getCommentCountIncrement).sum());
        commentCountList.add(commentDateRespVO);
        respVO.setCommentDateIncrement(commentCountList);

        //发布数
        respVO.setPublishCount(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getPublishCountIncrement).sum());
        List<AccountDyDataDateRespVO> publishCountList = new ArrayList<>();
        AccountDyDataDateRespVO publishDateRespVO = new AccountDyDataDateRespVO();
        publishDateRespVO.setStatisticsTime(statisticsTime);
        publishDateRespVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getPublishCountIncrement).sum());
        publishCountList.add(publishDateRespVO);
        respVO.setPublishCountDate(publishCountList);

        //转发
        respVO.setShareCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getShareCountIncrement).sum());
        List<AccountDyDataDateRespVO> shareCountList = new ArrayList<>();
        AccountDyDataDateRespVO shareDateRespVO = new AccountDyDataDateRespVO();
        shareDateRespVO.setStatisticsTime(statisticsTime);
        shareDateRespVO.setCountIncrement(accountDyDataStatisticsDOS.stream().mapToInt(AccountDyDataStatisticsDO::getShareCountIncrement).sum());
        shareCountList.add(shareDateRespVO);
        respVO.setShareDateIncrement(shareCountList);
    }


    @Override
    public List<AccountDyDataStatisticsAnalyzeRespVO> getStatisticsForAnalyze(Integer dateType,Integer platformType,Integer orderBy,Boolean isAsc) {
        List<AccountDyDataStatisticsAnalyzeRespVO> respVOList = new ArrayList<>();
        String sc = isAsc ? " asc" : " desc";
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(dateType)) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
            String orderByStr = "order by "+ StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue() + sc + " limit 5";
            List<AccountDyDataStatisticsDO> accountDyDataStatisticsDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
                    .eq(AccountDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(AccountDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(AccountDyDataStatisticsDO::getPlatformType, platformType)
                    .last(orderByStr));

            if (CollectionUtil.isEmpty(accountDyDataStatisticsDOS)) {
                return respVOList;
            }

            List<AccountDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(accountDyDataStatisticsDOS, AccountDyDataStatisticsAnalyzeRespVO.class);
            return voList;
        }
        List<AccountDyDataStatisticsAnalyzeRespVO> analyzeRespVOS = this.buildMoreDaysData(dateType, platformType, orderBy, null, null, respVOList, sc,0,5,null);

        List<AccountDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(analyzeRespVOS, AccountDyDataStatisticsAnalyzeRespVO.class);

        return voList;

    }

    private List<AccountDyDataStatisticsAnalyzeRespVO> buildMoreDaysData(Integer dateType, Integer platformType, Integer orderBy, Long dyBindId, List<Long> dyBindIds,List<AccountDyDataStatisticsAnalyzeRespVO> respVOList, String sc,int startNum,int pageSize,Map map) {
        LocalDate startTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
        LocalDate endTime = LocalDateTimeUtils.getMinusDaysTime(1);
        String orderByStr = StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue();

        LambdaQueryWrapper<AccountDyDataStatisticsDO> lambdaQueryWrapper = new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
//                .ge(AccountDyDataStatisticsDO::getStatisticsTime, startTime)
                .eq(AccountDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                .eq(AccountDyDataStatisticsDO::getPlatformType, platformType)
                .eq(dyBindId!=null,AccountDyDataStatisticsDO::getDyBindId,dyBindId)
                .in(dyBindIds!=null,AccountDyDataStatisticsDO::getDyBindId,dyBindIds);
        if(map != null) {
            Long count = accountDyDataStatisticsMapper.selectCount(lambdaQueryWrapper);
            map.put("count",count);
        }
        lambdaQueryWrapper.last("order by "+orderByStr+" "+sc+" limit "+startNum+","+pageSize);
        List<AccountDyDataStatisticsDO> accountDyDataStatisticsDOS = accountDyDataStatisticsMapper.selectList(lambdaQueryWrapper);

        if(CollectionUtil.isEmpty(accountDyDataStatisticsDOS)){
            return respVOList;
        }
        this.buildAnalyzeResp(respVOList, startTime, accountDyDataStatisticsDOS);
        return respVOList;
    }

    private void buildAnalyzeResp(List<AccountDyDataStatisticsAnalyzeRespVO> respVOList, LocalDate startTime, List<AccountDyDataStatisticsDO> accountDyDataStatisticsDOS) {
        for (AccountDyDataStatisticsDO endStatisticsDO : accountDyDataStatisticsDOS) {
            AccountDyDataStatisticsAnalyzeRespVO statisticsAnalyzeRespVO = AccountDyDataStatisticsConvert.INSTANCE.convert(endStatisticsDO);
            AccountDyDataStatisticsDO startStatisticsDO = accountDyDataStatisticsMapper.selectOne(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
//                    .ge(AccountDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(startTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                    .eq(AccountDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(AccountDyDataStatisticsDO::getDyBindId, endStatisticsDO.getDyBindId())
                    .orderByAsc(AccountDyDataStatisticsDO::getStatisticsTime)
                    .last("limit 1")
                    );
            if (startStatisticsDO != null) {
                if(startStatisticsDO.getId().equals(endStatisticsDO.getId())){
                    statisticsAnalyzeRespVO.setPublishCountIncrement(endStatisticsDO.getPublishCount());
                    statisticsAnalyzeRespVO.setPlayCountIncrement(endStatisticsDO.getPlayCount());
                    statisticsAnalyzeRespVO.setFansCountIncrement(endStatisticsDO.getFansCount());
                    statisticsAnalyzeRespVO.setDiggCountIncrement(endStatisticsDO.getDiggCount());
                    statisticsAnalyzeRespVO.setCommentCountIncrement(endStatisticsDO.getCommentCount());
                    statisticsAnalyzeRespVO.setShareCountIncrement(endStatisticsDO.getShareCount());
                    statisticsAnalyzeRespVO.setCollectCountIncrement(endStatisticsDO.getCollectCount());
                }else {
                    statisticsAnalyzeRespVO.setPublishCountIncrement(Math.max(endStatisticsDO.getPublishCount() - startStatisticsDO.getPublishCount(), 0));
                    statisticsAnalyzeRespVO.setPlayCountIncrement(Math.max(endStatisticsDO.getPlayCount() - startStatisticsDO.getPlayCount(), 0));
                    statisticsAnalyzeRespVO.setFansCountIncrement(Math.max(endStatisticsDO.getFansCount() - startStatisticsDO.getFansCount(), 0));
                    statisticsAnalyzeRespVO.setDiggCountIncrement(Math.max(endStatisticsDO.getDiggCount() - startStatisticsDO.getDiggCount(), 0));
                    statisticsAnalyzeRespVO.setCommentCountIncrement(Math.max(endStatisticsDO.getCommentCount() - startStatisticsDO.getCommentCount(), 0));
                    statisticsAnalyzeRespVO.setShareCountIncrement(Math.max(endStatisticsDO.getShareCount() - startStatisticsDO.getShareCount(), 0));
                    statisticsAnalyzeRespVO.setCollectCountIncrement(Math.max(endStatisticsDO.getCollectCount() - startStatisticsDO.getCollectCount(), 0));
                }
            }
            respVOList.add(statisticsAnalyzeRespVO);
        }
    }

    @Override
    public PageResult<AccountDyDataStatisticsAnalyzeRespVO> getStatisticsForPage(AccountDyDataStatisticsPageReqVO accountDyDataStatisticsPageReqVO){
        boolean isAsc = accountDyDataStatisticsPageReqVO.getIsAsc();
        Integer dateType = accountDyDataStatisticsPageReqVO.getDateType();
        Integer platformType = accountDyDataStatisticsPageReqVO.getPlatformType();
        Integer orderBy = accountDyDataStatisticsPageReqVO.getOrderBy();
        int pageSize = accountDyDataStatisticsPageReqVO.getPageSize();
        int pageNo = accountDyDataStatisticsPageReqVO.getPageNo()-1;
        int startNum = pageNo*pageSize;
        List<Long> dyBindIds = null;
        if(null != accountDyDataStatisticsPageReqVO.getGroupId() && null == accountDyDataStatisticsPageReqVO.getDyBindId()){
            List<AccountGroupCorrelationDO> correlationDOS = accountGroupCorrelationMapper.selectList(new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                    .eq(AccountGroupCorrelationDO::getGroupId, accountDyDataStatisticsPageReqVO.getGroupId()));
            dyBindIds = correlationDOS.stream().map(AccountGroupCorrelationDO::getDyBindId).collect(Collectors.toList());
        }
        PageResult<AccountDyDataStatisticsAnalyzeRespVO> pageResult = new PageResult<>();

        List<AccountDyDataStatisticsAnalyzeRespVO> respVOList = new ArrayList<>();
        String sc = isAsc ? " asc" : " desc";
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(dateType)) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
            String orderByStr = "order by "+ StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue() + sc + " limit "+startNum+","+pageSize;
            LambdaQueryWrapperX<AccountDyDataStatisticsDO> lambdaQueryWrapper = new LambdaQueryWrapperX<AccountDyDataStatisticsDO>()
                    .eq(AccountDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(AccountDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(AccountDyDataStatisticsDO::getPlatformType, platformType)
                    .eqIfPresent(AccountDyDataStatisticsDO::getDyBindId,accountDyDataStatisticsPageReqVO.getDyBindId())
                    .inIfPresent(AccountDyDataStatisticsDO::getDyBindId, dyBindIds);

            Long count = accountDyDataStatisticsMapper.selectCount(lambdaQueryWrapper);
            lambdaQueryWrapper.last(orderByStr);
            List<AccountDyDataStatisticsDO> accountDyDataStatisticsDOS = accountDyDataStatisticsMapper.selectList(lambdaQueryWrapper);

            if (CollectionUtil.isEmpty(accountDyDataStatisticsDOS)) {
                pageResult.setList(respVOList);
                return pageResult;
            }

            List<AccountDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(accountDyDataStatisticsDOS, AccountDyDataStatisticsAnalyzeRespVO.class);
            pageResult.setList(voList);
            pageResult.setTotal(count);
            return pageResult;
        }
        Map<String,Long> map = new HashMap();
        List<AccountDyDataStatisticsAnalyzeRespVO> analyzeRespVOS = this.buildMoreDaysData(dateType, platformType, orderBy,accountDyDataStatisticsPageReqVO.getDyBindId(), dyBindIds,respVOList, sc,startNum,pageSize,map);

        List<AccountDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(analyzeRespVOS, AccountDyDataStatisticsAnalyzeRespVO.class);
        pageResult.setList(voList);
        pageResult.setTotal(map.get("count"));

        return pageResult;

    }

    @Override
    public AccountDyDataStatisticsDO getYesterdayDataByOrderId(Long bindId, Integer type, LocalDate statisticsTime) {
        return accountDyDataStatisticsMapper.selectOne(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
                .eq(AccountDyDataStatisticsDO::getDyBindId,bindId)
                .eq(AccountDyDataStatisticsDO::getDyType,type)
                .ge(statisticsTime!=null,AccountDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(statisticsTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                .last("limit 1")
        );
    }

    @Override
    public void createStatisticsDO(AccountDyDataStatisticsDO statisticsDO) {

    }

}
