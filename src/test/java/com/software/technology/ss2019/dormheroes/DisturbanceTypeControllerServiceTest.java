package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.service.DisturbanceTypeControllerService;
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
public class DisturbanceTypeControllerServiceTest {

    @Autowired
    private DisturbanceTypeControllerService disturbanceTypeControllerService;


    @Test
    public void listOfAllDisturbanceTypesShouldNotBeEmpty(){
        ObjectId testId = new ObjectId();
        DisturbanceType testDisturbanceType = new DisturbanceType(testId,
                "created for SpringDisturbanceType"
        );
        disturbanceTypeControllerService.createDisturbanceType(testDisturbanceType);
        List<DisturbanceType> disturbanceTypeList = disturbanceTypeControllerService.getAllDisturbanceTypes();
        Assert.assertFalse("List of all DisturbanceTypes should not be empty" , disturbanceTypeList.isEmpty());
        disturbanceTypeControllerService.deleteDisturbanceTypeById(testId);
    }

    @Test
    public void deletedDisturbanceTypeShouldNotExistInDB(){
        ObjectId testId = new ObjectId();
        DisturbanceType testDisturbanceType = new DisturbanceType(testId,
                "created for SpringDisturbanceType"
        );
        disturbanceTypeControllerService.createDisturbanceType(testDisturbanceType);
        disturbanceTypeControllerService.deleteDisturbanceTypeById(testId);
        List<DisturbanceType> disturbanceTypeList = disturbanceTypeControllerService.getAllDisturbanceTypes();

        List<DisturbanceType> deletedDisturbanceType = disturbanceTypeList.stream()
                .filter(disturbanceType -> disturbanceType.get_id().equals(testId.toString()))
                .collect(Collectors.toList());

        Assert.assertTrue("The removed DisturbanceType should not exist in DB" , deletedDisturbanceType.isEmpty());
    }

    @Test
    public void getDisturbanceTypeByIdShouldReturnTheCorrectDisturbanceType(){
        ObjectId testId = new ObjectId();
        DisturbanceType testDisturbanceType = new DisturbanceType(testId,
                "created for SpringDisturbanceType"
        );
        disturbanceTypeControllerService.createDisturbanceType(testDisturbanceType);
        List<DisturbanceType> disturbanceTypeList = disturbanceTypeControllerService.getAllDisturbanceTypes();

        List<DisturbanceType> createdDisturbanceType = disturbanceTypeList.stream()
                .filter(disturbanceType -> disturbanceType.get_id().equals(testId.toString()))
                .collect(Collectors.toList());

        Assert.assertFalse("The searched DisturbanceType should be an exact match" , createdDisturbanceType.isEmpty());
        disturbanceTypeControllerService.deleteDisturbanceTypeById(testId);
    }

}
