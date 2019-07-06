package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.controller.IssueController;
import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
import com.software.technology.ss2019.dormheroes.service.DisturbanceTypeControllerService;
import com.software.technology.ss2019.dormheroes.service.NumberOfInvolvedPeopleIntervalControllerService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
public class IssueControllerTest {

    @Autowired
    private IssueController issueController;

    @Autowired
    private DisturbanceTypeControllerService disturbanceTypeControllerService;

    @Autowired
    private NumberOfInvolvedPeopleIntervalControllerService numberOfInvolvedPeopleIntervalControllerService;

    public Issue createTestIssue(){
        NumberOfInvolvedPeopleInterval numberOfInvolvedPeopleInterval = new NumberOfInvolvedPeopleInterval();
        Issue testIssue = new Issue();
        testIssue.setDescription("TestDescription");
        testIssue.setLocation("testLocation");
        testIssue.setTitle("TestTitle");
        testIssue.setNumberOfInvolvedPeople(numberOfInvolvedPeopleInterval.get_id());
        return testIssue;
    }
    @Test
    public void CreatedIssueWithNumberOfInvolvedPeopleIntervalShouldEqualsTheSavedIssueInDatabase() {
        DisturbanceType disturbanceType = new DisturbanceType();
        disturbanceType.setType("TestType");
        disturbanceType.setIsNumberOfInvolvedPeopleMandatory(true);
        NumberOfInvolvedPeopleInterval numberOfInvolvedPeopleInterval = new NumberOfInvolvedPeopleInterval();
        numberOfInvolvedPeopleInterval.setInterval("0-99");

        disturbanceTypeControllerService.createDisturbanceType(disturbanceType);
        NumberOfInvolvedPeopleInterval createdInterval = numberOfInvolvedPeopleIntervalControllerService.createNumberOfInvolvedPeopleInterval(numberOfInvolvedPeopleInterval);

        Issue testIssue = createTestIssue();
        testIssue.setNumberOfInvolvedPeople(createdInterval.get_id());
        testIssue.setDisturbanceType(disturbanceType.get_id());
        Issue savedIssueInDB = issueController.createIssue(testIssue);

        issueController.deleteIssueById(new ObjectId(savedIssueInDB.get_id()));
        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(disturbanceType.get_id()));
        numberOfInvolvedPeopleIntervalControllerService.deleteNumberOfInvolvedPeopleIntervalById(new ObjectId(createdInterval.get_id()));

        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue, savedIssueInDB);
    }


    @Test
    public void CreatedIssueWithoutNumberOfInvolvedPeopleIntervalShouldEqualsTheSavedIssueInDatabase() {
        DisturbanceType disturbanceType = new DisturbanceType();
        disturbanceType.setType("TestType");
        disturbanceType.setIsNumberOfInvolvedPeopleMandatory(false);
        disturbanceTypeControllerService.createDisturbanceType(disturbanceType);

        Issue testIssue = createTestIssue();
        testIssue.setDisturbanceType(disturbanceType.get_id());
        Issue savedIssueInDB = issueController.createIssue(testIssue);

        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(disturbanceType.get_id()));
        issueController.deleteIssueById(new ObjectId(testIssue.get_id()));

        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue, savedIssueInDB);
    }

    @Test
    public void listOfAllIssuesShouldNotBeEmpty() {
        DisturbanceType disturbanceType = new DisturbanceType();
        disturbanceType.setType("TestType");
        disturbanceType.setIsNumberOfInvolvedPeopleMandatory(false);
        disturbanceTypeControllerService.createDisturbanceType(disturbanceType);

        Issue testIssue = createTestIssue();
        testIssue.setDisturbanceType(disturbanceType.get_id());
        issueController.createIssue(testIssue);
        List<Issue> issues = issueController.getAllIssues();

        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(disturbanceType.get_id()));
        issueController.deleteIssueById(new ObjectId(testIssue.get_id()));

        Assert.assertFalse("List of issues should not be empty.", issues.isEmpty());
    }

    @Test
    public void updatedIssueShouldBeSavedInDatabase() {
        DisturbanceType disturbanceType = new DisturbanceType();
        disturbanceType.setType("TestType");
        disturbanceType.setIsNumberOfInvolvedPeopleMandatory(false);
        disturbanceTypeControllerService.createDisturbanceType(disturbanceType);

        Issue testIssue = createTestIssue();
        testIssue.setDisturbanceType(disturbanceType.get_id());
        issueController.createIssue(testIssue);
        testIssue.setDescription("DescriptionNew");
        Issue updatedIssueResult = issueController.updateIssueById(new ObjectId(testIssue.get_id()), testIssue);

        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(disturbanceType.get_id()));
        issueController.deleteIssueById(new ObjectId(updatedIssueResult.get_id()));

        Assert.assertEquals("The two issues should be equal but they are not.", testIssue.getDescription(), updatedIssueResult.getDescription());
    }

    @Test
    public void getIssueByIdShouldReturnTheCorrectIssue() {
        DisturbanceType disturbanceType = new DisturbanceType();
        disturbanceType.setType("TestType");
        disturbanceType.setIsNumberOfInvolvedPeopleMandatory(false);
        Issue testIssue = createTestIssue();
        testIssue.setDisturbanceType(disturbanceType.get_id());

        disturbanceTypeControllerService.createDisturbanceType(disturbanceType);
        issueController.createIssue(testIssue);

        Issue issueFromDB = issueController.getIssueById(new ObjectId(testIssue.get_id()));

        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(disturbanceType.get_id()));
        issueController.deleteIssueById(new ObjectId(issueFromDB.get_id()));

        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue.toString(), issueFromDB.toString());
    }

    @Test
    public void deletedIssueShouldNotBeInDB() {
        DisturbanceType disturbanceType = new DisturbanceType();
        disturbanceType.setType("TestType");
        disturbanceType.setIsNumberOfInvolvedPeopleMandatory(false);
        disturbanceTypeControllerService.createDisturbanceType(disturbanceType);

        Issue testIssue = createTestIssue();
        testIssue.setDisturbanceType(disturbanceType.get_id());
        issueController.createIssue(testIssue);
        issueController.deleteIssueById(new ObjectId(testIssue.get_id()));
        Issue deletedIssue = issueController.getIssueById(new ObjectId(testIssue.get_id()));
        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(disturbanceType.get_id()));

        Assert.assertNull("Deleted issue should not exist in DB but it does exist in DB.", deletedIssue);
    }

}
