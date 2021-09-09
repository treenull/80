package com.wyj.cloudopen.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author WYJ
 * @since 2021-09-09
 */
public class Website_record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库添加时间
     */
    private LocalDateTime addTime;

    /**
     * 访问web页面
     */
    private String accessWeb;

    /**
     * 访问ip地址-来源JS
     */
    private String accessIP;

    /**
     * 访问浏览器
     */
    private String accessBrowser;

    /**
     * 访问浏览器版本
     */
    private String accessBrowserVersion;

    /**
     * 访问系统名称
     */
    private String accessOsName;

    /**
     * 访问时间-来源JS
     */
    private String interviewTime;

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
    public String getAccessWeb() {
        return accessWeb;
    }

    public void setAccessWeb(String accessWeb) {
        this.accessWeb = accessWeb;
    }
    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }
    public String getAccessBrowser() {
        return accessBrowser;
    }

    public void setAccessBrowser(String accessBrowser) {
        this.accessBrowser = accessBrowser;
    }
    public String getAccessBrowserVersion() {
        return accessBrowserVersion;
    }

    public void setAccessBrowserVersion(String accessBrowserVersion) {
        this.accessBrowserVersion = accessBrowserVersion;
    }
    public String getAccessOsName() {
        return accessOsName;
    }

    public void setAccessOsName(String accessOsName) {
        this.accessOsName = accessOsName;
    }
    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    @Override
    public String toString() {
        return "Website_record{" +
                "addTime=" + addTime +
                ", accessWeb=" + accessWeb +
                ", accessIP=" + accessIP +
                ", accessBrowser=" + accessBrowser +
                ", accessBrowserVersion=" + accessBrowserVersion +
                ", accessOsName=" + accessOsName +
                ", interviewTime=" + interviewTime +
                "}";
    }
}
