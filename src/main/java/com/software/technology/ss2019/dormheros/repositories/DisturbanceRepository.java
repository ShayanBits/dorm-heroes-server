package com.software.technology.ss2019.dormheros.repositories;

import com.software.technology.ss2019.dormheros.model.Disturbance;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DisturbanceRepository extends MongoRepository<Disturbance, String> {
    Disturbance findBy_id(ObjectId _Id);
}