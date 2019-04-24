package com.software.technology.ss2019.dormheros.repositories;

import com.software.technology.ss2019.dormheros.model.Report;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, String> {
    Report findBy_id(ObjectId _Id);
}