package com.wyj.cloudopen.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wyj.cloudopen.entity.Website_record;
import com.wyj.cloudopen.service.IConfigService;
import com.wyj.cloudopen.service.IWebsite_recordService;
import com.wyj.cloudopen.utils.CommonUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    @Autowired
    private IConfigService iConfigService;


    @ResponseBody
    @GetMapping("/checkHealth")
    public JSONObject checkHealth(HttpServletResponse response){

        return CommonUtil.successJson("200");
    }

    @ResponseBody
    @GetMapping("/record")
    public JSONObject dataRecord(HttpServletResponse response, HttpServletRequest request){

        iWebsiteRecordService.record(request);

        return CommonUtil.successJson("200");
    }

    @ResponseBody
    @PostMapping("/detailedRecord")
    public JSONObject detailedRecord(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request){

        iWebsiteRecordService.detailedRecord(jsonObject,request);

        return CommonUtil.successJson("200");
    }

    //每隔30分钟执行一次
    //@Scheduled(cron="0 30 * * * ?")
    @ResponseBody
    @GetMapping("/pythonIp")
    public JSONObject pythonIp() throws IOException {

        //数据库获取所有未更新的ip数据
        QueryWrapper<Website_record> recordQueryWrapper = new QueryWrapper<>();
        // "SELECT id,accessIP FROM website_record WHERE status=0000 LIMIT 1"
        recordQueryWrapper.select("id","accessIP").eq("status",00).last("limit 10");
        List<Website_record> websiteRecords =  iWebsiteRecordService.list(recordQueryWrapper);

        //数据库获取配置的爬虫爬取链接
        String baseUrl = iConfigService.getConfigValue("IP_PY_URL");

        //循环抓取
        for (Website_record websiteRecord : websiteRecords) {

            //拼接链接
            String url = baseUrl + websiteRecord.getAccessIP();
            Connection connect = Jsoup.connect(url);
            // 利用headers
            connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            connect.header("Cookie", "PHPSESSID=b5b78939m384c9mf112va1ol09");
            connect.header("Referer", "https://ipchaxun.com/");
            connect.header("Host", "ipchaxun.com");
            connect.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
            connect.header("Upgrade-Insecure-Requests", "1");
            Document document = Jsoup.connect(url).get();
            String s = document.getElementsByClass("info").text();

            String[] splits = s.split("\\s+");
            String addr = "";
            String operator = "";
            //可能出现异常
            try {
                int index = splits[1].indexOf("：");
                addr = splits[1].substring(index+1);
                index = splits[2].indexOf("：");
                operator = splits[2].substring(index+1);
            }catch (Exception ignored){
                ignored.printStackTrace();
            }
            UpdateWrapper<Website_record> recordUpdateWrapper = new UpdateWrapper<>();
            // "UPDATE website_record SET accessAddr = '',accessOperator = '',status = 0000 WHERE id ="
            recordUpdateWrapper.setSql("accessAddr = '"+addr+"',accessOperator = '"+operator+"',status = 1").eq("id",websiteRecord.getId());
            iWebsiteRecordService.update(recordUpdateWrapper);

            //当前线程暂停5秒后执行，防止爬取频率过高，被Ban掉IP
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

        return CommonUtil.successJson("200");
    }


}
