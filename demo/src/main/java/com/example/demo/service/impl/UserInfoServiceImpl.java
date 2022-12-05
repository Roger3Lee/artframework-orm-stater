package com.example.demo.service.impl;

import com.example.demo.dataobject.UserInfoDo;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.UserInfoQueryDto;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public int insert(UserInfoDto entity) {
        return userInfoRepository.insert(UserInfoDto.UserInfoConvert.INSTANCE.convert(entity));
    }

    @Override
    public UserInfoDo get(UserInfoQueryDto dto) {
        return userInfoRepository.get(dto);
    }

    @Override
    public List<UserInfoDo> query(UserInfoQueryDto dto) {
        return userInfoRepository.query(dto);
    }

}
