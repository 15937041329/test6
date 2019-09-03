package com.test.demo.email.impl;

import com.test.demo.email.service.MailService;
import com.test.demo.publicres.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: Test
 * @description: 邮件业务类
 * @author: Ban shifeng
 * @create: 2019-08-14 11:53
 **/
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) throws BusinessException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 邮件发送者
        message.setTo(to); // 邮件接受者
        message.setSubject(subject); // 主题
        message.setText(content); // 内容
        mailSender.send(message);
    }
}
