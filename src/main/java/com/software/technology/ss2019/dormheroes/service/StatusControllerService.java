package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.Status;
import com.software.technology.ss2019.dormheroes.repositories.StatusRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusControllerService {

    @Autowired
    private StatusRepository statusRepository;

    public Status createStatus(Status status){
        return statusRepository.insert(status);
    }

    public List<Status> getAllStatus(){
        return statusRepository.findAll();
    }

    public Status getStatusById(ObjectId id){
        return statusRepository.findBy_id(id);
    }

    public void deleteStatusById(ObjectId id){
        statusRepository.deleteById(id.toString());
    }
}
