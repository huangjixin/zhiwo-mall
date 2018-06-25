package com.fulan.application.common;

import net.sf.json.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/19 13:45
 */
public class HttpClientUtil {

    private static final String API_TIMESTAMP = "http://api.submail.cn/service/timestamp.json";
    public static final String APPID = "appid";
    public static final String TIMESTAMP = "timestamp";
    public static final String SIGN_TYPE = "sign_type";
    public static final String SIGNATURE = "signature";
    public static final String APPKEY = "appkey";
    public static final String TYPE_NORMAL = "normal";

    private static CloseableHttpClient closeableHttpClient = null;

    static  {
        closeableHttpClient = HttpClientBuilder.create().build();
    }

    public static String doPost(String reuslt) {
        System.out.println("result = "+reuslt);
        HttpPost httppost = new HttpPost(reuslt);
        httppost.addHeader("charset", "UTF-8");
        HttpHost proxy=new HttpHost("10.22.2.101", 80);
        RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
        httppost.setConfig(requestConfig);
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            httppost.setEntity(builder.build());
            HttpResponse response = closeableHttpClient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(jsonStr);
                return jsonStr;
            }
            closeableHttpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String,String> parseResStr(String resStr) {
        HashMap<String,String> pp = new HashMap<String,String>();
        try {
            String[] ps = resStr.split("&");
            for(int i=0;i<ps.length;i++){
                int ix = ps[i].indexOf("=");
                if(ix!=-1){
                    pp.put(ps[i].substring(0,ix),ps[i].substring(ix+1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return pp;
    }

    /**
     * 请求数据 post提交
     *
     * @param url
     * @param data
     * @return boolean
     * */
    public static String sendPost(String appId,String appKey,String url, Map<String, Object> data) {
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("charset", "UTF-8");
        httppost.setEntity(build(appId,appKey,data));
        try {
            HttpResponse response = closeableHttpClient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(jsonStr);
                return jsonStr;
            }
            closeableHttpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *将请求数据转换为HttpEntity
     *
     * @param data
     * @return HttpEntity
     * */
    protected static HttpEntity build(String appId,String appKey,Map<String, Object> data) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody(APPID, appId);
        builder.addTextBody(TIMESTAMP, getTimestamp());
        builder.addTextBody(SIGN_TYPE, TYPE_NORMAL);
        data.put(APPID, appId);
        data.put(TIMESTAMP, getTimestamp());
        data.put(SIGN_TYPE, TYPE_NORMAL);
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
        builder.addTextBody(SIGNATURE, appKey);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                builder.addTextBody(key, String.valueOf(value), contentType);
            } else if (value instanceof File) {
                builder.addBinaryBody(key, (File) value);
            }
        }
        return builder.build();
    }

    /**
     * 请求时间戳
     * @return timestamp
     * */
    protected static String getTimestamp() {
        HttpGet httpget = new HttpGet(API_TIMESTAMP);
        HttpResponse response;
        try {
            response = closeableHttpClient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
            if (jsonStr != null) {
                JSONObject json = JSONObject.fromObject(jsonStr);
                return json.getString("timestamp");
            }
            closeableHttpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}