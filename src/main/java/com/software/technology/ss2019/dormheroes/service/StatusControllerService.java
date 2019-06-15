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
        logger.debug("Trying to create new status: " + status.toString());
        return statusRepository.insert(status);
    }

    public List<Status> getAllStatus(){
        logger.info("Trying to get a list of all status.");
        List<Status> listOfAllStatus = statusRepository.findAll();
        logger.info("Received a list of all status in database. Amount of Status: " + listOfAllStatus.size());
        return listOfAllStatus;
    }

    public Status getStatusById(ObjectId id){
        logger.info("Trying to find status by id: " + id.toHexString());
        Status foundStatus = statusRepository.findBy_id(id);
        logger.info("Received status from database: " + foundStatus.toString() );
        return foundStatus;
    }

    public void deleteStatusById(ObjectId id){
        logger.info("Trying to delete status by id: " + id.toHexString());
        statusRepository.deleteById(id.toString());
    }
}
