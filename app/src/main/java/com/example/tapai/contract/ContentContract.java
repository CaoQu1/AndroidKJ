package com.example.tapai.contract;

import com.example.tapai.model.BaseObjectBean;
import com.example.tapai.entity.QrCode;

import java.util.List;

public interface ContentContract {
    interface Model extends BaseModel {
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
        List<QrCode> getList();
    }
}
