package com.wyj.cloudopen;

import com.wyj.cloudopen.utils.HttpClientUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CloudopenApplicationTests {

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

}
