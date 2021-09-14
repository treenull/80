package com.wyj.cloudopen.service;

import com.wyj.cloudopen.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
public interface IConfigService extends IService<Config> {

    /**
     * 根据Key获取Value
     * @param configKey
     * @return
     */
    public String getConfigValue(String configKey);

}
