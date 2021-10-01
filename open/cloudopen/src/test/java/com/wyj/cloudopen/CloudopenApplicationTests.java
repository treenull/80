package com.wyj.cloudopen;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.entity.Area;
import com.wyj.cloudopen.service.IAreaService;
import com.wyj.cloudopen.utils.HttpClientUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.gj.util.*;



@SpringBootTest
class CloudopenApplicationTests {

	@Autowired
	private IAreaService iAreaService;

	@Test
	void contextLoads() {
	}

	/**
	 * Jsoup抓取测试
	 * @throws IOException
	 */
	@Test
	public void jsouptest1() throws IOException {
		//从URL加载HTML
		String ip = "116.179.37.58";
		String url = "https://ipchaxun.com/"+ip;
		Connection connect = Jsoup.connect(url);

		// 利用headers
		connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		connect.header("Cookie", "PHPSESSID=b5b78939m384c9mf112va1ol09");
		connect.header("Referer", "https://ipchaxun.com/");
		connect.header("Host", "ipchaxun.com");
		connect.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
		connect.header("Upgrade-Insecure-Requests", "1");
		Document document = Jsoup.connect(url).get();
		String s = document.getElementsByClass("info").text();
		String[] splits = s.split("\\s+");
		String[] split = s.split("：");

		System.out.println(s);

	}

	/**
	 * 百度API逆向解码，获取经纬度
	 */
	@Test
	public void test(){
		String key = "ylitFOZXvf6rY5nCPm73DVFKOKo9XMFZ";
		String address = "泛微软件大厦";
		String originDouble = HttpClientUtil.doGet(
				"http://api.map.baidu.com/geocoder?output=json&key="+key+"&address="+address);
		com.alibaba.fastjson.JSONObject jsonObjectOri = com.alibaba.fastjson.JSONObject.parseObject(originDouble);
		String status = jsonObjectOri.getString("status");
		if (status == "OK" || "OK".equals(status)) {// 解析的地址不为空时 进行值的获取
			String oriLng = jsonObjectOri.getJSONObject("result").getJSONObject("location").getString("lng");// 经度值
			String oriLat = jsonObjectOri.getJSONObject("result").getJSONObject("location").getString("lat");// 纬度值
			String location = oriLat + "," + oriLng;
			String result = HttpClientUtil.doGet(
					"http://api.map.baidu.com/geocoder?key=ylitFOZXvf6rY5nCPm73DVFKOKo9XMFZ&output=json&coordtype=wgs84ll&location="
							+ location);
			com.alibaba.fastjson.JSONObject jsonObjectAdds = com.alibaba.fastjson.JSONObject.parseObject(result);
			String province = jsonObjectAdds.getJSONObject("result").getJSONObject("addressComponent")
					.getString("province");// 省
			String city = jsonObjectAdds.getJSONObject("result").getJSONObject("addressComponent").getString("city");// 市

			System.out.println("province:" + province);
			System.out.println("city:" + city);
		}
	}

	/**
	 * 省市拼接 mysql 8.0
	 */
	@Test
	public void areatest(){
		List<Area> areaList = iAreaService.list();
		List<Area> areas = new ArrayList<>();
		int count = 0;
		for(Area area:areaList){
			count++;
			JSONObject jsonObject = iAreaService.getFullNameAndFullId(area.getAreaid().toString());
			if(jsonObject!= null && !jsonObject.isEmpty()){
				String fullname = jsonObject.getString("fname");
				String fullid = jsonObject.getString("fid");
				area.setFullname(fullname);
				area.setFullid(fullid);
				areas.add(area);
			}
			if(count>=100){
				count = 0;
				iAreaService.updateBatchById(areas);
			}

		}

	}

	/**
	 * 省市拼接 mysql 5.7
	 */
	@Test
//	@DS("mysql_2")
	public void areatest2() {
		List<Area> areaList = iAreaService.list();
		List<Area> areas = new ArrayList<>();
		int count = 0;
		for (Area area : areaList) {
			count++;
			JSONObject jsonObject = iAreaService.getFullNameAndFullId(area.getAreaid().toString());
			if (jsonObject != null && !jsonObject.isEmpty()) {
				String fullname = jsonObject.getString("fname");
				String fullid = jsonObject.getString("fid");
				area.setFullname(fullname);
				area.setFullid(fullid);
				areas.add(area);
			}
			if (count >= 100) {
				count = 0;
				iAreaService.updateBatchById(areas);
			}

		}
	}

	/**
	 * Jsoup模拟postman接口测试
	 * @throws IOException
	 */
	@Test
	public void postmantest() throws IOException, NoSuchAlgorithmException {
		//从URL加载HTML
		String host = "http://127.0.0.1:9182";
		String api = "/contract/detail";
		String param = "?contractId=2860921387552997625";
		String url = host+api+param;

		//表头时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		//表头登录AppToken
		String token = "qDiEwGfFcI";
		//表头登录AppSecret
		String secret = "CtY9B9Bc5KYdGzHHxFgQ0lA1HvMmyJ";
		/* MD5加密 */
		//表头登录签名signature
		//String signature = MD5.toMD5(token.trim()+secret.trim()+timestamp.trim());
		String signature = md5(token.trim()+secret.trim()+timestamp.trim());

		String signatures = "a241a6bc1304966281379501eb7baaa3";



		//Document document = Jsoup.connect(url).get();
		HashMap<String,String> header = new HashMap<>();

		header.put("Cache-Control","no-cache");
		header.put("Host", "127.0.0.1:9182");
		header.put("User-Agent", "PostmanRuntime/7.28.3");
		header.put("Accept", "*/*");
		header.put("Accept-Encoding","gzip, deflate, br");
		header.put("Connection","keep-alive");

		header.put("Postman-Token","07133faf-5ffc-4752-a68f-4532bbdb8bf8");

//		header.put("x-qys-timestamp","1632972339172");
//		header.put("x-qys-accesstoken","qDiEwGfFcI");
//		header.put("x-qys-signature","8638ba58cb9176cdb84277ea9f0bccfa");

		header.put("x-qys-timestamp",timestamp);
		header.put("x-qys-accesstoken",token);
		header.put("x-qys-signature",signature);

		String result = HttpClientUtil.doGet(url,header,null);

		JSONObject json_test = JSONObject.parseObject(result);

		System.out.println(result);

		System.out.println(json_test);
	}


	public static String md5(String text) {
		String result="";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
//       System.out.println("result: " + buf.toString());// 32位的加密
//       System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		return result;
	}
}
