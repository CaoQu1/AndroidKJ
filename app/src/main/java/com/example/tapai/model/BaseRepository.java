package com.example.tapai.model;

import com.example.tapai.contract.BaseModel;
import com.example.tapai.contract.Common;

import java.util.List;


/**
 * 基础数据仓库
 */
public class BaseRepository implements BaseModel {

    private Common.Repository repository;

    public <T> BaseRepository(Common.Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public <T> int insert(T model) {
        return this.repository.insert(model);
    }

    @Override
    public <T> int update(T model) {
        return this.repository.update(model);
    }

    @Override
    public <T> int delete(T model) {
        return this.repository.delete(model);
    }

    @Override
    public <T> List<T> getAll() {
        return this.repository.getAll();
    }
}
