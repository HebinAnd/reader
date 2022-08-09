package com.hebin.reader.task;

import com.hebin.reader.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComputeTask {

    //秒 分 时 日 星期 年
    Logger logger = LoggerFactory.getLogger(ComputeTask.class);

    @Resource
    BookService bookService;

    @Scheduled(cron = "0 10 * * * ?")
    public void updateScore(){
        bookService.updateScore();
        logger.info("有更新图书评分");
    }
}
