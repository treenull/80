package com.wyj.cloudopen.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 记录请求执行时间
 */
@Component
public class LogFilter extends OncePerRequestFilter implements Ordered {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpRequest  = request;
		HttpServletResponse httpResponse = response;

		long startTime = System.currentTimeMillis();
		String url = request.getRequestURI();

		// 如果是公有云回调的接口，则不打印相关信息
		if(url.contains("QYSCloudCallbackServlet")) {
			filterChain.doFilter(httpRequest, httpResponse);
			return;
		}

		//正常逻辑
		try {
			filterChain.doFilter(httpRequest, httpResponse);
		} finally {
			// 记录请求执行时间
			logger.info("requestUrl:{},costTime:{}", url, System.currentTimeMillis() - startTime);
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
