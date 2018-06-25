package com.fulan.application.service.impl;

import com.fulan.api.information.domain.Activity;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.domain.News;
import com.fulan.api.information.domain.NewsViewDetails;
import com.fulan.api.information.vo.CommenConstant;
import com.fulan.api.information.vo.InformationContentVO;
import com.fulan.api.information.vo.InformationVO;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.vo.AccountVO;
import com.fulan.application.customer.controller.InformationController;
import com.fulan.application.mapper.InformationMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.InformationService;
import com.fulan.application.util.domain.Response;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fsl on 2018/4/8.
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(InformationController.class);



    @Override
    public Response<List<InformationVO>> queryInformationList(Integer informationType) {

        Response<List<InformationVO>> listResponse = new Response<>(Response.SUCCESS,"成功");

        //AccountVO account = (AccountVO)redisUtil.getDmsUserInfo();
        AccountVO account = new AccountVO();
        account.setId("1");
        if(null==account){
            listResponse.setCode("0");
            listResponse.setMsg("未登录");
            return listResponse;
        }
        if(StringUtils.isEmpty(informationType)){
            listResponse.setCode("0");
            listResponse.setMsg("参数为空");
            return listResponse;
        }
        if(informationType.equals(CommenConstant.COMPANY_DYNAMIC)){

           return queryType(informationType,listResponse,account);


        }else if(informationType.equals(CommenConstant.HOT_ACTIVITY)){
            List<InformationVO> informationVOs=
                    informationMapper.queryInformationListByInformationType(informationType,account.getId(),account.getOrgId(),CommenConstant.IS_STATUS,account.getGeneralCompanyId(),account.getCompanyId());


            for (InformationVO informationVO : informationVOs) {
                Integer readCount = informationMapper.queryReadCount(informationVO.getInformationId(), informationVO.getInfoNewsType());
                informationVO.setReadCount(readCount);
                Integer signUpCount = informationMapper.queryActivityCount(informationVO.getInformationId());
                informationVO.setSignUpCount(signUpCount);
                Integer hasSignUpdPerson = informationMapper.queryHasSignUpPerson(informationVO.getInformationId());
                informationVO.setHasSignedUpPerson(hasSignUpdPerson);
                Long hasSignUp = informationMapper.queryHasSignUp(account.getId(), informationVO.getInformationId());
                if(null==hasSignUp){
                    informationVO.setHasSignedUp(CommenConstant.NOT_HAS_SIGN_UP);
                }else {
                    informationVO.setHasSignedUp(CommenConstant.HAS_SIGN_UP);
                }

            }
            listResponse.setData(informationVOs);
            return listResponse;

        }else  if(informationType.equals(CommenConstant.POLICY_STATEMENTS)){
                return queryType(informationType,listResponse,account);
        }else if(informationType.equals(CommenConstant.PRODUCT_ONLINE)){
                return queryType(informationType,listResponse,account);
        }


        listResponse.setCode("0");
        listResponse.setMsg("参数异常");
        return listResponse;
    }

    @Override
    public Response<String> activitySignUp(Long activityId) {
        Response<String> resp = new Response<>(Response.SUCCESS,"报名成功");
       // AccountVO account = (AccountVO)redisUtil.getDmsUserInfo();
        //TODO用户去redis中去取
        AccountVO account = new AccountVO();
        account.setId("1");
        if(null==account){
            resp.setCode(Response.ERROR);
            resp.setMsg("先去登录");
            return resp;
        }
        if(null==activityId){
            resp.setCode(Response.ERROR);
            resp.setMsg("参数为空");
            return resp;
        }
        Long hasAct=informationMapper.queryHasActivity(activityId);
        if(null==hasAct){
            resp.setCode(Response.ERROR);
            resp.setMsg("没有该活动");
            return resp;
        }
        Long hasSignUp = informationMapper.queryHasSignUp(account.getId(), activityId);
        if(null!=hasSignUp){
            resp.setCode(Response.ERROR);
            resp.setMsg("你已报过名");
            return resp;
        }

            Integer signUpCount=informationMapper.queryActivityCount(activityId);
        if(null==signUpCount){
            ApplyDetail app = new ApplyDetail();
            app.setId(Idfactory.generate());
            app.setAgentCode(account.getId());
            app.setAcountName(account.getAccountName());
            app.setActivityId(activityId);
            app.setCompanyId(account.getGeneralCompanyId());
            app.setCompanyName(account.getGeneralCompanyName());
            app.setBranchCompanyId(account.getCompanyId());
            app.setBranchCompanyName(account.getCompanyName());
            app.setOrgId(account.getOrgId());
            app.setOrgName(account.getOrgName());
            app.setLevelId(account.getPostType());
            app.setApplyDate(new Date());
            informationMapper.insertSignUp(app);
            return resp;
        }
            Integer hasSignUpPerson=informationMapper.queryHasSignUpPerson(activityId);
            if(hasSignUpPerson<=signUpCount){
                ApplyDetail app = new ApplyDetail();
                app.setId(Idfactory.generate());
                app.setAgentCode(account.getId());
                app.setAcountName(account.getAccountName());
                app.setActivityId(activityId);
                app.setCompanyId(account.getGeneralCompanyId());
                app.setCompanyName(account.getGeneralCompanyName());
                app.setBranchCompanyId(account.getCompanyId());
                app.setBranchCompanyName(account.getCompanyName());
                app.setOrgId(account.getOrgId());
                app.setOrgName(account.getOrgName());
                app.setLevelId(account.getPostType());
                app.setApplyDate(new Date());
                informationMapper.insertSignUp(app);
                return resp;
            }
            resp.setCode("0");
            resp.setMsg("已经报满");
            return  resp;


    }

    @Override
    public Response<InformationContentVO> queryDetailById(Long informationId, Integer infoNewsType) {
        Response<InformationContentVO> resp = new Response<>(Response.SUCCESS,"查询成功");
            if(null==informationId || null==infoNewsType){
                resp.setCode("0");
                resp.setMsg("参数为空");
                return  resp;
            }
        if(CommenConstant.ACTIVITY_TYPE==infoNewsType){
            InformationContentVO info=informationMapper.queryInfoContent(informationId);
            NewsViewDetails newsV = new NewsViewDetails();
            newsV.setId(Idfactory.generate());
            newsV.setNewsId(informationId);
            newsV.setAgentCode("1");
            newsV.setType(CommenConstant.ACTIVITY_TYPE);
            if(null==newsV.getViewCount()) {
                newsV.setViewCount(1l);
            }else {
                newsV.setViewCount(newsV.getViewCount()+1);
            }
            informationMapper.insertReadCount(newsV);
            resp.setData(info);
            return resp;
        }
        if(CommenConstant.NEWS_TYPE==infoNewsType){
            InformationContentVO info=informationMapper.queryNewsContent(informationId);
            NewsViewDetails newsV = new NewsViewDetails();
            newsV.setId(Idfactory.generate());
            newsV.setNewsId(informationId);
            //TODO代理编号
            newsV.setAgentCode("1");
            newsV.setType(CommenConstant.NEWS_TYPE);
            if(null==newsV.getViewCount()) {
                newsV.setViewCount(1l);
            }else {
                newsV.setViewCount(newsV.getViewCount()+1);
            }
            newsV.setViewCount(newsV.getViewCount()+1);
            informationMapper.insertReadCount(newsV);
            resp.setData(info);
            return resp;
        }
        resp.setCode("0");
        resp.setMsg("参数异常");
        return resp;


    }


    public Response<List<InformationVO>> queryType(Integer informationType,Response<List<InformationVO>> listResponse,
                          AccountVO account){

        List<InformationVO> info=informationMapper.queryInformationListByInformationType(informationType,account.getId(),account.getOrgId(),CommenConstant.IS_STATUS,account.getGeneralCompanyId(),account.getCompanyId());
        listResponse.setData(info);
       return listResponse;
    }
}
