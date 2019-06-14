package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
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
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
public class DisturbanceTypeControllerServiceTest {

    @Autowired
    private DisturbanceTypeControllerService disturbanceTypeControllerService;

    private DisturbanceType createTestDisturbanceType(){
        DisturbanceType testDisturbanceType = new DisturbanceType();
        testDisturbanceType.setType("testType");
        return testDisturbanceType;
    }

    @Test
    public void listOfAllDisturbanceTypesShouldNotBeEmpty(){
        DisturbanceType testDisturbanceType = createTestDisturbanceType();
        disturbanceTypeControllerService.createDisturbanceType(testDisturbanceType);
        List<DisturbanceType> disturbanceTypeList = disturbanceTypeControllerService.getAllDisturbanceTypes();
        Assert.assertFalse("List of all DisturbanceTypes should not be empty" , disturbanceTypeList.isEmpty());
        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(testDisturbanceType.get_id()));
    }

    @Test
    public void deletedDisturbanceTypeShouldNotExistInDB(){
        DisturbanceType testDisturbanceType = createTestDisturbanceType();
        disturbanceTypeControllerService.createDisturbanceType(testDisturbanceType);
        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(testDisturbanceType.get_id()));
        List<DisturbanceType> disturbanceTypeList = disturbanceTypeControllerService.getAllDisturbanceTypes();

        List<DisturbanceType> deletedDisturbanceType = disturbanceTypeList.stream()
                .filter(disturbanceType -> disturbanceType.get_id().equals(testDisturbanceType.get_id()))
                .collect(Collectors.toList());

        Assert.assertTrue("The removed DisturbanceType should not exist in DB" , deletedDisturbanceType.isEmpty());
    }

    @Test
    public void getDisturbanceTypeByIdShouldReturnTheCorrectDisturbanceType(){
        DisturbanceType testDisturbanceType = createTestDisturbanceType();
        disturbanceTypeControllerService.createDisturbanceType(testDisturbanceType);
        List<DisturbanceType> disturbanceTypeList = disturbanceTypeControllerService.getAllDisturbanceTypes();

        List<DisturbanceType> createdDisturbanceType = disturbanceTypeList.stream()
                .filter(disturbanceType -> disturbanceType.get_id().equals(testDisturbanceType.get_id()))
                .collect(Collectors.toList());

        Assert.assertFalse("The searched DisturbanceType should be an exact match" , createdDisturbanceType.isEmpty());
        disturbanceTypeControllerService.deleteDisturbanceTypeById(new ObjectId(testDisturbanceType.get_id()));
    }

}
