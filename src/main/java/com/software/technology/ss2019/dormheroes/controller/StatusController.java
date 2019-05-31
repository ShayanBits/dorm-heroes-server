package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.Status;
import com.software.technology.ss2019.dormheroes.service.StatusControllerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusControllerService statusControllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Status> getAllStatus() {
        return statusControllerService.getAllStatus();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Status getStatusById(@PathVariable("id") ObjectId id){
        return statusControllerService.getStatusById(id);
    }
}
