package com.wyj.cloudopen.entity;


import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
public class Yiyan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库添加时间
     */
    private Date addTime;

    /**
     * 访问ip地址-来源JS
     */
    private String accessIP;

    /**
     * 访问时间-来源JS
     */
    private String interviewTime;

    /**
     * 一言
     */
    private String yHitokoto;

    /**
     * 一言-出处
     */
    private String yFrom;

    /**
     * 一言-作者
     */
    private String yFromWho;

    /**
     * 一言-分享者
     */
    private String yCreator;

    /**
     * 一言-分享出处
     */
    private String yCommitFrom;

    /**
     * 一言-分享时间
     */
    private String yCreatedAt;

    /**
     * 一言-原始json
     */
    private String ySourceJson;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }
    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }
    public String getyHitokoto() {
        return yHitokoto;
    }

    public void setyHitokoto(String yHitokoto) {
        this.yHitokoto = yHitokoto;
    }
    public String getyFrom() {
        return yFrom;
    }

    public void setyFrom(String yFrom) {
        this.yFrom = yFrom;
    }
    public String getyFromWho() {
        return yFromWho;
    }

    public void setyFromWho(String yFromWho) {
        this.yFromWho = yFromWho;
    }
    public String getyCreator() {
        return yCreator;
    }

    public void setyCreator(String yCreator) {
        this.yCreator = yCreator;
    }
    public String getyCommitFrom() {
        return yCommitFrom;
    }

    public void setyCommitFrom(String yCommitFrom) {
        this.yCommitFrom = yCommitFrom;
    }
    public String getyCreatedAt() {
        return yCreatedAt;
    }

    public void setyCreatedAt(String yCreatedAt) {
        this.yCreatedAt = yCreatedAt;
    }
    public String getySourceJson() {
        return ySourceJson;
    }

    public void setySourceJson(String ySourceJson) {
        this.ySourceJson = ySourceJson;
    }

    @Override
    public String toString() {
        return "Yiyan{" +
            "addTime=" + addTime +
            ", accessIP=" + accessIP +
            ", interviewTime=" + interviewTime +
            ", yHitokoto=" + yHitokoto +
            ", yFrom=" + yFrom +
            ", yFromWho=" + yFromWho +
            ", yCreator=" + yCreator +
            ", yCommitFrom=" + yCommitFrom +
            ", yCreatedAt=" + yCreatedAt +
            ", ySourceJson=" + ySourceJson +
        "}";
    }
}
