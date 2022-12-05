package com.example.demo.dto;

import com.artframework.mybatisplus.extension.model.dto.BaseDTO;
import com.example.demo.dataobject.UserInfoDo;
import com.example.demo.vo.UserInfoVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoDto extends BaseDTO {
    private String userName;
    private String nickName;
    private String password;


    @Mapper
   public interface UserInfoConvert {
        UserInfoConvert INSTANCE = Mappers.getMapper(UserInfoConvert.class);

        UserInfoVO convertToVO(UserInfoDto dto);

        UserInfoVO convertToVO(UserInfoDo userInfoDo);

        UserInfoDto convert(UserInfoVO vo);

        UserInfoDo convert(UserInfoDto dto);
    }
}
