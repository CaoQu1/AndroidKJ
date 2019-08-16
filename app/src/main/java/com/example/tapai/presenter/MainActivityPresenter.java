package com.example.tapai.presenter;

import com.example.tapai.common.DatabaseRepository;
import com.example.tapai.contract.MainContract;
import com.example.tapai.model.BaseObjectBean;
import com.example.tapai.model.MainModel;
import com.example.tapai.entity.SystemUser;

import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivityPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Model model;


    public MainActivityPresenter(SQLiteDatabase sqLiteDatabase) {
//        String databasePath = Environment.getRootDirectory().getAbsolutePath()+ "/database/";
//        File dir = new File(databasePath);
//        if (!dir.exists())
//            dir.mkdirs();

        DatabaseRepository databaseRepository = new DatabaseRepository(SystemUser.class, null);
        databaseRepository.setSqLiteDatabase(sqLiteDatabase);
        databaseRepository.onCreate();
        model = new MainModel(databaseRepository);
    }

    @Override
    public void login(String userName, String passWord) {
        if (!isViewAttached()) {
            return;
        }
//        mView.showLoading();
//        model.login(userName, passWord, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                mView.onError(e);
//                mView.hideLoading();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    BaseObjectBean<String> objectBean = new BaseObjectBean<>(response.body().string());
//                    mView.onSuccess(objectBean);
//                    model.insert(new SystemUser(objectBean.getResult(), objectBean.getResult()));
//                    mView.hideLoading();
//                    mView.redirectActivity();
//                }
//            }
//        });
        mView.redirectActivity();
    }
}
