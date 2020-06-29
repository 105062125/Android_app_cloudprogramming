package com.example.cloudprog.viewmodels;

import android.app.Application;

import com.example.funkitapp.viewmodels.Injection;

/**
 * Application class responsible for initializing singletons and other
 * common components
 */
public class CloudApp extends Application {
    /**
     * The name of the field used to hold the ID of the note when information is passed
     * internally between activities.
     */
    public static String ITEM_ID = "noteId";

    @Override
    public void onCreate() {
        super.onCreate();
        Injection.initialize(getApplicationContext());
    }
}
