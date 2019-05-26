package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.service.DisturbanceTypeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/disturbanceType")
public class DisturbanceTypeController {

    @Autowired
    DisturbanceTypeControllerService disturbanceTypeControllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<DisturbanceType> getAllDisturbanceType(){
        return disturbanceTypeControllerService.getAllDisturbanceType();
    }
}
