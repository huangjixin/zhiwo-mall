/**
 * 
 */
package com.fulan.application.oa.service.impl;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.util.file.SFTPUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.alibaba.fastjson.JSONObject;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.oa.constant.ApplyAction;
import com.fulan.application.oa.constant.ApplyConstant;
import com.fulan.application.oa.constant.ApplyTypeEnum;
import com.fulan.application.oa.domain.FwdOaApplyForm;
import com.fulan.application.oa.domain.FwdOaFormAttachment;
import com.fulan.application.oa.mapper.FwdOaApplyFormMapper;
import com.fulan.application.oa.service.IApplyFormService;
import com.fulan.application.oa.service.IAttachmentService;
import com.fulan.application.oa.service.WorkFlowService;
import com.fulan.application.oa.vo.OAApplyFormVo;
import com.fulan.application.oa.vo.OAApplyFormVoParameter;
import sun.misc.BASE64Decoder;

/**
 * @author 黄记新 Tony
 *
 */
@Service
@Transactional
public class ApplyFormServiceImpl implements IApplyFormService {

	private static String BASE_MESSAGE = "申请表单业务类ApplyFormServiceImpl";
	private static Logger logger = Logger.getLogger(ApplyFormServiceImpl.class);

	@Autowired
	private FwdOaApplyFormMapper applyFormMapper;

	@Autowired
	private IAttachmentService attachmentService;

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private DictionaryService dictionaryService;

	@Value("${ftpuploadusername}")
	public  String ftpuploadusername;

	@Value("${ftpuploadpassword}")
	public  String ftpuploadpassword;

	@Value("${ftpuploadhost}")
	public  String ftpuploadhost;

	@Value("${ftpuploadport}")
	public  Integer ftpuploadport;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IApplyFormService#save(com.fulan.application
	 * .oa.domain.FwdOaApplyForm)
	 */
	@Override
	public int save(FwdOaApplyForm applyForm) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "保存申请表单开始，传入的参数是：" + applyForm.toString());
		}
		// 默认保存的status状态为0；
		if (applyForm.getStatus() == null) {
			applyForm.setStatus(ApplyConstant.APPLY_STATUS_PROGRESS);
		}
		int result = applyFormMapper.insertSelective(applyForm);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "保存申请表单结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IApplyFormService#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "删除申请表单开始，传入的参数id是：" + id);
		}
		int result = applyFormMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "删除申请表单结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IApplyFormService#update(com.fulan.
	 * application.oa.domain.FwdOaApplyForm)
	 */
	@Override
	public int update(FwdOaApplyForm applyForm) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "更新申请表单开始，传入的参数是：" + applyForm.toString());
		}
		int result = applyFormMapper.updateByPrimaryKey(applyForm);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "更新申请表单结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IApplyFormService#selectAll()
	 */
	@Override
	public List<FwdOaApplyForm> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询所有申请表单开始");
		}
		List<FwdOaApplyForm> applyFormes = applyFormMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = applyFormes.size() + "";
			logger.info(BASE_MESSAGE + "保存申请表单，结果的条目数是：" + msg);
		}
		return applyFormes;
	}

	@Override
	public List<OAApplyFormVo> selectOAApplyFormVo(OAApplyFormVoParameter parameter) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询分页查询申请表单（主表），地址（子表），银行卡（子表），手机号（子表）开始");
		}
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "参数是："+parameter.toString());
		}
		List<OAApplyFormVo> applyFormes = applyFormMapper.selectOAApplyFormVo(parameter);
		if (logger.isInfoEnabled()) {
			String msg = applyFormes.size() + "";
			logger.info(BASE_MESSAGE + "保存分页查询申请表单（主表），地址（子表），银行卡（子表），手机号（子表），结果的条目数是：" + msg);
		}
		return applyFormes;
	}

	@Override
	public FwdOaApplyForm selectById(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询根据id查询申请表单开始");
		}
		FwdOaApplyForm oaApplyForm = applyFormMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "根据id查询申请表单数据，结果是：" + JSONObject.toJSONString(oaApplyForm));
		}
		return oaApplyForm;
	}

	@Override
	public int saveForm(String strDirPath, MultipartFile file, FwdOaApplyForm applyForm) throws Exception {
		String newFileName = null;
		File targetFile = null;
		if (file != null && !file.isEmpty()) {
			String oldFileName = file.getOriginalFilename();
			// 获取文件后缀
			String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
			// 获取文件提交路径(服务器)
			// 把上传的文件写入到upload/images/{agentCode}/文件名 (先判断一下文件是否存在，不存在则先创建)
			String path = strDirPath + "upload" + File.separator + "images" + File.separator + applyForm.getAgentCode();
			File filePath = new File(path);
			if (!filePath.exists()) {
				boolean establish = filePath.mkdirs();
				if (!establish) {
					throw new RuntimeException("文件创建失败");
				}
			}

			// 文件重命名
			// 时间(毫秒数)+随机数+suffix
			// 1970-1-1~今天 System.currentTimeMillis();
			newFileName = System.currentTimeMillis() + new Random().nextInt(1000000) + suffix;
			// 上传文件 java.io.File
			targetFile = new File(filePath, newFileName);
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException("文件上传失败");
			}

		}

		// 如果绑定没有错误的话把applyForm插进去数据库。
		int num = this.save(applyForm);
		if (num > 0 && file != null && !file.isEmpty()) {
			// 新建一个附件对象，把相应信息例如path物理路径URL写进去外键等，保存进去数据库，
			FwdOaFormAttachment oaFormAttachment = new FwdOaFormAttachment();
			if (newFileName == null) {
				newFileName = "unknown";
			}

			String url = "upload/images/" + applyForm.getAgentCode() + "/" + newFileName;
			if (targetFile != null)
				oaFormAttachment.setPath(targetFile.toString());
			oaFormAttachment.setUrl(url);
			oaFormAttachment.setFormId(applyForm.getId());
			int count = attachmentService.save(oaFormAttachment);
			if (count < 1) {
				throw new RuntimeException("添加附件信息数据失败");
			}
		}

		// 启动申请流程。
		ApplyTypeEnum applyType = ApplyTypeEnum.getByCode(applyForm.getType());
		Map<String, Object> params = new HashMap<String, Object>();
		String formId = applyForm.getId().toString();
		String agentCode = applyForm.getAgentCode();
		String branchCode = ApplyConstant.BRANCH_CODE_SHANG_HAI;
		//this.workFlowService.startProcessAndSubmitForm(applyType, formId, agentCode, branchCode, params);
		return num;
	}

	@Override
	public int saveMultipleForm(String strDirPath, CommonsMultipartFile[] files, FwdOaApplyForm applyForm)
			throws Exception {
		String newFileName = null;
		File targetFile = null;
		
		// 如果绑定没有错误的话把applyForm插进去数据库。
		int num = this.save(applyForm);
				
		if (files != null && files.length > 0) {
			for (CommonsMultipartFile file : files) {
				String oldFileName = file.getOriginalFilename();
				// 获取文件后缀
				String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
				// 获取文件提交路径(服务器)
				// 把上传的文件写入到upload/images/{agentCode}/文件名 (先判断一下文件是否存在，不存在则先创建)
				String path = strDirPath + "upload" + File.separator + "images" + File.separator + applyForm.getAgentCode();
				File filePath = new File(path);
				if (!filePath.exists()) {
					boolean establish = filePath.mkdirs();
					if (!establish) {
						throw new RuntimeException("文件创建失败");
					}
				}

				// 文件重命名
				// 时间(毫秒数)+随机数+suffix
				// 1970-1-1~今天 System.currentTimeMillis();
				newFileName = System.currentTimeMillis() + new Random().nextInt(1000000) + suffix;
				// 上传文件 java.io.File
				targetFile = new File(filePath, newFileName);
				try {
					file.transferTo(targetFile);
				} catch (IllegalStateException | IOException e) {
					throw new RuntimeException("文件上传失败");
				}

				FwdOaFormAttachment oaFormAttachment = new FwdOaFormAttachment();

				String url = "upload/images/" + applyForm.getAgentCode() + "/" + newFileName;
				if (targetFile != null)
					oaFormAttachment.setPath(targetFile.toString());
				oaFormAttachment.setUrl(url);
				oaFormAttachment.setFormId(applyForm.getId());
				int count = attachmentService.save(oaFormAttachment);
			}
		}

		// 启动申请流程。
		ApplyTypeEnum applyType = ApplyTypeEnum.getByCode(applyForm.getType());
		Map<String, Object> params = new HashMap<String, Object>();
		String formId = applyForm.getId().toString();
		String agentCode = applyForm.getAgentCode();
		String branchCode = ApplyConstant.BRANCH_CODE_SHANG_HAI;
		this.workFlowService.startProcessAndSubmitForm(applyType, formId, agentCode, branchCode, params);
		return num;
	}

	/**
	 * 获取文件的后缀
	 * @return
	 */
	public static String getImageSuffix(String base64){
		if (base64.contains("data:image/png;base64")) {
			return "png";
		} else if (base64.contains("data:image/jpg;base64")) {
			return "jpg";
		} else if (base64.contains("data:image/gif;base64")) {
			return "gif";
		} else if (base64.contains("data:image/bmp;base64")) {
			return "bmp";
		} else if (base64.contains("data:image/jpeg;base64")) {
			return "jpeg";
		} else {
			return "png";
		}
	}

	/**
	 * base64字符串转文件
	 * @param base64
	 * @return
	 */
	public static File base64ToFile(String fileName, String base64) {
		File file = null;
		//去掉base64前面的data:image/png;base64,
		base64 = base64.replaceAll("data:[^b]+base64,","");
		try {
			file = new File(fileName);
			byte[] buffer = new BASE64Decoder().decodeBuffer(base64);
			FileOutputStream out = new FileOutputStream(file);
			out.write(buffer);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	/**
	 * 创建 保存的目标文件
	 * @param fileSuffix
	 * @return
	 */
	private File createSaveTarget(String fileSuffix, String relateFolderPath,String fileName){
		File folderPath = new File(relateFolderPath);
		if (!folderPath.exists()) {
			folderPath.mkdirs();
		}

		File tmp = new File(relateFolderPath, fileName);
		return tmp;
	}

	public File saveFile(File file,String suffix,String relateFolderPath,String fileName) throws IOException {
		if (file == null || !file.exists()) {
			throw new NullPointerException("保存的文件不存在");
		}

		File saveTarget = this.createSaveTarget(suffix, relateFolderPath,fileName);
		FileUtils.copyFile(file, saveTarget);

		return saveTarget;
	}

	@Override
	public int saveMultipleFormBase64(String strDirPath, String[] files, FwdOaApplyForm applyForm) throws Exception {
		String newFileName = null;
//		File targetFile = null;

		// 如果绑定没有错误的话把applyForm插进去数据库。
		int num = this.save(applyForm);

		if (files != null && files.length > 0) {
			Dictionary dictionary= dictionaryService.findByCode(CommenConstant.IMAGEDIR);

			SFTPUtil sftp = new SFTPUtil(ftpuploadusername,ftpuploadpassword,ftpuploadhost, ftpuploadport);
			sftp.login();
			for (String file : files) {
				String suffix = getImageSuffix(file);
				File f = base64ToFile("temp."+suffix,file);

				// 获取文件提交路径(服务器)
				// 把上传的文件写入到upload/images/{agentCode}/文件名 (先判断一下文件是否存在，不存在则先创建)
				String path = strDirPath + "upload" + File.separator + "images" + File.separator + applyForm.getAgentCode();
				File filePath = new File(path);
				if (!filePath.exists()) {
					boolean establish = filePath.mkdirs();
					if (!establish) {
						throw new RuntimeException("文件创建失败");
					}
				}

				// 文件重命名
				// 时间(毫秒数)+随机数+suffix
				// 1970-1-1~今天 System.currentTimeMillis();
				newFileName = System.currentTimeMillis() + new Random().nextInt(1000000) + "."+suffix;
				InputStream inputStream=new FileInputStream(f);//如果文件不存在会自动创建
				
				String picServerPath = dictionary.getValue()+
						"upload" + File.separator + "images" + File.separator + applyForm.getAgentCode();
				String uploadDir = dictionary.getValue()  + picServerPath;
				sftp.upload(uploadDir, newFileName, inputStream);

//				targetFile = saveFile(f,suffix,path,newFileName);

				FwdOaFormAttachment oaFormAttachment = new FwdOaFormAttachment();

				String url =CommenConstant.mediaPlay+"/upload/images/" + applyForm.getAgentCode() + "/" + newFileName;
//				if (targetFile != null)
//					oaFormAttachment.setPath(targetFile.toString());
				oaFormAttachment.setUrl(url);
				oaFormAttachment.setFormId(applyForm.getId());
				int count = attachmentService.save(oaFormAttachment);
			}

			sftp.logout();
		}

		// 启动申请流程。
//		ApplyTypeEnum applyType = ApplyTypeEnum.getByCode(applyForm.getType());
//		Map<String, Object> params = new HashMap<String, Object>();
//		String formId = applyForm.getId().toString();
//		String agentCode = applyForm.getAgentCode();
//		String branchCode = ApplyConstant.BRANCH_CODE_SHANG_HAI;
//		this.workFlowService.startProcessAndSubmitForm(applyType, formId, agentCode, branchCode, params);
		return num;
	}

	@Override
	public FwdOaApplyForm findById(Integer formId) {
		FwdOaApplyForm from = this.applyFormMapper.selectByPrimaryKey(formId);
		return from;
	}

	@Override
	public List<OAApplyFormVo> findPendingApproval(String agentCode,String[] types, Integer pageSize, Integer curPage) {

		List<Integer> formList = workFlowService.findPendingApprovalByUserId(agentCode, types, curPage, pageSize);
		if (formList == null || formList.size() == 0)
			return Collections.EMPTY_LIST;

		int size = formList.size();
		Integer[] ids = new Integer[size];
		for (int i = 0; i < size; i++) {
			ids[i] = formList.get(i);
		}

		OAApplyFormVoParameter param = new OAApplyFormVoParameter();
		param.setIds(ids);
		List<OAApplyFormVo> applyFormVoList = applyFormMapper.selectOAApplyFormVo(param);
		return applyFormVoList;
	}

	@Override
	public List<OAApplyFormVo> findProcessedApproval(String agentCode, String[] types, Integer pageSize, Integer curPage) {

		List<Integer> formList = workFlowService.findProcessedApprovalByUserId(agentCode, types, curPage, pageSize);
		if (formList == null || formList.size() == 0)
			return Collections.EMPTY_LIST;

		int size = formList.size();
		Integer[] ids = new Integer[size];
		for (int i = 0; i < size; i++) {
			ids[i] = formList.get(i);
		}

		OAApplyFormVoParameter param = new OAApplyFormVoParameter();
		param.setIds(ids);
		List<OAApplyFormVo> applyFormVoList = applyFormMapper.selectOAApplyFormVo(param);
		return applyFormVoList;
	}

	@Override
	public void handleApproval(Integer formId, ApplyAction action, String userId, String comment, String nextAssignee) {

		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(nextAssignee))
			params.put(ApplyConstant.PARAM_NEXT_ASSIGNEE, nextAssignee);

		boolean isApprovalFinished = workFlowService.handleTask(formId.toString(), action, userId, comment, params);

		if (isApprovalFinished) {

			Integer status = (ApplyAction.Agree == action) ? ApplyConstant.APPLY_STATUS_PASS
					: ApplyConstant.APPLY_STATUS_REJECT;

			FwdOaApplyForm form = this.applyFormMapper.selectByPrimaryKey(formId);
			form.setStatus(status);
			form.setEndDatetime(new Date());
			this.update(form);
		}

	}

	@Override
	public boolean isApplyFormActive(Integer formId) {
		FwdOaApplyForm form = this.selectById(formId);
		if (form != null && ApplyConstant.APPLY_STATUS_PROGRESS == form.getStatus())
			return true;
		return false;
	}

	@Override
	public List<Map<String, String>> getTaskInfosByName(String name) throws Exception {
		URL url = ApplyFormServiceImpl.class.getClassLoader().getResource("");
		String classPath = url.getPath();
		File file = new File(classPath+File.separator+name);
		if(!file.exists()) {
			throw new RuntimeException("BPMN模型文件不存在！");
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 读取XML文件
		String s = classPath+File.separator+name;
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 传入文件名可以是相对路径也可以是绝对路径
		Document document = db.parse(s);
		// 获取所有userTask节点的集合
		NodeList userTaskkList = document.getElementsByTagName("userTask");
		for (int i = 0; i < userTaskkList.getLength(); i++) {
			Map<String, String> hashMap = new HashMap<String, String>();
			// 未知节点属性的个数和属性名时:
			// 通过 item(i)方法 获取一个userTask节点，nodelist的索引值从0开始
			Node userTask = userTaskkList.item(i);
			// 获取userTask节点的所有属性集合
			NamedNodeMap attrs = userTask.getAttributes();
			// 遍历userTask的属性
			for (int j = 0; j < attrs.getLength(); j++) {
				// 通过item(index)方法获取userTask节点的某一个属性
				Node attr = attrs.item(j);
				// 获取属性名--属性值
				hashMap.put(attr.getNodeName(), attr.getNodeValue());
			}
			list.add(hashMap);
		}
		return list;
	}

	@Override
	public void updateTaskInfosByName(String name, List<Map<String, String>> nodeMap) throws Exception {
		URL url = ApplyFormServiceImpl.class.getClassLoader().getResource("");
		String classPath = url.getPath();
		File file = new File(classPath+File.separator+name);
		if(!file.exists()) {
			throw new RuntimeException("文件不存在！");
		}
		// 读取XML文件
		String s = classPath+File.separator+name;
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 传入文件名可以是相对路径也可以是绝对路径
		Document document = db.parse(s);
		// 获取所有userTask节点的集合
		NodeList userTaskkList = document.getElementsByTagName("userTask");
		
		// 数据是否更改标志变量；
		boolean dataChanged = false;
		
		for (int i = 0; i < userTaskkList.getLength(); i++) {
			// 未知节点属性的个数和属性名时:
			// 通过 item(i)方法 获取一个userTask节点，nodelist的索引值从0开始
			Node userTask = userTaskkList.item(i);
			// 获取userTask节点的所有属性集合
			NamedNodeMap attrs = userTask.getAttributes();
			// 遍历userTask的属性
			for (int k = 0; k < nodeMap.size(); k++) {
				String idNodeValue =attrs.getNamedItem("id").getNodeValue();
				String idMapValue = nodeMap.get(k).get("id");
				if (idNodeValue.equals(idMapValue)) {
					for (int j = 0; j < attrs.getLength(); j++) {
						Node attr = attrs.item(j);
						//循环Map里面的键值。
						for (Entry<String, String> key : nodeMap.get(k).entrySet()) {
							String nodeName = attr.getNodeName();
							String keyString = key.getKey();
							if (keyString.equals(nodeName)) {
								String attValue = attr.getNodeValue();
								String keyValue = key.getValue();
								if(!attValue.equals(keyValue)) {
									attr.setNodeValue(keyValue);
									dataChanged = true;
								}
								
//								break;
							}
						}
					}
					
//					break;
				}
			}
		}
		
		if(dataChanged == true) {
			document.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domSource = new DOMSource(document);
			StreamResult result = new StreamResult(new File(s));
			transformer.transform(domSource, result);
		}
	}

	
}
