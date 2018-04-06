/**
 * 
 */
package com.zwo.xiyangyang.schedule;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zwo.xiyangyang.modules.guess.domain.GuessCategory;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestionApi;
import com.zwo.xiyangyang.modules.guess.dto.GuessData;
import com.zwo.xiyangyang.modules.guess.service.IGuessCategoryService;
import com.zwo.xiyangyang.modules.guess.service.IGuessQuestionApiService;
import com.zwo.xiyangyang.modules.guess.service.IOptionsService;
import com.zwo.xiyangyang.modules.guess.service.IQuestionService;

/**
 * 通过定时器定时去查询远程URL的竞猜问题，并且将他们入库。
 * 
 * @author 黄记新
 *
 */
@Transactional(readOnly=false)
//@Lazy(true)
@Component
public class FetchRemoteQuestions {
	@Autowired
	private IOptionsService optionsService;
	@Autowired
	private IGuessCategoryService categoryService;
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IGuessQuestionApiService apiService;
	// API数组 0:超级大乐透，1福彩3D，2.排列3.3.排列5，4.七乐彩
	private String[] apiArray = { "http://f.apiplus.net/dlt.json", "http://f.apiplus.net/fc3d.json",
			"http://f.apiplus.net/pl3.json", "http://f.apiplus.net/pl5.json", "http://f.apiplus.net/qlc.json" };

//	@Scheduled(fixedRate = 5000)
	public void fixedRateJob() {
		// get("http://f.apiplus.net/gd11x5-2.json","gd11x5","广东11选5","gd11x5");
		get("http://f.apiplus.net/gd11x5-2.json", "gd11x5", "广东11选5", "gd11x5");
		// System.out.println("hello,world,this is a fixed rate job");
	}

	/**
	 * 发送 get请求
	 */
	/**
	 * @param url
	 * @param type
	 * @param name
	 * @param categoryCode
	 */
	@Transactional(readOnly=false)
	public void get(String url, String type, String name, String categoryCode) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			response = httpclient.execute(httpget);
			// 获取响应实体
			HttpEntity entity = (HttpEntity) response.getEntity();
			System.out.println("--------------------------------------");
			// 打印响应状态
			System.out.println(response.getStatusLine());
			if (entity != null) {
				String content = EntityUtils.toString(entity);
				if (content.indexOf("请求频率过快") > -1) {
					return;
				}
				// 此处的代码逻辑是：根据类型查询列表，取到第一个
				List<GuessQuestionApi> list = apiService.selectByType(type);
				GuessQuestionApi api = null;
				if (list.size() != 0) {
					api = list.get(0);
					if (!content.equals(api.getContent())) {
//						chectData(content, type, name, categoryCode);
						api.setUpdateDate(new Date());
						api.setContent(content);
						apiService.updateByPrimaryKeySelective(api);
					}
				}else {
					api = new GuessQuestionApi();
					api.setContent(content);
					api.setName(name);
					api.setType(type);
					apiService.insertSelective(api);
				}

				/*if (api == null) {
					chectData(content, type, name, categoryCode);
					
				}*/

				// 打印响应内容
				//System.out.println("Response content: " + EntityUtils.toString(entity));
			}
			System.out.println("------------------------------------");
			try {

			} finally {

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (response != null)
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
	}

	/**
	 * 根据返回的
	 * 
	 * @param jsonArray
	 */
	private void chectData(String content, String type, String name, String categoryCode) {
		JSONObject json;
		try {
			json = new JSONObject(content);
			JSONArray jsonArray = json.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				Object obj = jsonArray.get(i);
				Gson gson = new Gson();
				GuessData guessData = gson.fromJson(obj.toString(), GuessData.class);
				GuessQuestion question = this.questionService.selectByName(guessData.getExpect());

				/*
				 * 第一条竞猜数据格式是这样的：data:[ { expect: "2018069", opencode: "1,1,9", opentime:
				 * "2018-03-17 21:20:00", opentimestamp: 1521292800 } ]
				 */
				if (question != null) {
					// 如果该问题还没有检验校对答案。
					if (!question.getChecked()) {
						String openCode = null;
						if (guessData.getOpencode().indexOf("+") > -1) {
							openCode = guessData.getOpencode().replaceAll("+", ",");
						} else {
							openCode = guessData.getOpencode();
						}
						
						String[] openCodeArray = openCode.split(",");
						int result = 0;
						for (String codeStr : openCodeArray) {
							result += Integer.valueOf(codeStr);
						}
						String resu = result % 2 == 0 ? "偶数" : "奇数";
						GuessOptions guessOptions = new GuessOptions();
						guessOptions.setGuessQuestionId(question.getId());
						guessOptions.setIsRight(true);
						guessOptions.setName(resu);
						this.optionsService.checkOption(guessOptions);
						// optionsService.se
						// 校验好问题。
						question.setChecked(true);
						questionService.updateById(question);
					}
				}

				if (i == 0) {
					Integer expect = Integer.valueOf(guessData.getExpect());
					expect += 1;

					question = this.questionService.selectByName(expect + "");
					// 如果数据库里面还没有插入了
					if (question == null) {
						GuessCategory category = this.categoryService.selectByCode(categoryCode);

						GuessQuestion guessQuestion = new GuessQuestion();
						guessQuestion.setCreateDate(new Date());
						// 开彩前一分钟截止。
						Date questionEndTime = null;
						// 福彩3d
						if (type.equals("fc3d") || type.equals("pl3") || type.equals("pl5")) {
							questionEndTime = new Date(guessData.getOpentime().getTime() + 24 * 60 * 59000);
						} else if (type.equals("gd11x5")) {// 广东11选5 高频彩，所以时间必须在十分钟。
							questionEndTime = new Date(guessData.getOpentime().getTime() + 540000);
						}

						if (category != null) {
							guessQuestion.setGuessCategoryId(category.getId());
						}
						guessQuestion.setQuestionEndTime(questionEndTime);
						guessQuestion.setName(expect + "");
						guessQuestion.setChecked(false);
						guessQuestion.setDescription(expect + "期" + name + "所有开奖数字累计结果相加是");
						questionService.insert(guessQuestion);

						for (int j = 0; j < 2; j++) {
							GuessOptions options = new GuessOptions();
							options.setBetRate(1.8D);
							options.setIsRight(null);
							options.setGuessQuestionId(guessQuestion.getId());
							options.setName(j == 0 ? "偶数" : "奇数");
							optionsService.insert(options);
						}
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int result = Integer.valueOf("01");
		System.out.println(result);
	}
}
