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
    public void CreatedReportShouldEqualsTheSavedReportInDatabase(){
        Report testReport = new Report(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(),"DisturbanceTypeTest"),
                "dd",
                4);

        Report savedReportInDB = controller.createReport(testReport);
        Assert.assertEquals("The next two reports should be equal, but they are not.", testReport, savedReportInDB);
    }

    @Test
    public void listOfAllReportsShoultNotBeEmpty() {
        Report testReport = new Report(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        controller.createReport(testReport);
        List<Report> reports = controller.getAllReports();

        Assert.assertFalse("List of reports should not be empty", reports.isEmpty());

    }

    @Test
    public void getListOfAllReports(){
        Report testReport = new Report(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(), "DisturbanceTypeTest"),
                "dd",
                4);

        controller.createReport(testReport);
        testReport.setDescription("DescriptionNew");

        Report updatedReportResult = controller.updateReportById(testReport.getObjectID(), testReport);
        Assert.assertEquals("The two reports should be equal but they are not", testReport.getDescription(), updatedReportResult.getDescription());
    }

    @Test
    public void getReportById(){
        Report testReport = new Report(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(),"DisturbanceTypeTest"),
                "dd",
                4);

        controller.createReport(testReport);
        Report reportFromDB = controller.getReportById(testReport.getObjectID());
        Assert.assertEquals("The reports should be empty but they are not.", testReport, reportFromDB);
    }
    
    @Test
    public void deleteReport(){
        Report testReport = new Report(new ObjectId(),
                "serverTestLocation",
                new DisturbanceType(new ObjectId(),"DisturbanceTypeTest"),
                "dd",
                4);

        controller.createReport(testReport);
        controller.deleteReportById(new ObjectId(testReport.get_id()));
        Report deletedReport = controller.getReportById(new ObjectId(testReport.get_id()));
        Assert.assertNull(deletedReport);
    }
}
