package com.example.tapai.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tapai.contract.Common;


public class ConfigHelper implements Common.Config {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static ConfigHelper instance;


    private ConfigHelper() {
    }

    public static ConfigHelper getInstance() {
        try {
            if (instance == null) {
                synchronized (ConfigHelper.class) {
                    if (instance == null) {
                        instance = new ConfigHelper();
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return instance;
    }

    public void initHelper(Context context, String name) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(name, 0);
        editor = sharedPreferences.edit();
    }

    @Override
    public void putValue(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    public String getValue(String key) {
        return sharedPreferences.getString(key, null);
    }
}
