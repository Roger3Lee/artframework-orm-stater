package com.example.demo.controller;

import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.UserInfoQueryDto;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.UserInfoQueryVO;
import com.example.demo.vo.UserInfoQueryVO1;
import com.example.demo.vo.UserInfoVO;
import com.example.demo.vo.UserStateChangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    @PostMapping
    public String save(@RequestBody UserInfoVO userInfoVO) {
        UserInfoDto dto = UserInfoDto.UserInfoConvert.INSTANCE.convert(userInfoVO);
        service.insert(dto);
        return "success";
    }

    @GetMapping("/query")
    public List<UserInfoVO> query(UserInfoQueryVO1 queryVO) {
        UserInfoQueryVO vo = new UserInfoQueryVO();
        vo.setUserName(queryVO.getFeature().get("userName").toString());
        UserInfoQueryDto dto = UserInfoQueryDto.UserInfoQueryConvert.INSTANCE.INSTANCE.convertToDto(vo);
        return service.query(dto).stream().map(UserInfoDto.UserInfoConvert.INSTANCE::convertToVO).collect(Collectors.toList());
    }
}
