package com.example.tapai.presenter;

import android.database.sqlite.SQLiteDatabase;

import com.example.tapai.common.CommonHelper;
import com.example.tapai.common.DatabaseRepository;
import com.example.tapai.contract.ContentContract;
import com.example.tapai.model.ContentModel;
import com.example.tapai.entity.QrCode;

import java.util.Date;
import java.util.List;

public class ContentActivityPresenter extends BasePresenter<ContentContract.View> implements ContentContract.Presenter {

    private ContentContract.Model model;

    public ContentActivityPresenter(SQLiteDatabase sqLiteDatabase) {
        DatabaseRepository databaseRepository = new DatabaseRepository(QrCode.class, null);
        databaseRepository.setSqLiteDatabase(sqLiteDatabase);
        databaseRepository.onUpgrade();
        model = new ContentModel(databaseRepository);
    }

    @Override
    public List<QrCode> getList() {
        for (int i = 0; i < 10; i++) {
            QrCode qrCode = new QrCode(CommonHelper.GenerateNumber(10), new Date());
            model.insert(qrCode);
        }
        return model.getAll();
    }
}
