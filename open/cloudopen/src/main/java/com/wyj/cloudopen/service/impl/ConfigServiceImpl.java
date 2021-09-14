package com.wyj.cloudopen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyj.cloudopen.entity.Config;
import com.wyj.cloudopen.mapper.ConfigMapper;
import com.wyj.cloudopen.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public String getConfigValue(String configKey) {
        QueryWrapper<Config> configQueryWrapper = new QueryWrapper<>();
        // "SELECT configValue FROM config WHERE configKey = "IP_PY_URL""
        configQueryWrapper.select("configValue").eq("configKey",configKey);
        Config config = configMapper.selectOne(configQueryWrapper);
        return config.getConfigValue();

    }
}
