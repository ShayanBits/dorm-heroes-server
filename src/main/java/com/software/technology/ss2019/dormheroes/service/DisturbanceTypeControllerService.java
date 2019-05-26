package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.repositories.DisturbanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DisturbanceTypeControllerService {

    @Autowired
    private DisturbanceTypeRepository disturbanceTypeRepository;

    public List<DisturbanceType> getAllDisturbanceType(){
        return disturbanceTypeRepository.findAll();
    }
}
