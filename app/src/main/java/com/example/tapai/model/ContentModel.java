package com.example.tapai.model;

import com.example.tapai.contract.BaseModel;
import com.example.tapai.contract.Common;
import com.example.tapai.contract.ContentContract;

public class ContentModel extends BaseRepository implements ContentContract.Model {

    public <T> ContentModel(Common.Repository<T> repository) {
        super(repository);
    }
}
