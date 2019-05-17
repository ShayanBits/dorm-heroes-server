package com.software.technology.ss2019.dormheroes.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Issue{
    @Id
    private ObjectId _id;
    private Status status;
    private String title;
    private String location;
    private DisturbanceType disturbanceType;
    private String description;
    private int numberOfInvolvedPeople;
    private Date creationDate;
    private Date lastModifiedDate;

    public Issue(ObjectId _id,Status status,String title, String location, DisturbanceType disturbanceType,
                 String description, int numberOfInvolvedPeople) {
        this._id = _id;
        this.status = status;
        this.title = title;
        this.location = location;
        this.disturbanceType = disturbanceType;
        this.description = description;
        this.numberOfInvolvedPeople = numberOfInvolvedPeople;
        this.creationDate = new Date();
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DisturbanceType getDisturbanceType() {
        return disturbanceType;
    }

    public void setDisturbanceType(DisturbanceType disturbanceType) {
        this.disturbanceType = disturbanceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String place) {
        this.location = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfInvolvedPeople() {
        return numberOfInvolvedPeople;
    }

    public void setNumberOfInvolvedPeople(int numberOfInvolvedPeople) {
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

    @Override
    public String toString() {
        return "Issue{" +
                "_id=" + _id +
                ", location='" + location + '\'' +
                ", disturbanceType=" + disturbanceType +
                ", description='" + description + '\'' +
                ", numberOfInvolvedPeople=" + numberOfInvolvedPeople +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}