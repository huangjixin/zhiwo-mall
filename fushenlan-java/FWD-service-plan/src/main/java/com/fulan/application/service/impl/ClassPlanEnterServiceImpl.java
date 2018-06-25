package com.fulan.application.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.service.PersonnelService;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.ClassPlanEnter;
import com.fulan.api.plan.vo.ClassPlanEnterVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.ClassPlanEnterMapper;
import com.fulan.application.mapper.ClassPlanMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.ClassPlanEnterService;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 班级计划报名 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-02-06
 */
@Service
@Transactional
public class ClassPlanEnterServiceImpl extends ServiceImpl<ClassPlanEnterMapper, ClassPlanEnter> implements ClassPlanEnterService {

    @Autowired
    private ClassPlanEnterMapper classPlanEnterMapper;
    @Autowired
    private ClassPlanMapper classPlanMapper;
    @Autowired
    AccountService AccountService;
    @Autowired
    PersonnelService personnelService;
    @Autowired
    private IdGenerator idGenerator;
    @Override
    public Response<Boolean> insertClassPlanEnter(ClassPlanEnter classPlanEnter) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            ClassPlan classPlan = classPlanMapper.findClassPlanDetailById(classPlanEnter.getPlanId());
            if(classPlan!=null&&classPlanEnter.getPlanId()!=null&&classPlanEnter.getUserId()!=null) {
                Map<String,Object> paramMap = new HashMap<String,Object>();
                 paramMap.put("planId",classPlanEnter.getPlanId());
                 //查看报名总人数
                Integer enterNum = classPlanEnterMapper.getEnterCount(paramMap);
                //查看该用户是否已经报名
                paramMap.put("userId",classPlanEnter.getUserId());
                Integer enterFlag = classPlanEnterMapper.getEnterCount(paramMap);
                if(classPlan.getUserLimit()!=null&&enterFlag<=0&&enterNum<classPlan.getUserLimit()){
                    classPlanEnter.setId(idGenerator.generate());
                    classPlanEnter.setGmtCreate(new Date());
                    classPlanEnterMapper.insert(classPlanEnter);
                    res.setCode(Response.SUCCESS);
                    res.setMsg(Response.SUCCESS_MESSAGE);
                }
            }else{
                res.setCode(Response.ERROR);
                res.setMsg(Response.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }
	@Override
	@TxTransaction
	@Transactional
	public Response<Account> insertClassPlanEnterForEr(ClassPlanEnterVo classPlanEnterVo) {
		// TODO Auto-generated method stub
		Response<Account> res = null;
	 try {
		long accountId = idGenerator.generate();
		//随机生成密码
		String password = UUID.randomUUID().toString().substring(0, 8);
		//创建代理人account账号
		Response<Personnel> responsePersonnel = personnelService.getPersonnel(String.valueOf(classPlanEnterVo.getPersonnelId()));
		Personnel personnel = responsePersonnel.getData();
		Account account = new Account();
		account.setId(accountId);
		account.setAccountName(personnel.getIdentityCode());
		account.setPassword(password);
		account.setAccountType("2");
		res = AccountService.addAccountForEr(account);
		//关联学习计划
		 ClassPlan classPlan = classPlanMapper.findClassPlanDetailById(classPlanEnterVo.getPlanId());
         if(classPlan!=null&&classPlanEnterVo.getPlanId()!=null&&classPlanEnterVo.getUserId()!=null) {
             Map<String,Object> paramMap = new HashMap<String,Object>();
              paramMap.put("planId",classPlanEnterVo.getPlanId());
              //查看报名总人数
             Integer enterNum = classPlanEnterMapper.getEnterCount(paramMap);
             //查看该用户是否已经报名
             paramMap.put("userId",classPlanEnterVo.getUserId());
             Integer enterFlag = classPlanEnterMapper.getEnterCount(paramMap);
             if(classPlan.getUserLimit()!=null&&enterFlag<=0&&enterNum<classPlan.getUserLimit()){
            	 ClassPlanEnter classPlanEnter = new ClassPlanEnter();
            	 
                 classPlanEnter.setId(idGenerator.generate());
                 classPlanEnter.setGmtCreate(new Date());
                 classPlanEnter.setPlanId(classPlanEnterVo.getPlanId());
                 classPlanEnter.setUserId(res.getData().getId());
                 
                 classPlanEnterMapper.insert(classPlanEnter);
             	}
         	  } else{
                  res.setCode(Response.ERROR);
                  res.setMsg(Response.ERROR_MESSAGE);
              }
         	//更新流程信息
         personnelService.updatePersonnel(CommenConstant.ER_Flow_TRAINING_TEST, String.valueOf(classPlanEnterVo.getPersonnelId()),classPlanEnterVo.getOrgId());
         } catch (Exception e) {
         res.setCode(Response.ERROR);
         res.setMsg(Response.ERROR_MESSAGE);
     }
	 return res;
	}
}
