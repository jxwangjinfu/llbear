package com.junfeng.platform.csc.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * url测试用类
 *
 * @author hgf
 * 2019.09
 */
public class UrlOperate {




//	public static String sendPostRequest(String requestUrl, String payload) {
//		StringBuffer jsonString = new StringBuffer();
//		HttpURLConnection connection = null;
//		BufferedReader br = null;
//		try {
//			URL url = new URL(requestUrl);
//			connection = (HttpURLConnection) url.openConnection();
//
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//			connection.setRequestMethod("POST");
//
//			connection.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 11.0; Windows NT 6.1; Trident/5.0)");
//			connection.setRequestProperty("Accept", "application/json");
////            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//			connection.setReadTimeout(300000);
//			connection.setConnectTimeout(300000);
//
//			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//			writer.write(payload);
//			writer.close();
//			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//			String line;
//			while ((line = br.readLine()) != null) {
//				jsonString.append(line);
//			}
//			br.close();
//			connection.disconnect();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//			String line;
//			try {
//				while ((line = br.readLine()) != null) {
//					jsonString.append(line);
//				}
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			connection.disconnect();
//			try {
//				br.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return jsonString.toString();
//	}

//	public static String sendPostRequestH(String requestUrl, String payload) {
//		StringBuffer jsonString = new StringBuffer();
//		HttpURLConnection connection = null;
//		BufferedReader br = null;
//		try {
//			URL url = new URL(requestUrl);
//			connection = (HttpURLConnection) url.openConnection();
//
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Accept", "application/json");
//			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//			connection.setRequestProperty("resultData", payload);
//			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//			writer.write("");
//			writer.close();
//			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//			String line;
//			while ((line = br.readLine()) != null) {
//				jsonString.append(line);
//			}
//			br.close();
//			connection.disconnect();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//			String line;
//			try {
//				while ((line = br.readLine()) != null) {
//					jsonString.append(line);
//				}
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			connection.disconnect();
//			try {
//				br.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return jsonString.toString();
//	}


	public static String getHTML(String urlToRead) throws Exception {
		BufferedReader br = null;
		StringBuilder result = null;
		HttpURLConnection conn = null;
		try {
			result = new StringBuilder();
			URL url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					result.append(line);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result.toString();
	}

//	public String askQuestionHS(String question) {
//		String answer = "";
//		try {
//			question = URLEncoder.encode(question, "UTF-8");
//			answer = UrlOperate.getHTML("https://api.ownthink.com/bot?appid=8c38e144c7da83b7322899ac8a2ba6a2&userid=V1eTpL2o&spoken=" + question);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return answer;
//	}

//	public static void main(String[] args) {
//
//		try {
//			//get
//			System.out.println(getHTML("http://192.168.199.34:8083/getanswer?question=" + URLEncoder.encode("你好，兄弟", "UTF-8"))); //解答问题
//			System.out.println(getHTML("http://192.168.199.34:8083/getsimilaranswer?question=" + URLEncoder.encode("你好， ", "UTF-8"))); //解答问题
//			System.out.println(getHTML("http://192.168.199.34:8083/getdocument?title=" + URLEncoder.encode("网上购物流程", "UTF-8"))); //知识引导
//			System.out.println(getHTML("http://192.168.199.34:8083/loadaskandanswer?question=" + URLEncoder.encode("你好，朋友", "UTF-8") + "&answer=" + URLEncoder.encode("亲，你好", "UTF-8"))); //问答知识导入
//			System.out.println(getHTML("http://192.168.199.34:8083/loaddocument?parentTitle=" + URLEncoder.encode("", "UTF-8") + "&title=" + URLEncoder.encode("网上购物流程", "UTF-8") + "&docment=" + URLEncoder.encode("首先，你要注册一个帐号，登录后，可以在购物平台中先选择你要购买的商品进行查询，在查询出的页面，你可以选择以商家信誉排列商品或以价格高低排列商品，这样比较一目了然的可以看到你所要选的商品。……", "UTF-8")));//文档知识导入
//			System.out.println(getHTML("http://192.168.199.34:8083/trainaskandanswer?num=" + URLEncoder.encode("10", "UTF-8"))); //训练问答
//			System.out.println(getHTML("http://192.168.199.34:8083/searchtrain?title=" + URLEncoder.encode("CAD软件的角度取值为整数,1度,2度,3度,4度没有2.1度,? 爱问知识人", "UTF-8") + "&pagenum=" + URLEncoder.encode("1", "UTF-8"))); //查看训练结果
//			System.out.println(getHTML("http://192.168.199.34:8083/deletetrain?question=" + URLEncoder.encode("35年前的今天,一个伟人过世了请您在此说一句心里话@ 爱问知识人", "UTF-8"))); //删除训练结果
//			//post
//
//
////        	Date dct=(Date) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ct);
////        	System.out.println(dateToWeek("2019-10-15 10:27:35.0"));
////            System.out.println(dct.getDay());
//
////        	loadData();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//
//	/**
//	 * 根据日期获取 星期 （2019-05-06 ——> 星期一）
//	 *
//	 * @param datetime
//	 * @return
//	 */
//	public static String dateToWeek(String datetime) {
//
//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//		Calendar cal = Calendar.getInstance();
//		Date date;
//		try {
//			date = f.parse(datetime);
//			cal.setTime(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		//一周的第几天
//		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//		if (w < 0)
//			w = 0;
//		return weekDays[w];
//	}
//
//
//	public static void loadData( ) throws Exception {
//		String fileName="C:\\data\\chatbot\\baike_qa2019\\baike_qa_valid.json";
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
//		String line = null;
//		for(int i=0;i<100;i++) {
//			line = reader.readLine();
//			JSONObject object = JSONObject.parseObject(line);
//			String param="question="+ URLEncoder.encode(object.getString("title"), "UTF-8")+"&answer="+ URLEncoder.encode(object.getString("answer"), "UTF-8");
//			sendPostRequest("http://192.168.199.34:8083/loadaskandanswer",param);
//			logger.info(object.getString("title"));
//		}
//		reader.close();
//	}


}
