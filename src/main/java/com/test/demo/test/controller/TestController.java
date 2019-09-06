package com.test.demo.test.controller;

import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.publicres.jwt.JwtToken;
import com.test.demo.user.entity.User;
import com.test.demo.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @Resource
    private UserService userService;
    @PostMapping("test")
    public ApiResponseEntity test(String userName, String passWord){
        try {
            User user = userService.queryUserByUserName(userName, passWord);
            String token = JwtToken.sign(user.getId(),60L*1000L*30L);
            return ApiResponseEntity.ok().putDataValue("token",token);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.error("error");
        }
    }
    @GetMapping("test2")
    public ApiResponseEntity test2(String token){
        try {
            Integer id = JwtToken.unsign(token,Integer.class);
            return ApiResponseEntity.ok().putDataValue("id",id);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.error("error");
        }

    }
}
