package com.software.technology.ss2019.dormheroes.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Issue{

    private static final int TITLE_MIN_CHAR_SIZE = 5;
    private static final int TITLE_MAX_CHAR_SIZE = 35;
    private static final int DESCRIPTION_MAX_CHAR_SIZE = 500;

    @Id
    private ObjectId _id;

    @NotNull(message = "Location type must not be empty.")

    private String location;

    @NotNull(message = "Disturbance type must not be empty.")
    private String disturbanceType;

    @NotNull(message = "Description must not be empty.")
    @Size(max = DESCRIPTION_MAX_CHAR_SIZE, message = "Description must not be longer then 500 characters.")
    private String description;

    private Status status;

    @NotNull(message = "Title should not be empty.")
    @Size(min=TITLE_MIN_CHAR_SIZE, message = "Title should not contains less than 5 characters.")
    @Size(max = TITLE_MAX_CHAR_SIZE, message = "Title should not contains more than 35 characters.")
    private String title;

    private String numberOfInvolvedPeople;
    private Date creationDate;
    private Date lastModifiedDate;

    public Issue() {
        this.creationDate = new Date();
        this._id = new ObjectId();
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

    public String getDisturbanceType() {
        return disturbanceType;
    }

    public void setDisturbanceType(String disturbanceType) {
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

    public String getNumberOfInvolvedPeople() {
        return numberOfInvolvedPeople;
    }

    public void setNumberOfInvolvedPeople(String numberOfInvolvedPeople) {
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