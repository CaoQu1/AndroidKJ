package com.example.tapai.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.tapai.presenter.BasePresenter;

import butterknife.ButterKnife;

public class BaseMvpActivity<P extends BasePresenter> extends Activity {

    protected P mPresenter;
    protected View pView;
    protected SQLiteDatabase sqLiteDatabase;
    protected AlertDialog alertDialog;
    protected Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pView = this.getWindow().getDecorView();
        StatusBarUtil.setTransparent(this);
        sqLiteDatabase = openOrCreateDatabase("Test.db", MODE_PRIVATE, null);
        initView();
        initControl();
        ButterKnife.bind(this);
        handler = new Handler();
    }

    protected void initView() {
    }

    protected void initControl() {
    }

    protected void createDialog(String title, String message) {
        alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .create();
        alertDialog.show();
    }
}
