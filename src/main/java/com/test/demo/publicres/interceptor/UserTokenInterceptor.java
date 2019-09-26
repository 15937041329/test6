package com.test.demo.publicres.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.publicres.entity.ApiResponseEntity;
import com.test.demo.publicres.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program: Test
 * @description: 用户Token拦截器
 * @author: Ban shifeng
 * @create: 2019-08-07 15:06
 **/
@Slf4j
public class UserTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            log.info("===========================>token："+token);
            if (token != null){
                Integer id = JwtToken.unsign(token,Integer.class);
                log.info("===========================>id："+id);
                if (id != null){
                    return true;
                }
            }
            ApiResponseEntity responseData = ApiResponseEntity.forbidden();
            response.setContentType("application/json; charset=utf-8");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(responseData);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (Exception e) {

            ApiResponseEntity responseData = null;

            if (e instanceof TokenExpiredException) {
                responseData = ApiResponseEntity.expiredError();

            } else {
                responseData = ApiResponseEntity.setOrtherMessage(e.getMessage());
            }

            response.setContentType("application/json; charset=utf-8");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(responseData);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();

            return false;
        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
