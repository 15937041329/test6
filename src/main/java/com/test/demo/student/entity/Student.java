package com.test.demo.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 学生表(Student)实体类
 *
 * @author makejava
 * @since 2019-09-19 15:11:00
 */
@Data
@ApiModel(value = "学生实体" ,description = "Student Entity" )
public class Student implements Serializable {
    private static final long serialVersionUID = 143235228215745688L;
    //id
    @ApiModelProperty(value = "id")
    private Long id;
    //姓名
    @ApiModelProperty(value = "姓名")
    private String name;
    //性别
    @ApiModelProperty(value = "性别")
    private Integer gender;
    //年龄
    @ApiModelProperty(value = "年龄")
    private Integer age;
    //年级
    @ApiModelProperty(value = "年级")
    private String classname;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date cd;
    //是否删除
    @JsonIgnore
    @ApiModelProperty(value = "是否删除")
    private Integer dr;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Date getCd() {
        return cd;
    }

    public void setCd(Date cd) {
        this.cd = cd;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

}