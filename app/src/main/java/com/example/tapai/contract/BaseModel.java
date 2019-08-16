package com.example.tapai.contract;

import java.util.List;


public interface BaseModel {
    <T> int insert(T model);

    <T> int update(T model);

    <T> List<T> getAll();

    <T> int delete(T model);
}
