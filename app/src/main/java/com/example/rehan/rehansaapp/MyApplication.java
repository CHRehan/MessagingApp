package com.example.rehan.rehansaapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Innovetor on 2015-07-25.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       // Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BzaLTfd7Z0OVvTOY81G2XwKKautuhib82pGzlBMw", "OyLPEqFsLw5nnx5eZWeXuUOIcH5FvkzBgjUrQyuw");


    }
}
