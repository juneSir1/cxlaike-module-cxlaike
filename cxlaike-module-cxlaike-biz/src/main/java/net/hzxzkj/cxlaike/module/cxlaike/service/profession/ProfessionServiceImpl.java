package net.hzxzkj.cxlaike.module.cxlaike.service.profession;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo.ProfessionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.profession.ProfessionConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.profession.ProfessionDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.profession.AProfessionMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.redis.ProfessionRedisDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.util.*;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * 行业 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class ProfessionServiceImpl implements ProfessionService {

    @Resource
    private AProfessionMapper professionMapper;
    @Resource
    private ProfessionRedisDAO areaRedisDAO;


    @Override
    public List<ProfessionRespVO> getProfessionList() {
        List<ProfessionRespVO> professionRespVOS = areaRedisDAO.get();
        if (professionRespVOS != null && professionRespVOS.size() > 0) {
            return professionRespVOS;
        }
        List<ProfessionDO> oneProfessionDO = professionMapper.selectList(new LambdaQueryWrapper<ProfessionDO>()
                .eq(ProfessionDO::getLevel, 1)//一级行业
        );
        List<ProfessionRespVO> oneProfession = ProfessionConvert.INSTANCE.convertList(oneProfessionDO);
        for (ProfessionRespVO professionRespVO : oneProfession) {

            List<ProfessionDO> twoProfessionDOS = professionMapper.selectList(new LambdaQueryWrapper<ProfessionDO>()
                    .eq(ProfessionDO::getLevel, 2)//二级行业
                    .eq(ProfessionDO::getUpCode, professionRespVO.getCode())
            );
            List<ProfessionRespVO> twoProfession = ProfessionConvert.INSTANCE.convertList(twoProfessionDOS);
            professionRespVO.setChildren(twoProfession);

            for (ProfessionRespVO twoProfessionRespVO : twoProfession) {

                List<ProfessionDO> threeProfessionDOS = professionMapper.selectList(new LambdaQueryWrapper<ProfessionDO>()
                        .eq(ProfessionDO::getLevel, 3)//二级行业
                        .eq(ProfessionDO::getUpCode, twoProfessionRespVO.getCode())
                );

                List<ProfessionRespVO> threeProfession = ProfessionConvert.INSTANCE.convertList(threeProfessionDOS);
                twoProfessionRespVO.setChildren(threeProfession);
            }
        }
        //存redis
        areaRedisDAO.set(oneProfession);

        return oneProfession;
    }

    @Override
    public ProfessionDO getByCode(String code) {
        return professionMapper.selectOne(new LambdaQueryWrapper<ProfessionDO>()
                                .eq(ProfessionDO::getCode,code)
                                .last("limit 1"));
    }

    @Override
    public String[] getCodesByCode(String code) {
        ProfessionDO professionDO = professionMapper.selectOne(new LambdaQueryWrapper<ProfessionDO>()
                .eq(ProfessionDO::getCode,code)
                .eq(ProfessionDO::getLevel,3)
                .last("limit 1"));
        if(professionDO == null){
            throw exception(DATA_ERROR);
        }
        ProfessionDO oneProfessionDO = professionMapper.selectOne(new LambdaQueryWrapper<ProfessionDO>()
                .eq(ProfessionDO::getCode,professionDO.getUpCode())
                .eq(ProfessionDO::getLevel,2)
                .last("limit 1"));
        if(oneProfessionDO == null){
            throw exception(DATA_ERROR);
        }
        String[] codes = {oneProfessionDO.getUpCode(),professionDO.getUpCode(),code};
        return codes;
    }


}
