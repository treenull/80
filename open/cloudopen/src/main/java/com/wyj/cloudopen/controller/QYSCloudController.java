package com.wyj.cloudopen.controller;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 接收契约锁公有云回调  加密接口和不加密接口
 * @Author YaJun.Wang
 * @Data 2023/7/11 16:31
 * @Description:
 */

@Controller
@CrossOrigin
public class QYSCloudController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 公有云回调接收接口
	 * @param paramMap
	 * @param response
	 * @param request
	 * @return
	 * @Author YaJun.Wang
	 * @Data 2023/7/11 16:31
	 */
	@ResponseBody
	@RequestMapping("/QYSCloudCallbackServlet")
	public String QYSCloudCallbackServlet(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		logger.info("requestUrl:{}, start",request.getRequestURL());
		logger.info("接收到的公有云回调为：{}", paramMap);
		logger.info("requestUrl:{}, end",request.getRequestURL());

		return "{code:200,message:(๑•̀ㅂ•́)و✧}";
	}

	/**
	 * 公有云Saas服务商ticket验证接口
	 * @param paramMap
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/QYSSaasCheckTicket")
	public JSONObject QYSCloudSaasCheckTicket(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		logger.info("Saas接收到的信息为:{}",paramMap);
		JSONObject jsonObject = new JSONObject();
		JSONObject ticket = new JSONObject();
		ticket.put("contact","18916246609");
		ticket.put("name","王雅军");
		jsonObject.put("code",0);
		jsonObject.put("message","(๑•̀ㅂ•́)و✧");
		jsonObject.put("result",ticket);

		return jsonObject;
	}
}
