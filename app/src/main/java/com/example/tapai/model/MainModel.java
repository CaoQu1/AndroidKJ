package com.example.tapai.model;

import com.example.tapai.common.ConfigHelper;
import com.example.tapai.contract.Common;
import com.example.tapai.contract.MainContract;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Model层
 */
public class MainModel extends BaseRepository implements MainContract.Model {

    public <T>  MainModel(Common.Repository<T> repository)
    {
        super(repository);
    }

    /**
     * @param userName 用户名
     * @param passWord 密码
     * @param callback 回调函数
     */
    @Override
    public void login(String userName, String passWord, Callback callback) {

        OkHttpClient client = new OkHttpClient();
        String url = ConfigHelper.getInstance().getValue("remoteUrl");
        Request request = new Request.Builder()
                .url(url + "/api/user/login?userName" + userName + "&passWord=" + passWord)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
