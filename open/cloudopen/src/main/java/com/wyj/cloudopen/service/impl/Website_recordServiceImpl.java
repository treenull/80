package com.wyj.cloudopen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.cloudopen.entity.Website_record;
import com.wyj.cloudopen.mapper.Website_recordMapper;
import com.wyj.cloudopen.service.IWebsite_recordService;
import com.wyj.cloudopen.utils.DateUtils;
import com.wyj.cloudopen.utils.IpAndAddrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WYJ
 * @since 2021-09-09
 */
@Service
public class Website_recordServiceImpl extends ServiceImpl<Website_recordMapper, Website_record> implements IWebsite_recordService {

    @Autowired
    private Website_recordMapper websiteRecordMapper;


    @Override
    public int record(HttpServletRequest request) {
        Website_record website_record = new Website_record();
        String ip = IpAndAddrUtil.getIp(request);
        String bName = IpAndAddrUtil.getBrowserName(request);
        String bVersion = IpAndAddrUtil.getBrowserVersion(request);
        String osName = IpAndAddrUtil.getOsName(request);

        String hostname = request.getParameter("hostname");
        String timestamp = request.getParameter("timestamp");
        website_record.setAccessWeb(hostname);
        website_record.setInterviewTime(timestamp);
        website_record.setAccessIP(ip);
        website_record.setAccessBrowser(bName);
        website_record.setAccessBrowserVersion(bVersion);
        website_record.setAccessOsName(osName);

        //return 1;
        return websiteRecordMapper.insert(website_record);
    }

    @Override
    public int detailedRecord(JSONObject jsonObject, HttpServletRequest request) {
        Website_record websiteRecord = new Website_record();
        String ip = IpAndAddrUtil.getIp(request);
        String bName = IpAndAddrUtil.getBrowserName(request);
        String bVersion = IpAndAddrUtil.getBrowserVersion(request);
        String osName = IpAndAddrUtil.getOsName(request);

        websiteRecord.setAccessWeb(jsonObject.getString("hostname"));
        websiteRecord.setInterviewTime(jsonObject.getString("timestamp"));

        websiteRecord.setAddTime(DateUtils.getDateTime());
        websiteRecord.setAccessIP(ip);
        websiteRecord.setAccessBrowser(bName);
        websiteRecord.setAccessBrowserVersion(bVersion);
        websiteRecord.setAccessOsName(osName);

        //return 1;

        return websiteRecordMapper.insert(websiteRecord);
    }


}
