package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.controller.IssueController;
import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.model.Issue;
import com.software.technology.ss2019.dormheroes.service.DisturbanceTypeControllerService;
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
    private IssueController controller;

    @Autowired
    DisturbanceTypeControllerService disturbanceTypeControllerService;

    public Issue createTestIssueWithOptionalInterval(boolean isNumberOfInvolvedPeopleMandatory){
        DisturbanceType disturbanceType = null;
        List<DisturbanceType> listOfAllDisturbanceTypes = disturbanceTypeControllerService.getAllDisturbanceTypes();
        for (int i = 0; i < listOfAllDisturbanceTypes.size(); i++) {
            boolean isCurrentNumberOfInvolvedPeopleOptionalMandatory = listOfAllDisturbanceTypes.get(i).getIsNumberOfInvolvedPeopleMandatory();
            if( isNumberOfInvolvedPeopleMandatory && isCurrentNumberOfInvolvedPeopleOptionalMandatory ){
                disturbanceType = listOfAllDisturbanceTypes.get(i);
                break;
            }

            if (isNumberOfInvolvedPeopleMandatory == false && isCurrentNumberOfInvolvedPeopleOptionalMandatory){
                disturbanceType = listOfAllDisturbanceTypes.get(i);
                break;
            }
        }

        Issue testIssue = new Issue();
        testIssue.setDescription("TestDescription");
        testIssue.setDisturbanceType(disturbanceType);
        testIssue.setLocation("testLocation");
        testIssue.setTitle("TestTitle");
        testIssue.setNumberOfInvolvedPeople(5);
        return testIssue;
    }

    @Test
    public void CreatedIssueWithNumberOfInvolvedPeopleIntervalShouldEqualsTheSavedIssueInDatabase() {
        Issue testIssue = createTestIssueWithOptionalInterval(true);
        Issue savedIssueInDB = controller.createIssue(testIssue);
        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue, savedIssueInDB);
    }


    @Test
    public void CreatedIssueWithoutNumberOfInvolvedPeopleIntervalShouldEqualsTheSavedIssueInDatabase() {
        Issue testIssue = createTestIssueWithOptionalInterval(false);
        Issue savedIssueInDB = controller.createIssue(testIssue);
        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue, savedIssueInDB);
    }

    @Test
    public void listOfAllIssuesShouldNotBeEmpty() {
        Issue testIssue = createTestIssueWithOptionalInterval(false);
        controller.createIssue(testIssue);
        List<Issue> issues = controller.getAllIssues();
        Assert.assertFalse("List of issues should not be empty.", issues.isEmpty());

    }

    @Test
    public void updatedIssueShouldBeSavedInDatabase() {
        Issue testIssue = createTestIssueWithOptionalInterval(false);
        controller.createIssue(testIssue);
        testIssue.setDescription("DescriptionNew");

        Issue updatedIssueResult = controller.updateIssueById(new ObjectId(testIssue.get_id()), testIssue);
        Assert.assertEquals("The two issues should be equal but they are not.", testIssue.getDescription(), updatedIssueResult.getDescription());
    }

    @Test
    public void getIssueByIdShouldReturnTheCorrectIssue() {
        Issue testIssue = createTestIssueWithOptionalInterval(false);
        controller.createIssue(testIssue);
        Issue issueFromDB = controller.getIssueById(new ObjectId(testIssue.get_id()));
        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue.toString(), issueFromDB.toString());
    }

    @Test
    public void deletedIssueShouldNotBeInDB() {
        Issue testIssue = createTestIssueWithOptionalInterval(false);
        controller.createIssue(testIssue);
        controller.deleteIssueById(new ObjectId(testIssue.get_id()));
        Issue deletedIssue = controller.getIssueById(new ObjectId(testIssue.get_id()));
        Assert.assertNull("Deleted issue should not exist in DB but it does exist in DB.", deletedIssue);
    }

}
