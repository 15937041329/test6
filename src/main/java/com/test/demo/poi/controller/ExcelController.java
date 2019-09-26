package com.test.demo.poi.controller;

import com.alibaba.excel.EasyExcel;
import com.test.demo.poi.tools.DemoDataListener;
import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.user.entity.User;
import com.test.demo.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

/**
 * @program: test
 * @description: Excel接口
 * @author: Ban shifeng
 * @create: 2019-09-03 09:50
 **/
@Api(tags = "导入导出Excel接口")
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Resource
    private UserService userService;
    /**
     * 文件下载
     * <p>1. 创建excel对应的实体对象 参照{@link User}
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @ApiOperation("导出下载")
    @GetMapping("exportDownload")
    public void download(HttpServletResponse response){
        try {
            List<User> userList = userService.queryUsers();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //获得随机数
            StringBuilder num = new StringBuilder();
            Random random = new Random();
            for (int i=0; i<8;i++){
                num.append(random.nextInt(10));
            }
            response.setHeader("Content-disposition", "attachment;filename="+num+".xlsx");
            EasyExcel.write(response.getOutputStream(), User.class).sheet("用户数据").doWrite(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ApiOperation("读取excel")
    @GetMapping("readExcel")
    public ApiResponseEntity readExcel(){
        try {
            String filePath = "C:\\Users\\Administrator\\Desktop\\03348551.xlsx";
            DemoDataListener demoDataListener = new DemoDataListener();
            EasyExcel.read(filePath,User.class,demoDataListener).sheet().doRead();
            return ApiResponseEntity.ok();
        }catch (Exception e){
            log.error("读取excel失败："+e);
            return ApiResponseEntity.error("读取excel失败："+e.getMessage());
        }

    }
}
