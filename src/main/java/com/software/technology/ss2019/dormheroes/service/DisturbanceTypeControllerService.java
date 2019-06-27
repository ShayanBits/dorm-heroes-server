package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.repositories.DisturbanceTypeRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DisturbanceTypeControllerService {

    Logger logger = LoggerFactory.getLogger(DisturbanceTypeControllerService.class);

    @Autowired
    private DisturbanceTypeRepository disturbanceTypeRepository;

    DisturbanceType getDisturbanceTypeById(ObjectId id){
        logger.info("Trying to find the disturbanceType by the following id: " + id.toString());
        DisturbanceType foundDisturbanceType = disturbanceTypeRepository.findBy_id(id);
        logger.info("The following disturbanceType was found by Id: " + foundDisturbanceType.toString());
        return foundDisturbanceType;
    }

    public List<DisturbanceType> getAllDisturbanceTypes(){
        logger.info("Trying to get the list of all disturbanceTypes from database.");
        List<DisturbanceType> listOfAllDisturbanceTypes= disturbanceTypeRepository.findAll();
        logger.info("Received the list of all disturbanceTypes. There are " + listOfAllDisturbanceTypes.size() + " entries found.");
        return listOfAllDisturbanceTypes;
    }

    public DisturbanceType createDisturbanceType(DisturbanceType disturbanceType){
        logger.info("Trying to create the following new disturbanceType: " + disturbanceType.toString());
        DisturbanceType createdDisturbanceType =  disturbanceTypeRepository.insert(disturbanceType);
        logger.info("Created the following disturbanceType in database: " + createdDisturbanceType.toString());
        return createdDisturbanceType;
    }

    public void deleteDisturbanceTypeById(ObjectId id){
        logger.info("Trying to delete the disturbanceType by id: " + id.toHexString());
        disturbanceTypeRepository.deleteById(id.toString());
        logger.info("Successfully deleted the disturbanceType with the following id: " + id.toHexString());
    }

}
