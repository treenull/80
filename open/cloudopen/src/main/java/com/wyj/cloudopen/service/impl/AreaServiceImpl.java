package com.wyj.cloudopen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.service.IAreaService;
import com.wyj.cloudopen.entity.Area;
import com.wyj.cloudopen.mapper.AreaMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WYJ
 * @since 2021-09-28
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public JSONObject getFullNameAndFullId(String areaid) {
        return areaMapper.getFullNameAndFullId(areaid);
    }
}
