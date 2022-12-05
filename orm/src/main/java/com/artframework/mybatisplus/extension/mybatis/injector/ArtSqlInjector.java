package com.artframework.mybatisplus.extension.mybatis.injector;

import com.artframework.mybatisplus.extension.byteB.DynamicClassUtils;
import com.artframework.mybatisplus.extension.config.GlobalTableConfiguration;
import com.artframework.mybatisplus.extension.config.TableConfiguration;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.MapperBuilderAssistant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/17
 **/
@Slf4j
public class ArtSqlInjector extends DefaultSqlInjector {

    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        Class<?> modelClass = extractModelClass(mapperClass);
        if (modelClass != null) {
            String className = mapperClass.toString();
            Set<String> mapperRegistryCache = GlobalConfigUtils.getMapperRegistryCache(builderAssistant.getConfiguration());
            if (!mapperRegistryCache.contains(className)) {
                List<AbstractMethod> methodList = this.getMethodList(mapperClass);
                if (CollectionUtils.isNotEmpty(methodList)) {
                    //--Customize
                    Class<?> finalModelClass = buildModelClassProxy(modelClass);

                    //--Customize
                    TableInfo tableInfo = TableInfoHelper.initTableInfo(builderAssistant, finalModelClass);
                    //--Customize
                    installCache(modelClass.getName(), tableInfo);
                    //--Customize
                    // 循环注入自定义方法
                    methodList.forEach(m -> m.inject(builderAssistant, mapperClass, finalModelClass, tableInfo));
                } else {
                    log.debug(mapperClass.toString() + ", No effective injection method was found.");
                }
                mapperRegistryCache.add(className);
            }
        }
    }

    /**
     * 修改 LambdaUtils 类中的静态缓存数据
     *
     * @param className
     * @param tableInfo
     */
    private void installCache(String className, TableInfo tableInfo) {
        try {
            Field field = LambdaUtils.class.getDeclaredField("COLUMN_CACHE_MAP");
            field.setAccessible(true);
            Map<String, Map<String, ColumnCache>> COLUMN_CACHE_MAP = (Map<String, Map<String, ColumnCache>>) field.get(LambdaUtils.class);
            COLUMN_CACHE_MAP.put(className, createColumnCacheMap(tableInfo));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            log.error("修改 LambdaUtils 类中的静态缓存数据报错", ex);
        }
    }


    /**
     * 缓存实体字段 MAP 信息
     *
     * @param info 表信息
     * @return 缓存 map
     */
    private static Map<String, ColumnCache> createColumnCacheMap(TableInfo info) {
        Map<String, ColumnCache> map;

        if (info.havePK()) {
            map = CollectionUtils.newHashMapWithExpectedSize(info.getFieldList().size() + 1);
            map.put(LambdaUtils.formatKey(info.getKeyProperty()), new ColumnCache(info.getKeyColumn(), info.getKeySqlSelect()));
        } else {
            map = CollectionUtils.newHashMapWithExpectedSize(info.getFieldList().size());
        }

        info.getFieldList().forEach(i ->
                map.put(LambdaUtils.formatKey(i.getProperty()), new ColumnCache(i.getColumn(), i.getSqlSelect()))
        );
        return map;
    }


    private Class<?> buildModelClassProxy(Class<?> modelClass) {
        TableConfiguration.Table table = GlobalTableConfiguration.getTableConfig(modelClass.getName());
        if (null != table) {
            String name = modelClass.getName() + "Proxy";
            // 生成动态类
            try {
                Class<?> clazz = modelClass.getClassLoader().loadClass(name);
                return clazz;
            } catch (ClassNotFoundException e) {
                log.info("load Class " + name);
                HashMap propertyMap = new HashMap();
                try {
                    for (TableConfiguration.Table.TableColumn column : table.getColumns()) {
                        propertyMap.put(column.getName(), Class.forName(column.getType()));
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                return DynamicClassUtils.createBeanClass(name, modelClass, propertyMap);
            }
        }
        return modelClass;
    }
}
