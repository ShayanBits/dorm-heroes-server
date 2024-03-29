package com.software.technology.ss2019.dormheroes.repositories;

import com.software.technology.ss2019.dormheroes.model.Status;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatusRepository extends MongoRepository<Status, String> {
    Status findBy_id(ObjectId _Id);

    @Override
    void deleteById(String s);
}