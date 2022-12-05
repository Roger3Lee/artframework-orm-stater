package com.example.demo.dataobject;

import com.artframework.mybatisplus.extension.model.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/15
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "tbl_user_info",autoResultMap = true)
public class UserInfoDo extends BaseDO {
    /**
     *
     */
    @TableId
    @TableField("id")
    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    @TableField("password")
    private String password;
}
