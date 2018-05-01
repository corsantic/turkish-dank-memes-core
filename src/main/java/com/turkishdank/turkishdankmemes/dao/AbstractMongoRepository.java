package com.turkishdank.turkishdankmemes.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

public abstract class AbstractMongoRepository<T> implements AbstractDAO<T>
{
    protected Class<T> persistentClass;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @SuppressWarnings("unchecked")
    public AbstractMongoRepository()
    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void save(T t)
    {

        mongoTemplate.save(t,"Sound");
    }

    @Override
    public T getById(String id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return DataAccessUtils.singleResult(mongoTemplate.find(query, persistentClass,"Sound"));
    }

    @Override
    public void deleteAll()
    {
        Query query = new Query();
        mongoTemplate.remove(query, persistentClass,"Sound");
    }

    @Override
    public List<T> loadAll()
    {

        return mongoTemplate.findAll(persistentClass,"Sound");
    }


}