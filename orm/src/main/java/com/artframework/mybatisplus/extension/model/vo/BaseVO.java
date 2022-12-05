package com.artframework.mybatisplus.extension.model.vo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * VO类的基类
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/9
 **/
@Data
public class BaseVO {

    @JsonIgnore
    private Map<String, Object> feature=new HashMap();

    @JsonAnySetter
    public void setFeature(String key, Object value) { feature.put(key, value); }

    @JsonAnyGetter
    public Map<String, Object> getFeature(){
        return feature;
    }
}
