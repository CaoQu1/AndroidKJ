package com.example.tapai.view;

import android.app.Application;

import com.example.tapai.contract.Common;

import com.example.tapai.common.ConfigHelper;


public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Common.Config helper = ConfigHelper.getInstance();
        helper.initHelper(this, "http");
        helper.putValue("remoteUrl", "http://172.24.107.73:5001");
//        String url =helper.getValue("remoteUrl");
//        initHttp(url);
    }

    /**
     * @param baseUrl 远程服务器地址
     */
    public void initHttp(String baseUrl) {
//        HttpManager.init(this, baseUrl);
//        HttpManager.getInstance().setOnGetHeadersListener(new HttpManager.OnGetHeadersListener() {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("bear_token", "");
//                return headers;
//            }
//        });
    }
}
