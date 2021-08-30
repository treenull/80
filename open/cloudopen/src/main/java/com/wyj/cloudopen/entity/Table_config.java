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
public class Table_config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * KEY-对应表的字段名
     */
    private String configKey;

    /**
     * VALUE-对应的数据名
     */
    private String configValue;

    /**
     * 对应表的名字
     */
    private String tableName;

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
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "Table_config{" +
            "configKey=" + configKey +
            ", configValue=" + configValue +
            ", tableName=" + tableName +
        "}";
    }
}
