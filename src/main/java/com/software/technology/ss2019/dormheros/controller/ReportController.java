package com.software.technology.ss2019.dormheros.controller;

import com.software.technology.ss2019.dormheros.model.Report;
import com.software.technology.ss2019.dormheros.repositories.ReportRepository;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import javax.validation.Valid;
import java.util.List;
import java.util.Date;


@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Report createReport(@Valid @RequestBody Report report){
        report.set_id(ObjectId.get());
        return reportRepository.save(report);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Report getReportById(@PathVariable("id") ObjectId id){
        return reportRepository.findBy_id(id);
        //TODO specify required and not required variables for reports
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Report updateReportById(@PathVariable("id") ObjectId id, @Valid @RequestBody Report report){
        report.set_id(id);
        report.setLastModifiedDate(new Date());
        return reportRepository.save(report);
        //TODO: update object instead of saving a new on
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteReportById(@PathVariable("id") ObjectId id){
        reportRepository.delete(reportRepository.findBy_id(id));
    }

}
