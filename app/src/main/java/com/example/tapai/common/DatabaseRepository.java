package com.example.tapai.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tapai.contract.Common;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

/**
 * @param <T> 泛型实体
 *            数据仓库类
 */
public class DatabaseRepository<T> implements Common.Repository<T> {

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "/data/Test.db";

    private Class<T> entityClass;

    private String table_Name;

    private Map<String, Object> column_Names = new HashMap<>();

    private Map<String, Object> column_Values = new HashMap<>();

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseRepository(Class<T> entityClass, @Nullable String databaseName) {
        if (databaseName == null || databaseName.isEmpty()) {
            databaseName = DATABASE_NAME;
        }
//        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(databaseName, null);
        this.entityClass = entityClass;

    }

    public void onCreate() {
        if (entityClass == null) {
            initClass();
        }
        if (table_Name == null || table_Name.isEmpty()) {
            initTable();
        }
        if (column_Names.isEmpty()) {
            initColumns();
        }
        if (!isExist(table_Name)) {
            StringBuffer sqlBuffer = new StringBuffer("create table " + table_Name + "(");
            for (Map.Entry<String, Object> map : column_Names.entrySet()) {
                Field field = (Field) map.getValue();
                sqlBuffer.append(map.getKey() + " " + TypeToBase(field.getGenericType()) + ",");
            }
            if (sqlBuffer.length() > 14 + table_Name.length()) {
                sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
                sqlBuffer.append(")");
                sqLiteDatabase.execSQL(sqlBuffer.toString());
            }
        }
    }

    private String TypeToBase(Type type) {
        String dataType = "TEXT";
        switch (type.toString()) {
            case "class java.lang.String":
                dataType = "NVARCHAR";
                break;
            case "class java.lang.Integer":
                dataType = "INTEGER";
                break;
            case "class java.lang.Boolean":
                dataType = "BOOLEAN";
                break;
            case "class java.util.Date":
                dataType = "TIMESTAMP";
                break;
        }
        return dataType;
    }

    @Override
    public void onUpgrade() {
        if (entityClass == null) {
            initClass();
        }
        if (table_Name == null || table_Name.isEmpty()) {
            initTable();
        }
        sqLiteDatabase.execSQL("drop table if exists " + table_Name);
        onCreate();
    }

    private void initTable() {
        if (entityClass != null) {
            table_Name = entityClass.getSimpleName();
        }
    }

    private void initClass() {
//        Type genType = getClass().getGenericInterfaces()[0];
////        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
////        if (params.length > 0) {
////        }
//
//        Class<?> cl = getClass();
//        entityClass = (Class<T>) type;
    }

    private void initColumns() {
        if (entityClass != null) {
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                if (!"$change".equals(field.getName()) && !"serialVersionUID".equals(field.getName())) {
                    field.setAccessible(true);
                    column_Names.put(field.getName(), field);
                }
            }
        }
    }

    private String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return sqLiteDatabase;
    }

    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }


    @Override
    public Boolean isExist(String tableName) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from sqlite_master where type = 'table' and name ='" + tableName + "'", null);
        return cursor != null && cursor.getCount() > 0;
    }

    @Override
    public int insert(T model) {
        int idSelf = 0;
        try {
            if (!column_Names.isEmpty()) {
                if (isExist(table_Name)) {
                    ContentValues contentValues = new ContentValues();
                    for (Map.Entry<String, Object> map : column_Names.entrySet()) {
                        Field field = (Field) map.getValue();
                        Object val = field.get(model);
                        switch (field.getGenericType().toString()) {
                            case "class java.lang.Integer":
                                contentValues.put(map.getKey(), (Integer) val);
                                break;
                            case "class java.lang.Boolean":
                                contentValues.put(map.getKey(), (Boolean) val);
                                break;
                            case "class java.util.Date":
                                Date date = (Date) val;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String _date = format.format(date);
                                contentValues.put(map.getKey(), _date);
                                break;
                            case "class java.lang.String":
                            default:
                                contentValues.put(map.getKey(), val.toString());
                                break;
                        }

                    }
                    idSelf = (int) sqLiteDatabase.insert(table_Name, null, contentValues);
                }
            }
        } catch (IllegalAccessException ex) {
        }
        return idSelf;
    }

    @Override
    public int delete(T model) {
        return 0;
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try {
            if (entityClass != null && column_Names != null) {
                T instance = entityClass.newInstance();
                Cursor cursor = sqLiteDatabase.query(table_Name, null, null, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        for (Map.Entry<String, Object> map : column_Names.entrySet()) {
                            Field field = (Field) map.getValue();
                            switch (field.getGenericType().toString()) {
                                case "class java.lang.Integer":
                                    field.setInt(instance, cursor.getInt(cursor.getColumnIndex(map.getKey())));
                                    break;
                                case "class java.lang.Boolean":
                                    field.setBoolean(instance, cursor.getInt(cursor.getColumnIndex(map.getKey())) == 1);
                                    break;
                                case "class java.util.Date":
                                    String date = cursor.getString(cursor.getColumnIndex(map.getKey()));
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date _date = format.parse(date);
                                    field.set(instance, _date);
                                    break;
                                case "class java.lang.String":
                                default:
                                    field.set(instance, cursor.getString(cursor.getColumnIndex(map.getKey())));
                                    break;
                            }
                        }
                        list.add(instance);
                    } while (cursor.moveToNext());
                }
            }
        } catch (IllegalAccessException ex) {
        } catch (InstantiationException ex) {
        } catch (ParseException ex) {
        }
        return list;
    }

    @Override
    public int update(T model) {
        return 0;
    }
}
