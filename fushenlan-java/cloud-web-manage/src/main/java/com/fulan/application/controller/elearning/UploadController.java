package com.fulan.application.controller.elearning;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.util.domain.Response;


/**
 * @Author: zhouyun
 * @Date: 2018/1/30 
 */
@Controller
public class UploadController {
	
    @Autowired
    private DictionaryService dictionaryService;
	/**
     * 上传附件
     * @param request
     * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
     */
    @RequestMapping(value = "/manage/commonUploadFile",method = RequestMethod.POST)
    @ResponseBody
    public Attachment getUploadList (HttpServletRequest request) throws ClientProtocolException, IOException {
        
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        //获取上传文件   name值 必须为"fileName"：
        MultipartFile file = mRequest.getFile("fileName");
        
        //String category = request.getParameter("category");
        //获取其他参数
        Map<String, String[]> map = new HashMap(request.getParameterMap());
        
        String resultJsonStr = httpClientUpload(file,map);
        if (resultJsonStr != null ) {
            JSONObject jsonObject=JSONObject.fromObject(resultJsonStr);
            List<Attachment> attachments = (List<Attachment>)JSONArray.toList((JSONArray) jsonObject.get("data"), new Attachment(),new JsonConfig());
            if (attachments != null && attachments.size() > 0) {
                return attachments.get(0);
            }
        }
        return null;
        
    }
    
    public String httpClientUpload( MultipartFile file, Map<String, String[]> params)
            throws ClientProtocolException, IOException {
        
        HttpClient httpclient = new DefaultHttpClient();
        // 请求处理页面
        
        //附件上传请求的路径
        
         //上传路径
        Response<Dictionary> dictionary = dictionaryService.findByCodeDictionary("uploadPath");
        String reUrl = dictionary.getData().getValue();
        System.out.println(reUrl);
        String url = reUrl+"/attachment/uploadFiles";  
        //String url = "http://localhost:30002/attachment/uploadFiles";  //暂时写死
        HttpPost httppost = new HttpPost(url);
        // 创建待处理的文件
        String fileName = file.getOriginalFilename();
        ContentBody files = new ByteArrayBody(file.getBytes(), fileName);
        // 对请求的表单域进行填充
        
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,  
                Charset.forName("UTF-8"));
        
        reqEntity.addPart("file", files);
        //reqEntity.addPart("category", new StringBody(category));

        if (params != null) {
        	
            for (String key : params.keySet()) {
                String[] values = params.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    try {	
                        value = URLEncoder.encode(value, "UTF-8");
                        reqEntity.addPart(key, new StringBody(value));
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        // 设置请求
        httppost.setEntity(reqEntity);
        // 执行
        HttpResponse response = httpclient.execute(httppost);
        if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            String resString = EntityUtils.toString(entity, Charset.forName("UTF-8"));
            System.out.println("上传附件时返回的对象字符串："+resString);
            return resString;
        }
        return null;
    }
}
