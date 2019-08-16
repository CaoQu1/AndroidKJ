package com.example.tapai.presenter;

import com.example.tapai.contract.BaseView;

public class BasePresenter<V extends BaseView> {

    protected V mView;

    public void attachView(V mView) {
        this.mView = mView;
    }

    public void detachView() {
        this.mView = null;
    }

    public boolean isViewAttached() {
        return this.mView != null;
    }
}
