package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.model.Status;
import com.software.technology.ss2019.dormheroes.service.StatusControllerService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusControllerServiceTest {

    @Autowired
    private StatusControllerService statusControllerService;

    private Status createTestStatus(){
        Status testStatus = new Status();
        testStatus.setType("testStatusType");
        return testStatus;
    }

    @Test
    public void listOfAllStatusShouldNotBeEmpty() {
        Status testStatus = createTestStatus();
        statusControllerService.createStatus(testStatus);
        List<Status> statusList = statusControllerService.getAllStatus();
        Assert.assertFalse("List of all Status should not be empty", statusList.isEmpty());
        statusControllerService.deleteStatusById(new ObjectId(testStatus.get_id()));
    }

    @Test
    public void deletedStatusShouldNotExistInDB() {
        Status testStatus = createTestStatus();
        statusControllerService.createStatus(testStatus);
        statusControllerService.deleteStatusById(new ObjectId(testStatus.get_id()));
        List<Status> statusList = statusControllerService.getAllStatus();

        List<Status> deletedStatus = statusList.stream()
                .filter(status -> status.get_id().equals(testStatus.get_id()))
                .collect(Collectors.toList());

        Assert.assertTrue("The removed Status should not exist in DB", deletedStatus.isEmpty());
    }

    @Test
    public void getStatusByIdShouldReturnTheCorrectStatus() {
        Status testStatus = createTestStatus();
        statusControllerService.createStatus(testStatus);
        List<Status> statusList = statusControllerService.getAllStatus();

        List<Status> createdStatus = statusList.stream()
                .filter(status -> status.get_id().equals(testStatus.get_id()))
                .collect(Collectors.toList());

        Assert.assertFalse("The searched Status should be an exact match", createdStatus.isEmpty());
        statusControllerService.deleteStatusById(new ObjectId(testStatus.get_id()));
    }
}
