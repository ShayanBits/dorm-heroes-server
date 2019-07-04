package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.model.NumberOfInvolvedPeopleInterval;
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
public class NumberOfInvolvedPeopleIntervalTest {

    @Autowired
    private NumberOfInvolvedPeopleIntervalControllerService numberOfInvolvedPeopleIntervalControllerService;


    @Test
    public void listOfAllIntervalsShouldNotBeEmpty() {
        List<NumberOfInvolvedPeopleInterval> statusList = numberOfInvolvedPeopleIntervalControllerService.getAllIntervals();
        Assert.assertFalse("List of all Status should not be empty", statusList.isEmpty());
    }



    @Test
    public void getIntervalByIdShouldReturnTheCorrectInterval() {
        List<NumberOfInvolvedPeopleInterval> listOfAllIntervals = numberOfInvolvedPeopleIntervalControllerService.getAllIntervals();
        NumberOfInvolvedPeopleInterval selectedIntervalFromList = listOfAllIntervals.get(0);
        Assert.assertNotNull("The list should have at least one interval but the first interval is null", selectedIntervalFromList);
        NumberOfInvolvedPeopleInterval intervalFromDatabaseByID = numberOfInvolvedPeopleIntervalControllerService.getNumberOfInvolvedPeopleIntervalByID(new ObjectId(selectedIntervalFromList.get_id()));
        Assert.assertEquals("Both interval from databse should be equal but they are not.", selectedIntervalFromList.toString(), intervalFromDatabaseByID.toString());
    }
}
