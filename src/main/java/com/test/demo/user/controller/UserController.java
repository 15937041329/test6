package com.test.demo.user.controller;

import com.alibaba.fastjson.JSON;
import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.publicres.jwt.JwtToken;
import com.test.demo.user.entity.User;
import com.test.demo.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: Test
 * @description: 用户接口
 * @author: Ban shifeng
 * @create: 2019-08-07 10:49
 **/
@RestController
@Slf4j
@Api(tags = "user接口")
@RequestMapping("/v1")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @param userName
     * @Description: 用户登录
     * @return: com.test.demo.publicres.entity.ApiResponseEntity
     * @Author: Ban shifeng
     * @Date: 2019/8/7 10:56
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiResponseEntity login(@RequestParam("userName") String userName,
                                   @RequestParam("passWord") String passWord) {
        try {
            User user = userService.queryUserByUserName(userName, passWord);
            if (user == null){
                return ApiResponseEntity.customerError();
            }
            if (!user.getPassWord().equals(passWord)) {
                return ApiResponseEntity.customerError();
            }
            //给用户jwt加密生成token，有效期半小时
            String token = JwtToken.sign(user.getId(), 60L * 1000L * 30L);
            return ApiResponseEntity.ok().putDataValue("user", user).putDataValue("token", token);
        } catch (Exception e) {
            log.error("用户登录异常" + e);
            return ApiResponseEntity.error("用户登录异常：" + e.getMessage());
        }
    }

    /**
     * @param
     * @Description: 查询所有用户
     * @return: com.test.demo.publicres.entity.ApiResponseEntity
     * @Author: Ban shifeng
     * @Date: 2019/8/7 17:05
     */
    @ApiOperation("查询所有用户")
    @GetMapping("/user")
    public ApiResponseEntity getUser() {
        try {
            List<User> users = userService.queryUsers();
            if (users == null) {
                return ApiResponseEntity.notFound();
            }
            return ApiResponseEntity.ok().putDataValue("users", users);
        } catch (Exception e) {
            log.error("查询所有用户异常" + e);
            return ApiResponseEntity.error("查询所有用户异常：" + e.getMessage());
        }
    }

    /**
     * @param id
     * @Description: 根据id查询用户
     * @return: com.test.demo.publicres.entity.ApiResponseEntity
     * @Author: Ban shifeng
     * @Date: 2019/8/7 11:20
     */
    @ApiOperation("根据id查询用户")
    @GetMapping("/user/{id}")
    public ApiResponseEntity getUserById(@PathVariable("id") Integer id) {
        try {
            User user = userService.queryUserById(id);
            if (user == null) {
                return ApiResponseEntity.notFound();
            }
            return ApiResponseEntity.ok().putDataValue("user", user);
        } catch (Exception e) {
            log.error("根据id查询用户异常" + e);
            return ApiResponseEntity.error("根据id查询用户异常：" + e.getMessage());
        }
    }

    /**
     * @param userJson
     * @Description: 新增用户
     * @return: com.test.demo.publicres.entity.ApiResponseEntity
     * @Author: Ban shifeng
     * @Date: 2019/8/7 11:48
     */
    @ApiOperation("新增用户")
    @PostMapping("/user")
    public ApiResponseEntity addUser(@RequestParam(value = "userJson") String userJson) {
        try {
            log.info("=======================>userJson："+userJson);
            User addUser = JSON.parseObject(userJson, User.class);
            log.info("=======================>addUser："+addUser);
            Integer id = userService.addUser(addUser);
            User user = userService.queryUserById(id);
            if (user == null) {
                return ApiResponseEntity.notFound();
            }
            return ApiResponseEntity.created().putDataValue("user", user);
        } catch (Exception e) {
            log.error("新增用户异常" + e);
            return ApiResponseEntity.error("新增用户异常：" + e.getMessage());
        }
    }

    /**
     * @param userJson
     * @Description: 根据id修改用户
     * @return: com.test.demo.publicres.entity.ApiResponseEntity
     * @Author: Ban shifeng
     * @Date: 2019/8/7 12:13
     */
    @ApiOperation("根据id修改用户")
    @PutMapping("/user")
    public ApiResponseEntity modifyUser(@RequestParam(value = "userJson") String userJson) {
        try {
            log.info("=================================>userJson："+userJson);
            User addUser = JSON.parseObject(userJson, User.class);
            if (addUser.getId() == null) {
                return ApiResponseEntity.forbidden();
            }
            userService.updateUserById(addUser);
            User user = userService.queryUserById(addUser.getId());
            return ApiResponseEntity.created().putDataValue("user", user);
        } catch (Exception e) {
            log.error("根据id修改用户异常" + e);
            return ApiResponseEntity.error("根据id修改用户异常：" + e.getMessage());
        }
    }

    /**
     * @param id
     * @Description: 根据id删除用户
     * @return: com.test.demo.publicres.entity.ApiResponseEntity
     * @Author: Ban shifeng
     * @Date: 2019/8/7 14:26
     */
    @ApiOperation("根据id删除用户")
    @DeleteMapping("/user/{id}")
    public ApiResponseEntity delUser(@PathVariable("id") Integer id) {
        try {
            if (userService.deleteUserById(id)) {
                return ApiResponseEntity.noContent();
            }
            return ApiResponseEntity.badRequest();
        } catch (Exception e) {
            log.error("根据id删除用户异常" + e);
            return ApiResponseEntity.error("根据id删除用户异常：" + e.getMessage());
        }
    }
}
