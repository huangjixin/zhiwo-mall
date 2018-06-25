package com.fulan.application.task;

import com.fulan.application.common.Consts;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/12 16:28
 */
@Component
@Order(-1)
public class CacheUtil implements CommandLineRunner {

    private ExecutorService executorService = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(new BlackUserTask(BlackUserTask.Type.SAVE,null,null));
        executorService.execute(new ChannelTask());
        executorService.execute(new SystemTask());
        executorService.execute(new FactorTask());
    }
}