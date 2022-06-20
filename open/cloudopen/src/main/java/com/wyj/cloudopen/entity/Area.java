package com.wyj.cloudopen.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author WYJ
 * @since 2021-09-28
 */
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer areaid;

    private String areaname;

    private Integer parentid;

    private String fullname;

    private String fullid;

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }
    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
    public Integer getParentId() {
        return parentid;
    }

    public void setParentId(Integer parentId) {
        this.parentid = parentId;
    }
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getFullid() {
        return fullid;
    }

    public void setFullid(String fullid) {
        this.fullid = fullid;
    }

    @Override
    public String toString() {
        return "Area{" +
            "areaid=" + areaid +
            ", areaname=" + areaname +
            ", parentId=" + parentid +
            ", fullname=" + fullname +
            ", fullid=" + fullid +
        "}";
    }
}
