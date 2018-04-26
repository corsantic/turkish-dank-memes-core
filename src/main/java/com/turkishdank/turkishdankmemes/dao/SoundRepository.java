package com.turkishdank.turkishdankmemes.dao;

import com.turkishdank.turkishdankmemes.entity.Sound;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SoundRepository extends AbstractMongoRepository<Sound>
{
    public Sound loadByName(String name)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return DataAccessUtils.singleResult(mongoTemplate.find(query, persistentClass,"Sound"));
    }

}