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
    IssueControllerService issueControllerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Issue> getAllIssues() {
        return issueControllerService.getAllIssues();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Issue createIssue(@Valid @RequestBody Issue issue) {
        return issueControllerService.createIssue(issue);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Issue getIssueById(@PathVariable("id") ObjectId id) {
        return issueControllerService.getIssueById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Issue updateIssueById(@PathVariable("id") ObjectId id, @Valid @RequestBody Issue issue){
      return issueControllerService.updateIssueById(id, issue);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteIssueById(@PathVariable("id") ObjectId id){
       issueControllerService.deleteIssueById(id);
    }

}
