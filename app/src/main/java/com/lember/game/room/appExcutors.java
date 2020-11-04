package com.lember.game.room;

import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;




import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public class appExcutors {
    private static final Object LOCK=new Object();
    private static appExcutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    public appExcutors(Executor diskIO, Executor mainThread,Executor networkIO) {
        this.diskIO=diskIO;
        this.mainThread=mainThread;
        this.networkIO=networkIO;
    }
    public static appExcutors getsInstance(){
        if(sInstance==null){
            synchronized (LOCK){
                sInstance=new appExcutors(Executors.newSingleThreadExecutor(),Executors.newFixedThreadPool(3),new MainThreadExecutor());
            }
        }
        return  sInstance;
    }
    public Executor diskIO(){return diskIO;}
    public Executor mainThread(){return mainThread;}
    public Executor networkIO(){return networkIO;}

    private static class MainThreadExecutor implements Executor{
        private Handler mainTreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable runnable){mainTreadHandler.post(runnable);}


    }
}
