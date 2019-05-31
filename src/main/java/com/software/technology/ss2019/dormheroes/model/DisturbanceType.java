package com.software.technology.ss2019.dormheroes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class DisturbanceType {

    @Id
    private ObjectId _id;

    @NotNull(message = "Disturbance type must not be empty.")
    private String type;

    public DisturbanceType() {
    this._id = new ObjectId();
    }

    public String get_id() {
        return this._id.toHexString();
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DisturbanceType{" +
                "_id=" + _id +
                ", type='" + type + '\'' +
                '}';
    }
}
