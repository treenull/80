package com.wyj.cloudopen.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author WYJ
 * @since 2021-08-29
 */
public class Yiyan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库添加时间
     */
    private LocalDateTime add_time;

    /**
     * 访问ip地址-来源JS
     */
    private String access_ip;

    /**
     * 访问时间-来源JS
     */
    private String interview_time;

    /**
     * 一言
     */
    private String y_hitokoto;

    /**
     * 一言-出处
     */
    private String y_from;

    /**
     * 一言-作者
     */
    private String y_from_who;

    /**
     * 一言-分享者
     */
    private String y_creator;

    /**
     * 一言-分享出处
     */
    private String y_commit_from;

    /**
     * 一言-分享时间
     */
    private String y_created_at;

    /**
     * 一言-原始json
     */
    private Blob y_source_json;

    public LocalDateTime getAdd_time() {
        return add_time;
    }

    public void setAdd_time(LocalDateTime add_time) {
        this.add_time = add_time;
    }
    public String getAccess_ip() {
        return access_ip;
    }

    public void setAccess_ip(String access_ip) {
        this.access_ip = access_ip;
    }
    public String getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(String interview_time) {
        this.interview_time = interview_time;
    }
    public String gety_hitokoto() {
        return y_hitokoto;
    }

    public void sety_hitokoto(String y_hitokoto) {
        this.y_hitokoto = y_hitokoto;
    }
    public String gety_from() {
        return y_from;
    }

    public void sety_from(String y_from) {
        this.y_from = y_from;
    }
    public String gety_from_who() {
        return y_from_who;
    }

    public void sety_from_who(String y_from_who) {
        this.y_from_who = y_from_who;
    }
    public String gety_creator() {
        return y_creator;
    }

    public void sety_creator(String y_creator) {
        this.y_creator = y_creator;
    }
    public String gety_commit_from() {
        return y_commit_from;
    }

    public void sety_commit_from(String y_commit_from) {
        this.y_commit_from = y_commit_from;
    }
    public String gety_created_at() {
        return y_created_at;
    }

    public void sety_created_at(String y_created_at) {
        this.y_created_at = y_created_at;
    }
    public Blob gety_source_json() {
        return y_source_json;
    }

    public void sety_source_json(Blob y_source_json) {
        this.y_source_json = y_source_json;
    }

    @Override
    public String toString() {
        return "Yiyan{" +
            "add_time=" + add_time +
            ", access_ip=" + access_ip +
            ", interview_time=" + interview_time +
            ", y_hitokoto=" + y_hitokoto +
            ", y_from=" + y_from +
            ", y_from_who=" + y_from_who +
            ", y_creator=" + y_creator +
            ", y_commit_from=" + y_commit_from +
            ", y_created_at=" + y_created_at +
            ", y_source_json=" + y_source_json +
        "}";
    }
}
