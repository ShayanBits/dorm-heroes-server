package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.service.NumberOfInvolvedPeopleIntervalControllerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/numberOfInvolvedPeopleInterval")
public class NumberOfInvolvedPeopleIntervalController {

    @Autowired
    NumberOfInvolvedPeopleIntervalControllerService numberOfInvolvedPeopleIntervalControllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<NumberOfInvolvedPeopleInterval> getAllIntervals() {
        return numberOfInvolvedPeopleIntervalControllerService.getAllIntervals();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NumberOfInvolvedPeopleInterval getIntervalByID(@PathVariable("id") ObjectId id){
        return numberOfInvolvedPeopleIntervalControllerService.getIntervalByID(id);
    }
}
