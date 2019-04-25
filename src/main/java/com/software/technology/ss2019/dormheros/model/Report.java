package com.software.technology.ss2019.dormheros.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Report {
    @Id
    private ObjectId _id;

    private String place;
    private Disturbance disturbance;
    private String description;
    private int numberOfInvolvedPeople;
    private Date creationDate;
    private Date lastModifiedDate;

    public Report(ObjectId _id, String place, Disturbance disturbance,
                  String description, int numberOfInvolvedPeople) {
        this._id = _id;
        this.place = place;
        this.disturbance = disturbance;
        this.description = description;
        this.numberOfInvolvedPeople = numberOfInvolvedPeople;
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }


    public Disturbance getDisturbance() {
        return disturbance;
    }

    public void setDisturbance(Disturbance disturbance) {
        this.disturbance = disturbance;
    }

    public String getplace() {
        return place;
    }

    public void setplace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getnumberOfInvolvedPeople() {
        return numberOfInvolvedPeople;
    }

    public void setnumberOfInvolvedPeople(int numberOfInvolvedPeople) {
        this.numberOfInvolvedPeople = numberOfInvolvedPeople;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getlastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}