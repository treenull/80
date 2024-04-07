package com.wyj.cloudopen.utils.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.utils.HttpClientUtil;
import org.apache.http.client.HttpClient;

/**
 * @Author YaJun.Wang
 * @Data 2024/2/27 15:05
 * @Description:
 */
public class WxUtils {

	private static String appID = "wx429aeee05316d561";
	private static String appsecret = "b9cecf283849d3569ff27a1f99e3b951";


	public static String getAccessToken(){
		String url  = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx429aeee05316d561&secret=b9cecf283849d3569ff27a1f99e3b951";
		String postResultJson = HttpClientUtil.doGet(url);
		JSONObject jsonObject = JSON.parseObject(postResultJson);
		return jsonObject.getString("access_token");
	}

	public static String sendMessage(){
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccessToken();

		String message  = "{\n" +
				"    \"touser\": \"oKHJM6zAOjDD-OMdLt3uYwRzvhzQ\",\n" +
				"    \"template_id\": \"BKCBI2MiT1ToAlRBpKUdL_vq45Z4IoWfT3uccxaSFUQ\"\n" +
				"}";
		String postResltJson = HttpClientUtil.doPostJson(url,null,message);
		return postResltJson;
	}

	public static  String sendMediaMessage(){
		String access = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+access;

		String mediaMessage  = "{\n" +
				"    \"articles\": [\n" +
				"        {\n" +
				"            \"thumb_media_id\": \"2m-a2IQF7x1YJxNBBmzaMG9uYE_EUVAoFWWsDMdY3JJuVwSKyCLoEz-BWCtVU1Gi\",\n" +
				"            \"author\": \"WYJ\",\n" +
				"            \"title\": \"提醒喝水小助手\",\n" +
				"            \"content\": \"雪丽宝宝，该喝水啦~\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		String postResultJson = HttpClientUtil.doPostJson(url,null,mediaMessage);
		String mediaId = JSON.parseObject(postResultJson).getString("media_id");

		String url2 = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+access;
		String message = "{\n" +
				"    \"touser\": [\n" +
				"        \"oKHJM6zAOjDD-OMdLt3uYwRzvhzQ\",\n" +
				"        \"oKHJM6-k2X3v7QFL4sTKV8YhUK_g\"\n" +
				"    ],\n" +
				"    \"mpnews\": {\n" +
				"        \"media_id\": " + "\""+mediaId+"\""+ "\n"+
				"    },\n" +
				"    \"msgtype\": \"mpnews\"\n" +
				"}";
		return HttpClientUtil.doPostJson(url2,null,message);
	}


}
