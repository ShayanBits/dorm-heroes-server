package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.repositories.NumberOfPeopleIntervalRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NumberOfInvolvedPeopleIntervalControllerService {
    private final Logger logger = LoggerFactory.getLogger(NumberOfInvolvedPeopleIntervalControllerService.class);

    @Autowired
    private NumberOfPeopleIntervalRepository numberOfInvolvedPeopleIntervalRepository;

    public List<NumberOfInvolvedPeopleInterval> getAllIntervals(){
        logger.info("Trying to get the list of all intervals from database");
        List<NumberOfInvolvedPeopleInterval> listOfAllIntervals = numberOfInvolvedPeopleIntervalRepository.findAll();
        if(listOfAllIntervals == null || listOfAllIntervals.isEmpty()){
            logger.info("No entries in database found.");
            return Collections.emptyList();
        }
        logger.info("Received the list of all intervals from database. There are " + listOfAllIntervals.size() + " intervals found.");
        return listOfAllIntervals;
    }

    public NumberOfInvolvedPeopleInterval getNumberOfInvolvedPeopleIntervalByID(ObjectId id){
        logger.info("Trying to find the interval by the id " + id.toHexString() + " in database");
        NumberOfInvolvedPeopleInterval numbertOfInvolvedPeopleintervalFromDatabase = numberOfInvolvedPeopleIntervalRepository.findBy_id(id);
        if (numbertOfInvolvedPeopleintervalFromDatabase == null) {
            logger.info("Could not find NumberOfInvolvedPeopleInterval in database by the id: " + id.toHexString());
            return null;
        }
        logger.info("Found the following NumberOfInvolvedPeopleInterval in database:  " + numbertOfInvolvedPeopleintervalFromDatabase.toString());
        return numbertOfInvolvedPeopleintervalFromDatabase;
    }

    public NumberOfInvolvedPeopleInterval createNumberOfInvolvedPeopleInterval(NumberOfInvolvedPeopleInterval numberOfInvolvedPeopleInterval){
        logger.info("Trying to create the following new interval to database: " + numberOfInvolvedPeopleInterval.toString());
        NumberOfInvolvedPeopleInterval createdNumberOfInvolvedPeopleInterval = numberOfInvolvedPeopleIntervalRepository.insert(numberOfInvolvedPeopleInterval);
        if(createdNumberOfInvolvedPeopleInterval == null){
            logger.info("Could not create the new NumberOfInvolvedPeopleInterval in database");
            return null;
        }
        logger.info("Created the following NumberOfInvolvedPeopleInterval in database:  " + createdNumberOfInvolvedPeopleInterval.toString());
        return createdNumberOfInvolvedPeopleInterval;
    }

    public void deleteNumberOfInvolvedPeopleIntervalById(ObjectId id){
        logger.info("Trying to delete the following interval from database by the id: " + id.toHexString());
        numberOfInvolvedPeopleIntervalRepository.deleteById(id.toHexString());
        logger.info("Successfully deleted numberOfInvolvedPeopleInterval with the following id: " + id.toHexString());
    }
}
