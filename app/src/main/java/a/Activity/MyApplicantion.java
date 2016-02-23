package a.Activity;

import android.app.Application;

import com.google.gson.Gson;

import BasTool.MyOptions;

/**
 * Created by Administrator on 2016/2/22.
 */
public class MyApplicantion extends Application {
    public static Gson gson;
    private static MyApplicantion mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        gson = new Gson();
        MyOptions.initImageLoader(getApplicationContext());
    }
}
