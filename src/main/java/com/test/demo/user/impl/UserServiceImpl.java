package com.test.demo.user.impl;

import com.test.demo.publicres.exception.BusinessException;
import com.test.demo.user.dao.UserDao;
import com.test.demo.user.entity.User;
import com.test.demo.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: Test
 * @description: 用户业务类
 * @author: Ban shifeng
 * @create: 2019-08-07 10:48
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User queryUserByUserName(String userName, String passWord) throws BusinessException {
        try {
            return userDao.queryUserByUserName(userName);
        } catch (BusinessException e) {
            throw new BusinessException("登录失败");
        }
    }

    @Override
    public User queryUserById(Integer id) throws BusinessException {
        try {
            return userDao.queryUserById(id);
        } catch (BusinessException e) {
            throw new BusinessException("根据id查询用户失败");
        }
    }

    @Override
    public Integer addUser(User user) throws BusinessException {
        try {
            userDao.addUser(user);
            return user.getId();
        } catch (BusinessException e) {
            throw new BusinessException("新增用户失败");
        }
    }

    @Override
    public Boolean updateUserById(User user) throws BusinessException {
        try {
            if (user.getId() == null) {
                throw new BusinessException("检查参数id");
            }
            if (userDao.updateUserById(user) > 0) {
                return true;
            }
            return false;
        } catch (BusinessException e) {
            throw new BusinessException("修改用户失败");
        }
    }

    @Override
    public Boolean deleteUserById(Integer id) throws BusinessException {
        try {
            if (id == null) {
                throw new BusinessException("检查参数");
            }
            if (userDao.deleteUserById(id) > 0) {
                return true;
            }
            return false;
        } catch (BusinessException e) {
            throw new BusinessException("删除用户失败");
        }
    }

    @Override
    public List<User> queryUsers() throws BusinessException {
        try {
            return userDao.queryUsers();
        } catch (BusinessException e) {
            throw new BusinessException("查询所有用户失败");
        }
    }
}
