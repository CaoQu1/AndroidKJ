package com.example.tapai.contract;
import android.content.Intent;

import  com.uber.autodispose.AutoDisposeConverter;

public interface BaseView {
    void  showLoading();

    void  hideLoading();

    void  onError(Throwable throwable);

    void  redirectActivity();

    <T> AutoDisposeConverter<T> bindAutoDispose();
}
