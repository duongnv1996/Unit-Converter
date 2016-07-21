package com.duongkk.unitconverter.application;

import android.app.Application;

import com.duongkk.unitconverter.utils.DataStore;

/**
 * Created by MyPC on 7/12/2016.
 */
public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataStore.init(getApplicationContext());
    }
}
