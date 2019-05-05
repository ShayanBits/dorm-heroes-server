package com.software.technology.ss2019.dormheroes;

import com.software.technology.ss2019.dormheroes.controller.ReportController;
import com.software.technology.ss2019.dormheroes.model.DisturbanceType;
import com.software.technology.ss2019.dormheroes.model.Report;
import com.software.technology.ss2019.dormheroes.repositories.ReportRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportControllerTest {



    @Autowired
    private ReportController controller;


    @Test
    public void ReportShouldEqualsTheResultReport(){
        Report demoReport = new Report(new ObjectId(), "testLocation", new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"), "dd", 4);
        Report resultReport = controller.createReport(demoReport);
        Assert.assertEquals(demoReport, resultReport);
    }

    @Test
    public void getReportListAndUpdateReport(){
        Report demoReport = new Report(new ObjectId(), "testLocation", new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"), "dd", 4);

        controller.createReport(demoReport);
        List<Report> reports =  controller.getAllReports();

        Assert.assertFalse( reports.isEmpty() );

        demoReport.setDescription("DescriptionNew");
        ObjectId o = new ObjectId("s");
        Report updatedReportResult = controller.updateReportById(new ObjectId(demoReport.get_id()), demoReport);
        Assert.assertEquals(demoReport.getDescription(), updatedReportResult.getDescription());
    }

    @Test
    public void deleteReport(){
        Report demoReport = new Report(new ObjectId(), "testLocation", new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"), "dd", 4);
        controller.createReport(demoReport);
        controller.deleteReportById(new ObjectId(demoReport.get_id()));
        Report deletedReport = controller.getReportById(new ObjectId(demoReport.get_id()));
        Assert.assertNull(deletedReport);
    }
}
