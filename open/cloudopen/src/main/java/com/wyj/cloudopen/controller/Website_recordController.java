package com.wyj.cloudopen.controller;


import com.alibaba.fastjson.JSON;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

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
public class Website_recordController extends BaseController {

    @Autowired
    private IWebsite_recordService iWebsiteRecordService;
    @Autowired
    private IConfigService iConfigService;


    /**
     * 接口服务状态检测
     * @param response
     * @return
     */
    @ResponseBody
    @GetMapping("/checkHealth")
    public JSONObject checkHealth(HttpServletResponse response){

        logger.info("requestUrl:{},start","/checkHealth");
        return CommonUtil.successJson("200");
    }

    /**
     * 访问记录接口
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/record")
    public JSONObject dataRecord(HttpServletResponse response, HttpServletRequest request){

        logger.info("requestUrl:{},start","/record");
        iWebsiteRecordService.record(request);

        return CommonUtil.successJson("200");
    }

    /**
     * 访问详情记录接口，信息会更详细些
     * @param jsonObject
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/detailedRecord")
    public JSONObject detailedRecord(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request){

        logger.info("requestUrl:{},start","/detailedRecord");
        iWebsiteRecordService.detailedRecord(jsonObject,request);

        return CommonUtil.successJson("200");
    }

    /**
     * 根据详情接口记录的IP，去网站上爬取IP对应的省市信息，并入库
     * @return
     * @throws IOException
     */
    //每隔30分钟执行一次
    //@Scheduled(cron="0 30 * * * ?")
    @ResponseBody
    @GetMapping("/pythonIp")
    public JSONObject pythonIp() throws IOException {

        logger.info("requestUrl:{},start","/pythonIp");
        //数据库获取所有未更新的ip数据
        QueryWrapper<Website_record> recordQueryWrapper = new QueryWrapper<>();
        // "SELECT id,accessIP FROM website_record WHERE status=0000 LIMIT 1"
        recordQueryWrapper.select("id","accessIP").eq("status","000");
        List<Website_record> websiteRecords = iWebsiteRecordService.list(recordQueryWrapper);
        //数据库获取配置的爬虫爬取链接
        String baseUrl = iConfigService.getConfigValue("IP_PY_URL");
        logger.info("获取到待更新数据共:{}条, 本次爬虫爬取链接为：{}, 开始循环爬取",websiteRecords.size(),baseUrl);
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
                logger.error("抓取数据异常",ignored);
            }
            UpdateWrapper<Website_record> recordUpdateWrapper = new UpdateWrapper<>();
            // "UPDATE website_record SET accessAddr = '',accessOperator = '',status = 0000 WHERE id ="
            recordUpdateWrapper.setSql("accessAddr = '"+addr+"',accessOperator = '"+operator+"',status = 1").eq("id",websiteRecord.getId());
            iWebsiteRecordService.update(recordUpdateWrapper);
            logger.info("成功入库一条数据:{}",recordUpdateWrapper.getSqlSet());
            //当前线程暂停5秒后执行，防止爬取频率过高，被Ban掉IP
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                logger.error("当前线程暂停5s失败",e);
            }

        }
        logger.info("爬取结束");
        return CommonUtil.successJson("200");
    }

    /**
     * 解析ip地址
     *
     * @Param ipAddress ip地址
     * @Return 解析后的ip地址
     */
    public static String getIpSource(String ipAddress) {
        try {
            URL url = new URL("http://opendata.baidu.com/api.php?query=" + ipAddress + "&co=&resource_id=6006&oe=utf8");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "utf-8"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            Map map = JSON.parseObject(result.toString(), Map.class);
            List<Map<String, String>> data = (List) map.get("data");
            return data.get(0).get("location");
        } catch (Exception e) {
            return "";
        }
    }


}
