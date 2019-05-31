package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.repositories.DisturbanceTypeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DisturbanceTypeControllerService {

    @Autowired
    private DisturbanceTypeRepository disturbanceTypeRepository;

    public List<DisturbanceType> getAllDisturbanceTypes(){
        return disturbanceTypeRepository.findAll();
    }

    public DisturbanceType createDisturbanceType(DisturbanceType disturbanceType){
        return disturbanceTypeRepository.insert(disturbanceType);
    }

    public void deleteDisturbanceTypeById(ObjectId id){
        disturbanceTypeRepository.deleteById(id.toString());
    }
}
