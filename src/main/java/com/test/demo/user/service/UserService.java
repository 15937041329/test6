package com.test.demo.user.service;

import com.test.demo.publicres.exception.BusinessException;
import com.test.demo.user.entity.User;

import java.util.List;

/**
 * @program: Test
 * @description: 用户Service
 * @author: Ban shifeng
 * @create: 2019-08-07 10:48
 **/
public interface UserService {
    /**
     * @Description: 根据用户名查询用户
     * @param
     * @return: com.test.demo.user.entity.User
     * @Author: Ban shifeng
     * @Date: 2019/8/7 10:47
     */
    User queryUserByUserName(String userName,String passWord)throws BusinessException;
    /**
     * @Description: 根据id查询用户
     * @param id
     * @return: com.test.demo.user.entity.User
     * @Author: Ban shifeng
     * @Date: 2019/8/7 11:16
     */
    User queryUserById(Integer id) throws BusinessException;
    /**
     * @Description: 新增用户
     * @param user
     * @return: java.lang.Integer
     * @Author: Ban shifeng
     * @Date: 2019/8/7 11:29
     */
    Integer addUser(User user)throws BusinessException;
    /**
     * @Description: 根据id修改用户
     * @return: java.lang.Integer
     * @Author: Ban shifeng
     * @Date: 2019/8/7 11:54
     */
    Boolean updateUserById(User user) throws BusinessException;
    /**
     * @Description: 根据id删除用户
     * @param id
     * @return: java.lang.Integer
     * @Author: Ban shifeng
     * @Date: 2019/8/7 14:17
     */
    Boolean deleteUserById(Integer id) throws BusinessException;
    /**
     * @Description: 查询所有用户
     * @param
     * @return: java.util.List<com.test.demo.user.entity.User>
     * @Author: Ban shifeng
     * @Date: 2019/8/7 17:00
     */
    List<User> queryUsers()throws BusinessException;
}
