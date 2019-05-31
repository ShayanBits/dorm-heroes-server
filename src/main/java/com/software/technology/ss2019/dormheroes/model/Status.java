package com.software.technology.ss2019.dormheroes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class Status {
    @Id
    private ObjectId _id;

    @NotNull
    private String type;

    public Status(ObjectId _id, String type) {
        this._id = _id;
        this.type = type;
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
        return "Status{" +
                "_id=" + _id +
                ", status='" + type + '\'' +
                '}';
    }
}
