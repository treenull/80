package com.wyj.cloudopen.service;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.entity.Yiyan;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
public interface IYiyanService extends IService<Yiyan> {

    /**
     * 数据记录
     * @param jsonObject 一言原始数据
     * @param request  http请求数据
     * @return
     */
    public int dataRecord(JSONObject jsonObject, HttpServletRequest request);

}
