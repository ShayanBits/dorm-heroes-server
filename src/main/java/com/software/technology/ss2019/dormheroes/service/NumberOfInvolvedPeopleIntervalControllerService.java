package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.repositories.NumberOfPeopleIntervalRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberOfInvolvedPeopleIntervalControllerService {
    private final Logger logger = LoggerFactory.getLogger(NumberOfInvolvedPeopleIntervalControllerService.class);

    @Autowired
    private NumberOfPeopleIntervalRepository numberOfInvolvedPeopleRepository;

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
}
