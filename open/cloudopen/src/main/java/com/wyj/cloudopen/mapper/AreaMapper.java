package com.wyj.cloudopen.mapper;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WYJ
 * @since 2021-09-28
 */
public interface AreaMapper extends BaseMapper<Area> {

    JSONObject getFullNameAndFullId(@Param("areaid") String areaid);

}
