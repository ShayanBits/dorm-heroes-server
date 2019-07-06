package com.software.technology.ss2019.dormheroes.service;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.model.Status;
import com.software.technology.ss2019.dormheroes.repositories.IssueRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IssueControllerService {

    @Autowired
    private StatusControllerService statusControllerService;

    @Autowired
    private NumberOfInvolvedPeopleIntervalControllerService numberOfInvolvedPeopleIntervalControllerService;

    Logger logger = LoggerFactory.getLogger(IssueControllerService.class);

    @Autowired
    private IssueRepository issueRepository;


    @Autowired
    private DisturbanceTypeControllerService disturbanceTypeControllerService;


    public List<Issue> getAllIssues() {
        Map<String, String> mappedDisturbanceTypes = new HashMap<>();
        Map<String, String> mappedStatus = new HashMap<>();
        Map<String, String> mappedInterval = new HashMap<>();


        logger.info("Trying to receive the list of all issues from database.");
        List<Issue> listOfAllIssues = issueRepository.findByOrderByCreationDateDesc();
        if(listOfAllIssues == null || listOfAllIssues.isEmpty()){
            logger.info("There a no issues in database found.");
            return Collections.emptyList();
        }

        logger.info("Received the list of issues. There are " + listOfAllIssues.size() + " issues found.");

        for (int i = 0; i < listOfAllIssues.size(); i++) {
            Issue issue = listOfAllIssues.get(i);


            String disturbanceTypeID = issue.getDisturbanceType();
            if(mappedDisturbanceTypes.get(disturbanceTypeID) != null){
                issue.setDisturbanceType(mappedDisturbanceTypes.get(disturbanceTypeID));
            }else {
                DisturbanceType foundDisturbanceTypeFromDatabase = disturbanceTypeControllerService.getDisturbanceTypeById(new ObjectId(issue.getDisturbanceType()));
                if (foundDisturbanceTypeFromDatabase == null){
                    logger.info("There is no disturbanceType in database with the id: " + disturbanceTypeID + ". Removing issue from the list.);
                    listOfAllIssues.remove(issue);
                    i--;
                    continue;
                }
                issue.setDisturbanceType(foundDisturbanceTypeFromDatabase.getType());
            }

            String statusID = issue.getStatus();
            if(mappedStatus.get(statusID) != null){
                issue.setStatus(mappedStatus.get(statusID));
            }else {
                Status foundStatusInDatabase = statusControllerService.getStatusById(new ObjectId(statusID));
                if (foundStatusInDatabase == null){
                    logger.info("There is no status in database with the id: " + statusID + ". Removing issue from the list.");
                    listOfAllIssues.remove(issue);
                    i--;
                    continue;
                }
                issue.setStatus(foundStatusInDatabase.getType());
            }

            String intervalID = issue.getNumberOfInvolvedPeople();
            if(intervalID == null){
                continue;
            }
            if(mappedInterval.get(intervalID) != null){
                issue.setNumberOfInvolvedPeople(mappedStatus.get(intervalID));
            }else {
                NumberOfInvolvedPeopleInterval foundIntervalInDatabase = numberOfInvolvedPeopleIntervalControllerService.getNumberOfInvolvedPeopleIntervalByID(new ObjectId(intervalID));
                if (foundIntervalInDatabase == null){
                    logger.info("There is no NumberOfInvolvedPeopleInterval in database with the id: " + intervalID + ". Removing issue from the list.");
                    listOfAllIssues.remove(issue);
                    i--;
                    continue;
                }
                issue.setNumberOfInvolvedPeople(foundIntervalInDatabase.getInterval());
            }


        }
        logger.info("After filtering the list there are " + listOfAllIssues.size() + " issues left.");
        return listOfAllIssues;
    }

    public Issue createIssue(Issue issue){

        DisturbanceType disturbanceType = disturbanceTypeControllerService.getDisturbanceTypeById(new ObjectId(issue.getDisturbanceType()));
        if ( disturbanceType == null ){
            throw new IllegalArgumentException("Could not find the given disturbanceType in database.");
        }

        if (disturbanceType.getIsNumberOfInvolvedPeopleMandatory() && (issue.getNumberOfInvolvedPeople() == null || issue.getNumberOfInvolvedPeople().isEmpty())) {
            throw new IllegalArgumentException("The field numberOfInvolvedPeople cannot be Null when disturbanceType has the id : " + issue.getDisturbanceType());
        }

        if (disturbanceType.getIsNumberOfInvolvedPeopleMandatory() &&
                numberOfInvolvedPeopleIntervalControllerService.getNumberOfInvolvedPeopleIntervalByID(
                        new ObjectId(issue.getNumberOfInvolvedPeople())) == null) {
            throw new IllegalArgumentException("The field numberOfInvolvedPeople is mandatory but there is no interval in database with the id: " + issue.getNumberOfInvolvedPeople());
        }

        final String SENT_STATUS_OBJECT_IN_DB = statusControllerService.getSentStatus().get_id();
        logger.info("Trying to create the following new issue in database: " + issue.toString());
        issue.setStatus(SENT_STATUS_OBJECT_IN_DB);
        Issue createdIssue = issueRepository.insert(issue);
        logger.info("Issue has been created. Result from server: " + createdIssue.toString());
        return createdIssue;
    }


    public Issue getIssueById( ObjectId id){
        logger.info("Trying to find the issue by id " + id.toHexString() + " in database");
        Issue foundIssueInDB = issueRepository.findBy_id(id);
        if(foundIssueInDB == null){
            logger.info("There is no issue found in database with the id: " + id.toHexString());
            return null;
        }

        DisturbanceType foundDisturbanceTypeFromDatabase = disturbanceTypeControllerService.getDisturbanceTypeById(new ObjectId(foundIssueInDB.getDisturbanceType()));

        if (foundDisturbanceTypeFromDatabase == null){
            logger.info("There is no disturbanceType in database with the id: " + foundIssueInDB.getDisturbanceType());
            return null;
        }
        foundIssueInDB.setDisturbanceType(foundDisturbanceTypeFromDatabase.getType());

        Status foundStatusFromDatabase = statusControllerService.getStatusById(new ObjectId(foundIssueInDB.getStatus()));
        if (foundStatusFromDatabase == null){
            logger.info("There is no status in database with the id: " + foundIssueInDB.getDisturbanceType());
            return null;
        }
        foundIssueInDB.setStatus(foundStatusFromDatabase.getType());

        if(foundIssueInDB.getNumberOfInvolvedPeople() != null ){
            NumberOfInvolvedPeopleInterval foundNumberOfInvolvedPeopleInterval = numberOfInvolvedPeopleIntervalControllerService.getNumberOfInvolvedPeopleIntervalByID (new ObjectId(foundIssueInDB.getNumberOfInvolvedPeople()));
            if (foundNumberOfInvolvedPeopleInterval == null){
                logger.info("There is no numberOfInvolvedPeopleInterval in database with the id: " + foundIssueInDB.getNumberOfInvolvedPeople());
                return null;
            }
            foundIssueInDB.setNumberOfInvolvedPeople(foundNumberOfInvolvedPeopleInterval.getInterval());
        }


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
