package com.wyj.cloudopen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.cloudopen.entity.Yiyan;
import com.wyj.cloudopen.mapper.YiyanMapper;
import com.wyj.cloudopen.service.IYiyanService;
import com.wyj.cloudopen.utils.CommonUtil;
import com.wyj.cloudopen.utils.IpAndAddrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
@Service
public class YiyanServiceImpl extends ServiceImpl<YiyanMapper, Yiyan> implements IYiyanService {

    @Autowired
    private YiyanMapper yiyanMapper;

    @Override
    public int dataRecord(JSONObject jsonObject, HttpServletRequest request) {
        String ip = IpAndAddrUtil.getIp(request);
        Yiyan yiyan = new Yiyan();
        yiyan.setAccessIP(ip);
        yiyan.setyHitokoto(jsonObject.getString("hitokoto"));
        yiyan.setyFrom(jsonObject.getString("from"));
        yiyan.setyFromWho(jsonObject.getString("from_who"));
        yiyan.setySharer(jsonObject.getString("creator"));
        yiyan.setyCommitFrom(jsonObject.getString("commit_from"));
        yiyan.setyCreatedAt(jsonObject.getString("created_at"));
        yiyan.setySourceJson(jsonObject.toJSONString());
        return yiyanMapper.insert(yiyan);
    }

    @Override
    public JSONObject list(JSONObject jsonObject) {
        List<JSONObject> list = yiyanMapper.list(jsonObject);
        return CommonUtil.successPage(list);
    }
}
