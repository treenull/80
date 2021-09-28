package com.wyj.cloudopen.entity;

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

    private Integer areaid;

    private String areaname;

    private Integer parentId;

    private String fullname;

    private Integer fullid;

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
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public Integer getFullid() {
        return fullid;
    }

    public void setFullid(Integer fullid) {
        this.fullid = fullid;
    }

    @Override
    public String toString() {
        return "Area{" +
            "areaid=" + areaid +
            ", areaname=" + areaname +
            ", parentId=" + parentId +
            ", fullname=" + fullname +
            ", fullid=" + fullid +
        "}";
    }
}
