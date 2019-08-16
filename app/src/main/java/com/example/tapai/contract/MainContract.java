package com.example.tapai.contract;


import com.example.tapai.model.BaseObjectBean;


import okhttp3.Callback;

public interface MainContract {

    interface Model extends BaseModel {
        void login(String userName, String passWord, Callback callback);
    }

    interface View extends BaseView {

        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean<String> bean);
    }

    interface Presenter extends BasePresenter {
        void login(String userName, String passWord);
    }
}
