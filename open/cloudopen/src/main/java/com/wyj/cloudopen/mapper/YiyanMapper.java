package com.wyj.cloudopen.mapper;

import com.alibaba.fastjson.JSONObject;
import com.wyj.cloudopen.entity.Yiyan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
public interface YiyanMapper extends BaseMapper<Yiyan> {

    /**
     * 查询最新的10条数据
     * @param jsonObject
     * @return
     */
    List<JSONObject> list(JSONObject jsonObject);

}
