package com.wyj.cloudopen.controller;

import com.wyj.cloudopen.utils.wx.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author YaJun.Wang
 * @Data 2024/2/27 14:14
 * @Description:
 */
@Controller
public class WxController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * 微信公众号校验
	 * @param paramMap
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/WxCheckSignature")
	public String wxCheckSignature(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		logger.info("requestUrl:{}, start",request.getRequestURL());
		logger.info("接收到消息：{}", paramMap);
		logger.info("requestUrl:{}, end",request.getRequestURL());

		return paramMap.get("echostr");
	}


	@ResponseBody
	@RequestMapping("/WxGetAccessToken")
	public String wxGetAccessToken(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		return WxUtils.getAccessToken();
	}


	@ResponseBody
	@RequestMapping("/WxSendMessage")
	public String wxSendMessage(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		return WxUtils.sendMessage();
	}

	@ResponseBody
	@RequestMapping("/WxSendMediaMessage")
	public String wxSendMediaMessage(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		return WxUtils.sendMediaMessage();
	}
}
