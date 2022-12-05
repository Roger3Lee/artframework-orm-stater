package com.artframework.mybatisplus.extension.spring;

import com.artframework.mybatisplus.extension.model.vo.BaseVO;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 *
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/7
 **/
public class CustomizeExtendedServletRequestDataBinder extends ExtendedServletRequestDataBinder {

    private static final String PREFIX_EXT_REGEX = "^(feature\\[)[A-Za-z0-9]+(\\])$";
    private static final String PREFIX_EXT = "feature[%s]";

    public CustomizeExtendedServletRequestDataBinder(Object target) {
        super(target);
    }

    public CustomizeExtendedServletRequestDataBinder(Object target, String objectName) {
        super(target, objectName);
    }


    @Override
    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);

        if (BaseVO.class.isAssignableFrom(this.getTarget().getClass())) {
            List<PropertyValue> propertyValueList = mpvs.getPropertyValueList();

            for (int i = propertyValueList.size() - 1; i >= 0; i--) {
                PropertyValue pv = propertyValueList.get(i);
                if (null == this.getPropertyAccessor().getPropertyTypeDescriptor(pv.getName())
                        && !PREFIX_EXT_REGEX.matches(pv.getName())) {
                    propertyValueList.add(new PropertyValue(String.format(PREFIX_EXT, pv.getName()), pv.getValue()));
                }
            }
        }

    }
}
