package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueControllerService {

    Logger logger = LoggerFactory.getLogger(IssueControllerService.class);

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        logger.info("Trying to receive list of all issues from database.");
        List<Issue> listOfAllIssues = issueRepository.findByOrderByCreationDateDesc();
        logger.info("Received list of issues. Found " + listOfAllIssues.size() + " issues.");
        return listOfAllIssues;
    }

    public Issue createIssue(Issue issue){
        logger.info("Trying to create new issue in database: " + issue.toString());
        return issueRepository.insert(issue);
    }

    public Issue getIssueById( ObjectId id){
        logger.info("Trying to find issue by id in database: " + id.toHexString());
        Issue foundIssueInDB = issueRepository.findBy_id(id);
        logger.info("Found issue in database:  " + foundIssueInDB.toString());
        return foundIssueInDB;
    }

    public Issue updateIssueById(ObjectId id, Issue issue){
        logger.info("Trying to update issue with id " + id.toHexString() + " by the new issue: " + issue.toString());
        logger.info("Looking for issue in database.");
        Issue toBeUpdatedIssue = issueRepository.findBy_id(id);
        logger.info("Found issue in database: " + toBeUpdatedIssue.toString() + ". Updating all values.");
        toBeUpdatedIssue.setStatus(issue.getStatus());
        toBeUpdatedIssue.setTitle(issue.getTitle());
        toBeUpdatedIssue.setLocation(issue.getLocation());
        toBeUpdatedIssue.setDescription(issue.getDescription());
        toBeUpdatedIssue.setDisturbanceType(issue.getDisturbanceType());
        toBeUpdatedIssue.setNumberOfInvolvedPeople(issue.getNumberOfInvolvedPeople());
        toBeUpdatedIssue.setLastModifiedDate(new Date());
        logger.info("Saving updated issue into database: " + toBeUpdatedIssue.toString());
        return issueRepository.save(toBeUpdatedIssue);
    }

    public void deleteIssueById(ObjectId id){
        logger.info("Trying to delete issue with id: " + id.toHexString());
        issueRepository.delete(issueRepository.findBy_id(id));
    }

}
