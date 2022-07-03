package org.com.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.com.entity.UserExcel;
import org.com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ExcelListener extends AnalysisEventListener<UserExcel> {
    private static final Logger logger = LoggerFactory.getLogger(UserExcel.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 30;
    List<UserExcel> list = new ArrayList<>();

    private final UserService userService;

    // 构造函数，一定要写，添加到监听中
    public ExcelListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(UserExcel data, AnalysisContext context) {
// TODO Auto-generated method stub
        logger.info("invoke方法被调用");
        logger.info("解析到一条数据:{}", data);
        list.add(data);
// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
// 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
// TODO Auto-generated method stub
        System.out.println("doAfterAllAnalysed方法 被调用");
// 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        logger.info("{}条数据，开始存储数据库！", list.size());
        userService.saveList(list);
        logger.info("存储数据库成功！");
    }
}
