package com.artframework.mybatisplus.extension.model.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/27
 **/
@Data
public class BaseDO {
    @TableField(value = "feature", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> feature=new HashMap();

    public Object getFeatureValue(String name) {
        return this.feature.get(name);
    }

    public void setFeatureValue(String name, Object value) {
        this.feature.put(name, value);
    }
}
