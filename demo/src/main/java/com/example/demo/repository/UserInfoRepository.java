package com.example.demo.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dataobject.UserInfoDo;
import com.example.demo.dto.UserInfoQueryDto;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
@Repository
public class UserInfoRepository {
    @Autowired
    private UserInfoMapper mapper;

    public int insert(UserInfoDo entity) {
        return mapper.insert(entity);
    }

    public UserInfoDo get(UserInfoQueryDto query) {
        return mapper.selectById(query.getId());
    }

    public List<UserInfoDo> query(UserInfoQueryDto query) {
        LambdaQueryWrapper<UserInfoDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoDo::getUserName, query.getUserName());
        return mapper.selectList(queryWrapper);
    }

    public int save(UserInfoDo entity) {
        return mapper.updateById(entity);
    }
}
