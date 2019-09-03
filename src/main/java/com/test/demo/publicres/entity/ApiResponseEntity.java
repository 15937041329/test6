package com.test.demo.publicres.entity;
 
import java.util.HashMap;
import java.util.Map;

/**
 * Api请求响应实体
 * @author Ban shifeng
 * @date 2019-1-9
 *
 */
public class ApiResponseEntity {
 
    public static final String ERRORS_KEY = "errors";
 
    private final String resultCode;
    private final String message;
    private final Map<String, Object> data = new HashMap<>();

    public String getMessage() {
        return message;
    }
 
    public String getResultCode() {
        return resultCode;
    }
 
    public Map<String, Object> getData() {
        return data;
    }
 
    public ApiResponseEntity putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }
    private ApiResponseEntity(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
 
    public static ApiResponseEntity ok() {
        return new ApiResponseEntity("200", "Ok");
    }
    public static ApiResponseEntity error(String message) {
        return new ApiResponseEntity("999", message);
    }
    public static ApiResponseEntity notFound() {
        return new ApiResponseEntity("404", "资源未找到");
    }
    public static ApiResponseEntity created() {
        return new ApiResponseEntity("201", "资源新建成功");
    }
    public static ApiResponseEntity noContent() {
        return new ApiResponseEntity("204", "资源删除成功");
    }
    public static ApiResponseEntity badRequest() {
        return new ApiResponseEntity("500", "错误的请求");
    }
    public static ApiResponseEntity forbidden() {
        return new ApiResponseEntity("400", "非法请求，请检查参数");
    }
 
    public static ApiResponseEntity unauthorized() {
        return new ApiResponseEntity("401", "未授权的");
    }
 
    public static ApiResponseEntity serverInternalError() {
        return new ApiResponseEntity("999", "Server Internal Error");
    }
 
    public static ApiResponseEntity customerError() {
        return new ApiResponseEntity("999", "用户无效请检查登录用户名和密码");
    }
    
    public static ApiResponseEntity ExpiredError() {
        return new ApiResponseEntity("999", "token过期请重新登录获取");
    }
    
    public static ApiResponseEntity setOrtherMessage(String message) {
    	 return new ApiResponseEntity("999", "发生异常：" + message);
    }
}
