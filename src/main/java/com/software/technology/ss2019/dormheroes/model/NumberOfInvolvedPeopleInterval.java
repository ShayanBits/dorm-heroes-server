package com.software.technology.ss2019.dormheroes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class NumberOfInvolvedPeopleInterval {
    @Id
    private ObjectId _id;

    @NotNull
    private String number;

    public NumberOfInvolvedPeopleInterval() {
       this._id = new ObjectId();
    }

    public String get_id() {
        return this._id.toHexString();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Status{" +
                "_id=" + _id +
                ", number='" + number + '\'' +
                '}';
    }
}
