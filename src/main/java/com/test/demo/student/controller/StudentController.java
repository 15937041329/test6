package com.test.demo.student.controller;

import com.test.demo.student.entity.Student;
import com.test.demo.student.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生表(Student)表控制层
 *
 * @author makejava
 * @since 2019-09-19 15:11:00
 */
@RestController
@Api(tags = "学生接口")
@RequestMapping("student")
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Student selectOne(Long id) {
        return this.studentService.queryById(id);
    }
}