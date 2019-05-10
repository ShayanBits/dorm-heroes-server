package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.controller.IssueController;
import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.model.Issue;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueControllerTest {

    @Autowired
    private IssueController controller;

    @Test
    public void CreatedIssueShouldEqualsTheSavedIssueInDatabase() {
        Issue testIssue = new Issue(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        Issue savedIssueInDB = controller.createIssue(testIssue);
        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue, savedIssueInDB);
    }

    @Test
    public void listOfAllIssuesShouldNotBeEmpty() {
        Issue testIssue = new Issue(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        controller.createIssue(testIssue);
        List<Issue> issues = controller.getAllIssues();

        Assert.assertFalse("List of issues should not be empty.", issues.isEmpty());

    }

    @Test
    public void updatedIssueShouldBeSavedInDatabase() {
        Issue testIssue = new Issue(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        controller.createIssue(testIssue);
        testIssue.setDescription("DescriptionNew");

        Issue updatedIssueResult = controller.updateIssueById(new ObjectId(testIssue.get_id()), testIssue);
        Assert.assertEquals("The two issues should be equal but they are not.", testIssue.getDescription(), updatedIssueResult.getDescription());
    }

    @Test
    public void getIssueById() {
        Issue testIssue = new Issue(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        controller.createIssue(testIssue);
        Issue issueFromDB = controller.getIssueById(new ObjectId(testIssue.get_id()));
        Assert.assertEquals("The next two issues should be equal, but they are not.", testIssue.toString(), issueFromDB.toString());
    }

    @Test
    public void deleteIssue() {
        Issue testIssue = new Issue(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        controller.createIssue(testIssue);
        controller.deleteIssueById(new ObjectId(testIssue.get_id()));
        Issue deletedIssue = controller.getIssueById(new ObjectId(testIssue.get_id()));
        Assert.assertNull("Deleted issue should not exist in DB but it does exist in DB.", deletedIssue);
    }

    @Test
    public void testIssuesShoulddBeSortedByDate() throws InterruptedException {
        Issue firstCreated = new Issue(new ObjectId(),
                "first",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);
        Thread.sleep(100);
        Issue secondCreated = new Issue(new ObjectId(),
                "second",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);
        Thread.sleep(100);
        Issue thirdCreated = new Issue(new ObjectId(),
                "third",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        List<Issue> listOfReports = new ArrayList<>();
        listOfReports.add(firstCreated);
        listOfReports.add(secondCreated);
        listOfReports.add(thirdCreated);
        Collections.sort(listOfReports);
        Assert.assertEquals("The elements in the list should be sorted by date, but they are not.", firstCreated, listOfReports.get(2));
        Assert.assertEquals("The elements in the list should be sorted by date, but they are not.", secondCreated, listOfReports.get(1));
        Assert.assertEquals("The elements in the list should be sorted by date, but they are not.", thirdCreated, listOfReports.get(0));
        Assert.assertNotEquals("The first created report should not be the first in the list.", firstCreated, listOfReports.get(0));
    }

}
