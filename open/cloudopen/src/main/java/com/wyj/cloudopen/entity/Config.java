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
    private String configkey;

    /**
     * 配置属性VALUE
     */
    private String configvalue;

    public String getConfigKey() {
        return configkey;
    }

    public void setConfigKey(String configKey) {
        this.configkey = configKey;
    }
    public String getConfigValue() {
        return configvalue;
    }

    public void setConfigValue(String configValue) {
        this.configvalue = configValue;
    }

    @Override
    public String toString() {
        return "Config{" +
            "configKey=" + configkey +
            ", configValue=" + configvalue +
        "}";
    }
}
