package com.fulan.application.controller.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.security.domain.AccountGroup;
import com.fulan.api.security.domain.AccountManageVo;
import com.fulan.api.security.domain.TagTarget;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.domain.UserGroupVo;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.GroupService;
import com.fulan.api.security.service.GroupTagService;
import com.fulan.api.security.vo.AccountGroupVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.TagService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.util.StringUtils;

@Controller
@RequestMapping("/manage/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
    private TagService tagService;
	
	@Autowired
	private GroupTagService groupTagService;
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 分页查询
	 * @param group
	 * @param pageNo
	 * @param pageSize
	 * @param pageSortFiled
	 * @param pageSortType
	 * @return
	 */
	@RequestMapping("/listGroup")
	public String listGroup(@RequestParam(value="groupName", required=false)String groupName,
	        @RequestParam(value="groupDesc", required=false) String groupDesc, Model model,
	        @RequestParam(value="pageNo", required=false,defaultValue = "1") int pageNo,
            @RequestParam(value="pageSize", required=false,defaultValue = "10") int pageSize, 
            @RequestParam(value="pageSortFiled", required=false, defaultValue = "id") String pageSortFiled,
            @RequestParam(value="pageSortType", required=false, defaultValue = "DESC") String pageSortType){
	    UserGroup group = new UserGroup();
	    if (!StringUtils.isEmptry(groupName)) {
	        group.setGroupName(groupName);
	    }
	    if (!StringUtils.isEmptry(groupDesc)) {
            group.setGroupDesc(groupDesc);
        }
	    
//        UserGroup ug = groupService.selectByAccountId(953555301552882345L);
//        Response<List<TagVo>> reponse= tagService.findByCatagory(1);
//        List<Tag> tagList = tagService.listByAccountId(953555301552882345L);
	    PageInfo<UserGroup> page = groupService.listGroup(group, pageNo, pageSize, pageSortFiled, pageSortType);
	    if (page != null) {
	        List<UserGroup> groupList = page.getRecords();
	        
	        List<UserGroupVo> groupVoList = new ArrayList<UserGroupVo>();
	        if (groupList != null && groupList.size() > 0) {
	            for (UserGroup group2 : groupList) {
	                UserGroupVo groupVo = new UserGroupVo();
	                groupVo.setUserNum(groupService.countUserNum(group2.getId()));
	                groupVo.setGroupName(group2.getGroupName());
	                groupVo.setGroupDesc(group2.getGroupDesc());
	                groupVo.setId(group2.getId());
	                groupVoList.add(groupVo);
	            }
	        }
	        model.addAttribute("groupList", groupVoList);
	        model.addAttribute("page", page);
	        model.addAttribute("groupName", groupName);
	        model.addAttribute("groupDesc", groupDesc);
	    }
	    return "/security/group/group-list";
	}
	
	/**
	 * 跳到新增页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editGroup", method=RequestMethod.GET)
	public String editGroup (@RequestParam(value="id", required=false) Long id,Model model) {
	    UserGroup ug = null;
	    if (id != null) {  //编辑
	        ug = groupService.selectById(id);
	        //为了回显选中的标签
	        List<TagTarget> ttList = groupTagService.listByHostIdAndType(id, 1); //1-- 表示用户组标签
	        if (ttList != null && ttList.size() > 0) {
	            String strTagId = "";
	            for (TagTarget tagTarget : ttList) {
	                strTagId = strTagId + tagTarget.getTagId();
	            }
	            model.addAttribute("strTagId", strTagId);
	        }
	        
	        //回显选中的用户
	        List<AccountGroupVo> agvList = groupService.selectAccountGroupByGroupId(id);
	        if (agvList != null && agvList.size() > 0) {
	            String userStr = "";
	            String userName = "";
	            for (int i = 0; i < agvList.size(); i ++) {
	                userName += agvList.get(i).getAccountName();
	                userStr += agvList.get(i).getAccountId();
	                if (i != agvList.size() - 1) {
	                    userName += ",";
	                    userStr += ",";
                    }
	            }
	            model.addAttribute("userName", userName);
	            model.addAttribute("userStr", userStr);
	        }
	        model.addAttribute("group", ug);
	    } 
	    //用户标签组
	    Response<List<TagVo>> reponse= tagService.findByCatagory(1); //1表示用户组标签
	    model.addAttribute("TagVo", reponse.getData());
	    return "/security/group/group-edit";
	}
	
	/**
	 * 保存跟新
	 * @param group
	 * @param userStr
	 * @param tagIds
	 * @return
	 */
	@RequestMapping(value = "/saveGroup", method=RequestMethod.POST)
	@ResponseBody
    public int saveGroup (UserGroup group,
                @RequestParam(value="tagIds", required=false) String tagIds,
                @RequestParam(value="userStr", required=false) String userStr,
                @RequestParam(value="newTag", required=false) String newTag) {
	    if (group.getId() != null) {
	        group.setModifyUser(SessionContextUtils.getCurrentUserId());
	    } else {
	        group.setCreateUser(SessionContextUtils.getCurrentUserId());
	    }
	    int i = groupService.saveGroup(group, tagIds, userStr, newTag);
	    return i; 
    }
	
	/**
	 * 删除
	 * @param groupIds
	 * @return
	 */
	@RequestMapping(value = "/deleteGroup",method=RequestMethod.POST)
    @ResponseBody
	public String deleteGroup(@RequestParam(value="groupIds", required=false) String groupIds) {
	    String returnStr = groupService.deleteGroups(groupIds);
	   return returnStr;
    }
	
	/**
	 * 查询用户
	 * @param accountName
	 * @param telephone
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/listUsers")
    public String listByPage(
            String accountName,
            String telephone,
            Model model,String pageNo,String pageSize){
        if(""==pageNo || null ==pageNo){
            pageNo="1";
        }
        if(""==pageSize || null ==pageSize){
            pageSize="10";
        }
        if(""==accountName || null ==accountName){
            accountName="";
        }
        if(""==telephone || null ==telephone){
            telephone="";
        }
        PageInfo<AccountManageVo>  page = accountService.listByPagesNotInUserGroup(accountName, telephone, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        model.addAttribute("page", page);
        model.addAttribute("accountName", accountName);
        model.addAttribute("telephone", telephone);
        return "/security/group/account";
        
    }
	
}