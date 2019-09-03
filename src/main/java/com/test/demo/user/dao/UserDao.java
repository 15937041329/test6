package com.test.demo.user.dao;

import com.test.demo.publicres.exception.BusinessException;
import com.test.demo.user.entity.SixLevel;
import com.test.demo.user.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: Test
 * @description: 用户dao
 * @author: Ban shifeng
 * @create: 2019-08-07 10:43
 **/
public interface UserDao {
    /**
     * @Description: 根据用户名查询用户
     * @param
     * @return: com.test.demo.user.entity.User
     * @Author: Ban shifeng
     * @Date: 2019/8/7 10:47
     */
    @Select("select * from user where userName=#{userName}")
    User queryUserByUserName(@Param("userName") String userName)throws BusinessException;
    /**
     * @Description: 根据id查询用户
     * @param id
     * @return: com.test.demo.user.entity.User
     * @Author: Ban shifeng
     * @Date: 2019/8/7 11:16
     */
    @Select("select * from user where id=#{id}")
    User queryUserById(@Param("id") Integer id) throws BusinessException;
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
    Integer updateUserById(User user) throws BusinessException;
    /**
     * @Description: 根据id删除用户
     * @param id
     * @return: java.lang.Integer
     * @Author: Ban shifeng
     * @Date: 2019/8/7 14:17
     */
    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(@Param("id") Integer id) throws BusinessException;
    /**
     * @Description: 查询所有用户
     * @param
     * @return: java.util.List<com.test.demo.user.entity.User>
     * @Author: Ban shifeng
     * @Date: 2019/8/7 17:00
     */
    @Select("select * from user")
    List<User> queryUsers()throws BusinessException;
}
