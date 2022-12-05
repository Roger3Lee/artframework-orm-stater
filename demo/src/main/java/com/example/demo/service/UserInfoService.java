package com.example.demo.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dataobject.UserInfoDo;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.UserInfoQueryDto;

import java.util.List;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
public interface UserInfoService {
    int insert(UserInfoDto entity);

    UserInfoDo get(UserInfoQueryDto dto);

    List<UserInfoDo> query(UserInfoQueryDto dto);
}
