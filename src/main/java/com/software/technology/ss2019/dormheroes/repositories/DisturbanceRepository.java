package com.software.technology.ss2019.dormheroes.repositories;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DisturbanceRepository extends MongoRepository<DisturbanceType, String> {
    DisturbanceType findBy_id(ObjectId _Id);
}