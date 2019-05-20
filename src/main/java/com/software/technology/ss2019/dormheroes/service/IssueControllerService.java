package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueControllerService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        return issueRepository.findByOrderByCreationDateDesc();
    }

    public Issue createIssue(Issue issue){
        return issueRepository.insert(issue);
    }

    public Issue getIssueById( ObjectId id){
        return issueRepository.findBy_id(id);
    }

    public Issue updateIssueById(ObjectId id, Issue issue){
        Issue toBeUpdatedIssue = issueRepository.findBy_id(id);
        toBeUpdatedIssue.setStatus(issue.getStatus());
        toBeUpdatedIssue.setTitle(issue.getTitle());
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