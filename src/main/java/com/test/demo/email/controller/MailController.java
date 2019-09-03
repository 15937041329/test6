package com.test.demo.email.controller;

import com.test.demo.email.service.MailService;
import com.test.demo.publicres.entity.ApiResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: Test
 * @description: mail接口
 * @author: Ban shifeng
 * @create: 2019-08-14 11:56
 **/
@Api(tags = "mail接口")
@RestController
@Slf4j
public class MailController {
    @Resource
    private MailService mailService;

    /**
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @ApiOperation("发送邮件")
    @GetMapping("/mail")
    ApiResponseEntity sendMail(@RequestParam("to") String to,
                               @RequestParam("subject") String subject,
                               @RequestParam("content") String content){
        try {
            mailService.sendSimpleMail(to, subject, content);
            return ApiResponseEntity.ok();
        }catch (Exception e){
            log.error("发送邮件失败："+e);
            return ApiResponseEntity.error("发送邮件失败："+e.getMessage());
        }
    }
}
