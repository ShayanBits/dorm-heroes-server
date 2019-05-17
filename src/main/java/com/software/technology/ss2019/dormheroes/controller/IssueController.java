package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.Issue;


import com.software.technology.ss2019.dormheroes.service.IssueControllerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    IssueControllerService controllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Issue> getAllIssues() {
        return controllerService.getAllIssues();
    }

    @ResponseStatus(HttpStatus.CREATED)
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
