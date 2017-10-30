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
		Document document = soapTest.getDocument(url);
		String title = document.title();
		Elements scriptElements  = document.select("script[type=text/javascript]");
		if(scriptElements!=null && scriptElements.size()>0){
			Element element = null;
			for (int i = 0; i < scriptElements.size(); i++) {
				Element ele = scriptElements.get(i);
				String html = ele.html();
				int index = html.indexOf("iDetailData");
				if(index!=-1){
					element = ele;
					break;
				}
			}
			
			if(element!=null){
				
				String js = element.html();
				System.out.println(js);
				ScriptEngineManager manager = new ScriptEngineManager();   
				ScriptEngine engine = manager.getEngineByName("javascript");  
				try {
					engine.eval(js);
					ScriptObjectMirror c = (ScriptObjectMirror) engine.get("iDetailData");
					ScriptObjectMirror sku = (ScriptObjectMirror) c.get("sku");
					
					ScriptObjectMirror skuProps = (ScriptObjectMirror) sku.get("skuProps");
					int i = 0;
					while (skuProps.hasSlot(i)) {
						ScriptObjectMirror o = (ScriptObjectMirror) skuProps.getSlot(i);
						System.out.println("prop属性是："+o.get("prop"));
						ScriptObjectMirror value = (ScriptObjectMirror) o.get("value");
						System.out.println("遍历属性值开始：");
						//遍历属性值开始；
						int j=0;
						while (value.hasSlot(j)) {
							ScriptObjectMirror valueItem = (ScriptObjectMirror) value.getSlot(j);
							String name = (String) valueItem.get("name");
							System.out.println(name);
							j++;
						}
						
						i++;
					}
					
				} catch (ScriptException e) {
					e.printStackTrace();
				}
				
				
				
			}
		}
		
		Elements picElements  = document.select("ul[class=nav nav-tabs fd-clr]");
		if(picElements!=null && picElements.size()>0){
			for (Element element : picElements) {
				List<Node> childNodes = element.childNodes();
				for (Node node : childNodes) {
					String dataImgs = node.attr("data-imgs");
					if(!"".equals(dataImgs)){
						System.out.println(dataImgs);
						JSONObject jsonObject = JSONObject.parseObject(dataImgs);
						System.out.println(jsonObject.get("preview"));
					}
				}
			}
		}
		
		//详情图片
		List<String> imges = new ArrayList<String>();
		Elements el = document.select("div[id=desc-lazyload-container]");
		String dataTfsUrl = el.first().attr("data-tfs-url");
		System.out.println(dataTfsUrl);
		if(!"".equals(dataTfsUrl)){
			Document doc = Jsoup.connect(dataTfsUrl).get();
			Elements elements = doc.select("img");
			
			for (Element node : elements) {
				if(node.hasAttr("src")){
					String src = node.attr("src");
					src =src.replaceAll("\\\"", "");
					src=src.substring(1, src.length()-1);
					imges.add(src);
				}
			}
		}
		
		
		soapTest.download(imges);
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
