package com.test.demo.xxljob.config.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * @program: test
 * @description: 任务Handler示例（Bean模式）
 * @author: Ban shifeng
 * @create: 2019-09-20 11:26
 **/
@JobHandler(value="vkJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) {

        XxlJobLogger.log("XXL-JOB, Hello World.");
        return SUCCESS;
    }
}
