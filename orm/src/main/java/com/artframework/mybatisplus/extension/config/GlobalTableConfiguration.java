package com.artframework.mybatisplus.extension.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/21
 **/
public class GlobalTableConfiguration {
    private static Map<String, TableConfiguration.Table> TABLE_MAP = new HashMap<>();


    private static Map<String, TableConfiguration.Table> CONFIG() {
        return Collections.unmodifiableMap(TABLE_MAP);
    }

    public static void Init(TableConfiguration configuration) {
        for (TableConfiguration.Table table : configuration.getTables()) {
            TABLE_MAP.put(table.getType(), table);
        }
    }

    public static TableConfiguration.Table getTableConfig(String name) {
        return CONFIG().get(name);
    }
}
