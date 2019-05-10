package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;


import com.software.technology.ss2019.dormheroes.service.IssueControllerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import javax.validation.Valid;
import java.util.List;
import java.util.Date;


@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    IssueControllerService controllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Issue> getAllIssues() {
        return controllerService.getAllIssues();
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Issue createIssue(@Valid @RequestBody Issue issue)
    {
        return controllerService.createIssue(issue);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Issue getIssueById(@PathVariable("id") ObjectId id)
    {
        return controllerService.getIssueById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Issue updateIssueById(@PathVariable("id") ObjectId id, @Valid @RequestBody Issue issue){
      return controllerService.updateIssueById(id, issue);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteIssueById(@PathVariable("id") ObjectId id){
       controllerService.deleteIssueById(id);
    }

}
