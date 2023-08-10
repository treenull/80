package com.wyj.cloudopen.controller;

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
public class QYSCloudCallbackController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@ResponseBody
	@RequestMapping("/QYSCloudCallbackServlet")
	public String QYSCloudCallbackServlet(@RequestParam Map<String,String> paramMap, HttpServletResponse response, HttpServletRequest request){

		logger.info("requestUrl:{}, start",request.getRequestURL());
		logger.info("接收到的公有云回调为：{}", paramMap);
		logger.info("requestUrl:{}, end",request.getRequestURL());

		return "{code:200,message:(๑•̀ㅂ•́)و✧}";
	}
}
