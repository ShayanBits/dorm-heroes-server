package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.Status;
import com.software.technology.ss2019.dormheroes.repositories.StatusRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusControllerService {

    Logger logger = LoggerFactory.getLogger(StatusControllerService.class);

    @Autowired
    private StatusRepository statusRepository;

    public Status createStatus(Status status){
        logger.info("Trying to create the following status: " + status.toString());
        Status createdStatus = statusRepository.insert(status);
        logger.info("The following status has been created in database: " + createdStatus.toString());
        return createdStatus;
    }

    public List<Status> getAllStatus(){
        logger.info("Trying to get a list of all status.");
        List<Status> listOfAllStatus = statusRepository.findAll();
        logger.info("Received the list of all status from database. Total number of entries: " + listOfAllStatus.size());
        return listOfAllStatus;
    }

    public Status getStatusById(ObjectId id){
        logger.info("Trying to find the status with the id: " + id.toHexString());
        Status foundStatus = statusRepository.findBy_id(id);
        logger.info("Received the following status from database: " + foundStatus.toString() );
        return foundStatus;
    }

    public void deleteStatusById(ObjectId id){
        logger.info("Trying to delete status by id with the following id: " + id.toHexString());
        statusRepository.deleteById(id.toString());
        logger.info("Successfully deleted status with the following id: " + id.toHexString());
    }
}
