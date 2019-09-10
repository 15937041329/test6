package com.test.demo.user.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: Test
 * @description: 用户实体类
 * @author: Ban shifeng
 * @create: 2019-08-07 10:44
 **/
@Data
@ApiModel(value = "用户实体" , description = "User Entity")
public class User{
    @ApiModelProperty(value = "主键id" )
    @ExcelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "用户名" )
    @ExcelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码" )
    @ExcelProperty(value = "密码")
    private String passWord;
    @ApiModelProperty(value = "地址" )
    @ExcelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "电话" )
    @ExcelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "描述" )
    @ExcelProperty(value = "描述")
    private String description;
}
