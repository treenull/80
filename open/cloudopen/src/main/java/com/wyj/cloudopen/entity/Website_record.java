package com.wyj.cloudopen.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author WYJ
 * @since 2021-09-11
 */
public class Website_record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID主键
     */
    private int id;

    /**
     * 数据库添加时间
     */
    private String addTime;

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
     * 访问归属地地址
     */
    private String accessAddr;

    /**
     * 访问来源网络运营商
     */
    private String accessOperator;

    /**
     * 访问来源DNS解析
     */
    private String accessRdns;

    /**
     * 访问时间-来源JS
     */
    private String interviewTime;

    /**
     * 数据更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态 0001 已更新  0000 未更新
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
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
    public String getAccessAddr() {
        return accessAddr;
    }

    public void setAccessAddr(String accessAddr) {
        this.accessAddr = accessAddr;
    }

    public String getAccessOperator() {
        return accessOperator;
    }

    public void setAccessOperator(String accessOperator) {
        this.accessOperator = accessOperator;
    }

    public String getAccessRdns() { return accessRdns; }

    public void setAccessRdns(String accessRdns) { this.accessRdns = accessRdns; }
    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Website_record{" +
                "id=" + id +
                ", addTime='" + addTime + '\'' +
                ", accessWeb='" + accessWeb + '\'' +
                ", accessIP='" + accessIP + '\'' +
                ", accessBrowser='" + accessBrowser + '\'' +
                ", accessBrowserVersion='" + accessBrowserVersion + '\'' +
                ", accessOsName='" + accessOsName + '\'' +
                ", accessAddr='" + accessAddr + '\'' +
                ", accessOperator='" + accessOperator + '\'' +
                ", accessRdns='" + accessRdns + '\'' +
                ", interviewTime='" + interviewTime + '\'' +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
