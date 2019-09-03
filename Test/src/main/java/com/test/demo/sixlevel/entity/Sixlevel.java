package com.test.demo.sixlevel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ban shifeng
 * @since 2019-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Sixlevel对象", description="")
public class Sixlevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目编码")
    @TableField("projectCode")
    private String projectCode;

    @ApiModelProperty(value = "一级")
    private Long one;

    @ApiModelProperty(value = "二级")
    private Long two;

    @ApiModelProperty(value = "三级")
    private Long three;

    @ApiModelProperty(value = "四级")
    private Long four;

    @ApiModelProperty(value = "五级")
    private Long five;

    @ApiModelProperty(value = "六级")
    private Long six;

    @ApiModelProperty(value = "七级")
    private Long seven;

    @ApiModelProperty(value = "八级")
    private Long eight;

    @ApiModelProperty(value = "九级")
    private Long nine;

    @ApiModelProperty(value = "十级")
    private Long ten;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime cd;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime ud;

    @ApiModelProperty(value = "是否删除")
    private Integer dr;


}
