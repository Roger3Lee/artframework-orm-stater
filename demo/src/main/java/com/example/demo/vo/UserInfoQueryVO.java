package com.example.demo.vo;

import com.artframework.mybatisplus.extension.model.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoQueryVO extends BaseVO {
    private int id;
    private String userName;
}
