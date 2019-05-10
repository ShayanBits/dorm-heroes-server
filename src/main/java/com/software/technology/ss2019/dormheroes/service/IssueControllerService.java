package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IssueControllerService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        List<Issue> listOfAllIssuesFromDB = issueRepository.findAll();
        Collections.sort(listOfAllIssuesFromDB);
        return listOfAllIssuesFromDB;
    }

    public Issue createIssue(Issue issue){
        return issueRepository.insert(issue);
    }

    public Issue getIssueById( ObjectId id){
        return issueRepository.findBy_id(id);
    }

    public Issue updateIssueById(ObjectId id, Issue issue){
        Issue toBeUpdatedIssue = issueRepository.findBy_id(id);
        toBeUpdatedIssue.setLocation(issue.getLocation());
        toBeUpdatedIssue.setDescription(issue.getDescription());
        toBeUpdatedIssue.setDisturbanceType(issue.getDisturbanceType());
        toBeUpdatedIssue.setNumberOfInvolvedPeople(issue.getNumberOfInvolvedPeople());
        toBeUpdatedIssue.setLastModifiedDate(new Date());
        return issueRepository.save(toBeUpdatedIssue);
    }

    public void deleteIssueById(ObjectId id){
        issueRepository.delete(issueRepository.findBy_id(id));
    }

}
