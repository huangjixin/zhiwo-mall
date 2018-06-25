package com.fulan.application.controller.elearning;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.service.MaterialService;
import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.material.vo.MaterialVoCMS;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.service.GroupService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.TagService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * @Author: chenzuyu
 * @Date: 2018/1/25 20:16
 */
@Controller
public class MaterialController {
    
	@Autowired
    private MaterialService materialService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/manage/publicMaterialListByPage")
	public String publicMaterialListByPage(Model model,ServletRequest request,
			@RequestParam(value="keyWord",required=false) String keyWord,
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="id",required=false) String id,
            @RequestParam(value="groupId",required=false) String groupId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,
            @RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize
																){
		pubType="1";
		
		String loginUser="";
		UserGroup ug = groupService.selectByAccountId(SessionContextUtils.getLoginUserId());
        if (ug != null) {
            //  待完善  。暂时无法区分管理员和讲师角色
        }
		//String loginUser=SessionContextUtils.getLoginUserId().toString();
		PageInfo<Material> pageMaterial=materialService.materialListByPage(keyWord, type, submitter, pubType, id, groupId, tagId, uploadTimeBegin, uploadTimeEnd,loginUser, pageNo, pageSize);
		PageInfo<MaterialVoCMS> mvcPage = listData(pageMaterial);
		model.addAttribute("page",mvcPage);
		model.addAttribute("search_keyWord",keyWord);
		model.addAttribute("search_type",type);
		model.addAttribute("search_submitter",submitter);//管理员 登录 不传值 null 讲师登录  传讲师的group_id
		model.addAttribute("search_pubType",pubType);//管理员公共库/讲师私人库 1，讲师公共库2
		model.addAttribute("search_id",id);
//		model.addAttribute("search_groupId",groupId);
//		model.addAttribute("search_tagId",tagId);
		model.addAttribute("search_uploadTimeBegin",uploadTimeBegin);
		model.addAttribute("search_uploadTimeEnd",uploadTimeEnd);
		return "/elearning/material/public_material";
	}
	
	
	@RequestMapping("/manage/viewMaterial")
	public String viewMaterial(Model model,ServletRequest request){
		String id=request.getParameter("id");
		MaterialCourseVo materialVo = materialService.viewMaterial(id);
		model.addAttribute("material",materialVo);
		return "/elearning/material/material_to_view";
	}
	

	@RequestMapping("/manage/privateMaterialListByPage")
	public String privateMaterialListByPage(Model model,ServletRequest request,
			@RequestParam(value="keyWord",required=false) String keyWord,  //资料名称
            @RequestParam(value="type",required=false) String type,         //资料类型
            @RequestParam(value="pubType",required=false) String pubType,//讲师私人库 2, 讲师公共库1
            @RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,
            @RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		
	    if (pubType == null || "".equals(pubType)) {
	        pubType = "1";
	    }
	  //当前登录用户
		Long loginUser = SessionContextUtils.getLoginUserId();  
		
		//当前用户所属用户组
		UserGroup ug = groupService.selectByAccountId(SessionContextUtils.getLoginUserId());
		Long groupId = null;
        if (ug != null) {
            groupId = ug.getId(); //管理员不关联用户组。  如果是管理员 只能在公共资料库中查看所有资料;如果是讲师，在私人资料库中查看自己新建的资料，在公共资料库中查看分享的资料
            
        }
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("keyWord", keyWord);
        paramMap.put("loginUser", loginUser);
        paramMap.put("groupId", groupId);
        paramMap.put("type", type);
        paramMap.put("pubType", pubType);
        paramMap.put("uploadTimeBegin", uploadTimeBegin);
        paramMap.put("uploadTimeEnd", uploadTimeEnd);
        PageInfo<MaterialVoCMS> pageMaterial = materialService.listMaterialByPage(paramMap,pageNo, pageSize);
		//PageInfo<Material> pageMaterial=materialService.materialListByPage(keyWord, type, submitter, pubType, id, groupId, tagId, uploadTimeBegin, uploadTimeEnd,loginUser, pageNo, pageSize);
		//PageInfo<MaterialVoCMS> mvcPage = listData(pageMaterial);
		// 标签 
		model.addAttribute("page",pageMaterial);
		model.addAttribute("search_keyWord",keyWord);
		model.addAttribute("search_type",type);
		model.addAttribute("search_pubType",pubType);
		model.addAttribute("search_uploadTimeBegin",uploadTimeBegin);
		model.addAttribute("search_uploadTimeEnd",uploadTimeEnd);
		return "/elearning/material/private_material";
	}
	
	
	@RequestMapping("/manage/deleteMaterials")
	@ResponseBody
	public Response<List<String>> deleteMaterials(Model model,@RequestParam(value="materialIds")String[] materialIds){
		//String id=request.getParameter("id");
		//MaterialCourseVo materialVo = materialService.viewMaterial(id);
		List<String> list=materialService.deleteMaterials(materialIds);
		Response<List<String>> res = new Response<List<String>>();
		res.setData(list);
		return res;
	}
	
	@RequestMapping("/manage/insertMaterialsJsp")
	public String insertMaterialsJsp(Model model){
	    // 标签 
//        List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
//        model.addAttribute("tagList", tagList);
		return "/elearning/material/addEdit_material";
	}
	
	
	@RequestMapping("/manage/updateMaterialsJsp")
	public String updateMaterialsJsp(Model model,ServletRequest request){
		String id = request.getParameter("id");
		Material material = materialService.getMaterialById(id);
		model.addAttribute("material", material);
		
		//附件
		List<Attachment> attachments = attachmentService.findbyparms(CommenConstant.EL_MATERIAL_ATTACHMENT, material.getId());  //32表示资料附件
		if (attachments != null && attachments.size() > 0) {
		    model.addAttribute("attachment",attachments.get(0));
		}
		// 标签 
		List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
		model.addAttribute("tagList", tagList);
		return "/elearning/material/addEdit_material";
	}
	
	/**
	 * 新增修改方法
	 * @param model
	 * @param material
	 * @param fileId  --附件id
	 * @param createType --从基础课程进来新增时标志位 1   
	 * @return
	 */
	@RequestMapping("/manage/insertAndUpdateMaterial")
	@ResponseBody
    public String insertAndUpdateMaterial(Model model,Material material,
            String fileId, String createType){
		Long id= material.getId();
		//获取当前登录人
		Date date = new Date();
		Long userId = SessionContextUtils.getLoginUserId();
		//用于关联的用户组
		UserGroup ug = groupService.selectByAccountId(userId);
		if (ug != null) {
		    material.setGroupId(ug.getId());
		}
	    if(null != id){
	        //保存修改人
	        material.setModifyUser(userId);
	        //保存修改时间
	        material.setGmtModified(date);
	    } else {   
	        if (fileId != null && !"".equals(fileId)) {    //新增时 附件不为空.
	            //保存创建人
	            material.setCreateUser(userId);
	            //保存创建时间
	            material.setGmtCreate(date);
	        } else {
	            return "false";
	        }
	    }
	    String result = materialService.insertAndUpdateMaterial(material,fileId);
	    if (createType != null && !"".equals(createType) && "1".equals(createType)) {
	        if (result != null && !"".equals(result)) {
	            Material material2 = materialService.getMaterialById(result);
	            JSONObject json = JSONObject.fromObject(material2);
	            return json.toString();
	        }
	    }
	    return result;
    }
	
	@RequestMapping("/manage/shareMaterials")
	public String shareMaterials(Model model,
			@RequestParam(value="materialIds")String[] materialIds,
			@RequestParam(value="materialNames")String[] materialNames){
		List <Material> materials=new ArrayList<>();
		
		//用户组
	    List <UserGroup> groupList = groupService.listAllGroups();
		model.addAttribute("groupList", groupList);
		
		if (materialIds.length > 0) {
		    String materialIdStr = "";
		    for(int i = 0;i < materialIds.length; i++){
		        Material material=new Material();
		        material.setId(Long.parseLong(materialIds[i]));
		        material.setName(materialNames[i]);
		        materials.add(material);
		        
		        materialIdStr += materialIds[i];
		        if (i != materialIds.length -1) {
		            materialIdStr += ",";
		        }
		    }
		    List<MaterialShare> msList = materialService.listMaterialShareByMaterialId(materialIdStr);
            if (msList != null && msList.size() > 0) {
                String groupIds = "";
                for (int j = 0; j < msList.size(); j++) {
                    groupIds += msList.get(j).getGroupId();
                    if (j != msList.size() - 1) {
                        groupIds += ",";
                    }
                }
                model.addAttribute("groupIds", groupIds);
            }
		}
		
		model.addAttribute("materials", materials);
		return "/elearning/material/share_material";
	}
	
	@RequestMapping(value="/manage/doShareMaterials", method = RequestMethod.POST)
	public @ResponseBody Response<Boolean> doShareMaterials(Model model,
				@RequestParam(value="groupIds[]", required=false)String[] groupIds, 
				@RequestParam(value="materialIds[]", required=false)String[] materialIds, String noShare){
		
		Response<Boolean> res=materialService.share(groupIds, materialIds,noShare);
		return res;
	}
	
	@RequestMapping("/manage/publicMaterialListByCourse")
	public String publicMaterialListByCourse(Model model,ServletRequest request,
			@RequestParam(value="keyWord",required=false) String keyWord,
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,
            @RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize
																){
	    if (pubType == null || "".equals(pubType)) {
            pubType = "1";
        }
      //当前登录用户
        Long loginUser = SessionContextUtils.getLoginUserId();  
        
        //当前用户所属用户组
        UserGroup ug = groupService.selectByAccountId(SessionContextUtils.getLoginUserId());
        Long groupId = null;
        if (ug != null) {
            groupId = ug.getId(); //管理员不关联用户组。  如果是管理员 只能在公共资料库中查看所有资料;如果是讲师，在私人资料库中查看自己新建的资料，在公共资料库中查看分享的资料
            
        }
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("keyWord", keyWord);
        paramMap.put("loginUser", loginUser);
        paramMap.put("groupId", groupId);
        paramMap.put("type", type);
        paramMap.put("pubType", pubType);
        paramMap.put("uploadTimeBegin", uploadTimeBegin);
        paramMap.put("uploadTimeEnd", uploadTimeEnd);
        PageInfo<MaterialVoCMS> pageMaterial = materialService.listMaterialByPage(paramMap,pageNo, pageSize);
		//PageInfo<Material> pageMaterial=materialService.materialListByPage(keyWord, type, submitter, pubType, id, groupId, tagId, uploadTimeBegin, uploadTimeEnd,loginUser, pageNo, pageSize);
		//PageInfo<MaterialVoCMS> mvcPage = listData(pageMaterial);
        model.addAttribute("page",pageMaterial);
		model.addAttribute("search_keyWord",keyWord);
		model.addAttribute("search_type",type);
		model.addAttribute("search_pubType",pubType);//管理员公共库/讲师私人库 1，讲师公共库2
		model.addAttribute("search_uploadTimeBegin",uploadTimeBegin);
		model.addAttribute("search_uploadTimeEnd",uploadTimeEnd);
		return "/elearning/material/public_material_withCourse";
	}
	
	private PageInfo<MaterialVoCMS> listData(PageInfo<Material> pageMaterial) {
	    List<Material> maList = pageMaterial.getRecords();
	    List<MaterialVoCMS> mvcList = new ArrayList<MaterialVoCMS>();
        if (maList != null && maList.size() > 0) {
            for (Material material : maList) {
                MaterialVoCMS mvcCms = new MaterialVoCMS();
                mvcCms.setId(material.getId());
                mvcCms.setName(material.getName());
                mvcCms.setType(material.getType());
                mvcCms.setDescription(material.getDescription());
                mvcCms.setGroupId(material.getGroupId());
                mvcCms.setTagId(material.getTagId());
                mvcCms.setIsShare(material.getIsShare()); 
                mvcCms.setIsRelatePlan(material.getIsRelatePlan());
                mvcCms.setCreateUser(material.getCreateUser());
                mvcCms.setGmtCreate(material.getGmtCreate());
                mvcCms.setGmtModified(mvcCms.getGmtModified());
                mvcCms.setModifyUser(material.getModifyUser());
                Tag tag = tagService.findById(material.getTagId());
                if (tag != null) {
                    mvcCms.setTagName(tag.getTagName());
                }
                UserGroup ug = groupService.selectById(material.getGroupId());
                if (ug != null) {
                    mvcCms.setGroupName(ug.getGroupName());
                }
                mvcList.add(mvcCms);
            }
            PageInfo<MaterialVoCMS>  mvcPage = new PageInfo<MaterialVoCMS>();
            mvcPage.setPageNo(pageMaterial.getPageNo());
            mvcPage.setPageRecords(pageMaterial.getPageRecords());
            mvcPage.setPages(pageMaterial.getPages());
            mvcPage.setPageSize(pageMaterial.getPageSize());
            mvcPage.setPageSortFiled(pageMaterial.getPageSortFiled());
            mvcPage.setPageSortType(pageMaterial.getPageSortType());
            mvcPage.setPageTotal(pageMaterial.getPageTotal());
            mvcPage.setRecords(mvcList);
            
            return mvcPage;
        }
        return null;
	}
	
	/**
     * 新增修改方法
     * @param model
     * @param material
     * @param fileId  --附件id
     * @param createType --从基础课程进来新增时标志位 1   
     * @return
     */
    @RequestMapping("/manage/insertAndUpdateMaterial2")
    @ResponseBody
    public Material insertAndUpdateMaterial2(Model model,Material material,
            String fileId, String createType){
        Long id= material.getId();
        //获取当前登录人
        Date date = new Date();
        Long userId = SessionContextUtils.getLoginUserId();
        //用于关联的用户组
        UserGroup ug = groupService.selectByAccountId(userId);
        if (ug != null) {
            material.setGroupId(ug.getId());
        }
        if(null != id){
            //保存修改人
            material.setModifyUser(userId);
            //保存修改时间
            material.setGmtModified(date);
        } else {   
            if (fileId != null && !"".equals(fileId)) {    //新增时 附件不为空.
                //保存创建人
                material.setCreateUser(userId);
                //保存创建时间
                material.setGmtCreate(date);
            } else {
                return null;
            }
        }
        String result = materialService.insertAndUpdateMaterial(material,fileId);
        if (createType != null && !"".equals(createType) && "1".equals(createType)) {
            if (result != null && !"".equals(result)) {
                Material material2 = materialService.getMaterialById(result);
                return material2;
            }
        }
        return null;
    }
}
