package cars24auction.demo.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
    private final static int THREAD_POOL = 3;
    public static ThreadExecutor instance;

    private ExecutorService executorService;
    private ThreadExecutor(){
        executorService = Executors.newFixedThreadPool(THREAD_POOL);
    }

    public static ThreadExecutor getInstance(){
        if(instance==null)
            instance = new ThreadExecutor();
        return instance;
    }

    public Executor getExecutor(){
        return executorService;
    }
}
