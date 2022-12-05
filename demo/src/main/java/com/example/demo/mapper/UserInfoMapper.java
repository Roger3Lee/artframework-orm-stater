package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dataobject.UserInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
@Mapper
@Component
public interface UserInfoMapper extends BaseMapper<UserInfoDo> {

}
