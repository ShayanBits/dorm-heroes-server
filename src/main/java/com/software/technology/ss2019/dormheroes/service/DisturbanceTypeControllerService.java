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

    public List<DisturbanceType> getAllDisturbanceTypes(){
        logger.debug("Trying to get all disturbanceTypes from repository.");
        List<DisturbanceType> listOfAllDisturbanceTypes= disturbanceTypeRepository.findAll();
        logger.debug("Received list of all disturbanceTypes. Found " + listOfAllDisturbanceTypes.size() + " entries.");
        return listOfAllDisturbanceTypes;
    }

    public DisturbanceType createDisturbanceType(DisturbanceType disturbanceType){
        logger.debug("Trying to create a new disturbanceType: " + disturbanceType.toString());
        return disturbanceTypeRepository.insert(disturbanceType);
    }

    public void deleteDisturbanceTypeById(ObjectId id){
        logger.info("Trying to delete disturbance Type by id: " + id.toHexString());
        disturbanceTypeRepository.deleteById(id.toString());
    }
}
