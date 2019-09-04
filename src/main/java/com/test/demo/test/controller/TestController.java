package com.test.demo.test.controller;

import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.user.entity.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Test
 * @description: 测试
 * @author: Ban shifeng
 * @create: 2019-08-08 15:25
 **/
@Api(tags = "test接口")
@RestController
@Slf4j
public class TestController {
    @PostMapping("test")
    public ApiResponseEntity test(@RequestBody(required = false) User user){
        log.info("=======================================>user："+user);
        return ApiResponseEntity.ok();
    }
}
