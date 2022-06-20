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
    private String configkey;

    /**
     * VALUE-对应的数据名
     */
    private String configvalue;

    /**
     * 对应表的名字
     */
    private String tablename;

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
    public String getTableName() {
        return tablename;
    }

    public void setTableName(String tableName) {
        this.tablename = tableName;
    }

    @Override
    public String toString() {
        return "Table_config{" +
            "configKey=" + configkey +
            ", configValue=" + configvalue +
            ", tableName=" + tablename +
        "}";
    }
}
