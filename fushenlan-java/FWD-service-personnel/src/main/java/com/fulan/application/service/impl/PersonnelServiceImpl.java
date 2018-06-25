package com.fulan.application.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.service.FlowActionService;
import com.fulan.api.personnel.domain.ErTag;
import com.fulan.api.personnel.domain.InterviewAction;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.vo.PersonnelEducation;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.personnel.vo.PersonnelManageVo;
import com.fulan.api.personnel.vo.PersonnelPool;
import com.fulan.api.personnel.vo.PersonnelVo;
import com.fulan.api.personnel.vo.SearchPersonnelVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.MessageService;
import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.TagService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.InterviewActionMapper;
import com.fulan.application.mapper.PersonnelMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.ErTagService;
import com.fulan.application.service.PersonnelService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.message.MessageUtil;
import com.fulan.application.util.page.PageInfo;

@Service
@TxTransaction
@Transactional
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {

	@Autowired
	PersonnelMapper personnelMapper;

	@Autowired
	InterviewActionMapper interviewActionMapper;

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	IdGenerator idGenerator;
	

	@Autowired
	AttachmentService attachmentService;

	@Autowired
	TagService tagService;

	@Autowired
	ErTagService erTagService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	FlowActionService flowActionService;

	private int getPages(int size, int total) {
		int pages = 0;
		if (size == 0) {
			return 0;
		}
		pages = total / size;
		if (total % size != 0) {
			pages++;
		}
		return pages;
	}

	

	@Override
	public PersonnelManageInfoVo PersonnelCheck(String paperId) {
		return personnelMapper.checkPersonnelManageInfoById(Long.valueOf(paperId));
	}

	/**
	 * 
	 */
	@Override
	public Response<String> save(PersonnelVo personnelVo) {
		Account account = (Account) redisUtil.getUserInfo();// 获取用户对象
		Long id = idGenerator.generate();
		Personnel personnel = personnelVo.getPersonnel();
		String personnelStatus = personnel.getPersonnelStatus();
		//关联证件照
		List<Attachment> imgList = personnelVo.getImgList();
		for(Attachment attachment:imgList){
			if (personnel.getId() == 0) {
				attachment.setHostId(id);
			} else {
				attachment.setHostId(personnel.getId());
			}
			if(attachment.getId() != null && attachment.getId() != 0){
				attachmentService.updateById(attachment);
			}
		}
		
		personnel.setPersonnelStatus(CommenConstant.ER_Flow_signup);
		long pId = personnel.getId();
		Response<String> resp = new Response<String>(Response.SUCCESS, "添加个人信息成功");
		//判断增员人才id是否存在，如果不存在进行插入操作，否则进行更新操作
		if (personnel.getId() == 0) {
			personnel.setCreateTime(new Date());
			personnel.setCreateUser(account.getId());
			personnel.setId(id);
			personnel.setOrgId(account.getCompanyId());
			personnelMapper.insert(personnel);
		} else {
			id = personnel.getId();
			personnelMapper.updateByPrimaryKeySelective(personnel);
		}
		resp.setData(id.toString());
		FlowAction flowAction =  flowActionService.getFlowActionByOrgIdAndStep(account.getCompanyId(), 1);
		// 录入面试节点信息，用户信息不存在时进行节点更新，否则不进行任何操作
		if (pId == 0||"0".equals(personnelStatus)) {
			InterviewAction interviewAction = new InterviewAction();
			interviewAction.setFlowActionId(flowAction.getId());
			interviewAction.setPersonnelId(id);
			interviewAction.setStartTime(new Date());
			interviewAction.setId(idGenerator.generate());
			interviewAction.setProcessingPerson(account.getId());
			interviewAction.setProcessingName(account.getAccountName());
			interviewAction.setCreateTime(new Date());
			interviewAction.setCreateUser(account.getId());
			interviewActionMapper.insert(interviewAction);
		}
		return resp;
	}

	/**
	 * 
	 * 
	 * 
	 * @see com.fulan.application.service.PersonnelService#saveandtag(com.fulan.api.personnel.vo.PersonnelVo)
	 */
	@Override
	public Response<String> saveandtag(PersonnelVo personnelVo) {
		Account account = (Account) redisUtil.getUserInfo();// 获取用户对象
		Long id = idGenerator.generate();
		Personnel personnel = personnelVo.getPersonnel();

		personnel.setId(id);
		personnel.setPersonnelStatus("0");

		long pId = personnel.getId();
		// 检查账号是否存在(根据证件编号判断是否存在)
		Response<String> resp = new Response<String>(Response.SUCCESS, "添加个人信息成功");

		personnel.setCreateTime(new Date());
		personnel.setCreateUser(account.getId());
		personnel.setOrgId(account.getCompanyId());
		personnelMapper.insert(personnel);
		resp.setData(id.toString());

		/**
		 * 添加标签跟跟人信息的关系
		 */
		if (personnelVo.getTags() != null) {
			for (int i = 0; i < personnelVo.getTags().size(); i++) {
				//标签库里面没有 标签需新增标签
				if (personnelVo.getTags().get(i).getId() == null) {
					Long tagid = idGenerator.generate();
					Tag tag = new Tag();
					tag.setId(tagid);
					tag.setTagName(personnelVo.getTags().get(i).getTagName());
					tag.setCreateUser(account.getId());
					tag.setCreateTime(new Date());
					tag.setMoudle(3);
					tagService.insertDetail(tag);
					ErTag ertag = new ErTag();
					ertag.setPersonnelId(personnel.getId());
					ertag.setTagId(tagid);
					ertag.setTagName(personnelVo.getTags().get(i).getTagName());
					
					ertag.setCreateTime(new Date());
					ertag.setCreateUser(account.getId());
					ertag.setId(idGenerator.generate());
					erTagService.addErTag(ertag);
				} else {
					ErTag ertag = new ErTag();
					ertag.setPersonnelId(personnel.getId());
					ertag.setTagId(personnelVo.getTags().get(i).getId());
					ertag.setTagName(personnelVo.getTags().get(i).getTagName());
					ertag.setCreateTime(new Date());
					ertag.setCreateUser(account.getId());
					ertag.setId(idGenerator.generate());
					erTagService.addErTag(ertag);
				}
			}
		}

		return resp;
	}

	/**
	 * 
	 * 
	 * 
	 * @see com.fulan.application.service.PersonnelService#saveandtag(com.fulan.api.personnel.vo.PersonnelVo)
	 */
	@Override
	public Response<String> updatepersonnelandtag(PersonnelVo personnelVo) {
		Account account = (Account) redisUtil.getUserInfo();// 获取用户对象
		Personnel personnel = personnelVo.getPersonnel();

		// 检查账号是否存在(根据证件编号判断是否存在)
		Response<String> resp = new Response<String>(Response.SUCCESS, "修改个人信息成功");

		personnel.setUpdateTime(new Date());
		personnel.setUpdateUser(account.getId());

		personnelMapper.updateById(personnel);
		resp.setData(personnel.getId().toString());
		
		erTagService.removeErTagbypersonnelId(personnel.getId());
		
		
		/**
		 * 添加标签跟跟人信息的关系
		 */
		if (personnelVo.getTags() != null) {
			for (int i = 0; i < personnelVo.getTags().size(); i++) {
				//标签库里面没有 标签需新增标签
				if (personnelVo.getTags().get(i).getId() == null) {
					Long tagid = idGenerator.generate();
					Tag tag = new Tag();
					tag.setId(tagid);
					tag.setTagName(personnelVo.getTags().get(i).getTagName());
					tag.setCreateUser(account.getId());
					tag.setCreateTime(new Date());
					tag.setMoudle(3);
					tagService.insertDetail(tag);
					ErTag ertag = new ErTag();
					ertag.setPersonnelId(personnel.getId());
					ertag.setTagId(tagid);
					ertag.setTagName(personnelVo.getTags().get(i).getTagName());
					
					ertag.setCreateTime(new Date());
					ertag.setCreateUser(account.getId());
					ertag.setId(idGenerator.generate());
					erTagService.addErTag(ertag);
				} else {
					ErTag ertag = new ErTag();
					ertag.setPersonnelId(personnel.getId());
					ertag.setTagId(personnelVo.getTags().get(i).getId());
					ertag.setTagName(personnelVo.getTags().get(i).getTagName());
					ertag.setCreateTime(new Date());
					ertag.setCreateUser(account.getId());
					ertag.setId(idGenerator.generate());
					erTagService.addErTag(ertag);
				}
			}
		}

		return resp;
	}
	
	/**
	 * 人才详情-查看个人信息
	 */
	@Override
	public Response<Personnel> getPersonnel(Long personnelId) {
		Response<Personnel> resp = new Response<Personnel>(Response.SUCCESS, "人才详情-查找个人信息成功");
		Personnel personnel = personnelMapper.selectByPersonnelId(personnelId);
		resp.setData(personnel);
		return resp;
	}
	/**
	 * 查看个人教育信息
	 */
	@Override
	public Response<PersonnelEducation> getPersonnelEdu(Long personnelId) {
		Response<PersonnelEducation> resp = new Response<PersonnelEducation>(Response.SUCCESS, "人才详情-查找个人教育信息成功");
		PersonnelEducation personnelEducation = personnelMapper.selectEduByPersonnelId(personnelId);
		resp.setData(personnelEducation);
		return resp;
	}

	/**
	 * 人才详情-根据证件编号查找个人信息
	 */
	@Override
	public Response<Personnel> getPersonnelByIdentityCode(String identityCode) {
		Response<Personnel> resp = new Response<Personnel>(Response.SUCCESS, "根据证件编号查找个人信息成功");
		Personnel personnel = personnelMapper.getPersonnelByIdentityCode(identityCode);
		resp.setData(personnel);
		return resp;
	}

	/**
	 * 代理人人才库 accountId 当前登录用户id
	 */
	@Override
	public Response<List<Personnel>> getAgentPersonnel(String accountId) {
		Response<List<Personnel>> resp = new Response<List<Personnel>>(Response.SUCCESS, "查找代理人人才库信息成功");
		List<Personnel> personnel = personnelMapper.getAgentPersonnel(Long.valueOf(accountId));
		resp.setData(personnel);
		return resp;
	}

	/**
	 * 准增员人才库@RequestParam String accountId,@RequestParam String
	 * name,@RequestParam String cellphone accountId 当前登录用户id
	 */
	@Override
	public List<PersonnelTagVo> getMustIncrease(Page<PersonnelTagVo> page, String accountId, String keyWord) {
		List<PersonnelTagVo> personnellist = personnelMapper.getMustIncrease(page, Long.valueOf(accountId), keyWord);
		personnellist = tagService.getPersonnelVoListByList(personnellist);
		return personnellist;
	}

	public List<PersonnelTagVo> getMustIncreaseedit(String accountId, String personnelId) {
		PersonnelTagVo personnel = personnelMapper.getMustIncreaseedit(accountId, personnelId);
		List<PersonnelTagVo>  list = new ArrayList<PersonnelTagVo>();
		list.add(personnel);
		list = tagService.getPersonnelVoListByList(list);
		return list;
	}
	
	
	/**
	 * 增员中人才库 accountId 当前登录用户id
	 */
	@Override
	public Response<List<Personnel>> getIncreaseing(String accountId) {
		Response<List<Personnel>> resp = new Response<List<Personnel>>(Response.SUCCESS, "增员中才库信息成功");
		List<Personnel> personnel = personnelMapper.getIncreaseing(Long.valueOf(accountId));
		resp.setData(personnel);
		return resp;
	}

	@Override
	public Response<Boolean> uploadFile(File file, HttpServletRequest request) throws Exception {
		Response<Boolean> resp = new Response<Boolean>(Response.SUCCESS, "授权提交成功");
		InputStream is = new FileInputStream(file);
		// FileUtils.listFileNames("172.31.68.165", 22, "weblogic", "weblogic1",
		// "/home/weblogic/logs",file);
		/*
		 * try { FileInputStream in = new FileInputStream(new
		 * File("D:\\temp\\image\\gaigeming.jpg")); boolean flag =
		 * FileUtils.uploadFile("192.168.5.98", 22, "ftpuser", "ftpuser",
		 * "/home/ftpuser/www/images", "/2015/01/21", "gaigeming.jpg", in);
		 * System.out.println(flag); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); }
		 */
		resp.setData(true);
		return resp;
	}

	@Override
	public Response<PersonnelPool> getPersonnelPool(String personnelId) {
		Response<PersonnelPool> resp = new Response<PersonnelPool>(Response.SUCCESS, "查找代理人人才库基本信息成功");
		PersonnelPool personnelPool = personnelMapper.getPersonnelPool(Long.valueOf(personnelId));
		resp.setData(personnelPool);
		return resp;
	}

	public int selectteamSizebyaccountId(Long id) {
		return personnelMapper.selectteamSizebyaccountId(id);
	}

	/**
	 * selectTeamSizebyagentCode 查询某一个月入职人数
	 * 
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @param searchMonth
	 * @return
	 */
	public int selectentryNumbyparams(@Param("accountId") Long accountId, @Param("searchMonth") Long searchMonth) {

		return personnelMapper.selectentryNumbyparams(accountId, searchMonth);
	}

	/**
	 * selectTeamSizebyagentCode 查询代理人某一年入职人数
	 * 
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @param searchMonth
	 * @return
	 */
	public int selectYeaytargetbyaccountId(@Param("accountId") Long accountId) {

		return personnelMapper.selectYeaytargetbyaccountId(accountId);
	}

	/**
	 * selectteamYeaycountbyaccountId 团队年目标
	 * 
	 * @warn(注意事项 – 可选)
	 * @param accountIds
	 * @return
	 */
	public int selectteamYeaycountbyaccountId(@Param("accountIds") List<Long> accountIds) {

		return personnelMapper.selectteamYeaycountbyaccountId(accountIds);
	}

	/**
	 * selectteamMouthcountbyaccountId 团队月目标
	 * 
	 * @warn(注意事项 – 可选)
	 * @param accountIds
	 * @param searchMonth
	 * @return
	 */
	public int selectteamMouthcountbyaccountId(@Param("accountIds") List<Long> accountIds,
			@Param("searchMonth") Long searchMonth) {

		return personnelMapper.selectteamMouthcountbyaccountId(accountIds, searchMonth);
	}

	public String rankingbyaccountId(@Param("accountIds") List<Long> accountIds, @Param("accountId") Long accountId) {

		return personnelMapper.rankingbyaccountId(accountIds, accountId);
	}

	@Override
	public int updatePersonnel(Personnel personnel, InterviewAction interviewAction, String orgId,int step) {
		// TODO Auto-generated method stub
		String processingStatus = interviewAction.getProcessingStatus();
		InterviewAction upInterviewAction = new InterviewAction();
		upInterviewAction.setPersonnelId(interviewAction.getPersonnelId());
		upInterviewAction.setEndTime(new Date());
		//upInterviewAction.setProcessingName(interviewAction.getProcessingName());
		//upInterviewAction.setProcessingPerson(interviewAction.getProcessingPerson());
		upInterviewAction.setUpdateTime(new Date());
		upInterviewAction.setProcessingStatus(interviewAction.getProcessingStatus());
		upInterviewAction.setUpdateUser(interviewAction.getProcessingPerson());
		
		//添加消息
		if(personnel.getPersonnelStatus() != null && interviewAction.getFlowItemId()<Integer.valueOf(CommenConstant.ER_Flow_ONLINE_REGISTRATION)){
			messageService.addMessage(interviewAction.getProcessingPerson(),MessageUtil.getContent(1, personnel.getName(), Integer.valueOf(personnel.getPersonnelStatus())), "1",personnel.getId());
		}
		interviewAction.setProcessingStatus(null);
		//查询该增员人员面试阶段是否已存在
		Map<String , Object> searchMap = new HashMap<String,Object>();
		searchMap.put("personnel_id", interviewAction.getPersonnelId());
		searchMap.put("flow_action_id", interviewAction.getFlowActionId());
		List<InterviewAction> resultRecords = interviewActionMapper.selectByMap(searchMap);
		if(resultRecords.size()==0){
			interviewActionMapper.updateByPrimaryPersonnel(upInterviewAction);
			interviewActionMapper.insert(interviewAction);	
		}
		if(personnel.getPersonnelStatus() != null && interviewAction.getFlowItemId()>Integer.valueOf(CommenConstant.ER_Flow_first_time)){
			messageService.addMessage(personnel.getCreateUser(),MessageUtil.getContent(2, personnel.getName(), Integer.valueOf(personnel.getPersonnelStatus())), "2",personnel.getId());	
		}
		if(processingStatus != null && Integer.valueOf(interviewAction.getFlowItemId().toString())==Integer.valueOf(CommenConstant.ER_Flow_Selection)){
			personnel.setTrialResult(processingStatus);
		}
		return personnelMapper.updatePersonnelStatusByPersonnelId(personnel);
	}

	/**
	 * 根据id删除个人信息
	 */
	@Override
	public Response<String> deleteById(Long id) {
		Response<String> resp = new Response<String>(Response.SUCCESS, "删除指定个人信息成功");
		personnelMapper.deleteByPrimaryKey(id);
		return resp;
	}
	
	
	
	public Response<Integer> updateBypersonnel(Personnel personnel) {
		Response<Integer> resp = new Response<Integer>(Response.SUCCESS, "更新个人信息成功");
		resp.setData(personnelMapper.updateByPersonnelId(personnel));
		return resp;
	}

	@Override
	@Transactional
	public String checkResult(Personnel record) {
		Personnel personnel = personnelMapper.selectByPersonnelId(record.getId());
		personnel.setCheckResult(record.getCheckResult());
		personnel.setCheakOption(record.getCheakOption());
		int lg = personnelMapper.updateByPrimaryKeySelective(personnel);
     	if(1==lg){
			return record.getId().toString();
		}else{
			return null;
		}
	}
	
	
	
	public int  selectteamscalecountbyaccountId(List<Long> accountIds){
		
		
		return personnelMapper.selectteamscalecountbyaccountId(accountIds);
	}

	@Override
	public PageInfo<PersonnelManageVo> listByPage(Page<PersonnelManageVo> page, SearchPersonnelVo searchPersonnelVo) {
		PageInfo<PersonnelManageVo> pageInfo = new PageInfo<PersonnelManageVo>();
		pageInfo.setRecords(personnelMapper.PersonnelManageSearch(page,searchPersonnelVo) );
		int total = personnelMapper.PersonnelManageSearchCount(searchPersonnelVo);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(searchPersonnelVo.getPageSize(), total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
	
	@Override
	public PageInfo<PersonnelManageVo> listByOtherPage(Page<PersonnelManageVo> page, String keyWord, int pageNo,
			int pageSize) {
		
		PageInfo<PersonnelManageVo> pageInfo = new PageInfo<PersonnelManageVo>();
		pageInfo.setRecords(personnelMapper.PersonnelManageOtherSearch(page, keyWord, pageNo, pageSize));
		int total = personnelMapper.personnelManageOtherSearchCount(keyWord);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize, total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public PageInfo<PersonnelManageVo> listByOtherTwoPage(Page<PersonnelManageVo> page, String keyWord, int pageNo,
			int pageSize) {
		PageInfo<PersonnelManageVo> pageInfo = new PageInfo<PersonnelManageVo>();
		pageInfo.setRecords(personnelMapper.PersonnelManageOtherTwoSearch(page, keyWord, pageNo, pageSize));
		int total = personnelMapper.personnelManageOtherTwoSearchCount(keyWord);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize, total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
	 

}
