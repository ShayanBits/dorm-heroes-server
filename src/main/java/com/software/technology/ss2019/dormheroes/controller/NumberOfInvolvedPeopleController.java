package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.service.NumberOfInvolvedPeopleControllerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/numberOfInvolvedPeopleIntervals")
public class NumberOfInvolvedPeopleController {

    @Autowired
    NumberOfInvolvedPeopleControllerService numberOfInvolvedPeopleControllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<NumberOfInvolvedPeopleInterval> getAllStatus() {
        return numberOfInvolvedPeopleControllerService.getAllIntervals();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NumberOfInvolvedPeopleInterval getStatusById(@PathVariable("id") ObjectId id){
        return numberOfInvolvedPeopleControllerService.getIntervalByID(id);
    }
}
