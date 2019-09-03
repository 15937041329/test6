package com.test.demo.qrcode.controller;

import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.qrcode.tools.QRCodeUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: test
 * @description: 二维码接口
 * @author: Ban shifeng
 * @create: 2019-09-03 11:24
 **/
@Api(tags = "qrcode接口")
@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @GetMapping()
    public ApiResponseEntity qrcode(String content){
        String loginPath = "C:\\Users\\Administrator\\Desktop\\logo.jpg";
        String createPath = "C:\\Users\\Administrator\\Desktop\\";
        String aa = QRCodeUtil.zxingCodeCreate(content,createPath,500,loginPath);
        return ApiResponseEntity.ok();
    }
}
