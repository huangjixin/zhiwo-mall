import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

public class JSoapTest {

	public Document getDocument(String url) {
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		JSoapTest soapTest = new JSoapTest();
		String url="https://detail.1688.com/offer/539123350409.html?spm=a312h.7841636.1998813769.d_pic_3.1hzJv9&tracelog=p4p";
		url="https://detail.1688.com/offer/540395822242.html?spm=a312h.7841636.1998813769.d_pic_30.pNBkN1&tracelog=p4p";
		url="https://detail.1688.com/offer/39246421071.html?spm=a312h.7841636.1998813769.d_pic_34.pNBkN1&tracelog=p4p";
		url="https://detail.1688.com/offer/45609598926.html?spm=a312h.7841636.1998813769.d_pic_27.u6d7Pe&tracelog=p4p";
		url="https://jingcai.hao123.com/";
		url="https://jingcai.hao123.com/accountrecord?type=1";
		
		Document document = soapTest.getDocument(url);
		String title = document.title();
		System.out.println(document.html());
	}

	
	/*** 
	   * 下载图片 
	   * 
	   * @param listImgSrc 
	   */
	  private void download(List<String> listImgSrc) { 
	    try { 
	      for (String url : listImgSrc) { 
	        String imageName = "D:"+File.separator+"123.jpg"; 
	        URL uri = new URL(url); 
	        InputStream in = uri.openStream(); 
	        FileOutputStream fo = new FileOutputStream(new File(imageName)); 
	        byte[] buf = new byte[1024]; 
	        int length = 0; 
	        System.out.println("开始下载:" + url); 
	        while ((length = in.read(buf, 0, buf.length)) != -1) { 
	          fo.write(buf, 0, length); 
	        } 
	        in.close(); 
	        fo.close(); 
	        System.out.println(imageName + "下载完成"); 
	      } 
	    } catch (Exception e) { 
	      System.out.println("下载失败"); 
	    } 
	  } 
}
