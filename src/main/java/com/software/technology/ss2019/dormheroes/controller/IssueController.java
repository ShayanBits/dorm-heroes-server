package com.software.technology.ss2019.dormheroes.controller;

import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;


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
    private IssueRepository issueRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Issue createIssue(@Valid @RequestBody Issue issue){
        return issueRepository.insert(issue);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Issue getIssueById(@PathVariable("id") ObjectId id){
        return issueRepository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Issue updateIssueById(@PathVariable("id") ObjectId id, @Valid @RequestBody Issue issue){
        Issue toBeUpdatedIssue = issueRepository.findBy_id(id);
        toBeUpdatedIssue.setLocation(issue.getLocation());
        toBeUpdatedIssue.setDescription(issue.getDescription());
        toBeUpdatedIssue.setDisturbanceType(issue.getDisturbanceType());
        toBeUpdatedIssue.setNumberOfInvolvedPeople(issue.getNumberOfInvolvedPeople());
        toBeUpdatedIssue.setLastModifiedDate(new Date());
        return issueRepository.save(toBeUpdatedIssue);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteIssueById(@PathVariable("id") ObjectId id){
        issueRepository.delete(issueRepository.findBy_id(id));
    }

}
