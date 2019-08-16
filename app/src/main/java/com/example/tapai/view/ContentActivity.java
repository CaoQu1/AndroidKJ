package com.example.tapai.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.tapai.contract.ContentContract;
import com.example.tapai.entity.QrCode;
import com.example.tapai.model.BaseObjectBean;
import com.example.tapai.presenter.ContentActivityPresenter;
import com.uber.autodispose.AutoDisposeConverter;

import android.view.LayoutInflater;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContentActivity extends BaseMvpActivity<ContentActivityPresenter> implements ContentContract.View {

    private ListView listView;
    private BaseAdapter adapter;
    private TextView textViewCode;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.content_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        mPresenter = new ContentActivityPresenter(sqLiteDatabase);
        mPresenter.attachView(this);
    }

    @Override
    protected void initControl() {
        super.initControl();
        listView = findViewById(R.id.lv);
        final List<QrCode> qrCodes = mPresenter.getList();
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return qrCodes.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View convertView, ViewGroup viewGroup) {
                LayoutInflater inflater = ContentActivity.this.getLayoutInflater();
                View view;

                if (convertView == null) {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.item_view, null);
                } else {
                    view = convertView;
                }
                textViewCode = (TextView) view.findViewById(R.id.text_code);
                textViewCode.setText(qrCodes.get(i).getCode());

                textViewDate = (TextView) view.findViewById(R.id.text_date);
                Date date = qrCodes.get(i).getDate();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                textViewDate.setText(format.format(date));
                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    public void onSuccess(BaseObjectBean<String> bean) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void redirectActivity() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }
}
