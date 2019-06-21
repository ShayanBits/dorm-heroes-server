package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.repositories.NumberOfPeopleRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberOfInvolvedPeopleControllerService {
    private final Logger logger = LoggerFactory.getLogger(NumberOfInvolvedPeopleControllerService.class);

    @Autowired
    private NumberOfPeopleRepository numberOfInvolvedPeopleRepository;

    public NumberOfInvolvedPeopleInterval createNewInterval(NumberOfInvolvedPeopleInterval numberOfPeopleInterval){
        logger.info("Trying to create the new NumberOfInvolvedPeopleInterval: " + numberOfPeopleInterval.toString());
        NumberOfInvolvedPeopleInterval createdInterval= numberOfInvolvedPeopleRepository.insert(numberOfPeopleInterval);
        logger.info("Successfully created the following interval in database: " + createdInterval.toString());
        return createdInterval;
    }

    public List<NumberOfInvolvedPeopleInterval> getAllIntervals(){
        logger.info("Trying to get the list of all intervals from database");
        List<NumberOfInvolvedPeopleInterval> listOfAllIntervals = numberOfInvolvedPeopleRepository.findAll();
        logger.info("Received the list of all intervals from database. There are " + listOfAllIntervals.size() + " intervals found.");
        return listOfAllIntervals;
    }

    public NumberOfInvolvedPeopleInterval getIntervalByID(ObjectId id){
        logger.info("Trying to find the interval by id " + id.toHexString() + " in database");
        NumberOfInvolvedPeopleInterval intervalFromDatabase = numberOfInvolvedPeopleRepository.findBy_id(id);
        if(intervalFromDatabase == null){
            logger.info("Could not find NumberOfInvolvedPeopleInterval in database with id: " + id.toHexString());
            throw new NullPointerException("Could not find NumberOfInvolvedPeopleInterval in database with id: " + id.toHexString());
        }
        logger.info("Found the following interval in database:  " + intervalFromDatabase.toString());
        return intervalFromDatabase;
    }

    public void deleteIntervalByID(ObjectId id){
        logger.info("Trying to delete the interval with id: " + id.toHexString());
        numberOfInvolvedPeopleRepository.deleteById(id.toString());
        logger.info("Deleted the interval with id: " + id.toHexString());
    }
}
