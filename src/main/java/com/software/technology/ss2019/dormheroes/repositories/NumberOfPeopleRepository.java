package com.software.technology.ss2019.dormheroes.repositories;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NumberOfPeopleRepository extends MongoRepository<NumberOfInvolvedPeopleInterval, String> {
    NumberOfInvolvedPeopleInterval findBy_id(ObjectId _Id);

    @Override
    void deleteById(String s);
}