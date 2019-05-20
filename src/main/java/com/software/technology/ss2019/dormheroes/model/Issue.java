package com.software.technology.ss2019.dormheroes.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Issue{

    private static final int DESCRIPTION_MAX_CHAR_SIZE = 500;

    @Id
    private ObjectId _id;

    @NotNull(message = "Location type should not be empty.")

    private String location;

    @NotNull(message = "Disturbance type should not be empty.")
    private DisturbanceType disturbanceType;

    @NotNull(message = "Description should not be empty.")
    @Size(max = DESCRIPTION_MAX_CHAR_SIZE, message = "Description should not be longer then 500 characters.")
    private String description;

    private Status status;
    private String title;
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