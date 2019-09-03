package com.test.demo.poi.controller;

import com.test.demo.poi.tools.ExcelUtil;
import com.test.demo.publicres.entity.ApiResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: test
 * @description: Excel接口
 * @author: Ban shifeng
 * @create: 2019-09-03 09:50
 **/
@Api(tags = "Excel接口")
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @ApiOperation("导出Excel")
    @GetMapping()
    public ApiResponseEntity excel(){
        String filePath = "C:\\Users\\Administrator\\Desktop\\测试.xlsx";
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        List<String> head = Arrays.asList("表头1", "表头2", "表头3");
        ExcelUtil.writeBySimple(filePath,data,head);
        return ApiResponseEntity.ok();
    }
}
