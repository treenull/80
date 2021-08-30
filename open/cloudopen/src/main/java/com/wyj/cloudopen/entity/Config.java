package com.wyj.cloudopen.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置属性KEY
     */
    private String configKey;

    /**
     * 配置属性VALUE
     */
    private String configValue;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public String toString() {
        return "Config{" +
            "configKey=" + configKey +
            ", configValue=" + configValue +
        "}";
    }
}
