package com.software.technology.ss2019.dormheroes.repositories;

import com.software.technology.ss2019.dormheroes.model.Issue;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssueRepository extends MongoRepository<Issue, String> {
    Issue findBy_id(ObjectId _Id);
}