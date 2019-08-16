package com.example.tapai.model;

public class BaseObjectBean<T> {

    private  T result;

    public  BaseObjectBean(T result)
    {
        this.result=result;
    }

    public T getResult() {
        return result;
    }
}

