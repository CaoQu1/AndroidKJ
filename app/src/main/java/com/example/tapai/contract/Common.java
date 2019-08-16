package com.example.tapai.contract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface Common {

    interface Config {

        void putValue(String key, String value);

        String getValue(String key);

        void initHelper(Context context, String name);
    }

    interface Repository<T> {

        SQLiteDatabase getWritableDatabase();

        void onUpgrade();

        Boolean isExist(String tableName);

        int insert(T model);

        int update(T model);

        List<T> getAll();

        int delete(T model);
    }
}
