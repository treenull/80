package com.wyj.cloudopen.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private final Pattern pattern = Pattern.compile("[^\\\\]\\\\\\\\\\\\u");

	public void init(HttpServletRequest request, HttpServletResponse response) {

	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes.getRequest();
	}

	public HttpServletResponse getResponse() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes.getResponse();
	}

	/**
	 * 直接向response发送错误
	 *
	 * @param status
	 * @param message
	 * @param response
	 */
	public void sendErrorToResponse(int status, String message, HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setStatus(status);
			response.getOutputStream().write(("Error "+ status +" : " + message).getBytes("UTF-8"));
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 去除非法转译 比如 '\哈'
	 * @param text
	 * @return
	 */
	protected String filterInvalidEscape(String text) {
		if (text.startsWith("\\") && text.charAt(1) != '\\') {
			text = "\\" + text;
			logger.warn("头部增加一个\\");
		}
		if (text.endsWith("\\") && text.charAt(text.length() - 2) != '\\') {
			text += "\\";
			logger.warn("尾部增加一个\\");
		}
		final Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			final String target = matcher.group();
			String result = target.charAt(0) + "\\\\" + target.substring(1);
			text = text.replace(target, result);
			logger.warn("替换转译{} -> {}", target, result);
		}
		return text;
	}

	/**
	 * 去除非法转译 比如 '\哈'
	 * @param object
	 * @param objectMapper
	 * @return
	 */
	protected Object filterInvalidEscape(Object object, ObjectMapper objectMapper) {
		try {
			final String param = objectMapper.writeValueAsString(object);
			return objectMapper.readValue(filterInvalidEscape(param), object.getClass());
		} catch (Exception e) {
			logger.error("{}转换失败", JSONObject.toJSONString(object), e);
		}
		return object;
	}

}
