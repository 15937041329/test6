package com.test.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(value = "com.test.demo.*.dao")		//扫描dao
@ServletComponentScan
@EnableTransactionManagement        //开启事务
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean("duridDatasource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource() { return new DruidDataSource(); }

}
