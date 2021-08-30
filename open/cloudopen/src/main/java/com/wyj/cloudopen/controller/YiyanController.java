package com.wyj.cloudopen.controller;


import com.alibaba.fastjson.JSONObject;

import com.wyj.cloudopen.utils.CommonUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
public class YiyanController {

    @ResponseBody
    @GetMapping("/checkHealth")
    public JSONObject checkHealth(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return CommonUtil.successJson("200");
    }

    @ResponseBody
    @PostMapping("/dataRecord")
    public JSONObject dataRecord(@RequestBody JSONObject jsonObject, HttpServletResponse response){

        //response.setHeader("Access-Control-Allow-Origin","*");
        return CommonUtil.successJson("200");
    }

}
