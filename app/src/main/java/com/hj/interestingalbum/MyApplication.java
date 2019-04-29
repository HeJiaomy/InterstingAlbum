package com.hj.interestingalbum;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static MyApplication application;

    public static MyApplication getContext(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application= this;
    }

}
