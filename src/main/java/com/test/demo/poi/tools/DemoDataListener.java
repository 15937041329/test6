package com.test.demo.poi.tools;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.test.demo.user.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: test
 * @description: 读取数据监听器
 * @author: Ban shifeng
 * @create: 2019-09-19 11:51
 **/
@Slf4j
public class DemoDataListener extends AnalysisEventListener<User> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 6;
    private List<User> userList = new ArrayList<>();
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(user));
        userList.add(user);
        if (userList.size() >= BATCH_COUNT) {
            saveData();
            userList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //saveData();
        log.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", userList.size());
        log.info("存储数据库成功！");
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
