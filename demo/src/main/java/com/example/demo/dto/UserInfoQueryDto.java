package com.example.demo.dto;

import com.artframework.mybatisplus.extension.model.dto.BaseDTO;
import com.example.demo.vo.UserInfoQueryVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoQueryDto extends BaseDTO {
    private Long id;
    private String userName;

    @Mapper
    public interface UserInfoQueryConvert {
        UserInfoQueryDto.UserInfoQueryConvert INSTANCE = Mappers.getMapper(UserInfoQueryDto.UserInfoQueryConvert.class);

        UserInfoQueryDto convertToDto(UserInfoQueryVO queryVO);
    }
}
