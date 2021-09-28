package com.wyj.cloudopen.service;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.entity.Area;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WYJ
 * @since 2021-09-28
 */
public interface IAreaService extends IService<Area> {

    /**
     * 根据子节点查询所有的父节点，并将其拼接成字符串 类似：【河北省,承德市,围场县	5,239,246】
     * @param areaid
     * @return
     */
    public JSONObject getFullNameAndFullId(String areaid);

}
