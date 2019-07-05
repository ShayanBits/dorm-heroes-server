package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
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

    private NumberOfInvolvedPeopleInterval createTestNumberOfInvolvedPeopleInterval(){
        NumberOfInvolvedPeopleInterval testNumberOfInvolvedPeopleInterval = new NumberOfInvolvedPeopleInterval();
        testNumberOfInvolvedPeopleInterval.setInterval("0-99");
        return testNumberOfInvolvedPeopleInterval;
    }

    @Test
    public void listOfAllIntervalsShouldNotBeEmpty() {
        numberOfInvolvedPeopleIntervalControllerService.createNumberOfInvolvedPeopleInterval(createTestNumberOfInvolvedPeopleInterval());
        List<NumberOfInvolvedPeopleInterval> statusList = numberOfInvolvedPeopleIntervalControllerService.getAllIntervals();
        Assert.assertFalse("List of all Status should not be empty", statusList.isEmpty());
        numberOfInvolvedPeopleIntervalControllerService.deleteNumberOfInvolvedPeopleIntervalById(new ObjectId(createTestNumberOfInvolvedPeopleInterval().get_id()));
    }

    @Test
    public void getIntervalByIdShouldReturnTheCorrectInterval() {
        NumberOfInvolvedPeopleInterval createdNumberOfInvolvedPeopleInterval = createTestNumberOfInvolvedPeopleInterval();
        numberOfInvolvedPeopleIntervalControllerService.createNumberOfInvolvedPeopleInterval(createdNumberOfInvolvedPeopleInterval);
        NumberOfInvolvedPeopleInterval receivecNumberOfInvolvedPeopleInterval = numberOfInvolvedPeopleIntervalControllerService.getNumberOfInvolvedPeopleIntervalByID(new ObjectId(createdNumberOfInvolvedPeopleInterval.get_id()));
        Assert.assertEquals("Both interval from databse should be equal but they are not.", createdNumberOfInvolvedPeopleInterval.toString(), receivecNumberOfInvolvedPeopleInterval.toString());
        numberOfInvolvedPeopleIntervalControllerService.deleteNumberOfInvolvedPeopleIntervalById(new ObjectId(createdNumberOfInvolvedPeopleInterval.get_id()));
    }

    @Test
    public void deletedIntervalShouldNotBeInDatabase() {
        NumberOfInvolvedPeopleInterval createdNumberOfInvolvedPeopleInterval = createTestNumberOfInvolvedPeopleInterval();
        numberOfInvolvedPeopleIntervalControllerService.createNumberOfInvolvedPeopleInterval(createdNumberOfInvolvedPeopleInterval);
        numberOfInvolvedPeopleIntervalControllerService.deleteNumberOfInvolvedPeopleIntervalById(new ObjectId(createdNumberOfInvolvedPeopleInterval.get_id()));
        NumberOfInvolvedPeopleInterval numberOfInvolvedPeopleIntervalFromDatabase = numberOfInvolvedPeopleIntervalControllerService.getNumberOfInvolvedPeopleIntervalByID(new ObjectId(createdNumberOfInvolvedPeopleInterval.get_id()));
        Assert.assertNull("Intervall should be null but it is not.", numberOfInvolvedPeopleIntervalFromDatabase);
    }
}
