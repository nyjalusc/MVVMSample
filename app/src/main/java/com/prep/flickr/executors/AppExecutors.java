package com.prep.flickr.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    // ScheduledExecutorService allows execution of a Runnable after a specified amount of delay
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
    private AppExecutors() {

    }

    public static AppExecutors getInstance() {
        if (instance == null) {
            synchronized (AppExecutors.class) {
                instance = new AppExecutors();
            }
        }
        return instance;
    }

    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }


}