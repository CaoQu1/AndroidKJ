package com.example.tapai.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;


import android.os.Handler;
import android.os.Looper;
import android.view.View;

import android.widget.Button;


import android.widget.EditText;
import android.widget.Toast;


import com.dyhdyh.widget.loading.bar.LoadingBar;
import com.example.tapai.contract.MainContract;
import com.example.tapai.model.BaseObjectBean;
import com.example.tapai.presenter.MainActivityPresenter;
import com.uber.autodispose.AutoDisposeConverter;


import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainActivityPresenter> implements MainContract.View {

    @BindView(R.id.btnDownload)
    Button button;

    @BindView(R.id.user_name)
    EditText userNameTxt;

    @BindView(R.id.pass_word)
    EditText passWordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    private String getUserName() {
        return userNameTxt.getText().toString().trim();
    }

    private String getPassWord() {
        return passWordTxt.getText().toString().trim();
    }

    @OnClick(R.id.btnDownload)
    public void onViewClicked() {
        if (getUserName().isEmpty() || getPassWord().isEmpty()) {
            Toast.makeText(this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(getUserName(), getPassWord());
    }

    @Override
    public void initView() {
        super.initView();
        mPresenter = new MainActivityPresenter(sqLiteDatabase);
        mPresenter.attachView(this);
    }

    @Override
    public void initControl() {
        super.initControl();
        userNameTxt = findViewById(R.id.user_name);
        passWordTxt = findViewById(R.id.pass_word);
        button = findViewById(R.id.btnDownload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getUserName().isEmpty() || getPassWord().isEmpty()) {
                    Toast.makeText(MainActivity.this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(getUserName(), getPassWord());
            }
        });
    }

    @Override
    public void showLoading() {
        LoadingBar.make(pView).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingBar.cancel(pView);
            }
        }, 2000);
    }

    @Override
    public void hideLoading() {
        LoadingBar.cancel(pView);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(BaseObjectBean<String> bean) {
        try {
            String result = bean.getResult();
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }

    /**
     * 当活动即将可见时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void redirectActivity() {
//        handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
