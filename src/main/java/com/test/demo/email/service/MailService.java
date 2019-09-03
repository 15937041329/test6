package com.test.demo.email.service;

import com.test.demo.publicres.exception.BusinessException;

/**
 * @program: Test
 * @description: 邮件service
 * @author: Ban shifeng
 * @create: 2019-08-14 11:52
 **/
public interface MailService {
    /**
     * @Description: 发送邮件
     * @param to
     * @param subject
     * @param content
     * @return: void
     * @Author: Ban shifeng
     * @Date: 2019/8/14 11:53
     */
    void sendSimpleMail(String to, String subject, String content)throws BusinessException ;
}
