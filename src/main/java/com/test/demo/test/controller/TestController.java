package com.test.demo.test.controller;

import com.alibaba.fastjson.JSON;
import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.publicres.tools.HttpClientUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("test")
    public ApiResponseEntity test(){
        String result =  HttpClientUtil.doGet("https://www.bsfwebroot.jdvker.com:8443/admin/login?passWord=1&userName=1");
        return ApiResponseEntity.ok().putDataValue("result:",JSON.parseObject(result));
    }
}
