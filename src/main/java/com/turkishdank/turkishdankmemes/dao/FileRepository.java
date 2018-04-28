package com.turkishdank.turkishdankmemes.dao;

import com.mongodb.gridfs.GridFSFile;
import com.turkishdank.turkishdankmemes.entity.Sound;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository extends AbstractMongoRepository<GridFSFile>{

    public GridFSFile loadByName(String name)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return DataAccessUtils.singleResult(mongoTemplate.find(query, persistentClass,"fs.files"));
    }


}
