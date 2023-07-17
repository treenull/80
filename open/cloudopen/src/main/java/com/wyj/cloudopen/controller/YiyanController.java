package com.wyj.cloudopen.controller;


import com.alibaba.fastjson.JSONObject;

import com.wyj.cloudopen.service.IYiyanService;
import com.wyj.cloudopen.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
@Controller
@RequestMapping("/cloudopen/yiyan")
@CrossOrigin
public class YiyanController extends BaseController {

    @Autowired
    private IYiyanService iYiyanService;

    //@ResponseBody
    //@GetMapping("/checkHealth")
    public JSONObject checkHealth(HttpServletResponse response){

        return CommonUtil.successJson("200");
    }

    /**
     * 每条从一言接口拉取的文案入库，收集自己的文案库
     * @param jsonObject
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/dataRecord")
    public JSONObject dataRecord(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request){

        logger.info("requestUrl:{},start","/dataRecord");
        iYiyanService.dataRecord(jsonObject,request);

        return CommonUtil.successJson("200");
    }

    //@ResponseBody
    //@GetMapping("/list")
    public JSONObject recordList(HttpServletRequest request){

        return iYiyanService.list(CommonUtil.request2Json(request));

    }

}
