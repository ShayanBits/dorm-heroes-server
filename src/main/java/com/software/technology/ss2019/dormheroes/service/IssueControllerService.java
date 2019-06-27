package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.model.Status;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueControllerService {

    @Autowired
    private StatusControllerService statusControllerService;

    Logger logger = LoggerFactory.getLogger(IssueControllerService.class);

    @Autowired
    private IssueRepository issueRepository;


    @Autowired
    private DisturbanceTypeControllerService disturbanceTypeControllerService;

    public List<Issue> getAllIssues() {
        logger.info("Trying to receive the list of all issues from database.");
        List<Issue> listOfAllIssues = issueRepository.findByOrderByCreationDateDesc();
        logger.info("Received the list of issues. There are " + listOfAllIssues.size() + " issues found.");
        return listOfAllIssues;
    }

    public Issue createIssue(Issue issue){
        DisturbanceType disturbanceTypeFromRequestedIssue = disturbanceTypeControllerService.
                getDisturbanceTypeById(new ObjectId(issue.getDisturbanceType().get_id()));
        if ( disturbanceTypeFromRequestedIssue.getIsNumberOfInvolvedPeopleMandatory() && issue.getNumberOfInvolvedPeople() <= 1){
            throw new IllegalArgumentException("The field numberOfInvolvedPeople cannot be Null when disturbanceType is : " + issue.getDisturbanceType().getType());
        }
        else {
            final Status SENT_STATUS_OBJECT_IN_DB = statusControllerService.getSentStatus();
            logger.info("Trying to create the following new issue in database: " + issue.toString());
            issue.setStatus(SENT_STATUS_OBJECT_IN_DB);
            Issue createdIssue = issueRepository.insert(issue);
            logger.info("Issue has been created. Result from server: " + createdIssue.toString());
            return createdIssue;
        }
    }

    public Issue getIssueById( ObjectId id){
        logger.info("Trying to find the issue by id " + id.toHexString() + " in database");
        Issue foundIssueInDB = issueRepository.findBy_id(id);
        logger.info("Found the following issue in database:  " + foundIssueInDB.toString());
        return foundIssueInDB;
    }

    public Issue updateIssueById(ObjectId id, Issue issue){
        logger.info("Trying to update issue with id " + id.toHexString() + " by the new issue: " + issue.toString());
        logger.info("Looking for issue in database.");
        Issue toBeUpdatedIssue = issueRepository.findBy_id(id);
        logger.info("The following Issue was found and is about to be updated: " + toBeUpdatedIssue.toString());
        toBeUpdatedIssue.setStatus(issue.getStatus());
        toBeUpdatedIssue.setTitle(issue.getTitle());
        toBeUpdatedIssue.setLocation(issue.getLocation());
        toBeUpdatedIssue.setDescription(issue.getDescription());
        toBeUpdatedIssue.setDisturbanceType(issue.getDisturbanceType());
        toBeUpdatedIssue.setNumberOfInvolvedPeople(issue.getNumberOfInvolvedPeople());
        toBeUpdatedIssue.setLastModifiedDate(new Date());
        logger.info("Saving updated issue into database with following information: " + toBeUpdatedIssue.toString());
        Issue updatedIssueInDB = issueRepository.save(toBeUpdatedIssue);
        logger.info("The following issue has been successfully updated in database: " + updatedIssueInDB.toString());
        return updatedIssueInDB;
    }

    public void deleteIssueById(ObjectId id){
        logger.info("Trying to delete the issue with id: " + id.toHexString());
        issueRepository.delete(issueRepository.findBy_id(id));
        logger.info("Deleted the issue with id: " + id.toHexString());
    }

}
