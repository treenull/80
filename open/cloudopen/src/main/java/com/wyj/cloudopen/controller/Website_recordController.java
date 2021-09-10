package com.wyj.cloudopen.controller;


import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.service.IWebsite_recordService;
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
 * @since 2021-09-09
 */
@Controller
@RequestMapping("/cloudopen")
@CrossOrigin
public class Website_recordController {

    @Autowired
    private IWebsite_recordService iWebsiteRecordService;

    @ResponseBody
    @GetMapping("/record")
    public JSONObject dataRecord(HttpServletResponse response, HttpServletRequest request){

        iWebsiteRecordService.record(request);

        return CommonUtil.successJson("200");
    }

}
