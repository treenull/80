package com.wyj.cloudopen.service;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.entity.Website_record;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WYJ
 * @since 2021-09-09
 */
public interface IWebsite_recordService extends IService<Website_record> {

    /**
     * 网站访问记录
     * @param request
     * @return
     */
    int record(HttpServletRequest request);

}
